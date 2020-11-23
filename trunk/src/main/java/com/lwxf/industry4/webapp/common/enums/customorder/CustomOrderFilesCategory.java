package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/12 14:43
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CustomOrderFilesCategory {

    PICTURE(0,"平面图/图片"),
    THREED(1,"3D图"),
    VR(2,"VR图"),
    VIDEO(3,"视频"),
    ACCESSORY(4,"附件");


    private int value;
    private String name;

    CustomOrderFilesCategory(int value, String name) {
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
    public static CustomOrderFilesCategory getByValue(int value){
        CustomOrderFilesCategory allVaues[] = CustomOrderFilesCategory.values();
        CustomOrderFilesCategory status;
        for (int i = 0,len = allVaues.length; i < len; i++) {
            status = allVaues[i];
            if (status.getValue()==value){
                return status;
            }
        }
        return null;
    }
}
