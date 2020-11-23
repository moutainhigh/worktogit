package com.lwxf.industry4.webapp.domain.dao.system.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.rolePermission.RolePermissionDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.system.RolePermissionDao;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;


/**
 * 功能：
 * 
 * @author：dongshibo(zjl869319827@outlook.com)
 * @created：2018-12-07 14:10:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("rolePermissionDao")
public class RolePermissionDaoImpl extends BaseDaoImpl<RolePermission, String> implements RolePermissionDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<RolePermission> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<RolePermission> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<RolePermission> selectRolePermissionList(String roleId) {
		String sqlId = this.getNamedSqlId("selectRolePermissionList");
		return this.getSqlSession().selectList(sqlId,roleId);
	}
	@Override
	public List<RolePermissionDto> selectRolePermissionDtoList(String roleId) {
		String sqlId = this.getNamedSqlId("selectRolePermissionDtoList");
		return this.getSqlSession().selectList(sqlId,roleId);
	}

	@Override
	public int deleteByRoleId(String id) {
		String sqlId = this.getNamedSqlId("deleteByRoleId");
		return this.getSqlSession().delete(sqlId,id);
	}

	@Override
	public List<RolePermission> findByMenusKey(String key) {
		String sqlId = this.getNamedSqlId("findByMenusKey");
		return this.getSqlSession().selectList(sqlId,key);
	}

	@Override
	public int deleteEmployeePermissionByRoleId(String roleId) {
		String sqlId = this.getNamedSqlId("deleteEmployeePermissionByRoleId");
		return this.getSqlSession().delete(sqlId,roleId);
	}

	@Override
	public int insertEmployeePermission(RolePermission rolePermission) {
		String sqlId = this.getNamedSqlId("insertEmployeePermission");
		return this.getSqlSession().insert(sqlId,rolePermission);
	}

	@Override
	public List<RolePermission> findAll() {
		String sqlId = this.getNamedSqlId("findAll");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public int deleteByKey(String key) {
		String sqlId = this.getNamedSqlId("deleteByKey");
		return this.getSqlSession().delete(sqlId,key);
	}

	@Override
	public List<RolePermission> findAllDianzhuRolePer() {
		String sqlId = this.getNamedSqlId("findAllDianzhuRolePer");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public int addList(List<RolePermission> rolePermissionList) {
		String sqlId = this.getNamedSqlId("addList");
		return this.getSqlSession().insert(sqlId,rolePermissionList);
	}

	@Override
	public RolePermission findOneByRoleIdAndMenukey(String roleId, String menukey) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("roleId",roleId);
		mapContext.put("menukey",menukey);
		String sqlId = this.getNamedSqlId("findOneByRoleIdAndMenukey");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}


}