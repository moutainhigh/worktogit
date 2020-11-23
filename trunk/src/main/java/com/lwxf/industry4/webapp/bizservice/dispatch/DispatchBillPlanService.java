package com.lwxf.industry4.webapp.bizservice.dispatch;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlan;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-04-18 14:34:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface DispatchBillPlanService extends BaseService <DispatchBillPlan, String> {

	PaginatedList<DispatchBillPlan> selectByFilter(PaginatedFilter paginatedFilter);

	DispatchBillPlan findByStatusAndBranchId(int i, String branchId);
}