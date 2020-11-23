package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/7/007 16:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CustomOrderFilesStatus {
	TEMP(0,"临时"),
	FORMAL(1,"正式"),
	DELETE(2,"删除");
	private int value;
	private String name;
	CustomOrderFilesStatus(int value,String name){
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}
}
