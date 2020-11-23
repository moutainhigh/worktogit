package com.lwxf.industry4.webapp.domain.dao.warehouse.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.StorageDao;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Storage;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-13 15:21:18
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("storageDao")
public class StorageDaoImpl extends BaseDaoImpl<Storage, String> implements StorageDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<StorageDto> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<StorageDto> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public Storage findOneByName(String name,String branchId) {
		String sql = this.getNamedSqlId("findOneByName");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("name",name);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,branchId);
		return this.getSqlSession().selectOne(sql,mapContext);
	}

	@Override
	public StorageDto findOneById(String id) {
		String sql = this.getNamedSqlId("findOneById");
		return this.getSqlSession().selectOne(sql,id);
	}

	@Override
	public List<StockDto> findAllProduct() {
		String sql = this.getNamedSqlId("findAllProduct");
		return this.getSqlSession().selectList(sql);
	}



	@Override
	public Map findByOrderId(String orderId) {
		String sql = this.getNamedSqlId("findByOrderId");
		return this.getSqlSession().selectOne(sql,orderId);
	}
}