package com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

/**
 * F端APP财务审核首页下部信息列表
 * created by zhangxiaolin
 */
@ApiModel(value="财务审核首页下测列表",description="财务审核首页下测列表")
public class VerifyPaymentDto {
    @ApiModelProperty(value="收支信息ID(paymentId)",name="id")
    private String id;
    @ApiModelProperty(value="订单编号",name="orderNo")
    private String orderNo;
    @ApiModelProperty(value="订单ID",name="orderId")
    private String orderId;
    @ApiModelProperty(value="费用科目id",name="type")
    private Integer funds;
    @ApiModelProperty(value="费用科目名称",name="typeName")
    private String fundsName;
    @ApiModelProperty(value="费用科目编码",name="typeCode")
    private String fundsCode;
    @ApiModelProperty(value="经销商名称",name="companyName")
    private String companyName;
    @ApiModelProperty(value="创建人名称",name="creatorName")
    private String creatorName;
    @ApiModelProperty(value="创建时间",name="created")
    private Date created;

    public String getFundsCode() {
        return PaymentFunds.getByValue(this.funds).name();
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public String getFundsName() {
        return PaymentFunds.getByValue(this.getFunds())==null?"":PaymentFunds.getByValue(this.getFunds()).getName();
    }

    public void setFundsName(String fundsName) {
        this.fundsName = fundsName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }
}
