package com.lwxf.industry4.webapp.domain.dto.dealer;

import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;

/**
 * 功能：经销商排行Dto
 *
 * @author：DJL
 * @create：2019/12/7 11:53
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DealerReportRankDto {
    @ApiModelProperty("经销商ID")
    private String companyId;
    @ApiModelProperty("经销商名称")
    private String companyName;
    @ApiModelProperty("经销商地址")
    private String companyAddress;
    @ApiModelProperty("经销商订单总数")
    private Integer orderTotal;
    @ApiModelProperty("经销商订单总金额")
    private String orderMoneyTotal;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public Integer getOrderTotal() {
        return orderTotal;
    }

    public void setOrderTotal(Integer orderTotal) {
        this.orderTotal = orderTotal;
    }

    public String getOrderMoneyTotal() {
        if (orderMoneyTotal != null) {
            return String.format("%,.2f", Double.valueOf(orderMoneyTotal));
        } else {
            return orderMoneyTotal;
        }
    }

    public void setOrderMoneyTotal(String orderMoneyTotal) {
        this.orderMoneyTotal = orderMoneyTotal;
    }
}
