package com.lwxf.industry4.webapp.domain.dao.company;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.dto.company.*;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.WxDealerUserInfoDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
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
@IBatisSqlTarget
public interface CompanyDao extends BaseDao<Company, String> {

	PaginatedList<CompanyDto> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<CompanyDtoForApp> selectByFilterForApp(PaginatedFilter paginatedFilter);

	PaginatedList<CompanyAccountInfoDto> findAccountListInfo(PaginatedFilter paginatedFilter);

	Company selectByNo(String no,String branchId);

	Company findByTelAndName(String tel, String name,String branchId);

	Map<String,String> findCompanyStatistics();

	List<Company> findShopList(String address);

	List<String> getTop5CustOrdersCompaniesId(MapContext MapContext);

	CompanyDto findByCompanyId(String companyId);

	CompanyInfoDtoForApp findCompanyByIdForApp(String companyId);

	List<MonthsOrdersDto> selectCompaniesOrdersCountMonthly(MapContext MapContext);

	List<Company> findAllCompany(MapContext params);

	List<Map<String,Object>> companyTypesStatistics();

	int balancePlusForFactory(Double amount,Integer accountType);

	int balanceMinusForFactory(Double amount,Integer accountType);

    Map findBCompanyInfoById(String companyId);

    Map getCompanyInfoForApp(String companyId);

	List<CompanyAccountDto> getCompanyAccountInfoForApp(String companyId);

	List<Map> findByCityNameAndName(MapContext mapContext);


	Double getCompanyBalance(String companyId);


	Company findCompanyByType(Integer type);

	List<Company> findCompanyByTypes();

    Integer findCompanyNumByOrderCreated(MapContext params);

	WxDealerDto findWxDealerInfoByCId(String currCompanyId);

	WxDealerUserInfoDto findDealerUserInfoByUid(String currUserId);

	PaginatedList<CompanyDtoForApp> findWxDealers(PaginatedFilter paginatedFilter);

	Integer findAllCompanyCount(String branchId);

	Integer findIntentionDealer(Integer intention, String branchId);

	Integer findSignedDealer(Integer signed, String branchId);

	WxCompanyDto findByBranchIdAndDealerId(String branchId, String dealerId);

	List<Map> findWxDealersAddCustomer(MapContext mapContext);

	MapContext findDealerCount(String branchId);

	PaginatedList<CompanyDto> findCompanyBySearchInfo(PaginatedFilter paginatedFilter);

	List<Map> companyOrderRankingList(MapContext map);

	List<MapContext> findCompanyByNameAndTel(MapContext mapContext);

	Integer findAllDealerCount(String currBranchId);

    MapContext findDealerCountByStatus(String currBranchId, Integer status);

	Company findCompanyByName(String dealerName);
}