package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductAttributeService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customorder.OrderProductAttributeDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductAttribute;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-07-29 10:35:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("orderProductAttributeService")
public class OrderProductAttributeServiceImpl extends BaseServiceImpl<OrderProductAttribute, String, OrderProductAttributeDao> implements OrderProductAttributeService {


	@Resource

	@Override	public void setDao( OrderProductAttributeDao orderProductAttributeDao) {
		this.dao = orderProductAttributeDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OrderProductAttribute> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<OrderProductAttribute> findListByProductId(String productId) {
		return this.dao.findListByProductId(productId);
	}

}