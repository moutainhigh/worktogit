package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/5/10/010 17:29
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DealerAccountNature{
	PERSONAL(0,"个人账户"),
	PUBLIC(1,"对公账户");
	private int value;
	private String name;

	DealerAccountNature(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	/**
	 * 验证数据有效性
	 * @param value
	 * @return
	 */
	public static boolean contains(int value){
		return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public static DealerAccountNature getByValue(int value){
		DealerAccountNature allVaues[] = DealerAccountNature.values();
		DealerAccountNature type;
		for (int i = 0,len=allVaues.length; i <len ; i++) {
			type = allVaues[i];
			if(type.getValue()==value){
				return type;
			}
		}
		return null;
	}
}
