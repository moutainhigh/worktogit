package com.lwxf.industry4.webapp.common.enums.system;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/11/011 11:06
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PermissionShow {
	DISABLED(0,"不展示"),
	SHOW(1,"展示");

	private Integer value;
	private String name;


	PermissionShow(Integer value, String name) {
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

	public static PermissionShow getByValue(Integer value){
		PermissionShow allVaues[] = PermissionShow.values();
		PermissionShow status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue().equals(value)){
				return status;
			}
		}
		return null;
	}
}
