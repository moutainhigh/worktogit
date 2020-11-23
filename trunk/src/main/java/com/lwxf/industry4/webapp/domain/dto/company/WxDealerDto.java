package com.lwxf.industry4.webapp.domain.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.company.Company;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/14/014 10:30
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="微信经销商公司详情",description="微信经销商公司详情")
public class WxDealerDto extends Company {
	@ApiModelProperty(value = "头像")
	private String avatar;
	@ApiModelProperty(value = "用户名称")
	private String dealerName;
	@ApiModelProperty(value = "经销商地址")
	private String cityName;
	@ApiModelProperty(value = "负责人姓名")
	private String leaderName;
	@ApiModelProperty(value = "经销商省市县地址")
	private String dealerMergerName;
	@ApiModelProperty(value = "经销商类型")
	private String dealerTypeName;

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getDealerMergerName() {
		return dealerMergerName;
	}

	public void setDealerMergerName(String dealerMergerName) {
		this.dealerMergerName = dealerMergerName;
	}

	public String getDealerTypeName() {
		return dealerTypeName;
	}

	public void setDealerTypeName(String dealerTypeName) {
		this.dealerTypeName = dealerTypeName;
	}
}
