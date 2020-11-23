package com.lwxf.industry4.webapp.common.enums.payment;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/23/023 15:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PaymentResourceType {

	ORDER(0,"订单"),
	OUTSOURCE(2,"外协单"),
	AFTERSALEAPPLY(1,"售后");

	private Integer value;
	private String name;


	PaymentResourceType(Integer value, String name) {
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

	public static PaymentResourceType getByValue(Integer value){
		PaymentResourceType allVaues[] = PaymentResourceType.values();
		PaymentResourceType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}

}
