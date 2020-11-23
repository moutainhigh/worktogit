package com.lwxf.industry4.webapp.domain.entity.user;
import java.util.*;
import java.sql.*;
import java.util.Arrays;
import java.util.List;
import com.lwxf.industry4.webapp.domain.entity.base.AbstractNoIdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
/**
 * 功能：user_third_info 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-11-30 10:15
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "user_third_info",displayName = "user_third_info")
public class UserThirdInfo extends AbstractNoIdEntity {
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "user_id",displayName = "用户id，关联user表的id")
	private String userId;
	@Column(type = Types.VARCHAR,length = 50,name = "wx_open_id",displayName = "openid 微信公众号的用户标识，对当前开发者帐号唯一。一个openid对应一个公众号")
	private String wxOpenId;
	@Column(type = Types.VARCHAR,length = 50,name = "wx_nickname",displayName = "微信昵称")
	private String wxNickname;
	@Column(type = Types.VARCHAR,length = 50,name = "wx_union_id",displayName = "unionid 微信开放平台的用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的unionid是唯一的，默认设为open_id")
	private String wxUnionId;
	@Column(type = Types.BIT,nullable = false,name = "wx_is_subscribe",displayName = "是否订阅公众号，用户关注公众号时生成用户数据，该字段为1（true），当用户取消关注时，设为0（false）")
	private Boolean wxIsSubscribe;
	@Column(type = Types.BIT,nullable = false,name = "wx_is_bind",displayName = "微信是否绑定:0-否,1-是，默认true")
	private Boolean wxIsBind;
	@Column(type = Types.BIT,nullable = false,name = "email_is_bind",displayName = "信箱是否绑定:0-否,1-是，不填信箱时，为false")
	private Boolean emailIsBind;
	@Column(type = Types.BIT,nullable = false,name = "mobile_is_bind",displayName = "手机号是否绑定:0-否,1-是，不填手机号时，默认为false")
	private Boolean mobileIsBind;
	@Column(type = Types.VARCHAR,length = 260,name = "rongcloud_token",displayName = "融云平台的token值")
	private String rongcloudToken;
	@Column(type = Types.VARCHAR,length = 260,name = "app_token",displayName = "app的token值")
	private String appToken;
	@Column(type = Types.VARCHAR,length = 260,name = "branchId",displayName = "企业id")
	private String branchId;
	@Column(type = Types.VARCHAR,length = 260,name = "companyId",displayName = "经销商id")
	private String companyId;
	@Column(type = Types.VARCHAR,length = 260,name = "official_open_id",displayName = "公众号openId")
	private String officialOpenId;

	public UserThirdInfo() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.userId == null) {
			validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.wxOpenId) > 50) {
			validResult.put("wxOpenId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.wxNickname) > 50) {
			validResult.put("wxNickname", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.wxUnionId) > 50) {
			validResult.put("wxUnionId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.wxIsSubscribe == null) {
			validResult.put("wxIsSubscribe", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.wxIsBind == null) {
			validResult.put("wxIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.emailIsBind == null) {
			validResult.put("emailIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.mobileIsBind == null) {
			validResult.put("mobileIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.rongcloudToken) > 260) {
			validResult.put("rongcloudToken", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.appToken) > 260) {
			validResult.put("appToken", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("wxOpenId","wxNickname","wxUnionId","wxIsSubscribe","wxIsBind","emailIsBind","mobileIsBind","rongcloudToken","appToken");

	public static RequestResult validateFields(MapContext map) {
		Map<String, String> validResult = new HashMap<>();
		if(map.size()==0){
			return ResultFactory.generateErrorResult("error",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		boolean flag;
		Set<String> mapSet = map.keySet();
		flag = propertiesList.containsAll(mapSet);
		if(!flag){
			return ResultFactory.generateErrorResult("error",AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		}
		if(map.containsKey("wxIsSubscribe")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("wxIsSubscribe",String.class))) {
				validResult.put("wxIsSubscribe", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("wxIsBind")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("wxIsBind",String.class))) {
				validResult.put("wxIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("emailIsBind")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("emailIsBind",String.class))) {
				validResult.put("emailIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("mobileIsBind")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("mobileIsBind",String.class))) {
				validResult.put("mobileIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("wxOpenId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("wxOpenId",String.class)) > 50) {
				validResult.put("wxOpenId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("wxNickname")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("wxNickname",String.class)) > 50) {
				validResult.put("wxNickname", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("wxUnionId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("wxUnionId",String.class)) > 50) {
				validResult.put("wxUnionId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("wxIsSubscribe")) {
			if (map.get("wxIsSubscribe")  == null) {
				validResult.put("wxIsSubscribe", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("wxIsBind")) {
			if (map.get("wxIsBind")  == null) {
				validResult.put("wxIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("emailIsBind")) {
			if (map.get("emailIsBind")  == null) {
				validResult.put("emailIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("mobileIsBind")) {
			if (map.get("mobileIsBind")  == null) {
				validResult.put("mobileIsBind", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("rongcloudToken")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("rongcloudToken",String.class)) > 260) {
				validResult.put("rongcloudToken", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("appToken")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("appToken",String.class)) > 260) {
				validResult.put("appToken", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public String getCompanyId() {return companyId;}

	public void setCompanyId(String companyId) {this.companyId = companyId;}

	public String getBranchId() {return branchId;}

	public void setBranchId(String branchId) {this.branchId = branchId;}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setWxOpenId(String wxOpenId){
		this.wxOpenId=wxOpenId;
	}

	public String getWxOpenId(){
		return wxOpenId;
	}

	public void setWxNickname(String wxNickname){
		this.wxNickname=wxNickname;
	}

	public String getWxNickname(){
		return wxNickname;
	}

	public void setWxUnionId(String wxUnionId){
		this.wxUnionId=wxUnionId;
	}

	public String getWxUnionId(){
		return wxUnionId;
	}

	public void setWxIsSubscribe(Boolean wxIsSubscribe){
		this.wxIsSubscribe=wxIsSubscribe;
	}

	public Boolean getWxIsSubscribe(){
		return wxIsSubscribe;
	}

	public void setWxIsBind(Boolean wxIsBind){
		this.wxIsBind=wxIsBind;
	}

	public Boolean getWxIsBind(){
		return wxIsBind;
	}

	public void setEmailIsBind(Boolean emailIsBind){
		this.emailIsBind=emailIsBind;
	}

	public Boolean getEmailIsBind(){
		return emailIsBind;
	}

	public void setMobileIsBind(Boolean mobileIsBind){
		this.mobileIsBind=mobileIsBind;
	}

	public Boolean getMobileIsBind(){
		return mobileIsBind;
	}

	public void setRongcloudToken(String rongcloudToken){
		this.rongcloudToken=rongcloudToken;
	}

	public String getRongcloudToken(){
		return rongcloudToken;
	}

	public void setAppToken(String appToken){
		this.appToken=appToken;
	}

	public String getAppToken(){
		return appToken;
	}

	public String getOfficialOpenId() {
		return officialOpenId;
	}

	public void setOfficialOpenId(String officialOpenId) {
		this.officialOpenId = officialOpenId;
	}
}
