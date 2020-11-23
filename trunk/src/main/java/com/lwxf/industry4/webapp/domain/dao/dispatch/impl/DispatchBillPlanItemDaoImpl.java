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
import com.lwxf.industry4.webapp.domain.dao.dispatch.DispatchBillPlanItemDao;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlanItem;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-04-18 14:34:34
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dispatchBillPlanItemDao")
public class DispatchBillPlanItemDaoImpl extends BaseDaoImpl<DispatchBillPlanItem, String> implements DispatchBillPlanItemDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DispatchBillPlanItem> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DispatchBillPlanItem> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public DispatchBillPlanItem findByfinishedStockItemId(String finishedStockItemId) {
		String sqlId=this.getNamedSqlId("findByfinishedStockItemId");
		return this.getSqlSession().selectOne(sqlId,finishedStockItemId);
	}

	@Override
	public List<DispatchBillPlanItem> findBydbpIdAndStatus(String dispatchBillPlanId, Integer status1) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("dispatchBillPlanId",dispatchBillPlanId);
		mapContext.put("status",status1);
		String sqlId=this.getNamedSqlId("findBydbpIdAndStatus");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public int updateStatusByFinishedItemId(MapContext updatePlanItem) {
		String sqlId = this.getNamedSqlId("updateStatusByFinishedItemId");
		return this.getSqlSession().update(sqlId,updatePlanItem);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public MapContext findCountByBranchId(String branchId) {
		String sqlId = this.getNamedSqlId("findCountByBranchId");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public List<DispatchBillPlanItem> findListByDispatchBillPlanId(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findListByDispatchBillPlanId");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Integer findDebtProductNumByPlanId(String planId) {
		String sqlId = this.getNamedSqlId("findDebtProductNumByPlanId");
		return this.getSqlSession().selectOne(sqlId,planId);
	}

}