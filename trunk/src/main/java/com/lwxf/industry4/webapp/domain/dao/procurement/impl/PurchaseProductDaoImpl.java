package com.lwxf.industry4.webapp.domain.dao.procurement.impl;


import java.util.List;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;

import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.procurement.PurchaseProductDao;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseProductDto;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseProduct;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("purchaseProductDao")
public class PurchaseProductDaoImpl extends BaseDaoImpl<PurchaseProduct, String> implements PurchaseProductDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PurchaseProduct> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<PurchaseProduct> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<PurchaseProductDto> selectProductsByRequestId(String id) {
		String sqlId=this.getNamedSqlId("selectProductsByRequestId");
		return this.getSqlSession().selectList(sqlId,id);
	}


}