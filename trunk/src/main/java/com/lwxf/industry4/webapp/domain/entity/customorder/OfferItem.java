package com.lwxf.industry4.webapp.domain.entity.customorder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：offer_item 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-07-01 02:12 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "offer_item",displayName = "offer_item")
@ApiModel(value = "报价条目",description = "报价条目")
public class OfferItem extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 255,name = "name",displayName = "产品名称")
	@ApiModelProperty(value = "产品名称")
	private String name;
	@Column(type = Types.VARCHAR,length = 255,name = "spec",displayName = "规格")
	@ApiModelProperty(value = "规格")
	private String spec;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "unit_price",displayName = "单价")
	@ApiModelProperty(value = "单价")
	private BigDecimal unitPrice;
	@Column(type = Types.VARCHAR,length = 255,name = "unit",displayName = "单位")
	@ApiModelProperty(value = "单位")
	private String unit;
	@Column(type = Types.INTEGER,name = "num",displayName = "数量")
	@ApiModelProperty(value = "数量")
	private Integer num;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "subtotal",displayName = "小计")
	@ApiModelProperty(value = "小计")
	private BigDecimal subtotal;
	@Column(type = Types.VARCHAR,length = 255,name = "remarks",displayName = "备注")
	@ApiModelProperty(value = "备注")
	private String remarks;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "offer_id",displayName = "报价表主键ID")
	@ApiModelProperty(value = "报价表主键ID")
	private String offerId;

    public OfferItem() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.name) > 255) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.spec) > 255) {
			validResult.put("spec", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.unit) > 255) {
			validResult.put("unit", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.remarks) > 255) {
			validResult.put("remarks", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.offerId) > 13) {
			validResult.put("offerId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","spec","unitPrice","unit","num","subtotal","remarks");

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
		if(map.containsKey("unitPrice")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("unitPrice",String.class))) {
				validResult.put("unitPrice", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("num")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("num",String.class))) {
				validResult.put("num", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("subtotal")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("subtotal",String.class))) {
				validResult.put("subtotal", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 255) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("spec")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("spec",String.class)) > 255) {
				validResult.put("spec", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("unit")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("unit",String.class)) > 255) {
				validResult.put("unit", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("remarks")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("remarks",String.class)) > 255) {
				validResult.put("remarks", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setSpec(String spec){
		this.spec=spec;
	}

	public String getSpec(){
		return spec;
	}

	public void setUnitPrice(BigDecimal unitPrice){
		this.unitPrice=unitPrice;
	}

	public BigDecimal getUnitPrice(){
		return unitPrice;
	}

	public void setUnit(String unit){
		this.unit=unit;
	}

	public String getUnit(){
		return unit;
	}

	public void setNum(Integer num){
		this.num=num;
	}

	public Integer getNum(){
		return num;
	}

	public void setSubtotal(BigDecimal subtotal){
		this.subtotal=subtotal;
	}

	public BigDecimal getSubtotal(){
		return subtotal;
	}

	public void setRemarks(String remarks){
		this.remarks=remarks;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setOfferId(String offerId){
		this.offerId=offerId;
	}

	public String getOfferId(){
		return offerId;
	}
}
