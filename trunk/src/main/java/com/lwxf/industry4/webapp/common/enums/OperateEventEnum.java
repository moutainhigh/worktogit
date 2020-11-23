package com.lwxf.industry4.webapp.common.enums;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2019/2/25 0025 15:26
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum OperateEventEnum {
	ROLE_PERMISSION_MD("role:permission:md");

	private String value;
	OperateEventEnum(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
