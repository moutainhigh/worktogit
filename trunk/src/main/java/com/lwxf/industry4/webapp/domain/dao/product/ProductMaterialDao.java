package com.lwxf.industry4.webapp.domain.dao.product;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductMaterialDto;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.product.ProductMaterial;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface ProductMaterialDao extends BaseDao<ProductMaterial, String> {

	PaginatedList<ProductMaterial> selectByFilter(PaginatedFilter paginatedFilter);

	List<ProductMaterial> selectByCategoryId(String id);

	List<ProductMaterialDto> selectProductMaterialList(String cid, String name);

	boolean isExistByProductMaterial(ProductMaterial productMaterial);

	List<ProductMaterial> findAll();

	List<ProductMaterial> selectByType(Integer type);
}