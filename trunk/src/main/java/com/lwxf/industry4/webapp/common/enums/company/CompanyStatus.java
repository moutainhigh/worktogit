package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/6/006 16:20
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CompanyStatus {
	INTENTION(0,"意向"),
	NORMAL(1,"签约(正常)"),
	RETIRED_NETWORK(2,"已退网"),
	CLOSED_DOWN(3,"已倒闭"),
	DISABLED(4,"已禁用"),
	WORTHLESS(5,"无价值"),
	PENDING_APPROVAL(6,"待审批"),
	TO_BE_ENABLED(7,"待激活");
	private int value;
	private String name;

	CompanyStatus(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	public static Boolean contains(int value){
		return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public static CompanyStatus getByValue(int value){
		CompanyStatus allVaues[] = CompanyStatus.values();
		CompanyStatus status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
