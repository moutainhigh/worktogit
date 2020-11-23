package com.lwxf.industry4.webapp.bizservice.warehouse;


import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface FinishedStockItemService extends BaseService <FinishedStockItem, String> {

	PaginatedList<FinishedStockItem> selectByFilter(PaginatedFilter paginatedFilter);


	List<FinishedStockItem> findByDispatchId(String dispatchId);

	List<FinishedStockItem> findListByBarcodes(Set set);

	FinishedStockItemDto findOneById(String id);

	int deleteByFinishedStockId(String id);

	int updateOrderStatusByFinishedStock(FinishedStock finishedStock);


	Integer findByFinishedStockId(MapContext params);

	List<FinishedStockItemDto> findListByFinishedStockId(String finishedStockId);

	List<Map> findBydispatchBillId(String dispatchId);

	Map findByDispatchBillItemId(String dispatchBillItemId);

	

	List<FinishedStockItemDto> findByOrderIdAndType(MapContext orderIdAndType);

	PaginatedList<FinishedStockItemDto> findListByFilter(PaginatedFilter paginatedFilter);

	int updateShippedByIds(List itemIds);

	MapContext findByFinishedStockitemId(String finishedStockitemId);


	PaginatedList<MapContext> findListByOrderId(PaginatedFilter paginatedFilter);

	String findOrderIdByFinishedItemId(String finishedStockItemId);

	MapContext findOneByFinishedStockitemId(String finishedStockItemId);

    Integer findNumByCreated(String beginTime, String endTime, String day,Integer isIn,String delivered);

	Integer findFininshedstockStatementByDateAndIsin(Date date, Integer isIn);

	Integer findCountByTimeAndType(Date sTime, Date eTime, int type,Integer isIn);

	PaginatedList<Map<String,Object>> findListMapByFilter(PaginatedFilter paginatedFilter);

	int deleteByOrderId(String orderId);

	PaginatedList<MapContext> findFinishedStockNos(PaginatedFilter paginatedFilter);
}