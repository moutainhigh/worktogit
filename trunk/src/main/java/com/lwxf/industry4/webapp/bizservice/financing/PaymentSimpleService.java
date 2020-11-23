package com.lwxf.industry4.webapp.bizservice.financing;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleListDtoForApp;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimple;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;
import java.util.Map;

/**
 * 功能：
 * 
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-04-07 12:06:50
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface PaymentSimpleService extends BaseService<PaymentSimple, String> {

	PaginatedList<PaymentSimple> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<PaymentSimpleDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

	PaymentSimpleDto findDtoById(String id);

	List<Map<String,String>> getUserForPaymentSimple(String roleId);

	Map<String,String> countPaymentSimpleForApp();

	MapContext countPaymentForPageIndex(String branchId);

	List<PaymentSimpleListDtoForApp> selectCurrentDayListByFilterForApp();

	List<PaymentSimple> findFundsList(MapContext mapContext);

	MapContext findCountByFundsAndDeptId(String funds, String deptId,String type);
}