package com.lwxf.industry4.webapp.domain.dto.product;

import com.lwxf.industry4.webapp.domain.entity.product.ProductSpec;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/6/006 13:21
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ProductSpecDto extends ProductSpec{
	private String productCategoryName;


	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public ProductSpecDto clone(ProductSpec productSpec) {
		this.id=productSpec.getId();
		this.name=productSpec.getName();
		this.notes=productSpec.getNotes();
		this.productCategoryId=productSpec.getProductCategoryId();
		this.type=productSpec.getType();
		return this;
	}
}
