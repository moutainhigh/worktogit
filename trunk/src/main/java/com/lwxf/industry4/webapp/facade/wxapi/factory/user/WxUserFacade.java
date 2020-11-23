package com.lwxf.industry4.webapp.facade.wxapi.factory.user;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/17 0017 16:33
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface WxUserFacade extends BaseFacade {
	RequestResult userSmsCheck(MapContext userMap);

	RequestResult userLogin(MapContext userMap);


	RequestResult userPasswordLogin(MapContext userMap);

	RequestResult findDealerInfo(String uid);

	RequestResult findDealerUserInfo(String uid);

	RequestResult updateMobile(String uid,String oldMobile, String newMobile);

	RequestResult updatePassword(String uid, String oldPassword,String newPassword);

	RequestResult findHelps(String branchId,String uid,String name,Integer pageNum,Integer pageSize);

	RequestResult findHelpsInfo(String id);

	RequestResult findUserInfo(String currUserId,String cid);

	RequestResult updateUserInfo(String userId,MapContext mapContext);

	RequestResult forgetpassword(MapContext mapContext);

	RequestResult updateAvatar(String uid, MultipartFile multipartFile);

	RequestResult userlogout(String uid);

	RequestResult authCode(MapContext params);
}
