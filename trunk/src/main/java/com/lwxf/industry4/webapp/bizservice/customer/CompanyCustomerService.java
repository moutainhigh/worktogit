package com.lwxf.industry4.webapp.bizservice.customer;


import java.util.List;
import java.util.Map;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
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
public interface CompanyCustomerService extends BaseService <CompanyCustomer, String> {

	PaginatedList<CustomerDto> selectByFilter(PaginatedFilter paginatedFilter);

	List<CompanyCustomer> selectCustomersList(String companyId);


	Integer updateCustomerStatus(String userId);

	CompanyCustomer selectCustomerByCUId(String companyId,String userId);



	CustomerDto findCustomerMessageById(String companyId,String userId);


	List<CustomerCityCountDto> findCityCount(MapContext mapContext);

	List<CompanyCustomer> findCustomerByMap(MapContext mapContext);

	List<DateNum> findCustomerDatenum(MapContext mapContext);

	Integer findCustomerAmount();

	List<FactoryCustomerDto> findCustomerTodayNew();

	Integer findCustomerThisMonth();

	Integer findLastMoth();

	PaginatedList<FactoryCustomerDto> findCustomers(PaginatedFilter paginatedFilter);

	List<DateNum> findFactoryCustomerEveryMonthAdd();

	List<CompanyCustomer> findCustomerListByCid(String dealerId);

	List<String> findCompanyNameByUid(String customerId);


	List<Map> findCompanyCustomer(String companyId);

	PaginatedList<WxCustomerDto> findWxCustomers(PaginatedFilter paginatedFilter);

	WxCustomerInfoDto findWxCustomerInfo(MapContext params);

	PaginatedList<WxCustomerDto> findBWxCustomers(PaginatedFilter paginatedFilter);

	CompanyCustomer findByPhoneAndBranchId(String mobile, String companyId);

	CustomerDtoV2 selectDtoById(String id);

	CompanyCustomer findByMobile(String mobile);

	PaginatedList<CustomerDtoV2> findByClient(PaginatedFilter filter);

	CustomerDtoV2 findOneById(String id);

	MapContext findCustomerCount(String branchId);
}