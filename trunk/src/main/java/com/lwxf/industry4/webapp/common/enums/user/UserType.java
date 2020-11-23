
package com.lwxf.industry4.webapp.common.enums.user;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/5/005 14:52
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum UserType {
	FACTORY(0,"厂家"),
	DEALER(1,"经销商"),
	CLIENT(2,"客户"),
	SHARE(3,"共享设计师(或安装工d家居顾问）用户"),
	ADMIN(4,"系统账户"),
	SUPER_ADMIN(5,"超级管理员");

	private Integer value;
	private String name;


	UserType(Integer value, String name) {
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

	public static UserType getByValue(Integer value){
		UserType allVaues[] = UserType.values();
		UserType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}