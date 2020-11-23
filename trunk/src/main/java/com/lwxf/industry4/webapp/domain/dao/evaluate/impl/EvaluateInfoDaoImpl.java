package com.lwxf.industry4.webapp.domain.dao.evaluate.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.evaluate.EvaluateInfoDao;
import com.lwxf.industry4.webapp.domain.entity.evaluate.EvaluateInfo;
import org.springframework.stereotype.Repository;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-11-29 14:39:15
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("evaluateInfoDao")
public class EvaluateInfoDaoImpl extends BaseDaoImpl<EvaluateInfo, String> implements EvaluateInfoDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<EvaluateInfo> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<EvaluateInfo> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}