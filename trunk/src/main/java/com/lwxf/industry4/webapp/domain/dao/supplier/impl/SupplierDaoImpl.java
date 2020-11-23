package com.lwxf.industry4.webapp.domain.dao.supplier.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.supplier.SupplierDao;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDto;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：3965488@qq.com
 * @created：2019-06-12 15:33:44
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("supplierDao")
public class SupplierDaoImpl extends BaseDaoImpl<Supplier, String> implements SupplierDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Supplier> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Supplier> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public PaginatedList<SupplierDtoFowWx> selectDtoByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectDtoByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<SupplierDtoFowWx> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public SupplierDtoFowWx selectDtoById(String id) {
		String sqlId = this.getNamedSqlId("selectDtoById");
		return this.getSqlSession().selectOne(sqlId,id);
	}

	@Override
	public MapContext countSupplierToday(String branchId) {
		String sqlId = this.getNamedSqlId("countSupplierToday");
		return this.getSqlSession().selectOne(sqlId,branchId);
	}

	@Override
	public List<SupplierDto> findListByType(MapContext mapContext) {
		String sqlId = this.getNamedSqlId("findListByType");
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public PaginatedList<Map> findSupplierProducts(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("findSupplierProducts");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<Map> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}
}