package com.lwxf.industry4.webapp.domain.entity.supplier;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.*;

/**
 * 功能：supplier 实体类
 *
 * @author：zhangxiaolin 3965488@qq.com
 * @created：2019-06-12 03:27 
 * @version：2019 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "supplier",displayName = "supplier")
@ApiModel(value = "供应商信息",discriminator = "供应商信息")
public class Supplier extends IdEntity {
	@Column(type = Types.VARCHAR,length = 30,name = "supplier_code",displayName = "供应商编码")
	@ApiModelProperty(value = "供应商编码",example="001")
	private String supplierCode;
	@Column(type = Types.VARCHAR,length = 100,name = "supplier_name",displayName = "供应商名称")
	@ApiModelProperty(value = "供应商名称",example="测试名称")
	private String supplierName;
	@Column(type = Types.TINYINT,name = "supplier_level",displayName = "供应商级别")
	@ApiModelProperty(value = "供应商级别",example="0")
	private Byte supplierLevel;
	@Column(type = TypesExtend.DATETIME,name = "sign_time",displayName = "供应商签约时间")
	@ApiModelProperty(value = "供应商级别",example="2019-01-01")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	private Date signTime;
	@Column(type = TypesExtend.DATETIME,name = "end_sign_time",displayName = "供应商结束签约时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@ApiModelProperty(value = "供应商结束签约时间",example="2019-01-01")
	private Date endSignTime;
	@Column(type = Types.CHAR,length = 20,name = "contacts",displayName = "负责人")
	@ApiModelProperty(value = "负责人",example="张潇霖")
	private String contacts;
	@Column(type = Types.CHAR,length = 20,name = "position",displayName = "职位")
	@ApiModelProperty(value = "职位",example="总经理")
	private String position;
	@Column(type = Types.VARCHAR,length = 100,name = "supplier_phone",displayName = "联系电话")
	@ApiModelProperty(value = "联系电话",example="13523007337")
	private String supplierPhone;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "create_user",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,name = "update_time",displayName = "修改时间")
	private Date updateTime;
	@Column(type = Types.CHAR,length = 13,name = "update_user",displayName = "修改人")
	private String updateUser;
	@Column(type = Types.CHAR,length = 13,name = "area",displayName = "省市区地区id")
	@ApiModelProperty(value = "省市区地区id",example="110100")
	private String area;
	@ApiModelProperty(value="经度",name="lng",example="1")
	@Column(type = Types.FLOAT,name = "lng",displayName = "经度")
	private Float lng;
	@ApiModelProperty(value="纬度",name="lat",example="1")
	@Column(type = Types.FLOAT,name = "lat",displayName = "维度")
	private Float lat;
	@Column(type = Types.VARCHAR,length = 2048,name = "address",displayName = "详细地址")
	@ApiModelProperty(value = "详细地址",example="顺河路马李庄")
	private String address;
	@Column(type = Types.TINYINT,name = "supplier_stage",displayName = "供应商类型,0：意向，1：签约")
	@ApiModelProperty(value = "状态",example="1")
	private Integer supplierStage;
	@Column(type = Types.TINYINT,name = "status",displayName = "供应商状态,0：禁用，1：正常")
	@ApiModelProperty(value = "状态",example="1")
	private Integer status;
	@Column(type = Types.CHAR,length = 20,name = "factory_contact",displayName = "工厂负责人")
	@ApiModelProperty(value = "工厂负责人",example="4v14mj3ampdw")
	private String factoryContact;
	@Column(type = Types.VARCHAR,length = 1024,name = "remark",displayName = "备注")
	@ApiModelProperty(value = "备注",example="测试")
	private String remark;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	@ApiModelProperty(value = "企业id",example="40ord3va6adp")
	private String branchId;
	@Column(type = Types.CHAR,length = 13,name = "category_id",displayName = "类别ID(对应basecode  supply_type)")
	@ApiModelProperty(value = "工厂类别",example="4zhykobl651c")
	private String categoryId;
	private String fileIds;

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public Supplier() {
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();

		if (LwxfStringUtils.getStringLength(this.supplierCode) > 30) {
			validResult.put("supplierCode", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.supplierName) > 100) {
			validResult.put("supplierName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.contacts) > 13) {
			validResult.put("contacts", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.supplierPhone) > 100) {
			validResult.put("supplierPhone", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.updateUser) > 13) {
			validResult.put("updateUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.area) > 13) {
			validResult.put("area", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.address) > 2048) {
			validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.factoryContact) > 13) {
			validResult.put("factoryContact", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.remark) > 1024) {
			validResult.put("remark", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
			validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.categoryId) > 13) {
			validResult.put("categoryId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("supplierId","supplierCode","supplierName","supplierLevel","signTime","endSignTime","contacts","supplierPhone","createTime","createUser","updateTime","updateUser","area","address","supplierStage","factoryContact","remark","branchId","categoryId");

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
		if(map.containsKey("supplierLevel")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("supplierLevel",String.class))) {
				validResult.put("supplierLevel", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("signTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("signTime",String.class))) {
				validResult.put("signTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("endSignTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("endSignTime",String.class))) {
				validResult.put("endSignTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("createTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("createTime",String.class))) {
				validResult.put("createTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("updateTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updateTime",String.class))) {
				validResult.put("updateTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("supplierStage")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("supplierStage",String.class))) {
				validResult.put("supplierStage", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("supplierId")) {
			if (map.getTypedValue("supplierId",String.class)  == null) {
				validResult.put("supplierId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("supplierId",String.class)) > 13) {
					validResult.put("supplierId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("supplierCode")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("supplierCode",String.class)) > 30) {
				validResult.put("supplierCode", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("supplierName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("supplierName",String.class)) > 100) {
				validResult.put("supplierName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("contacts")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("contacts",String.class)) > 13) {
				validResult.put("contacts", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("supplierPhone")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("supplierPhone",String.class)) > 100) {
				validResult.put("supplierPhone", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("createUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("createUser",String.class)) > 13) {
				validResult.put("createUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("updateUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("updateUser",String.class)) > 13) {
				validResult.put("updateUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("area")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("area",String.class)) > 13) {
				validResult.put("area", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 2048) {
				validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("factoryContact")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("factoryContact",String.class)) > 13) {
				validResult.put("factoryContact", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("remark")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("remark",String.class)) > 1024) {
				validResult.put("remark", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("branchId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("branchId",String.class)) > 13) {
				validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("categoryId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("categoryId",String.class)) > 13) {
				validResult.put("categoryId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Float getLng() {return lng;}

	public void setLng(Float lng) {this.lng = lng;}

	public Float getLat() {return lat;}

	public void setLat(Float lat) {this.lat = lat;}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public void setSupplierCode(String supplierCode){
		this.supplierCode=supplierCode;
	}

	public String getSupplierCode(){
		return supplierCode;
	}

	public void setSupplierName(String supplierName){
		this.supplierName=supplierName;
	}

	public String getSupplierName(){
		return supplierName;
	}

	public void setSupplierLevel(Byte supplierLevel){
		this.supplierLevel=supplierLevel;
	}

	public Byte getSupplierLevel(){
		return supplierLevel;
	}

	public void setSignTime(Date signTime){
		this.signTime=signTime;
	}

	public Date getSignTime(){
		return signTime;
	}

	public void setEndSignTime(Date endSignTime){
		this.endSignTime=endSignTime;
	}

	public Date getEndSignTime(){
		return endSignTime;
	}

	public void setContacts(String contacts){
		this.contacts=contacts;
	}

	public String getContacts(){
		return contacts;
	}

	public void setSupplierPhone(String supplierPhone){
		this.supplierPhone=supplierPhone;
	}

	public String getSupplierPhone(){
		return supplierPhone;
	}

	public Date getCreated() {return created;}

	public void setCreated(Date created) {this.created = created;}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}

	public String getUpdateUser(){
		return updateUser;
	}

	public void setArea(String area){
		this.area=area;
	}

	public String getArea(){
		return area;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setSupplierStage(Integer supplierStage){
		this.supplierStage=supplierStage;
	}

	public Integer getSupplierStage(){
		return supplierStage;
	}

	public void setFactoryContact(String factoryContact){
		this.factoryContact=factoryContact;
	}

	public String getFactoryContact(){
		return factoryContact;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

	public void setBranchId(String branchId){
		this.branchId=branchId;
	}

	public String getBranchId(){
		return branchId;
	}

	public void setCategoryId(String categoryId){
		this.categoryId=categoryId;
	}

	public String getCategoryId(){
		return categoryId;
	}
}
