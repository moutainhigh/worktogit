package com.lwxf.industry4.webapp.bizservice.user;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.userRole.UserRole;

import java.util.List;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2020-01-07 13:47:50
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserRoleService extends BaseService <UserRole, String> {

	PaginatedList<UserRole> selectByFilter(PaginatedFilter paginatedFilter);

	RequestResult updateInsertUserRole(String uid,List<String> roleIds);
	RequestResult getUserRole();

}