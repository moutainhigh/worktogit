package com.lwxf.industry4.webapp.domain.dto.product;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.product.Product;
import com.lwxf.industry4.webapp.domain.entity.product.ProductFiles;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/4/004 15:38
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "产品信息",description = "产品信息")
public class ProductDto extends Product {
	@ApiModelProperty(value = "分类名称")
	private String categoryName;
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;
	@ApiModelProperty(value = "wx封面")
	private String wxCover;
	@ApiModelProperty(value = "微信图集")
	private List<ProductFiles> productFiles;
	@ApiModelProperty(value = "微信报价图")
	private ProductFiles wxOfferFiles;
	@ApiModelProperty(value = "上下架转义")
	private String lowerShelfName;
	@ApiModelProperty(value = "状态转义")
	private String statusName;



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

	public String getWxCover() {
		return wxCover;
	}

	public void setWxCover(String wxCover) {
		this.wxCover = wxCover;
	}

	public List<ProductFiles> getProductFiles() {
		return productFiles;
	}

	public void setProductFiles(List<ProductFiles> productFiles) {
		this.productFiles = productFiles;
	}

	public ProductFiles getWxOfferFiles() {
		return wxOfferFiles;
	}

	public void setWxOfferFiles(ProductFiles wxOfferFiles) {
		this.wxOfferFiles = wxOfferFiles;
	}

	public String getLowerShelfName() {
		return lowerShelfName;
	}

	public void setLowerShelfName(String lowerShelfName) {
		this.lowerShelfName = lowerShelfName;
	}

	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
}
