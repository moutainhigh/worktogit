package com.lwxf.industry4.webapp.common.enums.storage;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/22/022 16:10
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum FinishedStockItemType {
	CABINET(0,"柜体"),
	DOOR_HOMEGROWN(1,"门板-自产"),
	DOOR_OUTSOURCING(2,"门板-外协"),
	SPECIAL_SUPPLY(3,"特供实木"),
	HARDWARE(4,"五金");


	private Integer value;
	private String name;


	FinishedStockItemType(Integer value, String name) {
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

	public static FinishedStockItemType getByValue(Integer value){
		FinishedStockItemType allVaues[] = FinishedStockItemType.values();
		FinishedStockItemType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
