package com.lwxf.industry4.webapp.bizservice.financing.impl;


import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp.CompanyFinanceInfoDto;
import com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp.CompanyFinanceListDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.PaymentPrintTableDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentDao;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;

/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-18 20:13:58
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("paymentService")
public class PaymentServiceImpl extends BaseServiceImpl<Payment, String, PaymentDao> implements PaymentService {

	@Resource
	@Override	public void setDao( PaymentDao paymentDao) {
		this.dao = paymentDao;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PaymentDto> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<PaymentDto> findByOrderId(String orderId) {
		return this.dao.findByOrderId(orderId);
	}

	@Override
	public PaymentDto findByPaymentId(String paymentId) {
		return this.dao.findByPaymentId(paymentId);
	}

	@Override
	public int updateStatusByOrderIdAndFund(String orderId, Integer funds, Integer status) {
		return this.dao.updateStatusByOrderIdAndFund(orderId,funds,status);
	}

	@Override
	public PaginatedList<PaymentDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByPaginateFilter(paginatedFilter);
	}
	@Override
	public List<Map> findByOrderIdAndType(MapContext orderIdAndType) {
		return this.dao.findByOrderIdAndType(orderIdAndType);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}


	@Override
	public PaginatedList<CompanyFinanceListDto> findCompanyFinanceList(PaginatedFilter paginatedFilter) {
		return this.dao.findCompanyFinanceList(paginatedFilter);
	}

	@Override
	public CompanyFinanceInfoDto getCompanyFinanceInfoByPaymentId(String paymentId) {
		return this.dao.getCompanyFinanceInfoByPaymentId(paymentId);
	}

	@Override
	public PaymentDto findByOrderIdAndFunds(MapContext params) {
		return this.dao.findByOrderIdAndFunds(params);
	}

	@Override
	public PaymentDto findOrderFinanceInfo(String paymentId) {
		return this.dao.findOrderFinanceInfo(paymentId);
	}

	@Override
	public MapContext countPaymentForPageIndex(String branchId) {
		return this.dao.countPaymentForPageIndex(branchId);
	}

	@Override
	public PaymentPrintTableDto findPrintTableById(String id) {
		return this.dao.findPrintTableById(id);
	}

	@Override
	public BigDecimal findTodayAmountByType(Integer type) {
		return this.dao.findTodayAmountByType(type);
	}

	@Override
	public Integer findCountByStatus(MapContext param) {
		return this.dao.findCountByStatus(param);
	}

	@Override
	public Integer findTodayCountByStatus(Integer value,String branchId) {
		return this.dao.findTodayCountByStatus(value,branchId);
	}

	@Override
	public Integer findAllCountByStatus(Integer value,String branchId) {
		return this.dao.findAllCountByStatus(value,branchId);
	}

	@Override
	public List<PaymentDto> findByOrderIdfunds31(String orderId) {
		return this.dao.findByOrderIdfunds31(orderId);
	}

	@Override
	public MapContext findCompanyCapitalByCidAndType(MapContext params) {
		return this.dao.findCompanyCapitalByCidAndType(params);
	}

	@Override
	public MapContext findAmountByStatus(MapContext param) {
		return this.dao.findAmountByStatus(param);
	}

	@Override
	public MapContext findDealerPaymentCount(String dealerId) {
		return this.dao.findDealerPaymentCount(dealerId);
	}

	@Override
	public List<MapContext> findOrderFinanceCountinfo(MapContext mapContext) {
		return this.dao.findOrderFinanceCountinfo(mapContext);
	}

	@Override
	public List<Payment> findByBranchIdAndFunds(MapContext mapContext) {
		return this.dao.findByBranchIdAndFunds(mapContext);
	}

	@Override
	public PaymentDto findOneByOrderIdAndFunds(MapContext con) {
		return this.dao.findOneByOrderIdAndFunds(con);
	}
}