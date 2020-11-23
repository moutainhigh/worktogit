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
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityFilesDao;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityFilesService;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:37
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("activityFilesService")
public class ActivityFilesServiceImpl extends BaseServiceImpl<ActivityFiles, String, ActivityFilesDao> implements ActivityFilesService {


	@Resource

	@Override	public void setDao( ActivityFilesDao activityFilesDao) {
		this.dao = activityFilesDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}



	@Override
	public ActivityFiles findByActivityId(String activityId) {
		return this.dao.findByActivityId(activityId) ;
	}
}