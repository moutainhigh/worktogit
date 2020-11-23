package com.lwxf.industry4.webapp.common.enums.aftersale;

import com.lwxf.industry4.webapp.common.enums.dispatch.DispatchBillStatus;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/2 0002 20:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum AftersaleStatus {

	WAIT(0,"已受理"),
	TO_BE_PAY(1,"待支付"),
	TO_BE_DISPATCH(2,"待发货"),
	COMPLETED(3,"已发货")
	;
	private Integer value;
	private String name;
	AftersaleStatus(Integer value, String name) {
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

	public static AftersaleStatus getByValue(int value) {
		AftersaleStatus allVaues[] = AftersaleStatus.values();
		AftersaleStatus status;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue() == value) {

				return status;
			}
		}
		return null;
	}
}
