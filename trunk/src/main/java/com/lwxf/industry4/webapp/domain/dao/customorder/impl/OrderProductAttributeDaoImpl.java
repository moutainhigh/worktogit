package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.OrderProductAttributeDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductAttribute;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-07-29 10:35:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("orderProductAttributeDao")
public class OrderProductAttributeDaoImpl extends BaseDaoImpl<OrderProductAttribute, String> implements OrderProductAttributeDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OrderProductAttribute> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<OrderProductAttribute> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<OrderProductAttribute> findListByProductId(String id) {
		String sqlId=this.getNamedSqlId("findListByProductId");
		return this.getSqlSession().selectList(sqlId,id);
	}

}