package com.lwxf.industry4.webapp.domain.dto.customorder;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 功能：B端微信小程序：最近订单列表输出模型
 *
 * @author：DJL
 * @create：2019/11/1 10:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "B端微信小程序：最近订单列表输出模型",description = "B端微信小程序：最近订单列表输出模型")
public class WxBCustomOrderDto {

    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    @ApiModelProperty(value = "订单状态名称")
    private String statusName;
    @ApiModelProperty(value = "订单产品型类型名称")
    private String orderProductTypeName;
    @ApiModelProperty(value = "产品系列名称")
    private String seriesName;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private Date orderCreated;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal price;
    @ApiModelProperty(value = "产品数量")
    private Integer orderProductCount;
    @ApiModelProperty(value = "下单时间")
    private Date commitTime;


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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getOrderProductTypeName() {
        return orderProductTypeName;
    }

    public void setOrderProductTypeName(String orderProductTypeName) {
        this.orderProductTypeName = orderProductTypeName;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public Date getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(Date orderCreated) {
        this.orderCreated = orderCreated;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getOrderProductCount() {
        return orderProductCount;
    }

    public void setOrderProductCount(Integer orderProductCount) {
        this.orderProductCount = orderProductCount;
    }

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }
}
