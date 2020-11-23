package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/6/006 15:01
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DealerEmployeeRole {
	CLERK("256","店员"),
	MANAGER("257","经理"),
	SHOPKEEPER("258","店主");
	private String value;
	private String name;


	DealerEmployeeRole(String value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 数据范围校验
	 *
	 * @param value
	 * @return
	 */
	public static boolean contains(String value) {
		return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public String getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static DealerEmployeeRole getByValue(String value){
		DealerEmployeeRole allVaues[] = DealerEmployeeRole.values();
		DealerEmployeeRole status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
