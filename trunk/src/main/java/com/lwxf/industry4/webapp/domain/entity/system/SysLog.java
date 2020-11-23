package com.lwxf.industry4.webapp.domain.entity.system;
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：sys_log 实体类
 *
 * @author：zhangxiaolin(Mister_pan@126.com)
 * @created：2019-08-08 10:47 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "sys_log",displayName = "sys_log")
@ApiModel(value="操作日志实体类",description="日常记账实体")
public class SysLog extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 20,name = "operator_name",displayName = "操作人名称")
	@ApiModelProperty(value="操作人名称",name="operatorName")
	private String operatorName;
	@ApiModelProperty(value="操作人ID",name="operatorId")
	@Column(type = Types.VARCHAR,length = 50,name = "operator_id",displayName = "操作人id")
	private String operatorId;
	@ApiModelProperty(value="操作说明",name="operation")
	@Column(type = Types.VARCHAR,length = 100,name = "method",displayName = "方法名")
	private String operation;
	@ApiModelProperty(value="操作类型",name="operationType")
	@Column(type = Types.VARCHAR,length = 100,name = "operation_type",displayName = "方法名")
	private String operationType;
	@ApiModelProperty(value="操作方法名",name="method")
	@Column(type = Types.VARCHAR,length = 100,name = "method",displayName = "方法名")
	private String method;
	@ApiModelProperty(value="参数",name="params")
	@Column(type = Types.VARCHAR,length = 500,name = "params",displayName = "参数")
	private String params;
	@ApiModelProperty(value="操作描述",name="description")
	@Column(type = Types.VARCHAR,length = 100,name = "description",displayName = "描述,组合后在前台显示")
	private String description;
	@ApiModelProperty(value="模块名称",name="mouduleName")
	@Column(type = Types.VARCHAR,length = 50,name = "moudule_name",displayName = "模塊名稱")
	private String mouduleName;
	@ApiModelProperty(value="模块编码",name="mouduleCode")
	@Column(type = Types.VARCHAR,length = 50,name = "moudule_code",displayName = "模塊名稱")
	private String mouduleCode;
	@ApiModelProperty(value="模块类名",name="className")
	@Column(type = Types.VARCHAR,length = 100,name = "class_name",displayName = "类名")
	private String className;
	@ApiModelProperty(value="创建时间",name="created")
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	private String branchId;

    public SysLog() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.operatorName) > 20) {
			validResult.put("operatorName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.operation) > 50) {
			validResult.put("operation", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.method) > 100) {
			validResult.put("method", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.params) > 500) {
			validResult.put("params", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.description) > 100) {
			validResult.put("description", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	private final static List<String> propertiesList = Arrays.asList("id","operatorName","operation","method","params","desc","created","branchId");

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
		if(map.containsKey("operatorName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("operatorName",String.class)) > 20) {
				validResult.put("operatorName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("operation")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("operation",String.class)) > 50) {
				validResult.put("operation", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("method")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("method",String.class)) > 100) {
				validResult.put("method", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("params")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("params",String.class)) > 100) {
				validResult.put("params", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("desc")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("desc",String.class)) > 100) {
				validResult.put("desc", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public String getOperatorId() {
		return operatorId;
	}

	public void setOperatorId(String operatorId) {
		this.operatorId = operatorId;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
	}

	public void setOperatorName(String operatorName){
		this.operatorName=operatorName;
	}

	public String getOperatorName(){
		return operatorName;
	}

	public void setOperation(String operation){
		this.operation=operation;
	}

	public String getOperation(){
		return operation;
	}

	public void setMethod(String method){
		this.method=method;
	}

	public String getMethod(){
		return method;
	}

	public void setParams(String params){
		this.params=params;
	}

	public String getParams(){
		return params;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getMouduleName() {
		return mouduleName;
	}

	public void setMouduleName(String mouduleName) {
		this.mouduleName = mouduleName;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMouduleCode() {
		return mouduleCode;
	}

	public void setMouduleCode(String mouduleCode) {
		this.mouduleCode = mouduleCode;
	}
}
