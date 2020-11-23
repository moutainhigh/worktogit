package com.lwxf.industry4.webapp.common.constant;

public interface CommonConstant {

	/**
	 * 正常状态
	 */
	Integer STATUS_NORMAL = 0;

	/**
	 * 禁用状态
	 */
	Integer STATUS_DISABLE = -1;

	/**
	 * 删除标志
	 */
	Integer DEL_FLAG_1 = 1;

	/**
	 * 未删除
	 */
	Integer DEL_FLAG_0 = 0;

	/**
	 * 系统日志类型： 登录
	 */
	int LOG_TYPE_1 = 1;

	/**
	 * 系统日志类型： 操作
	 */
	int LOG_TYPE_2 = 2;
	
	
	/** {@code 500 Server Error} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_INTERNAL_SERVER_ERROR_500 = 500;
    /** {@code 200 OK} (HTTP/1.0 - RFC 1945) */
    public static final Integer SC_OK_200 = 200;

   /** 登录用户拥有角色缓存KEY前缀 */
    public static String LOGIN_USER_CACHERULES_ROLE = "loginUser_cacheRules::Roles_";
    /** 登录用户拥有权限缓存KEY前缀 */
    public static String LOGIN_USER_CACHERULES_PERMISSION  = "loginUser_cacheRules::Permissions_";
    /** 登录用户令牌缓存KEY前缀 */
    public static int  TOKEN_EXPIRE_TIME  = 3600; //3600秒即是一小时

    public static String PREFIX_USER_TOKEN  = "PREFIX_USER_TOKEN_";

    /**  登录的用户信息在redis中保存的记录前缀*/
    public static String PREFIX_LOGIN_USER_INFO="PREFIX_LOGIN_USER_INFO_";

    /**用户ID与用户名的映射*/
    public  static String PREFIX_USER_ID_TO_USERNAME_MAP="PREFIX_USER_ID_TO_USERNAME_MAP_";
    
    /**
     *  0：一级菜单
     */
    public static Integer MENU_TYPE_0  = 0;
   /**
    *  1：子菜单 
    */
    public static Integer MENU_TYPE_1  = 1;
    /**
     *  2：按钮权限
     */
    public static Integer MENU_TYPE_2  = 2;
    
    /**通告对象类型（USER:指定用户，ALL:全体用户）*/
    public static String MSG_TYPE_UESR  = "USER";
    public static String MSG_TYPE_ALL  = "ALL";
    
    /**发布状态（0未发布，1已发布，2已撤销）*/
    public static String NO_SEND  = "0";
    public static String HAS_SEND  = "1";
    public static String HAS_CANCLE  = "2";
    
    /**阅读状态（0未读，1已读）*/
    public static String HAS_READ_FLAG  = "1";
    public static String NO_READ_FLAG  = "0";
    
    /**优先级（L低，M中，H高）*/
    public static String PRIORITY_L  = "L";
    public static String PRIORITY_M  = "M ";
    public static String PRIORITY_H  = "H";

    /**
     * 短信模板方式  0 .登录模板、1.注册模板、2.忘记密码模板
     */
    public static final String SMS_TPL_TYPE_0  = "0";
    public static final String SMS_TPL_TYPE_1  = "1";
    public static final String SMS_TPL_TYPE_2  = "2";

    /**
     * 状态(0无效1有效)
     */
    public static final String STATUS_0 = "0";
    public static final String STATUS_1 = "1";

    /**
     * 同步工作流引擎1同步0不同步
     */
    public static final String ACT_SYNC_0 = "0";
    public static final String ACT_SYNC_1 = "1";

    /**
     * 消息类型1:通知公告2:系统消息
     */
    public static final String MSG_CATEGORY_1 = "1";
    public static final String MSG_CATEGORY_2 = "2";

    /**
     * 是否配置菜单的数据权限 1是0否
     */
    public static final Integer RULE_FLAG_0 = 0;
    public static final Integer RULE_FLAG_1 = 1;

    /**
     * 是否用户已被冻结 1(解冻)正常 2冻结
     */
    public static final Integer USER_UNFREEZE = 1;
    public static final Integer USER_FREEZE = 2;

    /**字典翻译文本后缀*/
    public static final String DICT_TEXT_SUFFIX = "_dictText";

    /**
     * 部门级别
     */
    /** 一级部门 */
    public static final Integer DEPT_LEVEL_1 = 1;
    /** 二级部门 */
    public static final Integer DEPT_LEVEL_2 = 2;

    /** 岗位 */
    public static final Integer DEPT_TYPE_1 = 1;

    /**
     *  聊天记录类型：1.字符串2.文件路径
     */
    public static final String CHAT_STR = "1";
    public static final String CHAT_FILEPAth = "2";
    /**
     * 是否已发送:1.已发送 2.未发送
     */
    public static final String CHAT_IS_SEND = "1";
    public static final String CHAT_NO_SEND = "2";

    /**
     * 默认公司账号限制
     */
    /** 公司（工厂）的账号数量限制 */
    public static final Integer MAX_FACTORY_ACCOUNT = 10;
    /** 公司（工厂）的经销商数量限制 */
    public static final Integer MAX_DEALER_ACCOUNT = 10;
    /** 公司（工厂）的一级部门数量限制 */
    public static final Integer MAX_FIRST_LEVEL_DEPART = 10;
    public static final String SHIRO_SINGLE_KEY = "SHIRO_SINGLE_KEY";
    public static final String SESSION_KEY = "SESSION_KEY";

}
