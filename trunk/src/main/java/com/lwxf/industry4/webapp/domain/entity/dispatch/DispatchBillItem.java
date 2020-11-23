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
 * 功能：dispatch_bill_item 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-20 03:06 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "dispatch_bill_item",displayName = "dispatch_bill_item")
@ApiModel(value = "发货条目信息",discriminator = "发货条目信息")
public class DispatchBillItem extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "dispatch_bill_id",displayName = "")
	@ApiModelProperty(value = "发货单主键ID")
	private String dispatchBillId;
	@Column(type = Types.CHAR,length = 13,name = "finished_stock_item_id",displayName = "")
	@ApiModelProperty(value = "包裹主键ID")
	private String finishedStockItemId;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 到货；1 - 缺失；2 - 损坏；默认0")
	@ApiModelProperty(value = "状态：0 - 到货；1 - 缺失；2 - 损坏；默认0")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "product_id",displayName = "")
	@ApiModelProperty(value = "产品id")
	private String productId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "order_produce_id",displayName = "")
	@ApiModelProperty(value = "生产单id")
	private String orderProduceId;
	@Column(type = Types.INTEGER,length = 11,nullable = false,name = "deliver_num",displayName = "")
	@ApiModelProperty(value = "发货包裹数量")
	private Integer deliverNum;

    public DispatchBillItem() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.dispatchBillId == null) {
			validResult.put("dispatchBillId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.dispatchBillId) > 13) {
				validResult.put("dispatchBillId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.productId == null) {
			validResult.put("productId", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.orderProduceId == null) {
			validResult.put("orderProduceId", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.finishedStockItemId) > 13) {
			validResult.put("finishedStockItemId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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

	private final static List<String> propertiesList = Arrays.asList("id","dispatchBillId","finishedStockItemId","status");

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
		if(map.containsKey("dispatchBillId")) {
			if (map.getTypedValue("dispatchBillId",String.class)  == null) {
				validResult.put("dispatchBillId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("dispatchBillId",String.class)) > 13) {
					validResult.put("dispatchBillId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("finishedStockItemId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("finishedStockItemId",String.class)) > 13) {
				validResult.put("finishedStockItemId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("productId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productId",String.class)) > 13) {
				validResult.put("productId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("orderProduceId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("orderProduceId",String.class)) > 13) {
				validResult.put("orderProduceId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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

	public String getProductId() {return productId;}

	public void setProductId(String productId) {this.productId = productId;}

	public String getOrderProduceId() {return orderProduceId;}

	public void setOrderProduceId(String orderProduceId) {this.orderProduceId = orderProduceId;}

	public void setDispatchBillId(String dispatchBillId){
		this.dispatchBillId=dispatchBillId;
	}

	public String getDispatchBillId(){
		return dispatchBillId;
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

	public Integer getDeliverNum() {
		return deliverNum;
	}

	public void setDeliverNum(Integer deliverNum) {
		this.deliverNum = deliverNum;
	}
}
