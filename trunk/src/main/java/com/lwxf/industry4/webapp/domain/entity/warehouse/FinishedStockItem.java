package com.lwxf.industry4.webapp.domain.entity.warehouse;
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
 * 功能：finished_stock_item 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 10:52 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "finished_stock_item",displayName = "finished_stock_item")
@ApiModel(value = "包裹信息",discriminator = "包裹信息")
public class FinishedStockItem extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "finished_stock_id",displayName = "成品库存id")
	@ApiModelProperty(value = "成品库存id")
	private String finishedStockId;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "类型：0 - 柜体；1 - 门板；2 - 五金（含五金配件、厨具、烟机灶具、礼品等）")
	@ApiModelProperty(value = "类型：0 - 柜体；1 - 门板-自产；2:门板-外协 ;3:特供实木 ;4 - 五金（含五金配件、厨具、烟机灶具、礼品等）")
	private Integer type;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = TypesExtend.DATETIME,name = "delivered",displayName = "发货时间")
	@ApiModelProperty(value = "发货时间")
	private Date delivered;
	@Column(type = Types.CHAR,length = 13,name = "deliverer",displayName = "发货人")
	@ApiModelProperty(value = "发货人")
	private String deliverer;
	@Column(type = Types.VARCHAR,length = 50,name = "barcode",displayName = "包装编号/条形码")
	@ApiModelProperty(value = "包装编号/条形码")
	private String barcode;
	@Column(type = Types.BIT,nullable = false,name = "is_shipped",displayName = "是否已创建配送计划：默认false")
	@ApiModelProperty(value = "是否已创建配送计划：默认false")
	private Boolean shipped;
	@Column(type = Types.VARCHAR,length = 300,name = "notes",displayName = "说明")
	@ApiModelProperty(value = "说明")
	private String notes;
	@Column(type = Types.BIT,nullable = false,name = "is_in",displayName = "是否已入库：默认false")
	@ApiModelProperty(value = "是否已入库：默认false")
	private Boolean in;
	@Column(type = Types.CHAR,length = 10,name = "location",displayName = "所在位置：根据用户习惯手动收入")
	@ApiModelProperty(value = "所在位置：根据用户习惯手动收入")
	private String location;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "operator",displayName = "入库操作人：执行入库操作时，写入操作人id")
	@ApiModelProperty(value = "入库操作人：执行入库操作时，写入操作人id")
	private String operator;
	@Column(type = TypesExtend.DATETIME,updatable = false,name = "operated",displayName = "入库时间：执行入库操作时写入")
	@ApiModelProperty(value = "入库时间：执行入库操作时写入")
	private Date operated;
	@Column(type = Types.VARCHAR,length = 200,name = "warehousingNotes",displayName = "入库备注")
	@ApiModelProperty(value = "入库备注")
	private String warehousingNotes;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "order_product_id",displayName = "订单产品主键ID")
	@ApiModelProperty(value = "订单产品主键ID")
	private String orderProductId;
	private String branchId;
	private Integer amount;

    public FinishedStockItem() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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
		if (LwxfStringUtils.getStringLength(this.deliverer) > 13) {
			validResult.put("deliverer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.barcode) > 50) {
			validResult.put("barcode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.shipped == null) {
			validResult.put("shipped", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 300) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.in == null) {
			validResult.put("in", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
 		if (LwxfStringUtils.getStringLength(this.location) > 10) {
			validResult.put("location", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.operator) > 13) {
			validResult.put("operator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.warehousingNotes) > 200) {
			validResult.put("warehousingNotes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.orderProductId == null) {
			validResult.put("orderProductId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.orderProductId) > 13) {
				validResult.put("orderProductId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("type","creator","created","delivered","deliverer","barcode","shipped","notes","in","location","warehousingNotes","operated","operator","orderProductId");

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
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("delivered")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("delivered",String.class))) {
				validResult.put("delivered", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("shipped")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("shipped",String.class))) {
				validResult.put("shipped", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("in")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("in",String.class))) {
				validResult.put("in", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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
		if(map.containsKey("deliverer")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("deliverer",String.class)) > 13) {
				validResult.put("deliverer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("barcode")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("barcode",String.class)) > 50) {
				validResult.put("barcode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("shipped")) {
			if (map.get("shipped")  == null) {
				validResult.put("shipped", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 300) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("in")) {
			if (map.get("in")  == null) {
				validResult.put("in", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("location")) {
			if (map.getTypedValue("location",String.class)  == null) {
				validResult.put("location", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("location",String.class)) > 10) {
					validResult.put("location", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("warehousingNotes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("warehousingNotes",String.class)) > 200) {
				validResult.put("warehousingNotes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("orderProductId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("orderProductId",String.class)) > 13) {
				validResult.put("orderProductId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setFinishedStockId(String finishedStockId){
		this.finishedStockId=finishedStockId;
	}

	public String getFinishedStockId(){
		return finishedStockId;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
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

	public void setDelivered(Date delivered){
		this.delivered=delivered;
	}

	public Date getDelivered(){
		return delivered;
	}

	public void setDeliverer(String deliverer){
		this.deliverer=deliverer;
	}

	public String getDeliverer(){
		return deliverer;
	}

	public void setBarcode(String barcode){
		this.barcode=barcode;
	}

	public String getBarcode(){
		return barcode;
	}

	public void setShipped(Boolean shipped){
		this.shipped=shipped;
	}

	public Boolean getShipped(){
		return shipped;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setIn(Boolean in){
		this.in=in;
	}

	public Boolean getIn(){
		return in;
	}

	public void setLocation(String location){
		this.location=location;
	}

	public String getLocation(){
		return location;
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

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public String getWarehousingNotes() {
		return warehousingNotes;
	}

	public void setWarehousingNotes(String warehousingNotes) {
		this.warehousingNotes = warehousingNotes;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}
}
