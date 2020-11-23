package com.lwxf.industry4.webapp.baseservice.rongcloud.message;

/**
 * 功能：消息类型定义
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/10/10 0010 9:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum MessageType {


	EMPLOYEE(0),			//,"店员管理"
	USER_IDENTITY(1),		//,"用户身份"
	RESERVATION(2),			//,"预约"
	RESERVATION_STATUS(3),	//,"预约状态"
	GOODS(4),				//,"商品"
	GOODS_COMMENT(5),		//,"商品评价"
	ORDERS(6),				//,"订单"
	LOGISTICS(7),			//,"物流"
	NEWS(8),				//,"新闻资讯"
	NEW_COMMENT(9),			//,"新闻资讯评价"
	NEW_PRAISE(10);			//,"新闻资讯点赞"




/*	NEWS(0), 			// 资讯
	MEASURE(1), 		// 测量
	DESIGN(2),			// 设计
	MANUFACTURE(3),		// 生产、制造
	LOGISTICS(4),		// 物流（发货）
	INSTALLATION(5),	// 安装
	APPRAISE(6),		// 评价
	IDENTITY_MANAGE(7), // 用户身份
	CLERK_MANAGE(8),	// 店员管理
	GOODS(9),			// 商品
	ORDER(10)			// 订单
	;*/
	private int value;
	MessageType(int value){
		this.value = value;
	}

	public int getValue(){
		return this.value;
	}
}
