package com.lwxf.industry4.webapp.common.enums.order;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/6/006 11:29
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum OrderAccountLogType {
	DESIGN(0,"设计费"),
	CARGO(1,"货款");

	private int value;
	private String name;
	OrderAccountLogType(int value,String name) {
		this.value=value;
		this.name=name;
	}

	public int getValue() {
		return value;
	}
	public static boolean validValue(int value){
		OrderAccountLogType [] arr = OrderAccountLogType.values();
		for (int m=0,len = arr.length;m<len;m++){
			if(arr[m].value == value){
				return true;
			}
		}
		return false;
	}
}
