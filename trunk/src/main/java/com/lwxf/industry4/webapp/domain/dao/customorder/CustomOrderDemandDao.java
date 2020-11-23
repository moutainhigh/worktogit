package com.lwxf.industry4.webapp.domain.dao.customorder;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDemandDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDemand;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface CustomOrderDemandDao extends BaseDao<CustomOrderDemand, String> {

	PaginatedList<CustomOrderDemand> selectByFilter(PaginatedFilter paginatedFilter);

	List<CustomOrderDemand> findByorderId(String orderId);

	int deleteByOrderId(String orderId);

	CustomOrderDemandDto selectByDemandId(String id);

	List<CustomOrderDemandDto> findListByOrderId(String id);

	CustomOrderDemand findByProductId(String id);
}