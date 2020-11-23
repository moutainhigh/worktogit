package com.lwxf.industry4.webapp.domain.dao.assemble.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.dispatchList.ConstructionInspectionDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.assemble.ConstructionInspectionDao;
import com.lwxf.industry4.webapp.domain.entity.assemble.ConstructionInspection;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-19 15:05:23
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("constructionInspectionDao")
public class ConstructionInspectionDaoImpl extends BaseDaoImpl<ConstructionInspection, String> implements ConstructionInspectionDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ConstructionInspection> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ConstructionInspection> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public ConstructionInspectionDto findConstructionByDispatchId(String dispatchListId) {
		String sqlId = this.getNamedSqlId("findConstructionByDispatchId");
		return this.getSqlSession().selectOne(sqlId,dispatchListId);
	}

}