package com.lwxf.industry4.webapp.common.enums.advertising;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/3 14:09
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum AdvertisingPosition {
    HOME_CAROUSEL(0,"首页轮播"),
    HOME_TOP(1,"首页上部"),
    HOME_MIDDLE(2,"首页中部"),
    HOME_DOWN(3,"首页下部");


    private Integer value;
    private String name;


    AdvertisingPosition(Integer value,String name){
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

    public static AdvertisingPosition getByValue(Integer value){
        AdvertisingPosition allVaues[] = AdvertisingPosition.values();
        AdvertisingPosition status;
        for (int i=0,len = allVaues.length;i<len;i++){
            status = allVaues[i];
            if(status.getValue()==value){
                return status;
            }
        }
        return null;
    }


}
