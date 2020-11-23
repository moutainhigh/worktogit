package com.lwxf.industry4.webapp.facade.admin.factory.product;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/30 0030 10:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProductPartsFacade extends BaseFacade {
	RequestResult findProducts(String branchId);

	RequestResult addProductParts(ProductParts productParts);


	RequestResult updateProductParts(String partsId, MapContext mapContext);

	RequestResult deleteProductParts(String partsId);
}
