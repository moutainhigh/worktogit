package com.lwxf.industry4.webapp.controller.smallProgram.smallProgramUser;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.baseservice.cache.RedisAttackLock;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsController;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.VerificationCodeType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.WeiXin.HttpRequest;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.WxDealerUserInfoDto;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.smallProgram.smallProgramUser.SpUserFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/17 0017 9:49
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("SpUserController")
@RequestMapping(value = "/spapi/f/users", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "SpUserController", tags = {"小程序F端接口:我的信息管理"})
public class SpUserController {
	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);
	@Resource(name = "spUserFacade")
	private SpUserFacade spUserFacade;

	@ApiOperation("获取微信小程序的openid")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code", value = "code码,登录凭证", dataType = "string", paramType = "query", required = true),
			@ApiImplicitParam(name = "encryptedData", value = "被加密的数据", dataType = "String", paramType = "query"),
			@ApiImplicitParam(name = "iv", value = "偏移量", dataType = "String", paramType = "query")
	})
	@RequestMapping(value = "/getOpenid", method = RequestMethod.GET)
	public String getOpenid(@RequestParam String code,
							@RequestParam(required = false) String encryptedData,
							@RequestParam(required = false) String iv) throws Exception {

		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		Map<String, Object> map = new HashMap<String, Object>();
		//code = "081ZExyD0qnP4j2LV5yD0hFLyD0ZExyK";
		//登录凭证不能为空
		if (code == null || code.length() == 0) {
			map.put("status", 0);
			map.put("msg", "code 不能为空");
			return jsonMapper.toJson(map);
		}
		//小程序唯一标识   (在微信小程序管理后台获取)
		//String wxspAppid = WebConstant.WX_APPID;
		String wxspAppid = "wx7a533e1e6bbd31a8";
		//小程序的 app secret (在微信小程序管理后台获取)
		//String wxspSecret = WebConstant.WX_APP_SECRET;
		String wxspSecret = "c386f503800f33bfc059c6c3e2e703b7";
		//授权（必填）
		String grant_type = "authorization_code";
		// 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid ////////////////
		//请求参数
		String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&js_code=" + code + "&grant_type=" + grant_type;
		//发送请求
		String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/jscode2session", params);
		//解析相应内容（转换成json对象）
		JSONObject json = JSONObject.parseObject(sr);
		//获取会话密钥（session_key）
		//String session_key = json.get("session_key").toString();
		//用户的唯一标识（openid）
		String openid = (String) json.get("openid");
		System.out.println("1+++++++++++++++++++++++++" + openid);
		//验证用户是否登录
		UserThirdInfo userThirdInfo = AppBeanInjector.userThirdInfoService.findByOpenId(openid);
		if (userThirdInfo == null) {
			map.put("status", 0);
			map.put("msg", "用户未登录");
			map.put("openId", openid);
			return jsonMapper.toJson(map);
		}
		User user = AppBeanInjector.userService.findById(userThirdInfo.getUserId());
		UserExtra userExtra = AppBeanInjector.userExtraService.findById(userThirdInfo.getUserId());
		map.put("status", 1);
		map.put("msg", "用户已登录");
		map.put("appToken", userThirdInfo.getAppToken());
		map.put("type", user.getType());
		return jsonMapper.toJson(map);

	}

	/**
	 * 用户手机号登录
	 *
	 * @param userMap
	 * @return
	 */
	@ApiOperation(value = "用户手机号登录", notes = "用户手机号登录")
	@PostMapping(value = "/mobile/login")
	public String userLogin(@RequestBody MapContext userMap) {
		RequestResult result = this.spUserFacade.userLogin(userMap);
		JsonMapper mapper = JsonMapper.createAllToStringMapper();
		return mapper.toJson(result);
	}

	/**
	 * 登录名（邮箱/手机/登录名 ）和密码登录
	 *
	 * @param userMap
	 * @return
	 */
	@ApiOperation(value = "登录名（邮箱/手机/登录名 ）和密码登录", notes = "登录名（邮箱/手机/登录名 ）和密码登录")
	@PostMapping(value = "/password/login")
	public String userPasswordlogin(@RequestBody MapContext userMap) {
		RequestResult result = this.spUserFacade.userPasswordLogin(userMap);
		JsonMapper mapper = JsonMapper.createAllToStringMapper();
		return mapper.toJson(result);
	}

	/**
	 * 获取验证码
	 */
	@ApiOperation(value = "获取验证码", notes = "获取验证码")
	@GetMapping("/users/{type}/phonenumbers/{phoneNumber}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "type的值为（登录：login  注册：register 修改密码：uppassword）", dataType = "string", paramType = "path", required = true),
			@ApiImplicitParam(name = "phoneNumber", value = "手机号码", dataType = "String", paramType = "path", required = true)

	})
	public RequestResult sendSms(@PathVariable String type, @PathVariable String phoneNumber) {
		RequestResult requestResult = null;
		switch (type) {
			case "login":
				requestResult = checkAttackLockMobile(phoneNumber, VerificationCodeType.LOGIN);
				if (requestResult != null) {
					return requestResult;
				}
				SmsUtil.sendMobileVerificationCode(phoneNumber, VerificationCodeType.LOGIN);
				break;
			case "register":
				requestResult = checkAttackLockMobile(phoneNumber, VerificationCodeType.REGISTER);
				if (requestResult != null) {
					return requestResult;
				}
				SmsUtil.sendMobileVerificationCode(phoneNumber, VerificationCodeType.REGISTER);
				break;
			case "uppassword": // 更新密码
				requestResult = checkAttackLockMobile(phoneNumber, VerificationCodeType.UPDATE_PASSWORD);
				if (requestResult != null) {
					return requestResult;
				}
				SmsUtil.sendMobileVerificationCode(phoneNumber, VerificationCodeType.UPDATE_PASSWORD);
				break;
			default: {
				logger.warn("发送手机短信，非法的请求类型!");
				return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.SYS_ILLEGAL_ARGUMENT_00005, AppBeanInjector.i18nUtil.getMessage("SYS_ILLEGAL_ARGUMENT_00005"));
			}
		}

		return ResultFactory.generateRequestResult("发送成功！");
	}

	/**
	 * 注册：手机短信码加防攻击锁
	 *
	 * @param mobile
	 * @return
	 */
	private RequestResult checkAttackLockMobile(String mobile, VerificationCodeType codeType) {
		String ip = WebUtils.getClientIpAddress();
		String key = LwxfStringUtils.format(RedisConstant.CODE_ATTACK_LOCK_MOBILE_IP_TPL, codeType.getValue(), ip);
		boolean locked = RedisAttackLock.checkLocked(key, RedisConstant.CODE_ATTACK_LOCK_REGISTER_IP_LIMIT, RedisConstant.CODE_ATTACK_LOCK_REGISTER_IP_TIMEOUT, TimeUnit.DAYS);
		if (locked) {
			return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.SYS_ERROR_CODE_IP_LIMIT_00021, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_CODE_IP_LIMIT_00021"));
		} else {
			String mobileKey = LwxfStringUtils.format(RedisConstant.CODE_ATTACK_LOCK_MOBILE_TPL, codeType.getValue(), mobile);
			boolean mobileLocked = RedisAttackLock.checkLocked(mobileKey, RedisConstant.CODE_ATTACK_LOCK_REGISTER_LIMIT, RedisConstant.CODE_ATTACK_LOCK_REGISTER_TIMEOUT, TimeUnit.DAYS);
			if (mobileLocked) {
				return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.SYS_ERROR_CODE_MOBILEOREMAIL_LIMIT_00022, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_CODE_MOBILEOREMAIL_LIMIT_00022"));
			}
		}
		return null;
	}

	/**
	 * 个人信息接口
	 *
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "个人信息接口", response = WxDealerUserInfoDto.class)
	private String findDealerUserInfo() {
		JsonMapper jsonMapper = JsonMapper.createAllToStringMapper();
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		String cid =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(cid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		return jsonMapper.toJson(this.spUserFacade.findLoginUserInfo(uid,cid));
	}

	/**
	 * 换绑手机号
	 *
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/mobile")
	@ApiOperation(value = "换绑手机号")
	private RequestResult updateMobile(@RequestBody MapContext mapContext) {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		List<Map<String, String>> error = new ArrayList<Map<String, String>>();
		String oldMobile = mapContext.getTypedValue("oldMobile", String.class);
		if (oldMobile == null) {
			Map errorMap = new HashMap();
			errorMap.put("oldMobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			error.add(errorMap);
		} else {
			//验证电话号码是否正确
			if (!ValidateUtils.isChinaPhoneNumber(oldMobile)) {
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put("oldMobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, oldMobile);
			}
		}
		String newMobile = mapContext.getTypedValue("newMobile", String.class);
		if (newMobile == null) {
			Map errorMap = new HashMap();
			errorMap.put("newMobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			error.add(errorMap);
		} else {
			//验证电话号码是否正确
			if (!ValidateUtils.isChinaPhoneNumber(newMobile)) {
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put("newMobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, newMobile);
			}
		}
		if (error.size() != 0) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, error);
		}
		// 验证手机验证码有效性
		String oldMobileCode = mapContext.getTypedValue("smsCode", String.class);
		//从缓存中取短信验证码
		String serverOldMobileCode = SmsUtil.getMobileVerificationCode(oldMobile, VerificationCodeType.LOGIN);
		if (serverOldMobileCode == null || !serverOldMobileCode.equals(oldMobileCode)) {
			Map<String, String> errorMap = new HashMap<>();
			errorMap.put("smsCode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_AUTHCODE_ERROR_20024"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
		}
		return this.spUserFacade.updateMobile(uid, oldMobile, newMobile);
	}


	/**
	 * 修改当前用户密码
	 *
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/password")
	@ApiOperation(value = "修改当前用户密码")
	private RequestResult updatePassword(@RequestBody MapContext mapContext) {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String newPassword = mapContext.getTypedValue("newPassword", String.class);
		String confirmPassword = mapContext.getTypedValue("confirmPassword", String.class);
		String oldPassword = mapContext.getTypedValue("oldPassword", String.class);
		//判断两次密码是否一致
		if (!newPassword.equals(confirmPassword)) {
			MapContext error = new MapContext();
			error.put("confirmPassword", AppBeanInjector.i18nUtil.getMessage("VALIDATE_PASSWORD_IS_NOT_CONSISTENT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, error);
		}
		return this.spUserFacade.updatePassword(uid, oldPassword, newPassword);
	}


	/**
	 * 登录人信息预加载接口
	 */
	@ApiOperation(value = "登录人信息预加载接口", notes = "登录人信息预加载接口")
	@GetMapping("/userInfo")
	public RequestResult findUserInfo() {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String cid = mapInfo.get("companyId") == null ? null : mapInfo.get("companyId").toString();
		if (cid == null) {
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.spUserFacade.findUserInfo(uid, cid);
	}

	/**
	 * 修改个人信息
	 */
	@ApiOperation(value = "修改个人信息", notes = "修改个人信息")
	@PutMapping("/users")
	private RequestResult updateUserInfo(
			@RequestBody MapContext mapContext) {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.spUserFacade.updateUserInfo(uid, mapContext);
	}

	/**
	 * 忘记密码
	 */
	@ApiOperation(value = "忘记密码", notes = "忘记密码")
	@PutMapping("/forgetpassword")
	private RequestResult forgetpassword(@RequestBody MapContext mapContext) {
		return this.spUserFacade.forgetpassword(mapContext);
	}

	/**
	 * 修改个人头像
	 *
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "修改个人头像", notes = "修改个人头像")
	@PutMapping(value = "/users/updateavatar")
	public RequestResult updateAvatar(@RequestBody MultipartFile multipartFile
	) {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		Map<String, Object> errorInfo = new HashMap<>();
		if (multipartFile == null) {
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		} else if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		} else if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
			return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize()));
		}
		if (errorInfo.size() > 0) {
			return ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.VALIDATE_ERROR, errorInfo);
		}
		return this.spUserFacade.updateAvatar(uid, multipartFile);
	}

	@ApiOperation(value = "用户退出账号", notes = "用户退出账号")
	@PutMapping("/users/logout")
	private RequestResult userLogout() {
		String atoken = WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid = mapInfo.get("userId") == null ? null : mapInfo.get("userId").toString();
		if (uid == null) {
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.spUserFacade.userlogout(uid);
	}
}
