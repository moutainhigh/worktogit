package com.lwxf.industry4.webapp.domain.entity.system;
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
 * 功能：basecode 实体类
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-05-04 10:47 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "basecode",displayName = "basecode")
@ApiModel(value = "字典数据",description = "字典数据")
public class Basecode extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "name",displayName = "字典名称")
	@ApiModelProperty(value = "字典名称")
	private String name;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "type",displayName = "字典类型")
	@ApiModelProperty(value = "字典类型")
	private String type;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "code",displayName = "字典码")
	@ApiModelProperty(value = "字典码")
	private String code;
	@Column(type = Types.VARCHAR,length = 1000,nullable = false,name = "value",displayName = "字典值")
	@ApiModelProperty(value = "字典值")
	private String value;
	@Column(type = Types.INTEGER,defaultValue = "0",name = "order_num",displayName = "排序")
	@ApiModelProperty(value = "排序")
	private Integer orderNum;
	@Column(type = Types.VARCHAR,length = 255,name = "remark",displayName = "备注")
	@ApiModelProperty(value = "备注")
	private String remark;
	@Column(type = Types.TINYINT,defaultValue = "0",name = "del_flag",displayName = "删除标记  -1：已删除  0：正常")
	@ApiModelProperty(value = "删除标记  -1：已删除  0：正常")
	private Integer delFlag;

    public Basecode() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 100) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.type == null) {
			validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.type) > 100) {
				validResult.put("type", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.code == null) {
			validResult.put("code", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.code) > 100) {
				validResult.put("code", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.value == null) {
			validResult.put("value", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.value) > 1000) {
				validResult.put("value", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.remark) > 255) {
			validResult.put("remark", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","name","type","code","value","orderNum","remark","delFlag");

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
		if(map.containsKey("orderNum")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("orderNum",String.class))) {
				validResult.put("orderNum", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("delFlag")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("delFlag",String.class))) {
				validResult.put("delFlag", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 100) {
					validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("type")) {
			if (map.getTypedValue("type",String.class)  == null) {
				validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("type",String.class)) > 100) {
					validResult.put("type", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("code")) {
			if (map.getTypedValue("code",String.class)  == null) {
				validResult.put("code", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("code",String.class)) > 100) {
					validResult.put("code", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("value")) {
			if (map.getTypedValue("value",String.class)  == null) {
				validResult.put("value", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("value",String.class)) > 1000) {
					validResult.put("value", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("remark")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("remark",String.class)) > 255) {
				validResult.put("remark", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setType(String type){
		this.type=type;
	}

	public String getType(){
		return type;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getCode(){
		return code;
	}

	public void setValue(String value){
		this.value=value;
	}

	public String getValue(){
		return value;
	}

	public void setOrderNum(Integer orderNum){
		this.orderNum=orderNum;
	}

	public Integer getOrderNum(){
		return orderNum;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
