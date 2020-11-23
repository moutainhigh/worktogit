package com.lwxf.industry4.webapp.bizservice.activity.impl;


import java.util.List;
import java.util.Map;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityJoinDto;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityJoinDao;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityJoinService;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityJoin;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:38
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("activityJoinService")
public class ActivityJoinServiceImpl extends BaseServiceImpl<ActivityJoin, String, ActivityJoinDao> implements ActivityJoinService {


	@Resource

	@Override	public void setDao( ActivityJoinDao activityJoinDao) {
		this.dao = activityJoinDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityJoin> selectByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public ActivityJoin findOneByCidAndUidAndAIdAndType(MapContext params) {
		return this.dao.findOneByCidAndUidAndAIdAndType(params);
	}

	@Override
	public List<Map> findListByCidAndUidAndAIdAndType(MapContext params) {
		return this.dao.findListByCidAndUidAndAIdAndType(params);
	}

	@Override
	public PaginatedList<ActivityJoinDto> findListByActivityId(PaginatedFilter paginatedFilter) {
		return this.dao.findByActivityId(paginatedFilter) ;
	}

	@Override
	public boolean hasJoiner(String activityId) {
		return this.dao.hasJoiner(activityId) ;
	}
}