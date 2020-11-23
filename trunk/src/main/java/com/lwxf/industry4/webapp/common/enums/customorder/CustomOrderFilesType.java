package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/6 14:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CustomOrderFilesType {

    DEMAND(0,"订单需求"),
    DESIGN(1,"设计图"),
    CONTRACT(2,"合同附件"),
    ORDER_PRODUCT(3,"订单产品"),
    PRODUCE_ORDER(4,"生产拆单"),
    PRODUCE_FLOW(5,"生产流程"),
    ORDER_PACKAGE(6,"订单包裹"),
    DESIGN_SKETCH(7,"效果图"),
    INSTALLATION_DIAGRAM(8,"安装图"),
    ;


    private int value;
    private String name;

    CustomOrderFilesType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
    public static Boolean contains(int value){
        return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
    }
    public static CustomOrderFilesType getByValue(int value){
        CustomOrderFilesType allVaues[] = CustomOrderFilesType.values();
        CustomOrderFilesType status;
        for (int i = 0,len = allVaues.length; i < len; i++) {
            status = allVaues[i];
            if (status.getValue()==value){
                return status;
            }
        }
        return null;
    }

}
