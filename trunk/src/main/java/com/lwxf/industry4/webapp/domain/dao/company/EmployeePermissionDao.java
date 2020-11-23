package com.lwxf.industry4.webapp.domain.dao.company;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeePermission;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:08
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface EmployeePermissionDao extends BaseDao<EmployeePermission, String> {

	PaginatedList<EmployeePermission> selectByFilter(PaginatedFilter paginatedFilter);

	List<EmployeePermission> findListByEmployeeId(String employeeId);

	int addList(List<RolePermission> rolePermissionList);

	int deleteByEmployeeId(String eid);

	EmployeePermission findByEmpIdAndkey(String employeeId,String moduleKey,String menuKey);

	List<EmployeePermission> findByMenusKey(String key);

	int deleteByKey(String key);
}