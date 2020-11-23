package com.lwxf.industry4.webapp.domain.dto.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/12 0012 11:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "F端微信小程序客户信息",description = "F端微信小程序客户信息")
public class WxCustomerDto {
	@ApiModelProperty(value = "客户姓名")
	private String customerName;
	@ApiModelProperty(value = "客户手机号")
	private String customerMobile;
	@ApiModelProperty(value = "经销商公司名称")
	private String companyName;
	@ApiModelProperty(value = "客户所在区域名称")
	private String customerMergerName;
	@ApiModelProperty(value = "经销商公司类型")
	private String companyType;
	@ApiModelProperty(value = "客户Id")
	private String customerId;
	@ApiModelProperty(value = "客户小区名称")
	private String community;
	@ApiModelProperty(value = "地区Id")
	private String cityAreaId;
	@ApiModelProperty(value = "客户详细地址")
	private String address;
	@ApiModelProperty(value = "客户性别名字")
	private String sexName;
	@ApiModelProperty(value = "客户性别")
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getCityAreaId() {
		return cityAreaId;
	}

	public void setCityAreaId(String cityAreaId) {
		this.cityAreaId = cityAreaId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyType() {
		return companyType;
	}

	public void setCompanyType(String companyType) {
		this.companyType = companyType;
	}

	public String getCustomerMergerName() {
		return customerMergerName;
	}

	public void setCustomerMergerName(String customerMergerName) {
		this.customerMergerName = customerMergerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}
}
