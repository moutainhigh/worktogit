package com.lwxf.industry4.webapp.domain.dao.product.impl;


import java.util.List;
import java.util.Map;


import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.miemiedev.mybatis.paginator.domain.PageList;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductMaterialDto;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDaoImpl;
import com.lwxf.industry4.webapp.domain.dao.product.ProductMaterialDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductMaterial;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Repository("productMaterialDao")
public class ProductMaterialDaoImpl extends BaseDaoImpl<ProductMaterial, String> implements ProductMaterialDao {

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<ProductMaterial> selectByFilter(PaginatedFilter paginatedFilter) {
		String sqlId = this.getNamedSqlId("selectByFilter");
		//
		//  过滤查询参数
		PageBounds pageBounds = this.toPageBounds(paginatedFilter.getPagination(), paginatedFilter.getSorts());
		PageList<ProductMaterial> pageList = (PageList) this.getSqlSession().selectList(sqlId, paginatedFilter.getFilters(), pageBounds);
		return this.toPaginatedList(pageList);
	}

	@Override
	public List<ProductMaterial> selectByCategoryId(String id) {
		String sql = this.getNamedSqlId("selectByCategoryId");
		return this.getSqlSession().selectList(sql,id);
	}

	@Override
	public List<ProductMaterialDto> selectProductMaterialList(String cid, String name) {
		String sql = this.getNamedSqlId("selectProductMaterialList");
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.REQUEST_PARAMETER_KEY_CID,cid);
		mapContext.put(WebConstant.KEY_ENTITY_NAME,name);
		return this.getSqlSession().selectList(sql,mapContext);
	}

	@Override
	public boolean isExistByProductMaterial(ProductMaterial productMaterial) {
		String sql = this.getNamedSqlId("isExistByProductMaterial");
		return this.getSqlSession().selectOne(sql,productMaterial);
	}

	@Override
	public List<ProductMaterial> findAll() {
		String sql = this.getNamedSqlId("findAll");
		return this.getSqlSession().selectList(sql);
	}

	@Override
	public List<ProductMaterial> selectByType(Integer type) {
		String sqlId = this.getNamedSqlId("selectByType");
		return this.getSqlSession().selectList(sqlId,type);
	}
}