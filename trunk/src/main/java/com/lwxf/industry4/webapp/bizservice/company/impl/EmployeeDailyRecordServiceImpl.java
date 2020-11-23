package com.lwxf.industry4.webapp.bizservice.company.impl;


import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.company.EmployeeDailyRecordService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.company.EmployeeDailyRecordCommentDao;
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
@Service("employeeDailyRecordService")
public class EmployeeDailyRecordServiceImpl extends BaseServiceImpl<EmployeeDailyRecord, String, EmployeeDailyRecordDao> implements EmployeeDailyRecordService {


	@Resource

	@Override	public void setDao( EmployeeDailyRecordDao employeeDailyRecordDao) {
		this.dao = employeeDailyRecordDao;
	}

	@Resource(name = "employeeDailyRecordCommentDao")
	private EmployeeDailyRecordCommentDao employeeDailyRecordCommentDao;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EmployeeDailyRecord> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<EmployeeDailyRecordDto> employeeDailyRecordList(PaginatedFilter paginatedFilter) {
		return this.dao.employeeDailyRecordList(paginatedFilter);
	}

	@Override
	public PaginatedList<EmployeeDailyRecordDtos> findListByFilter(PaginatedFilter paginatedFilter) {
		PaginatedList<EmployeeDailyRecordDtos> listByFilter = this.dao.findListByFilter(paginatedFilter);
		for(EmployeeDailyRecordDtos employeeDailyRecordDto:listByFilter.getRows()){
			employeeDailyRecordDto.setCommentDtoList(this.employeeDailyRecordCommentDao.findByRecordId(employeeDailyRecordDto.getId()));
		}
		return listByFilter;
	}

	@Override
	public EmployeeDailyRecordDto findDtoById(String employeeDailyRecordId) {
		return this.dao.findDtoById(employeeDailyRecordId);
	}

}