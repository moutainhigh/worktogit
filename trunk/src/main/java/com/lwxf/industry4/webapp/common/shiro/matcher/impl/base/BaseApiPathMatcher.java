package com.lwxf.industry4.webapp.common.shiro.matcher.impl.base;

import javax.servlet.http.HttpServletRequest;

import java.util.regex.Pattern;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.shiro.matcher.IApiPathPermissionMatcher;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;

/**
 * 功能：Shiro权限验证抽象基类
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/12/6 0006 14:58
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public abstract class BaseApiPathMatcher implements IApiPathPermissionMatcher {
	protected static final String PERM_TEMPLEATE = "{0}_{1}:{2}:{3}";
	protected Pattern reqApiPattern;

	protected String doPerm(HttpServletRequest request,int userType){
		String xp = request.getHeader(WebConstant.REQUEST_HEADER_X_P);
		if(LwxfStringUtils.isBlank(xp)){
			LwxfAssert.isTrue(false,"请求头中未设置X-P");
			return WebConstant.STRING_404;
		}
		String[] xpArr = xp.split("-");
		String perm = LwxfStringUtils.format(PERM_TEMPLEATE,userType,xpArr[0],xpArr[1],xpArr[2]);
		if(ShiroUtil.isPermitted(perm)){
			return ShiroUtil.WILDCARD_TOKEN;
		}
		return WebConstant.STRING_404;
	}
}
