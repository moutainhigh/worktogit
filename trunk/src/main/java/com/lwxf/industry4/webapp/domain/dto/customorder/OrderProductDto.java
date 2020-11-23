package com.lwxf.industry4.webapp.domain.dto.customorder;

import com.lwxf.industry4.webapp.common.enums.order.OrderProductStatus;
import com.lwxf.industry4.webapp.domain.dto.product.ProductPartsDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProduct;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductAttribute;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/12/012 15:05
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "订单产品信息DTO",discriminator = "订单产品信息DTO")
public class 	OrderProductDto extends OrderProduct {
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;
	@ApiModelProperty(value = "修改人名称")
	private String updateUserName;
	@ApiModelProperty(value = "附件集合")
	private List<CustomOrderFiles> uploadFiles;
	@ApiModelProperty(value = "生产单集合")
	private List<ProduceOrderDto> produceOrderList;
	@ApiModelProperty(value = "类型名称")
	private String typeName;
	@ApiModelProperty(value = "发货人名称")
	private String deliveryCreatorName;
	@ApiModelProperty(value = "入库人名称")
	private String stockInputCreatorName;
	@ApiModelProperty(value = "系列名称")
	private String seriesName;
	@ApiModelProperty(value = "资源文件ID")
	private List<String> fileIds;
	@ApiModelProperty(value = "包裹信息")
	private List<FinishedStockItemDto> finishedStockItemDtos;
	@ApiModelProperty(value = "产品部件的ID字符串")
	private String productParts;
	@ApiModelProperty(value = "经销商名称")
	private String companyName;
	@ApiModelProperty(value = "经销商Id")
	private String companyId;
	@ApiModelProperty(value = "产品状态名称")
	private String statusName;
	@ApiModelProperty(value = "产品状态")
	private String productStatus;
	@ApiModelProperty(value = "物流公司名称")
	private String logisticsCompanyName;
	@ApiModelProperty(value = "默认物流公司名称")
	private String baseLogisticsCompanyName;
	@ApiModelProperty(value = "默认物流公司电话")
	private String baseLogisticsCompanyTel;
	@ApiModelProperty(value = "默认物流公司ID")
	private String baseLogisticsCompanyId;
	@ApiModelProperty(value = "经销商地址")
	private String dealerAddress;
	@ApiModelProperty(value = "经销商所在城市id")
	private String dealerCityId;
	@ApiModelProperty(value = "经销商所在城市")
	private String dealerCityAddress;
	@ApiModelProperty(value = "收货地址")
	private String address;
	@ApiModelProperty(value = "经销商电话")
	private String dealerTel;
	@ApiModelProperty(value = "产品部件")
	private List<ProductPartsDto> productPartsDtos;
	@ApiModelProperty(value = "终端客户名称")
	private String customerName;
	@ApiModelProperty(value = "收货人名称")
	private String consigneeName;
	@ApiModelProperty(value = "收货人电话")
	private String consigneeTel;
	@ApiModelProperty(value = "下单人姓名")
	private String placeOrderName;
	@ApiModelProperty(value = "收货人姓名")
	private String deliveryCreator;
	@ApiModelProperty(value = "接单人姓名")
	private String receiverName;
	@ApiModelProperty(value = "是否需要发货确认")
	private String deliverSure;
	@ApiModelProperty(value = "工艺名称")
	private String bodyTecName;
	@ApiModelProperty(value = "挂账标识")
	private String onCredit;
	@ApiModelProperty(value = "主订单编号")
	private String orderNo;
	@ApiModelProperty(value = "下单时间（提交审核时间）")
	private Date commitTime;
	@ApiModelProperty(value = "延期原因列表")
	private List<String> delayReasonList;
	@ApiModelProperty(value = "生产发货工期")
	private String produceAndDeliveryInfo;
	@ApiModelProperty(value = "订单价格")
	private BigDecimal orderPrice;
	@ApiModelProperty(value = "订单备注")
	private BigDecimal orderNotes;
	@ApiModelProperty(value = "订单产品属性")
	private List<OrderProductAttribute> productAttributeValues;
	@ApiModelProperty(value = "AES加密得订单编号")
	private String aesOrderNo;

	@Override
	public String getDeliveryCreator() {
		return deliveryCreator;
	}

