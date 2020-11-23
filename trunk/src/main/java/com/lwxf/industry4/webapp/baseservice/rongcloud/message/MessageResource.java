package com.lwxf.industry4.webapp.baseservice.rongcloud.message;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/10/10/010 15:03
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum MessageResource {


	USERS(0,"用户"),       			//店员管理和用户身份属于用户
	RESERVATION(1,"预约"), 			//预约状态属于预约
	GOODS(2,"商品"),      			//商品
	GOODS_COMMENT(3,"商品评价"),		//商品评价
	ORDERS(4,"订单"),				//订单
	LOGISTICS(5,"物流"),				//物流
	NEWS(6,"新闻资讯"),				//新闻资讯
	NEWS_COMMENT(7,"新闻资讯评价");	//新闻资讯评价

	private int value;
	private String name;

	MessageResource(int value,String name) {
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

	public static MessageResource getByValue(int value){
		MessageResource allVaues[] = MessageResource.values();
		MessageResource status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
