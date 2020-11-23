package com.lwxf.industry4.webapp.baseservice.sms.yunpian;

import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;

/**
 * 功能：手机验证码类型定义
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/10/29 0029 14:41
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum VerificationCodeType {
	REGISTER("r"),
	UPDATE_PASSWORD("up"),
	LOGIN("l")
	;
	VerificationCodeType(String value){
		this.value=value;
	}
	private String value ;
	public String getValue() {
		return value;
	}
}
