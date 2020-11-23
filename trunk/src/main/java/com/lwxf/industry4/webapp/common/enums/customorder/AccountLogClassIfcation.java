package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/9/009 15:08
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum AccountLogClassIfcation {

	F_B(0,"厂家对经销商"),
	B_C(1,"经销商对终端");


	private int value;
	private String name;

	AccountLogClassIfcation(int value, String name) {
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
	public static AccountLogClassIfcation getByValue(int value){
		AccountLogClassIfcation allVaues[] = AccountLogClassIfcation.values();
		AccountLogClassIfcation status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
