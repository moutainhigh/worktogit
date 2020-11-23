package com.lwxf.industry4.webapp.domain.dao.activity.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityParamsDao;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityParams;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:37
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("activityParamsDao")
public class ActivityParamsDaoImpl extends BaseDaoImpl<ActivityParams, String> implements ActivityParamsDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityParams> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ActivityParams> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public List<ActivityParams> findByActivityId(String activityId) {
		String sqlId = this.getNamedSqlId("findByActivityId");
		return this.getSqlSession().selectList(sqlId,activityId);
	}

	@Override
	public int deleteByActivityId(String activityId) {
		String sqlId = this.getNamedSqlId("deleteByActivityId");
		return this.getSqlSession().delete(sqlId,activityId);
	}
}