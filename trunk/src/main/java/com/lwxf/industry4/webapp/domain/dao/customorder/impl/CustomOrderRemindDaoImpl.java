package com.lwxf.industry4.webapp.domain.dao.customorder.impl;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dao.customorder.CustomOrderRemindDao;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderRemindDto;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderRemind;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2020-01-06 10:14:29
 * @version：2020 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("customOrderRemindDao")
public class CustomOrderRemindDaoImpl extends BaseDaoImpl<CustomOrderRemind, String> implements CustomOrderRemindDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderRemind> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderRemind> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CustomOrderRemindDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDtoByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CustomOrderRemindDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public CustomOrderRemind selectByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("selectByOrderId");
		return this.getSqlSession().selectOne(sqlId, orderId);
	}

	@Override
	public CustomOrderRemindDto selectDtoByOrderId(String orderId) {
		String sqlId = this.getNamedSqlId("selectDtoByOrderId");
		return this.getSqlSession().selectOne(sqlId, orderId);
	}

}