package com.lwxf.industry4.webapp.common.aop.syslog;

public enum OperationMoudule {
    /**
     * 被操作的单元
     */
    UNKNOWN("unknown","不明"),
    USER("user","用户"),
    SUPPLIER("supplier","供应商"),
    SUPPLIER_PRODUCT("supplier_product","供应商产品"),
    COMPANY("company","经销商公司"),
    COMPANY_FINANCE("payment","经销商财务"),
    PAYMENT_SIMPLE("payment_simple","日常账"),
    Redis("redis","redis"),
    CUSTOM_ORDER("custom_order","订单"),
    CORPORATE_ORDER("corporate_order","外协订单"),
    FINANCE("finance","审核"),
    DESIGN("design","设计"),
    PRODUCE("produce","生产单"),
    FINISHEDSTOCKITEM("finished_stock_item","成品"),
    DISPATCH("dispatch","发货"),
    AFTERSALE("aftersale_apply","售后单"),
    CONTENTS("contents","内容"),
    MATERIAL("material","原材料"),
    CUSTOMER("company_customer","终端客户"),
    CORPORATE_PARTNERS("corporate_partners","外协厂家");

    private String value;
    private String mouduleName;

    OperationMoudule(String value,String mouduleName) {
        this.value = value;
        this.mouduleName = mouduleName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getMouduleName() {
        return mouduleName;
    }

    public void setMouduleName(String mouduleName) {
        this.mouduleName = mouduleName;
    }
}
