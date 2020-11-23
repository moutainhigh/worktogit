package com.lwxf.industry4.webapp.domain.entity.procurement;
import java.math.BigDecimal;
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
 * 功能：purchase 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-28 10:20
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "purchase",displayName = "purchase")
public class Purchase extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "name",displayName = "采购名称")
	private String name;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "采购单状态：0 - 待审批；1 - 待采购；2 - 采购中；3 - 待质检；4 - 全部合格；5 - 部分合格；6 - 全部不合格；7 - 全部入库;8-部分入库;9- 取消")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "buyer",displayName = "采购人")
	private String buyer;
	@Column(type = TypesExtend.DATETIME,updatable = false,name = "purchase_time",displayName = "采购时间")
	private Date purchaseTime;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "supplier_id",displayName = "供应商的公司id")
	private String supplierId;
	@Column(type = Types.VARCHAR,length = 500,name = "notes",displayName = "描述")
	private String notes;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.VARCHAR,length = 200,name = "purchase_request_no",displayName = "存储采购申请单的编号：可存多个编号，用英文的逗号分隔")
	private String purchaseRequestNo;
	@Column(type = Types.BIT,nullable = false,name = "is_paid",displayName = "是否已付款：true - 已付款；false - 未付款，默认false")
	private Boolean paid;
	@Column(type = Types.DECIMAL,precision = 20,scale=2,nullable = false,name = "total_money",displayName = "总钱数：默认为0，根据采购单下产品计算出总价写入改字段")
	private BigDecimal totalMoney;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "batch",displayName = "采购批次：可由程序自定生成，借鉴订单编号的生成")
	private String batch;
	@Column(type = Types.VARCHAR,length = 200,name = "remarks",displayName = "备注：用于备注质检结果或其他审批信息")
	private String remarks;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "storage_id",displayName = "仓库主键ID")
	private String storageId;

	public Purchase() {
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
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.buyer == null) {
			validResult.put("buyer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.buyer) > 13) {
				validResult.put("buyer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.supplierId == null) {
			validResult.put("supplierId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.supplierId) > 13) {
				validResult.put("supplierId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 500) {
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
		if (LwxfStringUtils.getStringLength(this.purchaseRequestNo) > 200) {
			validResult.put("purchaseRequestNo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.paid == null) {
			validResult.put("paid", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.batch == null) {
			validResult.put("batch", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.batch) > 50) {
				validResult.put("batch", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.remarks) > 200) {
			validResult.put("remarks", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.storageId == null) {
			validResult.put("storageId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.storageId) > 13) {
				validResult.put("storageId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","status","notes","purchaseRequestNo","paid","totalMoney","remarks");

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
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("paid")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("paid",String.class))) {
				validResult.put("paid", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("totalMoney")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("totalMoney",String.class))) {
				validResult.put("totalMoney", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
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
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 500) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("purchaseRequestNo")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("purchaseRequestNo",String.class)) > 200) {
				validResult.put("purchaseRequestNo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("paid")) {
			if (map.get("paid")  == null) {
				validResult.put("paid", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("remarks")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("remarks",String.class)) > 200) {
				validResult.put("remarks", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setBuyer(String buyer){
		this.buyer=buyer;
	}

	public String getBuyer(){
		return buyer;
	}

	public void setPurchaseTime(Date purchaseTime){
		this.purchaseTime=purchaseTime;
	}

	public Date getPurchaseTime(){
		return purchaseTime;
	}

	public void setSupplierId(String supplierId){
		this.supplierId=supplierId;
	}

	public String getSupplierId(){
		return supplierId;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
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

	public void setPurchaseRequestNo(String purchaseRequestNo){
		this.purchaseRequestNo=purchaseRequestNo;
	}

	public String getPurchaseRequestNo(){
		return purchaseRequestNo;
	}

	public void setPaid(Boolean paid){
		this.paid=paid;
	}

	public Boolean getPaid(){
		return paid;
	}

	public void setTotalMoney(BigDecimal totalMoney){
		this.totalMoney=totalMoney;
	}

	public BigDecimal getTotalMoney(){
		return totalMoney;
	}

	public void setBatch(String batch){
		this.batch=batch;
	}

	public String getBatch(){
		return batch;
	}

	public void setRemarks(String remarks){
		this.remarks=remarks;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setStorageId(String storageId){
		this.storageId=storageId;
	}

	public String getStorageId(){
		return storageId;
	}
}
