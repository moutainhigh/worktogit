package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-03-27 10:26
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DealerAccountLogType {
    OFFLINE_RECHARGE(0,"线下充值"),
    ONLINE_RECHARGE(1,"线上充值"),
    ORDER_DEDUCTION(2,"订单扣款"),
    DESIGN_DEDUCTION(3,"设计扣款"),
    CASH_WITHDRAWAL(4,"提现"),
    TO_CHANGE_INTO(5,"转入"),
    TO_CHANGE_OUT(6,"转出"),
    ORDER_PAYMENT_WITHHOLDING(7,"订单货款代扣");
    private int value;
    private String name;

    DealerAccountLogType(int value, String name) {
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
    public static DealerAccountLogType getByValue(int value){
        DealerAccountLogType allVaues[] = DealerAccountLogType.values();
        DealerAccountLogType type;
        for (int i = 0,len=allVaues.length; i <len ; i++) {
            type = allVaues[i];
            if(type.getValue()==value){
                return type;
            }
        }
        return null;
    }
}
