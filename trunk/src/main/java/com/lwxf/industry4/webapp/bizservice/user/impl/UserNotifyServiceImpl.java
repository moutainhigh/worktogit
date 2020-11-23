package com.lwxf.industry4.webapp.bizservice.user.impl;


import javax.annotation.Resource;

import com.lwxf.industry4.webapp.domain.dto.user.UserNotifyDto;
import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.user.UserNotifyService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.user.UserNotifyDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserNotify;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;


/**
 * 功能：
 * 
 * @author：renzhongshan(d3shan@126.com)
 * @created：2018-06-30 08:55:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("userNotifyService")
public class UserNotifyServiceImpl extends BaseServiceImpl<UserNotify, String, UserNotifyDao> implements UserNotifyService {


	@Resource

	@Override	public void setDao( UserNotifyDao userNotifyDao) {
		this.dao = userNotifyDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<UserNotify> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public PaginatedList<UserNotifyDto> selectAllByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectAllByFilter(paginatedFilter);
	}
}