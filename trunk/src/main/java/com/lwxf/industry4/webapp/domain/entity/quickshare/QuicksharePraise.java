package com.lwxf.industry4.webapp.domain.entity.quickshare;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.base.AbstractNoIdEntity;
import com.lwxf.mybatis.annotation.PrimaryConstraint;
import com.lwxf.mybatis.utils.TypesExtend;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
/**
 * 功能：quickshare_praise 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-02 01:16 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "quickshare_praise", primaryConstraints = @PrimaryConstraint(fieldNames = {"memberId","quickshareId"}), displayName = "quickshare_praise")
public class QuicksharePraise extends AbstractNoIdEntity {
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "quickshare_id",displayName = "帖子Id，关联消息帖子表")
	private String quickshareId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "member_id",displayName = "会员Id")
	private String memberId;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;

    public QuicksharePraise() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.quickshareId == null) {
			validResult.put("quickshareId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.quickshareId) > 13) {
				validResult.put("quickshareId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.memberId == null) {
			validResult.put("memberId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.memberId) > 13) {
				validResult.put("memberId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.created == null) {
			validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("quickshareId","memberId","created");

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
		if(map.containsKey("quickshareId")) {
			if (map.getTypedValue("quickshareId",String.class)  == null) {
				validResult.put("quickshareId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("quickshareId",String.class)) > 13) {
					validResult.put("quickshareId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("memberId")) {
			if (map.getTypedValue("memberId",String.class)  == null) {
				validResult.put("memberId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("memberId",String.class)) > 13) {
					validResult.put("memberId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setQuickshareId(String quickshareId){
		this.quickshareId=quickshareId;
	}

	public String getQuickshareId(){
		return quickshareId;
	}

	public void setMemberId(String memberId){
		this.memberId=memberId;
	}

	public String getMemberId(){
		return memberId;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}
}
