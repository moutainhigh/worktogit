package com.lwxf.industry4.webapp.common.worker.mobile;

import javax.annotation.Resource;

import java.util.List;

import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManagerEntity;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.AbstractAutowireWorker;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.AfterCommitEventListener;
import com.lwxf.industry4.webapp.baseservice.tsmanager.base.BeforeUpdateEventListener;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.enums.SQLType;
import com.lwxf.industry4.webapp.common.mobile.weixin.service.IMsgService;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;

import static com.lwxf.industry4.webapp.common.utils.WebUtils.getDataFromRequestMap;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 17:35:55
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
//@Component("userThirdInfoWorker")
public class UserThirdInfoWorker extends AbstractAutowireWorker implements AfterCommitEventListener, BeforeUpdateEventListener {
	private static final String DATA_CACHE_KEY_WX_USER_THIRD_INFO = "wx_user_third_info";
	@Resource(name = "weixinTemplateMsgService")
	private IMsgService templateMsgService;
	@Resource
	private UserThirdInfoService userThirdInfoService;

	@Override
	public void beforeUpdate(TSManagerEntity tsManagerEntity, SQLType sqlType) {
		if (null == tsManagerEntity) {
			return;
		}

		if (tsManagerEntity.getClazz() != UserThirdInfo.class) {
			return;
		}

		if (getDataFromRequestMap("WX_UNBIND_BY_USERID") != null) {
			UserThirdInfo userThirdInfo = userThirdInfoService.findByUserId(getDataFromRequestMap("WX_UNBIND_BY_USERID").toString());
			WebUtils.putDataToRequestMap(DATA_CACHE_KEY_WX_USER_THIRD_INFO, userThirdInfo);
		}
	}

	@Override
	public void afterCommit(List<TSManagerEntity> tsManagerEntitys) {

	}
}
