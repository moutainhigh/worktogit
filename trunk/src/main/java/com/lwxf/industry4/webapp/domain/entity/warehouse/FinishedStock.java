package com.lwxf.industry4.webapp.domain.entity.warehouse;
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
 * 功能：finished_stock 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 10:52 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "finished_stock",displayName = "finished_stock")
public class FinishedStock extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "storage_id",displayName = "")
	private String storageId;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "order_id",displayName = "")
	private String orderId;
	@Column(type = Types.INTEGER,nullable = false,name = "packages",displayName = "该订单包装的总包数")
	private Integer packages;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 未配送；1 - 部分配送；2 - 全部配送")
	private Integer status;
	@Column(type = Types.VARCHAR,length = 300,name = "notes",displayName = "库存描述")
	private String notes;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人（入库人）：默认为系统创建，可由人工手动创建")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间（入库时间）")
	private Date created;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "order_no",displayName = "订单编号：来自订单，不可修改")
	private String orderNo;
	@Column(type = Types.TINYINT,nullable = false,name = "way",displayName = "入库方式：0 - 系统自动入库；1 - 人工手动入库")
	private Integer way;
	@Column(type = Types.VARCHAR,length = 20,name = "flag",displayName = "标记：用于描述该订单包含什么，其值为A、B、C的组合（柜体、门板、五金）")
	private String flag;
	private String branchId;
	@Column(type = Types.TINYINT,name = "resource_type",displayName = "0:订单,1:售后单")
	@ApiModelProperty(value = "0:订单,1:售后单")
	private Integer resourceType;

    public FinishedStock() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.storageId == null) {
			validResult.put("storageId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.storageId) > 13) {
				validResult.put("storageId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.orderId == null) {
			validResult.put("orderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.orderId) > 13) {
				validResult.put("orderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.packages == null||this.packages==0) {
			validResult.put("packages", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 300) {
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
		if (this.resourceType == null) {
			validResult.put("resourceType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.orderNo == null) {
			validResult.put("orderNo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.orderNo) > 50) {
				validResult.put("orderNo", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.way == null) {
			validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.flag) > 20) {
			validResult.put("flag", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("packages","status","notes","way","flag");

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
		if(map.containsKey("packages")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("packages",String.class))) {
				validResult.put("packages", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("way")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("way",String.class))) {
				validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("packages")) {
			if (map.get("packages")  == null) {
				validResult.put("packages", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 300) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("way")) {
			if (map.get("way")  == null) {
				validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("flag")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("flag",String.class)) > 20) {
				validResult.put("flag", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setStorageId(String storageId){
		this.storageId=storageId;
	}

	public String getStorageId(){
		return storageId;
	}

	public void setOrderId(String orderId){
		this.orderId=orderId;
	}

	public String getOrderId(){
		return orderId;
	}

	public void setPackages(Integer packages){
		this.packages=packages;
	}

	public Integer getPackages(){
		return packages;
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

	public void setOrderNo(String orderNo){
		this.orderNo=orderNo;
	}

	public String getOrderNo(){
		return orderNo;
	}

	public void setWay(Integer way){
		this.way=way;
	}

	public Integer getWay(){
		return way;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}

	public String getFlag(){
		return flag;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}
}
