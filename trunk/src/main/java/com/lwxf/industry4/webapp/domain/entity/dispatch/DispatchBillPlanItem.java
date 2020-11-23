package com.lwxf.industry4.webapp.domain.entity.dispatch;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.facade.AppBeanInjector;
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
 * 功能：dispatch_bill_plan_item 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-04-18 02:31 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "dispatch_bill_plan_item",displayName = "dispatch_bill_plan_item")
@ApiModel(value = "配送计划条目信息",discriminator = "配送计划条目信息")
public class DispatchBillPlanItem extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "dispatch_bill_plan_id",displayName = "")
	@ApiModelProperty(value = "配送计划主键ID")
	private String dispatchBillPlanId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "finished_stock_item_id",displayName = "")
	@ApiModelProperty(value = "包裹主键ID(无用)")
	private String finishedStockItemId;
	@Column(type = Types.INTEGER,nullable = false,name = "status",displayName = "发货状态 0:未发货 1:已发货(默认为0)")
	@ApiModelProperty(value = "发货状态 0:未发货 1:已发货(默认为0)")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "produce_id",displayName = "")
	@ApiModelProperty(value = "生产单ID")
	private String produceId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "order_product_id",displayName = "")
	@ApiModelProperty(value = "产品ID")
	private String orderProductId;
	@Column(type = Types.VARCHAR,length = 255,nullable = false,name = "audit_opinion",displayName = "审核意见")
	@ApiModelProperty(value = "审核意见")
	private String auditOpinion;
	@Column(type = Types.INTEGER,nullable = false,name = "produce_num",displayName = "本次发货包裹数")
	@ApiModelProperty(value = "本次发货包裹数")
	private Integer produceNum;
	@Column(type = Types.INTEGER,nullable = false,name = "audit_result",displayName = "审核结果 0-可发货 1-不可发货")
	@ApiModelProperty(value = "审核结果 0-可发货 1-不可发货")
	private Integer auditResult;



    public DispatchBillPlanItem() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.dispatchBillPlanId == null) {
			validResult.put("dispatchBillPlanId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.dispatchBillPlanId) > 13) {
				validResult.put("dispatchBillPlanId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.finishedStockItemId == null) {
			validResult.put("finishedStockItemId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.finishedStockItemId) > 13) {
				validResult.put("finishedStockItemId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","dispatchBillPlanId","finishedStockItemId","status");

	public static RequestResult validateFields(MapContext map) {
		Map<String, String> validResult = new HashMap<>();
		if(map.size()==0){
			return ResultFactory.generateErrorResult("error",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		boolean flag;
		Set<String> mapSet = map.keySet();
		flag = propertiesList.containsAll(mapSet);
		if(!flag){
			return ResultFactory.generateErrorResult("error",AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("dispatchBillPlanId")) {
			if (map.getTypedValue("dispatchBillPlanId",String.class)  == null) {
				validResult.put("dispatchBillPlanId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("dispatchBillPlanId",String.class)) > 13) {
					validResult.put("dispatchBillPlanId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("finishedStockItemId")) {
			if (map.getTypedValue("finishedStockItemId",String.class)  == null) {
				validResult.put("finishedStockItemId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("finishedStockItemId",String.class)) > 13) {
					validResult.put("finishedStockItemId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setDispatchBillPlanId(String dispatchBillPlanId){
		this.dispatchBillPlanId=dispatchBillPlanId;
	}

	public String getDispatchBillPlanId(){
		return dispatchBillPlanId;
	}

	public void setFinishedStockItemId(String finishedStockItemId){
		this.finishedStockItemId=finishedStockItemId;
	}

	public String getFinishedStockItemId(){
		return finishedStockItemId;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public String getProduceId() {
		return produceId;
	}

	public void setProduceId(String produceId) {
		this.produceId = produceId;
	}

	public String getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}

	public String getAuditOpinion() {
		return auditOpinion;
	}

	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}

	public Integer getProduceNum() {
		return produceNum;
	}

	public void setProduceNum(Integer produceNum) {
		this.produceNum = produceNum;
	}

	public Integer getAuditResult() {
		return auditResult;
	}

	public void setAuditResult(Integer auditResult) {
		this.auditResult = auditResult;
	}
}
