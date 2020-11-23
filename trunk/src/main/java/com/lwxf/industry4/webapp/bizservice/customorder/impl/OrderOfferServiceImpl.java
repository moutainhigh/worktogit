package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.OrderOfferDao;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderOfferService;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderOffer;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-07-01 14:20:03
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("orderOfferService")
public class OrderOfferServiceImpl extends BaseServiceImpl<OrderOffer, String, OrderOfferDao> implements OrderOfferService {


	@Resource

	@Override	public void setDao( OrderOfferDao orderOfferDao) {
		this.dao = orderOfferDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OrderOffer> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public OrderOffer findByOrerId(String orderId) {
		return this.dao.findByOrerId(orderId);
	}

}