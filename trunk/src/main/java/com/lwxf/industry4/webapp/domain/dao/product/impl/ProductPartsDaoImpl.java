package com.lwxf.industry4.webapp.domain.dao.product.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductPartsDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-30 10:44:55
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("productPartsDao")
public class ProductPartsDaoImpl extends BaseDaoImpl<ProductParts, String> implements ProductPartsDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductParts> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProductParts> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<ProductPartsDto> findByProductType(Integer productType, String branchId) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("productType",productType);
		mapContext.put("branchId",branchId);
		String sqlId=this.getNamedSqlId("findByProductType");
		return this.sqlSession.selectList(sqlId,mapContext);
	}

	@Override
	public ProductParts findByType(Integer productType, Integer partsType, Integer produceType) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("productType",productType);
		mapContext.put("partsType",partsType);
		mapContext.put("produceType",produceType);
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		String sqlId=this.getNamedSqlId("findByType");
		return this.sqlSession.selectOne(sqlId,mapContext);
	}

	@Override
	public List<ProductPartsDto> findByOrderProductId(String orderProductId) {
		String sqlId=this.getNamedSqlId("findByOrderProductId");
		return this.sqlSession.selectList(sqlId,orderProductId);
	}

}