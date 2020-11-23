package com.lwxf.industry4.webapp.bizservice.financing;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleFundsDto;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimple;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFunds;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;


/**
 * 功能：
 * 
 * @author：djl(yuuyoo@163.com)
 * @created：2019-07-23 10:10:19
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface PaymentSimpleFundsService extends BaseService <PaymentSimpleFunds, String> {

	PaginatedList<PaymentSimpleFunds> selectByFilter(PaginatedFilter paginatedFilter);

	int addBatch(List<PaymentSimpleFundsDto> fundsList);

	int deleteByPid(String paymentSimpleId);

	List<PaymentSimpleFunds> findFundsList(MapContext mapContext);

	MapContext findCountByFundsNameAndDeptId(String fundsName, String deptId, String type);

	List<PaymentSimpleFundsDto> findListByPaymentId(String paymentId);
}