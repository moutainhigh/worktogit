package com.lwxf.industry4.webapp.domain.dto.statement;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能：财务报表订单数据Dto
 *
 * @author：DJL
 * @create：2019/12/9 14:47
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class FinanceReportOrderDto {

    @ApiModelProperty(value = "日期字符串")
    private String reportDate;
    @ApiModelProperty(value = "订单总数量")
    private Integer orderTotal;
    @ApiModelProperty(value = "订单均量")
    private BigDecimal orderTotalAverage;
    @ApiModelProperty(value = "订单总金额")
    private BigDecimal orderMoneyTotal;
    @ApiModelProperty(value = "订单均价")
    private BigDecimal orderMoneyAverage;
    @ApiModelProperty(value = "订单日总金额")
    private BigDecimal orderMoneyTotalOfDay;
    @ApiModelProperty(value = "已审核订单数量")
    private Integer hasApproveOrderTotal;
    @ApiModelProperty(value = "已审核订单金额")
    private BigDecimal hasApproveOrderMoneyTotal;
    @ApiModelProperty(value = "未审核订单数量")
    private Integer noApproveOrderTotal;
    @ApiModelProperty(value = "未审核订单金额")
    private BigDecimal noApproveOrderMoneyTotal;
    @ApiModelProperty(value = "订单数量环比量")
    private Integer orderTotalChainNum;
    @ApiModelProperty(value = "订单数量环比比值")
    private String orderTotalChainPercent;
    @ApiModelProperty(value = "订单金额环比量")
    private BigDecimal orderMoneyToalChainNum;
    @ApiModelProperty(value = "订单金额环比比值")
    private String orderMoneyToalChainPercent;
    @ApiModelProperty(value = "日订单均价")
    private BigDecimal orderMoneyAverageOfDay;
    @ApiModelProperty(value = "日订单均量")
    private Integer orderTotalAverageOfDay;

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public BigDecimal getOrderTotalAverage() {
        return orderTotalAverage;
    }

    public void setOrderTotalAverage(BigDecimal orderTotalAverage) {
        this.orderTotalAverage = orderTotalAverage;
    }

    public BigDecimal getOrderMoneyTotal() {
        return orderMoneyTotal;
    }

    public void setOrderMoneyTotal(BigDecimal orderMoneyTotal) {
        this.orderMoneyTotal = orderMoneyTotal;
    }

    public BigDecimal getOrderMoneyAverage() {
        return orderMoneyAverage;
    }

    public void setOrderMoneyAverage(BigDecimal orderMoneyAverage) {
        this.orderMoneyAverage = orderMoneyAverage;
    }

    public BigDecimal getOrderMoneyTotalOfDay() {
        return orderMoneyTotalOfDay;
    }

    public void setOrderMoneyTotalOfDay(BigDecimal orderMoneyTotalOfDay) {
        this.orderMoneyTotalOfDay = orderMoneyTotalOfDay;
    }

    public Integer getHasApproveOrderTotal() {
        return hasApproveOrderTotal;
    }

    public void setHasApproveOrderTotal(Integer hasApproveOrderTotal) {
        this.hasApproveOrderTotal = hasApproveOrderTotal;
    }

    public BigDecimal getHasApproveOrderMoneyTotal() {
        return hasApproveOrderMoneyTotal;
    }

    public void setHasApproveOrderMoneyTotal(BigDecimal hasApproveOrderMoneyTotal) {
        this.hasApproveOrderMoneyTotal = hasApproveOrderMoneyTotal;
    }

    public Integer getNoApproveOrderTotal() {
        return noApproveOrderTotal;
    }

    public void setNoApproveOrderTotal(Integer noApproveOrderTotal) {
        this.noApproveOrderTotal = noApproveOrderTotal;
    }

    public BigDecimal getNoApproveOrderMoneyTotal() {
        return noApproveOrderMoneyTotal;
    }

    public void setNoApproveOrderMoneyTotal(BigDecimal noApproveOrderMoneyTotal) {
        this.noApproveOrderMoneyTotal = noApproveOrderMoneyTotal;
    }

    public Integer getOrderTotalChainNum() {
        return orderTotalChainNum;
    }

    public void setOrderTotalChainNum(Integer orderTotalChainNum) {
        this.orderTotalChainNum = orderTotalChainNum;
    }

    public String getOrderTotalChainPercent() {
        return orderTotalChainPercent;
    }

    public void setOrderTotalChainPercent(String orderTotalChainPercent) {
        this.orderTotalChainPercent = orderTotalChainPercent;
    }

    public BigDecimal getOrderMoneyToalChainNum() {
        return orderMoneyToalChainNum;
    }

    public void setOrderMoneyToalChainNum(BigDecimal orderMoneyToalChainNum) {
        this.orderMoneyToalChainNum = orderMoneyToalChainNum;
    }

    public String getOrderMoneyToalChainPercent() {
        return orderMoneyToalChainPercent;
    }

    public void setOrderMoneyToalChainPercent(String orderMoneyToalChainPercent) {
        this.orderMoneyToalChainPercent = orderMoneyToalChainPercent;
    }

    public BigDecimal getOrderMoneyAverageOfDay() {
        return orderMoneyAverageOfDay;
    }

    public void setOrderMoneyAverageOfDay(BigDecimal orderMoneyAverageOfDay) {
        this.orderMoneyAverageOfDay = orderMoneyAverageOfDay;
    }

    public Integer getOrderTotalAverageOfDay() {
        return orderTotalAverageOfDay;
    }

    public void setOrderTotalAverageOfDay(Integer orderTotalAverageOfDay) {
        this.orderTotalAverageOfDay = orderTotalAverageOfDay;
    }
}
