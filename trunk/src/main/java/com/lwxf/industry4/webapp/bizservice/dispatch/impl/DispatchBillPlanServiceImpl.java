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
import com.lwxf.industry4.webapp.domain.dao.dispatch.DispatchBillPlanDao;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillPlanService;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlan;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-04-18 14:34:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dispatchBillPlanService")
public class DispatchBillPlanServiceImpl extends BaseServiceImpl<DispatchBillPlan, String, DispatchBillPlanDao> implements DispatchBillPlanService {


	@Resource

	@Override	public void setDao( DispatchBillPlanDao dispatchBillPlanDao) {
		this.dao = dispatchBillPlanDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchBillPlan> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public DispatchBillPlan findByStatusAndBranchId(int status, String branchId) {
		return this.dao.findByStatusAndBranchId(status,branchId);
	}

}