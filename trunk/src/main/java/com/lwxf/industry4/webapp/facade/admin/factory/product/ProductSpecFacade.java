package com.lwxf.industry4.webapp.facade.admin.factory.product;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.ProductSpec;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/6/006 11:30
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductSpecFacade extends BaseFacade {
	RequestResult deleteById(String cid, String id);

	RequestResult updateProductSpec(MapContext update, String cid, String id);

	RequestResult addProductSpec(ProductSpec productSpec);

	RequestResult selectProductSpecList(String cid, String name);
}
