package com.lwxf.industry4.webapp.bizservice.company;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.*;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.WxDealerUserInfoDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：dongshibo(F_baisi@163.com)
 * @created：2018-12-05 11:24:07
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CompanyService extends BaseService <Company, String> {

	PaginatedList<CompanyDto> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<CompanyDtoForApp> selectByFilterForApp(PaginatedFilter paginatedFilter);

	PaginatedList<CompanyAccountInfoDto> findAccountListInfo(PaginatedFilter paginatedFilter);

	Company selectByNo(String no,String branchId);

	Company findByTelAndName(String tel,String name,String branchId);

	List<Company> findShopList(String address);

	/*
	获取经销商订单数前五名的公司，F端app用
	 */
	List<String> getTop5CustOrdersCompaniesId(MapContext mapContext);

	List<MonthsOrdersDto> findCompaniesOrdersCountMonthly(MapContext MapContext);

	Map<String,String> findCompanyStatistics();

	CompanyDto findCompanyById(String companyId);

	CompanyInfoDtoForApp findCompanyByIdForApp(String companyId);

	List<Company> findAllCompany(MapContext params);

	List<Map<String,Object>> companyTypesStatistics();

	int balancePlusForFactory(Double amount,Integer accountType);

	int balanceMinusForFactory(Double amount,Integer accountType);

    Map findBCompanyInfoById(String companyId);

	List<Map> findByCityNameAndName(MapContext mapContext);

	Map getCompanyInfoForApp(String companyId);

	List<CompanyAccountDto> getCompanyAccountInfoForApp(String companyId);

	Double getCompanyBalance(String companyId);


	Company findCompanyByType(Integer type);

    Integer findCompanyNumByOrderCreated(String beginTime, String endTime, String day);

	WxDealerDto findWxDealerInfoByCId(String currCompanyId);

	WxDealerUserInfoDto findDealerUserInfoByUid(String currUserId);

	PaginatedList<CompanyDtoForApp> findWxDealers(PaginatedFilter paginatedFilter);

	Integer findAllCompanyCount(String branchId);

	Integer findIntentionDealer(Integer intention, String branchId);

	Integer findSignedDealer(Integer signed, String branchId);

	WxCompanyDto findByBranchIdAndDealerId(String branchId, String dealerId);

	List<Map> findWxDealersAddCustomer(MapContext mapContext);

	MapContext findDealerCount(String branchId);

	List<Map> companyOrderRankingList(MapContext map);

	PaginatedList<CompanyDto> findCompanyBySearchInfo(PaginatedFilter paginatedFilter);

	List<MapContext> findCompanyByNameAndTel(MapContext mapContext);

	Integer findAllDealerCount(String currBranchId);


	MapContext findDealerCountByStatus(String branchId, Integer status);

	Company findCompanyByName(String dealerName);
}