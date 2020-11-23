package com.lwxf.industry4.webapp.bizservice.customorder.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderAccountLogDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.OrderAccountLogDao;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderAccountLogService;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderAccountLog;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-03-05 15:13:03
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("orderAccountLogService")
public class OrderAccountLogServiceImpl extends BaseServiceImpl<OrderAccountLog, String, OrderAccountLogDao> implements OrderAccountLogService {


	@Resource

	@Override	public void setDao( OrderAccountLogDao orderAccountLogDao) {
		this.dao = orderAccountLogDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OrderAccountLog> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<OrderAccountLogDto> findByOrderId(String orderId) {
		return this.dao.findByOrderId(orderId);
	}

	@Override
	public String findTimeByOrderId(String orderId) {
		return this.dao.findTimeByOrderId(orderId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}
}