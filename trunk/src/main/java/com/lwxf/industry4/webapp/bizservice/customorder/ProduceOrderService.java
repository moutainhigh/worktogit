package com.lwxf.industry4.webapp.bizservice.customorder;


import java.util.Date;
import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDemandDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.CoordinationPrintTableDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceOrder;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-04-08 15:09:45
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProduceOrderService extends BaseService <ProduceOrder, String> {

	PaginatedList<PaymentProduceOrderDto> queryOrderOutsourcePayList(PaginatedFilter paginatedFilter);

	PaginatedList<ProduceOrder> selectByFilter(PaginatedFilter paginatedFilter);

    List<Map> findByOrderId(String orderId);
	ProduceOrderDto findOneById(String id);

	PaginatedList<ProduceOrderDto> findListByFilter(PaginatedFilter paginatedFilter);

	List<ProduceOrder> findListByIds(List<String> ids);

	List<ProduceOrder> findListByProductId(String productId);


	List<ProduceOrderDto> findListByOrderId(MapContext mapContext);

	List<ProduceOrderDto> findProduceOrderByProductId(String id);

	List<ProduceOrder> findIncompleteListByOrderId(String customOrderId,List<Integer> ways);

	int updatePayByOrderIdAndWays(String orderId, List<Integer> ways);

	List<ProduceOrder> findListByOrderIdAndTypesAndWays(String id, List<Integer> types, List<Integer> ways);

	int updateMapContextByIds(MapContext mapContext);

	int updatePlanTimeByIds(Date planTime, List ids);

	int deleteByOrderId(String orderId);

	int deleteByProductId(String pId);

	List findListOrderIdByPId(List ids);

	CoordinationPrintTableDto findCoordinationPrintInfo(String id);

	MapContext findCoordinationCount(String branchId);

	MapContext countTopProduct(MapContext params);

	List<ProduceOrderDto> findProduceListByProductId(MapContext param);

	Integer findByProductIdAndStockCount(String productId);


	List<ProduceOrder> findListByProductIdAndType(String id, Integer type);

	ProduceOrder findOneByTypeAndWayAndProductId(MapContext param);

	MapContext findListByProductIdAndState(String productId, int value);

    Integer findAllProduceNum(MapContext map);

	List<String> findLogisticsNobyOrderId(String orderId);

    List<String> findOutProduceInfoByOrderId(String orderId);

	MapContext findHasDeliverCountByProductId(String productId);

	MapContext findHasInPutCount(String productId);
}