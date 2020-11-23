package com.lwxf.industry4.webapp.bizservice.customorder;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProduct;
import com.lwxf.mybatis.utils.MapContext;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-04-11 17:38:26
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OrderProductService extends BaseService <OrderProduct, String> {

	PaginatedList<OrderProduct> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<OrderProductDto> selectDtoByFilter(PaginatedFilter paginatedFilter);

	List<Map> findByOrderId(String orderId);

	Integer countProductsByStatus(MapContext map);

	OrderProductDto findOneById(String id);

	List<OrderProductDto> findListByOrderId(String id);

    Integer findCountNumByCreatedAndType(String beginTime, String endTime, Integer type, String created,Integer series);


	BigDecimal findCountPriceByOrderId(String id);

	BigDecimal findCountPriceByCreatedAndStatus(String beginTime, String endTime,String created,Integer status,Integer type,Integer series);
	Integer findCountNumByCreatedAndStatus(String beginTime, String endTime,String created,Integer status,Integer type,Integer series);

	int deleteByOrderId(String orderId);

	List<OrderProductDto> findProductsByOrderId(String id);

	List<OrderProductDto> findListByAftersaleId(String id);

	PaginatedList<OrderProductDto> findListByPaginateFilter(PaginatedFilter paginatedFilter);

	int cancelOrderProduct(MapContext mapContext);

	List<MapContext> countPartStock(String branchId);

	MapContext countInputPart(String branchId);

	List<OrderProductDto> findListByOrderIdAndStatus(MapContext map);

    List<MapContext> findProductNumGroupByType(MapContext map);

    double findProductMoneyByType(String dealerId, String productType);

	double findProductPriceTrendByTime(MapContext params);

	Integer updateByMap(Map product);
}