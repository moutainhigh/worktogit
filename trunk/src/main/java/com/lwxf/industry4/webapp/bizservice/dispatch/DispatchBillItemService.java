package com.lwxf.industry4.webapp.bizservice.dispatch;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillItemDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillItem;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 15:10:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DispatchBillItemService extends BaseService <DispatchBillItem, String> {

	PaginatedList<DispatchBillItem> selectByFilter(PaginatedFilter paginatedFilter);

	List<DispatchBillItem> findListByOrderId(String orderId);

	Integer findDispatchItemCountByOrderId(String dispatchBillId);

	Integer findDispatchItemCountByDispatchIdAndType(String dispatchBillId, Integer type);

	MapContext findLogisticsByDispatchId(String dispatchBillId);

	List<MapContext> findByDispatchBillId(String dispatchBillId);

	int deleteByOrderId(String orderId);

	List<DispatchBillItemDto> findListByDIdAndTypes(String id, List<Integer> types);

	List<DispatchBillItem> findListByDispatchBillId(String dispatchBillId);
}