package com.lwxf.industry4.webapp.domain.entity.user;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.commons.utils.ValidateUtils;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.TypesExtend;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
/**
 * 功能：user 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-11-30 10:15
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "user",displayName = "user")
public class User extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "名称，用于显示")
	private String name;
	@Column(type = Types.TINYINT,nullable = false,name = "sex",displayName = "性别:0-男，1-女")
	private Integer sex;
	@Column(type = Types.VARCHAR,length = 50,name = "email",displayName = "")
	private String email;
	@Column(type = Types.VARCHAR,length = 200,name = "avatar",displayName = "头像，默认为AppBeanInjector.configuration.getUserDefaultAvatar()")
	private String avatar;
	@Column(type = Types.DATE,name = "birthday",displayName = "生日")
	private Date birthday;
	@Column(type = Types.VARCHAR,length = 20,name = "mobile",displayName = "")
	private String mobile;
	@Column(type = Types.TINYINT,nullable = false,name = "state",displayName = "状态：0-禁用，1-启用，默认为1")
	private Integer state;
	@Column(type = Types.VARCHAR,length = 50,name = "background",displayName = "自定义背景图")
	private String background;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = TypesExtend.DATETIME,name = "last_login",displayName = "最后登陆时间")
	private Date lastLogin;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "time_zone",displayName = "时区")
	private String timeZone;
	@Column(type = Types.VARCHAR,length = 50,name = "language",displayName = "语言")
	private String language;
	@Column(type = Types.CHAR,length = 13,name = "city_area_id",displayName = "所处城市id，精确到区（可用于匹配附近门店）")
	private String cityAreaId;
	@Column(type = Types.INTEGER,nullable = false,name = "followers",displayName = "粉丝数量：默认为0，没添加一个关注人，该字段增加1，相反则减一，减到0为止")
	private Integer followers;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "login_name",displayName = "登录名(默认为手机号)，用户只能修改一次")
	private String loginName;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "账号类型：0 - 厂家（factory）；1 - 经销商（dealer）；2 - 客户（client）")
	private Integer type;
	@Column(type = Types.BIT,nullable = false,name = "is_changed_login_name",displayName = "登录名是否已被更改过，false-未被更改；true-已更改（一个用户只能修改一次登录名）")
	private Boolean changedLoginName;
	private String branchId;
	@Column(type = Types.TINYINT,name = "enable_login_pc",displayName = "状态：0-禁用，1-启用")
	private Integer enableLoginPc;
	@Column(type = Types.TINYINT,name = "enable_login_wx",displayName = "状态：0-禁用，1-启用")
	private Integer enableLoginWx;

	public User() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 50) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.sex == null) {
			validResult.put("sex", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(this.email != null && !this.email.trim().equals("")){
			if (LwxfStringUtils.getStringLength(this.email) > 50) {
				validResult.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
			else if(!ValidateUtils.isEmail(this.email)){
				validResult.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_EMAIL"));
			}
		}else{
			this.email=null;
		}
		if (LwxfStringUtils.getStringLength(this.avatar) > 200) {
			validResult.put("avatar", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.mobile) > 20) {
			validResult.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(this.mobile!=null){
			if(!ValidateUtils.isMobile(this.mobile)){
				validResult.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
			}
		}
		if (this.state == null) {
			validResult.put("state", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.background) > 50) {
			validResult.put("background", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.timeZone == null) {
			validResult.put("timeZone", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.timeZone) > 50) {
				validResult.put("timeZone", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.language) > 50) {
			validResult.put("language", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
			validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.followers == null) {
			validResult.put("followers", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.loginName == null) {
			validResult.put("loginName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.loginName) > 50) {
				validResult.put("loginName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.changedLoginName == null) {
			validResult.put("changedLoginName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","userId","sex","email","avatar","birthday","mobile","state","background","lastLogin","timeZone","language","cityAreaId","followers","loginName","type","changedLoginName");

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
		if(map.containsKey("sex")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("sex",String.class))) {
				validResult.put("sex", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("birthday")) {
			if (map.get("birthday")  == null) {

			}else if (!DataValidatorUtils.isDate(map.getTypedValue("birthday",String.class))) {
				validResult.put("birthday", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}

		}
		if(map.containsKey("state")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("state",String.class))) {
				validResult.put("state", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("lastLogin")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("lastLogin",String.class))) {
				validResult.put("lastLogin", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("followers")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("followers",String.class))) {
				validResult.put("followers", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("changedLoginName")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("changedLoginName",String.class))) {
				validResult.put("changedLoginName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("sex")) {
			if (map.get("sex")  == null) {
				validResult.put("sex", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("email")) {
			if (map.get("email")  == null) {

			}else if(!ValidateUtils.isEmail(map.getTypedValue("email",String.class))){
				validResult.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_EMAIL"));
			}
			if (LwxfStringUtils.getStringLength(map.getTypedValue("email",String.class)) > 50) {
				validResult.put("email", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("avatar")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("avatar",String.class)) > 200) {
				validResult.put("avatar", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("mobile")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("mobile",String.class)) > 20) {
				validResult.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
			else if(!ValidateUtils.isMobile(map.getTypedValue("mobile",String.class))){
				validResult.put("mobile", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INVALID_MOBILE_NO"));
			}
		}
		if(map.containsKey("state")) {
			if (map.get("state")  == null) {
				validResult.put("state", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("background")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("background",String.class)) > 50) {
				validResult.put("background", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("timeZone")) {
			if (map.getTypedValue("timeZone",String.class)  == null) {
				validResult.put("timeZone", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("timeZone",String.class)) > 50) {
					validResult.put("timeZone", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("language")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("language",String.class)) > 50) {
				validResult.put("language", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("cityAreaId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cityAreaId",String.class)) > 13) {
				validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("followers")) {
			if (map.get("followers")  == null) {
				validResult.put("followers", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("loginName")) {
			if (map.getTypedValue("loginName",String.class)  == null) {
				validResult.put("loginName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("loginName",String.class)) > 50) {
					validResult.put("loginName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("changedLoginName")) {
			if (map.get("changedLoginName")  == null) {
				validResult.put("changedLoginName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setSex(Integer sex){
		this.sex=sex;
	}

	public Integer getSex(){
		return sex;
	}

	public void setEmail(String email){
		this.email=email;
	}

	public String getEmail(){
		return email;
	}

	public void setAvatar(String avatar){
		this.avatar=avatar;
	}

	public String getAvatar(){
		return avatar;
	}

	public void setBirthday(Date birthday){
		this.birthday=birthday;
	}

	public Date getBirthday(){
		return birthday;
	}

	public void setMobile(String mobile){
		this.mobile=mobile;
	}

	public String getMobile(){
		return mobile;
	}

	public void setState(Integer state){
		this.state=state;
	}

	public Integer getState(){
		return state;
	}

	public void setBackground(String background){
		this.background=background;
	}

	public String getBackground(){
		return background;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setLastLogin(Date lastLogin){
		this.lastLogin=lastLogin;
	}

	public Date getLastLogin(){
		return lastLogin;
	}

	public void setTimeZone(String timeZone){
		this.timeZone=timeZone;
	}

	public String getTimeZone(){
		return timeZone;
	}

	public void setLanguage(String language){
		this.language=language;
	}

	public String getLanguage(){
		return language;
	}

	public void setCityAreaId(String cityAreaId){
		this.cityAreaId=cityAreaId;
	}

	public String getCityAreaId(){
		return cityAreaId;
	}

	public void setFollowers(Integer followers){
		this.followers=followers;
	}

	public Integer getFollowers(){
		return followers;
	}

	public void setLoginName(String loginName){
		this.loginName=loginName;
	}

	public String getLoginName(){
		return loginName;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setChangedLoginName(Boolean changedLoginName){
		this.changedLoginName=changedLoginName;
	}

	public Boolean getChangedLoginName(){
		return changedLoginName;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Integer getEnableLoginPc() {
		return enableLoginPc;
	}

	public void setEnableLoginPc(Integer enableLoginPc) {
		this.enableLoginPc = enableLoginPc;
	}

	public Integer getEnableLoginWx() {
		return enableLoginWx;
	}

	public void setEnableLoginWx(Integer enableLoginWx) {
		this.enableLoginWx = enableLoginWx;
	}
}
