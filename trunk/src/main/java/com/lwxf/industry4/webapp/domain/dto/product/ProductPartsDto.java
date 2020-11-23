package com.lwxf.industry4.webapp.domain.dto.product;

import io.swagger.annotations.ApiModel;

import com.lwxf.industry4.webapp.domain.entity.product.ProductParts;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/30 0030 11:14
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "产品部件信息",description = "产品部件信息")
public class ProductPartsDto extends ProductParts {
	private String productTypeName;//产品名称
	private String partsTypeName;//部件名称
	private String produceTypeName;//生产方式
	private String partsName;//组合名称（部件名称+生产方式）

	public String getProductTypeName() {
		return productTypeName;
	}

	public void setProductTypeName(String productTypeName) {
		this.productTypeName = productTypeName;
	}

	public String getPartsTypeName() {
		return partsTypeName;
	}

	public void setPartsTypeName(String partsTypeName) {
		this.partsTypeName = partsTypeName;
	}

	public String getProduceTypeName() {
		return produceTypeName;
	}

	public void setProduceTypeName(String produceTypeName) {
		this.produceTypeName = produceTypeName;
	}

	public String getPartsName() {
		String partsName=this.partsTypeName+"-"+this.produceTypeName;
		return partsName;
	}

	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
}
