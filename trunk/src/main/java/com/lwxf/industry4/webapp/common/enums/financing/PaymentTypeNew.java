package com.lwxf.industry4.webapp.common.enums.financing;

/**
 * 功能：
 *
 * @author：zhangxiaolin(F_baisi)
 * @create：2019/8/12/022 9:30
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PaymentTypeNew {

    INCOME(1,"收入"),
    OUTLAY(2,"支出"),
    CHARGEBACK(3,"扣款"),
    COOPERATE_PAY(4,"厂家外协支付");//厂家账户扣款

    private Integer value;
    private String name;

    PaymentTypeNew(Integer value, String name){
        this.value = value;
        this.name = name;
    }

    /**
     * 数据范围校验
     *
     * @param value
     * @return
     */
    public static boolean contains(Integer value) {
        return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
    }

    public Integer getValue() {
        return this.value;
    }

    public String getName(){
        return this.name;
    }

    public static PaymentTypeNew getByValue(Integer value){
        PaymentTypeNew allVaues[] = PaymentTypeNew.values();
        PaymentTypeNew status;
        for (int i=0,len = allVaues.length;i<len;i++){
            status = allVaues[i];
            if(status.getValue()==value){
                return status;
            }
        }
        return null;
    }
}
