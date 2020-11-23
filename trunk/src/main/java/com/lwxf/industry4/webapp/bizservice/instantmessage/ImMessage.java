package com.lwxf.industry4.webapp.bizservice.instantmessage;


import java.util.Date;

/**
 * 即时消息的实体
 */
public class ImMessage {

    private String title;
    /**
     * 发送人
     */
    private String from;
    /**
     * 接收人
     */
    private String to;
    /**
     * 消息内容
     */
    private String msgContent;
    /**
     * 消息类型，0：全局消息，1：按照部门发送，2：按照人员发送
     */
    private Integer msgType;
    /**发送时间*/
    private Date sendTime;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }

    public Date getSendTime() {
        return sendTime;
    }

    public void setSendTime(Date sendTime) {
        this.sendTime = sendTime;
    }
}
