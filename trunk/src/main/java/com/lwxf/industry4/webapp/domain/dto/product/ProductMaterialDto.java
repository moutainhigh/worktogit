package com.lwxf.industry4.webapp.domain.dto.product;

import com.lwxf.industry4.webapp.domain.entity.product.ProductMaterial;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/3/003 17:13
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ProductMaterialDto extends ProductMaterial {
	private String productCategoryName;


	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public ProductMaterialDto clone(ProductMaterial productMaterial) {
		this.id=productMaterial.getId();
		this.name=productMaterial.getName();
		this.notes=productMaterial.getNotes();
		this.productCategoryId=productMaterial.getProductCategoryId();
		this.type=productMaterial.getType();
		return this;
	}
}
