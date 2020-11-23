package com.lwxf.industry4.webapp.domain.entity.corporatePartners;
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
 * 功能：corporate_partners 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-08-01 02:05 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "corporate_partners",displayName = "corporate_partners")
@ApiModel(value = "外协厂家信息",description = "外协厂家信息")
public class CorporatePartners extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 80,name = "name",displayName = "外协厂家名称")
	@ApiModelProperty(value = "外协厂家名称")
	private String name;
	@Column(type = Types.VARCHAR,length = 80,name = "bank_account",displayName = "银行账户")
	@ApiModelProperty(value = "银行账户")
	private String bankAccount;
	@Column(type = Types.VARCHAR,length = 80,name = "bank",displayName = "开户行")
	@ApiModelProperty(value = "开户行")
	private String bank;
	@Column(type = Types.VARCHAR,length = 100,name = "address",displayName = "详细地址")
	@ApiModelProperty(value = "详细地址")
	private String address;
	@Column(type = Types.VARCHAR,length = 30,name = "tel",displayName = "电话")
	@ApiModelProperty(value = "电话")
	private String tel;
	@Column(type = Types.VARCHAR,length = 20,name = "contact_name",displayName = "联系人名称")
	@ApiModelProperty(value = "联系人名称")
	private String contactName;
	@Column(type = Types.CHAR,length = 13,name = "city_area",displayName = "所在城市")
	@ApiModelProperty(value = "所在城市")
	private String cityArea;
	@Column(type = Types.VARCHAR,length = 255,name = "notes",displayName = "备注")
	@ApiModelProperty(value = "备注")
	private String notes;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "")
	@ApiModelProperty(value = "创建人Id")
	private String creator;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "")
	@ApiModelProperty(value = "企业ID")
	private String branchId;

    public CorporatePartners() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.name) > 80) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.bankAccount) > 80) {
			validResult.put("bankAccount", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.bank) > 80) {
			validResult.put("bank", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.address) > 100) {
			validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.tel) > 30) {
			validResult.put("tel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.contactName) > 20) {
			validResult.put("contactName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.cityArea) > 13) {
			validResult.put("cityArea", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 255) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.creator) > 13) {
			validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	private final static List<String> propertiesList = Arrays.asList("name","bankAccount","bank","address","tel","contactName","cityArea","notes","created","creator","branchId");

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
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 80) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("bankAccount")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bankAccount",String.class)) > 80) {
				validResult.put("bankAccount", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("bank")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bank",String.class)) > 80) {
				validResult.put("bank", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 100) {
				validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("tel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("tel",String.class)) > 30) {
				validResult.put("tel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("contactName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("contactName",String.class)) > 20) {
				validResult.put("contactName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("cityArea")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cityArea",String.class)) > 13) {
				validResult.put("cityArea", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 255) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("creator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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


	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setBankAccount(String bankAccount){
		this.bankAccount=bankAccount;
	}

	public String getBankAccount(){
		return bankAccount;
	}

	public void setBank(String bank){
		this.bank=bank;
	}

	public String getBank(){
		return bank;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setTel(String tel){
		this.tel=tel;
	}

	public String getTel(){
		return tel;
	}

	public void setContactName(String contactName){
		this.contactName=contactName;
	}

	public String getContactName(){
		return contactName;
	}

	public void setCityArea(String cityArea){
		this.cityArea=cityArea;
	}

	public String getCityArea(){
		return cityArea;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setBranchId(String branchId){
		this.branchId=branchId;
	}

	public String getBranchId(){
		return branchId;
	}
}
