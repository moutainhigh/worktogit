package com.lwxf.industry4.webapp.bizservice.company.impl;


import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeDailyRecordCommentService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
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
@Service("employeeDailyRecordCommentService")
public class EmployeeDailyRecordCommentServiceImpl extends BaseServiceImpl<EmployeeDailyRecordComment, String, EmployeeDailyRecordCommentDao> implements EmployeeDailyRecordCommentService {


	@Resource

	@Override	public void setDao( EmployeeDailyRecordCommentDao employeeDailyRecordCommentDao) {
		this.dao = employeeDailyRecordCommentDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeDailyRecordComment> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<EmployeeDailyRecordCommentDto> findByemployeeDailyRecordId(String employeeDailyRecordId) {
		return this.dao.findByemployeeDailyRecordId(employeeDailyRecordId);
	}

}