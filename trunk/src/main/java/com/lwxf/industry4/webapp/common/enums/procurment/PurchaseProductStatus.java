package com.lwxf.industry4.webapp.common.enums.procurment;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/3/23/023 11:19
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PurchaseProductStatus {
	PENDING_QUALITY_INSPECTION(0,"待质检"),
	UNQUALIFIED(1,"不合格"),
	PENDING_STORAGE(2,"待入库"),
	WAREHOUSING(3,"已入库"),
	RETURNED_GOODS(4,"已退货");
	private Integer value;
	private String name;


	PurchaseProductStatus(Integer value, String name) {
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

	public static PurchaseProductStatus getByValue(Integer value){
		PurchaseProductStatus allVaues[] = PurchaseProductStatus.values();
		PurchaseProductStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}
}
