package com.lwxf.industry4.webapp.domain.dao.warehouse.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.StorageOutputInItemDao;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInItemDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputInItem;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-20 10:16:05
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("storageOutputInItemDao")
public class StorageOutputInItemDaoImpl extends BaseDaoImpl<StorageOutputInItem, String> implements StorageOutputInItemDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<StorageOutputInItem> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<StorageOutputInItem> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<StorageOutputInItemDto> findListByStorageOutputInId(String id) {
		String sql = this.getNamedSqlId("findListByStorageOutputInId");
		return this.getSqlSession().selectList(sql,id);
	}

	@Override
	public PaginatedList<StorageOutputInDto> findInAndOutListBypid(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findInAndOutListBypid");
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<StorageOutputInDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

}