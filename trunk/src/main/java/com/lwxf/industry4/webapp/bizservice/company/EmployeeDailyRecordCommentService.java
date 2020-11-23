package com.lwxf.industry4.webapp.bizservice.company;


import java.util.List;

import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
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
public interface EmployeeDailyRecordCommentService extends BaseService <EmployeeDailyRecordComment, String> {

	PaginatedList<EmployeeDailyRecordComment> selectByFilter(PaginatedFilter paginatedFilter);

	List<EmployeeDailyRecordCommentDto> findByemployeeDailyRecordId(String employeeDailyRecordId);
}