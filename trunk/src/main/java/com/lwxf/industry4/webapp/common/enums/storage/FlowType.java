package com.lwxf.industry4.webapp.common.enums.storage;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/21/021 10:59
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum FlowType {
	WAREHOUSING(0,"入库"),
	OUT_STOCK(1,"出库");

	private Integer value;
	private String name;


	FlowType(Integer value, String name) {
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

	public static FlowType getByValue(Integer value){
		FlowType allVaues[] = FlowType.values();
		FlowType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
