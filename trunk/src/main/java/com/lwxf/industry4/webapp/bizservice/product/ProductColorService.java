package com.lwxf.industry4.webapp.bizservice.product;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.product.ProductColorDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-01 10:21:33
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductColorService extends BaseService <ProductColor, String> {

	PaginatedList<ProductColor> selectByFilter(PaginatedFilter paginatedFilter);
	List<ProductColor> selectByCategoryId(String id);

	List<ProductColorDto> selectProductColorByFilter(MapContext mapContext);

	List<ProductColorDto> findAll();

	boolean selectByCategoryIdAndNameAndId(String productCategoryId, String name,String id);

	boolean isExistCidAndId(String cid,String id);

	boolean isExistNameAndNoIdAndCid(String name,String id,String cid);

	List<ProductColor> selectByType(Integer type);
}