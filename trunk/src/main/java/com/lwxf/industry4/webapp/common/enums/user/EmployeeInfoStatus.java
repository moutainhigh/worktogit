package com.lwxf.industry4.webapp.common.enums.user;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/15/015 17:45
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum EmployeeInfoStatus {
	NORMAL(0,"正常"),
	IMMINENT_DEPARTURE(1,"即將离职"),
	PERSUADE(2,"劝退"),
	DISMISS(3,"辞退"),
	QUIT(4,"辞退");

	private int value;
	private String name;


	EmployeeInfoStatus(int value, String name) {
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

	public static EmployeeInfoStatus getByValue(int value){
		EmployeeInfoStatus allVaues[] = EmployeeInfoStatus.values();
		EmployeeInfoStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
