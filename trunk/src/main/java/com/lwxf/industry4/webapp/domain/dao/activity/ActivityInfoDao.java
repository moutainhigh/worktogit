package com.lwxf.industry4.webapp.domain.dao.activity;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityInfoDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityInfo;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:37
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ActivityInfoDao extends BaseDao<ActivityInfo, String> {

	PaginatedList<ActivityInfo> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<ActivityInfo> findIdAndCover(PaginatedFilter paginatedFilter);

	PaginatedList<Map<String,String>> findBAndFActivity(PaginatedFilter paginatedFilter);


	PaginatedList<Map> findByCompanyId(PaginatedFilter paginatedFilter);

	PaginatedList<Map> findBJoinActivity(PaginatedFilter paginatedFilter);
	PaginatedList<Map> findFActivity(PaginatedFilter paginatedFilter);


    PaginatedList<Map> findByFCompanyId(PaginatedFilter paginatedFilter);

	ActivityInfoDto findOneById(String id);
}