package com.lwxf.industry4.webapp.domain.dto.financing;

import com.lwxf.industry4.webapp.common.enums.financing.PaymentSimpleFunds;

public class PaymentSimpleStatementByFunds {
    private Integer funds;
    private String fundsName;
    private Double amount;

    public Integer getFunds() {
        return funds;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public String getFundsName() {
        return PaymentSimpleFunds.getByValue(this.funds)==null?"":PaymentSimpleFunds.getByValue(this.funds).getName();
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
