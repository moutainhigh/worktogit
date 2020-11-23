package com.lwxf.industry4.webapp.domain.dto.warehouse;

import com.lwxf.industry4.webapp.domain.entity.warehouse.Storage;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/13/013 16:57
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class StorageDto extends Storage {
	private String storekeeperName;
	private String categoryName;
	private String creatorName;
	private Integer categoryType;
	private String typeName;

	public String getStorekeeperName() {
		return storekeeperName;
	}

	public void setStorekeeperName(String storekeeperName) {
		this.storekeeperName = storekeeperName;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public Integer getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(Integer categoryType) {
		this.categoryType = categoryType;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
}
