package com.lwxf.industry4.webapp.common.enums.user;

import com.lwxf.industry4.webapp.common.enums.order.OrderGoodsStatus;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/18/018 11:51
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum UserAttentionType {
	COMPANY(0,"公司,店铺"),
	MEMBER(1,"用户"),
	SCHEME(2,"设计案例");

	private int value;
	private String name;


	UserAttentionType(int value, String name) {
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

	public static UserAttentionType getByValue(int value){
		UserAttentionType allVaues[] = UserAttentionType.values();
		UserAttentionType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
