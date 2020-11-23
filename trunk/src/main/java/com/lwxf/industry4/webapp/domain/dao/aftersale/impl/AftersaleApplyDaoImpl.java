package com.lwxf.industry4.webapp.domain.dao.aftersale.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.aftersale.*;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleDto;
import com.lwxf.industry4.webapp.domain.dto.aftersale.AftersaleOrderDto;
import com.lwxf.industry4.webapp.domain.dto.aftersale.DateNum;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.AftersalesPrintTableDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.OrderPrintTableDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.aftersale.AftersaleApplyDao;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-02 20:27:26
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("aftersaleApplyDao")
public class AftersaleApplyDaoImpl extends BaseDaoImpl<AftersaleApply, String> implements AftersaleApplyDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<AftersaleDto> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<AftersaleDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<AftersaleDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDtoByFilter");
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<AftersaleDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public AftersaleDto findAftersaleMessage(String aftersaleId) {
		String sqlId = this.getNamedSqlId("findAftersaleMessage");
		return this.getSqlSession().selectOne(sqlId,aftersaleId);
	}

	@Override
	public PaginatedList<MapContext> findByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<MapContext> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public AftersaleDto findOneById(String id) {
		String sqlId = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public Integer findCountByCidAndType(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findCountByCidAndType");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<AftersaleApply> findAftersaleListByCid(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleListByCid");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}


	@Override
	public Integer findCountByOederIdAndType(Integer orderType, List orderIds) {
		String sqlId=this.getNamedSqlId("findCountByOederIdAndType");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("orderType",orderType);
		mapContext.put("orderIds",orderIds);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findCountByCidAndStatus(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findCountByCidAndStatus");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<DateNum> findAftersaleByDate(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findAftersaleByDate");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Integer findFactoryAftersaleApply(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findFactoryAftersaleApply");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public Integer findTodayCount() {
		String sqlId=this.getNamedSqlId("findTodayCount");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public Integer findThisWeekCount() {
		String sqlId=this.getNamedSqlId("findThisWeekCount");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public Integer findThisMonthCount() {
		String sqlId=this.getNamedSqlId("findThisMonthCount");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public List<Map> findAftersaleByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findAftersaleByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public PaginatedList<MapContext> findAftersaleApplyList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findAftersaleApplyList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<MapContext> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<MapContext> findAftersaleProductByorderId(String resultOrderId) {
		String sqlId = this.getNamedSqlId("findAftersaleProductByorderId");
		return this.getSqlSession().selectList(sqlId,resultOrderId);
	}

	@Override
	public AftersaleOrderDto findOrderBaseInfoByOrderId(MapContext params) {
		String sqlId=this.getNamedSqlId("findOrderBaseInfoByOrderId");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public List<AftersaleApply> findByOrderId(String orderId) {
		String sqlId=this.getNamedSqlId("findByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public AftersaleApply findByAftersaleNo(String aftersaleOrderNo) {
		String sqlId=this.getNamedSqlId("findByAftersaleNo");
		return this.getSqlSession().selectOne(sqlId,aftersaleOrderNo);
	}

	@Override
	public List<AftersaleApply> findAftersaleListByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findAftersaleListByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public int deleteByResultOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByResultOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public Map<String, Long> countAftersale(String branchId) {
		String sqlId=this.getNamedSqlId("countAftersale");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public PaginatedList<MapContext> findWxAftersaleApplyList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findWxAftersaleApplyList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<MapContext> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public MapContext countAftersaleForPageIndex(String branchId) {
		String sqlId=this.getNamedSqlId("countAftersaleForPageIndex");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public AftersalesPrintTableDto findAftersalesPrintInfo(String id) {
		String sqlId = this.getNamedSqlId("findAftersalesPrintInfo");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public OrderPrintTableDto findOrderPrintTable(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findOrderPrintTable");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public AftersaleReportDto findAftersaleReport() {
		String sqlId = this.getNamedSqlId("findAftersaleReport");
		return this.getSqlSession().selectOne(sqlId);
	}

	@Override
	public PaginatedList<CustomOrderDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByPaginateFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDto> pageList =  (PageList)this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<AftersaleApply> findByOrderIdAndProductId(String parentId, String afterProductId) {
		String sqlId=this.getNamedSqlId("findByOrderIdAndProductId");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("orderId",parentId);
		mapContext.put("afterProductId",afterProductId);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}
}