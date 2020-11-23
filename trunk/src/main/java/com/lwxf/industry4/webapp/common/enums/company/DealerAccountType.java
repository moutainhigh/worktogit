package com.lwxf.industry4.webapp.common.enums.company;

public enum DealerAccountType {
    FREE_ACCOUNT(1,"自由账户"),
    FREEZE_ACCOUNT(2,"设计金"),
    DEPOSIT_ACCOUNT(3,"保证金"),
    SOFTWARE_ACCOUNT(4,"软件年金"),
    ACTIVITY_ACCOUNT(5,"活动款");
    private int value;
    private String name;

    DealerAccountType(int value, String name) {
        this.value = value;
        this.name = name;
    }

    public int getValue() {
        return value;
    }


    public String getName() {
        return name;
    }

    /**
     * 验证数据有效性
     * @param value
     * @return
     */
    public static boolean contains(int value){
        return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
    }
    public static DealerAccountType getByValue(int value){
        DealerAccountType allVaues[] = DealerAccountType.values();
        DealerAccountType type;
        for (int i = 0,len=allVaues.length; i <len ; i++) {
            type = allVaues[i];
            if(type.getValue()==value){
                return type;
            }
        }
        return null;
    }
}
