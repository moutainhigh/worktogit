package com.lwxf.industry4.webapp.common.enums.scheme;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/26/026 13:21
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum SchemeType {
	FACTORY(0,"厂家"),
	DEALER(1,"经销商或卖厂家产品的商户");
	private Integer value;
	private String name;

	SchemeType(Integer value,String name){
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

	public static SchemeType getByValue(Integer value){
		SchemeType allVaues[] = SchemeType.values();
		SchemeType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
