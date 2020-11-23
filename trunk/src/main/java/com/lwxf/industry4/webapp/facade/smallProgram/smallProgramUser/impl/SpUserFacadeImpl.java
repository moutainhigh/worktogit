package com.lwxf.industry4.webapp.facade.smallProgram.smallProgramUser.impl;

import sun.misc.BASE64Decoder;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.constant.CommonConstant;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.VerificationCodeType;
import com.lwxf.industry4.webapp.bizservice.branch.BranchService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyEmployeeService;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.contentmng.ContentsService;
import com.lwxf.industry4.webapp.bizservice.dept.DeptService;
import com.lwxf.industry4.webapp.bizservice.user.UserBasisService;
import com.lwxf.industry4.webapp.bizservice.user.UserExtraService;
import com.lwxf.industry4.webapp.bizservice.user.UserService;
import com.lwxf.industry4.webapp.bizservice.user.UserThirdInfoService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.constant.BizConstant;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.branch.BranchStatus;
import com.lwxf.industry4.webapp.common.enums.company.EmployeeStatus;
import com.lwxf.industry4.webapp.common.enums.user.UserState;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.UserExtraUtil;
import com.lwxf.industry4.webapp.domain.dto.company.WxDealerDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.WxDealerUserInfoDto;
import com.lwxf.industry4.webapp.domain.dto.user.LoginedUser;
import com.lwxf.industry4.webapp.domain.entity.branch.Branch;
import com.lwxf.industry4.webapp.domain.entity.company.Company;
import com.lwxf.industry4.webapp.domain.entity.company.CompanyEmployee;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserExtra;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.industry4.webapp.facade.smallProgram.smallProgramUser.SpUserFacade;
import com.lwxf.mybatis.utils.MapContext;

import static com.lwxf.industry4.webapp.facade.AppBeanInjector.baseFileUploadComponent;
import static com.lwxf.industry4.webapp.facade.AppBeanInjector.i18nUtil;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/10/17 0017 9:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("spUserFacade")
public class SpUserFacadeImpl extends BaseFacadeImpl implements SpUserFacade {

