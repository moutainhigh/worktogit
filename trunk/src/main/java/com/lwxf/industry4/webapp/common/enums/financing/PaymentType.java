package com.lwxf.industry4.webapp.common.enums.financing;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/22/022 9:30
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PaymentType {

	//C_TO_B(0,"客户对经销商"),
	//B_TO_C(1,"经销商对客户(常指退款)"),
	B_TO_F(1,"收入"), //厂家收入
	F_TO_B(2,"支出"), //厂家支出
	B_TO_F_WITHHOLD(3,"厂家对经销商扣款"), //厂家扣款（抵扣，非支出和收入）
	F_TO_COORDINATION(4,"厂家外协支付");//厂家账户扣款

	private Integer value;
	private String name;

	PaymentType(Integer value, String name){
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

	public static PaymentType getByValue(Integer value){
		PaymentType allVaues[] = PaymentType.values();
		PaymentType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
