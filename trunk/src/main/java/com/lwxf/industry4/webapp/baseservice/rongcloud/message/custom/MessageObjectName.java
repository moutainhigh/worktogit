package com.lwxf.industry4.webapp.baseservice.rongcloud.message.custom;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/10/30 0030 16:31
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum MessageObjectName {
	LWXF_TXT_MSG("LWXF:TxtMsg");

	private String value;
	MessageObjectName(String value){
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
