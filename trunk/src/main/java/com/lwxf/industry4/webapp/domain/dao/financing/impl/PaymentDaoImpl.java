package com.lwxf.industry4.webapp.domain.dao.financing.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp.CompanyFinanceInfoDto;
import com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp.CompanyFinanceListDto;
import org.springframework.stereotype.Repository;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.printTable.PaymentPrintTableDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentDao;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-18 20:13:58
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("paymentDao")
public class PaymentDaoImpl extends BaseDaoImpl<Payment, String> implements PaymentDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PaymentDto> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PaymentDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<PaymentDto> findByOrderIdfunds31(String orderId) {
		String sqlId = this.getNamedSqlId("findByOrderIdfunds31");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public List<PaymentDto> findByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public PaymentDto findByPaymentId(String paymentId) {
		String sqlId = this.getNamedSqlId("findByPaymentId");
		return this.getSqlSession().selectOne(sqlId,paymentId);
	}

	@Override
	public int updateStatusByOrderIdAndFund(String orderId, Integer funds, Integer status) {
		String sqlId = this.getNamedSqlId("updateStatusByOrderIdAndFund");
		MapContext mapContext = new MapContext();
		mapContext.put("orderId",orderId);
		mapContext.put("funds",funds);
		mapContext.put(WebConstant.KEY_ENTITY_STATUS,status);
		mapContext.put("auditor",WebUtils.getCurrUserId());
		mapContext.put("audited",DateUtil.getSystemDate());
		mapContext.put("payTime",DateUtil.getSystemDate());
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public PaginatedList<PaymentDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByPaginateFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PaymentDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public List<Map> findByOrderIdAndType(MapContext orderIdAndType) {
		String sqlId = this.getNamedSqlId("findByOrderIdAndType");
		return this.getSqlSession().selectList(sqlId,orderIdAndType);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public Map<String, String> countCompanyFinance() {
		String sqlId = this.getNamedSqlId("countCompanyFinance");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public PaginatedList<CompanyFinanceListDto> findCompanyFinanceList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findCompanyFinanceList");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyFinanceListDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public CompanyFinanceInfoDto getCompanyFinanceInfoByPaymentId(String paymentId) {
		String sqlId = this.getNamedSqlId("getCompanyFinanceInfoByPaymentId");
		return this.getSqlSession().selectOne(sqlId,paymentId);
	}

	@Override
	public PaymentDto findByOrderIdAndFunds(MapContext params) {
		String sqlId = this.getNamedSqlId("findByOrderIdAndFunds");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public PaymentDto findOrderFinanceInfo(String paymentId) {
		String sqlId = this.getNamedSqlId("findOrderFinanceInfo");
		return this.getSqlSession().selectOne(sqlId,paymentId);
	}

	@Override
	public MapContext countPaymentForPageIndex(String branchId) {
		String sqlId = this.getNamedSqlId("countPaymentForPageIndex");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public PaymentPrintTableDto findPrintTableById(String id) {
		String sqlId = this.getNamedSqlId("findPrintTableById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public BigDecimal findTodayAmountByType(Integer type) {
		String sqlId = this.getNamedSqlId("findTodayAmountByType");
		return this.getSqlSession().selectOne(sqlId,type);
	}

	@Override
	public Integer findCountByStatus(MapContext param) {
		String sqlId = this.getNamedSqlId("findCountByStatus");
		return this.getSqlSession().selectOne(sqlId,param);
	}

	@Override
	public Integer findTodayCountByStatus(Integer value,String branchId) {
		String sqlId = this.getNamedSqlId("findTodayCountByStatus");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("value",value);
		mapContext.put("branchId",branchId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}
	@Override
	public Integer findAllCountByStatus(Integer value,String branchId) {
		String sqlId = this.getNamedSqlId("findAllCountByStatus");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("value",value);
		mapContext.put("branchId",branchId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findCompanyCapitalByCidAndType(MapContext params) {
		String sqlId = this.getNamedSqlId("findCompanyCapitalByCidAndType");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public MapContext findAmountByStatus(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findAmountByStatus");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public MapContext findDealerPaymentCount(String dealerId) {
		String sqlId = this.getNamedSqlId("findDealerPaymentCount");
		return this.getSqlSession().selectOne(sqlId,dealerId);
	}

	@Override
	public List<MapContext> findOrderFinanceCountinfo(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findOrderFinanceCountinfo");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<Payment> findByBranchIdAndFunds(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findByBranchIdAndFunds");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public PaymentDto findOneByOrderIdAndFunds(MapContext con) {
		String sqlId = this.getNamedSqlId("findOneByOrderIdAndFunds");
		return this.getSqlSession().selectOne(sqlId,con);
	}
}