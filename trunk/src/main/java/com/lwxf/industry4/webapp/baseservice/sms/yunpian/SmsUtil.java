package com.lwxf.industry4.webapp.baseservice.sms.yunpian;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yunpian.sdk.YunpianClient;
import com.yunpian.sdk.model.Result;
import com.yunpian.sdk.model.SmsSingleSend;

import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;
import com.lwxf.industry4.webapp.common.authcode.AuthCodeUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.cache.constant.RedisConstant;

/**
 * 功能：手机短信服务
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 14:04:44
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class SmsUtil {
	private static final Logger logger = LoggerFactory.getLogger(SmsUtil.class);
	/**
	 * 短信错误返回码
	 */
	private static Map<Integer,String> errCodeMap= new HashMap<Integer, String>(){
		{
			put(Integer.valueOf(1),"请求参数缺失");
			put(Integer.valueOf(2),"请求参数格式错误");
			put(Integer.valueOf(3),"账户余额不足");
			put(Integer.valueOf(4),"关键词屏蔽");
			put(Integer.valueOf(5),"未自动匹配到合适的模板");
			put(Integer.valueOf(6),"添加模板失败");
			put(Integer.valueOf(7),"模板不可用");
			put(Integer.valueOf(8),"同一手机号30秒内重复提交相同的内容");
			put(Integer.valueOf(9),"同一手机号5分钟内重复提交相同的内容超过3次");
			put(Integer.valueOf(10),"手机号防骚扰名单过滤");
			put(Integer.valueOf(11),"接口不支持GET方式调用");
			put(Integer.valueOf(12),"接口不支持POST方式调用");
			put(Integer.valueOf(13),"营销短信暂停发送");
			put(Integer.valueOf(14),"解码失败");
			put(Integer.valueOf(15),"签名不匹配");
			put(Integer.valueOf(16),"签名格式不正确");
			put(Integer.valueOf(17),"24小时内同一手机号发送次数超过限制");
			put(Integer.valueOf(18),"签名校验失败");
			put(Integer.valueOf(19),"请求已失效");
			put(Integer.valueOf(20),"不支持的国家地区");
			put(Integer.valueOf(21),"解密失败");
			put(Integer.valueOf(22),"1小时内同一手机号发送次数超过限制");
			put(Integer.valueOf(23),"发往模板支持的国家列表之外的地区");
			put(Integer.valueOf(24),"添加告警设置失败");
			put(Integer.valueOf(25),"手机号和内容个数不匹配");
			put(Integer.valueOf(26),"流量包错误");
			put(Integer.valueOf(27),"未开通金额计费");
			put(Integer.valueOf(28),"运营商错误");
			put(Integer.valueOf(33),"超过频率");
			put(Integer.valueOf(34),"签名创建失败");
			put(Integer.valueOf(40),"未开启白名单");
			put(Integer.valueOf(43),"一天内同一手机号发送次数超过限制");
			put(Integer.valueOf(48),"参数长度超过限制");
			put(Integer.valueOf(-1),"非法的apikey");
			put(Integer.valueOf(-2),"API没有权限");
			put(Integer.valueOf(-3),"IP没有权限");
			put(Integer.valueOf(-4),"访问次数超限");
			put(Integer.valueOf(-5),"访问频率超限");
			put(Integer.valueOf(-7),"HTTP请求错误");
			put(Integer.valueOf(-8),"不支持流量业务");
			put(Integer.valueOf(-50),"未知异常");
			put(Integer.valueOf(-51),"系统繁忙");
			put(Integer.valueOf(-52),"充值失败");
			put(Integer.valueOf(-53),"提交短信失败");
			put(Integer.valueOf(-54),"记录已存在");
			put(Integer.valueOf(-55),"记录不存在");
			put(Integer.valueOf(-57),"用户开通过固定签名功能，但签名未设置");
		}
	};

	private static final String TEMPLATE_REGISTER_SECURITY_CODE = "【老屋新房】欢迎使用{0}，您的验证码是{1}。如非本人操作，请忽略本短信";
	private static final String TEMPLATE_USER_ACCOUNT_INFO = "【老屋新房】尊敬的{0}，欢迎使用{1}系统，您的账号是：{2}，初始密码是：{3}，请尽快修改密码，如果非本人操作请忽略";
	/**
	 * 获取找回密码的验证码
	 * @param loginName
	 * @return
	 */
	/*public static String getForgotVerificationCode(String loginName) {
		//先去redis里面查询验证码是否生成
		String key = RedisConstant.SMS_REGISTER_CODE.concat(loginName);
		return redisUtils.getString(key);
	}*/


	/**
	 * 获取注册的验证码
	 * @param phoneNumber
	 * @return
	 */
	public static String getMobileVerificationCode(String phoneNumber,VerificationCodeType codeType) {
		//先去redis里面查询验证码是否生成
		String key = LwxfStringUtils.format(RedisConstant.SMS_KEY_MOBILE_VERIFICATION_CODE_TPL,codeType.getValue(),phoneNumber);
		return AppBeanInjector.redisUtils.getString(key);
	}

	/**
	 * 手机注册验证码
	 * @param phoneNumber
	 * @return
	 */
	public static String sendMobileVerificationCode(String phoneNumber,VerificationCodeType codeType){
		//先去redis里面查询验证码是否生成
		String key = LwxfStringUtils.format(RedisConstant.SMS_KEY_MOBILE_VERIFICATION_CODE_TPL,codeType.getValue(),phoneNumber);
		String code = AppBeanInjector.redisUtils.getString(key);
		if(LwxfStringUtils.isBlank(code)){
			//先取验证码，验证码存在，不再变化
			code = AuthCodeUtils.getRandomNumberCode(SmsConstant.VERIFICATION_CODE_LENGTH);
		}
		logger.info("短信验证码为:"+code);
		Map<String,String> params = new HashMap<>();
		String smsText = LwxfStringUtils.format(TEMPLATE_REGISTER_SECURITY_CODE,"红田家居掌上管家",code);
		params.put(YunpianClient.MOBILE,phoneNumber);
		params.put(YunpianClient.TEXT,smsText);
		//TODO 模拟发短信
		//AppBeanInjector.redisUtils.set(key,code,SmsConstant.VERIFICATION_CODE_EXPIRED, TimeUnit.MINUTES);
		//TODO 存入短信验证码
		Result<SmsSingleSend> sendResult =  AppBeanInjector.yunpianClient.sms().single_send(params);

		// 出现错误时输出错误信息
		if(sendResult.getCode()==0){
			AppBeanInjector.redisUtils.set(key,code,SmsConstant.VERIFICATION_CODE_EXPIRED, TimeUnit.MINUTES);
		}else{
			logger.error("********************* 发送注册验证码时出现错误");
			logger.error("********************* code = {}",sendResult.getCode());
			logger.error("********************* msg = {}",sendResult.getMsg());
			logger.error("********************* detail = {}",sendResult.getDetail());
			Throwable throwable = sendResult.getThrowable();
			if(null != throwable){
				logger.error("********************* throwable = {}",sendResult.getThrowable());
			}
		}
		return code;
	}

	/**
	 * 手机绑定验证码
	 * @param mobile
	 * @return
	 */
	/*public static String sendBindVerificationCode(String userId, String mobile){
		String code = AuthCodeUtils.getRandomNumberCode(SmsConstant.VERIFICATION_CODE_LENGTH);
		redisUtils.set(RedisConstant.MOBILE_BIND.concat(userId), mobile.concat("#").concat(code),SmsConstant.VERIFICATION_CODE_EXPIRED, TimeUnit.MINUTES);

		if(!AppBeanInjector.configuration.isOnFEPublic()){
			logger.debug("非生产环境未发送手机验证码:{}", code);
			return code;
		}

		String param = LwxfStringUtils.format(VERIFICATION_CODE_PARAM,code);
		SendSmsRequest request = new SendSmsRequest();
		request.setSignName(SmsConstant.COMMON_SIGN_NAME);
		String language = AppBeanInjector.i18nUtil.getLanguage();
		//中文语言环境,其他为英文
		if(SmsConstant.ZH_LANGUAGE.equalsIgnoreCase(language)){
			request.setTemplateCode(SmsConstant.REGISTER_VERIFICATION_CODE_TEMPLATE_CODE);
		}else {
			request.setTemplateCode(SmsConstant.REGISTER_VERIFICATION_CODE_TEMPLATE_CODE);
		}
		request.setPhoneNumbers(mobile);
		request.setTemplateParam(param);
		try {
			//连接主机的超时时间（单位：毫秒）
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			//从主机读取数据的超时时间（单位：毫秒）
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			SendSmsResponse sendSmsResponse = smsService.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && SmsConstant.SMS_SEND_CODE_OK.equalsIgnoreCase(sendSmsResponse.getCode())) {
				//请求成功:把值写入到redis中
			}else{
				//打印日志
				logger.error(errCodeMap.get(sendSmsResponse.getCode()));
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return code;
	}*/

	/**
	 * 发送身份认证验证码
	 * @param mobile
	 * @return
	 */
	/*public static void sendAuthenticationCode(String mobile,String code){
		*//*if(!AppBeanInjector.configuration.isOnFEPublic()){
			logger.debug("非生产环境未发送手机验证码:{}", code);
		}*//*
		String param = LwxfStringUtils.format(VERIFICATION_CODE_PARAM,code);
		SendSmsRequest request = new SendSmsRequest();
		request.setSignName(SmsConstant.COMMON_SIGN_NAME);
		String language = AppBeanInjector.i18nUtil.getLanguage();
		//中文语言环境,其他为英文
		if(SmsConstant.ZH_LANGUAGE.equalsIgnoreCase(language)){
			request.setTemplateCode(SmsConstant.REGISTER_VERIFICATION_CODE_TEMPLATE_CODE);
		}else {
			request.setTemplateCode(SmsConstant.REGISTER_VERIFICATION_CODE_TEMPLATE_CODE);
		}
		request.setPhoneNumbers(mobile);
		request.setTemplateParam(param);
		try {
			//连接主机的超时时间（单位：毫秒）
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			//从主机读取数据的超时时间（单位：毫秒）
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			SendSmsResponse sendSmsResponse = smsService.getAcsResponse(request);
			if((sendSmsResponse.getCode() != null && SmsConstant.SMS_SEND_CODE_OK.equalsIgnoreCase(sendSmsResponse.getCode()))) {
				//打印日志
				logger.error(errCodeMap.get(sendSmsResponse.getCode()));
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * 忘记密码验证码
	 * @param mobile
	 * @return
	 */
	/*public static String sendForgotVerificationCode(String mobile){
		//生成验证码
		String code = AuthCodeUtils.getRandomNumberCode(SmsConstant.VERIFICATION_CODE_LENGTH);
		String param = LwxfStringUtils.format(VERIFICATION_CODE_PARAM,code);
		SendSmsRequest request = new SendSmsRequest();
		request.setSignName(SmsConstant.COMMON_SIGN_NAME);
		String language = AppBeanInjector.i18nUtil.getLanguage();
		//中文语言环境,其他为英文
		if(SmsConstant.ZH_LANGUAGE.equalsIgnoreCase(language)){
			request.setTemplateCode(SmsConstant.REGISTER_VERIFICATION_CODE_TEMPLATE_CODE);
		}else {
			request.setTemplateCode(SmsConstant.REGISTER_VERIFICATION_CODE_TEMPLATE_CODE);
		}
		request.setPhoneNumbers(mobile);
		request.setTemplateParam(param);
		try {
			//连接主机的超时时间（单位：毫秒）
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");
			//从主机读取数据的超时时间（单位：毫秒）
			System.setProperty("sun.net.client.defaultReadTimeout", "30000");
			SendSmsResponse sendSmsResponse = smsService.getAcsResponse(request);
			if(sendSmsResponse.getCode() != null && SmsConstant.SMS_SEND_CODE_OK.equalsIgnoreCase(sendSmsResponse.getCode())) {
				//请求成功:把值写入到redis中
				redisUtils.set(RedisConstant.FORGOT_CODE_MOBILE.concat(mobile), code,RedisConstant.FORGOT_CODE_TIME_OUT, TimeUnit.MINUTES);
			}else{
				//打印错误日志
				logger.error(errCodeMap.get(sendSmsResponse.getCode()));
			}
		} catch (ClientException e) {
			e.printStackTrace();
		}
		return code;
	}*/
	/**
	 * 构建发送信息请求对象
	 * @param
	 * @return
	 */
	/*private SendSmsRequest buildSendSmsRequest(SmsTemplate smsTemplate){
		//组装请求对象-具体描述见控制台-文档部分内容
		SendSmsRequest request = new SendSmsRequest();
		//必填:待发送手机号
		request.setPhoneNumbers(smsTemplate.getPhoneNumbers());
		//必填:短信签名-可在短信控制台中找到
		request.setSignName(smsTemplate.getSignName());
		//必填:短信模板-可在短信控制台中找到
		request.setTemplateCode(smsTemplate.getTemplateCode());
		//可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
		request.setTemplateParam(smsTemplate.getTemplateParam());
		//可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
		request.setOutId(smsTemplate.getOutId());
		return request;
	}*/

	/*public static void main(String[] args) {
		String phoneNumber = "15503717899";
		String code = sendRegisterVerificationCode(phoneNumber);
		System.out.println(code);
	}*/

	public static void sendDealerInfoMessage(String phoneNumber,String name,String password){
		Map<String,String> params = new HashMap<>();
		String smsText = LwxfStringUtils.format(TEMPLATE_USER_ACCOUNT_INFO,name,"红田家居掌上管家",phoneNumber,password);
		params.put(YunpianClient.MOBILE,phoneNumber);
		params.put(YunpianClient.TEXT,smsText);

		Result<SmsSingleSend> sendResult =  AppBeanInjector.yunpianClient.sms().single_send(params);
		// 出现错误时输出错误信息
		if(sendResult.getCode()==0){

		}else{
			logger.error("********************* 发送注册验证码时出现错误");
			logger.error("********************* code = {}",sendResult.getCode());
			logger.error("********************* msg = {}",sendResult.getMsg());
			logger.error("********************* detail = {}",sendResult.getDetail());
			Throwable throwable = sendResult.getThrowable();
			if(null != throwable){
				logger.error("********************* throwable = {}",sendResult.getThrowable());
			}
		}
	}
}
