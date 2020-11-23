package com.lwxf.industry4.webapp.baseservice.rongcloud.message;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/10/22 0022 10:57
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum  MessageCategory {
	SYSTEM(0,"系统消息"),
	USER(1,"用户消息");

	private int value;
	private String name;

	MessageCategory(int value,String name) {
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

	public static MessageResource getByValue(int value){
		MessageResource allVaues[] = MessageResource.values();
		MessageResource status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
