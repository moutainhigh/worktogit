package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/25/025 14:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum EmployeeDailyRecordVisibleState {
	DEPT(0,"本部门"),
	ALL(1,"全部可见");
	private int value;
	private String name;


	EmployeeDailyRecordVisibleState(int value, String name) {
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

	public static EmployeeDailyRecordVisibleState getByValue(int value){
		EmployeeDailyRecordVisibleState allVaues[] = EmployeeDailyRecordVisibleState.values();
		EmployeeDailyRecordVisibleState status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
