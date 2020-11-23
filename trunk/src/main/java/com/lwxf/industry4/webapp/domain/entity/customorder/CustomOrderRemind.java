package com.lwxf.industry4.webapp.domain.entity.customorder;
import java.util.*;
import java.sql.*;
import java.util.Date;

import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.mybatis.utils.TypesExtend;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
/**
 * 功能：订单催款
 *
 * @author：DJL(yuuyoo@163.com)
 * @created：2020-01-06 10:08 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="custom_order_remind对象", description="custom_order_remind")
@Table(name = "custom_order_remind",displayName = "custom_order_remind")
public class CustomOrderRemind extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "custom_order_id",displayName = "订单id")
	@ApiModelProperty(value = "订单id")
	private String customOrderId;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	@ApiModelProperty(value = "企业id")
	private String branchId;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态： 0 - 待催款； 1 - 催款中； 2 - 催款完成；")
	@ApiModelProperty(value = "状态： 0 - 待催款； 1 - 催款中； 2 - 催款完成；")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.VARCHAR,length = 50,name = "time_consuming",displayName = "催款耗时（催款提交到催款完成）")
	@ApiModelProperty(value = "催款耗时（催款提交到催款完成）")
	private String timeConsuming;
	@Column(type = Types.CHAR,length = 13,name = "receiver",displayName = "接单人（催款人员）")
	@ApiModelProperty(value = "接单人（催款人员）")
	private String receiver;
	@Column(type = TypesExtend.DATETIME,name = "receipt_time",displayName = "催款单接单时间")
	@ApiModelProperty(value = "催款单接单时间")
	private Date receiptTime;
	@Column(type = TypesExtend.DATETIME,name = "commit_time",displayName = "提交财务审核时间")
	@ApiModelProperty(value = "提交财务审核时间")
	private Date commitTime;
	@Column(type = Types.VARCHAR,length = 255,name = "remark",displayName = "催款备注")
	@ApiModelProperty(value = "催款备注")
	private String remark;

    public CustomOrderRemind() {
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.customOrderId == null) {
			validResult.put("customOrderId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
				validResult.put("customOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
			validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
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
		if (LwxfStringUtils.getStringLength(this.timeConsuming) > 50) {
			validResult.put("timeConsuming", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.receiver) > 13) {
			validResult.put("receiver", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.remark) > 255) {
			validResult.put("remark", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","customOrderId","branchId","status","creator","created","timeConsuming","receiver","receiptTime","commitTime","remark");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isInteger(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("receiptTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("receiptTime",String.class))) {
				validResult.put("receiptTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("commitTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("commitTime",String.class))) {
				validResult.put("commitTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("customOrderId")) {
			if (map.getTypedValue("customOrderId",String.class)  == null) {
				validResult.put("customOrderId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("customOrderId",String.class)) > 13) {
					validResult.put("customOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("branchId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("branchId",String.class)) > 13) {
				validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
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
		if(map.containsKey("timeConsuming")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("timeConsuming",String.class)) > 50) {
				validResult.put("timeConsuming", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("receiver")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("receiver",String.class)) > 13) {
				validResult.put("receiver", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("remark")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("remark",String.class)) > 255) {
				validResult.put("remark", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setBranchId(String branchId){
		this.branchId=branchId;
	}

	public String getBranchId(){
		return branchId;
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

	public void setTimeConsuming(String timeConsuming){
		this.timeConsuming=timeConsuming;
	}

	public String getTimeConsuming(){
		return timeConsuming;
	}

	public void setReceiver(String receiver){
		this.receiver=receiver;
	}

	public String getReceiver(){
		return receiver;
	}

	public void setReceiptTime(Date receiptTime){
		this.receiptTime=receiptTime;
	}

	public Date getReceiptTime(){
		return receiptTime;
	}

	public void setCommitTime(Date commitTime){
		this.commitTime=commitTime;
	}

	public Date getCommitTime(){
		return commitTime;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}
}
