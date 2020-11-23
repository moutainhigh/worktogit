package com.lwxf.industry4.webapp.domain.entity.financing;
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
 * 功能：payment_list 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-11-04 05:32 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "payment_list",displayName = "payment_list")
public class PaymentList extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,name = "payment_id",displayName = "")
	private String paymentId;
	@Column(type = Types.CHAR,length = 13,name = "company_id",displayName = "")
	private String companyId;
	@Column(type = Types.CHAR,length = 13,name = "custom_order_id",displayName = "")
	private String customOrderId;
	@Column(type = Types.FLOAT,name = "amount",displayName = "")
	private Float amount;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "")
	private String creator;

    public PaymentList() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.paymentId) > 13) {
			validResult.put("paymentId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
			validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
			validResult.put("customOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.creator) > 13) {
			validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","paymentId","companyId","customOrderId","amount","created","creator");

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
		if(map.containsKey("amount")) {
			if (!DataValidatorUtils.isFloat(map.getTypedValue("amount",String.class))) {
				validResult.put("amount", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("paymentId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("paymentId",String.class)) > 13) {
				validResult.put("paymentId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("companyId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId",String.class)) > 13) {
				validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("customOrderId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("customOrderId",String.class)) > 13) {
				validResult.put("customOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("creator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setPaymentId(String paymentId){
		this.paymentId=paymentId;
	}

	public String getPaymentId(){
		return paymentId;
	}

	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setAmount(Float amount){
		this.amount=amount;
	}

	public Float getAmount(){
		return amount;
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
}
