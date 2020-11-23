package com.lwxf.industry4.webapp.bizservice.dept;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.industry4.webapp.domain.entity.org.DeptMember;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-08 15:14:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DeptMemberService extends BaseService <DeptMember, String> {

	PaginatedList<DeptMember> selectByFilter(PaginatedFilter paginatedFilter);

	List<CompanyEmployeeDto> findListByDeptIdAndCompanyId(MapContext mapContext);

	List<DeptMember> selectByDeptId(String id);

	PaginatedList<EmployeeDeptDto> findListByDeptIdAndNameAndCompanyId(PaginatedFilter paginatedFilter);

	int deleteByDeptIdAndEmployeeId(String id, String eid);

	List<CompanyEmployeeDto> findListByEmployeeId(String employeeId);

	DeptMember findOneByDeptIdAndEmployeeId(String id, String eid);

	List<DeptMember> findDeptMemberListByEmployeeId(String employeeId);

	List<String> findDeptNameByEmployeeId(String employeeId);

	List selectUserIdByDeptId(String deptId);

	List<MapContext> findEmployeeNameByDeptId(String deptId);

	List<CompanyEmployeeDto> findEmployeeDtoByDeptId(String deptId);

	List<CompanyEmployeeDto> findEmployeeDtoByDeptIdAndRoleId(MapContext mapContext);

    List<MapContext> findEmployeeListGroupByDept(String currBranchId);

	List<MapContext> findEmployeeByDeptId(String deptId);
}