package com.lwxf.industry4.webapp.facade.user;

import java.util.List;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.industry4.webapp.common.result.RequestResult;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 17:56:13
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserThirdInfoFacade extends BaseFacade {
	List<UserThirdInfo> findByWxOpenId(String wxOpenId);
	RequestResult findUserThirdInfo();
	UserThirdInfo findByUserId(String userId);
	String wxQRCodeScan(UserThirdInfo userThirdInfo);
	RequestResult fixUserWxUnionId();
	String doWxAuthLogin(String openId);

	/**
	 * 更新融云的用户token
	 * @param userId
	 * @param rongToken
	 */
	void updateRongToken(String userId,String rongToken);
}
