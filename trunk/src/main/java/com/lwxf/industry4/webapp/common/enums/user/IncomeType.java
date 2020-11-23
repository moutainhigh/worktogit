package com.lwxf.industry4.webapp.common.enums.user;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/20/020 19:08
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum IncomeType {
	ONE(0,"2000~4000"),
	TWO(1,"4000~6000"),
	THREE(2,"6000~8000"),
	FOUR(3,"8000~10000");

	private Integer value;
	private String name;


	IncomeType(Integer value, String name) {
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

	public static IncomeType getByValue(Integer value){
		IncomeType allVaues[] = IncomeType.values();
		IncomeType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
