package com.lwxf.industry4.webapp.domain.dao.user;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.user.UserScheduleItem;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-14 11:55:53
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface UserScheduleItemDao extends BaseDao<UserScheduleItem, String> {

	PaginatedList<UserScheduleItem> selectByFilter(PaginatedFilter paginatedFilter);

	List<UserScheduleItem> findUserScheduleItem(String userScheduleId);
}