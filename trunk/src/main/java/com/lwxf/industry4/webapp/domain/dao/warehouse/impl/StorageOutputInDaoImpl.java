package com.lwxf.industry4.webapp.domain.dao.warehouse.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.StorageOutputInDao;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputIn;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-20 10:16:05
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("storageOutputInDao")
public class StorageOutputInDaoImpl extends BaseDaoImpl<StorageOutputIn, String> implements StorageOutputInDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<StorageOutputIn> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<StorageOutputIn> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<StorageOutputInDto> findListByPaginateFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findListByPaginateFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<StorageOutputInDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public StorageOutputIn findOneByNo(String no,String branchId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("no",no);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		String sql = this.getNamedSqlId("findOneByNo");
		return this.getSqlSession().selectOne(sql,mapContext);
	}

}