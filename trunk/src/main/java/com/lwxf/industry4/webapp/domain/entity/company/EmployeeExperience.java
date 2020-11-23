package com.lwxf.industry4.webapp.domain.entity.company;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.*;
/**
 * 功能：employee_experience 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-04-04 03:17 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "employee_experience",displayName = "employee_experience")
@ApiModel(value = "员工工作经历",discriminator = "员工工作经历")
public class EmployeeExperience extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 50,name = "company_name",displayName = "单位名称")
	@ApiModelProperty(value = "单位名称",name = "companyName")
	private String companyName;
	@Column(type = Types.VARCHAR,length = 50,name = "position",displayName = "职位")
	@ApiModelProperty(value = "职位",name = "position")
	private String position;
	@Column(type = Types.DATE,name = "start_time",displayName = "开始时间")
	@ApiModelProperty(value = "开始时间",name = "startTime")
	private Date startTime;
	@Column(type = Types.DATE,name = "end_time",displayName = "结束时间")
	@ApiModelProperty(value = "结束时间",name = "endTime")
	private Date endTime;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_employee_id",displayName = "公司员工主键ID")
	@ApiModelProperty(value = "公司员工主键ID",name = "companyEmplyeeId",required = true)
	private String companyEmployeeId;
	@Column(type = Types.VARCHAR,length = 200,name = "achievement",displayName = "主要成就")
	@ApiModelProperty(value = "主要成就",name = "achievement")
	private String achievement;
	@Column(type = Types.VARCHAR,length = 200,name = "duty",displayName = "职责")
	@ApiModelProperty(value = "职责",name = "duty")
	private String duty;
	@Column(type = Types.VARCHAR,length = 50,name = "prove",displayName = "证明人")
	@ApiModelProperty(value = "证明人",name = "prove")
	private String prove;
	@Column(type = Types.VARCHAR,length = 50,name = "prove_tel",displayName = "证明人电话")
	@ApiModelProperty(value = "证明人电话",name = "proveTel")
	private String proveTel;

    public EmployeeExperience() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.companyName) > 50) {
			validResult.put("companyName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.position) > 50) {
			validResult.put("position", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.companyEmployeeId) > 50) {
			validResult.put("companyEmployeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.achievement) > 200) {
			validResult.put("achievement", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.duty) > 200) {
			validResult.put("duty", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.prove) > 50) {
			validResult.put("prove", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.proveTel) > 50) {
			validResult.put("proveTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.companyEmployeeId==null) {
			validResult.put("companyEmployeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyEmployeeId) > 13) {
				validResult.put("companyEmployeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("companyName","position","startTime","endTime","achievement","duty","prove","proveTel");

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
		if(map.containsKey("companyName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("companyName",String.class)) > 50) {
				validResult.put("companyName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("position")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("position",String.class)) > 50) {
				validResult.put("position", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("achievement")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("achievement",String.class)) > 200) {
				validResult.put("achievement", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("duty")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("duty",String.class)) > 200) {
				validResult.put("duty", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("prove")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("prove",String.class)) > 50) {
				validResult.put("prove", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("proveTel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("proveTel",String.class)) > 50) {
				validResult.put("proveTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCompanyName(String companyName){
		this.companyName=companyName;
	}

	public String getCompanyName(){
		return companyName;
	}

	public void setPosition(String position){
		this.position=position;
	}

	public String getPosition(){
		return position;
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

	public void setCompanyEmployeeId(String companyEmployeeId){
		this.companyEmployeeId=companyEmployeeId;
	}

	public String getCompanyEmployeeId(){
		return companyEmployeeId;
	}

	public void setAchievement(String achievement){
		this.achievement=achievement;
	}

	public String getAchievement(){
		return achievement;
	}

	public void setDuty(String duty){
		this.duty=duty;
	}

	public String getDuty(){
		return duty;
	}

	public void setProve(String prove){
		this.prove=prove;
	}

	public String getProve(){
		return prove;
	}

	public void setProveTel(String proveTel){
		this.proveTel=proveTel;
	}

	public String getProveTel(){
		return proveTel;
	}
}
