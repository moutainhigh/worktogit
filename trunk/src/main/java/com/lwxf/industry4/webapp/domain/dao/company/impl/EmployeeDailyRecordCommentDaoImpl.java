package com.lwxf.industry4.webapp.domain.dao.company.impl;


import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.company.EmployeeDailyRecordCommentDto;
import com.lwxf.industry4.webapp.domain.dto.company.EmployeeDailyRecordCommentDtos;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeeDailyRecordCommentDao;
import com.lwxf.industry4.webapp.domain.dto.company.EmployeeDailyRecordCommentDto;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecordComment;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-05-25 10:10:20
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("employeeDailyRecordCommentDao")
public class EmployeeDailyRecordCommentDaoImpl extends BaseDaoImpl<EmployeeDailyRecordComment, String> implements EmployeeDailyRecordCommentDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeDailyRecordComment> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EmployeeDailyRecordComment> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<EmployeeDailyRecordCommentDtos> findByRecordId(String id) {
		String sqlId = this.getNamedSqlId("findByRecordId");
		return this.getSqlSession().selectList(sqlId,id);
	}

	@Override
	public List<EmployeeDailyRecordCommentDto> findByemployeeDailyRecordId(String employeeDailyRecordId) {
		String sqlId=this.getNamedSqlId("findByemployeeDailyRecordId");
		return this.sqlSession.selectList(sqlId,employeeDailyRecordId);
	}

}