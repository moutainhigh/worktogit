package com.lwxf.industry4.webapp.domain.entity.engineering;
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
 * 功能：engineering_order 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-05-27 05:57 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="engineering_order对象", description="engineering_order")
@Table(name = "engineering_order",displayName = "engineering_order")
public class EngineeringOrder extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,name = "custom_order_id",displayName = "订单表id")
	@ApiModelProperty(value = "订单表id")
	private String customOrderId;
	@Column(type = Types.CHAR,length = 13,name = "engineering_id",displayName = "工程订单基础信息表id")
	@ApiModelProperty(value = "工程订单基础信息表id")
	private String engineeringId;

    public EngineeringOrder() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
			validResult.put("customOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.engineeringId) > 13) {
			validResult.put("engineeringId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","customOrderId","engineeringId");

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
		if(map.containsKey("customOrderId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("customOrderId",String.class)) > 13) {
				validResult.put("customOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("engineeringId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("engineeringId",String.class)) > 13) {
				validResult.put("engineeringId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setEngineeringId(String engineeringId){
		this.engineeringId=engineeringId;
	}

	public String getEngineeringId(){
		return engineeringId;
	}
}
