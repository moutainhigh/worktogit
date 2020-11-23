package com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp;

import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentType;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * F端app财务-经销商财务信息列表
 * created by zhangxiaolin
 */
@ApiModel(value="经销商财务信息列表",description="经销商财务信息列表")
public class CompanyFinanceListDto {
     @ApiModelProperty(value="财务信息ID",name="财务信息ID")
     private String paymentId;
     @ApiModelProperty(value="创建时间",name="created")
     private Date created;
     @ApiModelProperty(value="经销商公司名称",name="companyName")
     private String companyName;
     private Integer type;
     @ApiModelProperty(value="类型名称",name="typeName")
     private String typeName;
     private Integer funds;
     @ApiModelProperty(value="科目名称",name="fundsName")
     private String fundsName;
     @ApiModelProperty(value="总金额",name="amount")
     private Double amount;
    @ApiModelProperty(value="备注",name="notes")
    private String notes;
    @ApiModelProperty(value="记账人id",name="holder")
    private String holder;
    @ApiModelProperty(value="记账人名称",name="holderName")
    private String holderName;
    @ApiModelProperty(value="创建人",name="creator")
    private String creator;
    @ApiModelProperty(value="支付方式名称",name="waysName")
    private String waysName;
    @ApiModelProperty(value="支付方式",name="way")
    private Integer way;
    @ApiModelProperty(value="订单编号",name="orderNo")
    private String orderNo;

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getWaysName() {
        return PaymentWay.getByValue(this.way)==null?"":PaymentWay.getByValue(this.way).getName();
    }

    public void setWaysName(String waysName) {
        this.waysName = waysName;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFundsName() {
        return PaymentFunds.getByValue(this.getFunds())==null?"":PaymentFunds.getByValue(this.getFunds()).getName();
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    //bug 系统中没有收入和支出字段，需要确认业务，先这样处理
    public String getTypeName() {
       return PaymentTypeNew.getByValue(this.getType()).getName();
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
