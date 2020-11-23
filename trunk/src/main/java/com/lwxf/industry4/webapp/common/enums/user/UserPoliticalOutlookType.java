package com.lwxf.industry4.webapp.common.enums.user;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/20/020 19:02
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum UserPoliticalOutlookType {
	COMMUNIST_PARTY_MEMBERS(0,"中共党员"),
	COMMUNIST_YOUTH_LEAGUE_MEMBER(1,"共青团员"),
	MASSES(2,"群众");

	private Integer value;
	private String name;


	UserPoliticalOutlookType(Integer value, String name) {
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

	public static UserPoliticalOutlookType getByValue(Integer value){
		UserPoliticalOutlookType allVaues[] = UserPoliticalOutlookType.values();
		UserPoliticalOutlookType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
