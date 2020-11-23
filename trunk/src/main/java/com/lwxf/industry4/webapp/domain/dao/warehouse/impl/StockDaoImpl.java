package com.lwxf.industry4.webapp.domain.dao.warehouse.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StockDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.StockDao;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Stock;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-13 15:21:19
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("stockDao")
public class StockDaoImpl extends BaseDaoImpl<Stock, String> implements StockDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Stock> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Stock> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<StockDto> findListByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<StockDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Stock findOneByStorageIdAndProductId(String storageId, String productId) {
		String sql = this.getNamedSqlId("findOneByStorageIdAndProductId");
		MapContext mapContext = MapContext.newOne();
		mapContext.put("storageId",storageId);
		mapContext.put("productId",productId);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public StockDto findOneById(String id) {
		String sql = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sql,id);
	}

	@Override
	public int deleteByIdAndStockId(String id, String stockId) {
		String sql = this.getNamedSqlId("deleteByIdAndStockId");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("stockId",stockId);
		return this.getSqlSession().delete(sql,mapContext);
	}

}