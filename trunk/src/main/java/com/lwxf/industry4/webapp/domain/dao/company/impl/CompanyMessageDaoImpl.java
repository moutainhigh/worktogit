package com.lwxf.industry4.webapp.domain.dao.company.impl;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyMessageDto;
import org.springframework.stereotype.Repository;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.CompanyMessageDao;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：3965488@qq.com
 * @created：2019-06-14 16:34:59
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("companyMessageDao")
public class CompanyMessageDaoImpl extends BaseDaoImpl<CompanyMessage, String> implements CompanyMessageDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyMessage> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyMessage> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CompanyMessageDto> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDtoByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyMessageDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<CompanyMessageDto> findMessagesByFromuser(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findMessagesByFromuser");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CompanyMessageDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}