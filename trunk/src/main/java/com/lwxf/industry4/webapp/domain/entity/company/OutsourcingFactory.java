package com.lwxf.industry4.webapp.domain.entity.company;
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
 * 功能：outsourcing_factory 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-06-01 04:59 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "outsourcing_factory",displayName = "outsourcing_factory")
public class OutsourcingFactory extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 200,name = "name",displayName = "外协厂家名称")
	private String name;
	@Column(type = Types.CHAR,length = 13,name = "city_area_id",displayName = "城市ID")
	private String cityAreaId;
	@Column(type = Types.VARCHAR,length = 200,name = "address",displayName = "详细地址")
	private String address;
	@Column(type = Types.VARCHAR,length = 200,name = "leader_tel",displayName = "联系人电话")
	private String leaderTel;
	@Column(type = Types.VARCHAR,length = 50,name = "leader_name",displayName = "联系人")
	private String leaderName;
	@Column(type = Types.VARCHAR,length = 50,name = "bank",displayName = "卡户行")
	private String bank;
	@Column(type = Types.VARCHAR,length = 50,name = "bank_account",displayName = "银行账号")
	private String bankAccount;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "备注")
	private String notes;
	@Column(type = Types.INTEGER,name = "state",displayName = "状态 0:正常,1:禁用")
	private Integer state;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,updatable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "updator",displayName = "修改人")
	private String updator;
	@Column(type = TypesExtend.DATETIME,name = "updated",displayName = "修改时间")
	private Date updated;
	@Column(type = TypesExtend.DATETIME,name = "signing_time",displayName = "签约时间")
	private Date signingTime;
	@Column(type = Types.CHAR,length = 13,name = "factory_leader",displayName = "厂家负责人")
	private String factoryLeader;

    public OutsourcingFactory() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.name) > 200) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
			validResult.put("cityAreaId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.address) > 200) {
			validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.leaderTel) > 200) {
			validResult.put("leaderTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.leaderName) > 50) {
			validResult.put("leaderName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.bank) > 50) {
			validResult.put("bank", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.bankAccount) > 50) {
			validResult.put("bankAccount", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.creator) > 13) {
			validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.updator) > 13) {
			validResult.put("updator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.factoryLeader) > 13) {
			validResult.put("factoryLeader", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","cityAreaId","address","leaderTel","leaderName","bank","bankAccount","notes","state","updator","updated","signingTime","factoryLeader");

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
		if(map.containsKey("state")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("state",String.class))) {
				validResult.put("state", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("updated")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updated",String.class))) {
				validResult.put("updated", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("signingTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("signingTime",String.class))) {
				validResult.put("signingTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 200) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("cityAreaId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cityAreaId",String.class)) > 13) {
				validResult.put("cityAreaId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 200) {
				validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("leaderTel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("leaderTel",String.class)) > 200) {
				validResult.put("leaderTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("leaderName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("leaderName",String.class)) > 50) {
				validResult.put("leaderName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("bank")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bank",String.class)) > 50) {
				validResult.put("bank", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("bankAccount")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bankAccount",String.class)) > 50) {
				validResult.put("bankAccount", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("updator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("updator",String.class)) > 13) {
				validResult.put("updator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("factoryLeader")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("factoryLeader",String.class)) > 13) {
				validResult.put("factoryLeader", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public void setLeaderTel(String leaderTel){
		this.leaderTel=leaderTel;
	}

	public String getLeaderTel(){
		return leaderTel;
	}

	public void setLeaderName(String leaderName){
		this.leaderName=leaderName;
	}

	public String getLeaderName(){
		return leaderName;
	}

	public void setBank(String bank){
		this.bank=bank;
	}

	public String getBank(){
		return bank;
	}

	public void setBankAccount(String bankAccount){
		this.bankAccount=bankAccount;
	}

	public String getBankAccount(){
		return bankAccount;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setState(Integer state){
		this.state=state;
	}

	public Integer getState(){
		return state;
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

	public void setUpdator(String updator){
		this.updator=updator;
	}

	public String getUpdator(){
		return updator;
	}

	public void setUpdated(Date updated){
		this.updated=updated;
	}

	public Date getUpdated(){
		return updated;
	}

	public void setSigningTime(Date signingTime){
		this.signingTime=signingTime;
	}

	public Date getSigningTime(){
		return signingTime;
	}

	public void setFactoryLeader(String factoryLeader){
		this.factoryLeader=factoryLeader;
	}

	public String getFactoryLeader(){
		return factoryLeader;
	}
}
