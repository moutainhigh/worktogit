package com.lwxf.industry4.webapp.domain.dao.system.impl;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.system.LogisticsCompanyDao;
import com.lwxf.industry4.webapp.domain.dto.system.LcNameAndNum;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 15:10:29
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("logisticsCompanyDao")
public class LogisticsCompanyDaoImpl extends BaseDaoImpl<LogisticsCompany, String> implements LogisticsCompanyDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<LogisticsCompany> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<LogisticsCompany> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<LogisticsCompany> findAllNormalByBranchId(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findAllNormalByBranchId");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<LcNameAndNum> findLCNameAndNum(MapContext params) {
		String sqlId = this.getNamedSqlId("findLCNameAndNum");
		return this.getSqlSession().selectList(sqlId,params);
	}

	@Override
	public LogisticsCompany findByNO(String no) {
		String sqlId = this.getNamedSqlId("findByNO");
		return this.getSqlSession().selectOne(sqlId,no);
	}

	@Override
	public LogisticsCompany findByLogisticId(String logisticId) {
		String sqlId = this.getNamedSqlId("findByLogisticId");
		return this.getSqlSession().selectOne(sqlId,logisticId);
	}

}