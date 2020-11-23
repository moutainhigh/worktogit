package com.lwxf.industry4.webapp.domain.dto.product;

import com.lwxf.industry4.webapp.domain.entity.product.ProductCategory;
import com.lwxf.industry4.webapp.domain.entity.product.ProductColor;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/3/003 17:08
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class ProductColorDto extends ProductColor {
	private String productCategoryName;


	public String getProductCategoryName() {
		return productCategoryName;
	}

	public void setProductCategoryName(String productCategoryName) {
		this.productCategoryName = productCategoryName;
	}

	public ProductColorDto clone(ProductColor productColor) {
		this.id=productColor.getId();
		this.name=productColor.getName();
		this.productCategoryId=productColor.getProductCategoryId();
		this.type=productColor.getType();
		return this;
	}
}
