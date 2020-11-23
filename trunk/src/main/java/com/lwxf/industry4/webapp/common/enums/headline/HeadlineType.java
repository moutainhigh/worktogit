package com.lwxf.industry4.webapp.common.enums.headline;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/13/013 14:30
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum HeadlineType {
	NEWS(0,"资讯"),
	BLOG(1,"博客类文章"),
	ADVERTISEMENT(2,"广告"),
	BROADCAST(3,"首页轮播"),
	DESIGN_CASE(4,"设计案例");

	private int value;
	private String name;


	HeadlineType(int value, String name) {
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

	public static HeadlineType getByValue(int value){
		HeadlineType allVaues[] = HeadlineType.values();
		HeadlineType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
