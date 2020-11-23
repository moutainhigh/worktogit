package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/6/006 16:41
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CompanyShareMemberStatus {
	APPROVAL_PENDING(0,"待审批（申请状态）"),
	NOT_APPROVED(1,"审批未通过"),
	NORMAL(2,"已审批（可营业状态）"),
	DISABLED(3,"被禁用");
	private int value;
	private String name;

	CompanyShareMemberStatus(int value, String name) {
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
	public static CompanyShareMemberStatus getByValue(int value){
		CompanyShareMemberStatus allVaues[] = CompanyShareMemberStatus.values();
		CompanyShareMemberStatus status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
