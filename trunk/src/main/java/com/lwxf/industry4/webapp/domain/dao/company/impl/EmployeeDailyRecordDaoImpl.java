package com.lwxf.industry4.webapp.domain.dao.company.impl;


import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeeDailyRecordDao;
import com.lwxf.industry4.webapp.domain.dto.company.EmployeeDailyRecordDto;
import com.lwxf.industry4.webapp.domain.dto.company.EmployeeDailyRecordDtos;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecord;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-05-25 10:10:19
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("employeeDailyRecordDao")
public class EmployeeDailyRecordDaoImpl extends BaseDaoImpl<EmployeeDailyRecord, String> implements EmployeeDailyRecordDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeDailyRecord> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EmployeeDailyRecord> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<EmployeeDailyRecordDtos> findListByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EmployeeDailyRecordDtos> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<EmployeeDailyRecordDto> employeeDailyRecordList(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("employeeDailyRecordList");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EmployeeDailyRecordDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public EmployeeDailyRecordDto findDtoById(String employeeDailyRecordId) {
		String sqlId=this.getNamedSqlId("findDtoById");
		return this.sqlSession.selectOne(sqlId,employeeDailyRecordId);
	}

}