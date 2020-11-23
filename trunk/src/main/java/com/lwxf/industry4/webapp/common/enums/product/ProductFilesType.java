package com.lwxf.industry4.webapp.common.enums.product;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/12/012 16:24
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProductFilesType {
	APP_COVER_MAP(0,"app封面图"),
	APP_MASTER_GRAPH(1,"app主图"),
	PC_COVER_MAP(2,"pc封面"),
	PC_MASTER_GRAPH(3,"pc主图"),
	WX_COVER_MAP(4,"wx封面"),
	WX_ATLAS(5,"wx图集"),
	WX_OFFER(6,"wx报价图");

	private Integer value;
	private String name;


	ProductFilesType(Integer value, String name) {
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

	public static ProductFilesType getByValue(Integer value){
		ProductFilesType allVaues[] = ProductFilesType.values();
		ProductFilesType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value.byteValue()){
				return status;
			}
		}
		return null;
	}
}
