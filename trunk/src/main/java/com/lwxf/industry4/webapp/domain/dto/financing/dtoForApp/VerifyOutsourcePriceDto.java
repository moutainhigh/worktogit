package com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * F端APP外协费审核页面
 * created by zhangxiaolin
 */
@ApiModel(value="外协审核单信息实体",description="外协审核单信息实体")
public class VerifyOutsourcePriceDto {
    @ApiModelProperty(value="收支信息ID",name="paymentId")
    private String id; //paymentId;
    @ApiModelProperty(value="订单id",name="orderId")
    private String orderId;//订单id
    @ApiModelProperty(value="订单号",name="orderNo")
    private String orderNo; //订单号
    @ApiModelProperty(value="订单金额",name="amount")
    private String amount;//订单金额
    @ApiModelProperty(value="经销商id",name="copmanyId")
    private String companyId; //经销商id
    @ApiModelProperty(value="经销商名称",name="compnayName")
    private String companyName;//经销商名称
    @ApiModelProperty(value="订单类型",name="orderType")
    private String orderType; //订单类型
    @ApiModelProperty(value="订单类型",name="productionType")
    private String productionType;//生产方式
    @ApiModelProperty(value="订单创建时间",name="orderCreated")
    private Date orderCreated; //订单创建时间
    @ApiModelProperty(value="接单员",name="orderCreator")
    private String orderCreator;//接单员
    @ApiModelProperty(value="审核人列表",name="financer")
    private List<Map<String,String>> financer;//审核人列表

    //外协信息
    private String coordinationName; //外协厂家名称
    private Double outSourceAmount; //外协金额
    private String coordinationAccount;//外协账户
    private String coordinationBank; //开户行名称
    private String note; //外协备注

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
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

    public Double getOutSourceAmount() {
        return outSourceAmount;
    }

    public void setOutSourceAmount(Double outSourceAmount) {
        this.outSourceAmount = outSourceAmount;
    }


    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCoordinationName() {
        return coordinationName;
    }

    public void setCoordinationName(String coordinationName) {
        this.coordinationName = coordinationName;
    }

    public String getCoordinationAccount() {
        return coordinationAccount;
    }

    public void setCoordinationAccount(String coordinationAccount) {
        this.coordinationAccount = coordinationAccount;
    }

    public String getCoordinationBank() {
        return coordinationBank;
    }

    public void setCoordinationBank(String coordinationBank) {
        this.coordinationBank = coordinationBank;
    }
}
