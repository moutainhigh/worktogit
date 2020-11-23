package com.lwxf.industry4.webapp.bizservice.user.impl;


import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.base.BaseNoIdServiceImpl;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.enums.MQEventEnum;
import com.lwxf.industry4.webapp.common.mobile.WeixinUtils;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dao.user.UserThirdInfoDao;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.mybatis.utils.MapContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


@Service("userThirdInfoService")
public class UserThirdInfoServiceImpl extends BaseNoIdServiceImpl<UserThirdInfo,UserThirdInfoDao> implements UserThirdInfoService {
	private Logger logger = LoggerFactory.getLogger(UserThirdInfoServiceImpl.class);

	@Override
	@Resource(name = "userThirdInfoDao")
	public void setDao(UserThirdInfoDao dao) {
		this.dao = dao;
	}
	@Resource
	private UserService userService;

	@Override
	public void bindingWeixin(String orgUserId, UserThirdInfo weixinInfo){
		bindingWeixinTmp(orgUserId, weixinInfo);
	}

	private void bindingWeixinTmp(String orgUserId, UserThirdInfo weixinInfo) {
		User user = userService.findById(weixinInfo.getUserId());
		if(user == null){
			WeixinUtils.sendErrorMsgToUser(MQEventEnum.USER_WX_BOUND_ERROR, ErrorCodes.BIZ_USER_NOT_FOUND_10002, "绑定用户不存在",weixinInfo.getUserId());
			return;
		}

		//查询微信绑定情况
		//注：此处判断换成unionid判断，因为网页注册用户的openid此时并未写入数据库，但此时不应该允许绑定其他账号
		if(LwxfStringUtils.isBlank(weixinInfo.getWxUnionId())){
			logger.error("此微信公众账号未绑定微信开放平台账号");
			return;
		}
		List<UserThirdInfo> userThirdInfoList = this.dao.selectByWxUnionId(weixinInfo.getWxUnionId());
		if(!userThirdInfoList.isEmpty()){
			UserThirdInfo bindUserInfo = null;
			for (UserThirdInfo info : userThirdInfoList){
				if(info.getWxIsBind()){
					bindUserInfo = info;
					break;
				}
			}

			if(bindUserInfo != null){
				if(!bindUserInfo.getUserId().equals(weixinInfo.getUserId())){
					MQEventEnum eventEnum = MQEventEnum.USER_WX_BOUND_OTHER_ERROR;
					WeixinUtils.sendErrorMsgToUser(eventEnum, ErrorCodes.BIZ_MB_WEIXIN_BOUND_10026, "您的微信账号已与其他账号绑定", weixinInfo.getUserId());
					return;
				}
			}
		}

		UserThirdInfo userThirdInfo = this.dao.selectByUserId(user.getId());

		//用户未绑定第三方账号
		if(userThirdInfo == null){
			//插入保存
			weixinInfo.setWxIsSubscribe(Boolean.TRUE);
			weixinInfo.setWxIsBind(Boolean.TRUE);
			weixinInfo.setEmailIsBind(Boolean.FALSE);
			weixinInfo.setMobileIsBind(Boolean.FALSE);
			this.dao.insert(weixinInfo);
		}else {
			//更新微信绑定信息
			MapContext mapContext = MapContext.newOne();
			mapContext.put("userId", user.getId());
			mapContext.put("wxOpenId", weixinInfo.getWxOpenId());
			mapContext.put("wxUnionId", weixinInfo.getWxUnionId());
			mapContext.put("wxIsSubscribe", Boolean.TRUE);
			mapContext.put("wxIsBind", Boolean.TRUE);
			mapContext.put("wxNickname", weixinInfo.getWxNickname());

			this.dao.updateByMapContext(mapContext);
		}
	}

