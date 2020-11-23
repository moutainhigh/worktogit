package com.lwxf.industry4.webapp.domain.dto.statement;

import io.swagger.annotations.ApiModelProperty;

public class CountByWeekDto {
    @ApiModelProperty(value = "周一")
    public Double mon;
    @ApiModelProperty(value = "周二")
    public Double tues;
    @ApiModelProperty(value = "周三")
    public Double wed;
    @ApiModelProperty(value = "周四")
    public Double thur;
    @ApiModelProperty(value = "周五")
    public Double fri;
    @ApiModelProperty(value = "周六")
    public Double sat;
    @ApiModelProperty(value = "周日")
    public Double sun;

    public Double getMon() {
        return mon;
    }

    public void setMon(Double mon) {
        this.mon = mon;
    }

    public Double getTues() {
        return tues;
    }

    public void setTues(Double tues) {
        this.tues = tues;
    }

    public Double getWed() {
        return wed;
    }

    public void setWed(Double wed) {
        this.wed = wed;
    }

    public Double getThur() {
        return thur;
    }

    public void setThur(Double thur) {
        this.thur = thur;
    }

    public Double getFri() {
        return fri;
    }

    public void setFri(Double fri) {
        this.fri = fri;
    }

    public Double getSat() {
        return sat;
    }

    public void setSat(Double sat) {
        this.sat = sat;
    }

    public Double getSun() {
        return sun;
    }

    public void setSun(Double sun) {
        this.sun = sun;
    }
}
