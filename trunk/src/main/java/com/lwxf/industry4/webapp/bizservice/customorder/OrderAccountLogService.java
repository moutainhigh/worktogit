package com.lwxf.industry4.webapp.bizservice.customorder;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderAccountLogDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderAccountLog;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-03-05 15:13:03
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OrderAccountLogService extends BaseService <OrderAccountLog, String> {

	PaginatedList<OrderAccountLog> selectByFilter(PaginatedFilter paginatedFilter);

	List<OrderAccountLogDto> findByOrderId(String orderId);

    String findTimeByOrderId(String orderId);

	int deleteByOrderId(String orderId);
}