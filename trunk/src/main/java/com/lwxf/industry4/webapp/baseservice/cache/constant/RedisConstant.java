package com.lwxf.industry4.webapp.baseservice.cache.constant;

/**
 * 功能：Redis缓存配置静态Hash、Set...键名及前缀
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-06-12 12:17:16
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class RedisConstant {
	public static final String SEPARATOR = ":";
	/**
	 * 默认锁超时时长　单位：毫秒  当前设置30秒
	 */
	public static final int DEFAULT_LOCK_TIME_OUT = 30000;
	/**
	 * 单个：默认锁自动释放时长　单位：毫秒 当前设置1分钟
	 */
	public static final int DEFAULT_SINGLE_EXPIRE_TIME = 60000;
	/**
	 * 批量：默认锁自动释放时长　单位：毫秒 当前设置60秒
	 */
	public static final int DEFAULT_BATCH_EXPIRE_TIME = 60000;
	/**
	 * 锁key 前缀
	 */
	public static final String LOCK_PREFIX = "L:";
	/**
	 * 缓存默认失效天数
	 */
	public static final long CACHE_DEFAULT_EXPIRE = 30L;
	/***************************** Session ********************************/
	/**
	 * session名称
	 */
	public static final String EASYPM_SESSION = "S:";
	/**
	 * session默认过期时间:单位天
	 */
	public static final long SESSION_TIMEOUT = 30L;
	/***************************** APP级 ********************************/
	/***************************** 用户组织级 ***************************/

	public static final String PLATFORM_TAG = "PT";
	/***************************** 用户全局级 ***************************/
	/**
	 * 用户表
	 */
	public static final String USER = "USER";

	/**************************** 锁操作名定义 ************************/
	/**
	 * 手机验证码锁的key模版
	 */
	public static final String SMS_KEY_MOBILE_VERIFICATION_CODE_TPL = "sms:{0}:{1}";

	/**
	 * 用户注册防攻击码及数量控制
	 */
	public static final String CODE_ATTACK_LOCK_MOBILE_TPL ="code:{0}:{1}";
	public static final String CODE_ATTACK_LOCK_MOBILE_IP_TPL ="code:{0}:ip:{1}";
	public static final int CODE_ATTACK_LOCK_REGISTER_LIMIT =3;
	public static final int CODE_ATTACK_LOCK_REGISTER_IP_LIMIT =1000;
	public static final int CODE_ATTACK_LOCK_REGISTER_TIMEOUT =1;
	public static final int CODE_ATTACK_LOCK_REGISTER_IP_TIMEOUT =1;

	// 用户所能访问的公司缓存KEY
	public static final String USER_CAN_VIEW_COMPANY_KEY_PREFIX ="UC:";
}