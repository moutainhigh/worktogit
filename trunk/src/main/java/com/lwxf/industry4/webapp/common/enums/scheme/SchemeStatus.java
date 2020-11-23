package com.lwxf.industry4.webapp.common.enums.scheme;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/8/6 10:11
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum  SchemeStatus {

	DRAFT(0,"草稿"),
	TO_AUDIT(1,"待审核"),
	PUBLISHED(2,"已发布"),
	SOLD_OUT(3,"已下架"),
	DELETE(4,"删除");
	private Integer value;
	private String name;

	SchemeStatus(Integer value,String name){
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

	public static SchemeStatus getByValue(Integer value){
		SchemeStatus allVaues[] = SchemeStatus.values();
		SchemeStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}

}
