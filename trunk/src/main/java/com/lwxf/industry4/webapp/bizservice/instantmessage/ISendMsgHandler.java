package com.lwxf.industry4.webapp.bizservice.instantmessage;


public interface ISendMsgHandler {
    /**
     * 发送文本消息
     * @param msg
     * @return
     */
    boolean sendTextMsg(ImMessage msg);
}
