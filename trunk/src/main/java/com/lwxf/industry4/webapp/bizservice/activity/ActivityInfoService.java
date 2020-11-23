package com.lwxf.industry4.webapp.bizservice.activity;


import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityDto;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityInfoDto;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityInfo;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:37
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ActivityInfoService extends BaseService <ActivityInfo, String> {

	PaginatedList<ActivityInfo> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<ActivityInfo> findIdAndCover(PaginatedFilter paginatedFilter);

	PaginatedList<Map<String,String>> findBAndFActivity(PaginatedFilter paginatedFilter);

	PaginatedList<Map> findByCompanyId(PaginatedFilter filter);

	PaginatedList<Map> findBJoinActivity(PaginatedFilter paginatedFilter);

	PaginatedList<Map> findFActivity(PaginatedFilter paginatedFilter);

	int addActivityInfo(ActivityDto activityDto);

	PaginatedList<Map> findByFCompanyId(PaginatedFilter paginatedFilter);

	ActivityInfoDto findOneById(String id);
}