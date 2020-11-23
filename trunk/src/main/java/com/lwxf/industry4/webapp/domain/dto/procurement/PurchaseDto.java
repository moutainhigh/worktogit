package com.lwxf.industry4.webapp.domain.dto.procurement;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.procurement.Purchase;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/27/027 19:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class PurchaseDto extends Purchase {
	private String creatorName; // 创建人名称
	private String buyerName; //采购人名称
	private String supplierName; //供应公司名称
	private String storageName; //仓库名称
	private List<PurchaseProductDto> purchaseProductDtoList; //采购产品集合数组

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public List<PurchaseProductDto> getPurchaseProductDtoList() {
		return purchaseProductDtoList;
	}

	public void setPurchaseProductDtoList(List<PurchaseProductDto> purchaseProductDtoList) {
		this.purchaseProductDtoList = purchaseProductDtoList;
	}
}
