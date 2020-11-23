package com.lwxf.industry4.webapp.bizservice.product;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductSpecDto;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductSpec;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-06 11:37:31
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductSpecService extends BaseService <ProductSpec, String> {

	PaginatedList<ProductSpec> selectByFilter(PaginatedFilter paginatedFilter);

	boolean isExistByProductSpec(ProductSpec productSpec);

	List<ProductSpecDto> selectProductSpecList(String cid, String name);

	List<ProductSpec> selectByCategoryId(String id);

	List<ProductSpec> findAll();

	List<ProductSpec> selectByType(Integer type);
}