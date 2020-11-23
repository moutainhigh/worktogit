package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import java.util.List;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderTimeService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderTimeDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderTime;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-09-03 15:47:14
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("customOrderTimeService")
public class CustomOrderTimeServiceImpl extends BaseServiceImpl<CustomOrderTime, String, CustomOrderTimeDao> implements CustomOrderTimeService {


	@Resource

	@Override	public void setDao( CustomOrderTimeDao customOrderTimeDao) {
		this.dao = customOrderTimeDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderTime> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public CustomOrderTime findByTypeAndSeries(MapContext mapContext) {
		return this.dao.findByTypeAndSeries(mapContext);
	}

	@Override
	public List<MapContext> selectList(MapContext mapContext) {
		return this.dao.selectList(mapContext);
	}

	@Override
	public List<CustomOrderTime> findBySeries(MapContext mapContext1) {
		return this.dao.findBySeries(mapContext1);
	}

	@Override
	public CustomOrderTime findMaxByOrderId(String orderId) {
		return this.dao.findMaxByOrderId(orderId);
	}

	@Override
	public CustomOrderTime findFirstByProductId(MapContext p) {
		return this.dao.findFirstByProductId(p);
	}

}