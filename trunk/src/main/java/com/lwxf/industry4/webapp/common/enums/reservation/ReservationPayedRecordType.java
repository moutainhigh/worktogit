package com.lwxf.industry4.webapp.common.enums.reservation;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/8/008 16:05
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ReservationPayedRecordType {
	DEPOSIT(0,"定金"),
	ADVANCE_CHARGE (1,"预付款"),
	TAIL_MONEY(2,"尾款");

	private int value;
	private String name;

	ReservationPayedRecordType(int value,String name) {
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

	public static ReservationPayedRecordType getByValue(int value){
		ReservationPayedRecordType allVaues[] = ReservationPayedRecordType.values();
		ReservationPayedRecordType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
