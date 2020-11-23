package com.lwxf.industry4.webapp.domain.dto.customorder;

import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.mybatis.annotation.Column;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.annotation.Resource;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/15 0015 17:50
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

@ApiModel(value = "微信小程序F端 订单详情输出模型",description = "微信小程序F端 订单详情输出模型")
public class WxCustomerOrderInfoDto {
	@ApiModelProperty(value = "经销商",name="dealerName")
	private String dealerName;
	@ApiModelProperty(value = "客户",name="customerName")
	private String customerName;
	@ApiModelProperty(value = "客户电话",name="customerMobile")
	private String customerMobile;
	@ApiModelProperty(value = "客户地址",name="customerAddress")
	private String customerAddress;
	@ApiModelProperty(value = "经销商电话",name="leaderMobile")
	private String leaderMobile;
	@ApiModelProperty(value = "创建人",name="creatorName")
	private String creatorName;//创建人
	@ApiModelProperty(value = "跟单员",name="merchandiserName")
	private String merchandiserName;//跟单员
	@ApiModelProperty(value = "跟单备注",name="documentaryNotes")
	private String documentaryNotes;
	@ApiModelProperty(value = "订单编号",name="orderNo")
	private String orderNo;
	@ApiModelProperty(value = "订单ID",name="orderNo")
	private String orderId;
	@ApiModelProperty(value = "是否需要设计",name="isDesign")
	private Integer isDesign;//是否需要设计
	@ApiModelProperty(value = "订单类型：售后、正常",name="orderType")
	private String orderType;//正常订单 售后订单
	@ApiModelProperty(value = "订单创建时间",name="orderCreated")
	private Date orderCreated;
	@ApiModelProperty(value = "计划交期",name="estimatedDeliveryDate")
	private Date estimatedDeliveryDate;//计划交期
	@ApiModelProperty(value = "实际交期",name="deliveryDate")
	private Date deliveryDate;//实际交期
	@ApiModelProperty(value = "已耗时",name="timeConsuming",hidden = true)
	private String timeConsuming;//已耗时
	@ApiModelProperty(value = "下车间时间",name="documentaryTime",hidden = true)
	private Date documentaryTime;//下车间时间
	@ApiModelProperty(value = "剩余时间",name="timeRemaining")
	private String timeRemaining;//剩余时间
	@ApiModelProperty(value = "剩余时间状态",name="timeRemainingStatus")
	private Integer timeRemainingStatus;//剩余时间状态
	@ApiModelProperty(value = "实际货款",name="factoryFinalPrice")
	private BigDecimal factoryFinalPrice;//实际货款
	@ApiModelProperty(value = "实付金额",name="payAmount")
	private BigDecimal payAmount;//实付金额
	@ApiModelProperty(value = "出厂价",name="factoryPrice")
	private BigDecimal factoryPrice;//出厂价
	@ApiModelProperty(value = "市场价",name="marketPrice")
	private BigDecimal marketPrice;//市场价
	@ApiModelProperty(value = "折扣价",name="factoryDiscounts")
	private BigDecimal factoryDiscounts;//折扣价
	@ApiModelProperty(value = "订单优惠说明",name="discountsNote")
	private String discountsNote;//订单优惠说明
	@ApiModelProperty(value = "订单状态",name="orderStatus")
	private Integer orderStatus;//订单状态
	@ApiModelProperty(value = "订单状态名字",name="orderStatus")
	private String orderStatusName;
	@ApiModelProperty(value = "订单财务到账状态",name="financeStatus")
	private String financeStatus;//订单财务到账状态
	@ApiModelProperty(value = "到账时间",name="audited")
	private Date audited;//到账时间（支付审核时间）
	@ApiModelProperty(value = "经手人/审核人",name="auditorName")
	private String auditorName;//经手人（审核人）
	@ApiModelProperty(value = "接单人",name="receiverName")
	private String receiverName;//接单人
	@ApiModelProperty(value = "下单人",name="placeOrderName")
	private String placeOrderName;//下单人
	@ApiModelProperty(value = "下单产品信息",name="orderProduct")
	private List<OrderProductDto> orderProduct;
	@ApiModelProperty(value = "设计",name="orderDesign")
	private List<CustomOrderDesignDto> orderDesign;
	@ApiModelProperty(value = "生产",name="orderProduce")
	private List orderProduce;
	@ApiModelProperty(value = "入库",name="orderFinishStock")
    private List orderFinishStock;
	@ApiModelProperty(value = "发货",name="orderDispatchBill")
    private List orderDispatchBill;
	@ApiModelProperty(value = "生产单信息",name="orderProduce")
	private List<ProduceOrderDto> producesList;
	@ApiModelProperty(value = "是否外协 0-不需要 1-需要",name="isCoordination")
	private Integer isCoordination;
	@ApiModelProperty(value = "收货地址",name="address")
	private String address;
	@ApiModelProperty(value = "所在地区id",name="branchId")
	private String branchId;
	@ApiModelProperty(value = "收货人",name="receiver")
	private String receiver;
	@ApiModelProperty(value = "下单人(建单人)",name="placeOrder")
	private String placeOrder;
	@ApiModelProperty(value = "收货人姓名",name="consigneeName")
	private String consigneeName;
	@ApiModelProperty(value = "收货人电话",name="consigneeTel")
	private String consigneeTel;
	private Integer type;
	@ApiModelProperty(value = "订单类型",name="consigneeTel")
	private String orderProductType;
	@ApiModelProperty(value = "标签",name="consigneeTel")
	private String notes;
	@ApiModelProperty(value = "标签",name="UploadFiles")
	private List<UploadFiles> UploadFiles;
	@Column(type = Types.CHAR, length = 13, name = "city_area_id", displayName = "客户所在地区id")
	@ApiModelProperty(value = "客户所在地区id")
	private String cityAreaId;
	@ApiModelProperty(value = "客户所在地区id")
	private String cityAreaName;
	@ApiModelProperty(value = "订单提交时间")
	private Date commitTime;

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

