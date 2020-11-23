package com.lwxf.industry4.webapp.bizservice.customorder;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderLogDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-05 17:15:05
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomOrderLogService extends BaseService <CustomOrderLog, String> {

	PaginatedList<CustomOrderLog> selectByFilter(PaginatedFilter paginatedFilter);
	List<CustomOrderLogDto> findByOrderIdAndState(String orderId, Integer stage);

	List<CustomOrderLogDto> findByOrderId(MapContext params);

	PaginatedList<Map> findMessageOrderInfo(PaginatedFilter paginatedFilter);
}