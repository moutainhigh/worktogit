package com.lwxf.industry4.webapp.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.system.Role;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;

/**
 * 功能：验证用户信息相关操作
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/14/014 11:18
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class UserUtil {
	private static final Logger logger = LoggerFactory.getLogger(UserUtil.class);


	/**
	 * 判断用户是否具有某角色
	 * @param key
	 * @return
	 */
	public static boolean hasRoleByKey(String userId,String key){
		Role role = AppBeanInjector.roleService.selectByKey(key,WebUtils.getCurrBranchId());
		if(role==null){
			return false;
		}
		CompanyEmployee companyEmployee = AppBeanInjector.companyEmployeeService.findCompanyByUidAndStatus(userId, 0);
		if(companyEmployee==null){
			return false;
		}
		return role.getId().equals(companyEmployee.getRoleId());
	}
}
