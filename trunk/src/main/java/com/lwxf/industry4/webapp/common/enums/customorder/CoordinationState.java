package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/9/009 22:47
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CoordinationState {
	TO_QUOTED(0,"待报价"),
	TO_PAY(1,"待支付"),
	TO_RECEIVED(2,"待收货"),
	COMPLETE(3,"完成");


	private int value;
	private String name;

	CoordinationState(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	public static Boolean contains(int value){
		return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public static CoordinationState getByValue(int value){
		CoordinationState allVaues[] = CoordinationState.values();
		CoordinationState status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
