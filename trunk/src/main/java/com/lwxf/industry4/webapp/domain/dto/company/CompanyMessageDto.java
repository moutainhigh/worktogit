package com.lwxf.industry4.webapp.domain.dto.company;

import com.lwxf.industry4.webapp.domain.entity.company.CompanyMessage;
import io.swagger.annotations.ApiModelProperty;

public class CompanyMessageDto extends CompanyMessage {

    @ApiModelProperty(value="发送者名称",name="fromUserName")
    String fromUserName;
    @ApiModelProperty(value="接收者名称",name="toUserName")
    String toUserName;
    @ApiModelProperty(value="所在城市名称",name="cityName")
    String cityName;
    @ApiModelProperty(value="经销商名称",name="cityName")
    String companyName;
    @ApiModelProperty(value="发送者头像",name="fromUserAvatar")
    String fromUserAvatar;
    @ApiModelProperty(value="接收者名称",name="toUserAvatar")
    String toUserAvatar;

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getFromUserAvatar() {
        return fromUserAvatar;
    }

    public void setFromUserAvatar(String fromUserAvatar) {
        this.fromUserAvatar = fromUserAvatar;
    }

    public String getToUserAvatar() {
        return toUserAvatar;
    }

    public void setToUserAvatar(String toUserAvatar) {
        this.toUserAvatar = toUserAvatar;
    }
}
