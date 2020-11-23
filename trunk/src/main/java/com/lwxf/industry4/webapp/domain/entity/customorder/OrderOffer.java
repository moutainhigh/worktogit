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
 * 功能：order_offer 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-07-01 02:12 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "order_offer",displayName = "order_offer")
@ApiModel(value = "订单报价信息",description = "订单报价信息")
public class OrderOffer extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 255,name = "quoter",displayName = "报价人")
	@ApiModelProperty(value = "报价人")
	private String quoter;
	@Column(type = Types.VARCHAR,length = 255,name = "auditor",displayName = "审核人")
	@ApiModelProperty(value = "审核人")
	private String auditor;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "design_fee",displayName = "设计费")
	@ApiModelProperty(value = "设计费")
	private BigDecimal designFee;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "price",displayName = "货款")
	@ApiModelProperty(value = "货款")
	private BigDecimal price;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "discount_design_fee",displayName = "优惠设计费")
	@ApiModelProperty(value = "优惠设计费")
	private BigDecimal discountDesignFee;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "discount_price",displayName = "优惠货款")
	@ApiModelProperty(value = "优惠货款")
	private BigDecimal discountPrice;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "receipts_design_fee",displayName = "实收设计费")
	@ApiModelProperty(value = "实收设计费")
	private BigDecimal receiptsDesignFee;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "receipts_price",displayName = "实收货款")
	@ApiModelProperty(value = "实收货款")
	private BigDecimal receiptsPrice;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "order_id",displayName = "订单主键ID")
	@ApiModelProperty(value = "订单主键ID")
	private String orderId;

    public OrderOffer() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.quoter) > 255) {
			validResult.put("quoter", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.auditor) > 255) {
			validResult.put("auditor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.orderId == null) {
			validResult.put("orderId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.orderId) > 13) {
				validResult.put("orderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("quoter","auditor","designFee","price","discountDesignFee","discountPrice","receiptsDesignFee","receiptsPrice");

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
		if(map.containsKey("designFee")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("designFee",String.class))) {
				validResult.put("designFee", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("price")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("price",String.class))) {
				validResult.put("price", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("discountDesignFee")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("discountDesignFee",String.class))) {
				validResult.put("discountDesignFee", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("discountPrice")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("discountPrice",String.class))) {
				validResult.put("discountPrice", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("receiptsDesignFee")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("receiptsDesignFee",String.class))) {
				validResult.put("receiptsDesignFee", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("receiptsPrice")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("receiptsPrice",String.class))) {
				validResult.put("receiptsPrice", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("quoter")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("quoter",String.class)) > 255) {
				validResult.put("quoter", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("auditor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("auditor",String.class)) > 255) {
				validResult.put("auditor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setQuoter(String quoter){
		this.quoter=quoter;
	}

	public String getQuoter(){
		return quoter;
	}

	public void setAuditor(String auditor){
		this.auditor=auditor;
	}

	public String getAuditor(){
		return auditor;
	}

	public void setDesignFee(BigDecimal designFee){
		this.designFee=designFee;
	}

	public BigDecimal getDesignFee(){
		return designFee;
	}

	public void setPrice(BigDecimal price){
		this.price=price;
	}

	public BigDecimal getPrice(){
		return price;
	}

	public void setDiscountDesignFee(BigDecimal discountDesignFee){
		this.discountDesignFee=discountDesignFee;
	}

	public BigDecimal getDiscountDesignFee(){
		return discountDesignFee;
	}

	public void setDiscountPrice(BigDecimal discountPrice){
		this.discountPrice=discountPrice;
	}

	public BigDecimal getDiscountPrice(){
		return discountPrice;
	}

	public void setReceiptsDesignFee(BigDecimal receiptsDesignFee){
		this.receiptsDesignFee=receiptsDesignFee;
	}

	public BigDecimal getReceiptsDesignFee(){
		return receiptsDesignFee;
	}

	public void setReceiptsPrice(BigDecimal receiptsPrice){
		this.receiptsPrice=receiptsPrice;
	}

	public BigDecimal getReceiptsPrice(){
		return receiptsPrice;
	}

	public void setOrderId(String orderId){
		this.orderId=orderId;
	}

	public String getOrderId(){
		return orderId;
	}
}
