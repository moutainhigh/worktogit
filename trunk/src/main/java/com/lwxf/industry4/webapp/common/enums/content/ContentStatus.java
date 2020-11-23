package com.lwxf.industry4.webapp.common.enums.content;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/4/28 16:06
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ContentStatus {

    DRAFT(0,"草稿"),
    ISSUE(1,"发布"),
    UNPUBLISH(2,"取消发布");
    private Integer value;
    private String name;
    ContentStatus(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
    public Integer getValue() {
        return value;
    }

    public String getName() {
        return name;
    }

    public static Boolean contains(int value) {
        return null == getByValue(value) ? Boolean.FALSE.booleanValue() : Boolean.TRUE.booleanValue();
    }

    public static ContentStatus getByValue(int value) {
        ContentStatus allVaues[] = ContentStatus.values();
        ContentStatus status;
        for (int i = 0, len = allVaues.length; i < len; i++) {
            status = allVaues[i];
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
