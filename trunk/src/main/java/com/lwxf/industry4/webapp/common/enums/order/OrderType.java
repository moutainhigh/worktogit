package com.lwxf.industry4.webapp.common.enums.order;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/11 16:45
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum OrderType {

    NORMALORDER(0, "正产订单"),
    SUPPLEMENTORDER(1, "补产订单"),
    RETURNORDER(2, "返货订单"),
    SAMPLINGORDER(3, "打样订单"),
    PLANKORDER(4, "样板订单"),
    EXHIBITIONORDER(5, "展厅订单"),
    REPLACEMENT(6,"补发订单"),
    APPENDORDER(7,"追加订单"),
    ENGINEERINGORDER(8,"工程订单"),
    ;


    private Integer value;
    private String name;

    OrderType(Integer value, String name) {
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

    public static OrderType getByValue(Integer value) {
        OrderType allVaues[] = OrderType.values();
        OrderType status;
        for (int i = 0, len = allVaues.length; i < len; i++) {
            status = allVaues[i];
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
