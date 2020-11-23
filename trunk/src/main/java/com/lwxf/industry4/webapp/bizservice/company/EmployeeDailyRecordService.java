package com.lwxf.industry4.webapp.bizservice.company;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
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
public interface EmployeeDailyRecordService extends BaseService <EmployeeDailyRecord, String> {

	PaginatedList<EmployeeDailyRecord> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<EmployeeDailyRecordDto> employeeDailyRecordList(PaginatedFilter paginatedFilter);
	PaginatedList<EmployeeDailyRecordDtos> findListByFilter(PaginatedFilter paginatedFilter);

	EmployeeDailyRecordDto findDtoById(String employeeDailyRecordId);
}