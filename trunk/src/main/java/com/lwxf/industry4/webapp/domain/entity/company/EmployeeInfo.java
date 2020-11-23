package com.lwxf.industry4.webapp.domain.entity.company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
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
 * 功能：employee_info 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-04-04 03:17 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "employee_info",displayName = "employee_info")
@ApiModel(value = "员工信息",discriminator = "员工信息")
public class EmployeeInfo extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_employee_id",displayName = "公司员工主键ID")
	@ApiModelProperty(value = "公司员工主键ID",name = "companyEmployeeId",required = true)
	private String companyEmployeeId;
	@Column(type = Types.VARCHAR,length = 50,name = "no",displayName = "工号")
	@ApiModelProperty(value = "工号",name = "no")
	private String no;
	@Column(type = Types.VARCHAR,length = 50,name = "emergency_contact_name",displayName = "紧急联系人名称")
	@ApiModelProperty(value = "紧急联系人名称",name = "emergencyContactName")
	private String emergencyContactName;
	@Column(type = Types.VARCHAR,length = 50,name = "emergency_contact_tel",displayName = "紧急联系人电话")
	@ApiModelProperty(name = "emergencyContactTel",value = "紧急联系人电话")
	private String emergencyContactTel;
	@Column(type = Types.BIT,name = "is_marry",displayName = "是否已婚 0 false 未婚 1 true 已婚")
	@ApiModelProperty(name = "marry",value = "是否已婚 0 false 未婚 1 true 已婚")
	private Boolean marry;
	@Column(type = Types.VARCHAR,length = 50,name = "status",displayName = "在职状态: 0(正常) 1(即将离职) 2(劝退) 3(辞退) 4(离职)")
	@ApiModelProperty(name = "status",value = "在职状态: 0(正常) 1(即将离职) 2(劝退) 3(辞退) 4(离职)")
	private String status;
	@Column(type = Types.VARCHAR,length = 50,name = "physical_condition",displayName = "身体状况")
	@ApiModelProperty(name = "physicalCondition",value = "身体状况")
	private String physicalCondition;
	@Column(type = Types.BIT,name = "is_receive_work_clothes",displayName = "是否领取工装 0 false 未领取 1 true 已领取")
	@ApiModelProperty(name = "receiveWorkClothes",value = "是否领取工装 0 false 未领取 1 true 已领取")
	private Boolean receiveWorkClothes;
	@Column(type = Types.BIT,name = "is_pay_social_security",displayName = "是否缴纳社保")
	@ApiModelProperty(name = "paySocialSecurity",value = "是否缴纳社保")
	private Boolean paySocialSecurity;
	@Column(type = Types.BIT,name = "is_accommodation",displayName = "是否住宿 0 false 不住宿 1 true 住宿")
	@ApiModelProperty(name = "accommodation",value = "是否住宿 0 false 不住宿 1 true 住宿")
	private Boolean accommodation;
	@Column(type = Types.BIT,name = "is_drive",displayName = "是否驾车 0 false 不驾车 1 true 驾车")
	@ApiModelProperty(name = "drive",value = "是否驾车 0 false 不驾车 1 true 驾车")
	private Boolean drive;
	@Column(type = Types.VARCHAR,length = 50,name = "license_plate_num",displayName = "车牌号")
	@ApiModelProperty(name = "licensePlateNum",value = "车牌号")
	private String licensePlateNum;
	@Column(type = Types.TINYINT,name = "source",displayName = "来源 0 社会招聘 1 员工推荐 2 中介机构 3.其它")
	@ApiModelProperty(name = "source",value = "来源 0 社会招聘 1 员工推荐 2 中介机构 3.其它")
	private Integer source;
	@Column(type = Types.TINYINT,name = "rank",displayName = "职级 1 一级 2 二级 3 三级")
	@ApiModelProperty(name = "rank",value = "职级 1 一级 2 二级 3 三级")
	private Integer rank;
	@Column(type = Types.VARCHAR,length = 200,name = "leadership_comments",displayName = "领导意见")
	@ApiModelProperty(name = "leadershipComments",value = "领导意见")
	private String leadershipComments;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "user_id",displayName = "员工的用户账号ID")
	@ApiModelProperty(name = "userId",value = "员工的用户账号ID")
	private String userId;
	@Column(type = Types.VARCHAR,length = 100,name = "university_graduated",displayName = "毕业院校")
	@ApiModelProperty(name = "universityGraduated",value = "毕业院校")
	private String universityGraduated;
	@Column(type = Types.DATE,name = "graduation_time",displayName = "毕业时间")
	@ApiModelProperty(name = "graduationTime",value = "毕业时间")
	private Date graduationTime;
	@Column(type = Types.DATE,name = "entry_time",displayName = "入职时间")
	@ApiModelProperty(name = "entryTime",value = "入职时间")
	private Date entryTime;
	@Column(type = Types.DATE,name = "correction_time",displayName = "转正时间")
	@ApiModelProperty(name = "correctionTime",value = "转正时间")
	private Date correctionTime;
	@Column(type = Types.DATE,name = "departure_time",displayName = "离职时间")
	@ApiModelProperty(name = "departureTime",value = "离职时间")
	private Date departureTime;
	@Column(type = Types.VARCHAR,length = 200,name = "wage_information",displayName = "工资信息")
	@ApiModelProperty(name = "wageInformation",value = "工资信息")
	private String wageInformation;
	@Column(type = Types.VARCHAR,length = 200,name = "self_evaluation",displayName = "自我评价")
	@ApiModelProperty(name = "selfEvaluation",value = "自我评价")
	private String selfEvaluation;
	@Column(type = Types.VARCHAR,length = 200,name = "supervisor_evaluation",displayName = "主管评价")
	@ApiModelProperty(value = "主管评价")
	private String supervisorEvaluation;
	@Column(type = Types.VARCHAR,length = 200,name = "personnel_evaluation",displayName = "人事评价")
	@ApiModelProperty(value = "人事评价")
	private String personnelEvaluation;
	@Column(type = Types.VARCHAR,length = 200,name = "exceptional_case",displayName = "特殊情况")
	@ApiModelProperty(value = "特殊情况")
	private String exceptionalCase;
	@Column(type = Types.DATE,name = "receive_work_clothes_time",displayName = "工装领取时间")
	@ApiModelProperty(value = "工装领取时间")
	private Date receiveWorkClothesTime;
	@Column(type = Types.VARCHAR,length = 50,name = "photo",displayName = "照片路径 全路径")
	@ApiModelProperty(value = "照片路径 全路径")
	private String photo;
	@Column(type = Types.VARCHAR,length = 50,name = "wage_card_number",displayName = "工资卡号")
	@ApiModelProperty(value = "工资卡号")
	private String wageCardNumber;
	@Column(type = Types.VARCHAR,length = 50,name = "bank_name",displayName = "开户银行")
	@ApiModelProperty(value = "开户银行")
	private String bankName;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "wages",displayName = "工资")
	@ApiModelProperty(value = "工资")
	private BigDecimal wages;

    public EmployeeInfo() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if(this.companyEmployeeId==null){
			validResult.put("companyEmployeeId",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyEmployeeId) > 13) {
				validResult.put("companyEmployeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.no) > 50) {
			validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.emergencyContactName) > 50) {
			validResult.put("emergencyContactName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.emergencyContactTel) > 50) {
			validResult.put("emergencyContactTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.status) > 50) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.physicalCondition) > 50) {
			validResult.put("physicalCondition", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.licensePlateNum) > 50) {
			validResult.put("licensePlateNum", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.leadershipComments) > 200) {
			validResult.put("leadershipComments", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.userId == null) {
			validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.universityGraduated) > 100) {
			validResult.put("universityGraduated", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.wageInformation) > 200) {
			validResult.put("wageInformation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.selfEvaluation) > 200) {
			validResult.put("selfEvaluation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.supervisorEvaluation) > 200) {
			validResult.put("supervisorEvaluation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.personnelEvaluation) > 200) {
			validResult.put("personnelEvaluation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.exceptionalCase) > 200) {
			validResult.put("exceptionalCase", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.photo) > 50) {
			validResult.put("photo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.wageCardNumber) > 50) {
			validResult.put("wageCardNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.bankName) > 50) {
			validResult.put("bankName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("no","emergencyContactName","emergencyContactTel","marry","status","physicalCondition","receiveWorkClothes","paySocialSecurity","accommodation","drive","licensePlateNum","source","rank","leadershipComments","universityGraduated","graduationTime","entryTime","correctionTime","departureTime","wageInformation","selfEvaluation","supervisorEvaluation","personnelEvaluation","exceptionalCase","receiveWorkClothesTime","photo","wageCardNumber","bankName","wages");

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
		if(map.containsKey("marry")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("marry",String.class))) {
				validResult.put("marry", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("receiveWorkClothes")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("receiveWorkClothes",String.class))) {
				validResult.put("receiveWorkClothes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("paySocialSecurity")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("paySocialSecurity",String.class))) {
				validResult.put("paySocialSecurity", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("accommodation")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("accommodation",String.class))) {
				validResult.put("accommodation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("drive")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("drive",String.class))) {
				validResult.put("drive", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("source")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("source",String.class))) {
				validResult.put("source", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("rank")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("rank",String.class))) {
				validResult.put("rank", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("graduationTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("graduationTime",String.class))) {
				validResult.put("graduationTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("entryTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("entryTime",String.class))) {
				validResult.put("entryTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("correctionTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("correctionTime",String.class))) {
				validResult.put("correctionTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("departureTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("departureTime",String.class))) {
				validResult.put("departureTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("receiveWorkClothesTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("receiveWorkClothesTime",String.class))) {
				validResult.put("receiveWorkClothesTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("no")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 50) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("emergencyContactName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("emergencyContactName",String.class)) > 50) {
				validResult.put("emergencyContactName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("emergencyContactTel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("emergencyContactTel",String.class)) > 50) {
				validResult.put("emergencyContactTel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("status")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("status",String.class)) > 50) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("physicalCondition")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("physicalCondition",String.class)) > 50) {
				validResult.put("physicalCondition", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("licensePlateNum")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("licensePlateNum",String.class)) > 50) {
				validResult.put("licensePlateNum", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("leadershipComments")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("leadershipComments",String.class)) > 200) {
				validResult.put("leadershipComments", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("universityGraduated")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("universityGraduated",String.class)) > 100) {
				validResult.put("universityGraduated", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("wageInformation")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("wageInformation",String.class)) > 200) {
				validResult.put("wageInformation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("selfEvaluation")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("selfEvaluation",String.class)) > 200) {
				validResult.put("selfEvaluation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("supervisorEvaluation")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("supervisorEvaluation",String.class)) > 200) {
				validResult.put("supervisorEvaluation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("personnelEvaluation")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("personnelEvaluation",String.class)) > 200) {
				validResult.put("personnelEvaluation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("exceptionalCase")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("exceptionalCase",String.class)) > 200) {
				validResult.put("exceptionalCase", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("photo")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("photo",String.class)) > 50) {
				validResult.put("photo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("wageCardNumber")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("wageCardNumber",String.class)) > 50) {
				validResult.put("wageCardNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("bankName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bankName",String.class)) > 50) {
				validResult.put("bankName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("wages")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("wages",String.class))) {
				validResult.put("wages", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
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

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public void setEmergencyContactName(String emergencyContactName){
		this.emergencyContactName=emergencyContactName;
	}

	public String getEmergencyContactName(){
		return emergencyContactName;
	}

	public String getEmergencyContactTel() {
		return emergencyContactTel;
	}

	public void setEmergencyContactTel(String emergencyContactTel) {
		this.emergencyContactTel = emergencyContactTel;
	}

	public void setMarry(Boolean marry){
		this.marry=marry;
	}

	public Boolean getMarry(){
		return marry;
	}

	public void setStatus(String status){
		this.status=status;
	}

	public String getStatus(){
		return status;
	}

	public void setPhysicalCondition(String physicalCondition){
		this.physicalCondition=physicalCondition;
	}

	public String getPhysicalCondition(){
		return physicalCondition;
	}

	public void setReceiveWorkClothes(Boolean receiveWorkClothes){
		this.receiveWorkClothes=receiveWorkClothes;
	}

	public Boolean getReceiveWorkClothes(){
		return receiveWorkClothes;
	}

	public void setPaySocialSecurity(Boolean paySocialSecurity){
		this.paySocialSecurity=paySocialSecurity;
	}

	public Boolean getPaySocialSecurity(){
		return paySocialSecurity;
	}

	public void setAccommodation(Boolean accommodation){
		this.accommodation=accommodation;
	}

	public Boolean getAccommodation(){
		return accommodation;
	}

	public void setDrive(Boolean drive){
		this.drive=drive;
	}

	public Boolean getDrive(){
		return drive;
	}

	public void setLicensePlateNum(String licensePlateNum){
		this.licensePlateNum=licensePlateNum;
	}

	public String getLicensePlateNum(){
		return licensePlateNum;
	}

	public void setSource(Integer source){
		this.source=source;
	}

	public Integer getSource(){
		return source;
	}

	public void setRank(Integer rank){
		this.rank=rank;
	}

	public Integer getRank(){
		return rank;
	}

	public void setLeadershipComments(String leadershipComments){
		this.leadershipComments=leadershipComments;
	}

	public String getLeadershipComments(){
		return leadershipComments;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setUniversityGraduated(String universityGraduated){
		this.universityGraduated=universityGraduated;
	}

	public String getUniversityGraduated(){
		return universityGraduated;
	}

	public void setGraduationTime(Date graduationTime){
		this.graduationTime=graduationTime;
	}

	public Date getGraduationTime(){
		return graduationTime;
	}

	public void setEntryTime(Date entryTime){
		this.entryTime=entryTime;
	}

	public Date getEntryTime(){
		return entryTime;
	}

	public void setCorrectionTime(Date correctionTime){
		this.correctionTime=correctionTime;
	}

	public Date getCorrectionTime(){
		return correctionTime;
	}

	public void setDepartureTime(Date departureTime){
		this.departureTime=departureTime;
	}

	public Date getDepartureTime(){
		return departureTime;
	}

	public void setWageInformation(String wageInformation){
		this.wageInformation=wageInformation;
	}

	public String getWageInformation(){
		return wageInformation;
	}

	public void setSelfEvaluation(String selfEvaluation){
		this.selfEvaluation=selfEvaluation;
	}

	public String getSelfEvaluation(){
		return selfEvaluation;
	}

	public void setSupervisorEvaluation(String supervisorEvaluation){
		this.supervisorEvaluation=supervisorEvaluation;
	}

	public String getSupervisorEvaluation(){
		return supervisorEvaluation;
	}

	public void setPersonnelEvaluation(String personnelEvaluation){
		this.personnelEvaluation=personnelEvaluation;
	}

	public String getPersonnelEvaluation(){
		return personnelEvaluation;
	}

	public void setExceptionalCase(String exceptionalCase){
		this.exceptionalCase=exceptionalCase;
	}

	public String getExceptionalCase(){
		return exceptionalCase;
	}

	public void setReceiveWorkClothesTime(Date receiveWorkClothesTime){
		this.receiveWorkClothesTime=receiveWorkClothesTime;
	}

	public Date getReceiveWorkClothesTime(){
		return receiveWorkClothesTime;
	}

	public void setPhoto(String photo){
		this.photo=photo;
	}

	public String getPhoto(){
		return photo;
	}

	public void setWageCardNumber(String wageCardNumber){
		this.wageCardNumber=wageCardNumber;
	}

	public String getWageCardNumber(){
		return wageCardNumber;
	}

	public void setBankName(String bankName){
		this.bankName=bankName;
	}

	public String getBankName(){
		return bankName;
	}

	public BigDecimal getWages() {
		return wages;
	}

	public void setWages(BigDecimal wages) {
		this.wages = wages;
	}
}
