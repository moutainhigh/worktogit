package com.lwxf.industry4.webapp.domain.dao.customorder.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.customorder.OrderProductPartsDao;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductParts;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-30 10:50:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("orderProductPartsDao")
public class OrderProductPartsDaoImpl extends BaseDaoImpl<OrderProductParts, String> implements OrderProductPartsDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<OrderProductParts> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<OrderProductParts> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<OrderProductParts> findByProductPartsId(String partsId) {
		String sqlId=this.getNamedSqlId("findByProductPartsId");
		return this.sqlSession.selectList(sqlId,partsId);
	}

	@Override
	public List<OrderProductParts> findByProductId(String id) {
		String sqlId=this.getNamedSqlId("findByProductId");
		return this.sqlSession.selectList(sqlId,id);
	}

	@Override
	public int deleteByProductId(String id) {
		String sqlId=this.getNamedSqlId("deleteByProductId");
		return this.sqlSession.delete(sqlId,id);
	}

	@Override
	public OrderProductParts findByProductIdAndPartsId(String productId, String id) {
		String sqlId=this.getNamedSqlId("findByProductIdAndPartsId");
		MapContext mapContext=MapContext.newOne();
		mapContext.put("productId",productId);
		mapContext.put("productPartId",id);
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

}