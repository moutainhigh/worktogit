package com.lwxf.industry4.webapp.common.enums.activity;

public enum ActivityClassify {

    NO_PARAMS(0,"海报"),
    HAS_PARAMS(1,"活动");
    private Integer value;
    private String name;
    ActivityClassify(Integer value, String name) {
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

    public static ActivityClassify getByValue(int value) {
        ActivityClassify allVaues[] = ActivityClassify.values();
        ActivityClassify classify;
        for (int i = 0, len = allVaues.length; i < len; i++) {
            classify = allVaues[i];
            if (classify.getValue() == value) {
                return classify;
            }
        }
        return null;
    }
}
