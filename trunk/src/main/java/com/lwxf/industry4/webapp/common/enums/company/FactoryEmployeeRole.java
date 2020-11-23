package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/12/012 10:24
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum FactoryEmployeeRole {
	DESIGN_SUPERVISOR("014","设计主管"),
	Lv10("110","Lv10"),
	DESIGNER("015","设计人员"),
	CHAIRMAN("008","董事长"),
	SUPER_COOL("super_cool","管理层");
	private String value;
	private String name;


	FactoryEmployeeRole(String value, String name) {
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
		return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public String getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static FactoryEmployeeRole getByValue(String value){
		FactoryEmployeeRole allVaues[] = FactoryEmployeeRole.values();
		FactoryEmployeeRole status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
