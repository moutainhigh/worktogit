package com.lwxf.industry4.webapp.domain.dto.productattribute;

import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeKey;
import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeValue;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/29 10:58
 * @Description:
 */
public class ProductAttributeKeyDto extends ProductAttributeKey {
    private List<ProductAttributeValue> attributeValueList;
    private String attributeValues;//多个值逗号拼接成字符串

    public List<ProductAttributeValue> getAttributeValueList() {
        return attributeValueList;
    }

    public void setAttributeValueList(List<ProductAttributeValue> attributeValueList) {
        this.attributeValueList = attributeValueList;
    }

    public String getAttributeValues() {
        return attributeValues;
    }

    public void setAttributeValues(String attributeValues) {
        this.attributeValues = attributeValues;
    }
}
