package com.lwxf.industry4.webapp.domain.dto.statement;

import io.swagger.annotations.ApiModelProperty;

public class WxFactoryStatementDto {

    @ApiModelProperty(value = "接单数")
    public Integer jiedan;
    @ApiModelProperty(value = "付款数")
    public Integer fukuan;
    @ApiModelProperty(value = "生产数")
    public Integer shengchan;
    @ApiModelProperty(value = "延期数")
    public Integer yanqi;
    @ApiModelProperty(value = "收入数")
    public Double shouru;
    @ApiModelProperty(value = "支出数")
    public Double zhichu;

    public Integer getJiedan() {
        return jiedan;
    }

    public void setJiedan(Integer jiedan) {
        this.jiedan = jiedan;
    }

    public Integer getFukuan() {
        return fukuan;
    }

    public void setFukuan(Integer fukuan) {
        this.fukuan = fukuan;
    }

    public Integer getShengchan() {
        return shengchan;
    }

    public void setShengchan(Integer shengchan) {
        this.shengchan = shengchan;
    }

    public Integer getYanqi() {
        return yanqi;
    }

    public void setYanqi(Integer yanqi) {
        this.yanqi = yanqi;
    }

    public Double getShouru() {
        return shouru;
    }

    public void setShouru(Double shouru) {
        this.shouru = shouru;
    }

    public Double getZhichu() {
        return zhichu;
    }

    public void setZhichu(Double zhichu) {
        this.zhichu = zhichu;
    }
}
