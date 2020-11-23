package com.lwxf.industry4.webapp.facade;


import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;

import com.aliyuncs.IAcsClient;
import com.github.binarywang.wxpay.service.WxPayService;
import com.yunpian.sdk.YunpianClient;

import com.lwxf.commons.uniquekey.IdGererateFactory;
import com.lwxf.industry4.webapp.baseservice.cache.RedisUtils;
import com.lwxf.industry4.webapp.baseservice.cache.SyncGetByRedis;
import com.lwxf.industry4.webapp.baseservice.rongcloud.service.SendSystemMessageService;
import com.lwxf.industry4.webapp.baseservice.security.csrf.CsrfService;
import com.lwxf.industry4.webapp.bizservice.branch.BranchService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.product.ProductCategoryService;
import com.lwxf.industry4.webapp.bizservice.product.ProductColorService;
import com.lwxf.industry4.webapp.bizservice.product.ProductMaterialService;
import com.lwxf.industry4.webapp.bizservice.product.ProductSpecService;
import com.lwxf.industry4.webapp.bizservice.reservation.ReservationService;
import com.lwxf.industry4.webapp.bizservice.system.*;
import com.lwxf.industry4.webapp.bizservice.user.UserExtraService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.component.BaseFileUploadComponent;
import com.lwxf.industry4.webapp.common.jmail.JMailService;
import com.lwxf.industry4.webapp.common.mobile.weixin.WeixinCfg;
import com.lwxf.industry4.webapp.common.mobile.weixin.service.IMsgService;
import com.lwxf.industry4.webapp.common.mobile.weixin.service.JsonMsgService;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.Configuration;
import com.lwxf.industry4.webapp.common.utils.LocalMessageSourceUtil;
import com.lwxf.industry4.webapp.common.utils.SpringContextUtil;
import com.lwxf.industry4.webapp.config.RongCloudCfg;
import com.lwxf.industry4.webapp.facade.mobile.WeixinFacade;
import com.lwxf.industry4.webapp.facade.user.UserFacade;
import com.lwxf.industry4.webapp.facade.user.UserThirdInfoFacade;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 10:44:32
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public abstract class AppBeanInjector {
	/**
	 * 配置及工具注入
	 */
	public static final Configuration configuration;
	public static final LocalMessageSourceUtil i18nUtil;
	public static final IdGererateFactory idGererateFactory;
	// redis
	public static final RedisUtils redisUtils;
	public static final SyncGetByRedis syncGetByRedis;
	// 微信
	public static final WeixinCfg weixinCfg;
	public static final WxMpConfigStorage wxMpConfigStorage;
	public static final WxMpService wxMpService;
	public static final JsonMsgService weixinJsonMsgService;
	public static final WxPayService wxPayService;
	// 文件上传
	public static final BaseFileUploadComponent baseFileUploadComponent;
	public static final IMsgService weixinTemplateMsgService;

	// mq
	/*public static final RabbitMQSender rabbitMQSender;
	public static final RabbitMQProperties rabbitMQProperties;*/
	// 手机短信
	public static final IAcsClient smsService;
	// 编号生成器
	public static final UniquneCodeGenerator uniquneCodeGenerator;

	// 融云配置
	public static final RongCloudCfg rongCloudCfg;
	public static final SendSystemMessageService sendSystemMessageService;

	// 云片网
	public static final YunpianClient yunpianClient;



	/**
	 * service 注入
	 */
	public static final JMailService jMailService;
	public static final UserService userService;
	public static final CsrfService csrfService;
	public static final UserExtraService userExtraService;
	public static final UserThirdInfoService userThirdInfoService;
	public static final ReservationService reservationService;
	public static final CompanyService companyService;
	public static final BranchService branchService;
	public static final CompanyEmployeeService companyEmployeeService;
	public static final MenusService menusService;
	public static final OperationsService operationsService;
	public static final ProductCategoryService productCategoryService;
	public static final ProductMaterialService productMaterialService;
	public static final ProductSpecService productSpecService;
	public static final ProductColorService productColorService;
	public static final RoleService roleService;
	public static final DeptService deptService;
	public static final RolePermissionService rolePermissionService;
	public static final BasecodeService basecodeService;
	public static final StorageService storageService;

	/**
	 * facade注入
	 */
	public static final UserFacade userFacade;
	public static final UserThirdInfoFacade userThirdInfoFacade;
	public static final WeixinFacade weixinFacade;


	static {
		// 工具及配置类
		configuration = (Configuration) SpringContextUtil.getBean("configuration");
		i18nUtil = (LocalMessageSourceUtil) SpringContextUtil.getBean("i18nUtil");
		idGererateFactory = (IdGererateFactory) SpringContextUtil.getBean("idGererateFactory");
		jMailService = (JMailService) SpringContextUtil.getBean("jMailService");
		weixinCfg = (WeixinCfg) SpringContextUtil.getBean("weixinCfg");
		wxMpService = (WxMpService) SpringContextUtil.getBean("wxMpService");
		wxMpConfigStorage = (WxMpConfigStorage)SpringContextUtil.getBean("wxMpConfigStorage");
		weixinJsonMsgService = (JsonMsgService) SpringContextUtil.getBean("weixinJsonMsgService");
		/*rabbitMQSender = (RabbitMQSender) SpringContextUtil.getBean("rabbitMQSender");
		rabbitMQProperties = (RabbitMQProperties) SpringContextUtil.getBean("rabbitMQProperties");*/
		smsService = (IAcsClient)SpringContextUtil.getBean("iAcsClientService");
		redisUtils = (RedisUtils)SpringContextUtil.getBean("redisUtils");
		syncGetByRedis = (SyncGetByRedis) SpringContextUtil.getBean("syncGetByRedis");
		baseFileUploadComponent = (BaseFileUploadComponent) SpringContextUtil.getBean("baseFileUploadComponent");
		uniquneCodeGenerator = (UniquneCodeGenerator) SpringContextUtil.getBean("uniquneCodeGenerator");
		weixinTemplateMsgService = (IMsgService) SpringContextUtil.getBean("weixinTemplateMsgService");
		wxPayService = (WxPayService) SpringContextUtil.getBean("wxPayService");
		rongCloudCfg = (RongCloudCfg) SpringContextUtil.getBean("rongCloudCfg");
		sendSystemMessageService = (SendSystemMessageService) SpringContextUtil.getBean("sendSystemMessageService");
		yunpianClient = (YunpianClient) SpringContextUtil.getBean("yunpianClient");

		// service bean
		userService = (UserService) SpringContextUtil.getBean("userService");
		csrfService = (CsrfService) SpringContextUtil.getBean("csrfService");
		userExtraService = (UserExtraService) SpringContextUtil.getBean("userExtraService");
		userThirdInfoService = (UserThirdInfoService) SpringContextUtil.getBean("userThirdInfoService");
		reservationService = (ReservationService) SpringContextUtil.getBean("reservationService");
		companyEmployeeService = (CompanyEmployeeService) SpringContextUtil.getBean("companyEmployeeService");
		companyService = (CompanyService) SpringContextUtil.getBean("companyService");
		branchService = (BranchService) SpringContextUtil.getBean("branchService");
		menusService = (MenusService) SpringContextUtil.getBean("menusService");
		operationsService = (OperationsService) SpringContextUtil.getBean("operationsService");
		productCategoryService = (ProductCategoryService) SpringContextUtil.getBean("productCategoryService");
		productMaterialService = (ProductMaterialService) SpringContextUtil.getBean("productMaterialService");
		productSpecService = (ProductSpecService) SpringContextUtil.getBean("productSpecService");
		productColorService = (ProductColorService) SpringContextUtil.getBean("productColorService");
		roleService = (RoleService) SpringContextUtil.getBean("roleService");
		deptService = (DeptService) SpringContextUtil.getBean("deptService");
		rolePermissionService = (RolePermissionService) SpringContextUtil.getBean("rolePermissionService");
		basecodeService = (BasecodeService) SpringContextUtil.getBean("basecodeService");
		storageService = (StorageService) SpringContextUtil.getBean("storageService");
		// facade bean
		userFacade = (UserFacade) SpringContextUtil.getBean("userFacade");
		userThirdInfoFacade = (UserThirdInfoFacade) SpringContextUtil.getBean("userThirdInfoFacade");
		weixinFacade = (WeixinFacade) SpringContextUtil.getBean("weixinFacade");
	}
}
