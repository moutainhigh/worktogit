package com.lwxf.industry4.webapp.domain.dto.supplier;

import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.supplier.Material;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/1 0001 14:23
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "原材料信息",description = "原材料信息")
public class MaterialDto extends Material {
	@ApiModelProperty(value = "创建人",name = "creatorName",notes = "")
	private String creatorName;
	@ApiModelProperty(value = "附件列表")
	 private List<UploadFiles> files;
	@ApiModelProperty(value = "图片id")
	private String fileIds;
	@ApiModelProperty(value = "供应商报价")
	private Double price;
	@ApiModelProperty(value = "原材料状态名称")
	private String materialStatusName;
	@ApiModelProperty(value = "原材料类别名称")
	private String typeName;
	@ApiModelProperty(value = "类别id")
	private String categoryId;
	@ApiModelProperty(value = "计量单位")
	private String unitType;



	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<UploadFiles> getFiles() {
		return files;
	}

	public void setFiles(List<UploadFiles> files) {
		this.files = files;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getMaterialStatusName() {
		String materialStatusName=null;
		if(this.getMaterialStatus()!=null){
			if(this.getMaterialStatus()==0){
				materialStatusName="启用";
			}
			if(this.getMaterialStatus()==1){
				materialStatusName="下线";
			}

		}
		return materialStatusName;
	}

	public void setMaterialStatusName(String materialStatusName) {
		this.materialStatusName = materialStatusName;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
}
