package com.lwxf.industry4.webapp.bizservice.company.impl;


import java.util.List;
import java.util.Map;


import com.google.javascript.rhino.head.RhinoSecurityManager;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dao.org.DeptDao;
import com.lwxf.industry4.webapp.domain.dto.company.companyEmployeeDto.CompanyEmploeeDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyEmployeeDao;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:07
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("companyEmployeeService")
public class CompanyEmployeeServiceImpl extends BaseServiceImpl<CompanyEmployee, String, CompanyEmployeeDao> implements CompanyEmployeeService {


	@Resource

	@Override	public void setDao( CompanyEmployeeDao companyEmployeeDao) {
		this.dao = companyEmployeeDao;
	}

	@Resource(name = "deptDao")
	private DeptDao deptDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyEmployee> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<CompanyEmployee> selectShopkeeperByCompanyIds(List<String> ids) {
		return this.dao.selectShopkeeperByCompanyIds(ids);
	}

	@Override
	public Integer updateAllDisabledByCompanyId(String cid) {
		return this.dao.updateAllDisabledByCompanyId(cid);
	}

	@Override
	public PaginatedList<CompanyEmployeeDto> findListByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByFilter(paginatedFilter);
	}

	@Override
	public CompanyEmployee findCompanyByUidAndStatus(String userId,Integer status) {
		return this.dao.findCompanyByUidAndStatus(userId,status);
	}

	@Override
	public CompanyEmployeeDto findEmployeeMessage(MapContext mapContext) {
		return this.dao.findEmployeeMessage(mapContext);
	}

	@Override
	public CompanyEmployeeDto findKeyByCidAndCustomerManager(String cid, String uid) {
		return this.dao.findKeyByCidAndCustomerManager(cid,uid);
	}

	@Override
	public List<CompanyEmployee> findListByRoleId(String id) {
		return this.dao.findListByRoleId(id);
	}

	@Override
	public PaginatedList<CompanyEmployeeDto> findEmployeeListByCID(PaginatedFilter paginatedFilter) {
		return this.dao.findEmployeeListByCID(paginatedFilter);
	}

	@Override
	public int updateShopkeeperByCompanyId(String cid,String roleKey) {
		return this.dao.updateShopkeeperByCompanyId(cid,roleKey);
	}

	@Override
	public List<CompanyEmployee> findEmployeeListByCidAndStatus(MapContext mapContext) {
		return this.dao.findEmployeeListByCidAndStatus(mapContext);
	}

	@Override
	public PaginatedList<CompanyEmploeeDto> findEmployeeList(PaginatedFilter paginatedFilter) {
		return this.dao.findEmployeeList(paginatedFilter);
	}

	@Override
	public EmployeeDeptDto findOneByEid(String eid) {
		EmployeeDeptDto employeeDeptDto = this.dao.findOneByEid(eid);
		employeeDeptDto.setDeptList(this.deptDao.findListByEmployeeId(employeeDeptDto.getId()));
		return employeeDeptDto;
	}

	@Override
	public List<MapContext> findCompanyEmployees(MapContext mapContext) {

		return this.dao.findCompanyEmployees(mapContext);
	}


	@Override
	public CompanyEmployee selectEmployeeByCidAndNo(String companyId,String no) {
		return this.dao.selectEmployeeByCidAndNo(companyId,no);
	}

	@Override
	public CompanyEmployee selectByUserId(String userId) {
		return this.dao.selectByUserId(userId);
	}

	@Override
	public Integer updateEmployeeStatus(MapContext mapContext) {
		return this.dao.updateEmployeeStatus(mapContext);
	}

	@Override
	public Integer updateStatus(String userId, Integer status) {
		return this.dao.updateStatus(userId,status);
	}


	@Override
	public CompanyEmployee findOneByCompanyIdAndUserId(String companyId,String userId) {
		return this.dao.findOneByCompanyIdAndUserId(companyId,userId);
	}

	@Override
	public List<Map<String, String>> getUserListByRoleId(String roleId) {
		return this.dao.getUserListByRoleId(roleId);
	}
	@Override
	public List<Map<String, String>> getUserListByRoleKey(String roleKey) {
		return this.dao.getUserListByRoleKey(roleKey);
	}

	@Override
	public List<Map> findListByCIdAndIdentity(MapContext cidAndIdrntity) {
		return this.dao.findListByCIdAndIdentity(cidAndIdrntity);
	}

	@Override
	public List<Map> findListCidAndStatus(MapContext cidAndStatus) {
		return this.dao.findListCidAndStatus(cidAndStatus);
	}

	@Override
	public List<Map> findEmployeeNameByIdByRoleId(MapContext params) {
		return this.dao.findEmployeeNameByIdByRoleId(params);

	}

	@Override
	public List<Map> findAllEmployeesByCid(String companyid) {
		return this.dao.findAllEmployeesByCid(companyid);
	}

	@Override
	public List<String> findAllDianzhuId() {
		return this.dao.findAllDianzhuId();
	}

	@Override
	public List<MapContext> findsalemans(String branchId) {
		return this.dao.findsalemans(branchId);
	}

	@Override
	public PaginatedList<CompanyEmployeeDto> findEmployeeBySearchInfo(PaginatedFilter paginatedFilter) {
		return this.dao.findEmployeeBySearchInfo(paginatedFilter);
	}

	@Override
	public List<Map> findAllEmployeesByBid(String branchId) {
		return this.dao.findAllEmployeesByBid(branchId);
	}
}