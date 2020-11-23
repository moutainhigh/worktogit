package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/1/001 20:04
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProduceOrderPermit {
	NOT_ALLOW(0,"不允许"),
	ALLOW(1,"允许");


	private int value;
	private String name;

	ProduceOrderPermit(int value, String name) {
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
	public static ProduceOrderPermit getByValue(int value){
		ProduceOrderPermit allVaues[] = ProduceOrderPermit.values();
		ProduceOrderPermit status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
