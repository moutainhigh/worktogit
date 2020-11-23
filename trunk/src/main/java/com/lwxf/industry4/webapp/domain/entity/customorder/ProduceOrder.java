package com.lwxf.industry4.webapp.domain.entity.customorder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：produce_order 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-04-12 01:29
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "produce_order",displayName = "produce_order")
@ApiModel(value = "生产拆单信息",discriminator = "生产拆单信息")
public class ProduceOrder extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "custom_order_id",displayName = "订单主键ID")
	@ApiModelProperty(value = "订单主键ID")
	private String customOrderId;
	@Column(type = Types.VARCHAR,length = 50,updatable = false,name = "custom_order_no",displayName = "订单编号")
	@ApiModelProperty(value = "订单编号")
	private String customOrderNo;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "no",displayName = "生产拆单明细编号")
	@ApiModelProperty(value = "生产拆单明细编号")
	private String no;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "update_user",displayName = "修改人")
	@ApiModelProperty(value = "修改人")
	private String updateUser;
	@Column(type = TypesExtend.DATETIME,name = "update_time",displayName = "修改时间")
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	@Column(type = TypesExtend.DATETIME,name = "completion_time",displayName = "完成时间")
	@ApiModelProperty(value = "完成时间")
	private Date completionTime;
	@Column(type = Types.INTEGER,nullable = false,name = "count",displayName = "数量")
	@ApiModelProperty(value = "数量")
	private Integer count;
	@Column(type = Types.VARCHAR,length = 300,name = "notes",displayName = "说明")
	@ApiModelProperty(value = "说明")
	private String notes;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "amount",displayName = "金额 是外协单时 才存在")
	@ApiModelProperty(value = "金额 是外协单时 才存在")
	private BigDecimal amount;
	@Column(type = Types.VARCHAR,length = 50,name = "coordination_name",displayName = "外协厂家名称")
	@ApiModelProperty(value = "外协厂家名称")
	private String coordinationName;
	@Column(type = Types.VARCHAR,length = 50,name = "coordination_account",displayName = "外协厂家账户")
	@ApiModelProperty(value = "外协厂家账户")
	private String coordinationAccount;
	@Column(type = Types.VARCHAR,length = 50,name = "coordination_bank",displayName = "外协厂家开户行")
	@ApiModelProperty(value = "外协厂家开户行")
	private String coordinationBank;
	@Column(type = Types.BIT,nullable = false,name = "is_pay",displayName = "是否付款 false 0 未付款 true 1 已付款")
	@ApiModelProperty(value = "是否付款 false 0 未付款 true 1 已付款")
	private Boolean pay;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "生产拆单类型 0 柜体 1 门板 2 五金")
	@ApiModelProperty(value = "生产拆单类型 0 柜体 1 门板 2 五金")
	private Integer type;
	@Column(type = Types.TINYINT,nullable = false,name = "way",displayName = "生产方式 0 自产 1 外协 2 特供实木")
	@ApiModelProperty(value = "生产方式 0 自产 1 外协 2 特供实木")
	private Integer way;
	@Column(type = Types.TINYINT,nullable = false,name = "state",displayName = "生产拆单状态:0 未开始 1 生产中 2 已完成")
	@ApiModelProperty(value = "生产拆单状态  自产:0 未开始 1 生产中 2 已完成   外协:0 待报价 1 待支付 2 待收货 3 完成")
	private Integer state;
	@Column(type = Types.DATE,name = "planned_time",displayName = "计划生产时间")
	@ApiModelProperty(value = "计划生产时间")
	private Date plannedTime;
	@Column(type = Types.DATE,name = "actual_time",displayName = "实际生产时间")
	@ApiModelProperty(value = "实际生产时间")
	private Date actualTime;
	private String branchId;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "order_product_id",displayName = "订单产品ID")
	private String orderProductId;
	@Column(type = Types.TINYINT,name = "permit",displayName = "是否允许生产 0 不允许 1 允许 只使用在自产的生产单")
	@ApiModelProperty(value = "是否允许生产 0 不允许 1 允许 只使用在自产的生产单")
	private Integer permit;
	@Column(type = Types.TINYINT,name = "resource_type",displayName = "0:订单,1:售后单")
	@ApiModelProperty(value = "0:订单,1:售后单")
	private Integer resourceType;
	@Column(type = Types.DATE,name = "plan_created",displayName = "生产计划创建时间")
	@ApiModelProperty(value = "生产计划创建时间")
	private Date planCreated;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "plan_creator",displayName = "生产计划创建人")
	@ApiModelProperty(value = "生产计划创建人")
	private String planCreator;
	@Column(type = Types.TINYINT,length = 2,nullable = false,updatable = false,name = "flag",displayName = "存在状态 0-正常 1-删除")
	@ApiModelProperty(value = "存在状态 0-正常 1-删除")
	private Integer flag;
	@ApiModelProperty(value = "物流公司id")
	@Column(type = Types.CHAR,length = 13,name = "logistics_company_id",displayName = "物流公司id")
	private String logisticsCompanyId;
	@ApiModelProperty(value = "物流单号")
	@Column(type = Types.VARCHAR,length = 50,name = "logistics_no",displayName = "物流单号")
	private String logisticsNo;
	@ApiModelProperty(value = "发货时间")
	@Column(type = TypesExtend.DATETIME,name = "delivered",displayName = "发货时间")
	private Date delivered;
	@ApiModelProperty(value = "发货人")
	@Column(type = Types.CHAR,length = 13,name = "deliverer",displayName = "发货人")
	private String deliverer;
	@ApiModelProperty(value = "是否已发货")
	@Column(type = Types.TINYINT,length = 2,name = "deliver_status",displayName = "是否已发货")
	private Integer deliverStatus;
	@ApiModelProperty(value = "是否创建配送计划")
	@Column(type = Types.TINYINT,length = 2,name = "is_shipped",displayName = "是否创建配送计划")
	private Integer shipped;
	@ApiModelProperty(value = "入库状态 0-部分入库 1-入库完成")
	@Column(type = Types.TINYINT,length = 2,name = "input_status",displayName = "入库状态 0-部分入库 1-入库完成")
	private Integer inputStatus;
	@ApiModelProperty(value = "已发货数量")
	@Column(type = Types.TINYINT,length = 20,name = "has_deliver_count",displayName = "已发货数量")
	private Integer hasDeliverCount;

	public ProduceOrder() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.customOrderId == null) {
			validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
				validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.no == null) {
			validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.no) > 50) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.creator == null) {
			validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.updateUser) > 13) {
			validResult.put("updateUser", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 300) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.coordinationName) > 50) {
			validResult.put("coordinationName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.coordinationAccount) > 50) {
			validResult.put("coordinationAccount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.coordinationBank) > 50) {
			validResult.put("coordinationBank", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.pay == null) {
			validResult.put("pay", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.way == null) {
			validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.state == null) {
			validResult.put("state", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.resourceType == null) {
			validResult.put("resourceType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("updateUser","updateTime","completionTime","count","notes","amount","coordinationName","coordinationAccount","coordinationBank","pay","type","way","state","plannedTime","actualTime","orderProductId","permit","planCreated","planCreator");

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
		if(map.containsKey("updateTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updateTime",String.class))) {
				validResult.put("updateTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("completionTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("completionTime",String.class))) {
				validResult.put("completionTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("count")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("count",String.class))) {
				validResult.put("count", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("amount")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount",String.class))) {
				validResult.put("amount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("pay")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("pay",String.class))) {
				validResult.put("pay", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("way")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("way",String.class))) {
				validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("updateUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("updateUser",String.class)) > 13) {
				validResult.put("updateUser", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 300) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("coordinationName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("coordinationName",String.class)) > 50) {
				validResult.put("coordinationName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("coordinationAccount")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("coordinationAccount",String.class)) > 50) {
				validResult.put("coordinationAccount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("coordinationBank")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("coordinationBank",String.class)) > 50) {
				validResult.put("coordinationBank", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("orderProductId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("orderProductId",String.class)) > 13) {
				validResult.put("orderProductId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("pay")) {
			if (map.get("pay")  == null) {
				validResult.put("pay", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("way")) {
			if (map.get("way")  == null) {
				validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("state")) {
			if (map.get("state")  == null) {
				validResult.put("state", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("plannedTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("plannedTime",String.class))) {
				validResult.put("plannedTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("actualTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("actualTime",String.class))) {
				validResult.put("actualTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setCustomOrderNo(String customOrderNo){
		this.customOrderNo=customOrderNo;
	}

	public String getCustomOrderNo(){
		return customOrderNo;
	}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
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

	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}

	public String getUpdateUser(){
		return updateUser;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setCompletionTime(Date completionTime){
		this.completionTime=completionTime;
	}

	public Date getCompletionTime(){
		return completionTime;
	}

	public void setCount(Integer count){
		this.count=count;
	}

	public Integer getCount(){
		return count;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}

	public BigDecimal getAmount(){
		return amount;
	}

	public void setCoordinationName(String coordinationName){
		this.coordinationName=coordinationName;
	}

	public String getCoordinationName(){
		return coordinationName;
	}

	public void setCoordinationAccount(String coordinationAccount){
		this.coordinationAccount=coordinationAccount;
	}

	public String getCoordinationAccount(){
		return coordinationAccount;
	}

	public void setCoordinationBank(String coordinationBank){
		this.coordinationBank=coordinationBank;
	}

	public String getCoordinationBank(){
		return coordinationBank;
	}

	public void setPay(Boolean pay){
		this.pay=pay;
	}

	public Boolean getPay(){
		return pay;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setWay(Integer way){
		this.way=way;
	}

	public Integer getWay(){
		return way;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Date getPlannedTime() {
		return plannedTime;
	}

	public void setPlannedTime(Date plannedTime) {
		this.plannedTime = plannedTime;
	}

	public Date getActualTime() {
		return actualTime;
	}

	public void setActualTime(Date actualTime) {
		this.actualTime = actualTime;
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

	public Integer getPermit() {
		return permit;
	}

	public void setPermit(Integer permit) {
		this.permit = permit;
	}

	public Integer getResourceType() {
		return resourceType;
	}

	public void setResourceType(Integer resourceType) {
		this.resourceType = resourceType;
	}

	public Date getPlanCreated() {
		return planCreated;
	}

	public void setPlanCreated(Date planCreated) {
		this.planCreated = planCreated;
	}

	public String getPlanCreator() {
		return planCreator;
	}

	public void setPlanCreator(String planCreator) {
		this.planCreator = planCreator;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}

	public String getLogisticsCompanyId() {
		return logisticsCompanyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId) {
		this.logisticsCompanyId = logisticsCompanyId;
	}

	public String getLogisticsNo() {
		return logisticsNo;
	}

	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}

	public Date getDelivered() {
		return delivered;
	}

	public void setDelivered(Date delivered) {
		this.delivered = delivered;
	}

	public String getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(String deliverer) {
		this.deliverer = deliverer;
	}

	public Integer getDeliverStatus() {
		return deliverStatus;
	}

	public void setDeliverStatus(Integer deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public Integer getShipped() {
		return shipped;
	}

	public void setShipped(Integer shipped) {
		this.shipped = shipped;
	}

	public Integer getInputStatus() {
		return inputStatus;
	}

	public void setInputStatus(Integer inputStatus) {
		this.inputStatus = inputStatus;
	}

	public Integer getHasDeliverCount() {
		return hasDeliverCount;
	}

	public void setHasDeliverCount(Integer hasDeliverCount) {
		this.hasDeliverCount = hasDeliverCount;
	}
}
