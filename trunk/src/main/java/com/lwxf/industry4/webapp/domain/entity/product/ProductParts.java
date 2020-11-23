package com.lwxf.industry4.webapp.domain.entity.product;
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
 * 功能：product_parts 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-30 10:40 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "product_parts",displayName = "product_parts")
public class ProductParts extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.TINYINT,nullable = false,name = "product_type",displayName = "产品类型 0-橱柜 1-衣柜 2-成品家具 3-电器 4-五金 5-样块")
	private Integer productType;
	@Column(type = Types.TINYINT,nullable = false,name = "parts_type",displayName = "部件类型 0-门板 1- 柜体 2-五金")
	private Integer partsType;
	@Column(type = Types.TINYINT,nullable = false,name = "produce_type",displayName = "生产方式 0-自产 1-外协 2-特供实木 3-五金")
	private Integer produceType;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "updator",displayName = "修改人")
	private String updator;
	@Column(type = TypesExtend.DATETIME,name = "updated",displayName = "修改时间")
	private Date updated;
	@Column(type = Types.VARCHAR,length = 20,name = "order_parts_identify",displayName = "部件在订单中体现的标识")
	private String orderPartsIdentify;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业ID")
	private String branchId;

    public ProductParts() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.productType == null) {
			validResult.put("productType", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.partsType == null) {
			validResult.put("partsType", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.produceType == null) {
			validResult.put("produceType", ErrorCodes.VALIDATE_NOTNULL);
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
		if (LwxfStringUtils.getStringLength(this.updator) > 13) {
			validResult.put("updator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.orderPartsIdentify) > 20) {
			validResult.put("orderPartsIdentify", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","productType","partsType","produceType","creator","created","updator","updated","orderPartsIdentify");

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
		if(map.containsKey("partsType")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("partsType",String.class))) {
				validResult.put("partsType", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("produceType")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("produceType",String.class))) {
				validResult.put("produceType", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("updated")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updated",String.class))) {
				validResult.put("updated", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("productType")) {
			if (map.getTypedValue("productType",String.class)  == null) {
				validResult.put("productType", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("partsType")) {
			if (map.get("partsType")  == null) {
				validResult.put("partsType", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("produceType")) {
			if (map.get("produceType")  == null) {
				validResult.put("produceType", ErrorCodes.VALIDATE_NOTNULL);
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
		if(map.containsKey("updator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("updator",String.class)) > 13) {
				validResult.put("updator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("orderPartsIdentify")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("orderPartsIdentify",String.class)) > 20) {
				validResult.put("orderPartsIdentify", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public Integer getProductType() {
		return productType;
	}

	public void setProductType(Integer productType) {
		this.productType = productType;
	}

	public Integer getPartsType() {
		return partsType;
	}

	public void setPartsType(Integer partsType) {
		this.partsType = partsType;
	}

	public Integer getProduceType() {
		return produceType;
	}

	public void setProduceType(Integer produceType) {
		this.produceType = produceType;
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

	public void setUpdator(String updator){
		this.updator=updator;
	}

	public String getUpdator(){
		return updator;
	}

	public void setUpdated(Date updated){
		this.updated=updated;
	}

	public Date getUpdated(){
		return updated;
	}

	public void setOrderPartsIdentify(String orderPartsIdentify){
		this.orderPartsIdentify=orderPartsIdentify;
	}

	public String getOrderPartsIdentify(){
		return orderPartsIdentify;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
