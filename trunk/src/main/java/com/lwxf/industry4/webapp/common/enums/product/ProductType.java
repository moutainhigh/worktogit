package com.lwxf.industry4.webapp.common.enums.product;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/2/23 14:22
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProductType {

    DOOR_PLANK(0,"门板"),
    CABINET_PLANK(1,"柜体板"),
    HANDLE(2,"拉手"),
    CONSUMABLES(3,"消耗品/办公用品"),
    RESTS(4,"其他");

    private Integer value;
    private String name;


    ProductType(Integer value, String name) {
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

    public static ProductType getByValue(Integer value){
        ProductType allVaues[] = ProductType.values();
        ProductType status;
        for (int i=0,len = allVaues.length;i<len;i++){
            status = allVaues[i];
            if(status.getValue()==value.byteValue()){
                return status;
            }
        }
        return null;
    }

}
