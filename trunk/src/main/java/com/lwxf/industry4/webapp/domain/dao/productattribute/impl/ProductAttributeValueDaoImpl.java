package com.lwxf.industry4.webapp.domain.dao.productattribute.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.productattribute.ProductAttributeValueDao;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeValue;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-07-29 10:28:04
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("productAttributeValueDao")
public class ProductAttributeValueDaoImpl extends BaseDaoImpl<ProductAttributeValue, String> implements ProductAttributeValueDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductAttributeValue> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProductAttributeValue> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<ProductAttributeValue> findByKeyId(MapContext map) {
		String sqlId = this.getNamedSqlId("findByKeyId");
		return this.getSqlSession().selectList(sqlId,map);
	}

	@Override
	public ProductAttributeValue findByKeyIdAndValue(MapContext map0) {
		String sqlId = this.getNamedSqlId("findByKeyIdAndValue");
		return this.getSqlSession().selectOne(sqlId,map0);
	}

}