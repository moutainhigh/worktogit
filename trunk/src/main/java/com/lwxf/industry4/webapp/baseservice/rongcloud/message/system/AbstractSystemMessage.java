package com.lwxf.industry4.webapp.baseservice.rongcloud.message.system;

import io.rong.messages.TxtMessage;
import io.rong.models.message.SystemMessage;
import io.rong.models.user.UserModel;

import java.util.HashMap;
import java.util.Map;

import com.lwxf.industry4.webapp.baseservice.rongcloud.RongCloudUtils;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.BaseMessage;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.MessageCategory;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.custom.LwxfTxtMsg;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.custom.MessageObjectName;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.common.utils.LwxfAssert;
import com.lwxf.industry4.webapp.common.utils.WebUtils;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018/10/20 0020 13:59
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public abstract class AbstractSystemMessage extends BaseMessage<SystemMessage> {
	protected AbstractSystemMessage(){
		this.setCategory(MessageCategory.SYSTEM);
	}
	@Override
	public SystemMessage createMessageModel() {
		LwxfAssert.notNull(null,"该方法还没实现");
		return null;
		/*LwxfAssert.notNull(this.getToUserId(),"还未设置toUserId，请检查");
		if(this.getToUserId() == null || this.getToUserId().length==0){
			return null;
		}
		// 初始化content内容
		Map<String,Object> content = new HashMap<>();
		User systemUser = WebUtils.getSystemUser(storeConfig.getCompanyId());
		this.setFromUserId(systemUser.getId());

		UserModel userModel = RongCloudUtils.createUserModelByUser(systemUser);
		// content.put(MSG_KEY_CONTENT, this.getContentText());
		this.setContentText(content);
		content.put(MSG_KEY_USER,userModel);
		this.setContent(this.json.toJson(content));

		// 初始化extra内容
		Map<String,Object> extra = new HashMap<>();
		extra.put(MSG_KEY_EXTRA_STORE,storeConfig);
		//extra.put(MSG_KEY_EXTRA_RESERVATION,this.reservation); // 改为用下边的代码 this.setExtraProperInfo(extra)
		this.setExtraProperInfo(extra);
		extra.put(MSG_KEY_EXTRA_CREATOR,this.creator);
		extra.put(MSG_KEY_CATEGORY,this.getCategory().getValue());
		extra.put(MSG_KEY_TYPE,this.getType().getValue());
		extra.put(MSG_KEY_OPERATION,this.getOperation().getValue());
		extra.put(MSG_KEY_RESOURCE,this.getResource().getValue());
		TxtMessage txtMessage = new LwxfTxtMsg(this.getContent(),this.json.toJson(extra));

		// 初始化pushContent
		this.setPushContent(this.getContentText());
		Map<String,Object> pushData = new HashMap<>();
		//pushData.put(MSG_KEY_PUSHDATA, this.getContentText()); // 改用下边的代码 this.setPushDataContent(pushData);
		this.setPushDataContent(pushData);
		this.setPushData(this.json.toJson(pushData));

		SystemMessage systemMessage = new SystemMessage(this.getFromUserId(),this.getToUserId(),txtMessage.getType(),txtMessage,this.getPushContent(),this.getPushData(),1,1,0);
		return systemMessage;*/
	}

	/**
	 * 设置每个消息特有的内容
	 * @param extra
	 */
	protected abstract void setExtraProperInfo(Map<String,Object> extra);

	/**
	 * 设置消息内容的文本
	 * @param content
	 */
	protected void setContentText(Map<String, Object> content) {
		content.put(MSG_KEY_CONTENT, this.getContentText());
	}

	/**
	 * 设置pushData的消息内容
	 * @param pushData
	 */
	protected void setPushDataContent(Map<String,Object> pushData){
		pushData.put(MSG_KEY_PUSHDATA, this.getContentText());
	}
}
