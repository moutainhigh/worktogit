package com.lwxf.industry4.webapp.common.enums.storage;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/21/021 17:25
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum StorageOutputInType {
	OUT_STOCK(0,"出库"),
	PURCHASE_WAREHOUSING(1,"采购入库"),
	INVENTORY_WAREHOUSING(2,"盘盈入库"),
	ORDER_WAREHOUSING(3,"订单入库"),
	MANUAL_WAREHOUSING(4,"手动入库"),
	OTHER(5,"其他");

	private Integer value;
	private String name;


	StorageOutputInType(Integer value, String name) {
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

	public static StorageOutputInType getByValue(Integer value){
		StorageOutputInType allVaues[] = StorageOutputInType.values();
		StorageOutputInType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
