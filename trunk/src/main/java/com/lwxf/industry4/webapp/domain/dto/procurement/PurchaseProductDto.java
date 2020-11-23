package com.lwxf.industry4.webapp.domain.dto.procurement;

import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseProduct;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/27/027 19:29
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class PurchaseProductDto extends PurchaseProduct {
	private String productName;
	private String productId;
	private String operatorName;//入库人


	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}
