package com.lwxf.industry4.webapp.bizservice.customorder;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderRemindDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderRemind;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2020-01-06 10:14:29
 * @version：2020 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomOrderRemindService extends BaseService <CustomOrderRemind, String> {

	PaginatedList<CustomOrderRemind> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<CustomOrderRemindDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

	CustomOrderRemind selectByOrderId(String orderId);

	CustomOrderRemindDto selectDtoByOrderId(String orderId);
}