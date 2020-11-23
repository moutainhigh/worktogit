package com.lwxf.industry4.webapp.domain.entity.chatrecord;
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
 * 功能：chat_record 实体类
 *
 * @author：lyh
 * @created：2019-12-05 07:15 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="chat_record对象", description="chat_record")
@Table(name = "chat_record",displayName = "chat_record")
public class ChatRecord extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 1,defaultValue = "1",name = "chat_type",displayName = "记录类型：1.字符串2.文件路径")
	@ApiModelProperty(value = "记录类型：1.字符串2.文件路径")
	private String chatType;
	@Column(type = TypesExtend.DATETIME,name = "chat_time",displayName = "聊天插入记录时间")
	@ApiModelProperty(value = "聊天插入记录时间")
	private Date chatTime;
	@Column(type = Types.VARCHAR,length = 2550,name = "chat_content",displayName = "聊天内容：文字为字符串，图片文件为地址")
	@ApiModelProperty(value = "聊天内容：文字为字符串，图片文件为地址")
	private String chatContent;
	@Column(type = Types.VARCHAR,length = 255,name = "chat_username",displayName = "用户姓名")
	@ApiModelProperty(value = "用户姓名")
	private String chatUsername;
	@Column(type = Types.VARCHAR,length = 255,name = "chat_user_id",displayName = "用户id")
	@ApiModelProperty(value = "用户id")
	private String chatUserId;
	@Column(type = Types.VARCHAR,length = 255,name = "chat_depat_id",displayName = "部门id")
	@ApiModelProperty(value = "部门id")
	private String chatDepatId;
	@Column(type = Types.VARCHAR,length = 255,defaultValue = "",name = "chat_depat_name",displayName = "部门名称")
	@ApiModelProperty(value = "部门名称")
	private String chatDepatName;
	@Column(type = TypesExtend.DATETIME,name = "chat_insert_time",displayName = "聊天记录插入时间")
	@ApiModelProperty(value = "聊天记录插入时间")
	private Date chatInsertTime;
	@Column(type = Types.CHAR,length = 1,defaultValue = "1",name = "chat_is_send",displayName = "是否已发送:1.已发送 2.未发送")
	@ApiModelProperty(value = "是否已发送:1.已发送 2.未发送")
	private String chatIsSend;
	@Column(type = Types.CHAR,length = 1,defaultValue = "1",name = "chat_is_Read",displayName = "是否已发送:1.已读 2.未读")
	@ApiModelProperty(value = "是否已发送:1.已读 2.未读")
	private String chatIsRead;
	@Column(type = Types.VARCHAR,length = 255,defaultValue = "",name = "chat_content_type",displayName = "聊天类型：1.点对点消息 2.群消息")
	@ApiModelProperty(value = "聊天类型：1.点对点消息 2.群消息")
	private String chatContentType;
	@Column(type = Types.VARCHAR,length = 255,defaultValue = "",name = "chat_reserve1",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String chatReserve1;
	@Column(type = Types.VARCHAR,length = 255,defaultValue = "",name = "chat_reserve2",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String chatReserve2;
	@Column(type = Types.VARCHAR,length = 255,defaultValue = "",name = "chat_reserve3",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String chatReserve3;

    public ChatRecord() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.chatType) > 1) {
			validResult.put("chatType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatContent) > 2550) {
			validResult.put("chatContent", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatUsername) > 255) {
			validResult.put("chatUsername", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatUserId) > 255) {
			validResult.put("chatUserId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatDepatId) > 255) {
			validResult.put("chatDepatId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatDepatName) > 255) {
			validResult.put("chatDepatName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatIsSend) > 1) {
			validResult.put("chatIsSend", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatIsRead) > 1) {
			validResult.put("chatIsRead", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatContentType) > 255) {
			validResult.put("chatContentType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatReserve1) > 255) {
			validResult.put("chatReserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatReserve2) > 255) {
			validResult.put("chatReserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.chatReserve3) > 255) {
			validResult.put("chatReserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","chatType","chatTime","chatContent","chatUsername","chatUserId","chatDepatId","chatDepatName","chatInsertTime","chatIsSend","chatIsRead","chatContentType","chatReserve1","chatReserve2","chatReserve3");

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
		if(map.containsKey("chatTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("chatTime",String.class))) {
				validResult.put("chatTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("chatInsertTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("chatInsertTime",String.class))) {
				validResult.put("chatInsertTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("chatType")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatType",String.class)) > 1) {
				validResult.put("chatType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatContent")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatContent",String.class)) > 2550) {
				validResult.put("chatContent", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatUsername")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatUsername",String.class)) > 255) {
				validResult.put("chatUsername", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatUserId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatUserId",String.class)) > 255) {
				validResult.put("chatUserId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatDepatId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatDepatId",String.class)) > 255) {
				validResult.put("chatDepatId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatDepatName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatDepatName",String.class)) > 255) {
				validResult.put("chatDepatName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatIsSend")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatIsSend",String.class)) > 1) {
				validResult.put("chatIsSend", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatIsRead")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatIsRead",String.class)) > 1) {
				validResult.put("chatIsRead", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatContentType")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatContentType",String.class)) > 255) {
				validResult.put("chatContentType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatReserve1")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatReserve1",String.class)) > 255) {
				validResult.put("chatReserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatReserve2")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatReserve2",String.class)) > 255) {
				validResult.put("chatReserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("chatReserve3")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("chatReserve3",String.class)) > 255) {
				validResult.put("chatReserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setChatType(String chatType){
		this.chatType=chatType;
	}

	public String getChatType(){
		return chatType;
	}

	public void setChatTime(Date chatTime){
		this.chatTime=chatTime;
	}

	public Date getChatTime(){
		return chatTime;
	}

	public void setChatContent(String chatContent){
		this.chatContent=chatContent;
	}

	public String getChatContent(){
		return chatContent;
	}

	public void setChatUsername(String chatUsername){
		this.chatUsername=chatUsername;
	}

	public String getChatUsername(){
		return chatUsername;
	}

	public void setChatUserId(String chatUserId){
		this.chatUserId=chatUserId;
	}

	public String getChatUserId(){
		return chatUserId;
	}

	public void setChatDepatId(String chatDepatId){
		this.chatDepatId=chatDepatId;
	}

	public String getChatDepatId(){
		return chatDepatId;
	}

	public void setChatDepatName(String chatDepatName){
		this.chatDepatName=chatDepatName;
	}

	public String getChatDepatName(){
		return chatDepatName;
	}

	public void setChatInsertTime(Date chatInsertTime){
		this.chatInsertTime=chatInsertTime;
	}

	public Date getChatInsertTime(){
		return chatInsertTime;
	}

	public void setChatIsSend(String chatIsSend){
		this.chatIsSend=chatIsSend;
	}

	public String getChatIsSend(){
		return chatIsSend;
	}

	public void setChatIsRead(String chatIsRead){
		this.chatIsRead=chatIsRead;
	}

	public String getChatIsRead(){
		return chatIsRead;
	}

	public void setChatContentType(String chatContentType){
		this.chatContentType=chatContentType;
	}

	public String getChatContentType(){
		return chatContentType;
	}

	public void setChatReserve1(String chatReserve1){
		this.chatReserve1=chatReserve1;
	}

	public String getChatReserve1(){
		return chatReserve1;
	}

	public void setChatReserve2(String chatReserve2){
		this.chatReserve2=chatReserve2;
	}

	public String getChatReserve2(){
		return chatReserve2;
	}

	public void setChatReserve3(String chatReserve3){
		this.chatReserve3=chatReserve3;
	}

	public String getChatReserve3(){
		return chatReserve3;
	}
}
