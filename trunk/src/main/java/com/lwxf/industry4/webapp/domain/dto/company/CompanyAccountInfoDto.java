package com.lwxf.industry4.webapp.domain.dto.company;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="经销商余额信息",description="经销商余额信息")
public class CompanyAccountInfoDto {

    @ApiModelProperty(value = "自由账户")
    private Double freeAccount;
    @ApiModelProperty(value = "设计金")
    private Double designFee;
    @ApiModelProperty(value = "保证金")
    private Double bond;
    @ApiModelProperty(value = "经销商id")
    private String id;
    @ApiModelProperty(value = "经销商名称")
    private String name;
    @ApiModelProperty(value = "经销商地址")
    private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getFreeAccount() {
        return freeAccount;
    }

    public void setFreeAccount(Double freeAccount) {
        this.freeAccount = freeAccount;
    }

    public Double getDesignFee() {
        return designFee;
    }

    public void setDesignFee(Double designFee) {
        this.designFee = designFee;
    }

    public Double getBond() {
        return bond;
    }

    public void setBond(Double bond) {
        this.bond = bond;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
