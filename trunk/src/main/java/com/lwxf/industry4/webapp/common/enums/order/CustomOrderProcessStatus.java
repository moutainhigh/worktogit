package com.lwxf.industry4.webapp.common.enums.order;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/17/017 18:41
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CustomOrderProcessStatus {
	HAVE_IN_HAND(0,"进行中"),
	COMPLETED(1,"已完成");
	private Integer value;
	private String name;


	CustomOrderProcessStatus(Integer value, String name) {
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

	public static CustomOrderProcessStatus getByValue(Integer value){
		CustomOrderProcessStatus allVaues[] = CustomOrderProcessStatus.values();
		CustomOrderProcessStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
