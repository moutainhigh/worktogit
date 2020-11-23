package com.lwxf.industry4.webapp.common.shiro.matcher.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.shiro.matcher.IApiPathPermissionMatcher;

/**
 *	功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 12:14:35
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class FirstPathMatcher implements IApiPathPermissionMatcher {
	private Pattern pathPattern = Pattern.compile(LwxfStringUtils.format("^/({0})(/(tasks/{0}(/comments)?)?)?$",WebConstant.REG_ID_MATCH));
	private String ipWhiteListPathTpl = "/api/({0})";
	private List<String> ipWhiteListPathArr = new ArrayList<String>(){{
		add("activities/?.*");
		add("system/?.*");
	}};
	private String ipWhiteListPathArrStr = LwxfStringUtils.collectionJoin(ipWhiteListPathArr,"|");
	private Pattern orgIpWhiteListPattern =Pattern.compile(LwxfStringUtils.format(ipWhiteListPathTpl,ipWhiteListPathArrStr));
	@Override
	public String matcher(HttpServletRequest request, String action, String servletPath, String referer) {
		Matcher matcher = pathPattern.matcher(servletPath);
		if (matcher.matches()) {
			return ShiroUtil.WILDCARD_TOKEN;
		}
		return null;
	}
}
