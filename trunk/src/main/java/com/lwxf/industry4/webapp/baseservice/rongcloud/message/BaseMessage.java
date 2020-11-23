package com.lwxf.industry4.webapp.baseservice.rongcloud.message;

import io.rong.models.message.MessageModel;

import com.lwxf.industry4.webapp.domain.entity.company.StoreConfig;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.commons.exception.UninitializedException;
import com.lwxf.commons.json.JsonMapper;

/**
 * 功能：消息的基类定义
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-10-07 15:25
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public abstract class BaseMessage<M extends MessageModel> {
	protected static final String MSG_KEY_CATEGORY = "category";
	protected static final String MSG_KEY_TYPE = "type";
	protected static final String MSG_KEY_RESOURCE = "resourece";
	protected static final String MSG_KEY_OPERATION = "operation";
	protected static final String MSG_KEY_CONTENT="content";
	protected static final String MSG_KEY_USER="user";
	protected static final String MSG_KEY_PUSHDATA="pushData";
	protected static final String MSG_KEY_EXTRA_STORE ="store";
	protected static final String MSG_KEY_EXTRA_CREATOR ="creator";

	protected JsonMapper json = new JsonMapper();
	private String fromUserId;
	private String[] toUserId;
	/**
	 * 融云消息的内容
	 */
	private String content;
	/**
	 * 消息的提示内容
	 */
	private String contentText;
	private String pushContent;
	private String pushData;
	private MessageCategory category;
	private MessageType type;
	private ResourceOperation operation;
	private MessageResource resource;
	protected StoreConfig storeConfig;
	protected User creator;

	public abstract M createMessageModel();

	public String getFromUserId() {
		return fromUserId;
	}

	public void setFromUserId(String fromUserId) {
		this.fromUserId = fromUserId;
	}

	public String[] getToUserId() {
		return toUserId;
	}

	public void setToUserId(String[] toUserId) {
		this.toUserId = toUserId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentText() {
		return contentText;
	}

	public void setContentText(String contentText) {
		this.contentText = contentText;
	}

	public String getPushContent() {
		return pushContent;
	}

	public void setPushContent(String pushContent) {
		this.pushContent = pushContent;
	}

	public String getPushData() {
		return pushData;
	}

	public void setPushData(String pushData) {
		this.pushData = pushData;
	}

	public MessageCategory getCategory() {
		return category;
	}

	public void setCategory(MessageCategory category) {
		this.category = category;
	}

	public MessageType getType() {
		if(this.type == null){
			throw new UninitializedException("消息类型未初始化");
		}
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public ResourceOperation getOperation() {
		if(this.operation == null){
			throw new UninitializedException("消息的资源操作未设置");
		}
		return operation;
	}

	public void setOperation(ResourceOperation operation) {
		this.operation = operation;
	}

	public MessageResource getResource() {
		if(this.resource == null){
			throw new UninitializedException("消息的资源定义未设置");
		}
		return resource;
}

	public void setResource(MessageResource resource) {
		this.resource = resource;
	}
}
