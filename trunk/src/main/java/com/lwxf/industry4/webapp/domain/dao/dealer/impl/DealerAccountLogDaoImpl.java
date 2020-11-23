package com.lwxf.industry4.webapp.domain.dao.dealer.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.dealer.DealerAccountLogDao;
import com.lwxf.industry4.webapp.domain.dto.dealer.DealerAccountLogDto;
import com.lwxf.industry4.webapp.domain.entity.dealer.DealerAccountLog;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 18:46:16
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("dealerAccountLogDao")
public class DealerAccountLogDaoImpl extends BaseDaoImpl<DealerAccountLog, String> implements DealerAccountLogDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerAccountLog> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DealerAccountLog> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<DealerAccountLog> findByCompanyIdAndCondition(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findByCompanyIdAndCondition");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<DealerAccountLog> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public DealerAccountLogDto selectByLogId(String logId) {
		String sqlId = this.getNamedSqlId("selectByLogId");
		return this.getSqlSession().selectOne(sqlId,logId);
	}

	@Override
	public DealerAccountLog findByOrderIdAndType(MapContext orderIdAndType) {
		String sqlId = this.getNamedSqlId("findByOrderIdAndType");
		return this.getSqlSession().selectOne(sqlId,orderIdAndType);
	}
}