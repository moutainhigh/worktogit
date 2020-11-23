package com.lwxf.industry4.webapp.bizservice.financing.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentFilesDao;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentFilesService;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-19 16:16:41
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("paymentFilesService")
public class PaymentFilesServiceImpl extends BaseServiceImpl<PaymentFiles, String, PaymentFilesDao> implements PaymentFilesService {


	@Resource

	@Override	public void setDao( PaymentFilesDao paymentFilesDao) {
		this.dao = paymentFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PaymentFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<PaymentFiles> findByPaymentId(String paymentId) {
		return this.dao.findByPaymentId(paymentId);
	}

	@Override
	public int deleteByPaymentId(String paymentId) {
		return this.dao.deleteByPaymentId(paymentId);
	}
}