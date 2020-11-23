package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderProcessDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderProcessDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderProcess;
import com.lwxf.mybatis.utils.MapContext;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-01-04 16:07:14
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("customOrderProcessDao")
public class CustomOrderProcessDaoImpl extends BaseDaoImpl<CustomOrderProcess, String> implements CustomOrderProcessDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderProcess> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderProcess> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<CustomOrderProcessDto> findListByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findListByOrderId");
		return this.getSqlSession().selectList(sqlId,orderId);
	}

	@Override
	public int updateStatusByOrderIdAndType(String OrderId, Integer type) {
		String sqlId = this.getNamedSqlId("updateStatusByOrderIdAndType");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID,OrderId);
		mapContext.put(WebConstant.KEY_ENTITY_ENDTIME,DateUtil.getSystemDate());
		mapContext.put("type",type);
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public String findTimeByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("findTimeByOrderId");
		return this.getSqlSession().selectOne(sqlId,orderId);
	}

	@Override
	public List<Map> findProcessPlan() {
		String sqlId = this.getNamedSqlId("findProcessPlan");
		return this.getSqlSession().selectOne(sqlId);
	}
}