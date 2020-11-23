package com.lwxf.industry4.webapp.common.enums.system;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/22/022 13:36
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum MenusDisabled {
	UNDISABLED(false,"启用"),
	DISABLED(true,"禁用");

	private Boolean value;
	private String name;


	MenusDisabled(Boolean value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 数据范围校验
	 *
	 * @param value
	 * @return
	 */
	public static boolean contains(Boolean value) {
		return null == value?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public Boolean getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static MenusDisabled getByValue(Boolean value){
		MenusDisabled allVaues[] = MenusDisabled.values();
		MenusDisabled status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
