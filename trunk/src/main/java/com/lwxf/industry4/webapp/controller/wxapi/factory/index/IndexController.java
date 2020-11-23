package com.lwxf.industry4.webapp.controller.wxapi.factory.index;

import com.alibaba.fastjson.JSONObject;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.cache.RedisAttackLock;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsController;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.VerificationCodeType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.WeiXin.HttpRequest;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.user.WxUserFacade;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Api(value="wxFIndexController",tags={"F端微信小程序接口:工厂端微信首页登录"})
@RestController("wxIndexController")
public class IndexController {
	private static final Logger logger = LoggerFactory.getLogger(SmsController.class);
	@Resource(name = "wxUserFacade")
	private WxUserFacade wxUserFacade;
	@ApiOperation("获取微信小程序的openid")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "code",value = "code码,登录凭证",dataType = "string",paramType = "query",required = true),
			@ApiImplicitParam(name = "encryptedData",value = "被加密的数据",dataType = "String",paramType = "query"),
			@ApiImplicitParam(name = "iv",value = "偏移量",dataType = "String",paramType = "query")
	})
	@RequestMapping(value = "/getOpenid", method = RequestMethod.GET)
	public Map getOpenid(@RequestParam String code,
										 @RequestParam(required = false) String encryptedData,
										 @RequestParam(required = false)String iv ) throws Exception{

		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		Map<String,Object> map = new HashMap<String,Object>();
		//code = "081ZExyD0qnP4j2LV5yD0hFLyD0ZExyK";
		//登录凭证不能为空
		if (code == null || code.length() == 0) {
			map.put("status", 0);
			map.put("msg", "code 不能为空");
			return map;
		}
		//小程序唯一标识   (在微信小程序管理后台获取)
		String wxspAppid = WebConstant.WX_APPID;
		//小程序的 app secret (在微信小程序管理后台获取)
		String wxspSecret = WebConstant.WX_APP_SECRET;
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
		//用户在微信平台的唯一标识
		String unionid=json.getString("unionid");
		//验证用户是否登录
		UserThirdInfo userThirdInfo = AppBeanInjector.userThirdInfoService.findByOpenId(openid);
		if(userThirdInfo==null){
			map.put("status", 0);
			map.put("msg", "用户未登录");
			map.put("openId",openid);
			map.put("unionid",unionid);
			return map;
		}
		User user = AppBeanInjector.userService.findById(userThirdInfo.getUserId());
		//添加unionid到系统用户表信息中
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",userThirdInfo.getUserId());
		mapContext.put("wxOpenId",openid);
		mapContext.put("wxUnionId",unionid);
		AppBeanInjector.userThirdInfoService.updateByMapContext(mapContext);
		UserExtra userExtra=AppBeanInjector.userExtraService.findById(userThirdInfo.getUserId());
		map.put("status",1);
		map.put("msg","用户已登录");
		map.put("appToken",userThirdInfo.getAppToken());
		map.put("type",user.getType());
		map.put("openId",openid);
		map.put("unionid",unionid);
		return map;
		//////////////// 2、对encryptedData加密数据进行AES解密 ////////////////
//		try {
//			String result = AesUtil.decrypt(encryptedData, session_key, iv, "UTF-8");
//			if (null != result && result.length() > 0) {
//				map.put("status", 1);
//				map.put("msg", "解密成功");
//
//				JSONObject userInfoJSON = JSONObject.parseObject(result);
//				Map<String,Object> userInfo = new HashMap<String,Object>();
//				userInfo.put("openId", userInfoJSON.get("openId"));
//				userInfo.put("nickName", userInfoJSON.get("nickName"));
//				userInfo.put("gender", userInfoJSON.get("gender"));
//				userInfo.put("city", userInfoJSON.get("city"));
//				userInfo.put("province", userInfoJSON.get("province"));
//				userInfo.put("country", userInfoJSON.get("country"));
//				userInfo.put("avatarUrl", userInfoJSON.get("avatarUrl"));
//				userInfo.put("unionId", userInfoJSON.get("unionId"));
//				map.put("userInfo", userInfo);
//				//System.out.println("map2:" + map);
//				return map;
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		map.put("status", 0);
//		map.put("msg", "解密失败");
//		//System.out.println("map3:" + map);

	}
	/**
	 * 用户手机号登录
	 * @param userMap
	 * @return
	 */
	@ApiOperation(value = "用户手机号登录",notes = "用户手机号登录")
	@RequestMapping(value = "/wxapi/f/users/login")
	public String userLogin(@RequestBody MapContext userMap) {
		RequestResult result = this.wxUserFacade.userLogin(userMap);
		JsonMapper mapper = JsonMapper.createAllToStringMapper();
		return mapper.toJson(result);
	}

	/**
	 * 登录名（邮箱/手机/登录名 ）和密码登录
	 *
	 * @param userMap
	 * @return
	 */
	@ApiOperation(value = "登录名（邮箱/手机/登录名 ）和密码登录",notes = "登录名（邮箱/手机/登录名 ）和密码登录")
	@PostMapping(value = "/users/password/login")
	public String userPasswordlogin(@RequestBody MapContext userMap) {
		RequestResult result = this.wxUserFacade.userPasswordLogin(userMap);
		JsonMapper mapper = JsonMapper.createAllToStringMapper();
		return mapper.toJson(result);
	}
	/**
	 * 获取验证码
	 *
	 */
	@ApiOperation(value = "获取验证码",notes = "获取验证码")
	@GetMapping("/users/{type}/phonenumbers/{phoneNumber}")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type",value = "type的值为（登录：login  注册：register 修改密码：uppassword）",dataType = "string",paramType = "path",required = true),
			@ApiImplicitParam(name = "phoneNumber",value = "手机号码",dataType = "String",paramType = "path",required = true)

	})
	public RequestResult sendSms(@PathVariable String type, @PathVariable String phoneNumber){
		RequestResult requestResult=null;
		String code ="";//测试屏蔽
		switch (type) {
			case  "login":
				requestResult = checkAttackLockMobile(phoneNumber,VerificationCodeType.LOGIN);
				if(requestResult!=null){
					return requestResult;
				}
				code =SmsUtil.sendMobileVerificationCode(phoneNumber,VerificationCodeType.LOGIN);
				break;
			case  "register":
				requestResult = checkAttackLockMobile(phoneNumber, VerificationCodeType.REGISTER);
				if(requestResult!=null){
					return requestResult;
				}
				code =SmsUtil.sendMobileVerificationCode(phoneNumber,VerificationCodeType.REGISTER);
				break;
			case  "uppassword": // 更新密码
				requestResult = checkAttackLockMobile(phoneNumber,VerificationCodeType.UPDATE_PASSWORD);
				if(requestResult!=null){
					return requestResult;
				}
				code =SmsUtil.sendMobileVerificationCode(phoneNumber,VerificationCodeType.UPDATE_PASSWORD);
				break;
			default: {
				logger.warn("发送手机短信，非法的请求类型!");
				return ResultFactory.generateErrorResult(ErrorCodes.SYS_ILLEGAL_ARGUMENT_00005,AppBeanInjector.i18nUtil.getMessage("SYS_ILLEGAL_ARGUMENT_00005"));
			}
		}

		return ResultFactory.generateRequestResult("发送成功！");
	}

	/**
	 * 验证验证码是否正确
	 *
	 * @param userMap
	 * @return
	 */
	@ApiOperation(value = "验证验证码是否正确",notes = "验证验证码是否正确")
	@PostMapping(value = "/users/authCode")
	public RequestResult authCode(@RequestBody MapContext userMap) {
		//RequestResult result = this.wxUserFacade.userPasswordLogin(userMap);
		RequestResult requestResult = this.wxUserFacade.userSmsCheck(userMap);
		JsonMapper mapper = JsonMapper.createAllToStringMapper();
		return requestResult;
	}



	/**
	 * 注册：手机短信码加防攻击锁
	 * @param mobile
	 * @return
	 */
	private RequestResult checkAttackLockMobile(String mobile,VerificationCodeType codeType){

		String ip = WebUtils.getClientIpAddress();
		String key = LwxfStringUtils.format(RedisConstant.CODE_ATTACK_LOCK_MOBILE_IP_TPL,codeType.getValue(),ip);
		boolean locked = RedisAttackLock.checkLocked(key,RedisConstant.CODE_ATTACK_LOCK_REGISTER_IP_LIMIT,RedisConstant.CODE_ATTACK_LOCK_REGISTER_IP_TIMEOUT, TimeUnit.DAYS);
		if(locked){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_CODE_IP_LIMIT_00021, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_CODE_IP_LIMIT_00021"));
		}else {
			String mobileKey = LwxfStringUtils.format(RedisConstant.CODE_ATTACK_LOCK_MOBILE_TPL,codeType.getValue(),mobile);
			boolean mobileLocked = 	RedisAttackLock.checkLocked(mobileKey,RedisConstant.CODE_ATTACK_LOCK_REGISTER_LIMIT,RedisConstant.CODE_ATTACK_LOCK_REGISTER_TIMEOUT,TimeUnit.DAYS);
			//return null;//TODO 测试屏蔽
			if(mobileLocked){
				return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_CODE_MOBILEOREMAIL_LIMIT_00022, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_CODE_MOBILEOREMAIL_LIMIT_00022"));
			}
		}
		return null;
	}
}
