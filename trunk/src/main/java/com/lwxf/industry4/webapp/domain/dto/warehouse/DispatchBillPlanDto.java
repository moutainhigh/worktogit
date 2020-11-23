package com.lwxf.industry4.webapp.domain.dto.warehouse;

import com.lwxf.mybatis.annotation.Column;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlan;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlanItem;

import java.util.Date;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/10/010 9:20
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "配送计划信息",discriminator = "配送计划信息")
public class DispatchBillPlanDto extends DispatchBillPlan {
	@ApiModelProperty(value = "配送计划条目信息")
	private List<DispatchBillPlanItem> dispatchBillPlanItems;
	@ApiModelProperty(value = "订单集合")
	private List<String> orderIds;

	@ApiModelProperty(value = "物流公司id")
	private String logisticsCompanyId;
	@ApiModelProperty(value = "物流单号")
	private String logisticsNo;
	@ApiModelProperty(value = "收货人")
	private String consignee;
	@ApiModelProperty(value = "收货人电话")
	private String consigneeTel;
	@ApiModelProperty(value = "经销商id")
	private String companyId;
	@ApiModelProperty(value = "收货地址")
	private String address;
	@ApiModelProperty(value = "实际发货时间")
	private Date actualDate;

	public List<DispatchBillPlanItem> getDispatchBillPlanItems() {
		return dispatchBillPlanItems;
	}

	public void setDispatchBillPlanItems(List<DispatchBillPlanItem> dispatchBillPlanItems) {
		this.dispatchBillPlanItems = dispatchBillPlanItems;
	}

	public List<String> getOrderIds() {
		return orderIds;
	}

	public void setOrderIds(List<String> orderIds) {
		this.orderIds = orderIds;
	}

	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public String getConsignee() {
		return consignee;
	}

	public void setConsignee(String consignee) {
		this.consignee = consignee;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getActualDate() {
		return actualDate;
	}

	public void setActualDate(Date actualDate) {
		this.actualDate = actualDate;
	}
}
