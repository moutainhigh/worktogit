package com.lwxf.industry4.webapp.domain.dao.visitinfo.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dao.visitinfo.VisitInfoDao;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.entity.visitinfo.VisitInfo;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-26 15:32:34
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("visitInfoDao")
public class VisitInfoDaoImpl extends BaseDaoImpl<VisitInfo, String> implements VisitInfoDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<VisitInfo> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<VisitInfo> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}