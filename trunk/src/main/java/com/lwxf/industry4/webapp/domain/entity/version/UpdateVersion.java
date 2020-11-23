package com.lwxf.industry4.webapp.domain.entity.version;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.base.AbstractNoIdEntity;
import com.lwxf.mybatis.utils.TypesExtend;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
/**
 * 功能：update_version 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-05-06 09:57 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "update_version",displayName = "update_version")
@ApiModel(value="版本信息",description="版本信息")
public class UpdateVersion extends AbstractNoIdEntity {
	@ApiModelProperty(value="id",name="versionId",required=true,example = "版本id")
	@Column(type = Types.INTEGER,nullable = false,name = "version_id",displayName = "")
	private Integer versionId;
	@ApiModelProperty(value="内部版本号",name="versionNo",example = "1")
	@Column(type = Types.VARCHAR,length = 20,name = "version_no",displayName = "内部版本号")
	private String versionNo;
	@ApiModelProperty(value="展示版本号",name="version",example = "1.1.1")
	@Column(type = Types.VARCHAR,length = 20,name = "version",displayName = "展示版本号")
	private String version;
	@ApiModelProperty(value="系统名称",name="name",example = "系统名称")
	@Column(type = Types.VARCHAR,length = 20,name = "name",displayName = "系统名称")
	private String name;
	@ApiModelProperty(value="系统类型:0：工业4.0整合版，1：工业4.0F端，2：工业4.0B端，3：工业4.0C端",name="sysType",example = "0")
	@Column(type = Types.TINYINT,name = "sys_type",displayName = "系统类型，0：工业4.0整合版，1：工业4.0F端，2：工业4.0B端，3：工业4.0C端")
	private Integer sysType;
	@ApiModelProperty(value="平台,0:ios,1:android，2：PC端",name="platform",example = "0")
	@Column(type = Types.TINYINT,name = "platform",displayName = "平台,0:ios,1:android，2：PC端")
	private Integer platform;
	@ApiModelProperty(value="下载地址",name="url",example = "http://industry4.htjjsc.com/download/app-release.apk")
	@Column(type = Types.VARCHAR,length = 1024,name = "url",displayName = "下载地址")
	private String url;
	@ApiModelProperty(value="MD5校验码",name="md5Value",example = "")
	@Column(type = Types.VARCHAR,length = 200,name = "md5_value",displayName = "MD5校验码")
	private String md5Value;
	@ApiModelProperty(value="是否有效，0：无效，1：有效，默认：0",name="updateState",example = "0")
	@Column(type = Types.TINYINT,name = "update_state",displayName = "是否有效，0：无效，1：有效，默认：0")
	private Integer updateState;
	@ApiModelProperty(value="文件大小",name="fileSize",example = "1")
	@Column(type = Types.INTEGER,name = "file_size",displayName = "文件大小")
	private Integer fileSize;
	@ApiModelProperty(value="更新说明",name="description",example = "1.优化界面")
	@Column(type = Types.VARCHAR,length = 1024,name = "description",displayName = "更新说明")
	private String description;
	@ApiModelProperty(value="创建人",name="createUser",example = "姓名")
	@Column(type = Types.VARCHAR,length = 30,name = "create_user",displayName = "创建人")
	private String createUser;
	@ApiModelProperty(value="创建时间",name="createTime",example = "")
	@Column(type = TypesExtend.DATETIME,name = "create_time",displayName = "创建时间")
	private Date createTime;
	@ApiModelProperty(value="修改人",name="updateUser",example = "姓名")
	@Column(type = Types.VARCHAR,length = 30,name = "update_user",displayName = "修改人")
	private String updateUser;
	@ApiModelProperty(value="修改时间",name="updateTime",example = "")
	@Column(type = TypesExtend.DATETIME,name = "update_time",displayName = "修改时间")
	private Date updateTime;

    public UpdateVersion() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.versionId == null) {
			validResult.put("versionId", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.versionNo) > 20) {
			validResult.put("versionNo", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.version) > 20) {
			validResult.put("version", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.name) > 20) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.url) > 1024) {
			validResult.put("url", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.md5Value) > 200) {
			validResult.put("md5Value", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.description) > 1024) {
			validResult.put("description", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.createUser) > 30) {
			validResult.put("createUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.updateUser) > 30) {
			validResult.put("updateUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("versionId","versionNo","version","name","sysType","platform","url","md5Value","updateState","fileSize","description","createUser","createTime","updateUser","updateTime");

	public static RequestResult validateFields(MapContext map) {
		Map<String, String> validResult = new HashMap<>();
		if(map.size()==0){
			return ResultFactory.generateErrorResult("error",ErrorCodes.VALIDATE_NOTNULL);
		}
		boolean flag;
		Set<String> mapSet = map.keySet();
		flag = propertiesList.containsAll(mapSet);
		if(!flag){
			return ResultFactory.generateErrorResult("error",ErrorCodes.VALIDATE_ILLEGAL_ARGUMENT);
		}
		if(map.containsKey("versionId")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("versionId",String.class))) {
				validResult.put("versionId", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("sysType")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("sysType",String.class))) {
				validResult.put("sysType", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("platform")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("platform",String.class))) {
				validResult.put("platform", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("updateState")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("updateState",String.class))) {
				validResult.put("updateState", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("fileSize")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("fileSize",String.class))) {
				validResult.put("fileSize", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("createTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("createTime",String.class))) {
				validResult.put("createTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("updateTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updateTime",String.class))) {
				validResult.put("updateTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("versionId")) {
			if (map.get("versionId")  == null) {
				validResult.put("versionId", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("versionNo")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("versionNo",String.class)) > 20) {
				validResult.put("versionNo", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("version")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("version",String.class)) > 20) {
				validResult.put("version", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 20) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("url")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("url",String.class)) > 1024) {
				validResult.put("url", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("md5Value")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("md5Value",String.class)) > 200) {
				validResult.put("md5Value", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("description")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("description",String.class)) > 1024) {
				validResult.put("description", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("createUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("createUser",String.class)) > 30) {
				validResult.put("createUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("updateUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("updateUser",String.class)) > 30) {
				validResult.put("updateUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setVersionId(Integer versionId){
		this.versionId=versionId;
	}

	public Integer getVersionId(){
		return versionId;
	}

	public void setVersionNo(String versionNo){
		this.versionNo=versionNo;
	}

	public String getVersionNo(){
		return versionNo;
	}

	public void setVersion(String version){
		this.version=version;
	}

	public String getVersion(){
		return version;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setSysType(Integer sysType){
		this.sysType=sysType;
	}

	public Integer getSysType(){
		return sysType;
	}

	public void setPlatform(Integer platform){
		this.platform=platform;
	}

	public Integer getPlatform(){
		return platform;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getUrl(){
		return url;
	}

	public void setMd5Value(String md5Value){
		this.md5Value=md5Value;
	}

	public String getMd5Value(){
		return md5Value;
	}

	public void setUpdateState(Integer updateState){
		this.updateState=updateState;
	}

	public Integer getUpdateState(){
		return updateState;
	}

	public void setFileSize(Integer fileSize){
		this.fileSize=fileSize;
	}

	public Integer getFileSize(){
		return fileSize;
	}

	public void setDescription(String description){
		this.description=description;
	}

	public String getDescription(){
		return description;
	}

	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}

	public String getCreateUser(){
		return createUser;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}

	public String getUpdateUser(){
		return updateUser;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}
}
