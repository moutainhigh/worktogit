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
import com.lwxf.industry4.webapp.domain.dao.user.UserScheduleItemDao;
import com.lwxf.industry4.webapp.bizservice.user.UserScheduleItemService;
import com.lwxf.industry4.webapp.domain.entity.user.UserScheduleItem;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-14 11:55:53
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("userScheduleItemService")
public class UserScheduleItemServiceImpl extends BaseServiceImpl<UserScheduleItem, String, UserScheduleItemDao> implements UserScheduleItemService {


	@Resource

	@Override	public void setDao( UserScheduleItemDao userScheduleItemDao) {
		this.dao = userScheduleItemDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UserScheduleItem> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<UserScheduleItem> findUserScheduleItem(String userScheduleId) {
		return this.dao.findUserScheduleItem(userScheduleId);
	}

}