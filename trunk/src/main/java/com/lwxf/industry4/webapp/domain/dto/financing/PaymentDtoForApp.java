package com.lwxf.industry4.webapp.domain.dto.financing;

import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentType;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="收支信息列表",description="收支信息列表")
public class PaymentDtoForApp {

    @ApiModelProperty(value="收支信息ID",name="id")
    private String id;
    @ApiModelProperty(value="创建时间",name="created")
    private Date created; //创建时间
    @ApiModelProperty(value="支付时间",name="pay_time")
    private Date pay_time;  //支付时间
    @ApiModelProperty(value="科目id",name="funds")
    private Integer funds;
    @ApiModelProperty(value="類型ID",name="type")
    private Integer type;
    @ApiModelProperty(value="類型ID",name="typeName")
    private String typeName;
    @ApiModelProperty(value="科目名称",name="fundsName")
    private String  fundsName;
    @ApiModelProperty(value="待审核金额",name="amount")
    private Double amount;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTypeName() {
        return PaymentTypeNew.getByValue(this.type)==null?"":PaymentTypeNew.getByValue(this.type).getName();
    }

    public String getFundsName() {
        return PaymentFunds.getByValue(this.funds)==null?"":PaymentFunds.getByValue(this.funds).getName();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getPay_time() {
        return pay_time;
    }

    public void setPay_time(Date pay_time) {
        this.pay_time = pay_time;
    }

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public Double getAmount() {
        return amount==null?0d:amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
