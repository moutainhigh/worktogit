package com.lwxf.industry4.webapp.common.enums.aftersale;

/**
 * 功能：售后来源
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/2 0002 20:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum AftersaleFrom {

	DEALER(0,"经销商"),
	FACTORY(1,"工厂"),
	;
	private Integer value;
	private String name;
	AftersaleFrom(Integer value, String name) {
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

	public static AftersaleFrom getByValue(int value) {
		AftersaleFrom allVaues[] = AftersaleFrom.values();
		AftersaleFrom status;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue() == value) {

				return status;
			}
		}
		return null;
	}
}
