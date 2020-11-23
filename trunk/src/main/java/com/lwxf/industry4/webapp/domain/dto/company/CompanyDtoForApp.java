package com.lwxf.industry4.webapp.domain.dto.company;

import com.lwxf.industry4.webapp.common.enums.company.CompanyGrade;
import com.lwxf.industry4.webapp.common.enums.company.CompanyStatus;
import com.lwxf.industry4.webapp.common.enums.company.CompanyType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@ApiModel(value="经销商公司模型",description="经销商公司模型")
public class CompanyDtoForApp  implements Serializable {
    private static final long serialVersionUID = 1L;
    @ApiModelProperty(value="公司id",name="id")
    private String id;//公司id
    @ApiModelProperty(value="公司名称",name="companyName")
    private String companyName;
    @ApiModelProperty(value="类型",name="type",required=true)
    private Integer type;
    @ApiModelProperty(value="类型名称",name="typeName")
    private String typeName;
    @ApiModelProperty(value="级别",name="grade",required=true)
    private Integer grade;
    @ApiModelProperty(value="级别名称",name="gradeName")
    private String gradeName;
    @ApiModelProperty(value="客户数",name="custCount")
    private String custCount;
    @ApiModelProperty(value="所在地区",name="area",required=true)
    private String areaName;
    @ApiModelProperty(value="所在地区(XX-XX-XX)",name="mergerName")
    private String mergerName;
    @ApiModelProperty(value="负责人",name="leaderName",required=true)
    private String leaderName;
    @ApiModelProperty(value="大区经理",name="businessManager",required=true)
    private String businessManagerName;
    @ApiModelProperty(value="签约时间",name="contractTime",required=true)
    private Date contractTime;
    @ApiModelProperty(value="状态",name="status")
    private Integer status;
    @ApiModelProperty(value="状态名称",name="statusName")
    private String statusName;
    @ApiModelProperty(value="负责人电话",name="dealerMobile")
    private String dealerMobile;
    @ApiModelProperty(value="经销商编号",name="no")
    private String no;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
    }

    public void setCustCount(String custCount) {
        this.custCount = custCount;
    }



    public String getLeaderName() {
        return leaderName;
    }

    public void setLeaderName(String leaderName) {
        this.leaderName = leaderName;
    }



    public Date getContractTime() {
        return contractTime;
    }

    public void setContractTime(Date contractTime) {
        this.contractTime = contractTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusName() {
        return CompanyStatus.getByValue(this.getStatus()).getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return CompanyType.getByValue(this.type).getName();
    }

    public String getGradeName() {
        String gradeName="";
        if(this.grade!=null){
            gradeName=CompanyGrade.getByValue(this.grade).getName();
        }
        return gradeName ;
    }

    public String getCustCount() {
        return "客户数:"+this.custCount;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getDealerMobile() {
        return dealerMobile;
    }

    public void setDealerMobile(String dealerMobile) {
        this.dealerMobile = dealerMobile;
    }

    public String getMergerName() {
        String merger=this.areaName;
        if(merger!=null&&!merger.equals("")){
            merger=merger.replaceAll(",","-");
        }
        return merger;
    }

    public void setMergerName(String mergerName) {
        this.mergerName = mergerName;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }
}
