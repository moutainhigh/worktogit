package com.lwxf.industry4.webapp.facade.admin.factory.product;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.ProductCategory;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/1/001 10:59
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductCategoryFacade extends BaseFacade {
	RequestResult findAll();
	RequestResult selectProductCategoryByFilter(MapContext mapContext);
	RequestResult addProductCategory(ProductCategory productCategory);
	RequestResult updateByMapContent(MapContext mapContext,String id);
	RequestResult deleteById(String id);
}
