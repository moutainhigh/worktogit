package com.lwxf.industry4.webapp.common.shiro.matcher.impl;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lwxf.commons.security.attacklocker.IAttackLocker;
import com.lwxf.commons.security.attacklocker.IAttackLockerInfo;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.shiro.matcher.IApiPathPermissionMatcher;
import com.lwxf.industry4.webapp.common.utils.SpringContextUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.shiro.ShiroUtil;
import com.lwxf.industry4.webapp.common.shiro.matcher.IApiPathPermissionMatcher;
import com.lwxf.industry4.webapp.common.utils.SpringContextUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;

/**
 * 功能：不常用的api的权限验证，放在验证的最后边
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 12:12:23
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class NoActiveApiPermissionMatcher implements IApiPathPermissionMatcher {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	IAttackLocker errorOutApiLocler;
	String errorsOutApi = "/api/system/errors";
	/**
	 * /users/0/preload
	 * /users/0/password
	 * /users/0
	 * /users/0/avatar
	 */
	/*private Pattern userPattern = Pattern.compile("/api/users/0(/(preload|password|avatar))?");*/
	/**
	 * /ip_blacklist
	 * /ip_blacklist_log
	 */

	@Override
	public String matcher(HttpServletRequest request, String action, String servletPath, String referer) {
		if(errorsOutApi.equals(servletPath)){
			if(errorOutApiLocler == null){
				errorOutApiLocler = (IAttackLocker)SpringContextUtil.getBean("errorOutApiLocker");
			}
			IAttackLockerInfo lockerInfo = errorOutApiLocler.getLockerInfo(servletPath);
			if(lockerInfo.isLocked() || lockerInfo.tryLock()){
				logger.error("******************* 前端错误日志输出API出现非法访问,已被锁定 "+errorOutApiLocler.getLockMinutes()+" 分钟");
				logger.error("******************* servletPath = "+servletPath);
				logger.error("******************* userId = "+ShiroUtil.getCurrUserId());
				logger.error("******************* IP = "+ WebUtils.getClientIpAddress());
				return WebConstant.STRING_EMPTY;
			}
			return ShiroUtil.WILDCARD_TOKEN;
		}
		return null;
	}
}
