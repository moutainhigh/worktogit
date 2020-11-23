package com.lwxf.industry4.webapp.domain.dto.customorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/15 0015 17:50
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

@ApiModel(value = "微信小程序F端 订单跟踪实体",description = "微信小程序B端-订单跟踪实体")
public class WxOrderTrace {


	@ApiModelProperty(value = "订单id ")
	private String orderId;
	@ApiModelProperty(value = "订单流程时间 ")
	private Date TraceTime;
	@ApiModelProperty(value = "订单流程状态")
	private String TraceName;
	@ApiModelProperty(value = "订单流程描述")
	private String TraceInfo;
	@ApiModelProperty(value = "订单流程人姓名")
	private String TracePeopleName;
	@ApiModelProperty(value = "订单人角色名称")
	private String TracePeopleInfo;
	//预计交货时间
	@ApiModelProperty(value = "预计交货时间")
	private Date estimatedDeliveryDate;
	@ApiModelProperty(value = "订单支付时间")
	private Date orderPayTime;
	@ApiModelProperty(value = "物流信息（物流公司，物流单号）")
	private List<MapContext> logisticsInfo;


	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getOrderId() {
		return orderId;
	}

	public void setTraceName(String traceName) {
		TraceName = traceName;
	}

	public void setTraceInfo(String traceInfo) {
		TraceInfo = traceInfo;
	}

	public void setTracePeopleName(String tracePeopleName) {
		TracePeopleName = tracePeopleName;
	}

	public void setTracePeopleInfo(String tracePeopleInfo) {
		TracePeopleInfo = tracePeopleInfo;
	}

	public String getTraceName() {
		return TraceName;
	}

	public String getTraceInfo() {
		return TraceInfo;
	}

	public String getTracePeopleName() {
		return TracePeopleName;
	}

	public String getTracePeopleInfo() {
		return TracePeopleInfo;
	}

	public Date getTraceTime() {
		return TraceTime;
	}

	public void setTraceTime(Date traceTime) {
		TraceTime = traceTime;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public Date getOrderPayTime() {
		return orderPayTime;
	}

	public void setOrderPayTime(Date orderPayTime) {
		this.orderPayTime = orderPayTime;
	}

	public List<MapContext> getLogisticsInfo() {
		return logisticsInfo;
	}

	public void setLogisticsInfo(List<MapContext> logisticsInfo) {
		this.logisticsInfo = logisticsInfo;
	}
}
