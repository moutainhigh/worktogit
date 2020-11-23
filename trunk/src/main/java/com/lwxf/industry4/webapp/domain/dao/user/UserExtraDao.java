package com.lwxf.industry4.webapp.domain.dao.user;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;


@IBatisSqlTarget
public interface UserExtraDao extends BaseDao<UserExtra, String> {

	PaginatedList<UserExtra> selectByFilter(PaginatedFilter paginatedFilter);

	/**
	 * 更新用户密码
	 *
	 * @param userExtra
	 * @return
	 */
	int updateUserExtra(UserExtra userExtra);
}