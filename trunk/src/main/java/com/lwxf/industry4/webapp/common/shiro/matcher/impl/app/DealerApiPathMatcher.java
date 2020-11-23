package com.lwxf.industry4.webapp.common.shiro.matcher.impl.app;

import javax.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.shiro.matcher.impl.base.BaseApiPathMatcher;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;

/**
 * 功能：经销商端APP Shiro权限验证
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/12/6 14:53
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DealerApiPathMatcher extends BaseApiPathMatcher {
	public DealerApiPathMatcher(){
		this.reqApiPattern = Pattern.compile("/app/b/.+");
	}
	/**
	 *
	 * @param request
	 * @param action
	 * @param servletPath
	 * @param referer
	 * @return
	 */
	@Override
	public String matcher(HttpServletRequest request, String action, String servletPath, String referer) {
		LoginedUser loginedUser = WebUtils.getCurrUser();
		Matcher matcher = reqApiPattern.matcher(servletPath);
		if(matcher.matches()){
			Integer userType = loginedUser.getType();
			if(userType == null || userType.intValue() != UserType.DEALER.getValue()){
				return WebConstant.STRING_404;
			}
			return this.doPerm(request,userType);
		}
		return null;
	}
}
