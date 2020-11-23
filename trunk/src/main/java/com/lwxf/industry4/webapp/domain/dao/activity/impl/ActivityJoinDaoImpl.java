package com.lwxf.industry4.webapp.domain.dao.activity.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityJoinDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityJoinDao;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityJoin;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:38
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("activityJoinDao")
public class ActivityJoinDaoImpl extends BaseDaoImpl<ActivityJoin, String> implements ActivityJoinDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityJoin> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ActivityJoin> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public ActivityJoin findOneByCidAndUidAndAIdAndType(MapContext params) {
		String sqlId = this.getNamedSqlId("findOneByCidAndUidAndAIdAndType");
		return this.getSqlSession().selectOne(sqlId,params);
	}

	@Override
	public List<Map> findListByCidAndUidAndAIdAndType(MapContext params) {
		String sqlId = this.getNamedSqlId("findListByCidAndUidAndAIdAndType");
		return this.getSqlSession().selectList(sqlId,params);
	}

	@Override
	public PaginatedList<ActivityJoinDto> findByActivityId(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByActivityId");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ActivityJoinDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public boolean hasJoiner(String activityId) {
		String sqlId = this.getNamedSqlId("hasJoiner");
		int count = this.getSqlSession().selectOne(sqlId,activityId);
		if(count>0){
			return true;
		}else{
			return false;
		}
	}
}