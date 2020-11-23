package com.lwxf.industry4.webapp.domain.dto.dealer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：经销商数量汇总统计Dto
 *
 * @author：DJL
 * @create：2019/12/6 14:54
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "经销商数量汇总统计")
public class DealerReportSummaryDto {
    @ApiModelProperty(value = "经销商总数量")
    private Integer total;
    @ApiModelProperty(value = "本月新增经销商数量")
    private Integer monthAdd;
    @ApiModelProperty(value = "本年新增经销商数量")
    private Integer yearAdd;
    @ApiModelProperty(value = "意向经销商总数量")
    private Integer intentionTotal;
    @ApiModelProperty(value = "本月新增意向经销商数量")
    private Integer intentionMonthAdd;
    @ApiModelProperty(value = "本年新增意向经销商数量")
    private Integer intentionYearAdd;
    @ApiModelProperty(value = "签约经销商总数量")
    private Integer normalTotal;
    @ApiModelProperty(value = "本月新增签约经销商数量")
    private Integer normalMonthAdd;
    @ApiModelProperty(value = "本年新增签约经销商数量")
    private Integer normalYearAdd;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getMonthAdd() {
        return monthAdd;
    }

    public void setMonthAdd(Integer monthAdd) {
        this.monthAdd = monthAdd;
    }

    public Integer getYearAdd() {
        return yearAdd;
    }

    public void setYearAdd(Integer yearAdd) {
        this.yearAdd = yearAdd;
    }

    public Integer getIntentionTotal() {
        return intentionTotal;
    }

    public void setIntentionTotal(Integer intentionTotal) {
        this.intentionTotal = intentionTotal;
    }

    public Integer getIntentionMonthAdd() {
        return intentionMonthAdd;
    }

    public void setIntentionMonthAdd(Integer intentionMonthAdd) {
        this.intentionMonthAdd = intentionMonthAdd;
    }

    public Integer getIntentionYearAdd() {
        return intentionYearAdd;
    }

    public void setIntentionYearAdd(Integer intentionYearAdd) {
        this.intentionYearAdd = intentionYearAdd;
    }

    public Integer getNormalTotal() {
        return normalTotal;
    }

    public void setNormalTotal(Integer normalTotal) {
        this.normalTotal = normalTotal;
    }

    public Integer getNormalMonthAdd() {
        return normalMonthAdd;
    }

    public void setNormalMonthAdd(Integer normalMonthAdd) {
        this.normalMonthAdd = normalMonthAdd;
    }

    public Integer getNormalYearAdd() {
        return normalYearAdd;
    }

    public void setNormalYearAdd(Integer normalYearAdd) {
        this.normalYearAdd = normalYearAdd;
    }
}
