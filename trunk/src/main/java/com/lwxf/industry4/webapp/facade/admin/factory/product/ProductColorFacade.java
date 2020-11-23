package com.lwxf.industry4.webapp.facade.admin.factory.product;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/1/001 15:35
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductColorFacade extends BaseFacade {
	RequestResult selectProductColorByFilter(MapContext mapContext);
	RequestResult findAll();

	RequestResult addProductColor(ProductColor productColor);

	RequestResult updateByMapComtent(MapContext mapContext, String id,String cid);

	RequestResult deleteById(String id,String cid);
}
