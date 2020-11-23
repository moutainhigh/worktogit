package com.lwxf.industry4.webapp.bizservice.financing.impl;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentSimpleFundsDto;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentSimpleFundsDao;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentSimpleFundsService;
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
@Service("paymentSimpleFundsService")
public class PaymentSimpleFundsServiceImpl extends BaseServiceImpl<PaymentSimpleFunds, String, PaymentSimpleFundsDao> implements PaymentSimpleFundsService {


	@Resource

	@Override	public void setDao( PaymentSimpleFundsDao paymentSimpleFundsDao) {
		this.dao = paymentSimpleFundsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PaymentSimpleFunds> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public int addBatch(List<PaymentSimpleFundsDto> fundsList) {
		return this.dao.insertBatch(fundsList);
	}

	@Override
	public int deleteByPid(String paymentSimpleId) {
		return this.dao.deleteByPid(paymentSimpleId);
	}

	@Override
	public List<PaymentSimpleFunds> findFundsList(MapContext mapContext) {
		return this.dao.findFundsList(mapContext);
	}

	@Override
	public MapContext findCountByFundsNameAndDeptId(String fundsName, String deptId, String type) {
		return this.dao.findCountByFundsNameAndDeptId(fundsName,deptId,type);
	}

	@Override
	public List<PaymentSimpleFundsDto> findListByPaymentId(String paymentId) {
		return this.dao.findListByPaymentId(paymentId);
	}


}