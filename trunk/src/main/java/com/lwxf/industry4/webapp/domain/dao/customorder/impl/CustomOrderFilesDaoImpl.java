package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderFilesDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("customOrderFilesDao")
public class CustomOrderFilesDaoImpl extends BaseDaoImpl<CustomOrderFiles, String> implements CustomOrderFilesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public List<CustomOrderFiles> selectByOrderIdAndType(String orderId,Integer type,String belongId) {
		String sqlId = this.getNamedSqlId("selectByOrderIdAndType");
		MapContext params = MapContext.newOne();
		params.put("customOrderId",orderId);
		params.put("type",type);
		params.put("belongId",belongId);
		return this.getSqlSession().selectList(sqlId,params);
	}

	@Override
	public CustomOrderFiles findByBelongIdAndTypeAndOrderId(String orderId, Integer type, String belongId) {
		String sqlId = this.getNamedSqlId("findByBelongIdAndTypeAndOrderId");
		MapContext params = MapContext.newOne();
		params.put("customOrderId",orderId);
		params.put("category",type);
		params.put("belongId",belongId);
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public List<CustomOrderFiles> selectByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("selectByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public int deleteByBelongId(String belongId) {
		String sqlId = this.getNamedSqlId("deleteByBelongId");
		return this.getSqlSession().delete(sqlId,belongId);
	}

	@Override
	public int updateByIds(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("updateByIds");
		return this.getSqlSession().update(sqlId,mapContext);
	}
}