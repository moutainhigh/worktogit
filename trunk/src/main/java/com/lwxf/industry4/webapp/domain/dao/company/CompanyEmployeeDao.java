package com.lwxf.industry4.webapp.domain.dao.company;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.companyEmployeeDto.CompanyEmploeeDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:07
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface CompanyEmployeeDao extends BaseDao<CompanyEmployee, String> {

	PaginatedList<CompanyEmployee> selectByFilter(PaginatedFilter paginatedFilter);


	CompanyEmployee selectEmployeeByCidAndNo(String companyId,String no);
	CompanyEmployee findOneByCompanyIdAndUserId(String companyId,String userId);
	CompanyEmployee selectByUserId(String userId);

	Integer updateEmployeeStatus(MapContext mapContext);

	Integer updateStatus(String userId, Integer status);
	List<CompanyEmployee> selectShopkeeperByCompanyIds(List<String> ids);

	Integer updateAllDisabledByCompanyId(String cid);

	PaginatedList<CompanyEmployeeDto> findListByFilter(PaginatedFilter paginatedFilter);

	CompanyEmployee findCompanyByUidAndStatus(String userId,Integer status);

	CompanyEmployeeDto findEmployeeMessage(MapContext mapContext);

	CompanyEmployeeDto findKeyByCidAndCustomerManager(String cid, String uid);

	List<CompanyEmployee> findListByRoleId(String id);

	PaginatedList<CompanyEmployeeDto> findEmployeeListByCID(PaginatedFilter paginatedFilter);

	int updateShopkeeperByCompanyId(String cid,String roleKey);

	List<CompanyEmployee> findEmployeeListByCidAndStatus(MapContext mapContext);

	PaginatedList<CompanyEmploeeDto> findEmployeeList(PaginatedFilter paginatedFilter);

	EmployeeDeptDto findOneByEid(String eid);

	List<Map<String,String>> getUserListByRoleId(String roleId);

	List<Map<String,String>> getUserListByRoleKey(String roleId);

	List<Map> findListByCIdAndIdentity(MapContext cidAndIdrntity);

	List<Map> findListCidAndStatus(MapContext cidAndStatus);

	List<MapContext> findCompanyEmployees(MapContext mapContext);

    List<Map> findEmployeeNameByIdByRoleId(MapContext params);

	List<Map> findAllEmployeesByCid(String companyid);

	List<String> findAllDianzhuId();

	List<MapContext> findsalemans(String branchId);

	PaginatedList<CompanyEmployeeDto> findEmployeeBySearchInfo(PaginatedFilter paginatedFilter);

	int updateRole(MapContext mapContext);

	List<Map> findAllEmployeesByBid(String branchId);
}