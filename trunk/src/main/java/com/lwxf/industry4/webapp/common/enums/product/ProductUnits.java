package com.lwxf.industry4.webapp.common.enums.product;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/3/003 14:22
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProductUnits {
	NUMBER(0,"个"),
	SHEET(1,"张"),
	METER(2,"米"),
	SQUARE_METER(3,"平方米"),
	BAG(4,"包");

	private Integer value;
	private String name;


	ProductUnits(Integer value, String name) {
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

	public static ProductUnits getByValue(Integer value){
		ProductUnits allVaues[] = ProductUnits.values();
		ProductUnits status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}
}
