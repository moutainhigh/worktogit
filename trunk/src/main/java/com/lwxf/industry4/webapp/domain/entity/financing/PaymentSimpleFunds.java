package com.lwxf.industry4.webapp.domain.entity.financing;
import java.math.BigDecimal;
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
 * 功能：payment_simple_funds 实体类
 *
 * @author：djl(yuuyoo@163.com)
 * @created：2019-07-23 09:58 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "payment_simple_funds",displayName = "payment_simple_funds")
public class PaymentSimpleFunds extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "payment_simple_id",displayName = "主表id")
	private String paymentSimpleId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "funds_name",displayName = "科目名称")
	private String fundsName;
	@Column(type = Types.VARCHAR,length = 20,name = "code",displayName = "编码")
	private String code;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "amount",displayName = "金额")
	private BigDecimal amount;
	@Column(type = Types.VARCHAR,length = 255,name = "notes",displayName = "备注")
	private String notes;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = Types.CHAR,length = 13,name = "operator",displayName = "操作人")
	private String operator;
	@Column(type = Types.CHAR,length = 13,name = "dept_id",displayName = "部门id")
	private String deptId;

    public PaymentSimpleFunds() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.paymentSimpleId == null) {
			validResult.put("paymentSimpleId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.paymentSimpleId) > 13) {
				validResult.put("paymentSimpleId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.fundsName == null) {
			validResult.put("fundsName", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.fundsName) > 50) {
				validResult.put("fundsName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.code) > 20) {
			validResult.put("code", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 255) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.creator) > 13) {
			validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.operator) > 13) {
			validResult.put("operator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","paymentSimpleId","fundsName","code","amount","notes","created","creator","operator","deptId");

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
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount",String.class))) {
				validResult.put("amount", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("paymentSimpleId")) {
			if (map.getTypedValue("paymentSimpleId",String.class)  == null) {
				validResult.put("paymentSimpleId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("paymentSimpleId",String.class)) > 13) {
					validResult.put("paymentSimpleId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("fundsName")) {
			if (map.getTypedValue("fundsName",String.class)  == null) {
				validResult.put("fundsName", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("fundsName",String.class)) > 50) {
					validResult.put("fundsName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("code")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("code",String.class)) > 20) {
				validResult.put("code", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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
		if(map.containsKey("operator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("operator",String.class)) > 13) {
				validResult.put("operator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("deptId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("deptId",String.class)) > 13) {
				validResult.put("deptId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setPaymentSimpleId(String paymentSimpleId){
		this.paymentSimpleId=paymentSimpleId;
	}

	public String getPaymentSimpleId(){
		return paymentSimpleId;
	}

	public void setFundsName(String fundsName){
		this.fundsName=fundsName;
	}

	public String getFundsName(){
		return fundsName;
	}

	public void setCode(String code){
		this.code=code;
	}

	public String getCode(){
		return code;
	}

	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}

	public BigDecimal getAmount(){
		return amount;
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

	public void setOperator(String operator){
		this.operator=operator;
	}

	public String getOperator(){
		return operator;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}
}
