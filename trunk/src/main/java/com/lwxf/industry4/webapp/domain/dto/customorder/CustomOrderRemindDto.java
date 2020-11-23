package com.lwxf.industry4.webapp.domain.dto.customorder;

import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderRemind;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能：催款dto
 *
 * @author：DJL
 * @create：2020/1/6 10:52
 * @version：2020 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "CustomOrderRemindDto", description = "订单催款Dto")
public class CustomOrderRemindDto extends CustomOrderRemind {
    // 订单编号
    @ApiModelProperty(value = "订单编号")
    private String no;
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;
    // 经销商名称
    @ApiModelProperty(value = "经销商名称")
    private String dealerName;
    // 经销商电话
    @ApiModelProperty(value = "经销商电话")
    private String dealerTel;
    // 经销商金额
    @ApiModelProperty(value = "账户余额(自由资金)")
    private BigDecimal balance;
    // 订单金额
    @ApiModelProperty(value = "工厂的最终报价")
    private BigDecimal factoryFinalPrice;
    // 催款状态名称
    @ApiModelProperty(value = "催款状态名称")
    private String statusName;
    // 下单人姓名
    @ApiModelProperty(value = "下单人姓名")
    private String creatorName;
    // 催款人姓名
    @ApiModelProperty(value = "催款人姓名")
    private String receiverName;
    // 订单接单人姓名
    @ApiModelProperty(value = "订单接单人姓名")
    private String orderReceiverName;

    @ApiModelProperty(value = "预计交货时间")
    private Date estimatedDeliveryDate;

    @ApiModelProperty(value = "终端客户姓名")
    private String customerName;
    @ApiModelProperty(value = "催款工期")
    private String pressForMoneyTime;
    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public CustomOrderRemindDto() {
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerTel() {
        return dealerTel;
    }

    public void setDealerTel(String dealerTel) {
        this.dealerTel = dealerTel;
    }

    public BigDecimal getBalance() {

        return balance.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getFactoryFinalPrice() {

        return factoryFinalPrice.setScale(2,BigDecimal.ROUND_HALF_UP);
    }

    public void setFactoryFinalPrice(BigDecimal factoryFinalPrice) {
        this.factoryFinalPrice = factoryFinalPrice;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getOrderReceiverName() {
        return orderReceiverName;
    }

    public void setOrderReceiverName(String orderReceiverName) {
        this.orderReceiverName = orderReceiverName;
    }

    public String getPressForMoneyTime() {
        return pressForMoneyTime;
    }

    public void setPressForMoneyTime(String pressForMoneyTime) {
        this.pressForMoneyTime = pressForMoneyTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
}
