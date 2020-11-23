package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/8 0008 15:30
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CompanyShareMemberIdentity {
	FURNITURECONSULTANT(0,"家具顾问"),
	DESIGNER(1,"设计师"),
	ERECTOR(2,"安装工"),


	;
	private int value;
	private String name;

	CompanyShareMemberIdentity(int value, String name) {
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
	public static CompanyShareMemberIdentity getByValue(int value){
		CompanyShareMemberIdentity allVaues[] = CompanyShareMemberIdentity.values();
		CompanyShareMemberIdentity identity;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			identity = allVaues[i];
			if (identity.getValue()==value){
				return identity;
			}
		}
		return null;
	}
}
