package com.lwxf.industry4.webapp.domain.dto.system;

import com.lwxf.industry4.webapp.domain.entity.system.SysSms;
import com.lwxf.mybatis.annotation.Column;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 功能：消息配置Dto
 *
 * @author：DJL
 * @create：2019/11/20 17:55
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "消息配置", description = "消息配置")
public class SysSmsDto extends SysSms {

    @ApiModelProperty(value = "短信模板编码")
    private String smsCode;
    @ApiModelProperty(value = "短信模板内容")
    private String smsContent;
    @ApiModelProperty(value = "微信模板编码")
    private String WxCode;
    @ApiModelProperty(value = "微信模板内容")
    private String WxContent;
    @ApiModelProperty(value = "节点名称")
    private String nodeTypeName;

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode;
    }

    public String getSmsContent() {
        return smsContent;
    }

    public void setSmsContent(String smsContent) {
        this.smsContent = smsContent;
    }

    public String getWxCode() {
        return WxCode;
    }

    public void setWxCode(String wxCode) {
        WxCode = wxCode;
    }

    public String getWxContent() {
        return WxContent;
    }

    public void setWxContent(String wxContent) {
        WxContent = wxContent;
    }

    public String getNodeTypeName() {
        return nodeTypeName;
    }

    public void setNodeTypeName(String nodeTypeName) {
        this.nodeTypeName = nodeTypeName;
    }
}
