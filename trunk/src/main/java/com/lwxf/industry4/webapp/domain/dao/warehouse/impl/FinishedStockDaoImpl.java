package com.lwxf.industry4.webapp.domain.dao.warehouse.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.FinishedStockDao;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("finishedStockDao")
public class FinishedStockDaoImpl extends BaseDaoImpl<FinishedStock, String> implements FinishedStockDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<FinishedStockDto> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<FinishedStockDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<FinishedStockItemDto> findListByFinishedStockId(String id) {
		String sqlId = this.getNamedSqlId("findListByFinishedStockId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public FinishedStockDto findOneById(String id) {
		String sqlId = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public List<FinishedStockItem> findUnshippedListById(String id) {
		String sqlId = this.getNamedSqlId("findUnshippedListById");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public FinishedStock findByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findByOrderId");
		return this.getSqlSession().selectOne(sqlId,orderId);
	}

	@Override
	public List<FinishedStock> findListByStorageId(String id) {
		String sqlId = this.getNamedSqlId("findListByStorageId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public List<FinishedStock> findListByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findListByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public Map findMapByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findMapByOrderId");
		return this.getSqlSession().selectOne(sqlId,orderId);
	}

	@Override
	public PaginatedList<MapContext> findDispathcBillList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findDispathcBillList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<MapContext> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public List<FinishedStockDto> findWxFinishedList(String orderId) {
		String sqlId = this.getNamedSqlId("findWxFinishedList");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public MapContext findCountByBranchId(String branchId) {
		String sqlId = this.getNamedSqlId("findCountByBranchId");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}
}