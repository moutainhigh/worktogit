package com.lwxf.industry4.webapp.domain.dto.printTable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.lwxf.industry4.webapp.domain.dto.customorder.*;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/7/11 0011 9:49
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="pc端后台订单打印信息",description="pc端后台订单打印信息")
public class OrderPrintTableDto {
	@ApiModelProperty(value="订单品类",name="orderProductTypeName",example = "柜体、全屋")
	private String orderProductTypeName;
	@ApiModelProperty(value="是否需要设计",name="isDesign",example = "",hidden = true)
	private Integer isDesign;
	@ApiModelProperty(value="订单类型",name="orderTypeName",example = "正常、散单")
	private String orderTypeName;
	@ApiModelProperty(value="订单编号",name="orderNo")
	private String orderNo;
	@ApiModelProperty(value="接单日期",name="orderTakeTime")
	private Date orderTakeTime;//接单日期
	@ApiModelProperty(value="接单人",name="orderTaker")
	private String orderTaker;//接单人
	@ApiModelProperty(value="审核时间",name="checkTime")
	private Date checkTime;//审核时间
	@ApiModelProperty(value="审核人",name="checker")
	private String checker;//审核人
	@ApiModelProperty(value="经销商",name="dealerCompanyName")
	private String dealerCompanyName;//经销商
	@ApiModelProperty(value="区域地址",name="cityArea")
	private String cityArea;//区域地址
	@ApiModelProperty(value="负责人",name="founderName")
	private String founderName;//负责人
	@ApiModelProperty(value="负责人电话",name="leaderTel")
	private String leaderTel;//负责人电话
	@ApiModelProperty(value="接单日期",name="orderTakeTime")
	private String consigneeName;//收货人
	@ApiModelProperty(value="收货人电话",name="consigneeTel")
	private String consigneeTel;//收货人电话
	@ApiModelProperty(value="收货地址",name="address")
	private String address;//收货地址
	@ApiModelProperty(value="指定物流",name="logisticsCompany")
	private String logisticsCompany;//指定物流
	@ApiModelProperty(value="终端客户姓名",name="customerName")
	private String customerName;//终端客户姓名
	@ApiModelProperty(value="客户电话",name="customerTel")
	private String customerTel;//客户电话
	@ApiModelProperty(value="小区名称",name="community")
	private String community;//小区名称
	@ApiModelProperty(value="安装地址",name="constructionAddress")
	private String constructionAddress;//安装地址
	@ApiModelProperty(value="计划交期",name="estimatedDeliveryDate")
	private Date estimatedDeliveryDate;//计划交期
	@ApiModelProperty(value="延期时间",name="delayTime")
	private Long delayTime;//延期时间
	@ApiModelProperty(value="实际交货时间",name="deliveryDate",hidden = true)
	private Date deliveryDate;//实际交货时间
	@ApiModelProperty(value="支付记录信息",name="paymentDtos")
	private List<PaymentDto> paymentDtos;//支付记录信息
	@ApiModelProperty(value="产品分类信息",name="orderProductDtos")
	private List<OrderProductDto> orderProductDtos;//产品分类信息
	@ApiModelProperty(value="产品生产信息",name="produceOrderDto")
	private ProduceOrderDto produceOrderDto;//产品生产信息
	@ApiModelProperty(value="产品设计信息",name="customOrderDesignDto")
	private CustomOrderDesignDto customOrderDesignDto;//产品设计信息
	@ApiModelProperty(value="产品需求信息",name="customOrderDemandDto")
	private CustomOrderDemandDto customOrderDemandDto;//产品需求信息
	@ApiModelProperty(value="生产流程信息",name="produceFlowDtos")
	private List<ProduceFlowDto> produceFlowDtos;//生产流程信息

	public String getOrderProductTypeName() {
		return orderProductTypeName;
	}

	public void setOrderProductTypeName(String orderProductTypeName) {
		this.orderProductTypeName = orderProductTypeName;
	}

	public String getOrderTypeName() {
		String nameValue="";
		if(this.isDesign==0){
           nameValue="正常";
		}else {
			nameValue="散单";
		}
		return nameValue;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Date getOrderTakeTime() {
		return orderTakeTime;
	}

	public void setOrderTakeTime(Date orderTakeTime) {
		this.orderTakeTime = orderTakeTime;
	}

	public String getOrderTaker() {
		return orderTaker;
	}

	public void setOrderTaker(String orderTaker) {
		this.orderTaker = orderTaker;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getDealerCompanyName() {
		return dealerCompanyName;
	}

	public void setDealerCompanyName(String dealerCompanyName) {
		this.dealerCompanyName = dealerCompanyName;
	}

	public String getCityArea() {
		return cityArea;
	}

	public void setCityArea(String cityArea) {
		this.cityArea = cityArea;
	}

	public String getFounderName() {
		return founderName;
	}

	public void setFounderName(String founderName) {
		this.founderName = founderName;
	}

	public String getLeaderTel() {
		return leaderTel;
	}

	public void setLeaderTel(String leaderTel) {
		this.leaderTel = leaderTel;
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

	public String getLogisticsCompany() {
		return logisticsCompany;
	}

	public void setLogisticsCompany(String logisticsCompany) {
		this.logisticsCompany = logisticsCompany;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerTel() {
		return customerTel;
	}

	public void setCustomerTel(String customerTel) {
		this.customerTel = customerTel;
	}

	public String getCommunity() {
		return community;
	}

	public void setCommunity(String community) {
		this.community = community;
	}

	public String getConstructionAddress() {
		return constructionAddress;
	}

	public void setConstructionAddress(String constructionAddress) {
		this.constructionAddress = constructionAddress;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public Long getDelayTime() {
		if(this.deliveryDate!=null){
			Long delayTimeValue=(this.estimatedDeliveryDate.getTime()-this.deliveryDate.getTime())/(3600*24);
			return delayTimeValue;
		}
		return delayTime;
	}

	public void setDelayTime(Long delayTime) {
		this.delayTime = delayTime;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public List<PaymentDto> getPaymentDtos() {
		return paymentDtos;
	}

	public void setPaymentDtos(List<PaymentDto> paymentDtos) {
		this.paymentDtos = paymentDtos;
	}

	public List<OrderProductDto> getOrderProductDtos() {
		return orderProductDtos;
	}

	public void setOrderProductDtos(List<OrderProductDto> orderProductDtos) {
		this.orderProductDtos = orderProductDtos;
	}

	public ProduceOrderDto getProduceOrderDto() {
		return produceOrderDto;
	}

	public void setProduceOrderDto(ProduceOrderDto produceOrderDto) {
		this.produceOrderDto = produceOrderDto;
	}

	public CustomOrderDesignDto getCustomOrderDesignDto() {
		return customOrderDesignDto;
	}

	public void setCustomOrderDesignDto(CustomOrderDesignDto customOrderDesignDto) {
		this.customOrderDesignDto = customOrderDesignDto;
	}

	public CustomOrderDemandDto getCustomOrderDemandDto() {
		return customOrderDemandDto;
	}

	public void setCustomOrderDemandDto(CustomOrderDemandDto customOrderDemandDto) {
		this.customOrderDemandDto = customOrderDemandDto;
	}

	public List<ProduceFlowDto> getProduceFlowDtos() {
		return produceFlowDtos;
	}

	public void setProduceFlowDtos(List<ProduceFlowDto> produceFlowDtos) {
		this.produceFlowDtos = produceFlowDtos;
	}

	public Integer getIsDesign() {
		return isDesign;
	}

	public void setIsDesign(Integer isDesign) {
		this.isDesign = isDesign;
	}
}
