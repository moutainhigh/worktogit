package com.lwxf.industry4.webapp.bizservice.user;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.dto.user.UserNotifyDto;
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
public interface UserNotifyService extends BaseService <UserNotify, String> {

	PaginatedList<UserNotify> selectByFilter(PaginatedFilter paginatedFilter);
	PaginatedList<UserNotifyDto> selectAllByFilter(PaginatedFilter paginatedFilter);

}