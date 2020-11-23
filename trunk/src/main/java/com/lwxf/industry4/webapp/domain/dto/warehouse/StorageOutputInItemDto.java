package com.lwxf.industry4.webapp.domain.dto.warehouse;

import com.lwxf.industry4.webapp.common.enums.storage.StorageOutputInType;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputInItem;

import java.util.Date;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/20/020 13:37
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class StorageOutputInItemDto extends StorageOutputInItem {
	private String materialName;
	private String supplierName;
	private String materialSize;
	private String unitType;
	private String color;
	private Date created;
	private String creatorName;
	private String no;
	private Integer type;
	private String typeName;//出入库类型
	private String statusName;
	private Integer status;


	public String getMaterialName() {
		return materialName;
	}

	public void setMaterialName(String materialName) {
		this.materialName = materialName;
	}

	public String getSupplierName() {
		return supplierName;
	}

	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		String value="";
		StorageOutputInType byValue = StorageOutputInType.getByValue(this.type);
		if(byValue!=null){
			value=byValue.getName();
		}
		return value;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
