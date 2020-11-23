package com.lwxf.industry4.webapp.common.enums.scheme;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/3/28/028 17:19
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum DesignSchemeFileFileType {
	IMG(0,"图片"),
	VIDEO(1,"视频"),
	FILE_3D(2,"3D"),
	VR(3,"vr");
	private Integer value;
	private String name;

	DesignSchemeFileFileType(Integer value,String name){
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

	public static DesignSchemeFileFileType getByValue(Integer value){
		DesignSchemeFileFileType allVaues[] = DesignSchemeFileFileType.values();
		DesignSchemeFileFileType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}

}
