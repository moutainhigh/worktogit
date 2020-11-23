package com.lwxf.industry4.webapp.domain.dto.printTable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.lwxf.industry4.webapp.domain.dto.customorder.OrderOfferDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.OfferItem;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderOffer;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/18/018 17:21
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "订单信息", discriminator = "订单信息")
public class OfferPrintTableDto extends CustomOrder {

	@ApiModelProperty(value = "客户名称")
	private String customerName;  //客户名称
	@ApiModelProperty(value = "客户小区")
	private String community;  //客户小区
	@ApiModelProperty(value = "客户电话")
	private String phone;//客户电话
	@ApiModelProperty(value = "经销商名称")
	private String dealerName;//经销商名称
	@ApiModelProperty(value = "经销商电话")
	private String dealerTel;//经销商电话
	@ApiModelProperty(value = "负责人")
	private String leaderName;
	@ApiModelProperty(value = "订单报价")
	private OrderOffer orderOffer;
	@ApiModelProperty(value = "报价条目")
	private List<OfferItem> offerItemList;
	@ApiModelProperty(value = "接单员名称")
	private String receiverName;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerTel() {
		return dealerTel;
	}

	public void setDealerTel(String dealerTel) {
		this.dealerTel = dealerTel;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public OrderOffer getOrderOffer() {
		return orderOffer;
	}

	public void setOrderOffer(OrderOffer orderOffer) {
		this.orderOffer = orderOffer;
	}

	public List<OfferItem> getOfferItemList() {
		return offerItemList;
	}

	public void setOfferItemList(List<OfferItem> offerItemList) {
		this.offerItemList = offerItemList;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}
}
