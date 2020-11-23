package com.lwxf.industry4.webapp.common.enums.user;

import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerStatus;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/11 0011 9:51
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum UserScheduleItemType {
	PERSONAL(0,"个人日程"),
	SYSTEM(1,"系统消息"),
	;
	private Integer value;
	private String name;

	UserScheduleItemType(Integer value, String name) {
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

	public static UserScheduleItemType getByValue(int value) {
		UserScheduleItemType allVaues[] = UserScheduleItemType.values();
		UserScheduleItemType type;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			type = allVaues[i];
			if (type.getValue() == value) {
				return type;
			}
		}
		return null;
	}
}
