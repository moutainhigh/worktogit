package com.lwxf.industry4.webapp.common.enums.headline;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/13 10:39
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum IsPublished {
    publish(1,"发布"),
    unpublished(0,"不发布");


    private int value;
    private String name;

    IsPublished(int value, String name) {
        this.value = value;
        this.name = name;
    }


    public int getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
