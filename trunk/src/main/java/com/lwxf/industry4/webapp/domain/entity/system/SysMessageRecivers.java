package com.lwxf.industry4.webapp.domain.entity.system;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：sys_message_recivers 实体类
 *
 * @author：zhangxiaolin(3965488qq.com)
 * @created：2019-12-09 04:42 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="消息接收者实体", description="消息接收者实体")
@Table(name = "sys_message_recivers",displayName = "sys_message_recivers")
public class SysMessageRecivers extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 0,name = "message_id",displayName = "消息id")
	@ApiModelProperty(value = "消息id")
	private String messageId;
	@Column(type = Types.CHAR,length = 0,name = "user_id",displayName = "用户id")
	@ApiModelProperty(value = "用户id")
	private String userId;
	@Column(type = Types.INTEGER,name = "is_read",displayName = "是否已读")
	@ApiModelProperty(value = "是否已读")
	private Integer read;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;

    public SysMessageRecivers() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.messageId) > 0) {
			validResult.put("messageId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.userId) > 0) {
			validResult.put("userId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","messageId","userId","read","created");

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
		if(map.containsKey("read")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("read",String.class))) {
				validResult.put("read", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("messageId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("messageId",String.class)) > 0) {
				validResult.put("messageId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("userId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("userId",String.class)) > 0) {
				validResult.put("userId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setMessageId(String messageId){
		this.messageId=messageId;
	}

	public String getMessageId(){
		return messageId;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setRead(Integer read){
		this.read=read;
	}

	public Integer getRead(){
		return read;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}
}
