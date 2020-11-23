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
 * 功能：dispatch_list 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2018-12-29 01:19 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "dispatch_list",displayName = "dispatch_list")
public class DispatchList extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "name",displayName = "安装的名称（一般指安装内容，例如：衣柜/橱柜/烟机灶具）")
	private String name;
	@Column(type = Types.CHAR,length = 13,name = "erector",displayName = "安装人")
	private String erector;
	@Column(type = TypesExtend.DATETIME,name = "construction_time",displayName = "安装（施工）时间")
	private Date constructionTime;
	@Column(type = Types.VARCHAR,length = 50,name = "address",displayName = "安装地址（客户地址）")
	private String address;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人（派工人员）")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间（派工时间）")
	private Date created;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 待安装；1 - 安装中；2 - 待质检；3 - 整改中（出现安装问题）；4 - 质检通过；5 - 已完成；")
	private Integer status;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "no",displayName = "派工单编号，编号规则：公司编号+时间戳，例如：htjjzzd20181218001")
	private String no;
	@Column(type = Types.BIT,nullable = false,name = "is_take_delivery",displayName = "是否由安装人员提货：true - 安装人员将货提走；false - 安排其他方式提货")
	private Boolean takeDelivery;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "order_id",displayName = "订单id")
	private String orderId;
	@Column(type = Types.VARCHAR,length = 500,name = "notes",displayName = "施工说明")
	private String notes;
	@Column(type = Types.VARCHAR,length = 50,name = "consignee",displayName = "提货人：姓名（例如：李四13566555555）")
	private String consignee;
	@Column(type = TypesExtend.DATETIME,name = "pickup_time",displayName = "提货时间")
	private Date pickupTime;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "")
	private String companyId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "dispatch_bill_id",displayName = "配送单Id")
	private String dispatchBillId;
	@Column(type = Types.CHAR,length = 13,name = "auditor",displayName = "提货审核人")
	private String auditor;
	@Column(type = TypesExtend.DATETIME,name = "except_time",displayName = "预计施工时间")
	private Date exceptTime;

    public DispatchList() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 100) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.erector) > 13) {
			validResult.put("erector", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.address) > 50) {
			validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.no == null) {
			validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.no) > 50) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.takeDelivery == null) {
			validResult.put("takeDelivery", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.orderId == null) {
			validResult.put("orderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.orderId) > 13) {
				validResult.put("orderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 500) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.consignee) > 50) {
			validResult.put("consignee", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.dispatchBillId == null) {
			validResult.put("dispatchBillId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.dispatchBillId) > 13) {
				validResult.put("dispatchBillId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.auditor) > 13) {
			validResult.put("auditor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","name","erector","constructionTime","address","creator","created","status","no","takeDelivery","orderId","notes","consignee","pickupTime","companyId","dispatchBillId","auditor","exceptTime");

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
		if(map.containsKey("constructionTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("constructionTime",String.class))) {
				validResult.put("constructionTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("takeDelivery")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("takeDelivery",String.class))) {
				validResult.put("takeDelivery", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("pickupTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("pickupTime",String.class))) {
				validResult.put("pickupTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("exceptTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("exceptTime",String.class))) {
				validResult.put("exceptTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 100) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("erector")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("erector",String.class)) > 13) {
				validResult.put("erector", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 50) {
				validResult.put("address", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("no")) {
			if (map.getTypedValue("no",String.class)  == null) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 50) {
					validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("takeDelivery")) {
			if (map.get("takeDelivery")  == null) {
				validResult.put("takeDelivery", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 500) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("consignee")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("consignee",String.class)) > 50) {
				validResult.put("consignee", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("companyId")) {
			if (map.getTypedValue("companyId",String.class)  == null) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId",String.class)) > 13) {
					validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
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
		if(map.containsKey("auditor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("auditor",String.class)) > 13) {
				validResult.put("auditor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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

	public void setErector(String erector){
		this.erector=erector;
	}

	public String getErector(){
		return erector;
	}

	public void setConstructionTime(Date constructionTime){
		this.constructionTime=constructionTime;
	}

	public Date getConstructionTime(){
		return constructionTime;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
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

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public void setTakeDelivery(Boolean takeDelivery){
		this.takeDelivery=takeDelivery;
	}

	public Boolean getTakeDelivery(){
		return takeDelivery;
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

	public void setConsignee(String consignee){
		this.consignee=consignee;
	}

	public String getConsignee(){
		return consignee;
	}

	public void setPickupTime(Date pickupTime){
		this.pickupTime=pickupTime;
	}

	public Date getPickupTime(){
		return pickupTime;
	}

	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setDispatchBillId(String dispatchBillId){
		this.dispatchBillId=dispatchBillId;
	}

	public String getDispatchBillId(){
		return dispatchBillId;
	}

	public void setAuditor(String auditor){
		this.auditor=auditor;
	}

	public String getAuditor(){
		return auditor;
	}

	public void setExceptTime(Date exceptTime){
		this.exceptTime=exceptTime;
	}

	public Date getExceptTime(){
		return exceptTime;
	}
}
