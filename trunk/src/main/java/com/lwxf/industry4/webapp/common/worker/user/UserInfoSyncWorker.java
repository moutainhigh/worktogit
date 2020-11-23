package com.lwxf.industry4.webapp.common.worker.user;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import org.apache.commons.collections4.CollectionUtils;

import com.lwxf.industry4.webapp.baseservice.rabbitmq.MessageEntity;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManagerEntity;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManualData;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.AbstractAutowireWorker;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.AfterCommitEventListener;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.BeforeUpdateEventListener;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.activity.SystemActivityEvent;
import com.lwxf.industry4.webapp.common.enums.SQLType;
import com.lwxf.industry4.webapp.common.jmail.JMailService;
import com.lwxf.industry4.webapp.common.utils.MQUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.user.User;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 11:05:18
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
//@Component("userInfoSyncWorker")
public class UserInfoSyncWorker extends AbstractAutowireWorker implements BeforeUpdateEventListener, AfterCommitEventListener {
	private static final String KEY_USER_INFOS = "userInfos";
	@Override
	public void afterCommit(List<TSManagerEntity> tsManagerEntitys) {
		List<MessageEntity> msgs = MQUtil.getMessageEntitiesFromCurrThread(KEY_USER_INFOS);
		if(CollectionUtils.isNotEmpty(msgs)){
			// AppBeanInjector.rabbitMQSender.sendAsynMessage(msgs); TODO:
		}
	}

	@Override
	public void beforeUpdate(TSManagerEntity tsManagerEntity, SQLType sqlType) {
		TSManualData tsManualData = (TSManualData) WebUtils.getDataFromRequestMap(WebConstant.TSMANAGER_MANUAL_ACTION_FLAG);
		Object params;
		Class clazz;
		if (null == tsManualData) {
			params = tsManagerEntity.getMapperParams();
			clazz = tsManagerEntity.getClazz();
		} else {
			String event = tsManualData.getEvent();
			if(JMailService.MAILSEND_CLASSNAME_SENDACTIVATIONMAIL.equals(event)){
				return;
			}
			params = tsManualData.getParams();
			sqlType = SystemActivityEvent.parseEventToSQLType(event);
			clazz = tsManualData.getClazz();
		}

		if(clazz != User.class){
			return;
		}

		if(sqlType == SQLType.INSERT || sqlType == SQLType.DELETE){
			return;
		}
		Map<String,Object> paramsMap = (Map<String,Object>)params;
		if(null == paramsMap){
			return;
		}
	}
}