	public String getCityAreaName() {
		return cityAreaName;
	}

	public void setCityAreaName(String cityAreaName) {
		this.cityAreaName = cityAreaName;
	}

	public String getCityAreaId() {
		return cityAreaId;
	}

	public void setCityAreaId(String cityAreaId) {
		this.cityAreaId = cityAreaId;
	}

	public List<com.lwxf.industry4.webapp.domain.entity.common.UploadFiles> getUploadFiles() {
		return UploadFiles;
	}

	public void setUploadFiles(List<com.lwxf.industry4.webapp.domain.entity.common.UploadFiles> uploadFiles) {
		UploadFiles = uploadFiles;
	}

	public String getOrderProductType() {
		return orderProductType;
	}

	public void setOrderProductType(String orderProductType) {
		this.orderProductType = orderProductType;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getPlaceOrder() {
		return placeOrder;
	}

	public void setPlaceOrder(String placeOrder) {
		this.placeOrder = placeOrder;
	}




	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLeaderMobile() {
		return leaderMobile;
	}

	public void setLeaderMobile(String leaderMobile) {
		this.leaderMobile = leaderMobile;
	}

	public String getMerchandiserName() {
		return merchandiserName;
	}

	public void setMerchandiserName(String merchandiserName) {
		this.merchandiserName = merchandiserName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getIsDesign() {
		return isDesign;
	}

	public void setIsDesign(Integer isDesign) {
		this.isDesign = isDesign;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}



	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
	}

	public String getTimeRemaining() {
		return timeRemaining;
	}

	public void setTimeRemaining(String timeRemaining) {
		this.timeRemaining = timeRemaining;
	}

	public BigDecimal getFactoryFinalPrice() {
		return factoryFinalPrice;
	}

	public void setFactoryFinalPrice(BigDecimal factoryFinalPrice) {
		this.factoryFinalPrice = factoryFinalPrice;
	}

	public BigDecimal getFactoryPrice() {
		return factoryPrice;
	}

	public void setFactoryPrice(BigDecimal factoryPrice) {
		this.factoryPrice = factoryPrice;
	}

	public String getDiscountsNote() {
		return discountsNote;
	}

	public void setDiscountsNote(String discountsNote) {
		this.discountsNote = discountsNote;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getFinanceStatus() {
		return financeStatus;
	}

	public void setFinanceStatus(String financeStatus) {
		this.financeStatus = financeStatus;
	}

	public Date getAudited() {
		return audited;
	}

	public void setAudited(Date audited) {
		this.audited = audited;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public List<OrderProductDto> getOrderProduct() {
		return orderProduct;
	}

	public void setOrderProduct(List<OrderProductDto> orderProduct) {
		this.orderProduct = orderProduct;
	}


	public List getOrderProduce() {
		return orderProduce;
	}

	public void setOrderProduce(List orderProduce) {
		this.orderProduce = orderProduce;
	}

	public List getOrderFinishStock() {
		return orderFinishStock;
	}

	public void setOrderFinishStock(List orderFinishStock) {
		this.orderFinishStock = orderFinishStock;
	}

	public List getOrderDispatchBill() {
		return orderDispatchBill;
	}

	public void setOrderDispatchBill(List orderDispatchBill) {
		this.orderDispatchBill = orderDispatchBill;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getTimeConsuming() {
		return timeConsuming;
	}

	public void setTimeConsuming(String timeConsuming) {
		this.timeConsuming = timeConsuming;
	}

	public Date getDocumentaryTime() {
		return documentaryTime;
	}

	public void setDocumentaryTime(Date documentaryTime) {
		this.documentaryTime = documentaryTime;
	}

	public Integer getTimeRemainingStatus() {
		return timeRemainingStatus;
	}

	public void setTimeRemainingStatus(Integer timeRemainingStatus) {
		this.timeRemainingStatus = timeRemainingStatus;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getDocumentaryNotes() {
		return documentaryNotes;
	}

	public void setDocumentaryNotes(String documentaryNotes) {
		this.documentaryNotes = documentaryNotes;
	}

	public List<CustomOrderDesignDto> getOrderDesign() {
		return orderDesign;
	}

	public void setOrderDesign(List<CustomOrderDesignDto> orderDesign) {
		this.orderDesign = orderDesign;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public List<ProduceOrderDto> getProducesList() {
		return producesList;
	}

	public void setProducesList(List<ProduceOrderDto> producesList) {
		this.producesList = producesList;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getPlaceOrderName() {
		return placeOrderName;
	}

	public void setPlaceOrderName(String placeOrderName) {
		this.placeOrderName = placeOrderName;
	}

	public String getOrderStatusName() {
		return orderStatusName;
	}

	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
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

	public BigDecimal getMarketPrice() {
		return marketPrice;
	}

	public void setMarketPrice(BigDecimal marketPrice) {
		this.marketPrice = marketPrice;
	}

	public BigDecimal getFactoryDiscounts() {
		return factoryDiscounts;
	}

	public void setFactoryDiscounts(BigDecimal factoryDiscounts) {
		this.factoryDiscounts = factoryDiscounts;
	}

	public Integer getIsCoordination() {
		return isCoordination;
	}

	public void setIsCoordination(Integer isCoordination) {
		this.isCoordination = isCoordination;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}
}
