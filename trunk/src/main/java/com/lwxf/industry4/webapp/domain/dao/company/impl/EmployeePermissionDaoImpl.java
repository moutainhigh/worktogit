package com.lwxf.industry4.webapp.domain.dao.company.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.system.PermissionShow;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.system.RolePermission;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeePermissionDao;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeePermission;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:08
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("employeePermissionDao")
public class EmployeePermissionDaoImpl extends BaseDaoImpl<EmployeePermission, String> implements EmployeePermissionDao {
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeePermission> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EmployeePermission> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<EmployeePermission> findListByEmployeeId(String employeeId) {
		String sqlId = this.getNamedSqlId("findListByEmployeeId");
		MapContext mapContext = new MapContext();
		mapContext.put("employeeId",employeeId);
		mapContext.put("show", PermissionShow.SHOW.getValue());
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public int addList(List<RolePermission> rolePermissionList) {
		String sqlId = this.getNamedSqlId("addList");
		return this.getSqlSession().insert(sqlId,rolePermissionList);
	}

	@Override
	public int deleteByEmployeeId(String eid) {
		String sql = this.getNamedSqlId("deleteByEmployeeId");
		return this.getSqlSession().delete(sql,eid);
	}

	@Override
	public EmployeePermission findByEmpIdAndkey(String employeeId, String moduleKey, String menuKey) {
		String sql = this.getNamedSqlId("findByEmpIdAndkey");
		MapContext map = MapContext.newOne();
		map.put("employeeId",employeeId);
		map.put("moduleKey",moduleKey);
		map.put("menuKey",menuKey);
		return this.getSqlSession().selectOne(sql,map);
	}

	@Override
	public List<EmployeePermission> findByMenusKey(String key) {
		String sqlId = this.getNamedSqlId("findByMenusKey");
		return this.getSqlSession().selectList(sqlId,key);
	}

	@Override
	public int deleteByKey(String key) {
		String sqlId = this.getNamedSqlId("deleteByKey");
		return this.getSqlSession().delete(sqlId,key);
	}
}