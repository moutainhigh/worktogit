package com.lwxf.industry4.webapp.domain.dao.common.impl;


import java.util.List;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.common.CityAreaDao;
import com.lwxf.industry4.webapp.domain.entity.common.CityArea;


/**
 * 功能：
 * 
 * @author：wangmingyuan(wangmingyuan@126.com)
 * @created：2018-06-20 20:23:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("cityAreaDao")
public class CityAreaDaoImpl extends BaseDaoImpl<CityArea, String> implements CityAreaDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<CityArea> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<CityArea> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}



	@Override
	public List<CityArea> selectCityAreaListByLevel(int levelType) {
		String sqlId = this.getNamedSqlId("selectCityAreaListByLevel");
		return this.getSqlSession().selectList(sqlId,levelType);
	}

	@Override
	public List<CityArea> selectCityAreaListByParentId(String parentId) {
		String sqlId = this.getNamedSqlId("selectCityAreaListByParentId");
		return this.getSqlSession().selectList(sqlId,parentId);
	}

	@Override
	public CityArea selectByMergerName(String mergerName) {
		String sqlId = this.getNamedSqlId("selectByMergerName");
		return this.getSqlSession().selectOne(sqlId,mergerName);
	}

	@Override
	public CityArea selectByNameAndLevelType(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("selectByNameAndLevelType");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public CityArea selectByNameAndParentId(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("selectByNameAndParentId");
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}


	@Override
	public List<CityArea> findAllCityArea() {
		String sqlId = this.getNamedSqlId("findAllCityArea");
		return this.getSqlSession().selectList(sqlId);
	}


	@Override
	public List<CityArea> findALl() {
		String sqlId = this.getNamedSqlId("findALl");
		return this.getSqlSession().selectList(sqlId);
	}

}