package com.lwxf.industry4.webapp.baseservice.rongcloud.message;

/**
 * 功能：资源的操作定义
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/10/10 0010 10:15
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ResourceOperation {
	CREATE(0),		// 创建、新增
	UPDATE(1),		// 修改、更新
	DELETE(2),		// 删除
	DISABLED(3);	// 禁用

	private int value;
	ResourceOperation(int value){
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}
