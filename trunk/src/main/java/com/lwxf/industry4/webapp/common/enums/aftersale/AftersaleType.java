package com.lwxf.industry4.webapp.common.enums.aftersale;

import com.lwxf.industry4.webapp.common.enums.dispatch.DispatchBillStatus;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/2 0002 20:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum AftersaleType {

	LOUFA(0,"漏发补货"),
	SUNHUAI(1,"损坏补货"),
	CUOFA(2,"错发补货"),
	QITA(3,"其他"),
	FANKUI(4,"反馈单"),
	BULIAO(5,"补料单"),
	;
	private Integer value;
	private String name;
	AftersaleType(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static Boolean contains(int value) {
		return null == getByValue(value) ? Boolean.FALSE.booleanValue() : Boolean.TRUE.booleanValue();
	}

	public static AftersaleType getByValue(int value) {
		AftersaleType allVaues[] = AftersaleType.values();
		AftersaleType type;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			type = allVaues[i];
			if (type.getValue() == value) {

				return type;
			}
		}
		return null;
	}
}
