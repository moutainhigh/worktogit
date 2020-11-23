package com.lwxf.industry4.webapp.domain.entity.activity;
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
 * 功能：activity_params 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 09:57 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "activity_params",displayName = "activity_params")
public class ActivityParams extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "activity_id",displayName = "活动id	")
	private String activityId;
	@Column(type = Types.VARCHAR,length = 50,name = "name",displayName = "项目名(参数标题)")
	private String name;
	@Column(type = Types.VARCHAR,length = 50,name = "params",displayName = "参数")
	private String params;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "描述")
	private String notes;

    public ActivityParams() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.activityId == null) {
			validResult.put("activityId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.activityId) > 13) {
				validResult.put("activityId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.name) > 50) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.params) > 50) {
			validResult.put("params", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","activityId","name","params","notes");

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
		if(map.containsKey("activityId")) {
			if (map.getTypedValue("activityId",String.class)  == null) {
				validResult.put("activityId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("activityId",String.class)) > 13) {
					validResult.put("activityId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("params")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("params",String.class)) > 50) {
				validResult.put("params", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setActivityId(String activityId){
		this.activityId=activityId;
	}

	public String getActivityId(){
		return activityId;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setParams(String params){
		this.params=params;
	}

	public String getParams(){
		return params;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}
}
