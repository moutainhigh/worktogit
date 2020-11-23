package com.lwxf.industry4.webapp.common.enums.branch;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/5/005 17:43
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum BranchType {

	FREE(1,"免费用户"),
	PAY(2,"付费用户");
	private Integer value;
	private String name;

	BranchType(Integer value, String name) {
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

	public static BranchType getByValue(int value) {
		BranchType allVaues[] = BranchType.values();
		BranchType grade;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			grade = allVaues[i];
			if (grade.getValue() == value) {

				return grade;
			}
		}
		return null;
	}
}
