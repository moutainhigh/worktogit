package com.lwxf.industry4.webapp.common.enums.dispatchList;

import com.lwxf.industry4.webapp.common.enums.company.CompanyCustomerGrade;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2018/12/20 0020 13:13
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DispatchListStatus {

	NODISPATCHEDWORKER(0,"未派工"),
	NOTAKEDELIVERY(1,"未提货"),
	NOCONTRUCTION(2,"未施工"),
	NOTCHECKED(3,"未检查"),
	NOREVIEW(4,"未复检"),
	END(5,"已完成"),
	;
	private Integer value;
	private String name;

	DispatchListStatus(Integer value, String name) {
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

	public static DispatchListStatus getByValue(int value) {
		DispatchListStatus allVaues[] = DispatchListStatus.values();
		DispatchListStatus status;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue() == value) {

				return status;
			}
		}
		return null;
	}
}
