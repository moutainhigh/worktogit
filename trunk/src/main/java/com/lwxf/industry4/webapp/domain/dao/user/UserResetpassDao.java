package com.lwxf.industry4.webapp.domain.dao.user;


import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.user.UserResetpass;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserResetpass;


@IBatisSqlTarget
public interface UserResetpassDao extends BaseDao<UserResetpass, String> {

	List<UserResetpass> selectByFilter(MapContext filter);

	/**
	 * 根据邮箱查找
	 *
	 * @param email
	 * @return
	 */
	UserResetpass selectByEmail(String email);

	/**
	 * @param userResetpass
	 * @return
	 */
	int updateUserResetpass(UserResetpass userResetpass);
}