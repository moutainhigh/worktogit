package com.lwxf.industry4.webapp.bizservice.user.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.user.UserScheduleDao;
import com.lwxf.industry4.webapp.bizservice.user.UserScheduleService;
import com.lwxf.industry4.webapp.domain.entity.user.UserSchedule;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-14 11:55:53
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("userScheduleService")
public class UserScheduleServiceImpl extends BaseServiceImpl<UserSchedule, String, UserScheduleDao> implements UserScheduleService {


	@Resource

	@Override	public void setDao( UserScheduleDao userScheduleDao) {
		this.dao = userScheduleDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UserSchedule> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<UserSchedule> findUserSchedule(String userId, String time) {
		return this.dao.findUserSchedule(userId,time);
	}

	@Override
	public UserSchedule findScheduleByUidAndTime(String userId, String scheduleTime) {
		return this.dao.findScheduleByUidAndTime(userId,scheduleTime);
	}

}