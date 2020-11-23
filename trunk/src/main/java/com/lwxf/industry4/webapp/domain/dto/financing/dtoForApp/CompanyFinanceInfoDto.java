package com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp;

import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentType;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

/**
 * F端app财务-经销商财务信息详情
 * created by zhangxiaolin
 */
@ApiModel(value="经销商财务信息详情",description="经销商财务信息详情")
public class CompanyFinanceInfoDto {

    @ApiModelProperty(value="创建时间",name="创建时间")
    private Date created;
    @ApiModelProperty(value="经销商名称",name="经销商名称")
    private String companyName;
    @ApiModelProperty(value="类型编码",name="1:收入,2:支出")
    private Integer type;
    @ApiModelProperty(value="支付方式编码",name="支付方式编码")
    private Integer way;
    @ApiModelProperty(value="支付方式名称",name="支付方式名称")
    private String wayName;
    @ApiModelProperty(value="类型名称",name="")
    private String typeName;
    @ApiModelProperty(value="科目编码",name="科目编码")
    private Integer funds;
    @ApiModelProperty(value="总金额",name="总金额")
    private Double amount;
    @ApiModelProperty(value="备注",name="备注")
    private String notes;
    @ApiModelProperty(value="记账人",name="记账人")
    private String name;
    @ApiModelProperty(value="科目名称",name="科目名称")
    private String fundsName;
    @ApiModelProperty(value="附件列表",name="附件列表")
    private List<String> fileList;
    @ApiModelProperty(value="收款人",name="收款人")
    private String holder;
    @ApiModelProperty(value="收款人名称",name="收款人名称")
    private String holderName;

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public String getWayName() {
        return PaymentWay.getByValue(this.way)==null?"":PaymentWay.getByValue(this.way).getName();
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return PaymentTypeNew.getByValue(this.type)==null?"":PaymentType.getByValue(this.type).getName();
    }

    public Integer getFunds() {
        return funds;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getFundsName() {
        return PaymentFunds.getByValue(this.getFunds())==null?"":PaymentFunds.getByValue(this.getFunds()).getName();
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public List<String> getFileList() {
        return fileList;
    }

    public void setFileList(List<String> fileList) {
        this.fileList = fileList;
    }
}
