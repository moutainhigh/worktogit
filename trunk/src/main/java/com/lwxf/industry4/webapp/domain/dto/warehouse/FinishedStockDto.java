package com.lwxf.industry4.webapp.domain.dto.warehouse;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockItemType;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/24/024 14:51
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "成品库单信息",discriminator = "成品库单信息")
public class FinishedStockDto extends FinishedStock {
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;
	private String estimatedDeliveryDate;
	@ApiModelProperty(value = "包裹信息")
	private List<FinishedStockItemDto> finishedStockItemDtos;
	@ApiModelProperty(value = "产品类型",hidden = true)
	private Integer productType;
	@ApiModelProperty(value = "包裹数量",hidden = true)
	private Integer finishedItemNum;



	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public List<FinishedStockItemDto> getFinishedStockItemDtos() {
		return finishedStockItemDtos;
	}

	public void setFinishedStockItemDtos(List<FinishedStockItemDto> finishedStockItemDtos) {
		this.finishedStockItemDtos = finishedStockItemDtos;
	}

	public String getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(String estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getFinishedItemNum() {
		return finishedItemNum;
	}

	public void setFinishedItemNum(Integer finishedItemNum) {
		this.finishedItemNum = finishedItemNum;
	}

}
