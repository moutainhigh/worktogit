package com.lwxf.industry4.webapp.common.enums.content;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/15/015 16:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ContentsType {

	ANSWER("4zswlexdudc0","使用帮助");
	private String value;
	private String name;
	ContentsType(String value, String name) {
		this.value = value;
		this.name = name;
	}
	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	public static Boolean contains(String value) {
		return null == getByValue(value) ? Boolean.FALSE.booleanValue() : Boolean.TRUE.booleanValue();
	}

	public static ContentsType getByValue(String value) {
		ContentsType allVaues[] = ContentsType.values();
		ContentsType status;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue() == value) {
				return status;
			}
		}
		return null;
	}
}
