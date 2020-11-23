package com.lwxf.industry4.webapp.domain.dao.system;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.rolePermission.RolePermissionDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface RolePermissionDao extends BaseDao<RolePermission, String> {

	PaginatedList<RolePermission> selectByFilter(PaginatedFilter paginatedFilter);

	List<RolePermission> selectRolePermissionList(String roleId);

	List<RolePermissionDto> selectRolePermissionDtoList(String roleId);

	int deleteByRoleId(String id);

	List<RolePermission> findByMenusKey(String key);

	int deleteEmployeePermissionByRoleId(String roleId);

	int insertEmployeePermission(RolePermission rolePermission);

	List<RolePermission> findAll();

	int deleteByKey(String key);

	List<RolePermission> findAllDianzhuRolePer();

	int addList(List<RolePermission> rolePermissionList);

    RolePermission findOneByRoleIdAndMenukey(String roleId, String menukey);
}