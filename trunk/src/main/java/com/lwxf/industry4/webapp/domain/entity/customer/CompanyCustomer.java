package com.lwxf.industry4.webapp.domain.entity.customer;
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
 * 功能：company_customer 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-01-03 01:26 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "company_customer",displayName = "company_customer")
@ApiModel(value = "客户信息",description = "客户信息")
public class CompanyCustomer extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "")
	@ApiModelProperty(value = "公司ID")
	private String companyId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "user_id",displayName = "")
	@ApiModelProperty(value = "用户ID")
	private String userId;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 新建；1 - 跟进中；2 - 已下单；3 - 已流逝（已在其他商家下订单）")
	@ApiModelProperty(value = "状态")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "customer_manager",displayName = "客户经理，默认为创建人，后期可能根据经销商业务调整变更业务经理")
	@ApiModelProperty(value = "客户经理")
	private String customerManager;
	@Column(type = Types.TINYINT,nullable = false,name = "grade",displayName = "客户等级：0 - 普通客户（低消费阶层）；1 - 中等客户（中等消费阶层）；2 - 高端客户（高端消费阶层）；3 - 特等客户（视钱如粪土）； ")
	@ApiModelProperty(value = "客户等级")
	private Integer grade;
	@Column(type = Types.TINYINT,nullable = false,name = "source",displayName = "客户来源：0-C端注册，1-电话访问，2-上门拜访，3-公众号，4-网络，5-电商（天猫，京东）")
	@ApiModelProperty(value = "客户来源")
	private Integer source;
	@Column(type = Types.VARCHAR,length = 50,name = "community",displayName = "小区名称")
	@ApiModelProperty(value = "小区名称")
	private String community;
	@Column(type = Types.CHAR,length = 13,name = "city_area_id",displayName = "地区Id")
	@ApiModelProperty(value = "地区Id")
	private String cityAreaId;
	@Column(type = Types.VARCHAR,length = 200,name = "address",displayName = "客户详细地址")
	@ApiModelProperty(value = "客户详细地址")
	private String address;
	@Column(type = Types.VARCHAR,length = 500,name = "remarks",displayName = "备注信息")
	@ApiModelProperty(value = "备注信息")
	private String remarks;
	@Column(type = Types.VARCHAR,length = 100,name = "name",displayName = "客户姓名")
	@ApiModelProperty(value = "客户姓名")
	private String name;
	@Column(type = Types.VARCHAR,length = 30,name = "phone",displayName = "客户电话")
	@ApiModelProperty(value = "客户电话")
	private String phone;
	@Column(type = Types.VARCHAR,length = 2,name = "sex",displayName = "客户性别")
	@ApiModelProperty(value = "客户性别")
	private Integer sex;

    public CompanyCustomer() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.userId == null) {
			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.creator == null) {
			validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
 		if (LwxfStringUtils.getStringLength(this.customerManager) > 13) {
			validResult.put("customerManager", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.grade == null) {
			validResult.put("grade", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.source == null) {
			validResult.put("source", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.community) > 50) {
			validResult.put("community", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
			validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.address) > 200) {
			validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.remarks) > 500) {
			validResult.put("remarks", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(this.name==null){
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 100) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","companyId","userId","status","creator","created","customerManager","grade","source","community","cityAreaId","address","remarks","name","phone","sex");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("grade")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("grade",String.class))) {
				validResult.put("grade", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("source")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("source",String.class))) {
				validResult.put("source", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 100) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("companyId")) {
			if (map.getTypedValue("companyId",String.class)  == null) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId",String.class)) > 13) {
					validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("userId")) {
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("userId",String.class)) > 13) {
					validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("creator")) {
			if (map.getTypedValue("creator",String.class)  == null) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
					validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("customerManager")) {
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("customerManager",String.class)) > 13) {
					validResult.put("customerManager", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
		}
		if(map.containsKey("grade")) {
			if (map.get("grade")  == null) {
				validResult.put("grade", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("source")) {
			if (map.get("source")  == null) {
				validResult.put("source", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("community")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("community",String.class)) > 50) {
				validResult.put("community", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("cityAreaId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cityAreaId",String.class)) > 13) {
				validResult.put("cityAreaId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 200) {
				validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("remarks")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("remarks",String.class)) > 500) {
				validResult.put("remarks", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setCustomerManager(String customerManager){
		this.customerManager=customerManager;
	}

	public String getCustomerManager(){
		return customerManager;
	}

	public void setGrade(Integer grade){
		this.grade=grade;
	}

	public Integer getGrade(){
		return grade;
	}

	public void setSource(Integer source){
		this.source=source;
	}

	public Integer getSource(){
		return source;
	}

	public void setCommunity(String community){
		this.community=community;
	}

	public String getCommunity(){
		return community;
	}

	public void setCityAreaId(String cityAreaId){
		this.cityAreaId=cityAreaId;
	}

	public String getCityAreaId(){
		return cityAreaId;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}
}
