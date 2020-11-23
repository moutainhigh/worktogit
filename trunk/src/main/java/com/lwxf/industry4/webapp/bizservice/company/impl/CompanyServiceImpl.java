package com.lwxf.industry4.webapp.bizservice.company.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyDao;
import com.lwxf.industry4.webapp.domain.dto.company.*;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.WxDealerUserInfoDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
@Service("companyService")
public class CompanyServiceImpl extends BaseServiceImpl<Company, String, CompanyDao> implements CompanyService {


	@Resource

	@Override	public void setDao( CompanyDao companyDao) {
		this.dao = companyDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyDto> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<CompanyDtoForApp> selectByFilterForApp(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilterForApp(paginatedFilter) ;
	}

	@Override
	public Company selectByNo(String no,String branchId) {
		return this.dao.selectByNo(no,branchId);
	}

	@Override
	public Company findByTelAndName(String tel, String name,String branchId) {
		return this.dao.findByTelAndName(tel,name,branchId);
	}

	@Override
	public List<Company> findShopList(String address) {
		return this.dao.findShopList(address);
	}

	@Override
	public List<String> getTop5CustOrdersCompaniesId(MapContext mapContext) {
		return this.dao.getTop5CustOrdersCompaniesId(mapContext);
	}

	@Override
	public List<MonthsOrdersDto> findCompaniesOrdersCountMonthly(MapContext mapContext) {
		return this.dao.selectCompaniesOrdersCountMonthly(mapContext);
	}

	@Override
	public Map<String, String> findCompanyStatistics() {
		return this.dao.findCompanyStatistics();
	}

	@Override
	public CompanyDto findCompanyById(String companyId) {
		return this.dao.findByCompanyId(companyId);
	}


	@Override
	public CompanyInfoDtoForApp findCompanyByIdForApp(String companyId) {
		return this.dao.findCompanyByIdForApp(companyId);
	}
	@Override
	public List<Company> findAllCompany(MapContext params) {
		return this.dao.findAllCompany(params);
	}


	public List<Map<String,Object>> companyTypesStatistics(){
		return this.dao.companyTypesStatistics();
	}

	@Override
	public int balancePlusForFactory(Double amount,Integer accountType) {
		return dao.balancePlusForFactory(amount,accountType);
	}

	@Override
	public int balanceMinusForFactory(Double amount,Integer accountType) {
		return dao.balanceMinusForFactory(amount,accountType);
	}

	@Override
	public Map findBCompanyInfoById(String companyId) {
		return this.dao.findBCompanyInfoById(companyId);
	}

	@Override
	public List<Map> findByCityNameAndName(MapContext mapContext) {
		return this.dao.findByCityNameAndName(mapContext);
	}

	@Override
	public Map getCompanyInfoForApp(String companyId) {
		return this.dao.getCompanyInfoForApp(companyId);
	}

	@Override
	public List<CompanyAccountDto> getCompanyAccountInfoForApp(String companyId) {
		return this.dao.getCompanyAccountInfoForApp(companyId);
	}

	@Override
	public Double getCompanyBalance(String companyId) {
		return this.dao.getCompanyBalance(companyId);
	}

	@Override
	public Company findCompanyByType(Integer type) {
		return this.dao.findCompanyByType(type);
	}

	@Override
	public Integer findCompanyNumByOrderCreated(String beginTime, String endTime, String day) {
		MapContext params = MapContext.newOne();
		params.put("beginTime", beginTime);
		params.put("endTime", endTime);
		params.put("created",day );
		return this.dao.findCompanyNumByOrderCreated(params);
	}

	@Override
	public PaginatedList<CompanyDtoForApp> findWxDealers(PaginatedFilter paginatedFilter) {
		return this.dao.findWxDealers(paginatedFilter);
	}

	@Override
	public Integer findAllCompanyCount(String branchId) {
		return this.dao.findAllCompanyCount(branchId);
	}

	@Override
	public Integer findIntentionDealer(Integer intention, String branchId) {
		return this.dao.findIntentionDealer(intention,branchId);
	}

	@Override
	public Integer findSignedDealer(Integer signed, String branchId) {
		return this.dao.findSignedDealer(signed,branchId);
	}

	@Override
	public WxCompanyDto findByBranchIdAndDealerId(String branchId, String dealerId) {
		return this.dao.findByBranchIdAndDealerId(branchId,dealerId);
	}

	@Override
	public List<Map> findWxDealersAddCustomer(MapContext mapContext) {
		return this.dao.findWxDealersAddCustomer(mapContext);
	}

	@Override
	public MapContext findDealerCount(String branchId) {
		return this.dao.findDealerCount(branchId);
	}

	@Override
	public PaginatedList<CompanyDto> findCompanyBySearchInfo(PaginatedFilter paginatedFilter) {
		return this.dao.findCompanyBySearchInfo(paginatedFilter);
	}

	@Override
	public List<MapContext> findCompanyByNameAndTel(MapContext mapContext) {
		return this.dao.findCompanyByNameAndTel(mapContext);
	}

	@Override
	public Integer findAllDealerCount(String currBranchId) {
		return this.dao.findAllDealerCount(currBranchId);
	}

	@Override
	public MapContext findDealerCountByStatus(String currBranchId, Integer status) {
		return this.dao.findDealerCountByStatus(currBranchId,status);
	}

	@Override
	public Company findCompanyByName(String dealerName) {
		return this.dao.findCompanyByName(dealerName);
	}

	@Override
	public WxDealerDto findWxDealerInfoByCId(String currCompanyId) {
		return this.dao.findWxDealerInfoByCId(currCompanyId);
	}

	@Override
	public WxDealerUserInfoDto findDealerUserInfoByUid(String currUserId) {
		return this.dao.findDealerUserInfoByUid(currUserId);
	}

	@Override
	public PaginatedList<CompanyAccountInfoDto> findAccountListInfo(PaginatedFilter paginatedFilter) {
		return this.dao.findAccountListInfo(paginatedFilter);
	}

	@Override
	public List<Map> companyOrderRankingList(MapContext map) {
		return this.dao.companyOrderRankingList(map);
	}
}