package com.lwxf.industry4.webapp.common.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.shiro.web.filter.authc.UserFilter;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.user.UserType;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;

/**
 *	功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 17:09:25
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class LwxfAdminFilter extends UserFilter {
	private Pattern fAdminUrlPattern = Pattern.compile("/fadmin(/.*)?");
	private Pattern bAdminUrlPattern = Pattern.compile("/badmin(/.*)?");
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		boolean ret=super.isAccessAllowed(request, response, mappedValue);
		if(!ret){
			return ret;
		}
		String servletPath = ((HttpServletRequest)request).getRequestURI();
		LoginedUser loginedUser = WebUtils.getCurrUser();
		// 厂家验证  (如果是系统账号也给予通过)
		Matcher matcher = fAdminUrlPattern.matcher(servletPath);
		if(matcher.matches()){
			if(this.validateUrl(loginedUser,UserType.FACTORY)||this.validateUrl(loginedUser,UserType.ADMIN)||this.validateUrl(loginedUser,UserType.SUPER_ADMIN)){
				return true;
			}
			return false;
		}
		// 经销商验证
		matcher = bAdminUrlPattern.matcher(servletPath);
		if(matcher.matches()){
			return this.validateUrl(loginedUser,UserType.DEALER);
		}
		return ret;
	}

	private boolean validateUrl(LoginedUser loginedUser,UserType userType){
		if(loginedUser.getType().intValue() == userType.getValue()){
			return Boolean.TRUE.booleanValue();
		}
		return Boolean.FALSE.booleanValue();
	}
}
