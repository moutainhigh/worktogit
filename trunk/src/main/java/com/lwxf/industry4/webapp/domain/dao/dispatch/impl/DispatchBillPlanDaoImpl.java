package com.lwxf.industry4.webapp.domain.dao.dispatch.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.dispatch.DispatchBillPlanDao;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlan;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-04-18 14:34:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dispatchBillPlanDao")
public class DispatchBillPlanDaoImpl extends BaseDaoImpl<DispatchBillPlan, String> implements DispatchBillPlanDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchBillPlan> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DispatchBillPlan> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public DispatchBillPlan findByStatusAndBranchId(int status, String branchId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("status",status);
		mapContext.put("branchId",branchId);
		String sqlId=this.getNamedSqlId("findByStatusAndBranchId");
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

}