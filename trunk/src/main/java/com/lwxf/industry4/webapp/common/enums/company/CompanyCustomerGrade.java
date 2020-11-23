package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2018/12/7 0007 8:54
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CompanyCustomerGrade {

    LOW(0,"普通客户"),
	MEDIUM(1,"中等客户"),
	HIGH(2,"高端客户"),
	SUPER(3,"超级客户"),



	;
	private Integer value;
	private String name;

	CompanyCustomerGrade(Integer value, String name) {
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