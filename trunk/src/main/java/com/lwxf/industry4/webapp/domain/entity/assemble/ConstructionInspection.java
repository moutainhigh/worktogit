package com.lwxf.industry4.webapp.domain.entity.assemble;
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
 * 功能：construction_inspection 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-19 02:59 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "construction_inspection",displayName = "construction_inspection")
public class ConstructionInspection extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "order_id",displayName = "订单id")
	private String orderId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "dispatch_list_id",displayName = "派工单id")
	private String dispatchListId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "检查项目：衣柜/橱柜/全屋/烟机灶具")
	private String name;
	@Column(type = Types.TINYINT,nullable = false,name = "result",displayName = "检查结果：0 - 不合格；1 - 合格；2 - 良好；3 - 优秀")
	private Integer result;
	@Column(type = Types.VARCHAR,length = 500,name = "notes",displayName = "检查结果说明")
	private String notes;
	@Column(type = Types.CHAR,length = 13,name = "checker",displayName = "检查人")
	private String checker;
	@Column(type = TypesExtend.DATETIME,name = "check_time",displayName = "检查时间")
	private Date checkTime;
	@Column(type = Types.CHAR,length = 13,name = "reviewer",displayName = "复查人")
	private String reviewer;
	@Column(type = TypesExtend.DATETIME,name = "review_time",displayName = "复查时间")
	private Date reviewTime;
	@Column(type = Types.TINYINT,name = "review_result",displayName = "复查结果：0 - 不合格；1 - 合格")
	private Integer reviewResult;
	@Column(type = Types.VARCHAR,length = 500,name = "review_notes",displayName = "复查结果说明")
	private String reviewNotes;

    public ConstructionInspection() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.orderId == null) {
			validResult.put("orderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.orderId) > 13) {
				validResult.put("orderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.dispatchListId == null) {
			validResult.put("dispatchListId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.dispatchListId) > 13) {
				validResult.put("dispatchListId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 50) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.result == null) {
			validResult.put("result", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 500) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.checker) > 13) {
			validResult.put("checker", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.reviewer) > 13) {
			validResult.put("reviewer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.reviewNotes) > 500) {
			validResult.put("reviewNotes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","orderId","dispatchListId","name","result","notes","checker","checkTime","reviewer","reviewTime","reviewResult","reviewNotes");

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
		if(map.containsKey("result")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("result",String.class))) {
				validResult.put("result", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("checkTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("checkTime",String.class))) {
				validResult.put("checkTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("reviewTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("reviewTime",String.class))) {
				validResult.put("reviewTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("reviewResult")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("reviewResult",String.class))) {
				validResult.put("reviewResult", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("orderId")) {
			if (map.getTypedValue("orderId",String.class)  == null) {
				validResult.put("orderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("orderId",String.class)) > 13) {
					validResult.put("orderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("dispatchListId")) {
			if (map.getTypedValue("dispatchListId",String.class)  == null) {
				validResult.put("dispatchListId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("dispatchListId",String.class)) > 13) {
					validResult.put("dispatchListId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("result")) {
			if (map.get("result")  == null) {
				validResult.put("result", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 500) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("checker")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("checker",String.class)) > 13) {
				validResult.put("checker", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("reviewer")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reviewer",String.class)) > 13) {
				validResult.put("reviewer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("reviewNotes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reviewNotes",String.class)) > 500) {
				validResult.put("reviewNotes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setOrderId(String orderId){
		this.orderId=orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setDispatchListId(String dispatchListId){
		this.dispatchListId=dispatchListId;
	}

	public String getDispatchListId(){
		return dispatchListId;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setResult(Integer result){
		this.result=result;
	}

	public Integer getResult(){
		return result;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setChecker(String checker){
		this.checker=checker;
	}

	public String getChecker(){
		return checker;
	}

	public void setCheckTime(Date checkTime){
		this.checkTime=checkTime;
	}

	public Date getCheckTime(){
		return checkTime;
	}

	public void setReviewer(String reviewer){
		this.reviewer=reviewer;
	}

	public String getReviewer(){
		return reviewer;
	}

	public void setReviewTime(Date reviewTime){
		this.reviewTime=reviewTime;
	}

	public Date getReviewTime(){
		return reviewTime;
	}

	public void setReviewResult(Integer reviewResult){
		this.reviewResult=reviewResult;
	}

	public Integer getReviewResult(){
		return reviewResult;
	}

	public void setReviewNotes(String reviewNotes){
		this.reviewNotes=reviewNotes;
	}

	public String getReviewNotes(){
		return reviewNotes;
	}
}
