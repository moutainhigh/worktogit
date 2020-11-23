package com.lwxf.industry4.webapp.domain.entity.dispatch;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;
/**
 * 功能：dispatch_bill 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-27 10:31
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "dispatch_bill",displayName = "dispatch_bill")
@ApiModel(value = "配送单信息",discriminator = "配送单信息")
public class DispatchBill extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 50,name = "no",displayName = "发货单编号")
	@ApiModelProperty(value = "发货单编号")
	private String no;
	@Column(type = Types.DATE,nullable = false,name = "plan_date",displayName = "计划发货日期：默认为订单的预发货日期，可修改")
	@ApiModelProperty(value = "计划发货日期")
	private Date planDate;
	@Column(type = TypesExtend.DATETIME,name = "actual_date",displayName = "实际发货日期")
	@ApiModelProperty(value = "实际发货日期")
	private Date actualDate;
	@Column(type = Types.VARCHAR,nullable = false,length = 30,name = "consignee",displayName = "收货人")
	@ApiModelProperty(value = "收货人")
	private String consignee;
	@Column(type = Types.VARCHAR,nullable = false,length = 20,name = "consignee_tel",displayName = "收货人电话")
	@ApiModelProperty(value = "收货人电话")
	private String consigneeTel;
	@Column(type = Types.CHAR,length = 13,name = "city_area_id",displayName = "收货地址所在城市")
	@ApiModelProperty(value = "收货地址所在城市")
	private String cityAreaId;
	@Column(type = Types.VARCHAR,length = 160,nullable = false,name = "address",displayName = "收货地址")
	@ApiModelProperty(value = "收货地址")
	private String address;
	@Column(type = Types.VARCHAR,length = 100,name = "logistics_company_id",displayName = "物流公司id")
	@ApiModelProperty(value = "物流公司id")
	private String logisticsCompanyId;
	@Column(type = Types.VARCHAR,length = 50,name = "logistics_no",displayName = "物流单号")
	@ApiModelProperty(value = "物流单号")
	private String logisticsNo;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 待取件；1 - 运输中；2 - 待取货；3 - 已收货；")
	@ApiModelProperty(value = "状态：0 - 待取件；1 - 运输中；2 - 待取货；3 - 已收货；")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value ="创建时间")
	private Date created;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "发货备注")
	@ApiModelProperty(value = "发货备注")
	private String notes;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "deliverer",displayName = "发货人")
	@ApiModelProperty(value = "发货人")
	private String deliverer;
	@Column(type = Types.CHAR,length = 11,name = "deliverer_tel",displayName = "发货人电话")
	@ApiModelProperty(value = "发货人电话")
	private String delivererTel;
	private String branchId;
	private Integer count;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "经销商id")
	@ApiModelProperty(value = "经销商id")
	private String companyId;
	@Column(type = Types.INTEGER,length = 2,nullable = false,name = "freight_type",displayName = "运费类型 0-包邮 1-到付")
	@ApiModelProperty(value = "运费类型 0-包邮 1-到付")
	private Integer freightType;
	@Column(type = Types.DECIMAL, precision = 10, scale = 2, nullable = false, name = "freight", displayName = "运费：默认0")
	@ApiModelProperty(value = "运费：默认0")
	private BigDecimal freight;
	@Column(type = Types.INTEGER,length = 2,nullable = false,name = "logistics_mode",displayName = "物流方式 0-配送 1-自提")
	@ApiModelProperty(value = "物流方式 0-配送 1-自提")
	private Integer logisticsMode;
	public DispatchBill() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.no) > 50) {
			validResult.put("no", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.consignee == null) {
			validResult.put("consignee", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.consignee) > 30) {
				validResult.put("consignee", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.count == null) {
			validResult.put("count", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.consigneeTel == null) {
			validResult.put("consigneeTel", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.consigneeTel) > 11) {
				validResult.put("consigneeTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
			validResult.put("cityAreaId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.address == null) {
			validResult.put("address", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.address) > 160) {
				validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.logisticsCompanyId) > 100) {
			validResult.put("logisticsCompanyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.logisticsNo) > 50) {
			validResult.put("logisticsNo", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
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
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.deliverer == null) {
			validResult.put("deliverer", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.deliverer) > 13) {
				validResult.put("deliverer", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.delivererTel) > 11) {
			validResult.put("delivererTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.logisticsNo == null) {
			validResult.put("logisticsNo", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.logisticsCompanyId == null) {
			validResult.put("logisticsCompanyId", ErrorCodes.VALIDATE_NOTNULL);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","no","planDate","actualDate","consignee","consigneeTel","cityAreaId","address","logisticsCompanyId","logisticsNo","status","creator","created","notes","deliverer","delivererTel");

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
		if(map.containsKey("planDate")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("planDate",String.class))) {
				validResult.put("planDate", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("actualDate")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("actualDate",String.class))) {
				validResult.put("actualDate", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("count")) {
			if (!DataValidatorUtils.isInteger(map.getTypedValue("count",String.class))) {
				validResult.put("count", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("no")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 50) {
				validResult.put("no", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("consignee")) {
			if (map.getTypedValue("consignee",String.class)  == null) {
				validResult.put("consignee", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("consignee",String.class)) > 30) {
					validResult.put("consignee", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("consigneeTel")) {
			if (map.getTypedValue("consigneeTel",String.class)  == null) {
				validResult.put("consigneeTel", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("consigneeTel",String.class)) > 11) {
					validResult.put("consigneeTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("cityAreaId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cityAreaId",String.class)) > 13) {
				validResult.put("cityAreaId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("address")) {
			if (map.getTypedValue("address",String.class)  == null) {
				validResult.put("address", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 160) {
					validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("logisticsCompanyId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("logisticsCompanyId",String.class)) > 100) {
				validResult.put("logisticsCompanyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("logisticsNo")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("logisticsNo",String.class)) > 50) {
				validResult.put("logisticsNo", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
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
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("deliverer")) {
			if (map.getTypedValue("deliverer",String.class)  == null) {
				validResult.put("deliverer", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("deliverer",String.class)) > 13) {
					validResult.put("deliverer", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("delivererTel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("delivererTel",String.class)) > 11) {
				validResult.put("delivererTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public void setPlanDate(Date planDate){
		this.planDate=planDate;
	}

	public Date getPlanDate(){
		return planDate;
	}

	public void setActualDate(Date actualDate){
		this.actualDate=actualDate;
	}

	public Date getActualDate(){
		return actualDate;
	}

	public void setConsignee(String consignee){
		this.consignee=consignee;
	}

	public String getConsignee(){
		return consignee;
	}

	public void setConsigneeTel(String consigneeTel){
		this.consigneeTel=consigneeTel;
	}

	public String getConsigneeTel(){
		return consigneeTel;
	}

	public void setCityAreaId(String cityAreaId){
		this.cityAreaId=cityAreaId;
	}

	public String getCityAreaId(){
		return cityAreaId;
	}

	public void setAddress(String address){
		this.address=address;
	}

	public String getAddress(){
		return address;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId){
		this.logisticsCompanyId=logisticsCompanyId;
	}

	public String getLogisticsCompanyId(){
		return logisticsCompanyId;
	}

	public void setLogisticsNo(String logisticsNo){
		this.logisticsNo=logisticsNo;
	}

	public String getLogisticsNo(){
		return logisticsNo;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
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

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setDeliverer(String deliverer){
		this.deliverer=deliverer;
	}

	public String getDeliverer(){
		return deliverer;
	}

	public void setDelivererTel(String delivererTel){
		this.delivererTel=delivererTel;
	}

	public String getDelivererTel(){
		return delivererTel;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public Integer getFreightType() {
		return freightType;
	}

	public void setFreightType(Integer freightType) {
		this.freightType = freightType;
	}

	public BigDecimal getFreight() {
		return freight;
	}

	public void setFreight(BigDecimal freight) {
		this.freight = freight;
	}

	public Integer getLogisticsMode() {
		return logisticsMode;
	}

	public void setLogisticsMode(Integer logisticsMode) {
		this.logisticsMode = logisticsMode;
	}
}
