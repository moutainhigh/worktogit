package com.lwxf.industry4.webapp.domain.dto.warehouse;

import com.lwxf.industry4.webapp.common.enums.storage.StorageOutputInType;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputIn;

import java.util.List;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/20/020 13:33
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class StorageOutputInDto extends StorageOutputIn {
	private String creatorName;  //创建人名称
	private String storageName;  //仓库名称
	private List<StorageOutputInItemDto> storageOutputInItemList;  //出入库单条目DTO集合
	private List purchaseProductIds;  //采购单产品条目主键ID集合
	private String supplierName;  //供应商名称
	private String typeName;//出入口类型
	private String purchaseName;//采购单名称

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getStorageName() {
		return storageName;
	}

	public void setStorageName(String storageName) {
		this.storageName = storageName;
	}

	public List<StorageOutputInItemDto> getStorageOutputInItemList() {
		return storageOutputInItemList;
	}

	public void setStorageOutputInItemList(List<StorageOutputInItemDto> storageOutputInItemList) {
		this.storageOutputInItemList = storageOutputInItemList;
	}

	public List getPurchaseProductIds() {
		return purchaseProductIds;
	}

	public void setPurchaseProductIds(List purchaseProductIds) {
		this.purchaseProductIds = purchaseProductIds;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getTypeName() {
		String name="";
		StorageOutputInType byValue = StorageOutputInType.getByValue(this.getType());
		if(byValue!=null){
			 name =byValue .getName();
		}
		return name;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getPurchaseName() {
		return purchaseName;
	}

	public void setPurchaseName(String purchaseName) {
		this.purchaseName = purchaseName;
	}
}
