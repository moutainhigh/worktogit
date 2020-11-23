package com.lwxf.industry4.webapp.domain.dto.customorder;

import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.entity.customer.CompanyCustomer;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;
import com.lwxf.industry4.webapp.domain.entity.customorder.OfferItem;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderOffer;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/15/015 15:32
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "订单详细信息",discriminator = "订单详细信息")
public class CustomOrderInfoDto {
	@ApiModelProperty(value = "订单数据",required = true)
	private CustomOrderDto customOrder;
	@ApiModelProperty(value = "订单需求")
	private List<CustomOrderDemandDto> orderDemand;
	@ApiModelProperty(value = "订单设计")
	private List<CustomOrderDesignDto> orderDesign;
	@ApiModelProperty(value = "订单合同附件")
	private List<CustomOrderFiles> orderContractFiles;
	@ApiModelProperty(value = "订单合同附件ids")
	private String orderFiles;
	@ApiModelProperty(value = "订单报价修改记录")
	private List<OrderAccountLogDto> orderAccountLogList;
	@ApiModelProperty(value = "订单产品")
	private List<OrderProductDto> orderProducts;
	@ApiModelProperty(value = "生产拆单")
	private List<ProduceOrderDto> produceOrderDtos;
	@ApiModelProperty(value = "订单报价")
	private OrderOffer orderOffer;
	@ApiModelProperty(value = "报价条目")
	private List<OfferItem> offerItems;
	@ApiModelProperty(value = "客户信息")
	private CompanyCustomer companyCustomer;
	@ApiModelProperty(value = "发货信息")
	private List<DispatchBillDto> dispatchBill;
	@ApiModelProperty(value = "财务信息")
	private PaymentDto payment;
	@ApiModelProperty(value = "发货信息(只显示订单下产品最新的物流信息)")
	private List<MapContext> dispatchBillMap;
	@ApiModelProperty(value = "催款客服id")
	private String remindUserId;
	@ApiModelProperty(value = "催款客服名称")
	private String remindUserName;
	@ApiModelProperty(value = "追加单信息")
	private List<CustomOrderDto> appendCustomOrder;


    public List<DispatchBillDto> getDispatchBill() {return dispatchBill;}

    public void setDispatchBill(List<DispatchBillDto> dispatchBill) {this.dispatchBill = dispatchBill;}

    public CustomOrderDto getCustomOrder() {
		return customOrder;
	}

	public void setCustomOrder(CustomOrderDto customOrder) {
		this.customOrder = customOrder;
	}

	public List<CustomOrderDemandDto> getOrderDemand() {
		return orderDemand;
	}

	public void setOrderDemand(List<CustomOrderDemandDto> orderDemand) {
		this.orderDemand = orderDemand;
	}

	public List<CustomOrderDesignDto> getOrderDesign() {
		return orderDesign;
	}

	public void setOrderDesign(List<CustomOrderDesignDto> orderDesign) {
		this.orderDesign = orderDesign;
	}

	public List<CustomOrderFiles> getOrderContractFiles() {
		return orderContractFiles;
	}

	public void setOrderContractFiles(List<CustomOrderFiles> orderContractFiles) {
		this.orderContractFiles = orderContractFiles;
	}

	public List<OrderAccountLogDto> getOrderAccountLogList() {
		return orderAccountLogList;
	}

	public void setOrderAccountLogList(List<OrderAccountLogDto> orderAccountLogList) {
		this.orderAccountLogList = orderAccountLogList;
	}

	public List<OrderProductDto> getOrderProducts() {
		return orderProducts;
	}

	public void setOrderProducts(List<OrderProductDto> orderProducts) {
		this.orderProducts = orderProducts;
	}

	public List<ProduceOrderDto> getProduceOrderDtos() {
		return produceOrderDtos;
	}

	public void setProduceOrderDtos(List<ProduceOrderDto> produceOrderDtos) {
		this.produceOrderDtos = produceOrderDtos;
	}

	public OrderOffer getOrderOffer() {
		return orderOffer;
	}

	public void setOrderOffer(OrderOffer orderOffer) {
		this.orderOffer = orderOffer;
	}

	public List<OfferItem> getOfferItems() {
		return offerItems;
	}

	public void setOfferItems(List<OfferItem> offerItems) {
		this.offerItems = offerItems;
	}

	public CompanyCustomer getCompanyCustomer() {
		return companyCustomer;
	}

	public void setCompanyCustomer(CompanyCustomer companyCustomer) {
		this.companyCustomer = companyCustomer;
	}

	public String getOrderFiles() {
		return orderFiles;
	}

	public void setOrderFiles(String orderFiles) {
		this.orderFiles = orderFiles;
	}

	public PaymentDto getPayment() {
		return payment;
	}

	public void setPayment(PaymentDto payment) {
		this.payment = payment;
	}

	public List<MapContext> getDispatchBillMap() {
		return dispatchBillMap;
	}

	public void setDispatchBillMap(List<MapContext> dispatchBillMap) {
		this.dispatchBillMap = dispatchBillMap;
	}

	public String getRemindUserId() {
		return remindUserId;
	}

	public void setRemindUserId(String remindUserId) {
		this.remindUserId = remindUserId;
	}

	public String getRemindUserName() {
		return remindUserName;
	}

	public void setRemindUserName(String remindUserName) {
		this.remindUserName = remindUserName;
	}

	public List<CustomOrderDto> getAppendCustomOrder() {
		return appendCustomOrder;
	}

	public void setAppendCustomOrder(List<CustomOrderDto> appendCustomOrder) {
		this.appendCustomOrder = appendCustomOrder;
	}
}
