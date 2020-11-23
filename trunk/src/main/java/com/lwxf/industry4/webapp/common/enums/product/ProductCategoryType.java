package com.lwxf.industry4.webapp.common.enums.product;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/14/014 11:01
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProductCategoryType {
	RAW_MATERIAL(0,"原材料"),
	TAILINGS(1,"尾料"),
	WASTE(2,"废料"),
	WITHDRAWAL(3,"退料"),
	RETURN_GOODS(4,"退货"),
	FINISHED_PRODUCT(5,"成品");

	private Integer value;
	private String name;


	ProductCategoryType(Integer value, String name) {
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

	public static ProductCategoryType getByValue(Integer value){
		ProductCategoryType allVaues[] = ProductCategoryType.values();
		ProductCategoryType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}
}
