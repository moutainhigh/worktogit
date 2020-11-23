package com.lwxf.industry4.webapp.domain.dto.customorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.customorder.OfferItem;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderOffer;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/9/009 9:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "订单报价信息",description = "订单报价信息")
public class OrderOfferDto extends OrderOffer {
	@ApiModelProperty(value = "订单报价条目集合")
	private List<OfferItem> offerItems;

	public List<OfferItem> getOfferItems() {
		return offerItems;
	}

	public void setOfferItems(List<OfferItem> offerItems) {
		this.offerItems = offerItems;
	}
}
