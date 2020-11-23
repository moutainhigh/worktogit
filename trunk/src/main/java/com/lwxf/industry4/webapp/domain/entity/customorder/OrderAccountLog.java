package com.lwxf.industry4.webapp.domain.entity.customorder;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;
/**
 * 功能：order_account_log 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 10:57 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@Table(name = "order_account_log",displayName = "order_account_log")
@ApiModel(value = "订单报价修改记录",discriminator = "订单报价修改记录")
public class OrderAccountLog extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "order_id",displayName = "订单主键ID")
	@ApiModelProperty(value = "订单主键ID")
	private String orderId;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "说明")
	@ApiModelProperty(value = "说明")
	private String notes;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "original_price",displayName = "原价")
	@ApiModelProperty(value = "原价")
	private BigDecimal originalPrice;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "present_price",displayName = "现价")
	@ApiModelProperty(value = "现价")
	private BigDecimal presentPrice;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "0:设计费(design）,1.货款(cargo),2其他")
	@ApiModelProperty(value = "0:设计费(design）,1.货款(cargo),2其他")
	private Integer type;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "preferential_price",displayName = "优惠价")
	@ApiModelProperty(value = "优惠价")
	private BigDecimal preferentialPrice;
	@Column(type = Types.TINYINT,nullable = false,name = "classification",displayName = "分类（0 厂家对经销商  1经销商对终端）")
	@ApiModelProperty(value = "分类（0 厂家对经销商  1经销商对终端）")
	private Integer classification;

	public OrderAccountLog() {
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
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.creator == null) {
			validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.classification == null) {
			validResult.put("classification", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(this.presentPrice != null){
			if(presentPrice.compareTo(new BigDecimal(100000000))!=-1){
				validResult.put("price",AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
			if(presentPrice.compareTo(new BigDecimal(0))==-1){
				validResult.put("price",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_LESS_THAN_ZERO_20025"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","orderId","notes","originalPrice","presentPrice","creator","created","type","preferentialPrice","classification");

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
		if(map.containsKey("originalPrice")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("originalPrice",String.class))) {
				validResult.put("originalPrice", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("presentPrice")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("presentPrice",String.class))) {
				validResult.put("presentPrice", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("preferentialPrice")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("preferentialPrice",String.class))) {
				validResult.put("preferentialPrice", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("classification")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("classification",String.class))) {
				validResult.put("classification", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
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
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("creator")) {
			if (map.getTypedValue("creator",String.class)  == null) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
					validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("classification")) {
			if (map.get("classification")  == null) {
				validResult.put("classification", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setOriginalPrice(BigDecimal originalPrice){
		this.originalPrice=originalPrice;
	}

	public BigDecimal getOriginalPrice(){
		return originalPrice;
	}

	public void setPresentPrice(BigDecimal presentPrice){
		this.presentPrice=presentPrice;
	}

	public BigDecimal getPresentPrice(){
		return presentPrice;
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

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setPreferentialPrice(BigDecimal preferentialPrice){
		this.preferentialPrice=preferentialPrice;
	}

	public BigDecimal getPreferentialPrice(){
		return preferentialPrice;
	}

	public void setClassification(Integer classification){
		this.classification=classification;
	}

	public Integer getClassification(){
		return classification;
	}
}
