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
 * 功能：order_product_parts 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-30 10:48 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "order_product_parts",displayName = "order_product_parts")
public class OrderProductParts extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "order_product_id",displayName = "订单产品ID")
	private String orderProductId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "product_parts_id",displayName = "产品部件ID")
	private String productPartsId;

    public OrderProductParts() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.orderProductId == null) {
			validResult.put("orderProductId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.orderProductId) > 13) {
				validResult.put("orderProductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.productPartsId == null) {
			validResult.put("productPartsId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.productPartsId) > 13) {
				validResult.put("productPartsId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","orderProductId","productPartsId");

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
		if(map.containsKey("orderProductId")) {
			if (map.getTypedValue("orderProductId",String.class)  == null) {
				validResult.put("orderProductId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("orderProductId",String.class)) > 13) {
					validResult.put("orderProductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("productPartsId")) {
			if (map.getTypedValue("productPartsId",String.class)  == null) {
				validResult.put("productPartsId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("productPartsId",String.class)) > 13) {
					validResult.put("productPartsId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setOrderProductId(String orderProductId){
		this.orderProductId=orderProductId;
	}

	public String getOrderProductId(){
		return orderProductId;
	}

	public void setProductPartsId(String productPartsId){
		this.productPartsId=productPartsId;
	}

	public String getProductPartsId(){
		return productPartsId;
	}
}
