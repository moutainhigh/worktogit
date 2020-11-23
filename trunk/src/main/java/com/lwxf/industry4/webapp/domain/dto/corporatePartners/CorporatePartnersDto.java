package com.lwxf.industry4.webapp.domain.dto.corporatePartners;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.corporatePartners.CorporatePartners;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/8/1/001 14:32
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "外协厂家信息",description = "外协厂家信息")
public class CorporatePartnersDto extends CorporatePartners {
	@ApiModelProperty(value = "地区名称")
	private String mergerName;
	@ApiModelProperty(value = "省ID")
	private String provinceId;
	@ApiModelProperty(value = "市ID")
	private String cityId;
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;
	@ApiModelProperty(value = "创建uid")
	private String uid;
	@ApiModelProperty(value = "是否启用pc登录")
	private Integer enableLoginPc;

	public Integer getEnableLoginPc() {
		return enableLoginPc;
	}

	public void setEnableLoginPc(Integer enableLoginPc) {
		this.enableLoginPc = enableLoginPc;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
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

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
