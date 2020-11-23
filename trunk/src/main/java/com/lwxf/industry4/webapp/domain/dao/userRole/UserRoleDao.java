package com.lwxf.industry4.webapp.domain.dao.userRole;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.userRole.UserRole;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2020-01-07 13:47:50
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface UserRoleDao extends BaseDao<UserRole, String> {

	PaginatedList<UserRole> selectByFilter(PaginatedFilter paginatedFilter);

	public List<UserRole> getAllUserRole(String uid);

}