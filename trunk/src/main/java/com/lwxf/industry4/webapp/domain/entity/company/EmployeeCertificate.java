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
 * 功能：employee_certificate 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-04-04 03:17 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "employee_certificate",displayName = "employee_certificate")
@ApiModel(value = "员工证书信息",discriminator = "员工证书信息")
public class EmployeeCertificate extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_employee_id",displayName = "员工主键ID")
	@ApiModelProperty(value = "员工主键ID",required = true)
	private String companyEmployeeId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "证书名称")
	@ApiModelProperty(value = "证书名称",required = true)
	private String name;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "company",displayName = "发证单位")
	@ApiModelProperty(value = "发证单位",required = true)
	private String company;
	@Column(type = Types.VARCHAR,length = 50,name = "level",displayName = "级别")
	@ApiModelProperty(value = "级别")
	private String level;
	@Column(type = Types.DATE,name = "award_time",displayName = "获取时间")
	@ApiModelProperty(value = "获取时间")
	private Date awardTime;

    public EmployeeCertificate() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.companyEmployeeId == null) {
			validResult.put("companyEmployeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.companyEmployeeId) > 13) {
				validResult.put("companyEmployeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 50) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.company == null) {
			validResult.put("company", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.company) > 50) {
				validResult.put("company", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.level) > 50) {
			validResult.put("level", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","company","level","awardTime");

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
		if(map.containsKey("awardTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("awardTime",String.class))) {
				validResult.put("awardTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("company")) {
			if (map.getTypedValue("company",String.class)  == null) {
				validResult.put("company", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("company",String.class)) > 50) {
					validResult.put("company", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("level")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("level",String.class)) > 50) {
				validResult.put("level", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCompanyEmployeeId(String companyEmployeeId){
		this.companyEmployeeId=companyEmployeeId;
	}

	public String getCompanyEmployeeId(){
		return companyEmployeeId;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setCompany(String company){
		this.company=company;
	}

	public String getCompany(){
		return company;
	}

	public void setLevel(String level){
		this.level=level;
	}

	public String getLevel(){
		return level;
	}

	public void setAwardTime(Date awardTime){
		this.awardTime=awardTime;
	}

	public Date getAwardTime(){
		return awardTime;
	}
}
