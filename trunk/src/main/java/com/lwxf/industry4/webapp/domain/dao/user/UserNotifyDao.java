package com.lwxf.industry4.webapp.domain.dao.user;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.user.UserNotifyDto;
import com.lwxf.industry4.webapp.domain.entity.user.UserNotify;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserNotify;


/**
 * 功能：
 * 
 * @author：renzhongshan(d3shan@126.com)
 * @created：2018-06-30 08:55:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface UserNotifyDao extends BaseDao<UserNotify, String> {

	PaginatedList<UserNotify> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<UserNotifyDto> selectAllByFilter(PaginatedFilter paginatedFilter);

}