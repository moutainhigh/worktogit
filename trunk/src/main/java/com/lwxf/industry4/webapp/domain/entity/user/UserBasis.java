package com.lwxf.industry4.webapp.domain.entity.user;
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
 * 功能：user_basis 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-22 10:15
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "user_basis",displayName = "user_basis")
@ApiModel(value = "用户基础信息",description = "用户基础信息")
public class UserBasis extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "user_id",displayName = "用户主键ID")
	@ApiModelProperty(value = "用户主键ID")
	private String userId;
	@Column(type = Types.TINYINT,name = "political_outlook",displayName = "政治面貌  0 Communist_Party_members(中共党员),1 Communist Youth League member(共青团员),2 Masses(群众)")
	@ApiModelProperty(value = "政治面貌  0 Communist_Party_members(中共党员),1 Communist Youth League member(共青团员),2 Masses(群众)")
	private Integer politicalOutlook;
	@Column(type = Types.VARCHAR,length = 100,name = "address",displayName = "家庭地址")
	@ApiModelProperty(value = "家庭地址")
	private String address;
	@Column(type = Types.VARCHAR,length = 100,name = "work_unit",displayName = "工作单位")
	@ApiModelProperty(value = "工作单位")
	private String workUnit;
	@Column(type = Types.VARCHAR,length = 100,name = "work",displayName = "职位")
	@ApiModelProperty(value = "职位")
	private String work;
	@Column(type = Types.TINYINT,name = "income",displayName = "收入 0 one(2000~4000),1 two(4000~6000),2 three(6000~8000),3 four(8000~10000)")
	@ApiModelProperty(value = "收入 0 one(2000~4000),1 two(4000~6000),2 three(6000~8000),3 four(8000~10000)")
	private Integer income;
	@Column(type = Types.TINYINT,name = "education",displayName = "学历 0-master(硕士),1-Undergraduate(本科),2-Junior_College(大专),3-(high_school)高中,4-school(中专),5-Junior_middle_school(初中)")
	@ApiModelProperty(value = "学历 0-master(硕士),1-Undergraduate(本科),2-Junior_College(大专),3-(high_school)高中,4-school(中专),5-Junior_middle_school(初中)")
	private Integer education;
	@Column(type = Types.VARCHAR,length = 50,name = "nation",displayName = "民族")
	@ApiModelProperty(value = "民族")
	private String nation;
	@Column(type = Types.VARCHAR,length = 18,name = "identity_number",displayName = "身份证号")
	@ApiModelProperty(value = "身份证号")
	private String identityNumber;
	@Column(type = Types.VARCHAR,length = 15,name = "qq_number",displayName = "QQ号")
	@ApiModelProperty(value = "QQ号")
	private String qqNumber;
	@Column(type = Types.VARCHAR,length = 50,name = "wechat_number",displayName = "微信号")
	@ApiModelProperty(value = "微信号")
	private String wechatNumber;
	@Column(type = Types.VARCHAR,length = 20,name = "contact_number",displayName = "联系电话")
	@ApiModelProperty(value = "联系电话")
	private String contactNumber;

	public UserBasis() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.userId == null) {
			validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.address) > 100) {
			validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.workUnit) > 100) {
			validResult.put("workUnit", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.work) > 100) {
			validResult.put("work", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.nation) > 50) {
			validResult.put("nation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.identityNumber) > 18) {
			validResult.put("identityNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.qqNumber) > 15) {
			validResult.put("qqNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.wechatNumber) > 50) {
			validResult.put("wechatNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.contactNumber) > 20) {
			validResult.put("contactNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("politicalOutlook","address","workUnit","work","income","education","nation","identityNumber","qqNumber","wechatNumber","contactNumber");

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
		if(map.containsKey("politicalOutlook")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("politicalOutlook",String.class))) {
				validResult.put("politicalOutlook", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("income")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("income",String.class))) {
				validResult.put("income", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("education")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("education",String.class))) {
				validResult.put("education", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 100) {
				validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("workUnit")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("workUnit",String.class)) > 100) {
				validResult.put("workUnit", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("work")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("work",String.class)) > 100) {
				validResult.put("work", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("nation")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("nation",String.class)) > 50) {
				validResult.put("nation", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("identityNumber")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("identityNumber",String.class)) != 18) {
				validResult.put("identityNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("qqNumber")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("qqNumber",String.class)) > 15) {
				validResult.put("qqNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("wechatNumber")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("wechatNumber",String.class)) > 50) {
				validResult.put("wechatNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("contactNumber")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("contactNumber",String.class)) > 20) {
				validResult.put("contactNumber", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setPoliticalOutlook(Integer politicalOutlook){
		this.politicalOutlook=politicalOutlook;
	}

	public Integer getPoliticalOutlook(){
		return politicalOutlook;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setWorkUnit(String workUnit){
		this.workUnit=workUnit;
	}

	public String getWorkUnit(){
		return workUnit;
	}

	public void setWork(String work){
		this.work=work;
	}

	public String getWork(){
		return work;
	}

	public void setIncome(Integer income){
		this.income=income;
	}

	public Integer getIncome(){
		return income;
	}

	public void setEducation(Integer education){
		this.education=education;
	}

	public Integer getEducation(){
		return education;
	}

	public void setNation(String nation){
		this.nation=nation;
	}

	public String getNation(){
		return nation;
	}

	public void setIdentityNumber(String identityNumber){
		this.identityNumber=identityNumber;
	}

	public String getIdentityNumber(){
		return identityNumber;
	}

	public void setQqNumber(String qqNumber){
		this.qqNumber=qqNumber;
	}

	public String getQqNumber(){
		return qqNumber;
	}

	public void setWechatNumber(String wechatNumber){
		this.wechatNumber=wechatNumber;
	}

	public String getWechatNumber(){
		return wechatNumber;
	}

	public void setContactNumber(String contactNumber){
		this.contactNumber=contactNumber;
	}

	public String getContactNumber(){
		return contactNumber;
	}
}
