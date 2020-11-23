package com.lwxf.industry4.webapp.common.enums.LogisticsCompany;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/8/1 0001 15:13
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum LogisticsCompanyStatus {
	NOT_ENABLED(0,"未启用"),
	NORMAL(1,"正常"),
	DISABLED(2,"禁用"),
	CLOSE_DOWN(3,"倒闭"),
	;
	private Integer value;
	private String name;

	LogisticsCompanyStatus(Integer value, String name) {
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

	public static LogisticsCompanyStatus getByValue(int value) {
		LogisticsCompanyStatus allVaues[] = LogisticsCompanyStatus.values();
		LogisticsCompanyStatus grade;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			grade = allVaues[i];
			if (grade.getValue() == value) {

				return grade;
			}
		}
		return null;
	}
}
