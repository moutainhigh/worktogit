package com.lwxf.industry4.webapp.domain.dto.customorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/15 0015 11:44
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "F端微信小程序：订单列表输出模型", description = "F端微信小程序：订单列表输出模型")
public class WxCustomOrderDto {
    @ApiModelProperty(value = "状态 ")
    private Integer status;
    @ApiModelProperty(value = "状态名称 ")
    private String statusName;
    @ApiModelProperty(value = "经销商名称")
    private String dealerName;
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    @ApiModelProperty(value = "订单编号")
    private String orderNo;
    @JsonFormat(locale = "zh", timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "创建时间")
    private Date orderCreated;
    @ApiModelProperty(value = "订单id")
    private String orderId;
    @ApiModelProperty(value = "业务经理名称")
    private String salesmanName;
    @ApiModelProperty(value = "订单产品类型")
    private String orderProductType;

    @ApiModelProperty(value = "订单产品类型名称")
    private String orderProductTypeName;
    @ApiModelProperty(value = "产品系列")
    private List<String> seriesNames;
    @ApiModelProperty(value = "产品数量")
    private Integer productNum;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal factoryFinalPrice;
    @ApiModelProperty(value = "订单价格")
    private String factoryPrice;
    @ApiModelProperty(value = "客户姓名")
    private String consigneeName;
    @ApiModelProperty(value = "下单人姓名")
    private String placeOrderName;
    @ApiModelProperty(value = "经销商端创建订单专用状态 ：0-草稿  1-已提交")
    private String state;
    @ApiModelProperty(value = "订单提交审核时间")
    private Date commitTime;

    public Date getCommitTime() {
        return commitTime;
    }

    public void setCommitTime(Date commitTime) {
        this.commitTime = commitTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getOrderProductTypeName() {
        return orderProductTypeName;
    }

    public void setOrderProductTypeName(String orderProductTypeName) {
        this.orderProductTypeName = orderProductTypeName;
    }

    public String getFactoryPrice(){
        return factoryPrice;
    }
    public void setFactoryPrice(String factoryPrice){
        this.factoryPrice = factoryPrice;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public List<String> getSeriesNames() {
        return seriesNames;
    }
    public void setSeriesNames(List<String> seriesNames) {
        this.seriesNames = seriesNames;
    }
    public String getOrderProductType() {
        return orderProductType;
    }
    public void setOrderProductType(String orderProductType) {
        this.orderProductType = orderProductType;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Date getOrderCreated() {
        return orderCreated;
    }

    public void setOrderCreated(Date orderCreated) {
        this.orderCreated = orderCreated;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    /**
     * 状态：
     * 0 - 待报价；
     * 1 - 待支付；
     * 2 - 待生产；
     * 3 - 生产中；
     * 4 - 待包装；
     * 5 - 待发货；
     * 6 - 已发货；
     * @return
     */
    public String getStatusName() {
        switch (this.status) {
            case 0:
                statusName = "待提交";
                break;
            case 1:
                statusName = "待支付";
                break;
            case 2:
                statusName = "待生产";
                break;
            case 3:
                statusName = "生产中";
                break;
            case 4:
                statusName = "待包装";
                break;
            case 5:
                statusName = "待发货";
                break;
            case 6:
                statusName = "已发货";
                break;
            case 7:
                statusName = "已完成";
                break;
            case 8:
                statusName = "已作废";
                break;
            case 9:
                statusName = "待接单";
                break;
            default:
                statusName = "其他";
        }
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }


    public BigDecimal getFactoryFinalPrice() {
        return factoryFinalPrice;
    }

    public void setFactoryFinalPrice(BigDecimal factoryFinalPrice) {
        this.factoryFinalPrice = factoryFinalPrice;
    }

    public String getPlaceOrderName() {
        return placeOrderName;
    }

    public void setPlaceOrderName(String placeOrderName) {
        this.placeOrderName = placeOrderName;
    }
}
