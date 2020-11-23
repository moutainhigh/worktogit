package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDemandDto;
import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderDemandDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderDemand;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 17:48:28
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("customOrderDemandDao")
public class CustomOrderDemandDaoImpl extends BaseDaoImpl<CustomOrderDemand, String> implements CustomOrderDemandDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderDemand> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderDemand> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CustomOrderDemand> findByorderId(String orderId) {
		String sqlId = this.getNamedSqlId("findByorderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public int deleteByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("deleteByOrderId");
		return this.getSqlSession().delete(sqlId,orderId);
	}

	@Override
	public CustomOrderDemandDto selectByDemandId(String id) {
		String sqlId = this.getNamedSqlId("selectByDemandId");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public List<CustomOrderDemandDto> findListByOrderId(String id) {
		String sqlId = this.getNamedSqlId("findListByOrderId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public CustomOrderDemand findByProductId(String id) {
		String sqlId = this.getNamedSqlId("findByProductId");
		return this.getSqlSession().selectOne(sqlId,id);
	}
}