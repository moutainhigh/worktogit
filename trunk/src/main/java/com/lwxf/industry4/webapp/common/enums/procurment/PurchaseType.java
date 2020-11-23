package com.lwxf.industry4.webapp.common.enums.procurment;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/27/027 18:23
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PurchaseType {
	ARTIFICIAL(0,"人工采购"),
	SYSTEM(1,"系统采购"),
	SAMPLE_DESIGN(2,"产品设计"),
	ACTIVITY(3,"活动采购");

	private Integer value;
	private String name;


	PurchaseType(Integer value, String name) {
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

	public static PurchaseType getByValue(Integer value){
		PurchaseType allVaues[] = PurchaseType.values();
		PurchaseType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}
}
