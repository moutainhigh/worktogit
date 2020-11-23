package com.lwxf.industry4.webapp.domain.dto.company;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
@ApiModel(value="经销商财务添加",description="经销商财务添加")
public class CompanyPaymentInfoDto {

    @ApiModelProperty(value="充值公司ID",name="CompanyId")
    private String CompanyId;
    @ApiModelProperty(value="充值公司ID",name="paymentId")
    private String paymentId;
    @ApiModelProperty(value="充值公司ID",name="CompanyName")
    private String CompanyName;
    @ApiModelProperty(value="科目",name="funds")
    private Integer funds;
    @ApiModelProperty(value="科目名称",name="fundsName")
    private String fundsName;
    @ApiModelProperty(value="金额",name="amount")
    private BigDecimal amount;
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    @ApiModelProperty(value="记账日期",name="pay_time")
    private Date pay_time;
    @ApiModelProperty(value="支付说明/备注",name="notes")
    private String notes;
    @ApiModelProperty(value="上传附件id",name="fileIds")
    private String fileIds;
    @ApiModelProperty(value="支付方式",name="way")
    private Integer way;
    @ApiModelProperty(value="支付方式名称",name="wayName")
    private String wayName;
    @ApiModelProperty(value="类型:1:转入,2:转出",name="type")
    private Integer type;
    @ApiModelProperty(value="类型名称",name="typeName")
    private String typeName;
    @ApiModelProperty(value="记账人ID",name="holder")
    private String holder; //记账人
    @ApiModelProperty(value="银行",name="bank")
    private String bank; //银行

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    @ApiModelProperty(value="创建时间",name="created")
    private String created; //记账人

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public void setFundsName(String fundsName) {
        this.fundsName = fundsName;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }

    public String getFundsName() {
        return PaymentFunds.getByValue(this.getFunds()).getName();
    }

    public String getWayName() {
        return PaymentWay.getByValue(this.way).getName();
    }

    public String getTypeName() {
        return this.getType()==1?"收入":"支出";
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }
}
