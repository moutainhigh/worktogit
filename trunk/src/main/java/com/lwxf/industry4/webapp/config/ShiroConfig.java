package com.lwxf.industry4.webapp.config;

import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.shiro.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.permission.WildcardPermissionResolver;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.Cookie;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 14:01:38
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Configuration
public class ShiroConfig {
	@Value("${shiro.rememberme.max.age:604800}")
	private Integer shiroRememberMeMaxAge;
	@Value("${shiro.session.redis.expire:10800}")
	private Integer shiroSessionRedisExpire;
	@Resource
	private com.lwxf.industry4.webapp.common.utils.Configuration configuration;

	@Bean
	public LwxfShiroRealm shiroRealm() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(LwxfShiroRealm.HASH_ALGORITHM);
		matcher.setHashIterations(LwxfShiroRealm.HASH_INTERATIONS);
		LwxfShiroRealm realm = new LwxfShiroRealm();
		WildcardPermissionResolver resolver = new WildcardPermissionResolver();
		realm.setPermissionResolver(resolver);
		realm.setCredentialsMatcher(matcher);
		return realm;
	}
//	@Bean(name = "sessionIdCookie")
//	public SimpleCookie getSessionIdCookie() {
//		SimpleCookie cookie = new SimpleCookie("sid");
////		cookie.setHttpOnly(true);
//		cookie.setMaxAge(-1);
////		cookie.setName("PHPSESSID");
//		return cookie;
//	}
	@Bean
	public Cookie simpleCookie(){
		SimpleCookie rememberMe = new SimpleCookie("rememberMe");
		rememberMe.setHttpOnly(true);
		rememberMe.setMaxAge(this.shiroRememberMeMaxAge);
		if(configuration.isOnProd())
			rememberMe.setSecure(true);
		return rememberMe;
	}

	@Bean
	public LwxfRememberMeManager rememberMeManager(){
		LwxfRememberMeManager rememberMeManager = new LwxfRememberMeManager();
		rememberMeManager.setCipherKey(org.apache.shiro.codec.Base64.decode("4AvVhmFLUs0KTA3Kprsdag=="));
		rememberMeManager.setCookie(simpleCookie());
		return rememberMeManager;
	}

	@Bean
	public SessionManager sessionManager(){
		DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
		sessionManager.setSessionIdCookieEnabled(true);
		sessionManager.setSessionIdUrlRewritingEnabled(false);
		//sessionManager.setSessionDAO(redisSessionDAO());
		return sessionManager;
	}

	@Bean
	public org.apache.shiro.mgt.SecurityManager securityManager(){
		DefaultWebSecurityManager securityManager =  new DefaultWebSecurityManager();
		securityManager.setRealm(this.shiroRealm());
		securityManager.setRememberMeManager(rememberMeManager());
//		securityManager.setSessionManager(sessionManager());  // TODO：暂时屏蔽sessionManger
		//securityManager.setCacheManager(this.cacheManager());
		return securityManager;
	}

	@Bean
	public ShiroFilterFactoryBean shiroFilterFactoryBean(org.apache.shiro.mgt.SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean  = new ShiroFilterFactoryBean();
		// 必须设置 SecurityManager
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//拦截器.
		Map<String,String> filterChainDefinitionMap = new LinkedHashMap<>();
		//配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
		filterChainDefinitionMap.put("/logout", "logout");
		filterChainDefinitionMap.put("/404", "anon");
		filterChainDefinitionMap.put("/flogin", "anon");

		// 匿名访问

		// 用户注册、激活、登录
		// 匿名url
		//TODO:数据库连接池监控
		filterChainDefinitionMap.put("/websocket/*", "anon");//socket接口
		filterChainDefinitionMap.put("/wxapi/b/aftersaleOrderDetailNoLogin/*", "anon");//不登陆直接查询订单详情接口
		filterChainDefinitionMap.put("/signup", "anon");
		filterChainDefinitionMap.put("/favicon.ico", "anon");
		filterChainDefinitionMap.put("/*.png", "anon");
		filterChainDefinitionMap.put("/*.jpg", "anon");
		filterChainDefinitionMap.put("/*.txt", "anon");
		filterChainDefinitionMap.put("/*.js", "anon");
		filterChainDefinitionMap.put("/config.json", "anon"); //客户端调用api配置文件
		filterChainDefinitionMap.put("/*.css", "anon");
		filterChainDefinitionMap.put("/images/**", "anon");
		filterChainDefinitionMap.put("/sitemap.xml", "anon");
		filterChainDefinitionMap.put("/*.txt", "anon");
		filterChainDefinitionMap.put("/news/**", "anon");
		filterChainDefinitionMap.put("/official/**", "anon");//公众号消息推送授权绑定

		filterChainDefinitionMap.put("/js/**", "anon");
		filterChainDefinitionMap.put("/css/**", "anon");
		filterChainDefinitionMap.put("/img/**", "anon");
		filterChainDefinitionMap.put("/fonts/**", "anon");
		filterChainDefinitionMap.put("/layui/**", "anon");

		filterChainDefinitionMap.put("/auth/user", "anon");


		filterChainDefinitionMap.put("/users/authCode", "anon");
		filterChainDefinitionMap.put("/auth/resource", "anon");

		filterChainDefinitionMap.put("/resetpass", "anon");
		filterChainDefinitionMap.put("/forgot", "anon");
		filterChainDefinitionMap.put("/resetpass/*", "anon");
		// 本地打包文件处理
		filterChainDefinitionMap.put("/*.js.map", "anon");

		// 匿名api
		// 公共api
		filterChainDefinitionMap.put("/app/commons/cities", "anon");
		filterChainDefinitionMap.put("/app/commons/updateVersions", "anon");//版本查询
		//swagger访问界面
		filterChainDefinitionMap.put("/swagger-ui.html", "anon");
		filterChainDefinitionMap.put("/webjars/**", "anon");
		filterChainDefinitionMap.put("/swagger-resources/**", "anon");
		filterChainDefinitionMap.put("/v2/**", "anon");
		filterChainDefinitionMap.put("/swagger-ui.html/**", "anon");

		filterChainDefinitionMap.put("/api/sys/touch", "anon");
		filterChainDefinitionMap.put("/api/site/touch", "anon");
		filterChainDefinitionMap.put("/api/users/login", "anon");
		filterChainDefinitionMap.put("/api/users/wxlogin", "anon");
		filterChainDefinitionMap.put("/api/users/register", "anon");
		filterChainDefinitionMap.put("/api/users/sharelogin", "anon");
		//用户忘记密码发的验证码
		filterChainDefinitionMap.put("/api/users/sendcode/forgot", "anon");
		filterChainDefinitionMap.put("/api/users/forgotcode/validate", "anon");

		// 站点的匿名路径
		filterChainDefinitionMap.put("/authCode", "anon");
		filterChainDefinitionMap.put("/dev/**", "anon");
		filterChainDefinitionMap.put("/p/**", "anon");
		filterChainDefinitionMap.put("/resources/**", "anon");
		filterChainDefinitionMap.put("/error", "anon");
		filterChainDefinitionMap.put("/WEB-INF/**", "anon");
		filterChainDefinitionMap.put("/api/wx", "anon");
		filterChainDefinitionMap.put("/api/ali/**", "anon");
		filterChainDefinitionMap.put("/api/wechat/**", "anon");
		filterChainDefinitionMap.put("/api/unionpay/**", "anon");
		filterChainDefinitionMap.put("/api/ercodepay/**", "anon");
		filterChainDefinitionMap.put("/wx/wxlogin", "anon");
		filterChainDefinitionMap.put("/wx/auth", "anon");
		//微信授权回调分发请求
		filterChainDefinitionMap.put("/wx/login", "anon");
		filterChainDefinitionMap.put("/app/login", "anon");
		filterChainDefinitionMap.put("/privacy", "anon");
		filterChainDefinitionMap.put("/terms", "anon");
		filterChainDefinitionMap.put("/privacy/", "anon");
		filterChainDefinitionMap.put("/terms/", "anon");


		filterChainDefinitionMap.put("/mall", "user"); // 商城首页及其他页面
		filterChainDefinitionMap.put("/mall/#/**", "user"); // 商城首页及其他页面

		//<!-- 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		filterChainDefinitionMap.put("/", "anon");
		filterChainDefinitionMap.put("/api/users/baseinfos", "anon");//获取用户的所有信息
		filterChainDefinitionMap.put("/common.js", "anon"); //测试api

		/**
		 * 预加载的api
		 */
		filterChainDefinitionMap.put("/api/preload/**", "anon"); //预加载



		/**
		 * 小程序路径
		 */
		filterChainDefinitionMap.put("/spapi/f/depts/**", "anon"); //小程序部门员工管理
		filterChainDefinitionMap.put("/spapi/f/messages/**", "anon");//小程序消息管理
		filterChainDefinitionMap.put("/spapi/f/orders/**", "anon");//小程序订单数据管理
		filterChainDefinitionMap.put("/spapi/f/searchs/**", "anon");//小程序搜索管理
		filterChainDefinitionMap.put("/spapi/f/users/**", "anon");//小程序个人信息管理


		//F端售后后台管理
		filterChainDefinitionMap.put("/api/f/aftersales/v2/aftersaleApplies","anon");//查询售后单列表
		filterChainDefinitionMap.put("/api/f/aftersales/v2/aftersaleApplies/*","anon");//查询售后单详情
		filterChainDefinitionMap.put("/api/f/aftersales/v2/dealers","anon");//查询经销商列表
		filterChainDefinitionMap.put("/api/f/aftersales/v2/dealers/*/customers","anon");//查询经销商客户列表
		filterChainDefinitionMap.put("/api/f/aftersales/v2/add","anon");//添加售后申请单
		filterChainDefinitionMap.put("/api/f/aftersales/v2/handle","anon");//处理售后单
		filterChainDefinitionMap.put("/api/f/aftersales/v2/aftersaleApplies/*/status/*","anon");//更新售后单状态
		filterChainDefinitionMap.put("/api/f/aftersales/v2/aftersaleApplies/*/addfiles","anon");//售后单上传
		filterChainDefinitionMap.put("//api/f/WxFactoryPurchase/**","anon");//售后单上传
		filterChainDefinitionMap.put("/api/f/menus/findPessionList","anon");//菜单权限

		//经销商物流公司
		filterChainDefinitionMap.put("/api/f/companyLogistics/**","anon");
		//经销商收货地址
		filterChainDefinitionMap.put("/api/f/companyAddress/**","anon");
		//订单产品管理
		filterChainDefinitionMap.put("/api/f/orderProduct/**","anon");
		//订单催款管理
		filterChainDefinitionMap.put("/api/f/customOrderRemind/**","anon");
		// 生产订单管理
		filterChainDefinitionMap.put("/api/f/produceorder/**", "anon");


		//微信B端经销商用户信息
		filterChainDefinitionMap.put("/wxapi/b/dealers/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/products/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/orders/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/companyCustomers/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/queryCoupleBack/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/SetCoupleBack/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/dealersInfo","anon");
		filterChainDefinitionMap.put("/wxapi/b/confirmationOfGoodsReceipt/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/querydealerCustom","anon");
		filterChainDefinitionMap.put("/wxapi/b/aftersaleOrder","anon");
		filterChainDefinitionMap.put("/wxapi/b/aftersaleOrderEdit","anon");
		filterChainDefinitionMap.put("/wxapi/b/aftersaleOrderCommit","anon");
		filterChainDefinitionMap.put("/wxapi/b/queryCoupleBackList","anon");
        filterChainDefinitionMap.put("/wxapi/b/deleteDealerOrderInfo/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/order/updatefiles/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/coupleBack/updatefiles/**","anon");
        filterChainDefinitionMap.put("/wxapi/b/deleteOrderFile/**","anon");
		filterChainDefinitionMap.put("/wxapi/b/companyAddress/**","anon"); // 经销商收货地址管理
		filterChainDefinitionMap.put("/wxapi/b/companyLogistics/**","anon"); // 经销商收货地址管理


        //拜访追踪

		filterChainDefinitionMap.put("/visitInfoController/addVisiInfo","anon");
		filterChainDefinitionMap.put("/visitInfoController/queryVisiInfoList","anon");
		filterChainDefinitionMap.put("/visitInfoController/uploadFileVisiInfo","anon");
		filterChainDefinitionMap.put("/visitInfoController/uploadFileVisiInfo2","anon");
		//im即时通讯联系人
		filterChainDefinitionMap.put("/chat/chatRecord/**","anon");
		//售后评价
		filterChainDefinitionMap.put("/EvaluteController/**","anon");
		//
		filterChainDefinitionMap.put("/wxdealer/DataChartController/**", "anon");
		filterChainDefinitionMap.put("/wxdealer/DealerChartController/**", "anon");
		filterChainDefinitionMap.put("/wxdealer/EmployeeManagerController/**", "anon");
		//附件/图片上传
		filterChainDefinitionMap.put("/attachFileController/**", "anon");

		filterChainDefinitionMap.put("/api/f/roles/findDealRolesByType", "anon");

		filterChainDefinitionMap.put("/api/f/branch/**", "anon");
		filterChainDefinitionMap.put("/api/f/corporatepartners/**", "anon");





		//日常账F端后台
		filterChainDefinitionMap.put("/api/f/payment_simples/**","anon");//简单记账管理
		filterChainDefinitionMap.put("/api/f/payment_simples","anon");//简单记账管理

		//F端经销商财务管理
		//filterChainDefinitionMap.put("/api/f/companyFinances/**","anon");
		//F端报表管理
		filterChainDefinitionMap.put("/api/f/dealerStatements/**", "anon");//经销商报表
		filterChainDefinitionMap.put("/api/f/paymentStatements/**", "anon");//财务报表
		filterChainDefinitionMap.put("/api/f/produceStatements/**", "anon");//生产报表
		filterChainDefinitionMap.put("/api/f/homeOrderCount", "anon"); //PC首页订单数据
		filterChainDefinitionMap.put("/api/f/myOrderCount", "anon"); //PC首页我的订单数据
		filterChainDefinitionMap.put("/api/f/finances/overview", "anon"); //查询财务审核统计


		//微信小程序F端产品管理
		filterChainDefinitionMap.put("/wxapi/f/products/**","anon");

		filterChainDefinitionMap.put("/api/f/ErpBigData/**","anon");//大数据报表接口

        //微信小程序F端  客户管理
		filterChainDefinitionMap.put("/wxapi/f/branchs/customers","anon");//微信小程序F端 终端客户列表
		filterChainDefinitionMap.put("/wxapi/f/branchs/customers/*","anon");//微信小程序F端 终端客户详情

        //微信小程序F端  客户管理
		filterChainDefinitionMap.put("/wxapi/f/branchs/customers","anon");//微信小程序F端 终端客户列表,客户添加
		filterChainDefinitionMap.put("/wxapi/f/branchs/customers/*","anon");//微信小程序F端 终端客户详情
		filterChainDefinitionMap.put("/wxapi/f/branchs/dealers/addCustomer","anon");//添加客户时的经销商列表接口
		filterChainDefinitionMap.put("/wxapi/f/branchs/dealers/*/employees","anon");//添加客户时的员工列表接口
		filterChainDefinitionMap.put("/wxapi/f/branchs/customers/*/orders","anon");//客户详情页，更多订单接口

		//微信小程序F端  经销商管理
		filterChainDefinitionMap.put("/wxapi/f/branchs/dealers","anon");//微信小程序F端 经销商列表,经销商添加
		filterChainDefinitionMap.put("/wxapi/f/branchs/dealers/*","anon");//微信小程序F端 经销商详情、修改
		filterChainDefinitionMap.put("/wxapi/f/branchs/dealers/coverImage","anon");//店铺封面添加

		//微信小程序F端  订单管理
		filterChainDefinitionMap.put("/wxapi/f/branchs/orders","anon");//微信小程序F端 订单列表
		filterChainDefinitionMap.put("/wxapi/f/branchs/orders/**","anon");//微信小程序F端 订单详情

		//微信小程序F端  我的管理
		filterChainDefinitionMap.put("/wxapi/f/branchs","anon");//微信小程序F端 我的页面接口
		filterChainDefinitionMap.put("/wxapi/f/branchs/info","anon");//个人信息接口
		filterChainDefinitionMap.put("/wxapi/f/branchs/mobile","anon");//换绑手机号
		filterChainDefinitionMap.put("/wxapi/f/branchs/password","anon");//修改当前用户密码
		filterChainDefinitionMap.put("/wxapi/f/branchs/helps","anon");//使用帮助接口
		filterChainDefinitionMap.put("/wxapi/f/branchs/helps/*","anon");//使用帮助问题详情
		filterChainDefinitionMap.put("/wxapi/f/branchs/userInfo","anon");//登录人数据预加载
		filterChainDefinitionMap.put("/wxapi/f/branchs/users","anon");//个人信息修改
		filterChainDefinitionMap.put("/wxapi/f/branchs/forgetpassword","anon");//忘记密码
		filterChainDefinitionMap.put("/wxapi/f/branchs/users/updateavatar","anon");//修改个人头像
		filterChainDefinitionMap.put("/wxapi/f/branchs/users/logout","anon");//退出登录
        //微信小程序F端
		filterChainDefinitionMap.put("/wxapi/f/users/login", "anon");//工厂端 用户登录
		filterChainDefinitionMap.put("/users/password/login", "anon");//工厂端 用户密码登录
		filterChainDefinitionMap.put("/users/*/phonenumbers/*", "anon");//工厂端 获取验证码

		//微信小程序F端 报表管理
		filterChainDefinitionMap.put("/wxapi/f/dealerStatements/**", "anon");//经销商报表（业务报表）
		filterChainDefinitionMap.put("/wxapi/f/aftersaleStatements/**", "anon");//售后报表
		filterChainDefinitionMap.put("/wxapi/f/customOrderStatements/**", "anon");//订单报表
		filterChainDefinitionMap.put("/wxapi/f/dispatchBillStatements/**", "anon");//发货报表
		filterChainDefinitionMap.put("/wxapi/f/designStatements/**", "anon");//设计报表
		filterChainDefinitionMap.put("/wxapi/f/finishStockStatements/**", "anon");//入库报表
		filterChainDefinitionMap.put("/wxapi/f/produceStatements/**", "anon");//生产报表

		//微信小程序B端 报表管理
		filterChainDefinitionMap.put("/wxapi/b/orderStatements/**", "anon");//B端订单报表
		filterChainDefinitionMap.put("/wxapi/b/aftersaleStatements/**", "anon");//B端售后报表




		filterChainDefinitionMap.put("/api/f/cityareas","anon");//工厂端 获取所有的地区信息
		filterChainDefinitionMap.put("/api/f/cities","anon");//工厂端
		filterChainDefinitionMap.put("/api/f/cities/*","anon");//工厂端

		filterChainDefinitionMap.put("/api/f/basecodes","anon");//工厂端 字典列表
		filterChainDefinitionMap.put("/api/f/basecodes/*","anon");//工厂端 字典 管理
		filterChainDefinitionMap.put("/api/f/basecodes/types/*","anon");//工厂端 字典 管理

		filterChainDefinitionMap.put("/api/sms/phonenumbers/*","anon");//工厂端 用户获取修改密码的验证码
		filterChainDefinitionMap.put("/api/f/users/login", "anon");//工厂端 用户登录
		filterChainDefinitionMap.put("/api/f/users/updateInsertUserRole", "anon");//更新或插入角色信息
		filterChainDefinitionMap.put("/api/f/users/getUserRole", "anon");//获取用户全部角色
		filterChainDefinitionMap.put("/api/f/users/updateRole", "anon");//更新角色信息
		filterChainDefinitionMap.put("/api/f/users/password", "anon");//工厂端 用户修改密码
		filterChainDefinitionMap.put("/api/f/users/info", "anon");//工厂端 查询用户信息
		filterChainDefinitionMap.put("/api/f/branch", "anon");//企业管理
		filterChainDefinitionMap.put("/api/f/company/logistics/*","anon");//工厂端物流公司管理

//		filterChainDefinitionMap.put("/api/f/dealers/**","anon");//工厂端经销商管理
		filterChainDefinitionMap.put("/api/f/customorders/inputexcel","anon");//订单信息导入
		filterChainDefinitionMap.put("/api/f/customorders/inputmaterial","anon");//原材料导入/inputsupply
		filterChainDefinitionMap.put("/api/f/customorders/inputsupply","anon");//供应商导入

		//工厂端活动管理
		filterChainDefinitionMap.put("/api/f/activity/**","anon");//工厂端活动管理列表
		//工厂端内容管理
		filterChainDefinitionMap.put("/api/f/contents/**","anon");
		filterChainDefinitionMap.put("/api/f/VipMemberController/**","anon");//经销商会员管理

		filterChainDefinitionMap.put("/api/f/findContents","anon");
		filterChainDefinitionMap.put("/api/f//findContentsById/**","anon");
		// 工厂端内容分类管理
		filterChainDefinitionMap.put("/api/f/contentTypes/**","anon");
		//工厂银行账户管理
		filterChainDefinitionMap.put("/api/f/bankAccounts/*","anon");
		filterChainDefinitionMap.put("/api/f/bankAccounts","anon");
		//财务科目管理
		filterChainDefinitionMap.put("/api/f/funds","anon");

		filterChainDefinitionMap.put("/api/f/customers","anon");//工厂端查询顾客

		filterChainDefinitionMap.put("/api/f/versions/**","anon");//版本更新

		filterChainDefinitionMap.put("/api/f/sys/errors", "anon");
		// 厂家后台的api全局拦截
//		filterChainDefinitionMap.put("/api/f/**", "user,factoryUrlFilter");


		//微信小程序F端  客户管理
		filterChainDefinitionMap.put("/wxapi/f/branchs/customers","anon");//微信小程序F端 终端客户列表
		filterChainDefinitionMap.put("/wxapi/f/branchs/customers/*","anon");//微信小程序F端 终端客户详情
		//微信小程序F端  财务管理
		filterChainDefinitionMap.put("/wxapi/f/paymentSimples","anon");//微信小程序F端 财务列表
		filterChainDefinitionMap.put("/wxapi/f/paymentSimples/viewIndex","anon");//微信小程序F端 财务首页
		filterChainDefinitionMap.put("/wxapi/f/paymentSimples/uploadImages","anon");//微信小程序F端 图片上传
		filterChainDefinitionMap.put("/wxapi/f/paymentSimples/*/paymentSimple","anon");//微信小程序F端 财务详情
		filterChainDefinitionMap.put("/wxapi/f/paymentSimples/*","anon");//微信小程序F端 财务详情

		//微信小程序F端  经销商消息管理
		filterChainDefinitionMap.put("/wxapi/f/companyMessages/messageList","anon");//微信小程序F端 终端客户列表
		filterChainDefinitionMap.put("/wxapi/f/companyMessages/messages/*","anon");////微信小程序F端 聊天详情
		filterChainDefinitionMap.put("/wxapi/f/companyMessages/sendMessage","anon");//微信小程序F端 终端客户详情
		filterChainDefinitionMap.put("/wxapi/f/companyMessages/companyList","anon");//微信小程序F端 终端客户详情
		//微信小程序B端  经销商消息管理
		filterChainDefinitionMap.put("/wxapi/b/companyMessages/messageList","anon");//微信小程序B端
		filterChainDefinitionMap.put("/wxapi/b/companyMessages/**","anon");//微信小程序B端
		//经销商首页
		filterChainDefinitionMap.put("/wxapi/b/dealer/**","anon");//微信小程序B端

		//微信api接口
		filterChainDefinitionMap.put("/wxapi/f/suppliers", "anon");
		filterChainDefinitionMap.put("/wxapi/f/suppliers/*", "anon");
		filterChainDefinitionMap.put("/wxapi/f/suppliers/uploadImages", "anon");
		//微信内容管理接口
		filterChainDefinitionMap.put("/wxapi/f/contents", "anon");
		filterChainDefinitionMap.put("/wxapi/f/contents/*", "anon");

		//微信小程序F端  售后管理
		filterChainDefinitionMap.put("/wxapi/f/aftersales/index","anon");//微信小程序F端 终端客户列表
		filterChainDefinitionMap.put("/wxapi/f/aftersales/*","anon");//微信小程序F端 终端客户详情
		//报表接口
		filterChainDefinitionMap.put("/wxapi/f/statements","anon");//微信小程序F端 报表接口
		filterChainDefinitionMap.put("/wxapi/f/customOrderStatements/**","anon");//微信小程序F端 订单报表接口
		filterChainDefinitionMap.put("/wxapi/f/paymentStatements/**","anon");//微信小程序F端 订单报表接口
		//报表接口
		filterChainDefinitionMap.put("/wxapi/f/dealer/index","anon");//微信小程序F端 经销商首页

		//PC后台接口放开
		filterChainDefinitionMap.put("/api/f/**","user");
        //PC经销商后台接口
		filterChainDefinitionMap.put("/api/b/**","user");

		//微信小程序获取openId接口
		filterChainDefinitionMap.put("/getOpenid", "anon");

		// 造易接口调用：测试用 TODO：造易对接开始后将删除
		filterChainDefinitionMap.put("/api/zy/users","anon");

		// 厂家的页面路径拦截
		filterChainDefinitionMap.put("/f*", "user");
		filterChainDefinitionMap.put("/f*/", "user");

		filterChainDefinitionMap.put("/**", "notFoundFilter");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
		shiroFilterFactoryBean.setLoginUrl(WebConstant.REDIRECT_PATH_FACTORY_LOGIN);
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/");
		//未授权界面;
		shiroFilterFactoryBean.setUnauthorizedUrl("/404");
		/**
		 * add filters
		 */
		Map<String, Filter> filterMap = shiroFilterFactoryBean.getFilters();
		filterMap.put("logout",new LogoutFilter(){{
			this.setRedirectUrl("flogin");
		}});
		filterMap.put("factoryUrlFilter",new LwxfFactoryShiroUrlFilter()); // 厂家的url过滤器
		//filterMap.put("dealerUrlFilter",new LwxfDealerShiroUrlFilter()); // 经销商的的url过滤器
		// 工厂端userFilter处理
		LwxfUserFilter lwxfUserFilter = new LwxfUserFilter();
		lwxfUserFilter.setLoginUrl(WebConstant.REDIRECT_PATH_FACTORY_LOGIN);
		filterMap.put("user",lwxfUserFilter);
		filterMap.put("notFoundFilter", new NotFoundFilter());
		//filterMap.put("adminFilter",new LwxfAdminFilter());

		return shiroFilterFactoryBean;
	}

	/*@Bean
	public RedisCacheManager cacheManager(){
		RedisCacheManager redisCacheManager = new RedisCacheManager();
		return redisCacheManager;
	}

	@Bean
	public RedisSessionDAO redisSessionDAO(){
		return new RedisSessionDAO(this.shiroSessionRedisExpire);
	}*/

	/*@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor(){
		return new LifecycleBeanPostProcessor();
	}*/
}
