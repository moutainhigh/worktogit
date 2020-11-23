package com.lwxf.industry4.webapp.domain.dao.activity.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.activity.ActivityInfoDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.activity.ActivityInfoDao;
import com.lwxf.industry4.webapp.domain.entity.activity.ActivityInfo;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:10:37
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("activityInfoDao")
public class ActivityInfoDaoImpl extends BaseDaoImpl<ActivityInfo, String> implements ActivityInfoDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityInfo> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ActivityInfo> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ActivityInfo> findIdAndCover(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findIdAndCover");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ActivityInfo> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Map<String,String>> findBAndFActivity(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findBAndFActivity");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map<String,String>> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<Map> findByCompanyId(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findByCompanyId");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<Map> findBJoinActivity(PaginatedFilter paginatedFilter) {
        String sqlId = this.getNamedSqlId("findBJoinActivity");
        //
        //  过滤查询参数
        PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
        PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
        return this.toPaginatedList(pageList);
	}

    @Override
    public PaginatedList<Map> findFActivity(PaginatedFilter paginatedFilter) {
        String sqlId = this.getNamedSqlId("findFActivity");
        //
        //  过滤查询参数
        PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
        PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
        return this.toPaginatedList(pageList);
    }


	@Override
	public int insert(ActivityInfo ActivityInfo) {
		String sqlId = this.getNamedSqlId("insert");
		return this.getSqlSession().insert(sqlId, ActivityInfo);
	}

	@Override
	public PaginatedList<Map> findByFCompanyId(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findByFCompanyId");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public ActivityInfoDto findOneById(String id) {
		String sqlId = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sqlId,id);
	}
}