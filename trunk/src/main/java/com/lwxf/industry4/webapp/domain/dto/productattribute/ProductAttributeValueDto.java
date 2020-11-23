package com.lwxf.industry4.webapp.domain.dto.productattribute;

import com.lwxf.industry4.webapp.domain.entity.productattribute.ProductAttributeValue;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/29 11:50
 * @Description:
 */
public class ProductAttributeValueDto extends ProductAttributeValue {
    private String keyName;

    public String getKeyName() {
        return keyName;
    }

    public void setKeyName(String keyName) {
        this.keyName = keyName;
    }
}
