package com.lwxf.industry4.webapp.domain.dao.design.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.design.DesignSchemeFilesDao;
import com.lwxf.industry4.webapp.domain.entity.design.DesignSchemeFiles;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-03 15:53:46
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("designSchemeFilesDao")
public class DesignSchemeFilesDaoImpl extends BaseDaoImpl<DesignSchemeFiles, String> implements DesignSchemeFilesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DesignSchemeFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DesignSchemeFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<String> findByPath(List<String> uploadFilesList) {
		String sqlId = this.getNamedSqlId("findByPath");
		return this.getSqlSession().selectList(sqlId,uploadFilesList);
	}

	@Override
	public int updateAllTempByDesignId(String id) {
		String sqlId = this.getNamedSqlId("updateAllTempByDesignId");
		return this.getSqlSession().update(sqlId,id);
	}

	@Override
	public int updateFilesList(String id, List imgIds) {
		String sqlId = this.getNamedSqlId("updateFilesList");
		MapContext mapContext = MapContext.newOne();
		mapContext.put("designSchemeId",id);
		mapContext.put("filesList",imgIds);
		return this.getSqlSession().update(sqlId,mapContext);
	}

	@Override
	public int deleteBySchemeId(String schemeId) {
		String sqlId = this.getNamedSqlId("deleteBySchemeId");
		return this.getSqlSession().delete(sqlId,schemeId);
	}

	@Override
	public List<DesignSchemeFiles> findBySchemeId(String schemeId) {
		String sqlId = this.getNamedSqlId("findBySchemeId");
		return this.getSqlSession().selectList(sqlId,schemeId);
	}

	@Override
	public List<String> findByResouceId(String resourceId) {
		String sqlId = this.getNamedSqlId("findByResouceId");
		return this.getSqlSession().selectList(sqlId, resourceId);
	}

	@Override
	public List<DesignSchemeFiles> findListByResourceIdAndType(String id, Integer type) {
		String sqlId = this.getNamedSqlId("findListByResourceIdAndType");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("type",type);
		return this.getSqlSession().selectList(sqlId,type);
	}
}