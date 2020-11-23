package com.lwxf.industry4.webapp.bizservice.activity.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityParamsDao;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityParamsService;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityParams;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:37
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("activityParamsService")
public class ActivityParamsServiceImpl extends BaseServiceImpl<ActivityParams, String, ActivityParamsDao> implements ActivityParamsService {


	@Resource

	@Override	public void setDao( ActivityParamsDao activityParamsDao) {
		this.dao = activityParamsDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityParams> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}


	@Override
	public List<ActivityParams> findByActivityId(String activityId) {
		return this.dao.findByActivityId(activityId);
	}

	@Override
	public int deleteByActivityId(String activityId) {
		return this.dao.deleteByActivityId(activityId);
	}
}