	@Override
	public void setDeliveryCreator(String deliveryCreator) {
		this.deliveryCreator = deliveryCreator;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getDeliveryCreatorName() {
		return deliveryCreatorName;
	}

	public void setDeliveryCreatorName(String deliveryCreatorName) {
		this.deliveryCreatorName = deliveryCreatorName;
	}

	public String getStockInputCreatorName() {
		return stockInputCreatorName;
	}

	public void setStockInputCreatorName(String stockInputCreatorName) {
		this.stockInputCreatorName = stockInputCreatorName;
	}

	public List<ProduceOrderDto> getProduceOrderList() {
		return produceOrderList;
	}

	public void setProduceOrderList(List<ProduceOrderDto> produceOrderList) {
		this.produceOrderList = produceOrderList;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public List<CustomOrderFiles> getUploadFiles() {
		return uploadFiles;
	}

	public void setUploadFiles(List<CustomOrderFiles> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getSeriesName() {
		//产品系列 列表 系列 0 定制实木 1 特供实木 2 美克 3 康奈 4 慧娜 5 模压
		return seriesName;
	}

	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}

	public List<String> getFileIds() {
		return fileIds;
	}

	public void setFileIds(List<String> fileIds) {
		this.fileIds = fileIds;
	}

	public List<FinishedStockItemDto> getFinishedStockItemDtos() {
		return finishedStockItemDtos;
	}

	public void setFinishedStockItemDtos(List<FinishedStockItemDto> finishedStockItemDtos) {
		this.finishedStockItemDtos = finishedStockItemDtos;
	}

	public String getProductParts() {
		return productParts;
	}

	public void setProductParts(String productParts) {
		this.productParts = productParts;
	}

	public String getStatusName() {
		String statusName=this.statusName;
		if(this.getStatus()!=null){
		statusName=	OrderProductStatus.getByValue(this.getStatus()).getName();
		}
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getLogisticsCompanyName() {
		return logisticsCompanyName;
	}

	public void setLogisticsCompanyName(String logisticsCompanyName) {
		this.logisticsCompanyName = logisticsCompanyName;
	}

	public String getDealerAddress() {
		return dealerAddress;
	}

	public void setDealerAddress(String dealerAddress) {
		this.dealerAddress = dealerAddress;
	}

	public List<ProductPartsDto> getProductPartsDtos() {
		return productPartsDtos;
	}

	public void setProductPartsDtos(List<ProductPartsDto> productPartsDtos) {
		this.productPartsDtos = productPartsDtos;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getBaseLogisticsCompanyName() {
		return baseLogisticsCompanyName;
	}

	public void setBaseLogisticsCompanyName(String baseLogisticsCompanyName) {
		this.baseLogisticsCompanyName = baseLogisticsCompanyName;
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

	public String getBaseLogisticsCompanyId() {
		return baseLogisticsCompanyId;
	}

	public void setBaseLogisticsCompanyId(String baseLogisticsCompanyId) {
		this.baseLogisticsCompanyId = baseLogisticsCompanyId;
	}

	public String getPlaceOrderName() {
		return placeOrderName;
	}

	public void setPlaceOrderName(String placeOrderName) {
		this.placeOrderName = placeOrderName;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public String getReceiverName() {
		return receiverName;
	}

	public void setReceiverName(String receiverName) {
		this.receiverName = receiverName;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getDeliverSure() {
		return deliverSure;
	}

	public void setDeliverSure(String deliverSure) {
		this.deliverSure = deliverSure;
	}

	public String getBodyTecName() {
		return bodyTecName;
	}

	public void setBodyTecName(String bodyTecName) {
		this.bodyTecName = bodyTecName;
	}

	public String getOnCredit() {
		return onCredit;
	}

	public void setOnCredit(String onCredit) {
		this.onCredit = onCredit;
	}

	public String getBaseLogisticsCompanyTel() {
		return baseLogisticsCompanyTel;
	}

	public void setBaseLogisticsCompanyTel(String baseLogisticsCompanyTel) {
		this.baseLogisticsCompanyTel = baseLogisticsCompanyTel;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDealerTel() {
		return dealerTel;
	}

	public void setDealerTel(String dealerTel) {
		this.dealerTel = dealerTel;
	}

	public Date getCommitTime() {
		return commitTime;
	}

	public void setCommitTime(Date commitTime) {
		this.commitTime = commitTime;
	}

    public List<String> getDelayReasonList() {
        return delayReasonList;
    }

    public void setDelayReasonList(List<String> delayReasonList) {
        this.delayReasonList = delayReasonList;
    }

	public String getProduceAndDeliveryInfo() {
		return produceAndDeliveryInfo;
	}

	public void setProduceAndDeliveryInfo(String produceAndDeliveryInfo) {
		this.produceAndDeliveryInfo = produceAndDeliveryInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(BigDecimal orderPrice) {
		this.orderPrice = orderPrice;
	}

	public BigDecimal getOrderNotes() {
		return orderNotes;
	}

	public void setOrderNotes(BigDecimal orderNotes) {
		this.orderNotes = orderNotes;
	}

	public String getDealerCityId() {
		return dealerCityId;
	}

	public void setDealerCityId(String dealerCityId) {
		this.dealerCityId = dealerCityId;
	}

	public String getDealerCityAddress() {
		return dealerCityAddress;
	}

	public void setDealerCityAddress(String dealerCityAddress) {
		this.dealerCityAddress = dealerCityAddress;
	}

	public List<OrderProductAttribute> getProductAttributeValues() {
		return productAttributeValues;
	}

	public void setProductAttributeValues(List<OrderProductAttribute> productAttributeValues) {
		this.productAttributeValues = productAttributeValues;
	}

	public String getAesOrderNo() {
		return aesOrderNo;
	}

	public void setAesOrderNo(String aesOrderNo) {
		this.aesOrderNo = aesOrderNo;
	}
}
