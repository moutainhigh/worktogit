package com.lwxf.industry4.webapp.domain.entity.aftersale;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：aftersale_apply 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-02-20 03:42
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="售后管理",description="售后管理")
@Table(name = "aftersale_apply",displayName = "aftersale_apply")
public class AftersaleApply extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="源订单id",name="custom_order_id",required=true,example = "")
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "custom_order_id",displayName = "订单id")
	private String customOrderId;
	@ApiModelProperty(value="类型：0 - 漏发补货；1 - 损坏补货；2 - 错发调货；3 - 其他；4-反馈单;5-补料单;",name="type",example = "")
	@Column(type = Types.TINYINT,name = "type",displayName = "类型：0 - 漏发补货；1 - 损坏补货；2 - 错发调货；3 - 其他；4-反馈单;5-补料单;")
	private Integer type;
	@ApiModelProperty(value="售后申请说明;",name="notes",example = "")
	@Column(type = Types.VARCHAR,length = 500,nullable = false,name = "notes",displayName = "售后申请说明")
	private String notes;
	@ApiModelProperty(value="经销商的公司id;",name="notes",example = "")
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "companyId",displayName = "经销商的公司id")
	private String companyId;
	@ApiModelProperty(value="状态：0-待处理,1 - 待执行；2 - 拒绝售后申请；3 - 已完成；默认0",required=true,name="status",example = "")
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0-待处理,1 - 待执行；2 - 拒绝售后申请；3 - 已完成；默认0")
	private Integer status;
	@ApiModelProperty(value="审核人：用户id，一般为厂家的售后服务人员的",name="checker",example = "")
	@Column(type = Types.CHAR,length = 13,name = "checker",displayName = "审核人：用户id，一般为厂家的售后服务人员的")
	private String checker;
	@ApiModelProperty(value="审核时间",name="checker",example = "")
	@Column(type = TypesExtend.DATETIME,name = "checkTime",displayName = "审核时间")
	private Date checkTime;
	@ApiModelProperty(value="处理结果：一般由售后人员填写，比如仅是配送，填写物流信息，如果是需要生产，则填订单编号（订单相关信息）",name="result",example = "")
	@Column(type = Types.VARCHAR,length = 500,name = "result",displayName = "处理结果：一般由售后人员填写，比如仅是配送，填写物流信息，如果是需要生产，则填订单编号（订单相关信息）")
	private String result;
	@ApiModelProperty(value="创建人（申请人）",name="creator",example = "")
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人（申请人）")
	private String creator;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间（申请时间）")
	private Date created;
	@ApiModelProperty(value="售后单编号",name="no",example = "")
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "no",displayName = "售后单编号")
	private String no;
	@ApiModelProperty(value="相关信息",name="information",example = "")
	@Column(type = Types.VARCHAR,length = 500,name = "information",displayName = "相关信息")
	private String information;
	@ApiModelProperty(value="相关信息",name="reason",example = "")
	@Column(type = Types.VARCHAR,length = 200,name = "reason",displayName = "拒绝缘由")
	private String reason;
	@ApiModelProperty(value="处理结果相关联的订单ID",name="resultOrderId",example = "")
	@Column(type = Types.CHAR,length = 13,name = "result_order_id",displayName = "处理结果相关联的订单ID")
	private String resultOrderId;
	@ApiModelProperty(value="是否收费：false - 免费；true - 收费；",name="isCharge",example = "")
	@Column(type = Types.BIT, nullable = false, name = "is_charge", displayName = "是否收费：false - 免费；true - 收费；")
	private Boolean isCharge;
	@ApiModelProperty(value="收费金额,默认为0",name="chargeAmount",example = "")
	@Column(type = Types.DECIMAL, precision = 10, scale = 2, nullable = false, name = "charge_amount", displayName = "收费金额,默认为0")
	private BigDecimal chargeAmount;
	@ApiModelProperty(value="实收金额,默认为0",name="chargeAmount",example = "")
	@Column(type = Types.DECIMAL, precision = 10, scale = 2, nullable = false, name = "pay_amount", displayName = "实收金额,默认为0")
	private BigDecimal payAmount;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	private String branchId;
	@Column(type = Types.CHAR,length = 13,name = "payment_id",displayName = "支付信息id")
	private String paymentId;
	@ApiModelProperty(value="客户名称",name="customerName",example = "")
	@Column(type = Types.CHAR,length = 13,name = "customer_name",displayName = "客户名称")
	private String customerName;
	@ApiModelProperty(value="收货人名称",name="consigneeName",example = "")
	@Column(type = Types.CHAR,length = 50,name = "consignee_name",displayName = "收货人名称")
	private String consigneeName;
	@ApiModelProperty(value="收货人电话",name="consigneeTel",example = "")
	@Column(type = Types.CHAR,length = 20,name = "consignee_tel",displayName = "收货人电话")
	private String consigneeTel;
	@ApiModelProperty(value="收货地址",name="consigneeAddress",example = "")
	@Column(type = Types.CHAR,length = 100,name = "consignee_address",displayName = "收货地址")
	private String consigneeAddress;
	@ApiModelProperty(value="收货地区",name="consigneeCityId",example = "")
	@Column(type = Types.CHAR,length = 13,name = "consignee_city_id",displayName = "收货地区")
	private String consigneeCityId;
	@Column(type = Types.BIT, name = "is_coordination", displayName = "是否需要外协: 0 - 不需要 ; 1 - 需要")
	@ApiModelProperty(value = "是否需要外协: 0 - 不需要 ; 1 - 需要")
	private Boolean coordination;
	@ApiModelProperty(value="来源类型：0 - 经销商；1 - 工厂；",name="fromType",example = "")
	@Column(type = Types.TINYINT,nullable = false, name = "from_type",displayName = "来源类型：0 - 经销商；1 - 工厂；")
	private Integer fromType;
	@ApiModelProperty(value="完成时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@Column(type = TypesExtend.DATETIME,name = "finish_date",displayName = "完成时间（完成时间）")
	private Date finishDate;
	@ApiModelProperty(value="需要售后的产品id",name="orderProductId")
	@Column(type = Types.CHAR,length = 13,name = "order_product_id",displayName = "需要售后的产品id")
	private String orderProductId;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	@ApiModelProperty(value="图片附件，多个用逗号间隔)",name="fileIds")
	private String fileIds;

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public BigDecimal getPayAmount() {
		return payAmount;
	}

	public void setPayAmount(BigDecimal payAmount) {
		this.payAmount = payAmount;
	}

	public String getFileIds() {
		return fileIds;
	}

	public void setFileIds(String fileIds) {
		this.fileIds = fileIds;
	}

	public AftersaleApply() {
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
		if (this.branchId == null) {
			validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
				validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.notes == null) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.notes) > 500) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.checker) > 13) {
			validResult.put("checker", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.result) > 500) {
			validResult.put("result", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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
		if (this.no == null) {
			validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.no) > 50) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.information) > 500) {
			validResult.put("information", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.reason) > 200) {
			validResult.put("reason", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.resultOrderId) > 13) {
			validResult.put("resultOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.isCharge == null) {
			validResult.put("isCharge", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.chargeAmount == null) {
			validResult.put("chargeAmount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.fromType == null) {
			validResult.put("fromType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("type","notes","status","checker","checkTime","result","information","reason","resultOrderId","is_charge","charge_amount");

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
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("checkTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("checkTime",String.class))) {
				validResult.put("checkTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("notes")) {
			if (map.getTypedValue("notes",String.class)  == null) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 500) {
					validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("checker")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("checker",String.class)) > 13) {
				validResult.put("checker", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("result")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("result",String.class)) > 500) {
				validResult.put("result", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("information")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("information",String.class)) > 500) {
				validResult.put("information", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("reason")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reason",String.class)) > 200) {
				validResult.put("reason", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("resultOrderId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("resultOrderId",String.class)) > 13) {
				validResult.put("resultOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("charge")) {
			if (map.get("charge")  == null) {
				validResult.put("charge", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("chargeAmount")) {
			if (map.get("chargeAmount")  == null) {
				validResult.put("chargeAmount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("fromType")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("fromType",String.class))) {
				validResult.put("fromType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("finishDate")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("finishDate",String.class))) {
				validResult.put("finishDate", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public String getBranchId() {return branchId;}

	public void setBranchId(String branchId) {this.branchId = branchId;}

	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setChecker(String checker){
		this.checker=checker;
	}

	public String getChecker(){
		return checker;
	}

	public void setCheckTime(Date checkTime){
		this.checkTime=checkTime;
	}

	public Date getCheckTime(){
		return checkTime;
	}

	public void setResult(String result){
		this.result=result;
	}

	public String getResult(){
		return result;
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

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public void setInformation(String information){
		this.information=information;
	}

	public String getInformation(){
		return information;
	}

	public void setReason(String reason){
		this.reason=reason;
	}

	public String getReason(){
		return reason;
	}

	public void setResultOrderId(String resultOrderId){
		this.resultOrderId=resultOrderId;
	}

	public String getResultOrderId(){
		return resultOrderId;
	}

	public Boolean getCharge() {
		return isCharge;
	}

	public void setCharge(Boolean charge) {
		isCharge = charge;
	}

	public BigDecimal getChargeAmount() {
		return chargeAmount;
	}

	public void setChargeAmount(BigDecimal chargeAmount) {
		this.chargeAmount = chargeAmount;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public String getConsigneeCityId() {
		return consigneeCityId;
	}

	public void setConsigneeCityId(String consigneeCityId) {
		this.consigneeCityId = consigneeCityId;
	}

	public Boolean getCoordination() {
		return coordination;
	}

	public void setCoordination(Boolean coordination) {
		this.coordination = coordination;
	}

	public Integer getFromType() {
		return fromType;
	}

	public void setFromType(Integer fromType) {
		this.fromType = fromType;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public String getOrderProductId() {
		return orderProductId;
	}

	public void setOrderProductId(String orderProductId) {
		this.orderProductId = orderProductId;
	}
}
