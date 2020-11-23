package com.lwxf.industry4.webapp.common.enums.storage;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/24/024 16:09
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum FinishedStockStatus {
	UNSHIPPED(0,"未配送"),
	PARTIAL_SHIPMENT(1,"部分配送"),
	ALL_SHIPMENT(2,"全部配送");


	private Integer value;
	private String name;


	FinishedStockStatus(Integer value, String name) {
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

	public static FinishedStockStatus getByValue(Integer value){
		FinishedStockStatus allVaues[] = FinishedStockStatus.values();
		FinishedStockStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
