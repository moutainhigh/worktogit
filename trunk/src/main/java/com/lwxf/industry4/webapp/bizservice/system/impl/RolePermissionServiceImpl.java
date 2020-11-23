package com.lwxf.industry4.webapp.bizservice.system.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.domain.dto.rolePermission.RolePermissionDto;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.system.RolePermissionDao;
import com.lwxf.industry4.webapp.bizservice.system.RolePermissionService;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("rolePermissionService")
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission, String, RolePermissionDao> implements RolePermissionService {


	@Resource

	@Override	public void setDao( RolePermissionDao rolePermissionDao) {
		this.dao = rolePermissionDao;
	}

	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<RolePermission> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<RolePermission> selectRolePermissionList(String roleId) {
		return this.dao.selectRolePermissionList(roleId);
	}

	@Override
	public List<RolePermissionDto> selectRolePermissionDtoList(String roleId) {
		return this.dao.selectRolePermissionDtoList(roleId);
	}

	@Override
	public int deleteByRoleId(String id) {
		return this.dao.deleteByRoleId(id);
	}

	@Override
	public List<RolePermission> findByMenusKey(String key) {
		return this.dao.findByMenusKey(key);
	}

	@Override
	public int updateRolePermissions(String roleId,List<RolePermission> rolePermissions) {
		//删除原先的角色权限
		this.dao.deleteByRoleId(roleId);
		//删除原先的员工角色权限
		this.dao.deleteEmployeePermissionByRoleId(roleId);
		//查询使用当前角色的员工列表
		List<CompanyEmployee> companyEmployeeList = this.companyEmployeeService.findListByRoleId(roleId);
		//新增新的角色权限
		for(RolePermission rolePermission:rolePermissions){
			//设置角色权限
			rolePermission.setId(AppBeanInjector.idGererateFactory.nextStringId());
			this.dao.insert(rolePermission);
			for(CompanyEmployee companyEmployee:companyEmployeeList){
				//设置员工角色权限
				rolePermission.setId(AppBeanInjector.idGererateFactory.nextStringId());
				rolePermission.setRoleId(companyEmployee.getId());
				this.dao.insertEmployeePermission(rolePermission);
			}
		}
		return 1;
	}

	@Override
	public List<RolePermission> findAll() {
		return this.dao.findAll();
	}

	@Override
	public int deleteByKey(String key) {
		return this.dao.deleteByKey(key);
	}

	@Override
	public List<RolePermission> findAllDianzhuRolePer() {
		return this.dao.findAllDianzhuRolePer();
	}

	@Override
	public int addList(List<RolePermission> rolePermissionList) {
		return this.dao.addList(rolePermissionList);
	}

	@Override
	public RolePermission findOneByRoleIdAndMenukey(String roleId, String menukey) {
		return this.dao.findOneByRoleIdAndMenukey(roleId,menukey);
	}


}