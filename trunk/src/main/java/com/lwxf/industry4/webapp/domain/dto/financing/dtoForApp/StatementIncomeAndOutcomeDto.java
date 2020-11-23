package com.lwxf.industry4.webapp.domain.dto.financing.dtoForApp;

public class StatementIncomeAndOutcomeDto {
    private double income;
    private double outcome;
    private String day;

    public double getIncome() {
        return income;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getOutcome() {
        return outcome;
    }

    public void setOutcome(double outcome) {
        this.outcome = outcome;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
