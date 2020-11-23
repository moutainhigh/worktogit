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
import com.lwxf.industry4.webapp.domain.dao.financing.PaymentListDao;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentListService;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentList;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-11-04 17:34:53
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("paymentListService")
public class PaymentListServiceImpl extends BaseServiceImpl<PaymentList, String, PaymentListDao> implements PaymentListService {


	@Resource

	@Override	public void setDao( PaymentListDao paymentListDao) {
		this.dao = paymentListDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PaymentList> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}