package com.lwxf.industry4.webapp.common.enums.activity;

public enum ActivityTarget {

    COMPANY(0,"经销商"),
    Client(1,"终端客户");
    private Integer value;
    private String name;
    ActivityTarget(Integer value, String name) {
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

    public static ActivityTarget getByValue(int value) {
        ActivityTarget allVaues[] = ActivityTarget.values();
        ActivityTarget target;
        for (int i = 0, len = allVaues.length; i < len; i++) {
            target = allVaues[i];
            if (target.getValue() == value) {
                return target;
            }
        }
        return null;
    }
}
