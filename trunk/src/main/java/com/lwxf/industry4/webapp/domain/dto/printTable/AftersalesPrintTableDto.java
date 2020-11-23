package com.lwxf.industry4.webapp.domain.dto.printTable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/12/012 19:13
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="售后管理",description="售后管理")
public class AftersalesPrintTableDto extends AftersaleApply {

	@ApiModelProperty(value = "创建人名称")
	private String creatorName;
	@ApiModelProperty(value = "审核人名称")
	private String checkerName;
	@ApiModelProperty(value = "经销商公司名称")
	private String companyName;
	@ApiModelProperty(value = "经销商地址")
	private String companyAdderss;
	@ApiModelProperty(value = "经销商电话")
	private String dealerTel;
	@ApiModelProperty(value = "收货人姓名")
	private String consigneeName;
	@ApiModelProperty(value = "收货人电话")
	private String consigneeTel;
	@ApiModelProperty(value = "收货人地址")
	private String address;
	@ApiModelProperty(value = "原订单经销商")
	private String originalCompanyName;
	@ApiModelProperty(value = "原订单收货人姓名")
	private String originalConsigneeName;
	@ApiModelProperty(value = "原订单收货人电话")
	private String originalConsigneeTel;
	@ApiModelProperty(value = "原订单收货人地址")
	private String originalAddress;
	@ApiModelProperty(value = "终端客户")
	private String customerName;
	@ApiModelProperty(value = "小区")
	private String community;
	@ApiModelProperty(value = "客户电话")
	private String phone;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDealerTel() {
		return dealerTel;
	}

	public void setDealerTel(String dealerTel) {
		this.dealerTel = dealerTel;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOriginalCompanyName() {
		return originalCompanyName;
	}

	public void setOriginalCompanyName(String originalCompanyName) {
		this.originalCompanyName = originalCompanyName;
	}

	public String getOriginalConsigneeName() {
		return originalConsigneeName;
	}

	public void setOriginalConsigneeName(String originalConsigneeName) {
		this.originalConsigneeName = originalConsigneeName;
	}

	public String getOriginalConsigneeTel() {
		return originalConsigneeTel;
	}

	public void setOriginalConsigneeTel(String originalConsigneeTel) {
		this.originalConsigneeTel = originalConsigneeTel;
	}

	public String getOriginalAddress() {
		return originalAddress;
	}

	public void setOriginalAddress(String originalAddress) {
		this.originalAddress = originalAddress;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCompanyAdderss() {
		return companyAdderss;
	}

	public void setCompanyAdderss(String companyAdderss) {
		this.companyAdderss = companyAdderss;
	}
}
