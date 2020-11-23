package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import java.util.List;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderTimeDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderTime;


/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-09-03 15:47:14
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("customOrderTimeDao")
public class CustomOrderTimeDaoImpl extends BaseDaoImpl<CustomOrderTime, String> implements CustomOrderTimeDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderTime> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderTime> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public CustomOrderTime findByTypeAndSeries(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("findByTypeAndSeries");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<MapContext> selectList(MapContext mapContext) {
		String sqlId=this.getNamedSqlId("selectList");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<CustomOrderTime> findBySeries(MapContext mapContext1) {
		String sqlId=this.getNamedSqlId("findBySeries");
		return this.getSqlSession().selectList(sqlId,mapContext1);
	}

	@Override
	public CustomOrderTime findMaxByOrderId(String orderId) {
		String sqlId=this.getNamedSqlId("findMaxByOrderId");
		return this.getSqlSession().selectOne(sqlId,orderId);
	}

	@Override
	public CustomOrderTime findFirstByProductId(MapContext p) {
		String sqlId=this.getNamedSqlId("findFirstByProductId");
		return this.getSqlSession().selectOne(sqlId,p);
	}


}