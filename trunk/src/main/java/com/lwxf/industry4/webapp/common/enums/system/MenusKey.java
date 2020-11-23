package com.lwxf.industry4.webapp.common.enums.system;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/2/18/018 10:35
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum MenusKey {
	STOCKMNG("stockmng","仓库管理"),
	OUTINMNG("outinmng","出入库管理");

	private String value;
	private String name;


	MenusKey(String value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 数据范围校验
	 *
	 * @param value
	 * @return
	 */
	public static boolean contains(String value) {
		return null == value?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public String getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static MenusKey getByValue(String value){
		MenusKey allVaues[] = MenusKey.values();
		MenusKey status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue().equals(value)){
				return status;
			}
		}
		return null;
	}
}
