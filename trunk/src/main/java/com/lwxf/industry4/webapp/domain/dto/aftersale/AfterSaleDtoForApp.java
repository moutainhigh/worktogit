package com.lwxf.industry4.webapp.domain.dto.aftersale;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value="F端后台售后管理页面",description="售后管理页面")
public class AfterSaleDtoForApp {
    @ApiModelProperty(value="订单编号",name="orderNo",example = "")
    private String orderNo;
    @ApiModelProperty(value="订单类型id",name="type",example = "")
    private String type;
    @ApiModelProperty(value="订单类型名称",name="typeName",example = "")
    private String typeName;
    @ApiModelProperty(value="创建时间",name="created",example = "")
    private Date created;
    @ApiModelProperty(value="创建人",name="creator",example = "")
    private String creator;
    @ApiModelProperty(value="问题描述",name="information",example = "")
    private String information; //问题描述
    @ApiModelProperty(value="意见",name="result",example = "")
    private String result; //意见
    @ApiModelProperty(value="附件列表",name="files",example = "")
    private List<String> files;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
