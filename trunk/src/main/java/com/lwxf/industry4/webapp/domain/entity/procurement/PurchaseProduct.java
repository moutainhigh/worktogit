package com.lwxf.industry4.webapp.domain.entity.procurement;

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
 * 功能：purchase_product 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-10-21 03:29
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "purchase_product",displayName = "purchase_product")
public class PurchaseProduct extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "purchase_request_id",displayName = "采购申请单id")
	private String purchaseRequestId;
	@Column(type = Types.CHAR,length = 13,name = "supplier_product_id",displayName = "供应商产品id")
	private String supplierProductId;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "price",displayName = "采购价：默认为供应商的产品报价,可手工更改")
	private BigDecimal price;
	@Column(type = Types.INTEGER,nullable = false,name = "quantity",displayName = "采购数量：默认0")
	private Integer quantity;
	@Column(type = Types.INTEGER,name = "status",displayName = "状态：0 - 待质检；1 - 不合格；2 - 待入库；3 - 已入库；4 - 已退货")
	private Integer status;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "附加说明：质检未通过或其他不能入库的说明")
	private String notes;
	@Column(type = Types.CHAR,length = 13,name = "operator",displayName = "入库人")
	private String operator;
	@Column(type = TypesExtend.DATETIME,name = "operated",displayName = "入库时间")
	private Date operated;
	@Column(type = Types.CHAR,length = 13,name = "storage_id",displayName = "仓库id")
	private String storageId;
	@Column(type = Types.INTEGER,name = "into_storage",displayName = "入库数量")
	private Integer intoStorage;

	public PurchaseProduct() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.purchaseRequestId == null) {
			validResult.put("purchaseRequestId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.purchaseRequestId) > 13) {
				validResult.put("purchaseRequestId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.supplierProductId) > 13) {
			validResult.put("supplierProductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.quantity == null) {
			validResult.put("quantity", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.operator) > 13) {
			validResult.put("operator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.storageId) > 13) {
			validResult.put("storageId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","purchaseRequestId","supplierProductId","price","quantity","status","notes","operator","operated","storageId");

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
		if(map.containsKey("price")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("price",String.class))) {
				validResult.put("price", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("quantity")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("quantity",String.class))) {
				validResult.put("quantity", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("operated")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("operated",String.class))) {
				validResult.put("operated", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("purchaseRequestId")) {
			if (map.getTypedValue("purchaseRequestId",String.class)  == null) {
				validResult.put("purchaseRequestId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("purchaseRequestId",String.class)) > 13) {
					validResult.put("purchaseRequestId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("supplierProductId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("supplierProductId",String.class)) > 13) {
				validResult.put("supplierProductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("quantity")) {
			if (map.get("quantity")  == null) {
				validResult.put("quantity", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("operator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("operator",String.class)) > 13) {
				validResult.put("operator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("storageId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("storageId",String.class)) > 13) {
				validResult.put("storageId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setPurchaseRequestId(String purchaseRequestId){
		this.purchaseRequestId=purchaseRequestId;
	}

	public String getPurchaseRequestId(){
		return purchaseRequestId;
	}

	public void setSupplierProductId(String supplierProductId){
		this.supplierProductId=supplierProductId;
	}

	public String getSupplierProductId(){
		return supplierProductId;
	}

	public void setPrice(BigDecimal price){
		this.price=price;
	}

	public BigDecimal getPrice(){
		return price;
	}

	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}

	public Integer getQuantity(){
		return quantity;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setOperator(String operator){
		this.operator=operator;
	}

	public String getOperator(){
		return operator;
	}

	public void setOperated(Date operated){
		this.operated=operated;
	}

	public Date getOperated(){
		return operated;
	}

	public void setStorageId(String storageId){
		this.storageId=storageId;
	}

	public String getStorageId(){
		return storageId;
	}

	public Integer getIntoStorage() {
		return intoStorage;
	}

	public void setIntoStorage(Integer intoStorage) {
		this.intoStorage = intoStorage;
	}
}
