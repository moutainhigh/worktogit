package com.lwxf.industry4.webapp.bizservice.customorder;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDesignDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDesign;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomOrderDesignService extends BaseService <CustomOrderDesign, String> {

	PaginatedList<CustomOrderDesign> selectByFilter(PaginatedFilter paginatedFilter);

	List<CustomOrderDesignDto> selectByOrderId(String orderId);

	int deleteByOrderId(String orderId);

	CustomOrderDesignDto findOneByDesignId(String designId);

	List<CustomOrderDesignDto> findListByOrderId(String id);

    String findTimeByOrderId(String orderId);

	CustomOrderDesign findConfirmedByOrderId(String id);

	Map findByOrderIdAndStatus(String orderId, int status);

	PaginatedList<CustomOrderDesignDto> findListByFilter(PaginatedFilter paginatedFilter);

	CustomOrderDesignDto findOneByProductId(String orderProductId);
}