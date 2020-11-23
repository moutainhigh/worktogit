package com.lwxf.industry4.webapp.bizservice.engineering.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.engineering.EngineeringOrderDao;
import com.lwxf.industry4.webapp.bizservice.engineering.EngineeringOrderService;
import com.lwxf.industry4.webapp.domain.entity.engineering.EngineeringOrder;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-05-27 18:14:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("engineeringOrderService")
public class EngineeringOrderServiceImpl extends BaseServiceImpl<EngineeringOrder, String, EngineeringOrderDao> implements EngineeringOrderService {


	@Resource

	@Override	public void setDao( EngineeringOrderDao engineeringOrderDao) {
		this.dao = engineeringOrderDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EngineeringOrder> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public MapContext findListByEngineeringId(String engineeringId) {
		return this.dao.findListByEngineeringId(engineeringId);
	}

	@Override
	public List<EngineeringOrder> findByEngineeringId(String engineeringId) {
		return this.dao.findByEngineeringId(engineeringId);
	}

	@Override
	public OrderProductDto findProductByOrderId(String customOrderId) {
		return this.dao.findProductByOrderId(customOrderId);
	}

}