package com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * F端APP财务审核首页上部统计信息
 * created by zhangxiaolin
 */
@ApiModel(value="审核首页上部统计信息",description="审核首页上部统计信息")
public class FinanceCountDto implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="今日审核单数",name="orderHandleToday")
    private Integer orderHandleToday;//今日审核单数
    @ApiModelProperty(value="本月审核单数",name="orderHandleMonthly")
    private Integer orderHandleMonthly;//本月审核单数
    @ApiModelProperty(value="今日扣款金额",name="chargebacksToday")
    private Double chargebacksToday;//今日扣款金额
    @ApiModelProperty(value="本月扣款金额",name="chargebacksMonthly")
    private Double chargebacksMonthly; //本月扣款金额
    @ApiModelProperty(value="审核人列表",name="verifyList")
    private List<VerifyPaymentDto> verifyList;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public List<VerifyPaymentDto> getVerifyList() {
        return verifyList;
    }

    public void setVerifyList(List<VerifyPaymentDto> verifyList) {
        this.verifyList = verifyList;
    }

    public Integer getOrderHandleToday() {
        return orderHandleToday;
    }

    public void setOrderHandleToday(Integer orderHandleToday) {
        this.orderHandleToday = orderHandleToday;
    }

    public Integer getOrderHandleMonthly() {
        return orderHandleMonthly;
    }

    public void setOrderHandleMonthly(Integer orderHandleMonthly) {
        this.orderHandleMonthly = orderHandleMonthly;
    }

    public Double getChargebacksToday() {
        return chargebacksToday;
    }

    public void setChargebacksToday(Double chargebacksToday) {
        this.chargebacksToday = chargebacksToday;
    }

    public Double getChargebacksMonthly() {
        return chargebacksMonthly;
    }

    public void setChargebacksMonthly(Double chargebacksMonthly) {
        this.chargebacksMonthly = chargebacksMonthly;
    }

}
