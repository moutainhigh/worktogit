package com.lwxf.industry4.webapp.common.enums.financing;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/5/005 13:27
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PaymentStatus {

	PENDING_PAYMENT(0,"待付款"),
	PAID(1,"已付款"),
	ABOLISH(2,"已废除"),
	ON_CREDIT(3,"挂账"),
	;

	private Integer value;
	private String name;

	PaymentStatus(Integer value, String name){
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

	public static PaymentStatus getByValue(Integer value){
		PaymentStatus allVaues[] = PaymentStatus.values();
		PaymentStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
