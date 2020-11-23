package com.lwxf.industry4.webapp.bizservice.user;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.user.UserSchedule;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-14 11:55:53
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserScheduleService extends BaseService <UserSchedule, String> {

	PaginatedList<UserSchedule> selectByFilter(PaginatedFilter paginatedFilter);

	List<UserSchedule> findUserSchedule(String userId, String time);

	UserSchedule findScheduleByUidAndTime(String userId, String scheduleTime);
}