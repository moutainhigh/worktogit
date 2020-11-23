package com.lwxf.industry4.webapp.domain.dao.company;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.dto.company.EmployeeDailyRecordDto;
import com.lwxf.industry4.webapp.domain.dto.company.EmployeeDailyRecordDtos;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecord;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-05-25 10:10:19
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface EmployeeDailyRecordDao extends BaseDao<EmployeeDailyRecord, String> {

	PaginatedList<EmployeeDailyRecord> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<EmployeeDailyRecordDtos> findListByFilter(PaginatedFilter paginatedFilter);
	PaginatedList<EmployeeDailyRecordDto> employeeDailyRecordList(PaginatedFilter paginatedFilter);

	EmployeeDailyRecordDto findDtoById(String employeeDailyRecordId);
}