package com.lwxf.industry4.webapp.common.enums.customorder;

import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
/**
 * 功能：订单催款枚举
 *
 * @author：DJL
 * @create：2020/1/6 10:37
 * @version：2020 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CustomOrderRemindStatus {

    TO_REMIND(0, "待催款"),
    REMINDING(1, "催款中"),
    END(2,"完成"),
    DRAFT(3, "草稿");

    private Integer value;
    private String name;

    CustomOrderRemindStatus(Integer value, String name){
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
        return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static OrderStatus getByValue(Integer value){
        OrderStatus allVaues[] = OrderStatus.values();
        OrderStatus status;
        for (int i=0,len = allVaues.length;i<len;i++){
            status = allVaues[i];
            if(status.getValue()==value){
                return status;
            }
        }
        return null;
    }
}
