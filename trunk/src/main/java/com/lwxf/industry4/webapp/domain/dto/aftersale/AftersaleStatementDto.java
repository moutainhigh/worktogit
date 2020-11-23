package com.lwxf.industry4.webapp.domain.dto.aftersale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="售后首页报表",description="售售后首页报表")
public class AftersaleStatementDto {

    @ApiModelProperty(value="收费补料单数量",name="bCount",example = "")
    private Integer bCount;
    @ApiModelProperty(value="收费补料单金额",name="bAmount",example = "")
    private Double bAmount;
    @ApiModelProperty(value="免费补料单数量",name="bmCount",example = "")
    private Integer bmCount;
    @ApiModelProperty(value="免费补料单金额",name="bmAmount",example = "")
    private Double bmAmount;
    @ApiModelProperty(value="反馈单数量",name="fCount",example = "")
    private Integer fCount;
    @ApiModelProperty(value="总数量",name="countAll",example = "")
    private Integer countAll;
    @ApiModelProperty(value="总金额",name="sumAll",example = "")
    private Double sumAll;
    private String date;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getCountAll() {
        return this.bCount+this.bmCount+this.fCount;
    }

    public Double getSumAll() {
        return this.bAmount+this.bmAmount;
    }

    public Integer getbCount() {
        return bCount;
    }

    public void setbCount(Integer bCount) {
        this.bCount = bCount;
    }

    public Double getbAmount() {
        return bAmount;
    }

    public void setbAmount(Double bAmount) {
        this.bAmount = bAmount;
    }

    public Integer getBmCount() {
        return bmCount;
    }

    public void setBmCount(Integer bmCount) {
        this.bmCount = bmCount;
    }

    public Double getBmAmount() {
        return bmAmount;
    }

    public void setBmAmount(Double bmAmount) {
        this.bmAmount = bmAmount;
    }

    public Integer getfCount() {
        return fCount;
    }

    public void setfCount(Integer fCount) {
        this.fCount = fCount;
    }
}
