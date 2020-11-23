package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/27/027 16:23
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum EmployeeDailyRecordRecordState {
	DRAFT(0,"草稿"),
	RELEASE(1,"发布"),
	DELETE(2,"删除");
	private int value;
	private String name;


	EmployeeDailyRecordRecordState(int value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 数据范围校验
	 *
	 * @param value
	 * @return
	 */
	public static boolean contains(int value) {
		return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public int getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static EmployeeDailyRecordRecordState getByValue(int value){
		EmployeeDailyRecordRecordState allVaues[] = EmployeeDailyRecordRecordState.values();
		EmployeeDailyRecordRecordState status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
