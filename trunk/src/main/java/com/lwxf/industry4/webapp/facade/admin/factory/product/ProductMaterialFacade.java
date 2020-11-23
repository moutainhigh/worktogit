package com.lwxf.industry4.webapp.facade.admin.factory.product;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.product.ProductMaterial;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/3/003 14:48
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductMaterialFacade extends BaseFacade {

	RequestResult selectProductMaterialList(String cid, String name);
	RequestResult addProductMaterial(ProductMaterial productMaterial);
	RequestResult updateProductMaterial(MapContext update,String cid,String id);
	RequestResult deleteById(String cid,String id);
}
