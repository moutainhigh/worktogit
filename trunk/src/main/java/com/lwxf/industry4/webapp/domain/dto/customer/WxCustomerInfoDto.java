package com.lwxf.industry4.webapp.domain.dto.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/12 0012 17:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "F端微信小程序 客户详情",description = "F端微信小程序 客户详情")
public class WxCustomerInfoDto {
	@ApiModelProperty(value = "类型")
	private String orderProductType;
	@ApiModelProperty(value = "货款")
	private BigDecimal orderAmount;
	@ApiModelProperty(value = "订单id")
	private String orderId;
	@ApiModelProperty(value = "姓名")
	private String customerName;
	@ApiModelProperty(value = "电话")
	private String customerMobile;
	@ApiModelProperty(value = "性别")
	private Integer sex;
	@ApiModelProperty(value = "地区")
	private String customerMergerName;
	@ApiModelProperty(value = "地址")
	private String customerAddress;
	@ApiModelProperty(value = "小区")
	private String community;
	@ApiModelProperty(value = "备注")
	private String remarks;
	@ApiModelProperty(value = "经销商")
	private String companyName;
	@ApiModelProperty(value = "业务经理")
	private String saleMan;
	@ApiModelProperty(value = "业务经理电话")
	private String saleManMobile;
	@ApiModelProperty(value = "客户用户表id")
	private String userId;
	@ApiModelProperty(value = "地区id")
	private String cityId;
	@ApiModelProperty(value = "经销商级别")
	private String gradeName;

	public String getOrderProductType() {
		return orderProductType;
	}

	public void setOrderProductType(String orderProductType) {
		this.orderProductType = orderProductType;
	}

	public BigDecimal getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(BigDecimal orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getCustomerMergerName() {
		return customerMergerName;
	}

	public void setCustomerMergerName(String customerMergerName) {
		this.customerMergerName = customerMergerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getSaleMan() {
		return saleMan;
	}

	public void setSaleMan(String saleMan) {
		this.saleMan = saleMan;
	}

	public String getSaleManMobile() {
		return saleManMobile;
	}

	public void setSaleManMobile(String saleManMobile) {
		this.saleManMobile = saleManMobile;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
}
