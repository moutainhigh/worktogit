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
import com.lwxf.industry4.webapp.domain.dao.customorder.OrderProductPartsDao;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductPartsService;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductParts;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-30 10:50:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("orderProductPartsService")
public class OrderProductPartsServiceImpl extends BaseServiceImpl<OrderProductParts, String, OrderProductPartsDao> implements OrderProductPartsService {


	@Resource

	@Override	public void setDao( OrderProductPartsDao orderProductPartsDao) {
		this.dao = orderProductPartsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OrderProductParts> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<OrderProductParts> findByProductPartsId(String partsId) {
		return this.dao.findByProductPartsId(partsId);
	}

	@Override
	public List<OrderProductParts> findByProductId(String id) {
		return this.dao.findByProductId(id);
	}

	@Override
	public int deleteByProductId(String id) {
		return this.dao.deleteByProductId(id);
	}

	@Override
	public OrderProductParts findByProductIdAndPartsId(String productId, String id) {
		return this.dao.findByProductIdAndPartsId(productId,id);
	}

}