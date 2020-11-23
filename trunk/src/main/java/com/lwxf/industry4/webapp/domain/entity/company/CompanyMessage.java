package com.lwxf.industry4.webapp.domain.entity.company;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

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
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：company_message 实体类
 *
 * @author：zhangxiaolin 3965488@qq.com
 * @created：2019-06-14 04:19 
 * @version：2019 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "company_message",displayName = "company_message")
public class CompanyMessage extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="发送者",name="fromUser",example = "4j1u3r1efshq")
	@Column(type = Types.CHAR,length = 13,name = "from_user",displayName = "发送者")
	private String fromUser;
	@ApiModelProperty(value="接收者",name="fromUser",example = "4vwk26sl1jwg")
	@Column(type = Types.CHAR,length = 13,name = "to_user",displayName = "接受者")
	private String toUser;
	@ApiModelProperty(value="信息",name="message",example = "test")
	@Column(type = Types.VARCHAR,name = "message",displayName = "信息")
	private String message;
	@ApiModelProperty(value="流向：1：经销商发给工厂，2：工厂发给经销商",name="direct",example = "2")
	@Column(type = Types.TINYINT,name = "direct",displayName = "流向：1：经销商发给工厂，2：工厂发给经销商")
	private Byte direct;
	@ApiModelProperty(value="图片或文字：1：文字，2：图片",name="type",example = "1")
	@Column(type = Types.TINYINT,name = "type",displayName = "图片或文字：1：文字，2：图片")
	private Byte type;
	@ApiModelProperty(value="图片 url",name="imgUrl",example = "")
	@Column(type = Types.VARCHAR,length = 200,name = "img_url",displayName = "图片 url")
	private String imgUrl;
	@ApiModelProperty(value="工厂已读标志",name="factoryRead",example = "0")
	@Column(type = Types.TINYINT,name = "factory_read",displayName = "工厂已读标志")
	private Byte factoryRead;
	@ApiModelProperty(value="经销商已读标志",name="companyRead",example = "0")
	@Column(type = Types.TINYINT,name = "company_read",displayName = "经销商已读标志")
	private Byte companyRead;
	@ApiModelProperty(value="经销商id",name="companyId",example = "4vwk26snjg8w")
	@Column(type = Types.INTEGER,name = "company_id",displayName = "经销商id")
	private String companyId;
	@ApiModelProperty(value="创建时间",name="created")
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	private Date created;
	@ApiModelProperty(value="企业id",name="branchId",example = "40ord3va6adp")
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	private String branchId;

    public CompanyMessage() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.fromUser) > 13) {
			validResult.put("fromUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.toUser) > 13) {
			validResult.put("toUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.imgUrl) > 200) {
			validResult.put("imgUrl", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
			validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","fromUser","toUser","message","direct","type","imgUrl","factoryRead","companyRead","companyId","created","branchId");

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
		if(map.containsKey("direct")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("direct",String.class))) {
				validResult.put("direct", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("factoryRead")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("factoryRead",String.class))) {
				validResult.put("factoryRead", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("companyRead")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("companyRead",String.class))) {
				validResult.put("companyRead", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("companyId")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("companyId",String.class))) {
				validResult.put("companyId", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("fromUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("fromUser",String.class)) > 13) {
				validResult.put("fromUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("toUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("toUser",String.class)) > 13) {
				validResult.put("toUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("imgUrl")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("imgUrl",String.class)) > 200) {
				validResult.put("imgUrl", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("branchId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("branchId",String.class)) > 13) {
				validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setFromUser(String fromUser){
		this.fromUser=fromUser;
	}

	public String getFromUser(){
		return fromUser;
	}

	public void setToUser(String toUser){
		this.toUser=toUser;
	}

	public String getToUser(){
		return toUser;
	}

	public void setMessage(String message){
		this.message=message;
	}

	public String getMessage(){
		return message;
	}

	public void setDirect(Byte direct){
		this.direct=direct;
	}

	public Byte getDirect(){
		return direct;
	}

	public void setType(Byte type){
		this.type=type;
	}

	public Byte getType(){
		return type;
	}

	public void setImgUrl(String imgUrl){
		this.imgUrl=imgUrl;
	}

	public String getImgUrl(){
		return imgUrl;
	}

	public void setFactoryRead(Byte factoryRead){
		this.factoryRead=factoryRead;
	}

	public Byte getFactoryRead(){
		return factoryRead;
	}

	public void setCompanyRead(Byte companyRead){
		this.companyRead=companyRead;
	}

	public Byte getCompanyRead(){
		return companyRead;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setBranchId(String branchId){
		this.branchId=branchId;
	}

	public String getBranchId(){
		return branchId;
	}
}
