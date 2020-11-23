package com.lwxf.industry4.webapp.common.enums.product;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/4/004 17:01
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProductStatus {
	ENABLE(0,"正常"),
	DISABLED(1,"禁用");

	private Integer value;
	private String name;


	ProductStatus(Integer value, String name) {
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

	public static ProductStatus getByValue(Integer value){
		ProductStatus allVaues[] = ProductStatus.values();
		ProductStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}
}
