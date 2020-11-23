package com.lwxf.industry4.webapp.domain.dto.statement;

import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;

public class PaymentCountByFunds {
    private Double amount;
    private String fundsName;
    private Integer count;
    private Integer funds;

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

    public String getFundsName() {
        return PaymentFunds.getByValue(this.funds).getName();
    }

    public void setFundsName(String fundsName) {
        this.fundsName = fundsName;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }
}
