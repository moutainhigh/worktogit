package com.lwxf.industry4.webapp.domain.dao.company.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.company.DealerEmployeeRole;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.companyEmployeeDto.CompanyEmploeeDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.CompanyEmployeeDto;
import com.lwxf.industry4.webapp.domain.dto.dept.EmployeeDeptDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyEmployeeDao;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:07
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("companyEmployeeDao")
public class CompanyEmployeeDaoImpl extends BaseDaoImpl<CompanyEmployee, String> implements CompanyEmployeeDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyEmployee> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyEmployee> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public int updateRole(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("updateRole");
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public List<Map> findAllEmployeesByBid(String branchId) {
		String sqlId=this.getNamedSqlId("findAllEmployeesByBid");
		return this.getSqlSession().selectList(sqlId,branchId);
	}

	@Override
	public List<CompanyEmployee> selectShopkeeperByCompanyIds(List<String> ids) {
		String sqlId = this.getNamedSqlId("selectShopkeeperByCompanyIds");
		MapContext mapContext = new MapContext();
		mapContext.put("ids",ids);
		mapContext.put("key",DealerEmployeeRole.SHOPKEEPER.getValue());
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Integer updateAllDisabledByCompanyId(String cid) {
		String sqlId = this.getNamedSqlId("updateAllDisabledByCompanyId");
		MapContext mapContext = new MapContext();
		mapContext.put("cid",cid);
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public PaginatedList<CompanyEmployeeDto> findListByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByFilter");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyEmployeeDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public CompanyEmployee findCompanyByUidAndStatus(String userId,Integer status) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",userId);
		mapContext.put("status",status);
		String sqlId=this.getNamedSqlId("findCompanyByUidAndStatus");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CompanyEmployeeDto findEmployeeMessage(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findEmployeeMessage");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CompanyEmployeeDto findKeyByCidAndCustomerManager(String cid, String uid) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",uid);
		mapContext.put("companyId",cid);
		String sqlId=this.getNamedSqlId("findKeyByCidAndCustomerManager");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<CompanyEmployee> findListByRoleId(String id) {
		String sqlId = this.getNamedSqlId("findListByRoleId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public PaginatedList<CompanyEmployeeDto> findEmployeeListByCID(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findEmployeeListByCID");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyEmployeeDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public int updateShopkeeperByCompanyId(String cid,String roleKey) {
		String sqlId = this.getNamedSqlId("updateShopkeeperByCompanyId");
		MapContext mapContext = new MapContext();
		mapContext.put("cid",cid);
		mapContext.put("roleKey",roleKey);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public List<CompanyEmployee> findEmployeeListByCidAndStatus(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findEmployeeListByCidAndStatus");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public PaginatedList<CompanyEmploeeDto> findEmployeeList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findEmployeeList");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyEmploeeDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public EmployeeDeptDto findOneByEid(String eid) {
		String sqlId = this.getNamedSqlId("findOneByEid");
		return this.getSqlSession().selectOne(sqlId,eid);
	}


	@Override
	public CompanyEmployee selectEmployeeByCidAndNo(String companyId,String no) {
		String sqlId=this.getNamedSqlId("selectEmployeeByCidAndNo");
        MapContext mapContext=MapContext.newOne();
        mapContext.put("companyId",companyId);
        mapContext.put("no",no);

		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CompanyEmployee selectByUserId(String userId) {
		String sqlId = this.getNamedSqlId("selectByUserId");
		return this.getSqlSession().selectOne(sqlId,userId);
	}

	@Override
	public Integer updateEmployeeStatus(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("updateEmployeeStatus");
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public Integer updateStatus(String userId, Integer status) {
		String sqlId=this.getNamedSqlId("updateStatus");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",userId);
		mapContext.put("status",status);
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public CompanyEmployee findOneByCompanyIdAndUserId(String companyId,String userId) {
		String sqlId=this.getNamedSqlId("findOneByCompanyIdAndUserId");
		MapContext params = MapContext.newOne();
		params.put(WebConstant.KEY_ENTITY_COMPANY_ID,companyId);
		params.put(WebConstant.KEY_ENTITY_USER_ID,userId);
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public List<Map<String, String>> getUserListByRoleId(String roleId) {
		String sqlId=this.getNamedSqlId("getUserListByRoleId");
		return this.getSqlSession().selectList(sqlId,roleId);
	}
	@Override
	public List<Map<String, String>> getUserListByRoleKey(String roleKey) {
		String sqlId=this.getNamedSqlId("getUserListByRoleKey");
		return this.getSqlSession().selectList(sqlId,roleKey);
	}
	@Override
	public List<Map> findListByCIdAndIdentity(MapContext cidAndIdrntity) {
		String sqlId = this.getNamedSqlId("findListByCIdAndIdentity");
		return this.getSqlSession().selectList(sqlId,cidAndIdrntity);
	}

	@Override
	public List<Map> findListCidAndStatus(MapContext cidAndStatus) {
		String sqlId = this.getNamedSqlId("findListCidAndStatus");
		return this.getSqlSession().selectList(sqlId,cidAndStatus);
	}

	@Override
	public List<MapContext> findCompanyEmployees(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findCompanyEmployees");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<Map> findEmployeeNameByIdByRoleId(MapContext params) {
		String sqlId = this.getNamedSqlId("findEmployeeNameByIdByRoleId");
		return this.getSqlSession().selectList(sqlId,params);
	}

	@Override
	public List<Map> findAllEmployeesByCid(String companyid) {
		String sqlId = this.getNamedSqlId("findAllEmployeesByCid");
		return this.getSqlSession().selectList(sqlId,companyid);
	}

	@Override
	public List<String> findAllDianzhuId() {
		String sqlId = this.getNamedSqlId("findAllDianzhuId");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public List<MapContext> findsalemans(String branchId) {
		String sqlId = this.getNamedSqlId("findsalemans");
		return this.getSqlSession().selectList(sqlId,branchId);
	}

	@Override
	public PaginatedList<CompanyEmployeeDto> findEmployeeBySearchInfo(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findEmployeeBySearchInfo");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyEmployeeDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}
}