package com.lwxf.industry4.webapp.common.shiro;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.concurrent.TimeUnit;

import com.lwxf.industry4.webapp.baseservice.cache.RedisUtils;
import com.lwxf.industry4.webapp.common.session.MySessionContext;
import com.lwxf.industry4.webapp.config.FilterConfig;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.UserFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lwxf.commons.agent.LwxfUserAgent;
import com.lwxf.commons.constant.CommonConstant;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.mobile.WeixinUtils;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.common.mobile.WeixinUtils;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import org.springframework.stereotype.Component;

/**
 *	功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 17:09:25
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

public class LwxfUserFilter extends UserFilter {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	@Override
	public boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		String requestURI = httpServletRequest.getRequestURI();
		// 微信端登陆处理
		if (WebUtils.getCurrUserId() == null){
			if(ShiroUtil.checkAuthPagePath(requestURI)){
				if(WebUtils.getUserAgent().isWeixin()){
					doWeixinRequest(request, response);
					return false;
				} else if(WebUtils.getUserAgent().isMobile()){
					String appToken = httpServletRequest.getParameter(WebConstant.REQUEST_PARAMETER_KEY_APP_TOKEN);
					if(LwxfStringUtils.isNotBlank(appToken)){
						this.doAppRequest(httpServletRequest,response,appToken);
						return false;
					}
				}
			}
		}
		return super.onPreHandle(request, response, mappedValue);
	}

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		if (isLoginRequest(request, response)) {
			return true;
		}
		Subject subject = getSubject(request, response);
		// If principal is not null, then the user is known and should be allowed access.
		boolean ret = subject.getPrincipal() != null;
		if (!ret) {
			return ret;
		}
		if (!(AppBeanInjector.configuration.isOnProd() || AppBeanInjector.configuration.isOnTest())) {
			return ret;
		}
		if (WebUtils.isAjaxRequest(request)) {
			return ret;
		}
		if (!WebUtils.getUserAgent().isWeixin()) {
			return ret;
		}
		UserThirdInfo uti = AppBeanInjector.userThirdInfoFacade.findByUserId(WebUtils.getCurrUserId());
		if (uti == null) {
			return ret;
		}
		/*ret = LwxfStringUtils.isNotEmpty(uti.getWxOpenId());
		if (!ret) {
			subject.logout();
			return false;
		}*/
		return ret;
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		HttpServletRequest httpReq = (HttpServletRequest) request;
		if (WebUtils.isAjaxRequest(request)) {
			try {
				StringBuilder sb = new StringBuilder();
				LwxfStringUtils.concat(sb,"      ==== 拒绝ajax请求 ====");
				LwxfStringUtils.concat(sb,"            cause : ", WebUtils.getCurrUserId() == null?"用户未登录":"没有权限");
				LwxfStringUtils.concat(sb,"        UserAgent : ", WebUtils.getOriginalUserAgent());
				LwxfStringUtils.concat(sb,"      RequestPath : ", httpReq.getServletPath());
				LwxfStringUtils.concat(sb,"          Referer : ", httpReq.getHeader("Referer"));
				LwxfStringUtils.concat(sb,"      X-PHPSESSID : ", httpReq.getHeader(CommonConstant.REQUEST_HEADER_KEY_X_PHPSESSIONID));
				LwxfStringUtils.concat(sb,"            X-SID : ", httpReq.getHeader(CommonConstant.REQUEST_HEADER_KEY_X_SID));
				LwxfStringUtils.concat(sb,"           X-C-ID : ", httpReq.getHeader(WebUtils.REQUEST_HEADER_X_C_ID));
				LwxfStringUtils.concat(sb,"              X-P : ", httpReq.getHeader(WebConstant.REQUEST_HEADER_X_P));
				LwxfStringUtils.concat(sb,"        sessionId : ", httpReq.getSession().getId());

				Cookie[] cookies = httpReq.getCookies();
				StringBuilder cookieSb = new StringBuilder();
				if(null != cookies){
					for (Cookie cookie : cookies) {
						cookieSb.append(cookie.getName()).append(" = ").append(cookie.getValue()).append("|");
					}
				}
				LwxfStringUtils.concat(sb,"           Cookie : ", cookieSb.length()==0?"没有任何cookie信息":cookieSb.toString());
				logger.error(sb.toString());
			} catch (Exception e) {
				logger.error("调试代码错误");
			}

			HttpServletResponse httpResponse = (HttpServletResponse) response;
			httpResponse.setStatus(401);
//			httpResponse.setHeader("Tag", "1");
			response.getWriter().write(WebConstant.REQUEST_RESULT_AJAX_EMPTY_OBJ);
			return false;
		} else {
			LwxfUserAgent userAgent = WebUtils.getUserAgent();
			if (userAgent.isWeixin()) {
				this.doWeixinRequest(request, response);
				return false;
			} else if(userAgent.isMobile()){
				String appToken = httpReq.getParameter(WebConstant.REQUEST_PARAMETER_KEY_APP_TOKEN);
				if(LwxfStringUtils.isNotBlank(appToken)){
					this.doAppRequest(httpReq,response,appToken);
				}
			}
			return super.onAccessDenied(request, response);
		}
	}


	@Override
	protected void saveRequest(ServletRequest request) {
		super.saveRequest(request);
		HttpServletRequest currRequest = (HttpServletRequest)request;
		String sessionId =currRequest.getSession().getId();
		currRequest.getQueryString();
		AppBeanInjector.redisUtils.hPut(sessionId,WebConstant.KEY_REDIS_CACHE_SAVE_REQUEST_URI,currRequest.getRequestURI(),10,TimeUnit.SECONDS);
		AppBeanInjector.redisUtils.hPut(sessionId,WebConstant.KEY_REDIS_CACHE_SAVE_REQUEST_QRY,currRequest.getQueryString(),10,TimeUnit.SECONDS);
	}

	private void doWeixinRequest(ServletRequest request, ServletResponse response) throws Exception{
		saveRequest(request);
		this.logger.debug(" ************ 微信授权通过前，保存当前的请求对象");
		this.logger.debug(" ************ Shiro to saved request path : {}",((HttpServletRequest)request).getRequestURI());
		this.logger.debug(" ************ Shiro to saved request sessionId : {}",((HttpServletRequest)request).getSession().getId());
		String loginUrl = WeixinUtils.getWebAuthorizationPath(WebUtils.getDomainUrl().concat("/wx/login"), AppBeanInjector.wxMpConfigStorage.getAppId(), ((HttpServletRequest) request).getSession().getId());
		WebUtils.issueRedirect(request, response, loginUrl);
	}

	// 处理app的登录
	private String appLoginUrl = "/app/login?{0}={1}&{2}={3}&{4}={5}";
	private void doAppRequest(HttpServletRequest request, ServletResponse response,String appToken) throws Exception{
		saveRequest(request);
		this.logger.debug(" ************ 通过APP访问授权通过前，保存当前的请求对象");
		this.logger.debug(" ************ Shiro to saved request path : {}",request.getRequestURI());
		this.logger.debug(" ************ Shiro to saved request sessionId : {}",request.getSession().getId());
		String companyId = request.getParameter(WebConstant.REQUEST_PARAMETER_KEY_CID);
		if(companyId == null){
			companyId = WebConstant.STRING_EMPTY;
		}
		String userId = request.getParameter(WebConstant.REQUEST_PARAMETER_KEY_UID);
		if(userId == null){
			userId = WebConstant.STRING_EMPTY;
		}
		WebUtils.issueRedirect(request, response, LwxfStringUtils.format(appLoginUrl,WebConstant.REQUEST_PARAMETER_KEY_APP_TOKEN,appToken,WebConstant.REQUEST_PARAMETER_KEY_CID,companyId,WebConstant.REQUEST_PARAMETER_KEY_UID,userId));
	}

	@Override
	public String getLoginUrl() {
		/*HttpServletRequest request = WebUtils.getHttpServletRequest();
		this.logger.error("********************* user jump to login page *********************");
		this.logger.error("********************* request Referer：{}",request.getRequestURL());
		this.logger.error("*********************     request url：{}",request.getRequestURL());
		this.logger.error("*********************  request reffer：{}",request.getHeader("Referer"));*/
		return super.getLoginUrl();
	}

	/*@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		if(WebUtils.isAjaxRequest(request))
			redirectToLogin(request, response);
		else
			super.onAccessDenied(request, response);
		return false;
	}*/
}
