package com.lwxf.industry4.webapp.domain.dao.company.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyDao;
import com.lwxf.industry4.webapp.domain.dto.company.*;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.WxDealerUserInfoDto;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.mybatis.utils.MapContext;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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
@Repository("companyDao")
public class CompanyDaoImpl extends BaseDaoImpl<Company, String> implements CompanyDao {
	@Override
	public List<Company> findCompanyByTypes() {
		String sqlId = this.getNamedSqlId("findCompanyByTypes");
		return this.getSqlSession().selectList(sqlId);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyDto> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Company selectByNo(String no,String branchId) {
		String sql = this.getNamedSqlId("selectByNo");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("no",no);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public Company findByTelAndName(String tel, String name,String branchId) {
		String sql = this.getNamedSqlId("findByTelAndName");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("tel",tel);
		mapContext.put("name",name);
		mapContext.put("branchId",branchId);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public List<Company> findShopList(String address) {
		String sql = this.getNamedSqlId("findShopList");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("address",address);
		return this.getSqlSession().selectList(sql,mapContext);
	}

	@Override
	public CompanyDto findByCompanyId(@Param(value="companyId") String companyId) {
		String sqlId = this.getNamedSqlId("findByCompanyId");
		return this.getSqlSession().selectOne(sqlId,companyId);
	}

	@Override
	public List<MonthsOrdersDto> selectCompaniesOrdersCountMonthly(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("selectCompaniesOrdersCountMonthly");
		getSqlSession().clearCache();
		 return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<Company> findAllCompany(MapContext params) {
		String sqlId=this.getNamedSqlId("findAllCompany");
		return this.getSqlSession().selectList(sqlId,params);
	}

	@Override
	public Map<String, String> findCompanyStatistics() {
		String sqlId = this.getNamedSqlId("findCompanyStatistics");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public PaginatedList<CompanyDtoForApp> selectByFilterForApp(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilterForApp");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyDtoForApp> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<String> getTop5CustOrdersCompaniesId(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("getTop5CustOrdersCompaniesId");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public CompanyInfoDtoForApp findCompanyByIdForApp(String companyId) {
		String sqlId = this.getNamedSqlId("findCompanyByIdForApp");
		return this.getSqlSession().selectOne(sqlId,companyId);
	}

	public List<Map<String,Object>> companyTypesStatistics(){
		String sqlId = this.getNamedSqlId("companyTypesStatistics");
		return this.getSqlSession().selectList(sqlId);
	}

	@Override
	public int balancePlusForFactory(Double amount,Integer accountType) {
		String sqlId = this.getNamedSqlId("balancePlusForFactory");
		MapContext map = MapContext.newOne();
		map.put("amount",amount);
		map.put("type",accountType);
		map.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		return this.getSqlSession().update(sqlId,map);
	}

	@Override
	public int balanceMinusForFactory(Double amount,Integer accountType) {
		String sqlId = this.getNamedSqlId("balanceMinusForFactory");
		MapContext map = MapContext.newOne();
		map.put("amount",amount);
		map.put("type",accountType);
		return this.getSqlSession().update(sqlId,map);
	}

	@Override
	public Map findBCompanyInfoById(String companyId) {
		String sqlId = this.getNamedSqlId("findBCompanyInfoById");
		return this.getSqlSession().selectOne(sqlId,companyId);

	}

	@Override
	public List<Map> findByCityNameAndName(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findByCityNameAndName");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Map getCompanyInfoForApp(String companyId) {
		String sqlId = this.getNamedSqlId("getCompanyInfoForApp");
		return this.getSqlSession().selectOne(sqlId,companyId);
	}

	@Override
	public List<CompanyAccountDto> getCompanyAccountInfoForApp(String companyId) {
		String sqlId = this.getNamedSqlId("getCompanyAccountInfoForApp");
		return this.getSqlSession().selectList(sqlId,companyId);
	}

	/**
	 * 查詢经销商账户详情
	 * @param paginatedFilter
	 * @return
	 */
	@Override
	public PaginatedList<CompanyAccountInfoDto> findAccountListInfo(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findAccountListInfo");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyAccountInfoDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Double getCompanyBalance(String companyId) {
		String sqlId = this.getNamedSqlId("getCompanyBalance");
		return this.getSqlSession().selectOne(sqlId,companyId);
	}

	@Override
	public Company findCompanyByType(Integer type) {
		String sqlId = this.getNamedSqlId("findCompanyByType");
		return this.getSqlSession().selectOne(sqlId,type);
	}

	@Override
	public Integer findCompanyNumByOrderCreated(MapContext params) {
		String sqlId = this.getNamedSqlId("findCompanyNumByOrderCreated");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public PaginatedList<CompanyDtoForApp> findWxDealers(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findWxDealers");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyDtoForApp> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Integer findAllCompanyCount(String branchId) {

		String sqlId = this.getNamedSqlId("findAllCompanyCount");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public Integer findIntentionDealer(Integer intention, String branchId) {
		String sqlId = this.getNamedSqlId("findIntentionDealer");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("status",intention);
		mapContext.put("branchId",branchId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findSignedDealer(Integer signed, String branchId) {
		String sqlId = this.getNamedSqlId("findSignedDealer");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("status",signed);
		mapContext.put("branchId",branchId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public WxCompanyDto findByBranchIdAndDealerId(String branchId, String dealerId) {
		String sqlId = this.getNamedSqlId("findByBranchIdAndDealerId");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("dealerId",dealerId);
		mapContext.put("branchId",branchId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<Map> findWxDealersAddCustomer(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findWxDealersAddCustomer");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public MapContext findDealerCount(String branchId) {
		String sqlId = this.getNamedSqlId("findDealerCount");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public PaginatedList<CompanyDto> findCompanyBySearchInfo(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findCompanyBySearchInfo");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public WxDealerDto findWxDealerInfoByCId(String currCompanyId) {
		String sqlId = this.getNamedSqlId("findWxDealerInfoByCId");
		return this.getSqlSession().selectOne(sqlId,currCompanyId);
	}

	@Override
	public WxDealerUserInfoDto findDealerUserInfoByUid(String currUserId) {
		String sqlId = this.getNamedSqlId("findDealerUserInfoByUid");
		return this.getSqlSession().selectOne(sqlId,currUserId);
	}

	@Override
	public List<Map> companyOrderRankingList(MapContext map) {
		String sqlId = this.getNamedSqlId("companyOrderRankingList");
		return this.getSqlSession().selectList(sqlId,map);
	}

	@Override
	public List<MapContext> findCompanyByNameAndTel(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findCompanyByNameAndTel");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Integer findAllDealerCount(String currBranchId) {
		String sqlId = this.getNamedSqlId("findAllDealerCount");
		return this.getSqlSession().selectOne(sqlId,currBranchId);
	}

	@Override
	public MapContext findDealerCountByStatus(String currBranchId, Integer status) {
		String sqlId = this.getNamedSqlId("findDealerCountByStatus");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("status",status);
		mapContext.put("branchId",currBranchId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Company findCompanyByName(String dealerName) {
		String sqlId = this.getNamedSqlId("findCompanyByName");
		return this.getSqlSession().selectOne(sqlId,dealerName);
	}
}