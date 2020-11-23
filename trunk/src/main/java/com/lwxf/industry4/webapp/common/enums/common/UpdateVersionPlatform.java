package com.lwxf.industry4.webapp.common.enums.common;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/13/013 14:35
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum UpdateVersionPlatform {

	IOS(0,"IOS"),
	ANDROID(1,"android"),
	PC(2,"PC"),



	;
	private Integer value;
	private String name;

	UpdateVersionPlatform(Integer value, String name) {
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

	public static UpdateVersionPlatform getByValue(int value) {
		UpdateVersionPlatform allVaues[] = UpdateVersionPlatform.values();
		UpdateVersionPlatform grade;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			grade = allVaues[i];
			if (grade.getValue() == value) {

				return grade;
			}
		}
		return null;
	}
}
