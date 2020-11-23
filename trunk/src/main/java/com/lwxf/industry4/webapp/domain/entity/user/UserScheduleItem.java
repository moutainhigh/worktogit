package com.lwxf.industry4.webapp.domain.entity.user;
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
 * 功能：user_schedule_item 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-14 11:46 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "user_schedule_item",displayName = "user_schedule_item")
public class UserScheduleItem extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "0 个人 1 系统")
	private Integer type;
	@Column(type = Types.VARCHAR,length = 200,nullable = false,name = "notes",displayName = "描述 不可为空")
	private String notes;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "start_time",displayName = "计划中的开始时间")
	private Date startTime;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "end_time",displayName = "计划中的结束时间")
	private Date endTime;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "user_schedule_id",displayName = "")
	private String userScheduleId;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "0待完成 1已完成 2延期 3终止")
	private Integer status;

    public UserScheduleItem() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.type == null) {
			validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.notes == null) {
			validResult.put("notes", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.notes) > 200) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.startTime == null) {
			validResult.put("startTime", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.endTime == null) {
			validResult.put("endTime", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.userScheduleId == null) {
			validResult.put("userScheduleId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.userScheduleId) > 13) {
				validResult.put("userScheduleId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","type","notes","startTime","endTime","userScheduleId","status");

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
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("startTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("startTime",String.class))) {
				validResult.put("startTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("endTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("endTime",String.class))) {
				validResult.put("endTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("notes")) {
			if (map.getTypedValue("notes",String.class)  == null) {
				validResult.put("notes", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
					validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("startTime")) {
			if (map.get("startTime")  == null) {
				validResult.put("startTime", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("endTime")) {
			if (map.get("endTime")  == null) {
				validResult.put("endTime", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("userScheduleId")) {
			if (map.getTypedValue("userScheduleId",String.class)  == null) {
				validResult.put("userScheduleId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("userScheduleId",String.class)) > 13) {
					validResult.put("userScheduleId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}

	public Date getStartTime(){
		return startTime;
	}

	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	public Date getEndTime(){
		return endTime;
	}

	public void setUserScheduleId(String userScheduleId){
		this.userScheduleId=userScheduleId;
	}

	public String getUserScheduleId(){
		return userScheduleId;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
