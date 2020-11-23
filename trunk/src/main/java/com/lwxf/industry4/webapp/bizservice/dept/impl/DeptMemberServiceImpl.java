package com.lwxf.industry4.webapp.bizservice.dept.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.dept.DeptMemberService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.org.DeptDao;
import com.lwxf.industry4.webapp.domain.dao.org.DeptMemberDao;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.industry4.webapp.domain.entity.org.DeptMember;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-08 15:14:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("deptMemberService")
public class DeptMemberServiceImpl extends BaseServiceImpl<DeptMember, String, DeptMemberDao> implements DeptMemberService {


	@Resource

	@Override	public void setDao( DeptMemberDao deptMemberDao) {
		this.dao = deptMemberDao;
	}

	@Resource(name = "deptDao")
	private DeptDao deptDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DeptMember> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<CompanyEmployeeDto> findListByDeptIdAndCompanyId(MapContext mapContext) {
		return this.dao.findListByDeptIdAndCompanyId(mapContext);
	}

	@Override
	public List<DeptMember> selectByDeptId(String id) {
		return this.dao.selectByDeptId(id);
	}

	@Override
	public PaginatedList<EmployeeDeptDto> findListByDeptIdAndNameAndCompanyId(PaginatedFilter paginatedFilter) {
		PaginatedList<EmployeeDeptDto> list = this.dao.findListByDeptIdAndNameAndCompanyId(paginatedFilter);
		for (EmployeeDeptDto employeeDeptDto:list.getRows()){
			employeeDeptDto.setDeptList(this.deptDao.findListByEmployeeId(employeeDeptDto.getId()));
		}
		return list;
	}

	@Override
	public int deleteByDeptIdAndEmployeeId(String id, String eid) {
		return this.dao.deleteByDeptIdAndEmployeeId(id,eid);
	}

	@Override
	public List<CompanyEmployeeDto> findListByEmployeeId(String employeeId) {
		return this.dao.findListByEmployeeId(employeeId);
	}

	@Override
	public DeptMember findOneByDeptIdAndEmployeeId(String id, String eid) {
		return this.dao.findOneByDeptIdAndEmployeeId(id,eid);
	}

	@Override
	public List<DeptMember> findDeptMemberListByEmployeeId(String employeeId) {
		return this.dao.findDeptMemberListByEmployeeId(employeeId);
	}

	@Override
	public List<String> findDeptNameByEmployeeId(String employeeId) {
		return this.dao.findDeptNameByEmployeeId(employeeId);
	}

	@Override
	public List selectUserIdByDeptId(String deptId) {
		return this.dao.selectUserIdByDeptId(deptId);
	}

	@Override
	public List<MapContext> findEmployeeNameByDeptId(String deptId) {
		return this.dao.findEmployeeNameByDeptId(deptId);
	}
	@Override
	public List<CompanyEmployeeDto> findEmployeeDtoByDeptId(String deptId) {
		return this.dao.findEmployeeDtoByDeptId(deptId);
	}

	@Override
	public List<CompanyEmployeeDto> findEmployeeDtoByDeptIdAndRoleId(MapContext mapContext) {
		return this.dao.findEmployeeDtoByDeptIdAndRoleId(mapContext);
	}

	@Override
	public List<MapContext> findEmployeeListGroupByDept(String currBranchId) {
		return this.dao.findEmployeeListGroupByDept(currBranchId);
	}

	@Override
	public List<MapContext> findEmployeeByDeptId(String deptId) {
		return this.dao.findEmployeeByDeptId(deptId);
	}

}