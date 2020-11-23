package com.lwxf.industry4.webapp.bizservice.financing;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp.CompanyFinanceInfoDto;
import com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp.CompanyFinanceListDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.PaymentPrintTableDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-18 20:13:58
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface PaymentService extends BaseService <Payment, String> {

	PaginatedList<PaymentDto> selectByFilter(PaginatedFilter paginatedFilter);

	List<PaymentDto> findByOrderId (String orderId);

	PaymentDto findByPaymentId (String paymentId);

	int updateStatusByOrderIdAndFund(String orderId, Integer funds, Integer status);

	PaginatedList<PaymentDto> findListByPaginateFilter(PaginatedFilter paginatedFilter);

	List<Map> findByOrderIdAndType(MapContext orderIdAndType);

	int deleteByOrderId(String orderId);
	/**
	 * 财务-F端app经销商财务收支列表查询
	 */
	PaginatedList<CompanyFinanceListDto> findCompanyFinanceList(PaginatedFilter paginatedFilter);

	/**
	 * 财务-F端app经销商财务信息详情
	 */
	CompanyFinanceInfoDto getCompanyFinanceInfoByPaymentId(String paymentId);

	PaymentDto findByOrderIdAndFunds(MapContext params);

	PaymentDto findOrderFinanceInfo(String paymentId);

	MapContext countPaymentForPageIndex(String branchId);

	PaymentPrintTableDto findPrintTableById(String id);

	BigDecimal findTodayAmountByType(Integer type);

	Integer findCountByStatus(MapContext param);

	Integer findTodayCountByStatus(Integer value,String branchId);

	Integer findAllCountByStatus(Integer value,String branchId);

	List<PaymentDto> findByOrderIdfunds31(String orderId);

	MapContext findCompanyCapitalByCidAndType(MapContext params);

	MapContext findAmountByStatus(MapContext mapContext);

    MapContext findDealerPaymentCount(String dealerId);

	List<MapContext> findOrderFinanceCountinfo(MapContext mapContext);

	List<Payment> findByBranchIdAndFunds(MapContext mapContext);

	PaymentDto findOneByOrderIdAndFunds(MapContext con);
}