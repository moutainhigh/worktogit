package com.lwxf.industry4.webapp.domain.dto.printTable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProduct;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceOrder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/16/016 16:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "生产拆单信息更多",discriminator = "生产拆单信息更多")
public class CoordinationPrintTableDto extends ProduceOrder {
	@ApiModelProperty(value = "经销商名称")
	private String dealerName;
	@ApiModelProperty(value = "负责人名称")
	private String leaderName;
	@ApiModelProperty(value = "联系人电话")
	private String leaderTel;
	@ApiModelProperty(value = "客户名称")
	private String customerName;
	@ApiModelProperty(value = "小区")
	private String community;
	@ApiModelProperty(value = "客户电话")
	private String customerTel;
	@ApiModelProperty(value = "订单产品信息")
	private OrderProduct orderProduct;
//	@ApiModelProperty(value = "支付时间")
//	private String payTime;
//	@ApiModelProperty(value = "审核人")
//	private String auditorName;
//	@ApiModelProperty(value = "收支单号")
//	private String paymentNo;
//
//	public String getDealerName() {
//		return dealerName;
//	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getLeaderTel() {
		return leaderTel;
	}

	public void setLeaderTel(String leaderTel) {
		this.leaderTel = leaderTel;
	}

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

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public OrderProduct getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(OrderProduct orderProduct) {
		this.orderProduct = orderProduct;
	}

//	public String getPayTime() {
//		return payTime;
//	}
//
//	public void setPayTime(String payTime) {
//		this.payTime = payTime;
//	}
//
//	public String getAuditorName() {
//		return auditorName;
//	}
//
//	public void setAuditorName(String auditorName) {
//		this.auditorName = auditorName;
//	}
//
//	public String getPaymentNo() {
//		return paymentNo;
//	}
//
//	public void setPaymentNo(String paymentNo) {
//		this.paymentNo = paymentNo;
//	}
}
