package com.lwxf.industry4.webapp.baseservice.rongcloud;

import io.rong.RongCloud;
import io.rong.models.message.MessageModel;
import io.rong.models.response.ResponseResult;
import io.rong.models.response.TokenResult;
import io.rong.models.user.UserModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lwxf.industry4.webapp.baseservice.rongcloud.message.BaseMessage;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.commons.exception.ExecuteFailException;
import com.lwxf.commons.exception.LwxfException;
import com.lwxf.industry4.webapp.baseservice.rongcloud.message.BaseMessage;
import com.lwxf.industry4.webapp.common.utils.WebUtils;

/**
 * 功能：融云工具类
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-09-14 16:56
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class RongCloudUtils {
	public static final String RESULT_CODE_200 = "200";
	private static Logger logger = LoggerFactory.getLogger(RongCloudUtils.class.getName());

	public static UserModel createUserModelByUser(User user){
		UserModel userModel = new UserModel();
		userModel.setId(user.getId());
		userModel.setName(user.getName());
		userModel.setPortrait(WebUtils.getDomainUrl().concat(user.getAvatar()));
		return userModel;
	}
	/**
	 * 在融云上注册用户
	 * @param user
	 * @return
	 */
	public static TokenResult registerUser(User user){
		RongCloud rongCloud = RongCloud.getInstance(AppBeanInjector.rongCloudCfg.getAppKey(), AppBeanInjector.rongCloudCfg.getAppSecret());
		try {
			TokenResult result = rongCloud.user.register(createUserModelByUser(user));
			if(!result.code.toString().equals(RESULT_CODE_200)){
				logger.error("############ 注册融云平台用户时出现错误");
				logger.error("############ 错误码：{}",result.code);
				logger.error("############ 错误信息：{}",result.getMsg());
				return null;
			}
			return result;
		} catch (Exception e) {
			logger.error("注册融云平台用户时异常",e);
		}
		return null;
	}

	/**
	 * 发送消息
	 * @param message
	 * @return
	 */
	public static void sendSystemMessage(BaseMessage message){
		RongCloud rongCloud = RongCloud.getInstance(AppBeanInjector.rongCloudCfg.getAppKey(), AppBeanInjector.rongCloudCfg.getAppSecret());
		try {
			MessageModel messageModel = message.createMessageModel();
			if(null == messageModel){
				return;
			}
			ResponseResult responseResult = rongCloud.message.system.send(messageModel);
			if(!responseResult.getCode().equals(Integer.valueOf(200))){
				logger.error("*************** 发送系统消息失败");
				logger.error("*************** code = {}",responseResult.getCode());
				logger.error("*************** msg = {}",responseResult.getMsg());
			}
		} catch (Exception e) {
			logger.error("发送消息时出现异常",e);
			throw new ExecuteFailException("发送消息时出现异常");
		}
	}
}
