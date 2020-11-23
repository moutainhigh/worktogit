package com.lwxf.industry4.webapp.bizservice.financing.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentFilesService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentSimpleFilesService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentFilesDao;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentSimpleFilesDao;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFiles;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-04-11 16:16:41
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("paymentSimpleFilesService")
public class PaymentSimpleFilesServiceImpl extends BaseServiceImpl<PaymentSimpleFiles, String, PaymentSimpleFilesDao> implements PaymentSimpleFilesService {

	@Resource
	@Override	public void setDao( PaymentSimpleFilesDao paymentSimpleFilesDao) {
		this.dao = paymentSimpleFilesDao;
	}

	@Override
	public PaginatedList<PaymentSimpleFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}
	@Override
	public List<PaymentSimpleFiles> findByPaymentSimpleId(String paymentSimpleId) {
		return this.dao.findByPaymentSimpleId(paymentSimpleId);
	}
}