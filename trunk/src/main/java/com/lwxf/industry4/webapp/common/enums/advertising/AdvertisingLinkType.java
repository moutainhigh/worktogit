package com.lwxf.industry4.webapp.common.enums.advertising;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/3/25 16:04
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum AdvertisingLinkType {

    COMMODITY(0,"商品"),
    ARTICLE(0,"文章"),
    ACTIVITY(0,"活动"),
    STORE(1,"店铺");


    private Integer value;
    private String name;

    AdvertisingLinkType(Integer value, String name){
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

    public static AdvertisingLinkType getByValue(Integer value){
        AdvertisingLinkType allVaues[] = AdvertisingLinkType.values();
        AdvertisingLinkType status;
        for (int i=0,len = allVaues.length;i<len;i++){
            status = allVaues[i];
            if(status.getValue()==value){
                return status;
            }
        }
        return null;
    }

}
