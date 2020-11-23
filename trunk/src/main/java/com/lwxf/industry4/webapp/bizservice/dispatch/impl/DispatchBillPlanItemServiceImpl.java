package com.lwxf.industry4.webapp.bizservice.dispatch.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.dispatch.DispatchBillPlanItemDao;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillPlanItemService;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlanItem;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-04-18 14:34:34
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dispatchBillPlanItemService")
public class DispatchBillPlanItemServiceImpl extends BaseServiceImpl<DispatchBillPlanItem, String, DispatchBillPlanItemDao> implements DispatchBillPlanItemService {


	@Resource

	@Override	public void setDao( DispatchBillPlanItemDao dispatchBillPlanItemDao) {
		this.dao = dispatchBillPlanItemDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchBillPlanItem> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public DispatchBillPlanItem findByfinishedStockItemId(String finishedStockItemId) {
		return this.dao.findByfinishedStockItemId(finishedStockItemId);
	}

	@Override
	public List<DispatchBillPlanItem> findBydbpIdAndStatus(String dispatchBillPlanId, Integer status1) {
		return this.dao.findBydbpIdAndStatus(dispatchBillPlanId,status1);
	}

	@Override
	public int updateStatusByFinishedItemId(MapContext updatePlanItem) {
		return this.dao.updateStatusByFinishedItemId(updatePlanItem);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		return this.dao.deleteByOrderId(orderId);
	}

	@Override
	public MapContext findCountByBranchId(String branchId) {
		return this.dao.findCountByBranchId(branchId);
	}

	@Override
	public List<DispatchBillPlanItem> findListByDispatchBillPlanId(MapContext mapContext) {
		return this.dao.findListByDispatchBillPlanId(mapContext);
	}

	@Override
	public Integer findDebtProductNumByPlanId(String planId) {
		return this.dao.findDebtProductNumByPlanId(planId);
	}

}