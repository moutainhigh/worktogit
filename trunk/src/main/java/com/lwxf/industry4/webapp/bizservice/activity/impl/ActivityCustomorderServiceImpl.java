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
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityCustomorderDao;
import com.lwxf.industry4.webapp.bizservice.activity.ActivityCustomorderService;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityCustomorder;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:38
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("activityCustomorderService")
public class ActivityCustomorderServiceImpl extends BaseServiceImpl<ActivityCustomorder, String, ActivityCustomorderDao> implements ActivityCustomorderService {


	@Resource

	@Override	public void setDao( ActivityCustomorderDao activityCustomorderDao) {
		this.dao = activityCustomorderDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityCustomorder> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

}