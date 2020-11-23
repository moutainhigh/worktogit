package com.lwxf.industry4.webapp.bizservice.dispatch;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillDto;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillPlanItemDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.DispatchPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBill;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 15:10:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DispatchBillService extends BaseService <DispatchBill, String> {

	PaginatedList<DispatchBillDto> selectByFilter(PaginatedFilter paginatedFilter);

	List<DispatchBillDto> findDispatchsByOrderId(String orderId);;

	int deleteDispatchBillAndItemById(String id);

	DispatchBillDto findDispatchsBillById(String dispatchId);

	List<MapContext> findDispatchInfoForOrder(String orderId);

    int findYSHItemCount(String orderId);

    String findTimeByOrderId(String orderId);

    List<Map> findFactoryDispatchsByOrderId(String orderId);

	PaginatedList<DispatchBillPlanItemDto> findDispathcBillList(PaginatedFilter paginatedFilter);

	List<DispatchBill> findDispatchListByOrderId(String orderId);

	List<Map> findDispatchList(String resultOrderId);

	List<Map> findDispatchListByFinishedItemId(List itemids);

	List<FinishedStockDto> findFinishedItemTypeByDispatchId(String dispatchBillId, List itemids);

	DispatchPrintTableDto findDispatchPrintInfo(String id);

	DispatchBillDto findDispatchPrintInfoById(String dispatchBillId);

	List<MapContext> findDispatchsInfoByOrderId(String id);

    List<DispatchBill> findListByTimeAndBranchId(MapContext ma);
}