package com.lwxf.industry4.webapp.domain.dao.supplier.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierDtoFowWx;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.supplier.SupplierProductDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.supplier.SupplierProductDao;
import com.lwxf.industry4.webapp.domain.entity.supplier.SupplierProduct;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-28 09:25:21
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("supplierProductDao")
public class SupplierProductDaoImpl extends BaseDaoImpl<SupplierProduct, String> implements SupplierProductDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<SupplierProduct> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<SupplierProduct> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public SupplierProduct findOneBySupplierAndProductId(String id, String productId) {
		String sqlId = this.getNamedSqlId("findOneBySupplierAndProductId");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("productId",productId);
		return this.getSqlSession().selectOne(sqlId,mapContext);
	}

	@Override
	public List<SupplierProductDto> findListBySupplierAndProductIds(String id, List productIds) {
		String sqlId = this.getNamedSqlId("findListBySupplierAndProductIds");
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		mapContext.put("productIds",productIds);
		return this.getSqlSession().selectList(sqlId,mapContext);
	}

	@Override
	public List<SupplierProduct> selectAllSupplierProduct(MapContext map) {
		String sqlId = this.getNamedSqlId("selectAllSupplierProduct");
		return this.getSqlSession().selectList(sqlId,map);
	}

	@Override
	public int deleteBySupplierId(String supplierId) {
		String sqlId = this.getNamedSqlId("deleteBySupplierId");
		return this.getSqlSession().delete(sqlId,supplierId);
	}

	@Override
	public List<SupplierProduct> findListBySupplierIdAndMaterialType(String supplierId, String code) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("supplierId",supplierId);
		mapContext.put("type",code);
		String sqlId=this.getNamedSqlId("findListBySupplierIdAndMaterialType");
		return this.sqlSession.selectList(sqlId,mapContext);
	}
}