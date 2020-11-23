package com.lwxf.industry4.webapp.bizservice.customorder;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderProcessDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderProcess;

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
public interface CustomOrderProcessService extends BaseService <CustomOrderProcess, String> {

	PaginatedList<CustomOrderProcess> selectByFilter(PaginatedFilter paginatedFilter);

	List<CustomOrderProcessDto> findListByOrderId(String orderId);


	int updateStatusByOrderIdAndType(String OrderId, Integer type);

    String findTimeByOrderId(String orderId);

    List<Map> findProcessPlan();

}