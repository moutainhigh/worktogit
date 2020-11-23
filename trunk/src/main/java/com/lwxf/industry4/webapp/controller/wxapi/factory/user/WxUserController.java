package com.lwxf.industry4.webapp.controller.wxapi.factory.user;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.SmsUtil;
import com.lwxf.industry4.webapp.baseservice.sms.yunpian.VerificationCodeType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.json.JacksonObjectMapper;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.FileMimeTypeUtil;
import com.lwxf.industry4.webapp.common.utils.LoginUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.company.WxDealerDto;
import com.lwxf.industry4.webapp.domain.dto.companyEmployee.WxDealerUserInfoDto;
import com.lwxf.industry4.webapp.domain.dto.contentmng.ContentsDto;
import com.lwxf.industry4.webapp.domain.entity.contentmng.ContentsContent;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.wxapi.factory.user.WxUserFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/18 0018 17:57
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@RestController("wxFUserController")
@RequestMapping(value = "/wxapi/f/branchs", produces = WebConstant.RESPONSE_CONTENT_TYPE_JSON_CHARTSET)
@Api(value = "wxFUserController", tags = {"F端微信小程序接口:我的信息管理"})
public class WxUserController {
	@Resource(name = "wxUserFacade")
	private WxUserFacade wxUserFacade;

	/**
	 * 我的页面接口
	 * @return
	 */
	@GetMapping
	@ApiOperation(value = "我的页面接口",response = WxDealerDto.class)
	private RequestResult findDealerInfo(){
		String atoken=WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.wxUserFacade.findDealerInfo(uid);
	}

	/**
	 * 个人信息接口
	 * @return
	 */
	@GetMapping("/info")
	@ApiOperation(value = "个人信息接口",response = WxDealerUserInfoDto.class)
	private String findDealerUserInfo(){
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken=WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		return jsonMapper.toJson(this.wxUserFacade.findDealerUserInfo(uid));
	}

