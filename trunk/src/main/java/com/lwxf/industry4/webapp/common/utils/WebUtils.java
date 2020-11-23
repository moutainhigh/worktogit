package com.lwxf.industry4.webapp.common.utils;

import com.lwxf.commons.agent.AccessClient;
import com.lwxf.commons.agent.LwxfUserAgent;
import com.lwxf.commons.collection.http.RequestMap;
import com.lwxf.commons.collection.http.RequestThreadLocal;
import com.lwxf.commons.constant.CommonConstant;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManualData;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.SavedRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.lwxf.industry4.webapp.common.constant.WebConstant.SESSION_KEY_CURR_USER;


/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 16:42:51
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public abstract class WebUtils extends org.apache.shiro.web.util.WebUtils {
	public static int page_state_login=1;
	public static final String REQUEST_HEADER_X_C_ID ="X-C-ID";
	private static final String REQUESTMAP_KEY_HTTP_SERVLET_REQUEST = "HttpServletRequest";
	private static final String REQUESTMAP_KEY_HTTP_SERVLET_RESPONSE = "HttpServletResponse";
	private static final String REQUEST_HEADER_USER_AGENT_KEY="User-Agent";
	private static final String SESSION_KEY_LWXF_CLIENT_IP ="LWXF_CLIENT_IP";
	private static final String MQ_USER_TAG="X-TAG";
	private static final String CURR_LOGIN_USER_ID="curr_login_user_id";
	private static final String CURR_COMPANY_ID="curr_company_id";
	private static final String FINISHED_STORAGE_ID="finished_storage_id";
	private static final String CURR_BRANCH_ID="curr_branch_id";
	/**
	 * 仅用于本地测试环境
	 */
	private static final String DEFAULT_CLIENT_IP="223.88.19.158";
	private static final String[] HEADERS_TO_TRY = {
			"X-Real-IP",
			"X-Forwarded-For",
			"Proxy-Client-IP",
			"WL-Proxy-Client-IP",
			"HTTP_X_FORWARDED_FOR",
			"HTTP_X_FORWARDED",
			"HTTP_X_CLUSTER_CLIENT_IP",
			"HTTP_CLIENT_IP",
			"HTTP_FORWARDED_FOR",
			"HTTP_FORWARDED",
			"HTTP_VIA",
			"REMOTE_ADDR"};
	private static final RequestMap requestMap = new RequestMap();
	private static final Logger logger = LoggerFactory.getLogger(WebUtils.class);

	/**
	 * 将缓存数据压入requestMap中
	 * @param key
	 * @param data
	 */
	public static void putDataToRequestMap(Object key, Object data){
		org.springframework.util.Assert.isTrue(!requestMap.containsKey(key), "线程中埋点key重复key:" + String.valueOf(key));
		requestMap.put(key,data);
	}

	/**
	 * 从requestMap中获取缓存数据
	 * @param key
	 * @return
	 */
	public static Object getDataFromRequestMap(Object key){
		return requestMap.get(key);
	}

	/**
	 * 从requestMap中获取缓存数据
	 * @param key
	 * @return
	 */
	public static Object removeDataFromRequestMap(Object key){
		return requestMap.remove(key);
	}

	/**
	 * 将当前的请求对象放入requestMap中（无特殊需要请不要调用改方法）
	 * @param request
	 */
	private static void putHttpServletRequest(HttpServletRequest request) {
		requestMap.put(REQUESTMAP_KEY_HTTP_SERVLET_REQUEST, request);
	}

	/**
	 * 将当前的请求响应对象放入requestMap中（无特殊需要请不要调用改方法）
	 * @param response
	 */
	private static void putHttpServletResponse(HttpServletResponse response) {
		requestMap.put(REQUESTMAP_KEY_HTTP_SERVLET_RESPONSE, response);
	}

	public static void setRequestEnvironment(HttpServletRequest request,HttpServletResponse response){
		RequestThreadLocal.clear();
		putHttpServletRequest(request);
		putHttpServletResponse(response);
	}
	public static HttpSession getHttpSession() {
		return getHttpServletRequest().getSession();
	}

	/**
	 * 从requestMap中获取当前的请求对象
	 * @return
	 */
	public static HttpServletRequest getHttpServletRequest() {
		if(requestMap == null){
			return null;
		}
		return (HttpServletRequest) requestMap.get(REQUESTMAP_KEY_HTTP_SERVLET_REQUEST);
	}

	/**
	 * 从requestMap中获取当前的请求响应对象
	 * @return
	 */
	public static HttpServletResponse getHttpServletResponse() {
		if(requestMap == null){
			return null;
		}
		return (HttpServletResponse) requestMap.get(REQUESTMAP_KEY_HTTP_SERVLET_RESPONSE);
	}

	/**
	 * 获取当前登录用户id
	 * @return
	 */
	public static String getCurrUserId(){
		Object currUserId = requestMap.get(CURR_LOGIN_USER_ID);
		if(null != currUserId){
			return currUserId.toString();
		}
		return ShiroUtil.getCurrUserId();
	}



	/**
	 * 小程序和pc获取uid
	 * @return
	 */
	public static String getUId() {
		String currUserId = "";
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		if (atoken != null) {
			MapContext mapInfo = LoginUtil.checkLogin(atoken);
			currUserId = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		}
		if(currUserId == null){
			currUserId = getCurrUserId();
		}
		return currUserId;
	}

	/**
	 * 小程序和pc获取uid
	 * @return
	 */
	public static String getBId() {
		String branchId = "";
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		if (atoken != null) {
			MapContext mapInfo = LoginUtil.checkLogin(atoken);
			branchId = mapInfo.get("branchId") == null ? null : mapInfo.get("branchId").toString();
		}
		if(branchId == null){
			branchId = getCurrBranchId();
		}
		return branchId;
	}


	public static String getCid() {
		String branchId = "";
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		if (atoken != null) {
			MapContext mapInfo = LoginUtil.checkLogin(atoken);
			branchId = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
		}
		if(branchId == null){
			branchId = getCurrBranchId();
		}
		return branchId;
	}

	/**
	 * 获取当前登录用户企业id
	 * @return
	 */
	public static String getCurrBranchId(){
		HttpSession session = WebUtils.getHttpSession();
		if(session == null){
			return null;
		}
		String currBranchId = (String) session.getAttribute(CURR_BRANCH_ID);
		if(null == currBranchId){
			LoginedUser currUser = ShiroUtil.getCurrUser();
			if(currUser==null){
				return null;
			}
			currBranchId =currUser.getBranchId();
			session.setAttribute(CURR_BRANCH_ID,currBranchId);
		}
		return currBranchId;
	}



	/**
	 * 获取当前登录用户对象
	 * @return
	 */
	public static LoginedUser getCurrUser(){
		HttpSession session = WebUtils.getHttpSession();
		if(session == null){
			return null;
		}
		LoginedUser user = (LoginedUser)session.getAttribute(SESSION_KEY_CURR_USER);
		if(null == user){
			user = ShiroUtil.getCurrUser();
			session.setAttribute(SESSION_KEY_CURR_USER,user);
		}
		return user;
	}

	/**
	 * 从请求头中获取mq的用户tag
	 * @return
	 */
	public static String getRabbitMQUserTag(){
		Object tagObj = requestMap.get(MQ_USER_TAG);
		if(tagObj != null){
			return tagObj.toString();
		}
		String tag = getHttpServletRequest().getHeader(MQ_USER_TAG);
		if(LwxfStringUtils.isBlank(tag)){
			logger.error("**********{} 请求头中未设置X-TAG标识", getHttpServletRequest().getRequestURL().toString());
		}
		return tag;
	}

	/**
	 * 设置当前的登录用户id
	 * @param userId
	 */
	public static void setCurrUserId(String userId){
		requestMap.put(CURR_LOGIN_USER_ID,userId);
	}

	/**
	 * 设置当前用户的mq消息的tag标识
	 * @param tag
	 */
	public static void setRabbitMQUserTag(String tag){
		requestMap.put(MQ_USER_TAG,tag);
	}

	/**
	 * 设置当前公司id（当前单位）
	 * @param companyId
	 */
	public static void setCurrCompanyId(String companyId){
		requestMap.put(CURR_COMPANY_ID,companyId);
	}

	/**
	 * 获取当前公司的id
	 * @return
	 */
	public static String getCurrCompanyId(){
		if(requestMap == null){
			return null;
		}
		String currCompanyId = (String)requestMap.get(CURR_COMPANY_ID);
		if(null != currCompanyId){
			return currCompanyId;
		}

		currCompanyId = getHttpServletRequest().getHeader(REQUEST_HEADER_X_C_ID);
		if(LwxfStringUtils.isBlank(currCompanyId) || currCompanyId.equals("undefined")){
			CompanyEmployee companyEmployee = AppBeanInjector.companyEmployeeService.selectByUserId(WebUtils.getCurrUserId());
			currCompanyId = companyEmployee==null||!companyEmployee.getStatus().equals(EmployeeStatus.NORMAL.getValue())?null:companyEmployee.getCompanyId();
		}

		requestMap.put(CURR_COMPANY_ID,currCompanyId);
		return currCompanyId;
	}

//	public static String getFinishedStorageId(){
//		if(requestMap == null){
//			return null;
//		}
//		String finished =(String)requestMap.get(FINISHED_STORAGE_ID);
//		if(finished!=null){
//			return finished;
//		}
//		Storage storage = AppBeanInjector.storageService.findOneByProductCategoryKey(ProductCategoryKey.finished.getId(), WebUtils.getCurrBranchId());
//		if(storage!=null){
//			requestMap.put(FINISHED_STORAGE_ID,storage.getId());
//			return storage.getId();
//		}
//		return null;
//	}



	/**
	 * 获取用户的agent
	 * @return
	 */
	private static LwxfUserAgent getUserAgent(HttpServletRequest request) {
		HttpSession httpSession = request.getSession();
		LwxfUserAgent agent = null;
		if (httpSession != null) {
			agent = (LwxfUserAgent) httpSession.getAttribute("user_agent");
		}
		if (null == agent) {
			String userAgent = request.getHeader(REQUEST_HEADER_USER_AGENT_KEY);
			agent = LwxfUserAgent.parseUserAgentString(userAgent);
			if (userAgent != null && httpSession != null) {
				httpSession.setAttribute("user_agent", agent);
			}
			if (null == userAgent) {
				logger.error("userAgent= "+(httpSession==null?"":httpSession.getId()) + getRequestUrl(request));
			}
		}
		return agent;
	}

	/**
	 * 获取用户的agent
	 * @return
	 */
	public static LwxfUserAgent getUserAgent() {
		HttpServletRequest request = getHttpServletRequest();
		Assert.notNull(request,"request对象为空");
		return getUserAgent(request);
	}

	/**
	 * 获取请求路径（不含请求方法信息 - request.getMethod()）
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		return getRequestPath(request, false);
	}

	/**
	 * 获取请求的url（包含求方法信息 - request.getMethod()）
	 * @param request
	 * @return
	 */
	public static String getRequestUrl(HttpServletRequest request) {
		return getRequestPath(request, true);
	}

	/**
	 * 获取请求响应的返回路径
	 * @param page
	 * @return
	 */
	private static  final String PAGE_PATH_TPL="{0}/{1}";
	public static String getResponsePagePath(String page) {
		LwxfUserAgent userAgent = getUserAgent();
		String path;
		if(userAgent.isPc()){
			path = AccessClient.pc.toString();
		}else{
			path=AccessClient.mobile.toString();
		}
		boolean isErrorPage=WebConstant.STRING_404.equals(page)||WebConstant.STRING_ERROR.equals(page) ||"org_userlimit".equals(page);
		if(AppBeanInjector.configuration.isOnLocalTest() && userAgent.isMobile()){
			path = path + "/pub";
		}else if(!isErrorPage &&  AppBeanInjector.configuration.isOnFEPublic()) {
			path = path + "/pub";
		}
		return LwxfStringUtils.format(PAGE_PATH_TPL,path,page);
	}

	/**
	 * 获取后台管理的响应路径
	 * @param page
	 * @return
	 */
	public static String getAdminsPagePath(String page) {
		return getResponsePagePath(page);
	}

	/**
	 * 获取门户站点的响应路径
	 * @param page
	 * @return
	 */
	public static String getPortalsPagePath(String page) {
		return getResponsePagePath(page);
	}

	/**
	 * 获取当前用户的默认相应路径
	 * @return
	 */
	public static String getCurrUserDefaultPage() {
		LoginedUser user = getCurrUser();
		if(null == user){
			return "redirect:/login";
		}
		if(isCurrCompanyEmployee(user.getCompanyEmployee())){
			return getResponsePagePath("admin");
		}
		return getResponsePagePath("mall");
	}

	public static boolean isCurrCompanyEmployee(CompanyEmployee employee){
		return  null != employee && employee.getCompanyId().equals(getCurrCompanyId());
	}

	/**
	 * 获取404错误页面
	 * @return
	 */
	public static String getError404PagePath(){
		return getResponsePagePath("404");
	}

	/**
	 * 获取404错误页面
	 * @return
	 */
	public static String getErrorPagePath(){
		return getResponsePagePath("error");
	}
	/**
	 * 判断是否为ajax请求
	 * @param request
	 * @return
	 */
	public static boolean isAjaxRequest(ServletRequest request) {
		HttpServletRequest httpRequest = null;
		if (request instanceof HttpServletRequest) {
			httpRequest = (HttpServletRequest) request;
		}
		if (httpRequest == null) {
			return false;
		}
		if (httpRequest.getHeader(CommonConstant.REQUEST_HEADER_KEY_AJAX_TAG) != null) {
			return true;
		}
		String accept = httpRequest.getHeader("accept");
		if (accept == null) {
			return false;
		}
		return accept.indexOf(WebConstant.RESPONSE_CONTENT_TYPE_JSON) > -1;

	}

	/**
	 * 获取当前服务的根路径，后缀不带/
	 * @return
	 */
	public static String getDomainUrl() {
		String domainUrl = AppBeanInjector.configuration.getDomainUrl();
		if (domainUrl != null) {
			return domainUrl;
		}
		// 没有配置时自动生成
		HttpServletRequest request = WebUtils.getHttpServletRequest();
		if (request == null) {
			return "null";
		}
		String url = request.getRequestURL().toString();
		String sPath=request.getServletPath();
		if(!WebConstant.STRING_DIVIDE.equals(sPath)) {
			url = url.replace(sPath, WebConstant.STRING_EMPTY);
		}
		if(url.endsWith(WebConstant.STRING_DIVIDE)) {
			url = url.substring(0, url.length() - 1);
		}
		AppBeanInjector.configuration.setDomainUrl(url);
		return url;
	}

	/**
	 * 获取请求端的ip地址
	 * @return
	 */
	public static String getClientIpAddress() {
		if (!AppBeanInjector.configuration.isOnProd()) {
			return DEFAULT_CLIENT_IP;
		}
		HttpServletRequest request = getHttpServletRequest();
		HttpSession session = request.getSession(false);
		String ip = null;
		if (session != null) {
			ip = (String) request.getSession().getAttribute(SESSION_KEY_LWXF_CLIENT_IP);
			if (ip != null) {
				return ip;
			}
		}
		boolean isOK = false;
		for (String header : HEADERS_TO_TRY) {
			ip = request.getHeader(header);
			if (ip != null && ip.length() != 0 && !WebConstant.STRING_UNKNOWN.equalsIgnoreCase(ip)) {
				isOK = true;
				break;
			}
		}
		if (!isOK) {
			ip = request.getRemoteAddr();
		}

		//对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
		int firstIndex = ip.indexOf(",");
		if (firstIndex > 0 && LwxfStringUtils.isNotEmpty(ip)) {//"***.***.***.***".length() = 15
			ip = ip.substring(0, firstIndex);
		}
		if (session != null) {
			session.setAttribute(SESSION_KEY_LWXF_CLIENT_IP, ip);
		}
		return ip;
	}

	/**
	 * 为了埋数据时候简化代码，提供该方法
	 *
	 * @return
	 */
	public static TSManualData getTSManualData() {
		TSManualData tsManualData;
		Object object = WebUtils.getDataFromRequestMap(WebConstant.TSMANAGER_MANUAL_ACTION_FLAG);
		if (object == null) {
			tsManualData = TSManualData.newOne();
			WebUtils.putDataToRequestMap(WebConstant.TSMANAGER_MANUAL_ACTION_FLAG, tsManualData);
		} else {
			tsManualData = (TSManualData) object;
		}
		return tsManualData;
	}

	/**
	 * 判断线程中是否埋过该key
	 *
	 * @param key
	 * @return
	 */
	public static boolean requestMapContainsKey(String key) {
		return requestMap.containsKey(key);
	}

	/**
	 * 根据操作类型获取验证码:注册，邮件邀请
	 * @param operateType
	 * @return
	 */
	public static String getAuthcodeByOperateType(String operateType){
		HttpSession session = getHttpSession();
		if (null == session) {
			throw ExceptionGenerateFactory.createException(ErrorCodes.SYS_SESSION_TIMEOUT_00015);
		}
		if(logger.isDebugEnabled()){
			logger.debug("getAuthcodeByOperateType-session-id:"+session.getId());
		}
		return String.valueOf(session.getAttribute(operateType));
	}

	/**
	 * 获取请求路径
	 * @param request
	 * @param includeMethod：是否包含请求方法
	 * @return
	 */
	private static String getRequestPath(HttpServletRequest request, boolean includeMethod) {
		StringBuilder stringBuffer = new StringBuilder();
		String servletPath = request.getServletPath();
		String pathInfo = request.getPathInfo();
		String qry = request.getQueryString();

		if (includeMethod) {
			stringBuffer.append(request.getMethod());
		}
		if (servletPath != null) {
			stringBuffer.append(servletPath);
		}
		if (pathInfo != null) {
			stringBuffer.append(pathInfo);
		}
		if (qry != null) {
			stringBuffer.append("?").append(qry);
		}
		return stringBuffer.toString();
	}

	public static String getOriginalUserAgent() {
		HttpServletRequest request = getHttpServletRequest();
		String userAgent = request.getHeader(REQUEST_HEADER_USER_AGENT_KEY);
		if(null == userAgent){
			userAgent = "";
		}
		return userAgent;
	}


	public static final String redirectToSavedRequest(ServletRequest request, String fallbackUrl){
		String successUrl = fallbackUrl;
		HttpServletRequest currRequest = (HttpServletRequest)request;
		String sessionId = currRequest.getSession().getId();

		String savedRequestUri = (String) AppBeanInjector.redisUtils.hGet(sessionId,WebConstant.KEY_REDIS_CACHE_SAVE_REQUEST_URI);
		logger.debug("******************* 登陆后的跳转路径："+savedRequestUri);
		if(null != savedRequestUri){
			successUrl = savedRequestUri;
			String savedRequestQRY = (String) AppBeanInjector.redisUtils.hGet(sessionId,WebConstant.KEY_REDIS_CACHE_SAVE_REQUEST_QRY);
			if(null != savedRequestQRY){
				successUrl=successUrl.concat("?").concat(savedRequestQRY);
			}
		}
		logger.debug("******************* 登陆后的跳转路径：successUrl1 = "+successUrl);
		logger.debug(" ************ Curr request path : {}",currRequest.getRequestURI());
		logger.debug(" ************ Curr request sessionId : {}",sessionId);
		if(successUrl == null){
			SavedRequest savedRequest = WebUtils.getSavedRequest(request);
			if (savedRequest != null && AccessControlFilter.GET_METHOD.equalsIgnoreCase(savedRequest.getMethod())) {
				successUrl = savedRequest.getRequestUrl().concat("?").concat(WebConstant.REQUEST_PARAMETER_KEY_CID).concat("=").concat(currRequest.getParameter(WebConstant.REQUEST_PARAMETER_KEY_CID));
				logger.debug(" ************ APP授权通过后，获取到的Saved请求对象");
				logger.debug(" ************ Shiro saved request path : {}",successUrl);
			}
		}
		if (successUrl == null) {
			successUrl = WebConstant.REDIRECT_PATH_MALL;
		}

		logger.debug("******************* 登陆后的跳转路径：successUrl2 = "+successUrl);
		logger.debug("******************* 登陆后的跳转路径：redirectPath = "+LwxfStringUtils.format(WebConstant.REDIRECT_PATH_TEMPLATE,successUrl));
		return LwxfStringUtils.format(WebConstant.REDIRECT_PATH_TEMPLATE,successUrl);
	}

	public static String getPayPricePath() {
		return getResponsePagePath("price");
	}

	public static String dateNowFomatToStr() {
		Date date = new Date();
		String strDateFormat = "yyyy-MM-dd HH:mm:ss";
		SimpleDateFormat sdf = new SimpleDateFormat(strDateFormat);
		return sdf.format(date);
	}

	/**
	 * 获取系统用户
	 * @param companyId
	 * @return
	 */
	private static final String SYSTEM_USER_ID = "system";
	private static final String SYSTEM_USER_NAME = "System";
	public static User getSystemUser(String companyId) {
		User user = new User();
		user.setId(SYSTEM_USER_ID);
		user.setName(SYSTEM_USER_NAME);
		user.setAvatar(AppBeanInjector.configuration.getUserDefaultAvatar());
		return user;
	}


}
