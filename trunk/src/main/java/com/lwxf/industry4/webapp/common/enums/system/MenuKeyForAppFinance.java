package com.lwxf.industry4.webapp.common.enums.system;

public enum MenuKeyForAppFinance {

    VIEW_COMPANYFINANCE("financingmng_dealer","经销商财务管理"),
    VIEW_AUDIT("financingmng_order","经销商财务审核"),
    VIEW_PAYMENTSIMPLE("financingmng_DailyAccount","客户管理");

    private String value;
    private String name;


    MenuKeyForAppFinance(String value, String name) {
        this.value = value;
        this.name = name;
    }

    /**
     * 数据范围校验
     *
     * @param value
     * @return
     */
    public static boolean contains(Integer value) {
        return null == value?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
    }
    public String getValue() {
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static MenuKeyForAppFinance getByValue(String value){
        MenuKeyForAppFinance allVaues[] = MenuKeyForAppFinance.values();
        MenuKeyForAppFinance menusKey;
        for (int i=0,len = allVaues.length;i<len;i++){
            menusKey = allVaues[i];
            if(menusKey.getValue().equals(value)){
                return menusKey;
            }
        }
        return null;
    }

}
