package com.lwxf.industry4.webapp.common.enums.procurment;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/22 0022 13:31
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PurchaseAuditType {
	AUDIT(0,"审核人"),
	COPY(1,"抄送人"),
	PROPOSER(2,"申请人"),
	PURCHASER(3,"采购人");


	private Integer value;
	private String name;


	PurchaseAuditType(Integer value, String name) {
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

	public static PurchaseAuditType getByValue(Integer value){
		PurchaseAuditType allVaues[] = PurchaseAuditType.values();
		PurchaseAuditType type;
		for (int i=0,len = allVaues.length;i<len;i++){
			type = allVaues[i];
			if(type.getValue()==value.byteValue()){
				return type;
			}
		}
		return null;
	}
}
