package com.lwxf.industry4.webapp.domain.entity.procurement;

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
 * 功能：purchase_audit 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-10-21 03:29
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "purchase_audit",displayName = "purchase_audit")
public class PurchaseAudit extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "user_id",displayName = "人员id")
	private String userId;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = " 人员分类：0-审核人 1-抄送人  2-申请人 3-采购人")
	private Integer type;
	@Column(type = Types.VARCHAR,length = 100,name = "audit_opinion",displayName = "审核意见")
	private String auditOpinion;
	@Column(type = TypesExtend.DATETIME,name = "audit_time",displayName = "审核时间")
	private Date auditTime;
	@Column(type = Types.TINYINT,length = 2,name = "result",displayName = "审核结果 0-同意 1-拒绝")
	private Integer result;
	@Column(type = Types.CHAR,length = 13,name = "purchase_request_id",displayName = "申请单id")
	private String purchaseRequestId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.TINYINT,length = 2,name = "status",displayName = "审核状态 0-已审核 1-未审核")
	private Integer status;

	public PurchaseAudit() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.userId == null) {
			validResult.put("userId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.type == null) {
			validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.auditOpinion) > 100) {
			validResult.put("auditOpinion", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.purchaseRequestId) > 13) {
			validResult.put("purchaseRequestId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.creator == null) {
			validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	private final static List<String> propertiesList = Arrays.asList("id","userId","type","auditOpinion","auditTime","result","purchaseRequestId","creator","created");

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
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("auditTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("auditTime",String.class))) {
				validResult.put("auditTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("userId")) {
			if (map.getTypedValue("userId",String.class)  == null) {
				validResult.put("userId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("userId",String.class)) > 13) {
					validResult.put("userId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("auditOpinion")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("auditOpinion",String.class)) > 100) {
				validResult.put("auditOpinion", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("result")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("result",String.class)) > 2) {
				validResult.put("result", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("purchaseRequestId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("purchaseRequestId",String.class)) > 13) {
				validResult.put("purchaseRequestId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("creator")) {
			if (map.getTypedValue("creator",String.class)  == null) {
				validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
					validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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


	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setAuditOpinion(String auditOpinion){
		this.auditOpinion=auditOpinion;
	}

	public String getAuditOpinion(){
		return auditOpinion;
	}

	public void setAuditTime(Date auditTime){
		this.auditTime=auditTime;
	}

	public Date getAuditTime(){
		return auditTime;
	}

	public void setResult(Integer result){
		this.result=result;
	}

	public Integer getResult(){
		return result;
	}

	public void setPurchaseRequestId(String purchaseRequestId){
		this.purchaseRequestId=purchaseRequestId;
	}

	public String getPurchaseRequestId(){
		return purchaseRequestId;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}

