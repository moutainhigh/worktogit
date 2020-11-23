package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/6/006 16:35
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum UserIdentityDefinition {
	HOME_ADVISOR(0,"家具顾问"),
	STYLIST(1,"设计师"),
	ERECTOR(2,"安装工");
	private int value;
	private String name;

	UserIdentityDefinition(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	/**
	 * 数据验证
	 * @param value
	 * @return
	 */
	public static Boolean contains(int value){
		return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public static UserIdentityDefinition getByValue(int value){
		UserIdentityDefinition allVaues[] = UserIdentityDefinition.values();
		UserIdentityDefinition status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
