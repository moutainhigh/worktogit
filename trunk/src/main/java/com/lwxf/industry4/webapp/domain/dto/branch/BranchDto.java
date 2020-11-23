package com.lwxf.industry4.webapp.domain.dto.branch;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lwxf.mybatis.annotation.Column;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.branch.Branch;

import java.sql.Types;
import java.util.Date;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/5/005 10:29
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "企业信息",description = "企业信息")
public class BranchDto extends Branch{
	@ApiModelProperty(value = "状态名称")
	private String statusName;
	@ApiModelProperty(value = "类型名称")
	private String typeName;
    @ApiModelProperty(value = "解密后的有效期至")
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date decodeExpireDate;
	@ApiModelProperty(value = "解密后的经销商账号启用数量")
	private Integer decodeDealerLoginNum;
	@ApiModelProperty(value = "解密后的经销商登录微信小程序账号启用数量")
	private Integer decodeDealerLoginWxNum;
	@ApiModelProperty(value = "解密后的经销商登录PC端账号启用数量")
	private Integer decodeDealerLoginPcNum;
	@ApiModelProperty(value = "解密后的工厂账号启用数量")
	private Integer decodeFactoryLoginNum;
	@ApiModelProperty(value = "解密后的工厂登录微信小程序账号启用数量")
	private Integer decodeFactoryLoginWxNum;
	@ApiModelProperty(value = "解密后的工厂登录PC端账号启用数量")
	private Integer decodeFactoryLoginPcNum;
    @ApiModelProperty(value = "已启用的经销商账号启用数量")
    private Integer countDealerLoginNum;
    @ApiModelProperty(value = "已启用的经销商登录微信小程序账号启用数量")
    private Integer countDealerLoginWxNum;
    @ApiModelProperty(value = "已启用的经销商登录PC端账号启用数量")
    private Integer countDealerLoginPcNum;
    @ApiModelProperty(value = "已启用的工厂账号启用数量")
    private Integer countFactoryLoginNum;
    @ApiModelProperty(value = "已启用的工厂登录微信小程序账号启用数量")
    private Integer countFactoryLoginWxNum;
    @ApiModelProperty(value = "已启用的工厂登录PC端账号启用数量")
    private Integer countFactoryLoginPcNum;
    @ApiModelProperty(value = "发货计划审核人员roleID")
    private String auditDeliverRoleId;

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Date getDecodeExpireDate() {
        return decodeExpireDate;
    }

    public void setDecodeExpireDate(Date decodeExpireDate) {
        this.decodeExpireDate = decodeExpireDate;
    }

    public Integer getDecodeDealerLoginNum() {
        return decodeDealerLoginNum;
    }

    public void setDecodeDealerLoginNum(Integer decodeDealerLoginNum) {
        this.decodeDealerLoginNum = decodeDealerLoginNum;
    }

    public Integer getDecodeDealerLoginWxNum() {
        return decodeDealerLoginWxNum;
    }

    public void setDecodeDealerLoginWxNum(Integer decodeDealerLoginWxNum) {
        this.decodeDealerLoginWxNum = decodeDealerLoginWxNum;
    }

    public Integer getDecodeDealerLoginPcNum() {
        return decodeDealerLoginPcNum;
    }

    public void setDecodeDealerLoginPcNum(Integer decodeDealerLoginPcNum) {
        this.decodeDealerLoginPcNum = decodeDealerLoginPcNum;
    }

    public Integer getDecodeFactoryLoginNum() {
        return decodeFactoryLoginNum;
    }

    public void setDecodeFactoryLoginNum(Integer decodeFactoryLoginNum) {
        this.decodeFactoryLoginNum = decodeFactoryLoginNum;
    }

    public Integer getDecodeFactoryLoginWxNum() {
        return decodeFactoryLoginWxNum;
    }

    public void setDecodeFactoryLoginWxNum(Integer decodeFactoryLoginWxNum) {
        this.decodeFactoryLoginWxNum = decodeFactoryLoginWxNum;
    }

    public Integer getDecodeFactoryLoginPcNum() {
        return decodeFactoryLoginPcNum;
    }

    public void setDecodeFactoryLoginPcNum(Integer decodeFactoryLoginPcNum) {
        this.decodeFactoryLoginPcNum = decodeFactoryLoginPcNum;
    }

    public Integer getCountDealerLoginNum() {
        return countDealerLoginNum;
    }

    public void setCountDealerLoginNum(Integer countDealerLoginNum) {
        this.countDealerLoginNum = countDealerLoginNum;
    }

    public Integer getCountDealerLoginWxNum() {
        return countDealerLoginWxNum;
    }

    public void setCountDealerLoginWxNum(Integer countDealerLoginWxNum) {
        this.countDealerLoginWxNum = countDealerLoginWxNum;
    }

    public Integer getCountDealerLoginPcNum() {
        return countDealerLoginPcNum;
    }

    public void setCountDealerLoginPcNum(Integer countDealerLoginPcNum) {
        this.countDealerLoginPcNum = countDealerLoginPcNum;
    }

    public Integer getCountFactoryLoginNum() {
        return countFactoryLoginNum;
    }

    public void setCountFactoryLoginNum(Integer countFactoryLoginNum) {
        this.countFactoryLoginNum = countFactoryLoginNum;
    }

    public Integer getCountFactoryLoginWxNum() {
        return countFactoryLoginWxNum;
    }

    public void setCountFactoryLoginWxNum(Integer countFactoryLoginWxNum) {
        this.countFactoryLoginWxNum = countFactoryLoginWxNum;
    }

    public Integer getCountFactoryLoginPcNum() {
        return countFactoryLoginPcNum;
    }

    public void setCountFactoryLoginPcNum(Integer countFactoryLoginPcNum) {
        this.countFactoryLoginPcNum = countFactoryLoginPcNum;
    }

    public String getAuditDeliverRoleId() {
        return auditDeliverRoleId;
    }

    public void setAuditDeliverRoleId(String auditDeliverRoleId) {
        this.auditDeliverRoleId = auditDeliverRoleId;
    }
}
