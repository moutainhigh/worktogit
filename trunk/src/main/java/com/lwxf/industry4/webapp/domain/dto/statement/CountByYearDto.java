package com.lwxf.industry4.webapp.domain.dto.statement;

import io.swagger.annotations.ApiModelProperty;

public class CountByYearDto {

    @ApiModelProperty(value = "周一")
    public Double jan;
    public Double feb;
    public Double mar;
    public Double apr;
    public Double may;
    public Double jun;
    public Double jul;
    public Double aug;
    public Double sept;
    public Double oct;
    public Double nov;
    public Double dece;

    public Double getJan() {
        return jan;
    }

    public void setJan(Double jan) {
        this.jan = jan;
    }

    public Double getFeb() {
        return feb;
    }

    public void setFeb(Double feb) {
        this.feb = feb;
    }

    public Double getMar() {
        return mar;
    }

    public void setMar(Double mar) {
        this.mar = mar;
    }

    public Double getApr() {
        return apr;
    }

    public void setApr(Double apr) {
        this.apr = apr;
    }

    public Double getMay() {
        return may;
    }

    public void setMay(Double may) {
        this.may = may;
    }

    public Double getJun() {
        return jun;
    }

    public void setJun(Double jun) {
        this.jun = jun;
    }

    public Double getJul() {
        return jul;
    }

    public void setJul(Double jul) {
        this.jul = jul;
    }

    public Double getAug() {
        return aug;
    }

    public void setAug(Double aug) {
        this.aug = aug;
    }

    public Double getSept() {
        return sept;
    }

    public void setSept(Double sept) {
        this.sept = sept;
    }

    public Double getOct() {
        return oct;
    }

    public void setOct(Double oct) {
        this.oct = oct;
    }

    public Double getNov() {
        return nov;
    }

    public void setNov(Double nov) {
        this.nov = nov;
    }

    public Double getDece() {
        return dece;
    }

    public void setDece(Double dece) {
        this.dece = dece;
    }
}
