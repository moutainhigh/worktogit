package com.lwxf.industry4.webapp.common.enums.system;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/2/002 20:24
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum RoleType {
	FACTORY(0,"厂家角色"),
	DEALER(1,"经销商角色"),
	SYSTEM(2,"系统角色");

	private Integer value;
	private String name;


	RoleType(Integer value, String name) {
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

	public static RoleType getByValue(Integer value){
		RoleType allVaues[] = RoleType.values();
		RoleType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
