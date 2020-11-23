package com.lwxf.industry4.webapp.domain.dto.company;

import com.lwxf.industry4.webapp.common.enums.company.DealerAccountType;

/**
 * 该dto用于 F端经销商财务信息页面中间账户统计部分
 */
public class CompanyAccountDto {
    private String typeName;
    private Integer type;
    private Double balance;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public String getTypeName() {
        return DealerAccountType.getByValue(this.getType())==null?"":DealerAccountType.getByValue(this.getType()).getName();
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
