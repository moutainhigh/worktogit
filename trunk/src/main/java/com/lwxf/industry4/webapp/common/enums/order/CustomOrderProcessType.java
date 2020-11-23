package com.lwxf.industry4.webapp.common.enums.order;

import com.lwxf.industry4.webapp.common.enums.company.DealerEmployeeRole;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/17/017 18:33
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CustomOrderProcessType {
	SPARE_PARTS(0,"备料"),
	MACHINING(1,"加工"),
	EDGE_SEALING(2,"封边"),
	TRIMMING(3,"修边"),
	QUALITY_TESTING(4,"质检"),
	PACKING(5,"包装"),
	PENDING_STORAGE(6,"待入库"),
	WAREHOUSING(7,"已入库");
	private Integer value;
	private String name;


	CustomOrderProcessType(Integer value, String name) {
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

	public static CustomOrderProcessType getByValue(Integer value){
		CustomOrderProcessType allVaues[] = CustomOrderProcessType.values();
		CustomOrderProcessType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
