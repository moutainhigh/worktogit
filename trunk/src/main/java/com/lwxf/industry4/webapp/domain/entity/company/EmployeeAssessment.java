package com.lwxf.industry4.webapp.domain.entity.company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.facade.AppBeanInjector;
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
 * 功能：employee_assessment 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-04-07 09:58 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "employee_assessment",displayName = "employee_assessment")
@ApiModel(value = "员工考核信息",discriminator = "员工考核信息")
public class EmployeeAssessment extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "dept_id",displayName = "部门主键ID")
	@ApiModelProperty(value = "部门主键ID",required = true)
	private String deptId;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_employee_id",displayName = "公司员工主键ID")
	@ApiModelProperty(value = "公司员工主键ID",required = true)
	private String companyEmployeeId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "assessor",displayName = "考核人")
	@ApiModelProperty(value = "考核人",required = true)
	private String assessor;
	@Column(type = Types.VARCHAR,length = 200,nullable = false,name = "evaluate",displayName = "评价")
	@ApiModelProperty(value = "评价",required = true)
	private String evaluate;
	@Column(type = Types.VARCHAR,length = 200,name = "self_evaluation",displayName = "自我评价")
	@ApiModelProperty(value = "自我评价")
	private String selfEvaluation;
	@Column(type = Types.DATE,nullable = false,name = "start_time",displayName = "开始时间")
	@ApiModelProperty(value = "开始时间",required = true)
	private Date startTime;
	@Column(type = Types.DATE,nullable = false,name = "end_time",displayName = "结束时间")
	@ApiModelProperty(value = "结束时间",required = true)
	private Date endTime;

    public EmployeeAssessment() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.deptId == null) {
			validResult.put("deptId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.deptId) > 13) {
				validResult.put("deptId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.companyEmployeeId == null) {
			validResult.put("companyEmployeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.companyEmployeeId) > 13) {
				validResult.put("companyEmployeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.assessor == null) {
			validResult.put("assessor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.assessor) > 50) {
				validResult.put("assessor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.evaluate == null) {
			validResult.put("evaluate", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.evaluate) > 200) {
				validResult.put("evaluate", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.selfEvaluation) > 200) {
			validResult.put("selfEvaluation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("deptId","assessor","evaluate","selfEvaluation","startTime","endTime");

	public static RequestResult validateFields(MapContext map) {
		Map<String, String> validResult = new HashMap<>();
		if(map.size()==0){
			return ResultFactory.generateErrorResult("error",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		boolean flag;
		Set<String> mapSet = map.keySet();
		flag = propertiesList.containsAll(mapSet);
		if(!flag){
			return ResultFactory.generateErrorResult("error",AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		}
		if(map.containsKey("startTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("startTime",String.class))) {
				validResult.put("startTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("endTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("endTime",String.class))) {
				validResult.put("endTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("deptId")) {
			if (map.getTypedValue("deptId",String.class)  == null) {
				validResult.put("deptId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("deptId",String.class)) > 13) {
					validResult.put("deptId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("assessor")) {
			if (map.getTypedValue("assessor",String.class)  == null) {
				validResult.put("assessor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("assessor",String.class)) > 50) {
					validResult.put("assessor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("evaluate")) {
			if (map.getTypedValue("evaluate",String.class)  == null) {
				validResult.put("evaluate", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("evaluate",String.class)) > 200) {
					validResult.put("evaluate", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("selfEvaluation")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("selfEvaluation",String.class)) > 200) {
				validResult.put("selfEvaluation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setDeptId(String deptId){
		this.deptId=deptId;
	}

	public String getDeptId(){
		return deptId;
	}

	public void setCompanyEmployeeId(String companyEmployeeId){
		this.companyEmployeeId=companyEmployeeId;
	}

	public String getCompanyEmployeeId(){
		return companyEmployeeId;
	}

	public void setAssessor(String assessor){
		this.assessor=assessor;
	}

	public String getAssessor(){
		return assessor;
	}

	public void setEvaluate(String evaluate){
		this.evaluate=evaluate;
	}

	public String getEvaluate(){
		return evaluate;
	}

	public void setSelfEvaluation(String selfEvaluation){
		this.selfEvaluation=selfEvaluation;
	}

	public String getSelfEvaluation(){
		return selfEvaluation;
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
}
