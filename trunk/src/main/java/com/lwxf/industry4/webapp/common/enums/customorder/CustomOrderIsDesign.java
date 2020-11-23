package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/12/012 10:18
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CustomOrderIsDesign {

	NEED_DESIGN(1,"需要设计"),
	UNWANTED_DESIGN(0,"不需要设计");


	private int value;
	private String name;

	CustomOrderIsDesign(int value, String name) {
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
	public static CustomOrderIsDesign getByValue(int value){
		CustomOrderIsDesign allVaues[] = CustomOrderIsDesign.values();
		CustomOrderIsDesign status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}

}
