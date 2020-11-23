package com.lwxf.industry4.webapp.domain.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.company.CompanyType;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.utils.TypesExtend;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/13 0013 16:46
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "经销商详情",description = "经销商详情")
public class WxCompanyDto {
	@ApiModelProperty(value="经销商名称",name="name")
	protected String name;
	@ApiModelProperty(value="类型值 0：生产商/制造商/总店；1：直营店；2：店中店；3：专卖店；4：加盟店；5：散单;6:供应商;",name="type")
	protected Integer type;
	@ApiModelProperty(value = "类型名称",name = "typeName")
	private String typeName;
	@ApiModelProperty(value="所在地(最终地址ID,如某区id)",name="cityAreaId",example = "110101")
	protected String cityAreaId;
	@ApiModelProperty(value="区域地址",name="mergerName")
	protected String mergerName;
	@ApiModelProperty(value="详细地址",name="address",example = "测试地址")
	protected String address;
	@ApiModelProperty(value="经度",name="address")
	protected Float lng;
	@ApiModelProperty(value="纬度",name="address")
	protected Float lat;
	@ApiModelProperty(value="状态:0 - 意向；1 - 已签约（正常）；2 - 已退网；3 - 已倒闭；4 - 已禁用;5 - 无价值;6 - 待财务审核;7 - 待启用",name="status")
	protected Integer status;
	@ApiModelProperty(value = "状态名称",name = "statusName")
	private String statusName;
	@ApiModelProperty(value="负责人姓名",name="leaderName")
	protected String leaderName;
	@ApiModelProperty(value="负责人电话",name="leaderTel",example = "13523333333")
	protected String leaderTel;
	@ApiModelProperty(value="业务员/业务经理/大区经理",name="businessManagerName")
	protected String businessManagerName;
	@ApiModelProperty(value="公司介绍",name="companyInfo")
	protected String companyInfo;
	@ApiModelProperty(value="签约时间",name="contractTime",example = "2019-01-01")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected Date contractTime;
	@ApiModelProperty(value="合同有效期",name="contractTime",example = "2019-01-01")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	protected Date contractExpiredDate;
	@ApiModelProperty(value="订单id",name="orderId")
	private String orderId;
	@ApiModelProperty(value="订单产品类型",name="orderProdyctType")
	private String orderProdyctType;
	@ApiModelProperty(value="订单金额",name="orderAmount")
	private String orderAmount;
	@ApiModelProperty(value="名片",name="cardUrl")
	private List cardUrl;
	@ApiModelProperty(value="店铺封面",name="coverUrl")
	private String coverUrl;
	@ApiModelProperty(value="合同附件",name="companyUrl")
	private List companyUrl;
	@ApiModelProperty(value="账户余额",name="accountBalances")
	private String accountBalances;
	@ApiModelProperty(value="其他信息",name="otherFundRemarks")
	private String otherFundRemarks;
	@ApiModelProperty(value="备注信息",name="note")
	protected String note;
	@ApiModelProperty(value="经销商ID",name="dealerId")
	protected String dealerId;
	@ApiModelProperty(value = "经销商级别",name = "gradeName")
	private String gradeName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTypeName() {
		return CompanyType.getByValue(this.type).getName();
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
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

	public Float getLng() {
		return lng;
	}

	public void setLng(Float lng) {
		this.lng = lng;
	}

	public Float getLat() {
		return lat;
	}

	public void setLat(Float lat) {
		this.lat = lat;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getStatusName() {
		return CompanyStatus.getByValue(this.getStatus()).getName();
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
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

	public String getBusinessManagerName() {
		return businessManagerName;
	}

	public void setBusinessManagerName(String businessManagerName) {
		this.businessManagerName = businessManagerName;
	}

	public String getCompanyInfo() {
		return companyInfo;
	}

	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}

	public Date getContractTime() {
		return contractTime;
	}

	public void setContractTime(Date contractTime) {
		this.contractTime = contractTime;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Date getContractExpiredDate() {
		return contractExpiredDate;
	}

	public void setContractExpiredDate(Date contractExpiredDate) {
		this.contractExpiredDate = contractExpiredDate;
	}

	public String getOrderProdyctType() {
		return orderProdyctType;
	}

	public void setOrderProdyctType(String orderProdyctType) {
		this.orderProdyctType = orderProdyctType;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public List getCardUrl() {
		return cardUrl;
	}

	public void setCardUrl(List cardUrl) {
		this.cardUrl = cardUrl;
	}

	public String getCoverUrl() {
		return  coverUrl;
	}

	public void setCoverUrl(String coverUrl) {
		this.coverUrl = coverUrl;
	}

	public List getCompanyUrl() {
		return companyUrl;
	}

	public void setCompanyUrl(List companyUrl) {
		this.companyUrl = companyUrl;
	}

	public String getAccountBalances() {
		return accountBalances;
	}

	public void setAccountBalances(String accountBalances) {
		this.accountBalances = accountBalances;
	}

	public String getMergerName() {
		return this.mergerName.replaceAll(",","-");
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	public String getOtherFundRemarks() {
		return otherFundRemarks;
	}

	public void setOtherFundRemarks(String otherFundRemarks) {
		this.otherFundRemarks = otherFundRemarks;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
}
