package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderLogDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderLogDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-05 17:15:05
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("customOrderLogDao")
public class CustomOrderLogDaoImpl extends BaseDaoImpl<CustomOrderLog, String> implements CustomOrderLogDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderLog> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderLog> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CustomOrderLogDto> findByOrderIdAndState(String orderId, Integer stage) {
		MapContext params = MapContext.newOne();
		params.put("orderId",orderId);
		params.put("stage",stage);
		String sqlId = this.getNamedSqlId("findByOrderIdAndState");
		return this.getSqlSession().selectList(sqlId,params);
	}

	@Override
	public List<CustomOrderLogDto> findByOrderId(MapContext params) {
		String sqlId = this.getNamedSqlId("findByOrderId");
		return this.getSqlSession().selectList(sqlId,params);
	}

	@Override
	public PaginatedList<Map> findMessageOrderInfo(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findMessageOrderInfo");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}
}