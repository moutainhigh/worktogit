package com.lwxf.industry4.webapp.domain.dao.dispatch;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlanItem;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-04-18 14:34:34
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface DispatchBillPlanItemDao extends BaseDao<DispatchBillPlanItem, String> {

	PaginatedList<DispatchBillPlanItem> selectByFilter(PaginatedFilter paginatedFilter);

	DispatchBillPlanItem findByfinishedStockItemId(String finishedStockItemId);

	List<DispatchBillPlanItem> findBydbpIdAndStatus(String dispatchBillPlanId, Integer status1);

	int updateStatusByFinishedItemId(MapContext updatePlanItem);

	int deleteByOrderId(String orderId);

	MapContext findCountByBranchId(String branchId);

	List<DispatchBillPlanItem> findListByDispatchBillPlanId(MapContext mapContext);

    Integer findDebtProductNumByPlanId(String planId);
}