package com.lwxf.industry4.webapp.bizservice.user;


import java.util.List;

import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.user.UserResetpass;


public interface UserResetpassService extends BaseService<UserResetpass, String> {

	List<UserResetpass> selectByFilter(MapContext filter);

	/**
	 * 根据邮箱查找
	 *
	 * @param email
	 * @return
	 */
	UserResetpass findByEmail(String email);

	/**
	 * @param userResetpass
	 * @return
	 */
	UserResetpass updateUserResetpass(UserResetpass userResetpass);
}