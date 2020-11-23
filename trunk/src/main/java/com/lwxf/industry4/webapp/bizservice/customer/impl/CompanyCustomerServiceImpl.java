package com.lwxf.industry4.webapp.bizservice.customer.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.bizservice.customer.CompanyCustomerService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customer.CompanyCustomerDao;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;


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
@Service("companyCustomerService")
public class CompanyCustomerServiceImpl extends BaseServiceImpl<CompanyCustomer, String, CompanyCustomerDao> implements CompanyCustomerService {


	@Resource(name="companyCustomerDao")

	@Override	public void setDao( CompanyCustomerDao companyCustomerDao) {
		this.dao = companyCustomerDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomerDto> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<CompanyCustomer> selectCustomersList(String companyId) {
		return this.dao.selectCustomersList(companyId);
	}

	@Override
	public Integer updateCustomerStatus(String userId) {
		return this.dao.updateCustomerStatus(userId);
	}

	@Override
	public CompanyCustomer selectCustomerByCUId(String companyId,String userId) {
		return this.dao.selectCustomerByCUId(companyId,userId);
	}

	@Override
	public CustomerDto findCustomerMessageById(String companyId,String userId) {
		return this.dao.findCustomerMessageById(companyId,userId);
	}

	@Override
	public List<CustomerCityCountDto> findCityCount(MapContext mapContext) {
		return this.dao.findCityCount(mapContext);
	}

	@Override
	public List<CompanyCustomer> findCustomerByMap(MapContext mapContext) {
		return this.dao.findCustomerByMap(mapContext);
	}

	@Override
	public List<DateNum> findCustomerDatenum(MapContext mapContext) {
		return this.dao.findCustomerDatenum(mapContext);
	}

	@Override
	public Integer findCustomerAmount() {
		return this.dao.findCustomerAmount();
	}

	@Override
	public List<FactoryCustomerDto> findCustomerTodayNew() {
		return this.dao.findCustomerTodayNew();
	}

	@Override
	public Integer findCustomerThisMonth() {
		return this.dao.findCustomerThisMonth();
	}

	@Override
	public Integer findLastMoth() {
		return this.dao.findLastMoth();
	}

	@Override
	public PaginatedList<FactoryCustomerDto> findCustomers(PaginatedFilter paginatedFilter) {
		return this.dao.findCustomers(paginatedFilter);
	}

	@Override
	public List<DateNum> findFactoryCustomerEveryMonthAdd() {
		return this.dao.findFactoryCustomerEveryMonthAdd();
	}

	@Override
	public List<CompanyCustomer> findCustomerListByCid(String dealerId) {
		return this.dao.findCustomerListByCid(dealerId);
	}

	@Override
	public List<String> findCompanyNameByUid(String customerId) {
		return this.dao.findCompanyNameByUid(customerId);
	}

	@Override
	public List<Map> findCompanyCustomer(String companyId) {
		return this.dao.findCompanyCustomer(companyId);

	}

	@Override
	public PaginatedList<WxCustomerDto> findWxCustomers(PaginatedFilter paginatedFilter) {
		return this.dao.findWxCustomers(paginatedFilter);
	}

	@Override
	public WxCustomerInfoDto findWxCustomerInfo(MapContext params) {
		return this.dao.findWxCustomerInfo(params);
	}

	@Override
	public PaginatedList<WxCustomerDto> findBWxCustomers(PaginatedFilter paginatedFilter) {
		return this.dao.findBWxCustomers(paginatedFilter);
	}

	@Override
	public CompanyCustomer findByPhoneAndBranchId(String mobile, String companyId) {
		return this.dao.findByPhoneAndBranchId(mobile,companyId);
	}

	@Override
	public CustomerDtoV2 selectDtoById(String id) {
		return this.dao.selectDtoById(id);
	}

	@Override
	public CompanyCustomer findByMobile(String mobile) {
		return this.dao.findByMobile(mobile);
	}

	@Override
	public PaginatedList<CustomerDtoV2> findByClient(PaginatedFilter filter) {
		return this.dao.findByClient(filter);
	}

	@Override
	public CustomerDtoV2 findOneById(String id) {
		return this.dao.findOneById(id);
	}

	@Override
	public MapContext findCustomerCount(String branchId) {
		return this.dao.findCustomerCount(branchId);
	}
}