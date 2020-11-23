package com.lwxf.industry4.webapp.common.enums.headline;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/9/13/013 17:44
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum HeadlineFilesCategory {
	IMAGE(0,"图片"),
	VIDEO(1,"视频");
	private int value;
	private String name;


	HeadlineFilesCategory(int value, String name) {
		this.value = value;
		this.name = name;
	}

	/**
	 * 数据范围校验
	 *
	 * @param value
	 * @return
	 */
	public static boolean contains(int value) {
		return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public int getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static HeadlineFilesCategory getByValue(int value){
		HeadlineFilesCategory allVaues[] = HeadlineFilesCategory.values();
		HeadlineFilesCategory status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
