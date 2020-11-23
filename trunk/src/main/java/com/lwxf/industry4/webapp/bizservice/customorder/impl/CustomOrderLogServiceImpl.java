package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderLogDto;
import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderLogDao;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderLogService;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-05 17:15:05
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("customOrderLogService")
public class CustomOrderLogServiceImpl extends BaseServiceImpl<CustomOrderLog, String, CustomOrderLogDao> implements CustomOrderLogService {


	@Resource

	@Override	public void setDao( CustomOrderLogDao customOrderLogDao) {
		this.dao = customOrderLogDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderLog> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<CustomOrderLogDto> findByOrderIdAndState(String orderId, Integer stage) {
		return this.dao.findByOrderIdAndState(orderId, stage);
	}

	@Override
	public List<CustomOrderLogDto> findByOrderId(MapContext params) {
		return this.dao.findByOrderId(params);
	}

	@Override
	public PaginatedList<Map> findMessageOrderInfo(PaginatedFilter paginatedFilter) {
		return this.dao.findMessageOrderInfo(paginatedFilter);
	}
}