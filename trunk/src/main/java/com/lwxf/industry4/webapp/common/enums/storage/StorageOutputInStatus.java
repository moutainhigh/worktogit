package com.lwxf.industry4.webapp.common.enums.storage;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/21/021 17:11
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum StorageOutputInStatus {
	UNCERTAIN(0,"待确定"),
	CONFIRM(1,"确认"),
	CANCEL(2,"取消");

	private Integer value;
	private String name;


	StorageOutputInStatus(Integer value, String name) {
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

	public static StorageOutputInStatus getByValue(Integer value){
		StorageOutputInStatus allVaues[] = StorageOutputInStatus.values();
		StorageOutputInStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
