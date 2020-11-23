package com.lwxf.industry4.webapp.domain.entity.customorder;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.*;
/**
 * 功能：order_product_attribute 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-07-29 03:20
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="order_product_attribute对象", description="order_product_attribute")
@Table(name = "order_product_attribute",displayName = "order_product_attribute")
public class OrderProductAttribute extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,name = "order_product_id",displayName = "订单产品id")
	@ApiModelProperty(value = "订单产品id")
	private String orderProductId;
	@Column(type = Types.CHAR,length = 13,name = "product_attribute_key_id",displayName = "产品属性id")
	@ApiModelProperty(value = "产品属性id")
	private String productAttributeKeyId;
	@Column(type = Types.CHAR,length = 13,name = "product_attribute_value_id",displayName = "产品属性值表id")
	@ApiModelProperty(value = "产品属性值表id")
	private String productAttributeValueId;
	@Column(type = Types.VARCHAR,length = 50,name = "key_name",displayName = "属性名称")
	@ApiModelProperty(value = "属性名称")
	private String keyName;
	@Column(type = Types.VARCHAR,length = 50,name = "value_name",displayName = "属性值")
	@ApiModelProperty(value = "属性值")
	private String valueName;

	public OrderProductAttribute() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.orderProductId) > 13) {
			validResult.put("orderProductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.productAttributeKeyId) > 13) {
			validResult.put("productAttributeKeyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.productAttributeValueId) > 13) {
			validResult.put("productAttributeValueId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.keyName) > 50) {
			validResult.put("keyName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.valueName) > 50) {
			validResult.put("valueName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","orderProductId","productAttributeKeyId","productAttributeValueId","keyName","valueName");

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
			if (LwxfStringUtils.getStringLength(map.getTypedValue("orderProductId",String.class)) > 13) {
				validResult.put("orderProductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("productAttributeKeyId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productAttributeKeyId",String.class)) > 13) {
				validResult.put("productAttributeKeyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("productAttributeValueId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productAttributeValueId",String.class)) > 13) {
				validResult.put("productAttributeValueId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("keyName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("keyName",String.class)) > 50) {
				validResult.put("keyName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("valueName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("valueName",String.class)) > 50) {
				validResult.put("valueName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public void setProductAttributeKeyId(String productAttributeKeyId){
		this.productAttributeKeyId=productAttributeKeyId;
	}

	public String getProductAttributeKeyId(){
		return productAttributeKeyId;
	}

	public void setProductAttributeValueId(String productAttributeValueId){
		this.productAttributeValueId=productAttributeValueId;
	}

	public String getProductAttributeValueId(){
		return productAttributeValueId;
	}

	public void setKeyName(String keyName){
		this.keyName=keyName;
	}

	public String getKeyName(){
		return keyName;
	}

	public void setValueName(String valueName){
		this.valueName=valueName;
	}

	public String getValueName(){
		return valueName;
	}
}
