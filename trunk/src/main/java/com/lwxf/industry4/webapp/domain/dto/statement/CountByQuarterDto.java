package com.lwxf.industry4.webapp.domain.dto.statement;

import io.swagger.annotations.ApiModelProperty;

public class CountByQuarterDto {
    @ApiModelProperty(value = "季度第一个月")
    public Double month1;
    @ApiModelProperty(value = "季度第二个月")
    public Double month2;
    @ApiModelProperty(value = "季度第三个月")
    public Double month3;

    public Double getMonth1() {
        return month1;
    }

    public void setMonth1(Double month1) {
        this.month1 = month1;
    }

    public Double getMonth2() {
        return month2;
    }

    public void setMonth2(Double month2) {
        this.month2 = month2;
    }

    public Double getMonth3() {
        return month3;
    }

    public void setMonth3(Double month3) {
        this.month3 = month3;
    }
}
