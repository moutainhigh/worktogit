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
 * 功能：employee_education_experience 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-04-04 03:17 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "employee_education_experience",displayName = "employee_education_experience")
@ApiModel(value = "员工教育经历",discriminator = "员工教育经历")
public class EmployeeEducationExperience extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_employee_id",displayName = "公司员工主键ID")
	@ApiModelProperty(value = "公司员工主键ID",required = true)
	private String companyEmployeeId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "学习单位名称")
	@ApiModelProperty(value = "学习单位名称",required = true)
	private String name;
	@Column(type = Types.TINYINT,nullable = false,name = "level",displayName = "教育级别: 0-小学/初中 1-高中 2-大学 3-成教 4-培训机构")
	@ApiModelProperty(value = "教育级别: 0-小学/初中 1-高中 2-大学 3-成教 4-培训机构",required = true)
	private Integer level;
	@Column(type = Types.VARCHAR,length = 50,name = "major",displayName = "专业")
	@ApiModelProperty(value = "专业")
	private String major;
	@Column(type = Types.DATE,name = "start_time",displayName = "开始时间")
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	@Column(type = Types.DATE,name = "end_time",displayName = "结束时间")
	@ApiModelProperty(value = "结束时间")
	private Date endTime;
	@Column(type = Types.VARCHAR,length = 50,name = "curriculum",displayName = "课程")
	@ApiModelProperty(value = "课程")
	private String curriculum;
	@Column(type = Types.VARCHAR,length = 200,name = "achievement",displayName = "所获荣誉")
	@ApiModelProperty(value = "所获荣誉")
	private String achievement;

    public EmployeeEducationExperience() {  
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
		if (this.level == null) {
			validResult.put("level", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.major) > 50) {
			validResult.put("major", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.curriculum) > 50) {
			validResult.put("curriculum", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.achievement) > 200) {
			validResult.put("achievement", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","level","major","startTime","endTime","curriculum","achievement");

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
		if(map.containsKey("level")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("level",String.class))) {
				validResult.put("level", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
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
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("level")) {
			if (map.get("level")  == null) {
				validResult.put("level", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("major")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("major",String.class)) > 50) {
				validResult.put("major", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("curriculum")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("curriculum",String.class)) > 50) {
				validResult.put("curriculum", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("achievement")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("achievement",String.class)) > 200) {
				validResult.put("achievement", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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

	public void setLevel(Integer level){
		this.level=level;
	}

	public Integer getLevel(){
		return level;
	}

	public void setMajor(String major){
		this.major=major;
	}

	public String getMajor(){
		return major;
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

	public void setCurriculum(String curriculum){
		this.curriculum=curriculum;
	}

	public String getCurriculum(){
		return curriculum;
	}

	public void setAchievement(String achievement){
		this.achievement=achievement;
	}

	public String getAchievement(){
		return achievement;
	}
}
