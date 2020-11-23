package com.lwxf.industry4.webapp.domain.dao.design.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.design.DesignSchemeDto;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.design.DesignSchemeDao;
import com.lwxf.industry4.webapp.domain.entity.design.DesignScheme;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 15:53:46
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("designSchemeDao")
public class DesignSchemeDaoImpl extends BaseDaoImpl<DesignScheme, String> implements DesignSchemeDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DesignScheme> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DesignScheme> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<DesignSchemeDto> selectByCondition(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByCondition");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(),paginatedFilter.getSorts());
		PageList<DesignSchemeDto> pageList = (PageList) this.getSqlSession().selectList(sqlId,paginatedFilter.getFilters(),pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Integer findCountByDesigner(String designer) {
		String sqlId = this.getNamedSqlId("findCountByDesigner");
		return this.getSqlSession().selectOne(sqlId,designer);
	}

	@Override
	public PaginatedList<DesignSchemeDto> findListByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByFilter");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(),paginatedFilter.getSorts());
		PageList<DesignSchemeDto> pageList = (PageList) this.getSqlSession().selectList(sqlId,paginatedFilter.getFilters(),pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public DesignSchemeDto findOneById(String id) {
		String sqlId = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public List<Map<String, String>> findStyleAndCountByDesigner(String designer) {
		String sqlId = this.getNamedSqlId("findStyleAndCountByDesigner");
		return this.getSqlSession().selectList(sqlId,designer);
	}

	@Override
	public List<DesignSchemeDto> findByDesignerAndStatus(MapContext map) {
		String sqlId = this.getNamedSqlId("findByDesignerAndStatus");
		return this.getSqlSession().selectList(sqlId,map);
	}


	@Override
	public Map<String, String> selectBySchemeId(String id) {
		String sqlId = this.getNamedSqlId("selectBySchemeId");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public PaginatedList<Map> findListBydesigner(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListBydesigner");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(),paginatedFilter.getSorts());
		PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId,paginatedFilter.getFilters(),pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public PaginatedList<Map> findList(PaginatedFilter filter) {
		String sqlId = this.getNamedSqlId("findList");
		PageBounds pageBounds = this.toPageBounds(filter.getPagination(),filter.getSorts());
		PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId,filter.getFilters(),pageBounds);
		return this.toPaginatedList(pageList);
	}
}