package com.lwxf.industry4.webapp.common.enums.headline;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/13/013 14:29
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum HeadlineStatus {
	NORMAL(0,"正常"),
	DISABLED(1,"禁用"),
	DELETE(2,"删除");

	private int value;
	private String name;


	HeadlineStatus(int value, String name) {
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

	public static HeadlineStatus getByValue(int value){
		HeadlineStatus allVaues[] = HeadlineStatus.values();
		HeadlineStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