	/**
	 * 换绑手机号
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/mobile")
	@ApiOperation(value = "换绑手机号")
	private RequestResult updateMobile(@RequestBody MapContext mapContext){
		String atoken=WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		List<Map<String,String>> error = new ArrayList<Map<String, String>>();
		String oldMobile = mapContext.getTypedValue("oldMobile", String.class);
		if(oldMobile==null){
			Map errorMap = new HashMap();
			errorMap.put("oldMobile",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			error.add(errorMap);
		}else{
			//验证电话号码是否正确
			if (!ValidateUtils.isChinaPhoneNumber(oldMobile)) {
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put("oldMobile",AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, oldMobile);
			}
		}
		String newMobile = mapContext.getTypedValue("newMobile", String.class);
		if(newMobile==null){
			Map errorMap = new HashMap();
			errorMap.put("newMobile",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			error.add(errorMap);
		}else{
			//验证电话号码是否正确
			if (!ValidateUtils.isChinaPhoneNumber(newMobile)) {
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put("newMobile",AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, newMobile);
			}
		}
		String twoNewMobile = mapContext.getTypedValue("twoNewMobile", String.class);
		if(newMobile==null){
			Map errorMap = new HashMap();
			errorMap.put("twoNewMobile",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			error.add(errorMap);
		}else{
			//验证电话号码是否正确
			if (!ValidateUtils.isChinaPhoneNumber(newMobile)) {
				Map<String, String> errorMap = new HashMap<>();
				errorMap.put("twoNewMobile",AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
				return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, newMobile);
			}
		}
		if(error.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,error);
		}
		//确认两次输入的手机号是否一致
		if(!twoNewMobile.equals(newMobile)){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,"输入不一致");
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
		return this.wxUserFacade.updateMobile(uid,oldMobile,newMobile);
	}


	/**
	 * 修改当前用户密码
	 * @param mapContext
	 * @return
	 */
	@PutMapping("/password")
	@ApiOperation(value = "修改当前用户密码                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 ")
	private RequestResult updatePassword(@RequestBody MapContext mapContext){
		String atoken=WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String newPassword = mapContext.getTypedValue("newPassword", String.class);
		String confirmPassword = mapContext.getTypedValue("confirmPassword",String.class);
		String oldPassword = mapContext.getTypedValue("oldPassword",String.class);
		//判断两次密码是否一致
		if(!newPassword.equals(confirmPassword)){
			MapContext error = new MapContext();
			error.put("confirmPassword",AppBeanInjector.i18nUtil.getMessage("VALIDATE_PASSWORD_IS_NOT_CONSISTENT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,error);
		}
		return this.wxUserFacade.updatePassword(uid,oldPassword,newPassword);
	}

	/**
	 * 使用帮助接口
	 * @return
	 */
	@GetMapping("/helps")
	@ApiOperation(value = "使用帮助接口",response = ContentsDto.class)
	private RequestResult findHelps(@RequestParam(required = false)@ApiParam(value = "问题名称") String name,
									@RequestParam(required = false,defaultValue = "1")@ApiParam(value = "页码") Integer pageNum,
									@RequestParam(required = false,defaultValue = "10")@ApiParam(value = "数据量") Integer pageSize){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String branchid =mapInfo.get("branchId")==null?null:mapInfo.get("branchId").toString();
		return this.wxUserFacade.findHelps(branchid,uid,name,pageNum,pageSize);
	}

	/**
	 * 使用帮助问题详情
	 */
	@GetMapping("/helps/{id}")
	@ApiOperation(value = "使用帮助问题详情",response = ContentsContent.class)
	private RequestResult findHelpsInfo(@PathVariable @ApiParam(value = "帮助问题Id") String id){
		return this.wxUserFacade.findHelpsInfo(id);
	}


	/**
	 * 登录人信息预加载接口
	 */
	@ApiOperation(value = "登录人信息预加载接口",notes = "登录人信息预加载接口")
	@GetMapping("/userInfo")
	public RequestResult findUserInfo(){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		String cid =mapInfo.get("companyId")==null?null:mapInfo.get("companyId").toString();
		if(cid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.wxUserFacade.findUserInfo(uid,cid);
	}
	/**
	 * 修改个人信息
	 */
	@ApiOperation(value = "修改个人信息",notes = "修改个人信息")
	@PutMapping("/users")
	private RequestResult updateUserInfo(
										 @RequestBody MapContext mapContext){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
         return this.wxUserFacade.updateUserInfo(uid,mapContext);
	}
    /**
	 * 忘记密码
	 */
	@ApiOperation(value = "忘记密码",notes = "忘记密码")
	@PutMapping("/forgetpassword")
	private RequestResult forgetpassword(@RequestBody MapContext mapContext){
		return this.wxUserFacade.forgetpassword(mapContext);
	}
	/**
	 * 修改个人头像
	 * @param multipartFile
	 * @return
	 */
	@ApiOperation(value = "修改个人头像",notes = "修改个人头像")
	@PostMapping(value = "/users/updateavatar")
	public String updateAvatar(@RequestBody MultipartFile multipartFile
									 ) {
		JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED")));
		}
		Map<String, Object> errorInfo = new HashMap<>();
		if (multipartFile == null) {
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		} else if (!FileMimeTypeUtil.isLegalImageType(multipartFile)) {
			errorInfo.put("multipartFile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		} else if (multipartFile.getSize() > 1024L * 1024L * AppBeanInjector.configuration.getUploadBackgroundMaxsize()) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.BIZ_FILE_SIZE_LIMIT_10031, LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_FILE_SIZE_LIMIT_10031"), AppBeanInjector.configuration.getUploadBackgroundMaxsize())));
		}
		if (errorInfo.size() > 0) {
			return jsonMapper.toJson(ResultFactory.generateErrorResult(com.lwxf.commons.exception.ErrorCodes.VALIDATE_ERROR, errorInfo));
		}
		RequestResult result=this.wxUserFacade.updateAvatar(uid, multipartFile);
		return jsonMapper.toJson(result);
	}
	@ApiOperation(value = "用户退出账号",notes = "用户退出账号")
	@PutMapping("/users/logout")
	private RequestResult userLogout(){
		String atoken= WebUtils.getHttpServletRequest().getHeader("X-ATOKEN");
		MapContext mapInfo = LoginUtil.checkLogin(atoken);
		String uid =mapInfo.get("userId")==null?null:mapInfo.get("userId").toString();
		if(uid==null){
			return ResultFactory.generateErrorResult(com.lwxf.industry4.webapp.common.exceptions.ErrorCodes.VALIDATE_USER_NOT_LOGGED, AppBeanInjector.i18nUtil.getMessage("VALIDATE_USER_NOT_LOGGED"));
		}
		return this.wxUserFacade.userlogout(uid);
	}
}
