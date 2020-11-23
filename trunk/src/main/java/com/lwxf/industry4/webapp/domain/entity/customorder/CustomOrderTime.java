package com.lwxf.industry4.webapp.domain.entity.customorder;
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
 * 功能：custom_order_time 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-09-03 03:45 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "custom_order_time",displayName = "custom_order_time")
public class CustomOrderTime extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.TINYINT,name = "produce_type",displayName = "部件类型 0 柜体 1 门板 2 五金")
	private Integer produceType;
	@Column(type = Types.TINYINT,name = "product_type",displayName = "产品类型")
	private Integer productType;
	@Column(type = Types.TINYINT,name = "product_series",displayName = "产品系列")
	private Integer productSeries;
	@Column(type = Types.INTEGER,name = "receive_time",displayName = "接单工期")
	private Integer receiveTime;
	@Column(type = Types.INTEGER,name = "order_time",displayName = "下单工期")
	private Integer orderTime;
	@Column(type = Types.INTEGER,name = "order_remind",displayName = "催款工期")
	private Integer orderRemind;
	@Column(type = Types.INTEGER,name = "audit_time",displayName = "财务审核工期")
	private Integer auditTime;
	@Column(type = Types.INTEGER,name = "produce_time",displayName = "生产工期")
	private Integer produceTime;
	@Column(type = Types.INTEGER,name = "input_time",displayName = "入库工期")
	private Integer inputTime;
	@Column(type = Types.INTEGER,name = "deliver_time",displayName = "物流工期")
	private Integer deliverTime;
	@Column(type = Types.INTEGER,name = "all_cost",displayName = "总工期")
	private Integer allCost;
	@Column(type = Types.TINYINT,name = "order_type",displayName = "订单类型：1：正常，2:补料单")
	private Integer orderType;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	private String branchId;

    public CustomOrderTime() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
			validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","produceType","productType","productSeries","orderTime","produceTime","deliverTime","allCost","orderType","branchId");

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
		if(map.containsKey("produceType")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("produceType",String.class))) {
				validResult.put("produceType", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("productType")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("productType",String.class))) {
				validResult.put("productType", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("productSeries")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("productSeries",String.class))) {
				validResult.put("productSeries", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("orderTime")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("orderTime",String.class))) {
				validResult.put("orderTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("produceTime")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("produceTime",String.class))) {
				validResult.put("produceTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("deliverTime")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("deliverTime",String.class))) {
				validResult.put("deliverTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("allCost")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("allCost",String.class))) {
				validResult.put("allCost", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("orderType")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("orderType",String.class))) {
				validResult.put("orderType", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
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


	public void setProduceType(Integer produceType){
		this.produceType=produceType;
	}

	public Integer getProduceType(){
		return produceType;
	}

	public void setProductType(Integer productType){
		this.productType=productType;
	}

	public Integer getProductType(){
		return productType;
	}

	public void setProductSeries(Integer productSeries){
		this.productSeries=productSeries;
	}

	public Integer getProductSeries(){
		return productSeries;
	}

	public void setOrderTime(Integer orderTime){
		this.orderTime=orderTime;
	}

	public Integer getOrderTime(){
		return orderTime;
	}

	public void setProduceTime(Integer produceTime){
		this.produceTime=produceTime;
	}

	public Integer getProduceTime(){
		return produceTime;
	}

	public void setDeliverTime(Integer deliverTime){
		this.deliverTime=deliverTime;
	}

	public Integer getDeliverTime(){
		return deliverTime;
	}

	public void setAllCost(Integer allCost){
		this.allCost=allCost;
	}

	public Integer getAllCost(){
		return allCost;
	}

	public void setOrderType(Integer orderType){
		this.orderType=orderType;
	}

	public Integer getOrderType(){
		return orderType;
	}

	public void setBranchId(String branchId){
		this.branchId=branchId;
	}

	public String getBranchId(){
		return branchId;
	}

	public Integer getReceiveTime() {
		return receiveTime;
	}

	public void setReceiveTime(Integer receiveTime) {
		this.receiveTime = receiveTime;
	}

	public Integer getOrderRemind() {
		return orderRemind;
	}

	public void setOrderRemind(Integer orderRemind) {
		this.orderRemind = orderRemind;
	}

	public Integer getAuditTime() {
		return auditTime;
	}

	public void setAuditTime(Integer auditTime) {
		this.auditTime = auditTime;
	}

	public Integer getInputTime() {
		return inputTime;
	}

	public void setInputTime(Integer inputTime) {
		this.inputTime = inputTime;
	}
}
