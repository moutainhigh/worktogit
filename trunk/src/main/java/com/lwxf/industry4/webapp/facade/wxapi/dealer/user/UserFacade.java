package com.lwxf.industry4.webapp.facade.wxapi.dealer.user;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/6/14/014 10:19
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface UserFacade extends BaseFacade {
	RequestResult findDealerInfo(String uid);

	RequestResult findDealerUserInfo(String uid);

	RequestResult updateMobile(String uid,String oldMobile, String newMobile);

	RequestResult updatePassword(String uid, String oldPassword,String newPassword);

	RequestResult findHelps(String uid,String name,Integer pageNum,Integer pageSize);

	RequestResult findHelpsInfo(String id);

	RequestResult findUserInfo(String uid,String cid);

	RequestResult userlogout(String uid);

	RequestResult updateUserInfo(String uid, MapContext mapContext);

	RequestResult forgetpassword(MapContext mapContext);

	RequestResult updateAvatar(String uid, MultipartFile multipartFile);
}