	@Resource(name = "branchService")
	private BranchService branchService;
	@Resource(name = "companyEmployeeService")
	private CompanyEmployeeService companyEmployeeService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "deptService")
	private DeptService deptService;
	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "userExtraService")
	private UserExtraService userExtraService;
	@Resource(name = "userThirdInfoService")
	private UserThirdInfoService userThirdInfoService;
	@Resource(name = "contentsService")
	private ContentsService contentsService;
	@Resource(name = "userBasisService")
	private UserBasisService userBasisService;
	/**
	 * 手机验证码登录
	 * @param userMap
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult userLogin(MapContext userMap) {
		Map<String, String> errorMap = new HashMap<>();
		String loginName = userMap.getTypedValue(WebConstant.KEY_ENTITY_LOGINNAME, String.class);
		//页面短信验证码
		String mobileCode = userMap.getTypedValue("smsCode", String.class);
		//从缓存中取短信验证码
		String serverMobileCode = SmsUtil.getMobileVerificationCode(loginName, VerificationCodeType.LOGIN);
		if (serverMobileCode == null || !serverMobileCode.equals(mobileCode)) {
			errorMap.put("smsCode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_AUTHCODE_ERROR_20024"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, errorMap);
		}
		User user = AppBeanInjector.userService.findByMobile(loginName);
		if (user == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
		}
		//判断用户是否被禁用
		if (user.getState().intValue() == UserState.DISABLED.getValue()) {
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
		}
		//验证用户的企业是否停用
		Branch branch=this.branchService.findById(user.getBranchId());
		if(branch==null){
			return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
		}
		if(branch.getStatus()== BranchStatus.DISCONTINUATION.getValue()){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
		}
		//判断企业有没有过有效期
		String expireDate=branch.getExpireDate();
		String keys=user.getBranchId();
		//解密有效期的AES加密数据
		String expireDateValue=AESDncode(expireDate,keys);
		if(expireDateValue==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.SYS_UNKNOW_00000,AppBeanInjector.i18nUtil.getMessage("SYS_UNKNOW_00000"));
		}
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Long expire=dateFormat.parse(expireDateValue).getTime();
			Long dateTime= DateUtil.getSystemDate().getTime();
			if(dateTime>expire){
				return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.SYS_INVITATION_HAS_EXPIRED_00011,AppBeanInjector.i18nUtil.getMessage("SYS_INVITATION_HAS_EXPIRED_00011"));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		//验证员工是否被禁用
		CompanyEmployee employee = this.companyEmployeeService.findCompanyByUidAndStatus(user.getId(), EmployeeStatus.NORMAL.getValue());
		if (null==employee){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
		}
		//更新用户最后登录时间
		MapContext update = MapContext.newOne();
		update.put(WebConstant.KEY_ENTITY_ID, user.getId());
		update.put(WebConstant.KEY_ENTITY_LASTLOGIN, DateUtil.getSystemDate());
		AppBeanInjector.userService.updateByMapContext(update);
		//绑定用户
		UserThirdInfo userThirdInfo=AppBeanInjector.userThirdInfoService.findByUserId(user.getId());
		MapContext map = MapContext.newOne();
		map.put("userId", userThirdInfo.getUserId());
		map.put("wxOpenId", userMap.getTypedValue("openId", String.class));
		AppBeanInjector.userThirdInfoService.updateByMapContext(map);
		MapContext result = MapContext.newOne();
		result.put("token",userThirdInfo.getAppToken());
		result.put("branchId",user.getBranchId());
		result.put("type",user.getType());
		return ResultFactory.generateRequestResult(result);
	}

	/**
	 * 登录名 密码登录
	 * @param userMap
	 * @return
	 */
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult userPasswordLogin(MapContext userMap) {
		String loginName = userMap.getTypedValue(WebConstant.KEY_ENTITY_LOGINNAME, String.class);
		String password = userMap.getTypedValue(WebConstant.KEY_USER_EXTRA_PASSWORD, String.class);
		if (LwxfStringUtils.isBlank(loginName) || LwxfStringUtils.isBlank(password)) {
			return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
		}
		User user = AppBeanInjector.userService.findByMobile(loginName);
		if (null == user) {
			user = AppBeanInjector.userService.findByLoginName(loginName);
		}
		if (null == user) {
			user = AppBeanInjector.userService.findByEmail(loginName);
		}
		if (user == null) {
			return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
		}
		// 密码验证不通过
		if (!AppBeanInjector.userExtraService.isPasswordCorrect(user.getId(), password)) {
			return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
		}
		//判断用户是否被禁用
		if (user.getState().intValue() == UserState.DISABLED.getValue()) {
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
		}
		//验证用户的企业是否停用
		Branch branch=this.branchService.findById(user.getBranchId());
		if(branch==null){
			return ResultFactory.generateErrorResult(ErrorCodes.LOGIN_FAIL_90000, AppBeanInjector.i18nUtil.getMessage("LOGIN_FAIL_90000"));
		}
		if(branch.getStatus()== BranchStatus.DISCONTINUATION.getValue()){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
		}
		//判断企业有没有过有效期
		String expireDate=branch.getExpireDate();
		String keys=user.getBranchId();
		//解密有效期的AES加密数据
		String expireDateValue=AESDncode(expireDate,keys);
		if(expireDateValue==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.SYS_UNKNOW_00000,AppBeanInjector.i18nUtil.getMessage("SYS_UNKNOW_00000"));
		}
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			Long expire=dateFormat.parse(expireDateValue).getTime();
			Long dateTime=DateUtil.getSystemDate().getTime();
			if(dateTime>expire){
				return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.SYS_INVITATION_HAS_EXPIRED_00011,AppBeanInjector.i18nUtil.getMessage("SYS_INVITATION_HAS_EXPIRED_00011"));
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
		//验证员工是否被禁用
		CompanyEmployee employee = this.companyEmployeeService.findCompanyByUidAndStatus(user.getId(), EmployeeStatus.NORMAL.getValue());
		if (null==employee){
			return ResultFactory.generateErrorResult(ErrorCodes.SYS_ERROR_USER_ISDISABLED_00019, AppBeanInjector.i18nUtil.getMessage("SYS_ERROR_USER_ISDISABLED_00019"));
		}
		//更新用户最后登录时间
		MapContext update = MapContext.newOne();
		update.put(WebConstant.KEY_ENTITY_ID, user.getId());
		update.put(WebConstant.KEY_ENTITY_LASTLOGIN, DateUtil.getSystemDate());
		AppBeanInjector.userService.updateByMapContext(update);
		//绑定用户
		UserThirdInfo userThirdInfo=AppBeanInjector.userThirdInfoService.findByUserId(user.getId());
		MapContext map = MapContext.newOne();
		map.put("userId", userThirdInfo.getUserId());
		map.put("wxOpenId", userMap.getTypedValue("openId", String.class));
		AppBeanInjector.userThirdInfoService.updateByMapContext(map);
		MapContext result = MapContext.newOne();
		result.put("token",userThirdInfo.getAppToken());
		result.put("branchId",user.getBranchId());
		result.put("type",user.getType());
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	public RequestResult findDealerInfo(String uid) {
		//判断当前用户企业公司是否存在
		CompanyEmployee companyEmployee = AppBeanInjector.companyEmployeeService.selectByUserId(uid);
		if(companyEmployee==null){
			return ResultFactory.generateResNotFoundResult();
		}
		User user=this.userService.findByUserId(uid);
		String userName=user.getName();
		String userAvatar=user.getAvatar();
		WxDealerDto wxDealerInfo = this.companyService.findWxDealerInfoByCId(companyEmployee.getCompanyId());
		wxDealerInfo.setAvatar(userAvatar);
		wxDealerInfo.setDealerName(userName);
		return ResultFactory.generateRequestResult(wxDealerInfo);
	}

	@Override
	public RequestResult findLoginUserInfo(String uid,String cid) {
		Company company=this.companyService.findById(cid);
		WxDealerUserInfoDto dealerUserInfoByUid = this.companyService.findDealerUserInfoByUid(uid);
		dealerUserInfoByUid.setCompanyName(company.getName());
		dealerUserInfoByUid.setDepts(this.deptService.findListByUserId(uid));
		return ResultFactory.generateRequestResult(dealerUserInfoByUid);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateMobile(String uid,String oldMobile, String newMobile) {
		//判断是否是当前用户修改手机号
		LoginedUser currUser = AppBeanInjector.userFacade.findLoginedUserById(uid);
		if(!currUser.getMobile().equals(oldMobile)){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		//判断新手机号是否已存在
		User byMobile = this.userService.findByMobile(newMobile);
		if(byMobile!=null){
			Map<String, String> validResult = new HashMap<>();
			validResult.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_ERROR,validResult);
		}
		MapContext mapContext = new MapContext();
		mapContext.put(WebConstant.KEY_ENTITY_MOBILE,newMobile);
		mapContext.put(WebConstant.KEY_ENTITY_ID,currUser.getId());
		this.userService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updatePassword(String uid,String oldPassword,String newPassword) {
		if (!this.userExtraService.isPasswordCorrect(uid,oldPassword)) {
			MapContext error = new MapContext();
			error.put("oldPassword",AppBeanInjector.i18nUtil.getMessage("VALIDATE_PASSWORD_INCORRECT"));
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_ERROR,error);
		}
		UserExtra userExtra = new UserExtra();
		UserExtraUtil.saltingPassword(userExtra,newPassword);
		MapContext toSave = new MapContext();
		toSave.put(WebConstant.KEY_USER_EXTRA_SALT, userExtra.getSalt());
		toSave.put(WebConstant.KEY_ENTITY_USER_ID, uid);
		toSave.put(WebConstant.KEY_USER_EXTRA_PASSWORD, userExtra.getPassword());
		toSave.put(WebConstant.KEY_USER_EXTRA_TOKEN, userExtra.getToken());
		this.userExtraService.updateByMapContext(toSave);
		toSave = MapContext.newOne();
		toSave.put(WebConstant.KEY_ENTITY_USER_ID, uid);
		toSave.put(WebConstant.KEY_ENTITY_APP_TOKEN, UserExtraUtil.generateAppToken(userExtra, null));
		this.userThirdInfoService.updateByMapContext(toSave);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findUserInfo(String uid,String cid) {
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_USER_ID,uid);
		mapContext.put(WebConstant.KEY_ENTITY_COMPANY_ID, cid);
		User user=this.userService.findByUserId(uid);
		Company company=this.companyService.findById(cid);
		MapContext result=MapContext.newOne();
		result.put("userId",uid);
		result.put("userName",user.getName());
		result.put("mobile",user.getMobile());
		result.put("companyId",cid);
		result.put("companyName",company.getName());
		result.put("branchId",user.getBranchId());
		result.put("sex",user.getSex());
		return ResultFactory.generateRequestResult(result);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateUserInfo(String userId,MapContext mapContext) {
		MapContext mapContext1=MapContext.newOne();
		if(mapContext.containsKey("sex")){
			Integer sex=mapContext.getTypedValue("sex",Integer.class);
			mapContext1.put("sex",sex);
		}
		if(mapContext.containsKey("name")){
			String name=mapContext.getTypedValue("name",String.class);
			mapContext1.put("name",name);
		}
		if(mapContext.containsKey("birthday")){
			String birthdayValue = mapContext.getTypedValue("birthday",String.class);
			if(CommonConstant.STRING_EMPTY.equals(birthdayValue )){
				birthdayValue = null;
			}
			mapContext1.put("birthday",birthdayValue);
		}
		if(mapContext1.size()>0) {
			mapContext1.put("id", userId);
			this.userService.updateByMapContext(mapContext1);
		}
		MapContext map=MapContext.newOne();
		if(mapContext.containsKey("identityNumber")){
			String identityNumber=mapContext.getTypedValue("identityNumber",String.class);
			if(!identityNumber.trim().equals("")){
				map.put("identityNumber",identityNumber);
			}
		}
		if(mapContext.containsKey("education")){
			String educationValue = mapContext.getTypedValue("education",String.class);
			if(CommonConstant.STRING_EMPTY.equals(educationValue )){
				educationValue = null;
			}
			map.put("education",educationValue);
		}
		if(map.size()>0) {
			map.put("userId", userId);
			this.userBasisService.updateByMapContext(map);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult forgetpassword(MapContext mapContext) {
		String mobile=mapContext.getTypedValue("mobile",String.class);
		User user=this.userService.findByMobile(mobile);
		if(user==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//新密码
		String newPassword = mapContext.getTypedValue("newPassword", String.class);
		//确认密码
		String affirmPassword= mapContext.getTypedValue("affirmPassword", String.class);
		if(!newPassword.equals(affirmPassword)){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_PASSWORD_IS_NOT_CONSISTENT,AppBeanInjector.i18nUtil.getMessage("VALIDATE_PASSWORD_IS_NOT_CONSISTENT"));
		}
		UserExtra userExtra = new UserExtra();
		UserExtraUtil.saltingPassword(userExtra,newPassword);
		String userId=user.getId();
		MapContext toSave = new MapContext();
		toSave.put(WebConstant.KEY_USER_EXTRA_SALT, userExtra.getSalt());
		toSave.put(WebConstant.KEY_ENTITY_USER_ID, userId);
		toSave.put(WebConstant.KEY_USER_EXTRA_PASSWORD, userExtra.getPassword());
		toSave.put(WebConstant.KEY_USER_EXTRA_TOKEN, userExtra.getToken());
		this.userExtraService.updateByMapContext(toSave);
		toSave = MapContext.newOne();
		toSave.put(WebConstant.KEY_ENTITY_USER_ID, userId);
		toSave.put(WebConstant.KEY_ENTITY_APP_TOKEN, UserExtraUtil.generateAppToken(userExtra, null));
		this.userThirdInfoService.updateByMapContext(toSave);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateAvatar(String userId, MultipartFile multipartFile) {
		MapContext userMap = MapContext.newOne();
		userMap.put(WebConstant.KEY_ENTITY_ID, userId);
		User user = userService.findById(userId);
		if (user == null) {
			String errMsg = i18nUtil.getMessage("BIZ_RES_NOT_FOUND_10001");
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RES_NOT_FOUND_10001, errMsg);
		}
		UploadInfo uploadInfo = baseFileUploadComponent.doUploadByModule(multipartFile, UploadResourceType.AVATAR, userId);
		userMap.put("avatar", AppBeanInjector.configuration.getDomainUrl().concat(uploadInfo.getRelativePath()));
		try {
			//判断是系统新的头像地址进行删除
			if (user.getAvatar().startsWith(BizConstant.EASPYM4_FILE_DOMAIN) && !user.getAvatar().equals(AppBeanInjector.configuration.getUserDefaultAvatar())) {
				AppBeanInjector.baseFileUploadComponent.deleteFileByDir(user.getAvatar());
			}
			this.userService.updateByMapContext(userMap);
		} catch (RuntimeException ex) {
			baseFileUploadComponent.deleteFile(uploadInfo.getRelativePath(), UploadResourceType.AVATAR, 0);
			throw ex;
		}
		userMap.remove("id");
		return ResultFactory.generateRequestResult(userMap);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult userlogout(String uid) {
		MapContext mapContext=MapContext.newOne();
		mapContext.put("userId",uid);
		mapContext.put("wxOpenId",null);

		return ResultFactory.generateRequestResult(this.userThirdInfoService.userlogout(mapContext));
	}
	private String AESDncode(String expireDate, String keys) {
		try {
			//1.构造密钥生成器，指定为AES算法,不区分大小写
			KeyGenerator keygen= KeyGenerator.getInstance("AES");
			//2.根据ecnodeRules规则初始化密钥生成器
			//生成一个128位的随机源,根据传入的字节数组
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG") ;
			secureRandom.setSeed(keys.getBytes());
			keygen.init(128, secureRandom);
			//keygen.init(128, new SecureRandom(keys.getBytes()));
			//3.产生原始对称密钥
			SecretKey original_key=keygen.generateKey();
			//4.获得原始对称密钥的字节数组
			byte [] raw=original_key.getEncoded();
			//5.根据字节数组生成AES密钥
			SecretKey key=new SecretKeySpec(raw, "AES");
			//6.根据指定算法AES自成密码器
			Cipher cipher=Cipher.getInstance("AES");
			//7.初始化密码器，第一个参数为加密(Encrypt_mode)或者解密(Decrypt_mode)操作，第二个参数为使用的KEY
			cipher.init(Cipher.DECRYPT_MODE, key);
			//8.将加密并编码后的内容解码成字节数组
			byte [] byte_content= new BASE64Decoder().decodeBuffer(expireDate);
			/*
			 * 解密
			 */
			byte [] byte_decode=cipher.doFinal(byte_content);
			String AES_decode=new String(byte_decode,"utf-8");
			return AES_decode;
		} catch (Exception e) {
			e.printStackTrace();
		}

		//如果有错就返加nulll
		return null;
	}
}
