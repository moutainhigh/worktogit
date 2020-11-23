package com.lwxf.industry4.webapp.facade.admin.factory.productattribute;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.productattribute.ProductAttributeKeyDto;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeKey;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/29 10:39
 * @Description:
 */
public interface ProductAttributeFacade extends BaseFacade {
    RequestResult findProductAttributes(String branchId,String status,String name);

    RequestResult addProductAttributekey(ProductAttributeKey attributeKey);

    RequestResult addProductAttributeValue(String keyId, String attributeValues);

    RequestResult updateProductAttribute(String keyId, MapContext mapContext);

    RequestResult deleteProductAttribute(String keyId);

    RequestResult addProductAttribute(List<ProductAttributeKeyDto> attributeKeys);

    RequestResult updateProductAttributeValue(String valueId, MapContext mapContext);

    RequestResult deleteProductAttributeValue(String valueId);
}
