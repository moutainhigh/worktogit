package com.lwxf.industry4.webapp.bizservice.customorder;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderTime;
import com.lwxf.mybatis.utils.MapContext;
import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-09-03 15:47:14
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomOrderTimeService extends BaseService <CustomOrderTime, String> {

	PaginatedList<CustomOrderTime> selectByFilter(PaginatedFilter paginatedFilter);


	CustomOrderTime findByTypeAndSeries(MapContext mapContext);

    List<MapContext> selectList(MapContext mapContext);

	List<CustomOrderTime> findBySeries(MapContext mapContext1);

    CustomOrderTime findMaxByOrderId(String orderId);

	CustomOrderTime findFirstByProductId(MapContext p);
}