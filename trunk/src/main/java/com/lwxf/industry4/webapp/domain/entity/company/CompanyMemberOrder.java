package com.lwxf.industry4.webapp.domain.entity.company;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：company_member_order 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-03-24 02:39 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="共享成员和订单关联表", description="共享成员和订单关联表")
@Table(name = "company_member_order",displayName = "company_member_order")
public class CompanyMemberOrder extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "custome_order_id",displayName = "订单id")
	@ApiModelProperty(value = "订单id")
	private String customeOrderId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_share_member_id",displayName = "公司成员表id")
	@ApiModelProperty(value = "公司成员表id")
	private String companyShareMemberId;
	@Column(type = Types.TINYINT,name = "identity",displayName = "身份(0：家具顾问；1：设计师；2：安装工)")
	private Integer identity;

    public CompanyMemberOrder() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.customeOrderId == null) {
			validResult.put("customeOrderId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.customeOrderId) > 13) {
				validResult.put("customeOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.companyShareMemberId == null) {
			validResult.put("companyShareMemberId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.companyShareMemberId) > 13) {
				validResult.put("companyShareMemberId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","customeOrderId","companyShareMemberId");

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
		if(map.containsKey("customeOrderId")) {
			if (map.getTypedValue("customeOrderId",String.class)  == null) {
				validResult.put("customeOrderId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("customeOrderId",String.class)) > 13) {
					validResult.put("customeOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("companyShareMemberId")) {
			if (map.getTypedValue("companyShareMemberId",String.class)  == null) {
				validResult.put("companyShareMemberId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("companyShareMemberId",String.class)) > 13) {
					validResult.put("companyShareMemberId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCustomeOrderId(String customeOrderId){
		this.customeOrderId=customeOrderId;
	}

	public String getCustomeOrderId(){
		return customeOrderId;
	}

	public void setCompanyShareMemberId(String companyShareMemberId){
		this.companyShareMemberId=companyShareMemberId;
	}

	public String getCompanyShareMemberId(){
		return companyShareMemberId;
	}

	public Integer getIdentity() {
		return identity;
	}

	public void setIdentity(Integer identity) {
		this.identity = identity;
	}
}
