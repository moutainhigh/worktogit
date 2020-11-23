package com.lwxf.industry4.webapp.common.enums.user;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/20/020 19:10
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum EducationType {
	MASTER(0,"硕士"),
	UNDERGRADUATE(1,"本科"),
	JUNIOR_COLLEGE(2,"大专"),
	HIGH_SCHOOL(3,"高中"),
	SCHOOL(4,"中专"),
	JUNIOR_MIDDLE_SCHOOL(3,"初中");

	private Integer value;
	private String name;


	EducationType(Integer value, String name) {
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
		return null == value?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public Integer getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static EducationType getByValue(Integer value){
		EducationType allVaues[] = EducationType.values();
		EducationType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
