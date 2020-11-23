package com.lwxf.industry4.webapp.domain.dto.customer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/1/001 15:41
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "客户信息",description = "客户信息")
public class CustomerDtoV2 extends CompanyCustomer {
	@ApiModelProperty(value = "城市名称")
	public String cityAreaName;//城市名称
	@ApiModelProperty(value = "客户经理名称")
	private String customerManagerName;//客户经理名称
	@ApiModelProperty(value = "公司名称")
	private String companyName;//公司名称
	@ApiModelProperty(value = "公司负责人电话")
	private String leaderTel;//公司负责人电话
	@ApiModelProperty(value = "省ID")
	private String provinceId;
	@ApiModelProperty(value = "市ID")
	private String cityId;

	public String getCityAreaName() {
		return cityAreaName;
	}

	public void setCityAreaName(String cityAreaName) {
		this.cityAreaName = cityAreaName;
	}

	public String getCustomerManagerName() {
		return customerManagerName;
	}

	public void setCustomerManagerName(String customerManagerName) {
		this.customerManagerName = customerManagerName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLeaderTel() {
		return leaderTel;
	}

	public void setLeaderTel(String leaderTel) {
		this.leaderTel = leaderTel;
	}

	public String getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(String provinceId) {
		this.provinceId = provinceId;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}
}
