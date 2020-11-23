package com.lwxf.industry4.webapp.domain.dao.warehouse;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface FinishedStockItemDao extends BaseDao<FinishedStockItem, String> {

	PaginatedList<FinishedStockItem> selectByFilter(PaginatedFilter paginatedFilter);


	List<FinishedStockItem> findByDispatchId(String dispatchId);



	List<FinishedStockItem> findListByBarcodes(Set set);

	FinishedStockItemDto findOneById(String id);

	int deleteByFinishedStockId(String id);

	List<FinishedStockItem> findListByInAndId(int i,String stockId);

	List<Map> findBydispatchBillId(String dispatchId);


	Integer findByFinishedStockId(MapContext params);

	List<FinishedStockItemDto> findListByFinishedStockId(String finishedStockId);

	Map findByDispatchBillItemId(String dispatchBillItemId);

	

	List<FinishedStockItemDto> findByOrderIdAndType(MapContext orderIdAndType);

	PaginatedList<FinishedStockItemDto> findListByFilter(PaginatedFilter paginatedFilter);

	int updateShippedByIds(List itemIds);

	MapContext findByFinishedStockitemId(String finishedStockitemId);

	PaginatedList<MapContext> findListByOrderId(PaginatedFilter paginatedFilter);

	String findOrderIdByFinishedItemId(String finishedStockItemId);

	MapContext findOneByFinishedStockitemId(String finishedStockItemId);

    Integer findNumByCreated(MapContext params);

	Integer findFininshedstockStatementByDateAndIsin(MapContext params);

	Integer findCountByTimeAndType(MapContext params);

	PaginatedList<Map<String,Object>> findListMapByFilter(PaginatedFilter paginatedFilter);

	int deleteByOrderId(String orderId);

	PaginatedList<MapContext> findFinishedStockNos(PaginatedFilter paginatedFilter);

	List<FinishedStockItemDto> findListByProductId(String productId);
}