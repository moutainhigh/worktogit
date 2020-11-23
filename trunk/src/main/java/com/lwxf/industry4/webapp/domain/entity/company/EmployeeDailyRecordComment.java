package com.lwxf.industry4.webapp.domain.entity.company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
/**
 * 功能：employee_daily_record_comment 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-05-25 10:02 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value = "员工日志的评论信息",description = "员工日志的评论信息")
@Table(name = "employee_daily_record_comment",displayName = "employee_daily_record_comment")
public class EmployeeDailyRecordComment extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "commentUser",value = "评论人(后台默认当前登录人)")
	@Column(type = Types.CHAR,length = 13,name = "comment_user",displayName = "评论人")
	private String commentUser;
	@ApiModelProperty(name = "commentContent",value = "评论内容")
	@Column(type = Types.VARCHAR,length = 2048,name = "comment_content",displayName = "评论内容")
	private String commentContent;
	@ApiModelProperty(name = "createTime",value = "评论时间(后台默认当前系统时间)")
	@Column(type = TypesExtend.DATETIME,name = "create_time",displayName = "评论时间")
	private Date createTime;
	@ApiModelProperty(name = "updateTime",value = "修改时间")
	@Column(type = TypesExtend.DATETIME,name = "update_time",displayName = "修改时间")
	private Date updateTime;
	@ApiModelProperty(name = "dailyRecordId",value = "日志ID")
	@Column(type = Types.CHAR,length = 13,name = "daily_record_id",displayName = "日志ID")
	private String dailyRecordId;

    public EmployeeDailyRecordComment() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.commentUser) > 13) {
			validResult.put("commentUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.commentContent) > 2048) {
			validResult.put("commentContent", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.dailyRecordId) > 13) {
			validResult.put("dailyRecordId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","commentUser","commentContent","createTime","updateTime","dailyRecordId");

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
		if(map.containsKey("commentUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("commentUser",String.class)) > 13) {
				validResult.put("commentUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("commentContent")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("commentContent",String.class)) > 2048) {
				validResult.put("commentContent", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("dailyRecordId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("dailyRecordId",String.class)) > 13) {
				validResult.put("dailyRecordId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCommentUser(String commentUser){
		this.commentUser=commentUser;
	}

	public String getCommentUser(){
		return commentUser;
	}

	public void setCommentContent(String commentContent){
		this.commentContent=commentContent;
	}

	public String getCommentContent(){
		return commentContent;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setDailyRecordId(String dailyRecordId){
		this.dailyRecordId=dailyRecordId;
	}

	public String getDailyRecordId(){
		return dailyRecordId;
	}
}
