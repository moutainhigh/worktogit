package com.lwxf.industry4.webapp.common.enums.activity;

public enum ActivityStatus {

    UN_PUBLISHED(0,"未发布"),
    PUBLISHED(1,"已发布"),
    END(2,"已结束");
    private Integer value;
    private String name;
    ActivityStatus(Integer value, String name) {
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

    public static ActivityStatus getByValue(int value) {
        ActivityStatus allVaues[] = ActivityStatus.values();
        ActivityStatus status;
        for (int i = 0, len = allVaues.length; i < len; i++) {
            status = allVaues[i];
            if (status.getValue() == value) {
                return status;
            }
        }
        return null;
    }
}
