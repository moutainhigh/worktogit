package com.lwxf.industry4.webapp.common.enums.dispatch;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/23/023 16:09
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DispatchBillItemStatus {
	ARRIVAL(0,"到货"),
	DEFECT(1,"缺失"),
	DAMAGE(2,"损坏");
	private Integer value;
	private String name;

	DispatchBillItemStatus(Integer value, String name) {
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

	public static DispatchBillItemStatus getByValue(int value) {
		DispatchBillItemStatus allVaues[] = DispatchBillItemStatus.values();
		DispatchBillItemStatus grade;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			grade = allVaues[i];
			if (grade.getValue() == value) {

				return grade;
			}
		}
		return null;
	}
}
