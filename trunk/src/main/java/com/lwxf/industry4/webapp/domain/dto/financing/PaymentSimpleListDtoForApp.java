package com.lwxf.industry4.webapp.domain.dto.financing;

import com.lwxf.industry4.webapp.common.constant.BizConstant;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentSimpleFunds;

import java.util.Date;

public class PaymentSimpleListDtoForApp {

    private String id;
    private Date created; //创建时间
    private Date pay_time;  //支付时间
    private Integer funds;
    private Double amount;
    private Integer type;
    private String fundsName;
    private String typeName;

    public String getTypeName() {
        if(this.getType()==1){
            return BizConstant.PAYMENT_SIMPLE_TYPE_1;
        }else{
            return BizConstant.PAYMENT_SIMPLE_TYPE_2;
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getFundsName() {
        return PaymentSimpleFunds.getByValue(this.funds)==null?"":PaymentSimpleFunds.getByValue(this.funds).getName();
    }

    public void setFundsName(String fundsName) {
        this.fundsName = fundsName;
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
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
