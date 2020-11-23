package com.lwxf.industry4.webapp.common.enums.order;

public enum OrderStage {

    LEAFLET(0,"传单"),
    RECEIPT(1,"接单"),
    PLACE_AN_ORDER(2,"下单"),
    PRESS_FOR_MONEY(3,"催款"),
    TO_EXAMINE(4,"审核"),
    PRODUCTION(5,"生产"),
    WAREHOUSING(6,"入库"),
    DELIVERY(7,"发货"),
    END(8,"完成"),

    ;

    private Integer value;
    private String name;


    OrderStage(Integer value, String name) {
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
    public Integer getValue() {
        return this.value;
    }

    public String getName(){
        return this.name;
    }

}
