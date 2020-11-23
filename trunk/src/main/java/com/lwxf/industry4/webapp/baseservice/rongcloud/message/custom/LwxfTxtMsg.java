package com.lwxf.industry4.webapp.baseservice.rongcloud.message.custom;

import io.rong.messages.TxtMessage;
import io.rong.util.GsonUtil;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/10/30 0030 16:56
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class LwxfTxtMsg extends TxtMessage {
	public LwxfTxtMsg(String content, String extra) {
		super(content, extra);
	}

	@Override
	public String getType() {
		return MessageObjectName.LWXF_TXT_MSG.getValue();
	}

	@Override
	public String toString() {
		return GsonUtil.toJson(this, LwxfTxtMsg.class);
	}
}
