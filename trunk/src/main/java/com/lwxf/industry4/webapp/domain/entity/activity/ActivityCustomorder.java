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
 * 功能：activity_customorder 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 09:57 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "activity_customorder",displayName = "activity_customorder")
public class ActivityCustomorder extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "activity_id",displayName = "")
	private String activityId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "order_id",displayName = "订单id")
	private String orderId;
	@Column(type = Types.CHAR,length = 13,name = "activity_params_id",displayName = "活动参数id")
	private String activityParamsId;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;

    public ActivityCustomorder() {  
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
		if (this.orderId == null) {
			validResult.put("orderId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.orderId) > 13) {
				validResult.put("orderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.activityParamsId) > 13) {
			validResult.put("activityParamsId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.created == null) {
			validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","activityId","orderId","activityParamsId","created");

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
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
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
		if(map.containsKey("orderId")) {
			if (map.getTypedValue("orderId",String.class)  == null) {
				validResult.put("orderId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("orderId",String.class)) > 13) {
					validResult.put("orderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("activityParamsId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("activityParamsId",String.class)) > 13) {
				validResult.put("activityParamsId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
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

	public void setOrderId(String orderId){
		this.orderId=orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setActivityParamsId(String activityParamsId){
		this.activityParamsId=activityParamsId;
	}

	public String getActivityParamsId(){
		return activityParamsId;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}
}
