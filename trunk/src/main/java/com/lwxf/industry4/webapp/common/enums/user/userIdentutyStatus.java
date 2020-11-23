package com.lwxf.industry4.webapp.common.enums.user;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/10/11 13:11
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum userIdentutyStatus {
    TOAUDIT(0,"待审核"),
    AUDITFAILED(1,"审核未通过"),
    VERIFIED(2,"正常使用"),
    DISABLED(3,"已禁用");


    private int value;
    private String name;

    userIdentutyStatus(int value,String name){
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
