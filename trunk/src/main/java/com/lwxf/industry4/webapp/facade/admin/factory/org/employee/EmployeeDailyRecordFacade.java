package com.lwxf.industry4.webapp.facade.admin.factory.org.employee;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecord;
import com.lwxf.industry4.webapp.domain.entity.company.EmployeeDailyRecordComment;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/25/025 14:30
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface EmployeeDailyRecordFacade extends BaseFacade {
	RequestResult addDailyrecord(EmployeeDailyRecord employeeDailyRecord);

	RequestResult findDailyrecord(MapContext mapContext, Integer pageSize, Integer pageNum);

	RequestResult updateDailyrecord(String id, MapContext mapContext);

	RequestResult deleteDailyrecord(String id);

	RequestResult addDailyrecordComment(String id,EmployeeDailyRecordComment employeeDailyRecordComment);

	RequestResult deleteComment(String id, String commentId);
}
