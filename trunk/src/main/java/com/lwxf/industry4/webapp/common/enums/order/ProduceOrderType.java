package com.lwxf.industry4.webapp.common.enums.order;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/12/012 13:27
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProduceOrderType {
	CABINET_BODY(0,"柜体"),
	DOOR_PLANK(1,"门板"),
	HARDWARE(2,"五金");

	private Integer value;
	private String name;


	ProduceOrderType(Integer value, String name) {
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
		return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public Integer getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static ProduceOrderType getByValue(Integer value){
		ProduceOrderType allVaues[] = ProduceOrderType.values();
		ProduceOrderType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
