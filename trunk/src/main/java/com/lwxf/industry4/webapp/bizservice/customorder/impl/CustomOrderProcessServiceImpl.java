package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderProcessService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderProcessDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderProcessDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderProcess;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-01-04 16:07:14
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("customOrderProcessService")
public class CustomOrderProcessServiceImpl extends BaseServiceImpl<CustomOrderProcess, String, CustomOrderProcessDao> implements CustomOrderProcessService {


	@Resource

	@Override	public void setDao( CustomOrderProcessDao customOrderProcessDao) {
		this.dao = customOrderProcessDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderProcess> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public List<CustomOrderProcessDto> findListByOrderId(String orderId) {
		return this.dao.findListByOrderId(orderId);
	}

	@Override
	public int updateStatusByOrderIdAndType(String OrderId, Integer type) {
		return this.dao.updateStatusByOrderIdAndType(OrderId,type);
	}

	@Override
	public String findTimeByOrderId(String orderId) {
		return this.dao.findTimeByOrderId(orderId);
	}

	@Override
	public List<Map> findProcessPlan() {
		return this.dao.findProcessPlan();
	}
}