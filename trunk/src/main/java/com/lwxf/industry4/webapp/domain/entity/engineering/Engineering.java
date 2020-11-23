package com.lwxf.industry4.webapp.domain.entity.engineering;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：engineering 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-05-28 09:30
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="engineering对象", description="engineering")
@Table(name = "engineering",displayName = "engineering")
public class Engineering extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业ID")
	@ApiModelProperty(value = "企业ID")
	private String branchId;
	@Column(type = Types.CHAR,length = 13,name = "company_id",displayName = "经销商id")
	@ApiModelProperty(value = "经销商id")
	private String companyId;
	@Column(type = Types.CHAR,length = 0,name = "custom_id",displayName = "终端客户id（引用company_customer表id）")
	@ApiModelProperty(value = "终端客户id（引用company_customer表id）")
	private String customId;
	@Column(type = Types.VARCHAR,length = 50,name = "no",displayName = "工程单编号")
	@ApiModelProperty(value = "工程单编号")
	private String no;
	@Column(type = Types.VARCHAR,length = 50,name = "consignee_name",displayName = "收货人姓名")
	@ApiModelProperty(value = "收货人姓名")
	private String consigneeName;
	@Column(type = Types.VARCHAR,length = 20,name = "consignee_tel",displayName = "收货人电话")
	@ApiModelProperty(value = "收货人电话")
	private String consigneeTel;
	@Column(type = Types.CHAR,length = 13,name = "city_area_id",displayName = "收货地址省市区")
	@ApiModelProperty(value = "收货地址省市区")
	private String cityAreaId;
	@Column(type = Types.VARCHAR,length = 255,name = "address",displayName = "详细地址")
	@ApiModelProperty(value = "详细地址")
	private String address;
	@Column(type = TypesExtend.DATETIME,name = "place_order_time",displayName = "下单时间")
	@ApiModelProperty(value = "下单时间")
	private Date placeOrderTime;
	@Column(type = Types.CHAR,length = 13,name = "place_order",displayName = "下单人")
	@ApiModelProperty(value = "下单人")
	private String placeOrder;
	@Column(type = Types.CHAR,length = 13,name = "receiver",displayName = "接单人")
	@ApiModelProperty(value = "接单人")
	private String receiver;
	@Column(type = Types.CHAR,length = 13,name = "merchandiser",displayName = "跟单人")
	@ApiModelProperty(value = "跟单人")
	private String merchandiser;
	@Column(type = Types.VARCHAR,length = 255,name = "notes",displayName = "工程单备注")
	@ApiModelProperty(value = "工程单备注")
	private String notes;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = Types.INTEGER,name = "total_num",displayName = "订单总数量")
	@ApiModelProperty(value = "订单总数量")
	private Integer totalNum;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "total_amount",displayName = "订单总金额")
	@ApiModelProperty(value = "订单总金额")
	private BigDecimal totalAmount;
	@Column(type = Types.INTEGER,name = "del_flag",displayName = "删除标识 0-正常 1-已删除")
	@ApiModelProperty(value = "删除标识 0-正常 1-已删除")
	private Integer delFlag;
	public Engineering() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
			validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
			validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.customId) > 0) {
			validResult.put("customId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.no) > 50) {
			validResult.put("no", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.consigneeName) > 50) {
			validResult.put("consigneeName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.consigneeTel) > 20) {
			validResult.put("consigneeTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
			validResult.put("cityAreaId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.address) > 255) {
			validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.placeOrder) > 13) {
			validResult.put("placeOrder", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.receiver) > 13) {
			validResult.put("receiver", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.merchandiser) > 13) {
			validResult.put("merchandiser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 255) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.creator) > 13) {
			validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","branchId","companyId","customId","no","consigneeName","consigneeTel","cityAreaId","address","placeOrderTime","placeOrder","receiver","merchandiser","notes","created","creator","totalNum","totalAmount");

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
		if(map.containsKey("placeOrderTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("placeOrderTime",String.class))) {
				validResult.put("placeOrderTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("totalNum")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("totalNum",String.class))) {
				validResult.put("totalNum", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("totalAmount")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("totalAmount",String.class))) {
				validResult.put("totalAmount", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("branchId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("branchId",String.class)) > 13) {
				validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("companyId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId",String.class)) > 13) {
				validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("customId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("customId",String.class)) > 0) {
				validResult.put("customId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("no")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 50) {
				validResult.put("no", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("consigneeName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("consigneeName",String.class)) > 50) {
				validResult.put("consigneeName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("consigneeTel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("consigneeTel",String.class)) > 20) {
				validResult.put("consigneeTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("cityAreaId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cityAreaId",String.class)) > 13) {
				validResult.put("cityAreaId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("address")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 255) {
				validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("placeOrder")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("placeOrder",String.class)) > 13) {
				validResult.put("placeOrder", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("receiver")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("receiver",String.class)) > 13) {
				validResult.put("receiver", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("merchandiser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("merchandiser",String.class)) > 13) {
				validResult.put("merchandiser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 255) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("creator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setBranchId(String branchId){
		this.branchId=branchId;
	}

	public String getBranchId(){
		return branchId;
	}

	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setCustomId(String customId){
		this.customId=customId;
	}

	public String getCustomId(){
		return customId;
	}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public void setConsigneeName(String consigneeName){
		this.consigneeName=consigneeName;
	}

	public String getConsigneeName(){
		return consigneeName;
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

	public void setPlaceOrderTime(Date placeOrderTime){
		this.placeOrderTime=placeOrderTime;
	}

	public Date getPlaceOrderTime(){
		return placeOrderTime;
	}

	public void setPlaceOrder(String placeOrder){
		this.placeOrder=placeOrder;
	}

	public String getPlaceOrder(){
		return placeOrder;
	}

	public void setReceiver(String receiver){
		this.receiver=receiver;
	}

	public String getReceiver(){
		return receiver;
	}

	public void setMerchandiser(String merchandiser){
		this.merchandiser=merchandiser;
	}

	public String getMerchandiser(){
		return merchandiser;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setTotalNum(Integer totalNum){
		this.totalNum=totalNum;
	}

	public Integer getTotalNum(){
		return totalNum;
	}

	public void setTotalAmount(BigDecimal totalAmount){
		this.totalAmount=totalAmount;
	}

	public BigDecimal getTotalAmount(){
		return totalAmount;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}
}
