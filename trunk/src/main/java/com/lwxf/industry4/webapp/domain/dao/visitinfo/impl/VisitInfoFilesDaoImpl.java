package com.lwxf.industry4.webapp.domain.dao.visitinfo.impl;




import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.domain.dao.visitinfo.VisitInfoFilesDao;
import com.lwxf.mybatis.utils.MapContext;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfoFiles;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-26 11:42:07
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("visitInfoFilesDao")
public class VisitInfoFilesDaoImpl extends BaseDaoImpl<VisitInfoFiles, String> implements VisitInfoFilesDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<VisitInfoFiles> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<VisitInfoFiles> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	protected String getDefaultSqlNameSpace() {
		return super.getDefaultSqlNameSpace();
	}

	@Override
	protected String getDefaultDataSubject() {
		return super.getDefaultDataSubject();
	}

	@Override
	protected String getNamedSqlId(Class<?> sqlNameSpaceClass, String sqlId) {
		return super.getNamedSqlId(sqlNameSpaceClass, sqlId);
	}

	@Override
	protected String getNamedSqlId(String sqlNameSpace, String sqlId) {
		return super.getNamedSqlId(sqlNameSpace, sqlId);
	}

	@Override
	protected String getNamedSqlId(String sqlId) {
		return super.getNamedSqlId(sqlId);
	}

	@Override
	protected SqlSession getSqlSession() {
		return super.getSqlSession();
	}

	@Override
	public int insert(VisitInfoFiles entity) {
		return super.insert(entity);
	}

	@Override
	public int deleteById(String id) {
		return super.deleteById(id);
	}

	@Override
	public int updateByMapContext(MapContext mapContext) {
		return super.updateByMapContext(mapContext);
	}

	@Override
	public VisitInfoFiles selectById(String id) {
		return super.selectById(id);
	}

	@Override
	public Boolean isExist(Class<VisitInfoFiles> entityClass, String id) {
		return super.isExist(entityClass, id);
	}

	@Override
	protected PageBounds toPageBounds(Pagination pagination, List<Map<String, String>> sortItems) {
		return super.toPageBounds(pagination, sortItems);
	}

	@Override
	protected PageBounds toPageBounds(Pagination pagination) {
		return super.toPageBounds(pagination);
	}

	@Override
	protected <E> PaginatedList<E> toPaginatedList(PageList<E> pageList) {
		return super.toPaginatedList(pageList);
	}

	@Override
	protected Map<String, Object> newParamMap() {
		return super.newParamMap();
	}

	@Override
	protected String getTableName(Class<?> entityClass) {
		return super.getTableName(entityClass);
	}
}