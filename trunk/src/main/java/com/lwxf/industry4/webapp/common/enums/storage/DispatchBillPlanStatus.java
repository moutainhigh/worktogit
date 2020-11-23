package com.lwxf.industry4.webapp.common.enums.storage;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/19/019 16:33
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DispatchBillPlanStatus {
	INCOMPLETE(0,"未完成"),
	COMPLETED(1,"已完成");


	private Integer value;
	private String name;


	DispatchBillPlanStatus(Integer value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 数据范围校验
	 *
	 * @param value
	 * @return
	 */
	public static boolean contains(Integer value) {
		return null == value?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public Integer getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static DispatchBillPlanStatus getByValue(Integer value){
		DispatchBillPlanStatus allVaues[] = DispatchBillPlanStatus.values();
		DispatchBillPlanStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
