package com.lwxf.industry4.webapp.common.constant;

import com.lwxf.commons.constant.CommonConstant;

/**
 * 功能：与web相关的常量定义
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 9:41:07
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public final class WebConstant extends CommonConstant {
	public static final String FACTORY_NAME_CODE = "HT";
	public static final String REQUEST_HEADER_KEY_X_APP_TOKEN = "X-A-TOKEN";
	public static final String REQUEST_HEADER_X_P ="X-P";
	public static final String REQUEST_PARAMETER_KEY_APP_TOKEN = "atoken";
	public static final String REQUEST_PARAMETER_KEY_CID = "cid";
	public static final String REQUEST_PARAMETER_KEY_UID = "uid";
	/**
	 * 存储在当前线程中的缓存数据的key定义（存在WebUtil中的request map中）
	 */
	public static final String REQUEST_MAP_KEY_API_MEMBER_INSTANCE = "api_member_instance";
	public static final String REQUEST_MAP_KEY_API_RAP_INSTANCE = "api_rap_instance";

	public static final String REQUEST_METHOD_GET = "get";
	public static final String REQUEST_ACTION_CREATE = "create";
	public static final String REQUEST_ACTION_READ = "read";
	public static final String REQUEST_ACTION_UPDATE = "update";
	public static final String REQUEST_ACTION_DELETE = "delete";

	public static final String TIMEZONE_DEFAULT = "GMT+:08:00";
	public static final String LANGUAGE_DEFAULT = "zh-CN";
	public static final String REQUEST_RESULT_AJAX_EMPTY_OBJ = "{}";
	public static final String REDIRECT_PATH_TEMPLATE = "redirect:{0}";
	public static final String REDIRECT_PATH_ERROR = "/error";
	public static final String REDIRECT_PATH_404 = "/404";
	public static final String REDIRECT_PATH_MALL = "/mall";
	public static final String REDIRECT_PATH_FACTORY_ADMIN = "/fadmin";
	public static final String REDIRECT_PATH_FACTORY_LOGIN = "/flogin";
	public static final String REDIRECT_PATH_DEALER_ADMIN = "/badmin";
	public static final String REDIRECT_PATH_DEALER_LOGIN = "/blogin";
	public static final String PAGE_FACTORY_LOGIN = "flogin";
	public static final String PAGE_FACTORY_ADMIN = "fadmin";
	public static final String PAGE_FACTORY_HOME = "home";
	/**
	 * 验证码key register TODO:AuthCodeServlet重构生成逻辑
	 */
	public static final String SESSION_KEY_AUTHCODE_REGISTER ="authCode";

	public static final String SESSION_KEY_CURR_USER="s_curr_user";
	/**
	 * 在TsManager中用于手动处理业务的标记
	 */
	public static final String TSMANAGER_MANUAL_ACTION_FLAG="tsmanager_manual_action";

	/**
	 *  原easypm 微信公众平台配置路径
	 */
	public static final String WEIXIN_PATH_V1 = "https://free.easypm.cn/api/weixin";
	//埋数据标志点：为了新增组织时候以下mq组装消息忽略:组织增加成员、创建私人项目、私人看板、任务类型、企业文档目录
	public static final String MQ_ORG_ADD_IGNORE = "mq_org_add_ignore";

	/**
	 * mq token前缀
	 */
	public static final String MQ_TOKEN_PREFIX = "MQ:";

	/**
	 * key 定义
	 */
	// common key 定义
	public static final String KEY_COMMON_ADD="add";
	public static final String KEY_COMMON_EMAIL ="email";
	public static final String KEY_COMMON_LINK ="link";
	public static final String KEY_COMMON_CURR_PROJECT_ID ="currProjectId";
	public static final String KEY_COMMON_ORG_USER_ID ="orgUserId";

	public static final String KEY_ENTITY_ID = "id";
	public static final String KEY_ENTITY_USER_ID = "userId";
	public static final String KEY_ENTITY_STATE="state";
	public static final String KEY_ENTITY_ROLE="role";
	public static final String KEY_ENTITY_VISIBILITY="visibility";
	public static final String KEY_ENTITY_NAME="name";
	public static final String KEY_ENTITY_COLOR="color";
	public static final String KEY_ENTITY_DISABLED="disabled";
	public static final String KEY_ENTITY_BRANCH_ID="branchId";
	/**
	 * 预约单key定义
	 */
	public static final String KEY_ENTITY_PHONE="phone";
	public static final String KEY_ENTITY_STARTTIME="startTime";
	public static final String KEY_ENTITY_ENDTIME="endTime";

	//goods
	public static final String KEY_ENTITY_GOODS = "goods";
	/**
	 * 快享key定义
	 */
	public static final String KEY_ENTITY_CONTENT="content";
	public static final String KEY_ENTITY_CREATOR="creator";

	//order
	public static final String KEY_ENTITY_MEMBER_ID = "memberId";
	public static final String KEY_ENTITY_ADDRESS = "address";
	public static final String KEY_ENTITY_STORECONFIG = "storeConfig";
	public static final String KEY_ENTITY_STATUS = "status";
	public static final String KEY_ENTITY_DEFAULTED = "defaulted";
	public static final String KEY_ENTITY_PARENTID = "parentId";
	public static final String KEY_ENTITY_LEVELTYPE = "levelType";
	public static final String KEY_ENTITY_KEYWORDS = "keywords";
	public static final String KEY_ENTITY_UPDATED="updated";
	public static final String KEY_ENTITY_RECEIPTTIME="receiptTime";
	public static final String KEY_ENTITY_DESCR="descr";
	public static final String KEY_ENTITY_PAIDTIME="paidTime";
    public static final String KEY_ENTITY_WXMENUS="wxmenus";
	public static final String KEY_ENTITY_TRADENO="tradeNo";
	public static final String KEY_ENTITY_COMPANY_ID="companyId";
	public static final String KEY_ENTITY_APP_TOKEN="appToken";
	public static final String KEY_ENTITY_RONGCLOUD_TOKEN="rongcloudToken";

	public static final String KEY_ENTITY_LOGINNAME="loginName";
	public static final String KEY_ENTITY_LASTLOGIN="lastLogin";
	public static final String KEY_ENTITY_MOBILE="mobile";
	public static final String KEY_ENTITY_USERNAME="username";
	public static final String KEY_ENTITY_CITYAREAID="cityAreaId";
	public static final String KEY_ENTITY_AVATAR="avatar";
	public static final String KEY_ENTITY_RESOURCEID="resourceId";
	public static final String KEY_ENTITY_LINKMAN="linkman";
	public static final String KEY_ENTITY_SERVICECALL="serviceCall";
	public static final String KEY_ENTITY_CONFIRMED="confirmed";
	public static final String KEY_ENTITY_RESERVATION_ID="reservationId";
	public static final String KEY_ENTITY_PUBLISHED="published";
	public static final String KEY_ENTITY_HEADLINE_ID="headlineId";
	public static final String KEY_ENTITY_PRAISES="praises";
	public static final String KEY_ENTITY_PV="pv";
	public static final String KEY_ENTITY_COUNTREPLY="countReply";
	public static final String KEY_ENTITY_REPLYCOUNT="replyCount";
	public static final String KEY_ENTITY_CREATED="created";
	public static final String KEY_ENTITY_ROOT_ID="rootId";
	public static final String KEY_ENTITY_CATEGORY_ID="categoryId";
	public static final String KEY_ENTITY_MERGER_NAME="mergerName";

	/**
	 * 支付记录相关常量定义
	 */
	public static final String KEY_ENTITY_FUNDS="funds";

	public static final String KEY_RESET_PASSWORD_USER_ID = "RESET_PASSWORD_USER_ID";
	/**
	 * 获取日志相关常量定义
	 */
	public static final String KEY_ACTIVITY_REF_ID = "refId";
	public static final String KEY_ACTIVITY_GROUPING = "grouping";
	public static final String KEY_ACTIVITY_STATUS_NAME = "statusName";

	public static final String KEY_KANBAN_KBC_TASKS_REORDER = "kbcTasksReorder";
	public static final String KEY_BE_DELETE_ENTITY_ORGUSER = "beDeleteEntityOrgUser";

	public static final String KEY_USER_NOTIFY_TOP_TIME= "topTime";
	public static final String KEY_USER_NOTIFY_READED= "readed";

	public static final String KEY_PRELOAD_PERMISSIONS = "permissions";
	public static final String KEY_PRELOAD_USER = "user";
	public static final String KEY_PRELOAD_USER_ROLE = "userRole";
	public static final String KEY_PRELOAD_USER_MENU="userMenu";
	public static final String KEY_PRELOAD_COMPANY = "company";
	public static final String KEY_PRELOAD_COMPANIES = "companies";
	public static final String KEY_PRELOAD_COMPANY_EMPLOYEE = "companyEmployee";
	public static final String KEY_PRELOAD_COMPANY_SHARE_MEMBERS = "companyShareMembers";
	public static final String KEY_PRELOAD_SYSCFG = "syscfg";
	public static final String KEY_PRELOAD_STORECFG = "storecfg";
	public static final String KEY_PRELOAD_USER_THIRD_INFO = "userThirdInfo";
	public static final String KEY_PRELOAD_ORDER = "order";
	public static final String KEY_PRELOAD_DICTIONARIES = "dictionaries"; // 字典数据key
	public static final String KEY_PRELOAD_MENUS = "menus"; // 菜单
	public static final String KEY_PRELOAD_OPERATIONS = "operations"; // 操作按钮key
	public static final String KEY_PRELOAD_PRODUCT_CATEGORY = "productCategory"; // 产品分类
	public static final String KEY_PRELOAD_PRODUCT_MATERIAL = "productMaterial"; // 产品材质
	public static final String KEY_PRELOAD_PRODUCT_SPEC = "productSpec"; // 产品规格
	public static final String KEY_PRELOAD_PRODUCT_COLOR = "productColor"; // 产品颜色
	public static final String KEY_PRELOAD_ROLES = "roles"; // 系统角色
	public static final String KEY_PRELOAD_DEPTS = "depts"; // 部门
	public static final String KEY_BASE_CODE = "baseCode";//基础吗
	/**
	 * 微信支付
	 */
	public static final String KEY_WxPay_APPID = "appid";
	public static final String KEY_WxPay_MCH_ID = "mch_id";
	public static final String KEY_WxPay_DEVICE_INFO = "device_info";
	public static final String KEY_WxPay_NONCE_STR = "nonce_str";
	public static final String KEY_WxPay_SIGN = "sign";
	public static final String KEY_WxPay_BODY = "body";
	public static final String KEY_WxPay_OUT_TRADE_NO = "out_trade_no";
	public static final String KEY_WxPay_TOTAL_FEE = "total_fee";
	public static final String KEY_WxPay_SPBILL_CREATE_IP = "spbill_create_ip";
	public static final String KEY_WxPay_NOTIFY_URL = "notify_url";
	public static final String KEY_WxPay_TRADE_TYPE = "trade_type";
	public static final String KEY_WxPay_TRADE_OPENID = "openid";
	public static final String KEY_WxPay_OPENID = "openid";
	public static final String KEY_WxPay_KEY = "key";

	/**
	 * user
	 */
	public static final String KEY_USER_EXTRA_SALT="salt";
	public static final String KEY_USER_EXTRA_PASSWORD="password";
	public static final String KEY_USER_EXTRA_TOKEN="token";

	/**
	 *  设计案例
	 */
	public static final String KEY_SCHEME_SCHEMEID = "schemeId";
	public static final String KEY_SCHEME_PAGEVIEW = "pageView";

	/**
	 * 企业动态
	 */
	public static final String KEY_NEWS_HTML=".html";
	/**
	 * Shiro用key定义
	 */
	public static final String KEY_SHIRO_ORG_DISABLE_USER_PROJECTNEWASSIGNS = "org_disable_user_projectnewassigns";

	/**
	 * EasyPM新版的微信授权回调路径
	 */
	public static final String WEIXIN_OAUTH_REDIRECT_URL = "/wx/auth?wxOpenId={0}";

	/**
	 * 正则的主键匹配
	 */
	public static final String REG_ID_MATCH = "[a-zA-Z0-9]{12,13}";

	public static final String IP_DEFAULT = "0.0.0.0";
	public static final String USER_DEFAULT_PASSWORD = "lwxf@26370";

	public static final String WX_TEMPLATE_MSG_USER_LEAVE_DATA = "wx_template_msg_user_leave_data";

	public static final String WX_TEMPLATE_MSG_APPLY_SUCCESS = "wx_template_msg_apply_success";


	public static final String KEY_REDIS_CACHE_SAVE_REQUEST_URI="saveRequestURI";
	public static final String KEY_REDIS_CACHE_SAVE_REQUEST_QRY="saveRequestQRY";
	public static final String KEY_REDIS_PLATFORM_FLAG_APP="app";
	public static final String KEY_REDIS_PLATFORM_FLAG_NEWSTORE="newstore";

