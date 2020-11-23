package com.lwxf.industry4.webapp.domain.dao.jdproduct.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.jdproduct.JdProductInfoDao;
import com.lwxf.industry4.webapp.domain.entity.jdproduct.JdProductInfo;
import org.springframework.stereotype.Repository;

import java.util.Date;


/**
 * 功能：
 * 
 * @author：lyh
 * @created：2019-12-02 16:50:47
 * @version：
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("jdProductInfoDao")
public class JdProductInfoDaoImpl extends BaseDaoImpl<JdProductInfo, String> implements JdProductInfoDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<JdProductInfo> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<JdProductInfo> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public int deleteByFetchingTime(Date fetchingTime) {
		String sqlId = this.getNamedSqlId("deleteByFetchingTime");
        int sqlId1 = this.getSqlSession().delete(sqlId,fetchingTime);
        return sqlId1;
	}



















}