package com.lwxf.industry4.webapp.domain.dao.org.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.org.DeptMemberDao;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.industry4.webapp.domain.entity.org.DeptMember;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-08 15:14:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("deptMemberDao")
public class DeptMemberDaoImpl extends BaseDaoImpl<DeptMember, String> implements DeptMemberDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DeptMember> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DeptMember> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CompanyEmployeeDto> findListByDeptIdAndCompanyId(MapContext mapContext) {
		String sql = this.getNamedSqlId("findListByDeptIdAndCompanyId");
		return this.getSqlSession().selectList(sql,mapContext);
	}

	@Override
	public List<DeptMember> selectByDeptId(String id) {
		String sql = this.getNamedSqlId("selectByDeptId");
		return this.getSqlSession().selectList(sql,id);
	}

	@Override
	public PaginatedList<EmployeeDeptDto> findListByDeptIdAndNameAndCompanyId(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByDeptIdAndNameAndCompanyId");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EmployeeDeptDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public int deleteByDeptIdAndEmployeeId(String id, String eid) {
		String sql = this.getNamedSqlId("deleteByDeptIdAndEmployeeId");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("employeeId",eid);
		return this.getSqlSession().delete(sql,mapContext);
	}

	@Override
	public List<CompanyEmployeeDto> findListByEmployeeId(String employeeId) {
		String sql = this.getNamedSqlId("findListByEmployeeId");
		return this.getSqlSession().selectOne(sql,employeeId);
	}

	@Override
	public DeptMember findOneByDeptIdAndEmployeeId(String id, String eid) {
		String sql = this.getNamedSqlId("findOneByDeptIdAndEmployeeId");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("employeeId",eid);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public List<DeptMember> findDeptMemberListByEmployeeId(String employeeId) {
		String sql = this.getNamedSqlId("findDeptMemberListByEmployeeId");
		return this.getSqlSession().selectList(sql,employeeId);
	}

	@Override
	public List<String> findDeptNameByEmployeeId(String employeeId) {
		String sql = this.getNamedSqlId("findDeptNameByEmployeeId");
		return this.getSqlSession().selectList(sql,employeeId);
	}

	@Override
	public List selectUserIdByDeptId(String deptId) {
		String sql = this.getNamedSqlId("selectUserIdByDeptId");
		return this.getSqlSession().selectList(sql,deptId);
	}

	@Override
	public List<MapContext> findEmployeeNameByDeptId(String deptId) {
		String sql = this.getNamedSqlId("findEmployeeNameByDeptId");
		return this.getSqlSession().selectList(sql,deptId);
	}
	@Override
	public List<CompanyEmployeeDto> findEmployeeDtoByDeptId(String deptId) {
		String sql = this.getNamedSqlId("findEmployeeDtoByDeptId");
		return this.getSqlSession().selectList(sql,deptId);
	}

	@Override
	public List<CompanyEmployeeDto> findEmployeeDtoByDeptIdAndRoleId(MapContext mapContext) {
		String sql = this.getNamedSqlId("findEmployeeDtoByDeptIdAndRoleId");
		return this.getSqlSession().selectList(sql,mapContext);
	}

	@Override
	public List<MapContext> findEmployeeListGroupByDept(String currBranchId) {
		String sql = this.getNamedSqlId("findEmployeeListGroupByDept");
		return this.getSqlSession().selectList(sql,currBranchId);
	}

	@Override
	public List<MapContext> findEmployeeByDeptId(String deptId) {
		String sql = this.getNamedSqlId("findEmployeeByDeptId");
		return this.getSqlSession().selectList(sql,deptId);
	}

}