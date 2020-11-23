package com.lwxf.industry4.webapp.domain.dao.warehouse.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.FinishedStockItemDao;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.mybatis.utils.MapContext;

import org.springframework.stereotype.Repository;

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
@Repository("finishedStockItemDao")
public class FinishedStockItemDaoImpl extends BaseDaoImpl<FinishedStockItem, String> implements FinishedStockItemDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<FinishedStockItem> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<FinishedStockItem> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}



	@Override
	public List<FinishedStockItem> findByDispatchId(String dispatchId) {
		String sqlId = this.getNamedSqlId("findByDispatchId");
		return this.getSqlSession().selectList(sqlId,dispatchId);
	}


	@Override
	public List<FinishedStockItem> findListByBarcodes(Set set) {
		String sqlId = this.getNamedSqlId("findListByBarcodes");
		return this.getSqlSession().selectList(sqlId,set);
	}

	@Override
	public FinishedStockItemDto findOneById(String id) {
		String sqlId = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public int deleteByFinishedStockId(String id) {
		String sql = this.getNamedSqlId("deleteByFinishedStockId");
		return this.getSqlSession().delete(sql,id);
	}

	@Override
	public List<FinishedStockItem> findListByInAndId(int i,String stockId) {
		String sqlId = this.getNamedSqlId("findListByInAndId");
		MapContext mapContext = MapContext.newOne();
		mapContext.put("i",i);
		mapContext.put(WebConstant.KEY_ENTITY_ID,stockId);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public Integer findByFinishedStockId(MapContext params) {
		String sqlId = this.getNamedSqlId("findByFinishedStockId");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public List<FinishedStockItemDto> findListByFinishedStockId(String finishedStockId) {
		String sqlId=this.getNamedSqlId("findListByFinishedStockId");
		return this.getSqlSession().selectList(sqlId,finishedStockId);
	}

	@Override
	public PaginatedList<FinishedStockItemDto> findListByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<FinishedStockItemDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public int updateShippedByIds(List itemIds) {
		String sqlId = this.getNamedSqlId("updateShippedByIds");
		return this.getSqlSession().update(sqlId,itemIds);
	}

	@Override
	public MapContext findByFinishedStockitemId(String finishedStockitemId) {
		String sqlId=this.getNamedSqlId("findByFinishedStockitemId");
		return this.getSqlSession().selectOne(sqlId,finishedStockitemId);
	}

	@Override
	public PaginatedList<MapContext> findListByOrderId(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByOrderId");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<MapContext> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public String findOrderIdByFinishedItemId(String finishedStockItemId) {
		String sqlId = this.getNamedSqlId("findOrderIdByFinishedItemId");
		return this.getSqlSession().selectOne(sqlId,finishedStockItemId);
	}

	@Override
	public MapContext findOneByFinishedStockitemId(String finishedStockItemId) {
		String sqlId = this.getNamedSqlId("findOneByFinishedStockitemId");
		return this.getSqlSession().selectOne(sqlId,finishedStockItemId);
	}

	@Override
	public Map findByDispatchBillItemId(String dispatchBillItemId) {
		String sqlId = this.getNamedSqlId("findByDispatchBillItemId");
		return this.getSqlSession().selectOne(sqlId,dispatchBillItemId);
	}




	@Override
	public List<Map> findBydispatchBillId(String dispatchId) {
		String sqlId = this.getNamedSqlId("findBydispatchBillId");
		return this.getSqlSession().selectList(sqlId,dispatchId);
	}

	@Override
	public List<FinishedStockItemDto> findByOrderIdAndType(MapContext orderIdAndType) {
		String sqlId = this.getNamedSqlId("findByOrderIdAndType");
		return this.getSqlSession().selectList(sqlId,orderIdAndType);
	}

	@Override
	public Integer findNumByCreated(MapContext params) {
		String sqlId = this.getNamedSqlId("findNumByCreated");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public Integer findFininshedstockStatementByDateAndIsin(MapContext params) {
		String sqlId = this.getNamedSqlId("findFininshedstockStatementByDateAndIsin");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public Integer findCountByTimeAndType(MapContext params) {
		String sqlId=this.getNamedSqlId("findCountByTimeAndType");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public PaginatedList<Map<String,Object>> findListMapByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListMapByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map<String,Object>> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public PaginatedList<MapContext> findFinishedStockNos(PaginatedFilter paginatedFilter) {
		String sqlId=this.getNamedSqlId("findFinishedStockNos");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<MapContext> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<FinishedStockItemDto> findListByProductId(String productId) {
		String sqlId=this.getNamedSqlId("findListByProductId");
		return this.getSqlSession().selectList(sqlId,productId);
	}
}