package com.lwxf.industry4.webapp.domain.dao.product.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.thoughtworks.xstream.core.util.ThreadSafeSimpleDateFormat;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductDoorDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductDoor;


/**
 * 功能：
 * 
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-02-22 13:07:48
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("productDoorDao")
public class ProductDoorDaoImpl extends BaseDaoImpl<ProductDoor, String> implements ProductDoorDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductDoor> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProductDoor> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}


	@Override
	public List<ProductDoor> selectByCategoryId(String categoryId) {
		String sql = this.getNamedSqlId("selectByCategoryId");
		return this.getSqlSession().selectList(sql,categoryId);
	}

	@Override
	public List<ProductDoor> findAll() {
		String sqlId = this.getNamedSqlId("findAll");
		return this.getSqlSession().selectList(sqlId);
	}
}