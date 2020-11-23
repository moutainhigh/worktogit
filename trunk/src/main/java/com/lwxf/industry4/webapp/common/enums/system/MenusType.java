package com.lwxf.industry4.webapp.common.enums.system;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/1/7/007 10:14
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum MenusType {
	FACTORY_BACKSTAGE(0,"厂家后台菜单"),
	DEALER_BACKSTAGE(1,"经销商后台菜单"),
	DEALER_APP(2,"经销商APP菜单");

	private Integer value;
	private String name;


	MenusType(Integer value, String name) {
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

	public static MenusType getByValue(Integer value){
		MenusType allVaues[] = MenusType.values();
		MenusType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
