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
 * 功能：user_schedule 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-14 11:46 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "user_schedule",displayName = "user_schedule")
public class UserSchedule extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "time",displayName = "日程日期")
	private Date time;
	@Column(type = Types.INTEGER,name = "size",displayName = "事件个数")
	private Integer size;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "user_id",displayName = "用户Id")
	private String userId;

    public UserSchedule() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.time == null) {
			validResult.put("time", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.userId == null) {
			validResult.put("userId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","time","size","userId");

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
		if(map.containsKey("time")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("time",String.class))) {
				validResult.put("time", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("size")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("size",String.class))) {
				validResult.put("size", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("time")) {
			if (map.get("time")  == null) {
				validResult.put("time", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("userId")) {
			if (map.getTypedValue("userId",String.class)  == null) {
				validResult.put("userId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("userId",String.class)) > 13) {
					validResult.put("userId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setTime(Date time){
		this.time=time;
	}

	public Date getTime(){
		return time;
	}

	public void setSize(Integer size){
		this.size=size;
	}

	public Integer getSize(){
		return size;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}
}
