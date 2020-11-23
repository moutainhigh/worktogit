package com.lwxf.industry4.webapp.common.enums.customorder;

import org.apache.lucene.search.similarities.Distribution;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/28 17:28
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum  CustomOrderLogStage {

    Marketing(0,"营销阶段（创建->设计确认（含设计确认）"),
    production(1,"生产阶段（财务审批->成品入库）"),
    Distribution(2,"配送阶段（发货 -> 确认收货）"),
    erection(3,"安装阶段（创建安装单 -> 安装完成）"),
    After(4,"售后阶段（补漏和物损等）");


    private int value;
    private String name;

    CustomOrderLogStage(int value, String name) {
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
    public static CustomOrderLogStage getByValue(int value){
        CustomOrderLogStage allVaues[] = CustomOrderLogStage.values();
        CustomOrderLogStage status;
        for (int i = 0,len = allVaues.length; i < len; i++) {
            status = allVaues[i];
            if (status.getValue()==value){
                return status;
            }
        }
        return null;
    }


}

