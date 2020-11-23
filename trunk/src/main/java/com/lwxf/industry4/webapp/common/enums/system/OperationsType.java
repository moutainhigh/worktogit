package com.lwxf.industry4.webapp.common.enums.system;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/22/022 11:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum OperationsType {
	PUBLIC(0,"公共操作"),
	FACTORY(1,"厂家操作"),
	DEALER(2,"经销商操作"),
	SUPPLIER(3,"供应商操作");

	private Integer value;
	private String name;


	OperationsType(Integer value, String name) {
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

	public static OperationsType getByValue(Integer value){
		OperationsType allVaues[] = OperationsType.values();
		OperationsType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
