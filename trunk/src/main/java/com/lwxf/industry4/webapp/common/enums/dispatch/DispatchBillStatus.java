package com.lwxf.industry4.webapp.common.enums.dispatch;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/25/025 18:30
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DispatchBillStatus {
	WAITING_FOR_PIECES(0,"待取件"),
	TRANSPORT(1,"运输中"),
	WAITING_FOR_DELIVERY(2,"待取货"),
	RECEIVED_GOODS(3,"已收货");
	private Integer value;
	private String name;

	DispatchBillStatus(Integer value, String name) {
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

	public static DispatchBillStatus getByValue(int value) {
		DispatchBillStatus allVaues[] = DispatchBillStatus.values();
		DispatchBillStatus grade;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			grade = allVaues[i];
			if (grade.getValue() == value) {

				return grade;
			}
		}
		return null;
	}
}