	@Override
	public void unSubscribeByWxOpenId(String wxOpenId) {
		//关闭消息通知
		/* TODO：暂时不考虑微信
		List<UserThirdInfo> userThirdInfoList = userThirdInfoDao.selectByWxOpenId(wxOpenId);
		if(CollectionUtils.isEmpty(userThirdInfoList)){
			return;
		}
		UserThirdInfo thirdInfo = userThirdInfoList.get(0);
		String userId = thirdInfo.getUserId();
		User user = userService.findById(userId);
		//判断是否是店主取消关注  如果是则只取消微信的绑定状态
		MapContext  mapContext = MapContext.newOne();
		mapContext.put("wxIsBind",Boolean.FALSE);
		//更新关注状态
		mapContext.put("wxOpenId", wxOpenId);
		mapContext.put("wxIsSubscribe", Boolean.FALSE);
		this.userThirdInfoDao.updateByWxOpenId(mapContext);
		int userRole = user.getRole().intValue();

		if(userRole==UserRole.SHOPKEEPER.getValue()){
			return;
		}


		mapContext = MapContext.newOne();
		// 更新用户的状态为禁用
		if(userRole != UserRole.MEMBER.getValue()){
			mapContext.put(WebConstant.KEY_ENTITY_ROLE,UserRole.MEMBER.getValue());
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,thirdInfo.getUserId());
		mapContext.put(WebConstant.KEY_ENTITY_STATE,UserState.DISABLED.getValue());

		// 整理发送微信消息的数据
		Map<String,Object> userLeaveData = new HashMap<>();
		userLeaveData.put("user",user);
		userLeaveData.put("tousers",this.userThirdInfoDao.findAllClerks());
		WebUtils.putDataToRequestMap(WebConstant.WX_TEMPLATE_MSG_USER_LEAVE_DATA,userLeaveData);

		this.userService.updateByMapContext(mapContext);
		*/
	}

	@Override
	public void unbindWeixinByUserId(String userId) {
		WebUtils.putDataToRequestMap("WX_UNBIND_BY_USERID", userId);
		//更新微信状态
		MapContext mapContext = MapContext.newOne();
		mapContext.put("userId", userId);
		mapContext.put("wxIsBind", Boolean.FALSE);
		mapContext.put("wxIsSubscribe", Boolean.FALSE);

		mapContext.put("wxOpenId", null);
		mapContext.put("wxUnionId", null);
		mapContext.put("wxNickname", null);

		this.dao.updateByMapContext(mapContext);
	}

	@Override
	public List<UserThirdInfo> findByWxOpenId(String wxOpenId) {
		return this.dao.selectByWxOpenId(wxOpenId);
	}

	@Override
	public List<UserThirdInfo> findByWxUnionId(String wxUnionId) {
		return this.dao.selectByWxUnionId(wxUnionId);
	}

	@Override
	public UserThirdInfo findByUserId(String userId) {
		return this.dao.selectByUserId(userId);
	}

	@Override
	public List<UserThirdInfo> findAllWithNotBlankWxOpenId() {
		return dao.findAllWithNotBlankWxOpenId();
	}

	@Override
	public List<UserThirdInfo> findByUserIds(List<String> userIdList) {
		return dao.findByUserIds(userIdList);
	}

	@Override
	public int add(UserThirdInfo userThirdInfo) {
		return this.dao.insert(userThirdInfo);
	}

	@Override
	public int updateByMapContext(MapContext saveMap) {
		return this.dao.updateByMapContext(saveMap);
	}

	@Override
	public void updateByWxOpenId(MapContext mapContext) {
		this.dao.updateByWxOpenId(mapContext);
	}

	public  List<UserThirdInfo> findClerks(List<String> strList){
		return this.dao.findByUserIds(strList);
	};

	public List<UserThirdInfo> findAllClerks(String companyId){
		return this.dao.findAllClerks(companyId);
	}

	@Override
	public UserThirdInfo findByOpenId(String wxOpenId) {
		return this.dao.selectByOpenId(wxOpenId);
	}

	@Override
	public void deleteByOppenId(String wxOppenId) {
		 this.dao.deleteByOpenId(wxOppenId);
	}

	@Override
	public List<UserThirdInfo> findByAppTokenAndUserId(String appToken,String userId) {
		return this.dao.findByAppTokenAndUserId(appToken,userId);
	}

	@Override
	public UserThirdInfo findByAppToken(String appToken) {
		return this.dao.findByAppToken(appToken);
	}

	@Override
	public Object userlogout(MapContext mapContext) {
		return this.dao.userlogout(mapContext);
	}

	@Override
	public UserThirdInfo findUserByunionid(String unionid) {
		return this.dao.findUserByunionid(unionid);
	}
}