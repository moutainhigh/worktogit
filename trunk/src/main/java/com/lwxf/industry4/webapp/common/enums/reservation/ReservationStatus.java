package com.lwxf.industry4.webapp.common.enums.reservation;

/**
 * 功能：
 *
 * @author：dell
 * @create：2018/7/4 18:38
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ReservationStatus {
	UNTREATED(0,"待接单"),
	UNSCANNED (1,"已接单"),
	TO_DESIGN(2,"已测量"),
	DESIGN(3,"设计中"),
	DESIGN_VERIFICATION(4,"等待设计确认"),
	TO_QUOTE(5,"设计已确认"),
	QUOTED_PRICE(6,"已报价"),
	TO_BE_PRODUCED(7,"已付预付款"),
	PRODUCTION(8,"生产中"),
	PENDING_DELIVERY(9,"生产完成"),
	ALREADY_SHIPPED(10,"已发货"),
	TO_BE_INSTALLED(11,"已到货"),
	INSTALLATION(12,"安装中"),
	INSTALLATION_IS_COMPLETE(13,"安装完成"),
	ALREADY_PAID(14,"已支付尾款"),
	COMPLETE(15,"完成"),
	CANCELLED(16,"已取消");

	private int value;
	private String name;

	ReservationStatus(int value,String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 数据范围校验
	 *
	 * @param value
	 * @return
	 */
	public static boolean contains(int value) {
		return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}

	public int getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static ReservationStatus getByValue(int value){
		ReservationStatus allVaues[] = ReservationStatus.values();
		ReservationStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
