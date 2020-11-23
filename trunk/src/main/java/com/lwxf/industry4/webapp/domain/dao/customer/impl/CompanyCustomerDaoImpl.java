package com.lwxf.industry4.webapp.domain.dao.customer.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customer.CompanyCustomerDao;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;

import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.customer.*;
import com.lwxf.industry4.webapp.domain.dto.user.UserAreaDto;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-04 10:50:26
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("companyCustomerDao")
public class CompanyCustomerDaoImpl extends BaseDaoImpl<CompanyCustomer, String> implements CompanyCustomerDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomerDto> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomerDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CompanyCustomer> selectCustomersList(String companyId) {
		String sqlId=this.getNamedSqlId("selectCustomersList");


		return this.getSqlSession().selectList(sqlId,companyId);
	}

	@Override
	public Integer updateCustomerStatus(String userId) {
		String sqlId=this.getNamedSqlId("updateCustomerStatus");
		return this.getSqlSession().update(sqlId,userId);
	}

	@Override
	public CompanyCustomer selectCustomerByCUId(String companyId,String userId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("companyId",companyId);
		mapContext.put("userId",userId);
		String sqlId=this.getNamedSqlId("selectCustomerByCUId");
		return this.getSqlSession().selectOne(sqlId,mapContext);

	}

	@Override
	public CustomerDto findCustomerMessageById(String companyId,String userId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("companyId",companyId);
		mapContext.put("userId",userId);
		String sqlId=this.getNamedSqlId("findCustomerMessageById");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<CustomerCityCountDto> findCityCount(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findCityCount");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<CompanyCustomer> findCustomerByMap(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findCustomerByMap");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<DateNum> findCustomerDatenum(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findCustomerDatenum");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Integer findCustomerAmount() {
		String sqlId=this.getNamedSqlId("findCustomerAmount");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public List<FactoryCustomerDto> findCustomerTodayNew() {
		String sqlId=this.getNamedSqlId("findCustomerTodayNew");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public Integer findCustomerThisMonth() {
		String sqlId=this.getNamedSqlId("findCustomerThisMonth");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public Integer findLastMoth() {
		String sqlId=this.getNamedSqlId("findLastMoth");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public PaginatedList<FactoryCustomerDto> findCustomers(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findCustomers");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<FactoryCustomerDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<DateNum> findFactoryCustomerEveryMonthAdd() {
		String sqlId = this.getNamedSqlId("findFactoryCustomerEveryMonthAdd");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public List<CompanyCustomer> findCustomerListByCid(String companyId) {
		String sqlId = this.getNamedSqlId("findCustomerListByCid");
		return this.getSqlSession().selectList(sqlId,companyId);
	}

	@Override
	public List<String> findCompanyNameByUid(String customerId) {
		String sqlId = this.getNamedSqlId("findCompanyNameByUid");
		return this.getSqlSession().selectList(sqlId,customerId);
	}

	@Override
	public List<Map> findCompanyCustomer(String companyId) {
		String sqlId = this.getNamedSqlId("findCompanyCustomer");
		return this.getSqlSession().selectList(sqlId,companyId);
	}

	@Override
	public PaginatedList<WxCustomerDto> findWxCustomers(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findWxCustomers");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<WxCustomerDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public WxCustomerInfoDto findWxCustomerInfo(MapContext params) {
		String sqlId=this.getNamedSqlId("findWxCustomerInfo");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public PaginatedList<WxCustomerDto> findBWxCustomers(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findBWxCustomers");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<WxCustomerDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public CompanyCustomer findByPhoneAndBranchId(String mobile, String companyId) {
		String sqlId = this.getNamedSqlId("findByPhoneAndBranchId");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("mobile",mobile);
		mapContext.put("companyId",companyId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CustomerDtoV2 selectDtoById(String id) {
		String sqlId = this.getNamedSqlId("selectDtoById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public CompanyCustomer findByMobile(String mobile) {
		String sqlId = this.getNamedSqlId("findByMobile");
		return this.getSqlSession().selectOne(sqlId,mobile);
	}

	@Override
	public PaginatedList<CustomerDtoV2> findByClient(PaginatedFilter filter) {
		String sqlId = this.getNamedSqlId("findByClient");
		PageBounds pageBounds = this.toPageBounds(filter.getPagination(), filter.getSorts());
		PageList<CustomerDtoV2> pageList = (PageList) this.getSqlSession().selectList(sqlId, filter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public CustomerDtoV2 findOneById(String id) {
		String sqlId = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public MapContext findCustomerCount(String branchId) {
		String sqlId = this.getNamedSqlId("findCustomerCount");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}
}