package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2018/12/6 0006 13:20
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CompanyCustomerStatus {

	CREATE(0, "新建"),
	FOLLOWUP(1,"跟进中"),
	ORDER(2,"已下单"),
	DEAD(3,"已流逝"),
	;

	private Integer value;
	private String name;

	CompanyCustomerStatus(Integer value, String name) {
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

	public static CompanyCustomerStatus getByValue(int value) {
		CompanyCustomerStatus allVaues[] = CompanyCustomerStatus.values();
		CompanyCustomerStatus status;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}

}