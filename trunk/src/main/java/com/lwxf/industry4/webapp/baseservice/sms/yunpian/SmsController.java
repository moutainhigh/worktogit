package com.lwxf.industry4.webapp.baseservice.sms.yunpian;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.cache.RedisAttackLock;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;

/**
 * 功能：发送手机消息外部调用服务
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 8:51:28
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/app/sms")
public class SmsController {
	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);
	@GetMapping("/{type}/phonenumbers/{phoneNumber}")
	public RequestResult sendSms(@PathVariable String type, @PathVariable String phoneNumber){
		RequestResult requestResult=null;
		String code = "";
		switch (type) {
			case  "login":
				requestResult = checkAttackLockMobile(phoneNumber,VerificationCodeType.LOGIN);
				if(requestResult!=null){
					return requestResult;
				}
				 code = SmsUtil.sendMobileVerificationCode(phoneNumber,VerificationCodeType.LOGIN);
				break;
			case  "register":
				requestResult = checkAttackLockMobile(phoneNumber,VerificationCodeType.REGISTER);
				if(requestResult!=null){
					return requestResult;
				}
				code = SmsUtil.sendMobileVerificationCode(phoneNumber,VerificationCodeType.REGISTER);
				break;
			case  "uppassword": // 更新密码
				requestResult = checkAttackLockMobile(phoneNumber,VerificationCodeType.UPDATE_PASSWORD);
				if(requestResult!=null){
					return requestResult;
				}
				code = SmsUtil.sendMobileVerificationCode(phoneNumber,VerificationCodeType.UPDATE_PASSWORD);
				break;
			default: {
				logger.warn("发送手机短信，非法的请求类型!");
				return ResultFactory.generateErrorResult(ErrorCodes.SYS_ILLEGAL_ARGUMENT_00005,AppBeanInjector.i18nUtil.getMessage("SYS_ILLEGAL_ARGUMENT_00005"));
			}
		}

		return ResultFactory.generateRequestResult("发送成功！code ="+code );
	}

	/**
	 * 密码找回手机短信码加防攻击锁
	 * @param mobile
	 * @return
	 */
	private RequestResult checkAttackLockForgot(String mobile){
		/*String ip = WebUtils.getClientIpAddress();
		String key = RedisConstant.CODE_ATTACK_LOCK_FORGOT_IP.concat(ip);
		boolean locked = RedisAttackLock.checkLocked(key,RedisConstant.CODE_ATTACK_LOCK_FORGOT_IP_LIMIT,RedisConstant.CODE_ATTACK_LOCK_FORGOT_IP_TIMEOUT,TimeUnit.DAYS);
		if(locked){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_CODE_IP_LIMIT_00021, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_CODE_IP_LIMIT_00021"));
		}else {
			String mobileKey = RedisConstant.CODE_ATTACK_LOCK_FORGOT.concat(mobile);
			boolean mobileLocked = 	RedisAttackLock.checkLocked(mobileKey,RedisConstant.CODE_ATTACK_LOCK_FORGOT_LIMIT,RedisConstant.CODE_ATTACK_LOCK_FORGOT_TIMEOUT,TimeUnit.DAYS);
			if(mobileLocked){
				return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_CODE_MOBILEOREMAIL_LIMIT_00022, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_CODE_MOBILEOREMAIL_LIMIT_00022"));
			}
		}*/
		return null;
	}

	/**
	 * 注册：手机短信码加防攻击锁
	 * @param mobile
	 * @return
	 */
	private RequestResult checkAttackLockMobile(String mobile,VerificationCodeType codeType){
		String ip = WebUtils.getClientIpAddress();
		String key = LwxfStringUtils.format(RedisConstant.CODE_ATTACK_LOCK_MOBILE_IP_TPL,codeType.getValue(),ip);
		boolean locked = RedisAttackLock.checkLocked(key,RedisConstant.CODE_ATTACK_LOCK_REGISTER_IP_LIMIT,RedisConstant.CODE_ATTACK_LOCK_REGISTER_IP_TIMEOUT,TimeUnit.DAYS);
		if(locked){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_CODE_IP_LIMIT_00021, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_CODE_IP_LIMIT_00021"));
		}else {
			String mobileKey = LwxfStringUtils.format(RedisConstant.CODE_ATTACK_LOCK_MOBILE_TPL,codeType.getValue(),mobile);
			boolean mobileLocked = 	RedisAttackLock.checkLocked(mobileKey,RedisConstant.CODE_ATTACK_LOCK_REGISTER_LIMIT,RedisConstant.CODE_ATTACK_LOCK_REGISTER_TIMEOUT,TimeUnit.DAYS);
			if(mobileLocked){
				return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_CODE_MOBILEOREMAIL_LIMIT_00022, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_CODE_MOBILEOREMAIL_LIMIT_00022"));
			}
		}
		return null;
	}
}
