package com.lwxf.industry4.webapp.baseservice.sms.yunpian;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.cache.RedisAttackLock;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/13/013 11:28
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController
@RequestMapping(value = "/api/sms")
public class SmsFactoryController {
	private static final Logger logger = LoggerFactory.getLogger(SmsFactoryController.class);

	@GetMapping("phonenumbers/{phoneNumber}")
	public RequestResult sendSms(@PathVariable String phoneNumber) {
		RequestResult requestResult = null;
		requestResult = checkAttackLockMobile(phoneNumber, VerificationCodeType.UPDATE_PASSWORD);
		if (requestResult != null) {
			return requestResult;
		}
		SmsUtil.sendMobileVerificationCode(phoneNumber, VerificationCodeType.UPDATE_PASSWORD);

		return ResultFactory.generateSuccessResult();
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
