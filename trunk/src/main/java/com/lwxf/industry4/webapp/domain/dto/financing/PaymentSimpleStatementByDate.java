package com.lwxf.industry4.webapp.domain.dto.financing;

public class PaymentSimpleStatementByDate {
    private String date;
    private Double income;
    private Double outcome;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getIncome() {
        return income;
    }

    public void setIncome(Double income) {
        this.income = income;
    }

    public Double getOutcome() {
        return outcome;
    }

    public void setOutcome(Double outcome) {
        this.outcome = outcome;
    }
}
