package com.lwxf.industry4.webapp.bizservice.dept.impl;


import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.org.DeptDao;
import com.lwxf.industry4.webapp.domain.dto.dept.DeptDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.industry4.webapp.domain.entity.org.Dept;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-08 15:14:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("deptService")
public class DeptServiceImpl extends BaseServiceImpl<Dept, String, DeptDao> implements DeptService {


	@Resource

	@Override	public void setDao( DeptDao deptDao) {
		this.dao = deptDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Dept> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public Dept findDeptByNameAndParentId(String name,String parentId,String branchId) {
		return this.dao.findDeptByNameAndParentId(name,parentId,branchId);
	}

	@Override
	public Dept findDeptByKey(String key,String branchId) {
		return this.dao.findDeptByKey(key,branchId);
	}

	@Override
	public List<Dept> selectDeptByCompanyIdAndParentId(String companyId, String parentId) {
		return this.dao.selectDeptByCompanyIdAndParentId(companyId,parentId);
	}

	@Override
	public Dept selectByEmpId(String empId) {
		return this.dao.selectByEmpId(empId);
	}

	@Override
	public Dept findOneByKeyOrNameAndCompanyId(MapContext mapContext) {
		return this.dao.findOneByKeyOrNameAndCompanyId(mapContext);
	}

	@Override
	public List<Dept> findListByCompanyId(String companyId) {
		return this.dao.findListByCompanyId(companyId);
	}
	@Override
	public List<Dept> findAll(String companyId) {
		return this.dao.findAll(companyId);
	}

	@Override
	public EmployeeDeptDto findOneByUserId(String id) {
		EmployeeDeptDto employeeDeptDto = this.dao.findOneByUserId(id);
		employeeDeptDto.setDeptList(this.dao.findListByEmployeeId(employeeDeptDto.getId()));
		return employeeDeptDto;
	}

	@Override
	public List<Dept> findListByParentId(String id) {
		return this.dao.findListByParentId(id);
	}

	@Override
	public List<Dept> findListByCompanyIdAndParent(String companyId) {
		return this.dao.findListByCompanyIdAndParent(companyId);
	}

	@Override
	public List<Dept> findListByUserId(String currUserId) {
		return this.dao.findListByUserId(currUserId);
	}

	@Override
	public List<Dept> findAllDeptByBranchId(String branchId) {
		return this.dao.findAllDeptByBranchId(branchId);
	}
}