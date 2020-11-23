package com.lwxf.industry4.webapp.common.enums.procurment;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/27/027 18:27
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PurchaseRquestStatus {
	STAY(0,"待提交"),
	TO_BE_AUDIT(1,"待审核"),
	TO_BE_PAY(2,"待付款"),
	PURCHASEING(3,"采购中"),
	TO_BE_FINISHED(4,"待入库"),
	END(5,"已完成"),
	CANCEL(6,"已取消");

	private Integer value;
	private String name;


	PurchaseRquestStatus(Integer value, String name) {
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

	public static PurchaseRquestStatus getByValue(Integer value){
		PurchaseRquestStatus allVaues[] = PurchaseRquestStatus.values();
		PurchaseRquestStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}
}
