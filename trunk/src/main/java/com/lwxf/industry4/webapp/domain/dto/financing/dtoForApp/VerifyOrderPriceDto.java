package com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * F端APP财务货款审核页面
 * created by zhangxiaolin
 */
@ApiModel(value="订单审核单信息实体",description="订单审核单信息实体")
public class VerifyOrderPriceDto {
    @ApiModelProperty(value="收支信息ID",name="id")
    private String id;
    @ApiModelProperty(value="订单id",name="orderId")
    private String orderId;
    @ApiModelProperty(value="订单号",name="orderNo")
    private String orderNo;
    @ApiModelProperty(value="订单金额",name="factoryFinalPrice")
    private Double factoryFinalPrice;
    @ApiModelProperty(value="经销商账户余额",name="companyBalance")
    private Double companyBalance;
    @ApiModelProperty(value="经销商id",name="copmanyId")
    private String companyId;
    @ApiModelProperty(value="经销商名称",name="compnayName")
    private String companyName;
    @ApiModelProperty(value="订单类型",name="orderType")
    private String orderType;
    @ApiModelProperty(value="订单类型",name="orderTypeName")
    private String orderTypeName;
    @ApiModelProperty(value="生产方式",name="productionType")
    private String productionType;
    @ApiModelProperty(value="订单创建时间",name="orderCreated")
    private Date orderCreated;
    @ApiModelProperty(value="接单员",name="orderCreator")
    private String orderCreator;
    @ApiModelProperty(value="审核人列表(name:名称,id:审核人id)",name="financer")
    private List<Map<String,String>> financer;
    @ApiModelProperty(value="要求交期",name="estimatedDeliveryDate")
    private Date estimatedDeliveryDate; //订单创建时间

    public Double getCompanyBalance() {
        return companyBalance;
    }

    public void setCompanyBalance(Double companyBalance) {
        this.companyBalance = companyBalance;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public String getOrderTypeName() {
        return orderTypeName;
    }

    public void setOrderTypeName(String orderTypeName) {
        this.orderTypeName = orderTypeName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Double getFactoryFinalPrice() {
        return factoryFinalPrice==null?0:factoryFinalPrice;
    }

    public void setFactoryFinalPrice(Double factoryFinalPrice) {
        this.factoryFinalPrice = factoryFinalPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getProductionType() {
        return productionType;
    }

    public void setProductionType(String productionType) {
        this.productionType = productionType;
    }

    public Date getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(Date orderCreated) {
        this.orderCreated = orderCreated;
    }

    public String getOrderCreator() {
        return orderCreator;
    }

    public void setOrderCreator(String orderCreator) {
        this.orderCreator = orderCreator;
    }

    public List<Map<String, String>> getFinancer() {
        return financer;
    }

    public void setFinancer(List<Map<String, String>> financer) {
        this.financer = financer;
    }
}
