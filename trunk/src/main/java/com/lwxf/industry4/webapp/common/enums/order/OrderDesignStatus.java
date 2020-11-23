package com.lwxf.industry4.webapp.common.enums.order;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/18 19:40
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum OrderDesignStatus {

    BEINGDESIGNED(0,"设计中"),
    TOAUDIT(1,"待审核"),
    AUDITED(2,"已审核");


    private Integer value;
    private String name;

    OrderDesignStatus(Integer value, String name) {
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
        return null == getByValue(value) ? Boolean.FALSE.booleanValue() : Boolean.TRUE.booleanValue();
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName() {
        return this.name;
    }

    public static OrderDesignStatus getByValue(Integer value) {
        OrderDesignStatus allVaues[] = OrderDesignStatus.values();
        OrderDesignStatus status;
        for (int i = 0, len = allVaues.length; i < len; i++) {
            status = allVaues[i];
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
