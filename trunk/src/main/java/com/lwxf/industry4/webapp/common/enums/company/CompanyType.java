package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/6/006 15:29
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CompanyType {
	MANUFACTURERS(0,"生产商/制造商/总店"),
	DIRECT_SHOP(1,"直营店"),
	SHOP_IN_STORE(2,"店中店"),
	EXCLUSIVE_SHOP(3,"专卖店"),
	FRANCHISED_STORE(4,"加盟店"),
	LOOSE_ORDER(5,"散单"),
	SUPPLIER(6,"供应商");
	private int value;
	private String name;

	CompanyType(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	/**
	 * 验证数据有效性
	 * @param value
	 * @return
	 */
	public static boolean contains(int value){
		return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public static CompanyType getByValue(int value){
		CompanyType allVaues[] = CompanyType.values();
		CompanyType status;
		for (int i = 0,len=allVaues.length; i <len ; i++) {
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
