package com.lwxf.industry4.webapp.domain.dto.supplier;

import com.lwxf.industry4.webapp.domain.entity.supplier.Supplier;
import com.lwxf.industry4.webapp.domain.entity.supplier.SupplierProduct;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

public class SupplierDto extends Supplier {

    @ApiModelProperty(value="产品列表",name="products",example = "")
    List<SupplierProduct> products;
    @ApiModelProperty(value="产品id列表",name="materialIds",example = "")
    List<String> materialIds;

    public List<SupplierProduct> getProducts() {
        return products;
    }

    public void setProducts(List<SupplierProduct> products) {
        this.products = products;
    }

    public List<String> getMaterialIds() {
        return materialIds;
    }

    public void setMaterialIds(List<String> materialIds) {
        this.materialIds = materialIds;
    }
}
