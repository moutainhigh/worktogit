package com.lwxf.industry4.webapp.domain.dto.company;

import com.lwxf.industry4.webapp.common.enums.company.CompanyGrade;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.company.CompanyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

@ApiModel(value="经销商公司详细信息",description="经销商公司详细信息")
public class CompanyInfoDtoForApp {
    private String id;//公司id
    @ApiModelProperty(value="公司名称",name="name")
    private String name;
    @ApiModelProperty(value="类型",name="type")
    private Integer type;
    private String typeName;
    private Integer grade;
    @ApiModelProperty(value="级别",name="grade")
    private String gradeName;
    @ApiModelProperty(value="客户数",name="custCount")
    private Integer custCount;
    @ApiModelProperty(value="订单数",name="orderCount")
    private Integer orderCount;
    @ApiModelProperty(value="对厂账户",name="balance")
    private Double balance;
    @ApiModelProperty(value="性质",name="nature")
    private Integer nature;
//    @ApiModelProperty(value="性质",name="nature")
//    private String natureName;
    private Integer status;
    @ApiModelProperty(value="状态",name="status")
    private String statusName;
    @ApiModelProperty(value="经度",name="lng")
    private Float lng;
    @ApiModelProperty(value="纬度",name="lat")
    private Float lat;
    @ApiModelProperty(value="负责人名称",name="leaderName")
    private String leaderName;
    @ApiModelProperty(value="负责人电话",name="leaderTel")
    private String leaderTel;
    @ApiModelProperty(value="业务经理",name="businessManagerName")
    private String businessManagerName;
    @ApiModelProperty(value="地区名称",name="areaName")
    private String areaName;
    @ApiModelProperty(value="详细地址",name="address")
    private String address;

    @ApiModelProperty(value="备注",name="note")
    private String note;

    @ApiModelProperty(value="签约时间",name="contractTime")
    private Date contractTime;
    @ApiModelProperty(value="过期时间",name="contractExpiredDate")
    private Date contractExpiredDate;

    @ApiModelProperty(value="图片url列表，查询时使用)",name="imgPathList")
    private List<String> imgPathList;

    public List<String> getImgPathList() {
        return imgPathList;
    }

    public void setImgPathList(List<String> imgPathList) {
        this.imgPathList = imgPathList;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public Date getContractExpiredDate() {
        return contractExpiredDate;
    }

    public void setContractExpiredDate(Date contractExpiredDate) {
        this.contractExpiredDate = contractExpiredDate;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getBusinessManagerName() {
        return businessManagerName;
    }

    public void setBusinessManagerName(String businessManagerName) {
        this.businessManagerName = businessManagerName;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getCustCount() {
        return custCount;
    }

    public void setCustCount(Integer custCount) {
        this.custCount = custCount;
    }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Integer getNature() {
        return nature;
    }

    public void setNature(Integer nature) {
        this.nature = nature;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }

    public String getLeaderTel() {
        return leaderTel;
    }

    public void setLeaderTel(String leaderTel) {
        this.leaderTel = leaderTel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getGradeName() {
        return  CompanyGrade.getByValue(this.grade).getName();
    }

    public String getStatusName() {
        return  CompanyStatus.getByValue(this.status).getName();
    }

    public String getTypeName() {
        return CompanyType.getByValue(this.type).getName();
    }
}