//	/**
//	 * 老屋新房微信小程序的appId和 secret
//	 */
//	public static final String WX_APPID="wx2f409a951129d9f7";
//	public static final String WX_APP_SECRET="b4e3d9a80ceb7dcf5acf2479ab9613d6";
//
//	/**
//	 * 老屋新房微信公众号的appId和 secret
//	 */
//	public static final String WX_OFFICIAL_APPID="wxd7ea3de533c8126d";
//	public static final String WX_OFFICIAL_APP_SECRET="b24edc87aa15f8b18f225ebc0ee5209e";

	/**
	 * 紅田微信小程序的appId和 secret
	 */
//	public static final String WX_APPID="wxb21f4c2f7f82fb0e";
//	public static final String WX_APP_SECRET="615b7399f6d65471608db9386674e840";

	/**
	 * 紅田微信公众号的appId和 secret
	 */
	public static final String WX_OFFICIAL_APPID="wxbad0d3ebdb4e26dc";
	public static final String WX_OFFICIAL_APP_SECRET="34ecaec44a4fef5e3c1511202ee32435";

	/**
	 * 哈尔滨紅田微信小程序的appId和 secret
	 */
    public static final String WX_APPID="wx651877c9cf728eaa";
	public static final String WX_APP_SECRET="21c3809ef24288187139f7c2f68e84bf";
	/**
	 * 默认选择
	 */
	public static final Byte CHECKED = 1;
	/** 售后订单费用 免费 */
	public static final Integer NO_CHARGE = 0;
	/** 售后订单费用 收费 */
	public static final Integer IS_CHARGE = 1;
	/** 禁用状态 */
	public static final Integer DISABLE = 0;
	/** 启用状态 */
	public static final Integer ENABLE = 1;

	private WebConstant(){

	}
}
