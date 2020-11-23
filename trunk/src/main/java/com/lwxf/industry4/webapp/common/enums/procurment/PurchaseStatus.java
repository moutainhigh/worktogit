package com.lwxf.industry4.webapp.common.enums.procurment;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/27/027 20:25
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PurchaseStatus {
	PENDING_APPROVAL(0,"待审批"),
	APPROVED(1,"待采购"),
	IN_PROCUREMENT(2,"采购中"),
	ARRIVED(3,"待质检"),
	QUALITY_INSPECTION_PASS(4,"全部合格"),
	PARTIAL_QUALIFICATION(5,"部分合格"),
	FAILURE_OF_QUALITY_INSPECTION(6,"全部不合格"),
	ALL_WAREHOUSING(7,"全部入库"),
	PARTIAL_WAREHOUSING(8,"部分入库"),
	CANCEL(9,"取消");
	private Integer value;
	private String name;


	PurchaseStatus(Integer value, String name) {
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

	public static PurchaseStatus getByValue(Integer value){
		PurchaseStatus allVaues[] = PurchaseStatus.values();
		PurchaseStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}
}
