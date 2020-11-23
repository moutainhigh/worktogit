package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/12/012 14:11
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CustomOrderCoordination {
	UNWANTED_COORDINATION(false,"不需要外协"),
	NEED_COORDINATION(true,"需要外协");


	private Boolean value;
	private String name;

	CustomOrderCoordination(Boolean value, String name) {
		this.value = value;
		this.name = name;
	}

	public Boolean getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	public static Boolean contains(Boolean value){
		return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public static CustomOrderCoordination getByValue(Boolean value){
		CustomOrderCoordination allVaues[] = CustomOrderCoordination.values();
		CustomOrderCoordination status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}

}
