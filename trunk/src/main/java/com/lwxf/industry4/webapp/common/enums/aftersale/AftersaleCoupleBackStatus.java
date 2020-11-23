package com.lwxf.industry4.webapp.common.enums.aftersale;

/**
 * 功能：售后反馈单状态
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/2 0002 20:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum AftersaleCoupleBackStatus {

	WAIT(0,"待处理"),
	PROCESSING(1,"处理中"),
	REFUSE(2,"拒绝"),
	COMPLETED(3,"已完成")
	;
	private Integer value;
	private String name;
	AftersaleCoupleBackStatus(Integer value, String name) {
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

	public static AftersaleCoupleBackStatus getByValue(int value) {
		AftersaleCoupleBackStatus allVaues[] = AftersaleCoupleBackStatus.values();
		AftersaleCoupleBackStatus status;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue() == value) {

				return status;
			}
		}
		return null;
	}
}
