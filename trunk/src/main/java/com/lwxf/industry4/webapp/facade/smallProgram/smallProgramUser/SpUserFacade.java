package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramUser;

import org.springframework.web.multipart.MultipartFile;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/17 0017 9:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface SpUserFacade extends BaseFacade {
	RequestResult findDealerInfo(String uid);

	RequestResult findLoginUserInfo(String uid,String cid);

	RequestResult updateMobile(String uid, String oldMobile, String newMobile);

	RequestResult updatePassword(String uid, String oldPassword, String newPassword);

	RequestResult findUserInfo(String uid, String cid);

	RequestResult updateUserInfo(String uid, MapContext mapContext);

	RequestResult forgetpassword(MapContext mapContext);

	RequestResult updateAvatar(String uid, MultipartFile multipartFile);

	RequestResult userlogout(String uid);

	RequestResult userLogin(MapContext userMap);

	RequestResult userPasswordLogin(MapContext userMap);
}
