package com.lwxf.industry4.webapp.common.enums.activity;

public enum ActivityType {

    COMPANY(1,"经销商"),
    FACTORY(0,"厂家")
    ;
    private Integer value;
    private String name;
    ActivityType(Integer value, String name) {
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

    public static ActivityType getByValue(int value) {
        ActivityType allVaues[] = ActivityType.values();
        ActivityType type;
        for (int i = 0, len = allVaues.length; i < len; i++) {
            type = allVaues[i];
            if (type.getValue() == value) {
                return type;
            }
        }
        return null;
    }
}
