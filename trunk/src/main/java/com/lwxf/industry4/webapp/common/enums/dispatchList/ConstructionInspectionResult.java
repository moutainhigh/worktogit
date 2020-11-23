package com.lwxf.industry4.webapp.common.enums.dispatchList;

import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerGrade;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2018/12/26 0026 19:52
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ConstructionInspectionResult {
	INCOMPETENT(0,"不合格"),
	QUALIFIED(1,"合格"),
	GOOD(2,"良好"),
	EXCELLENT(3,"优秀"),

	;
	private Integer value;
	private String name;

	ConstructionInspectionResult(Integer value, String name) {
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

	public static CompanyCustomerGrade getByValue(int value) {
		CompanyCustomerGrade allVaues[] = CompanyCustomerGrade.values();
		CompanyCustomerGrade grade;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			grade = allVaues[i];
			if (grade.getValue() == value) {

				return grade;
			}
		}
		return null;
	}
}
