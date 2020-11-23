package com.lwxf.industry4.webapp.common.enums.storage;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/19/019 16:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DispatchBillPlanItemStatus {
	UNSHIPPED(0,"未发货"),
	SHIPPED(1,"已发货");


	private Integer value;
	private String name;


	DispatchBillPlanItemStatus(Integer value, String name) {
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

	public static DispatchBillPlanItemStatus getByValue(Integer value){
		DispatchBillPlanItemStatus allVaues[] = DispatchBillPlanItemStatus.values();
		DispatchBillPlanItemStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
