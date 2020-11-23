package com.lwxf.industry4.webapp.domain.entity.dealer;
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
 * 功能：dealer_account_details 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-24 06:21 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "dealer_account_details",displayName = "dealer_account_details")
public class DealerAccountDetails extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "明细名称：比如2018双十一活动保证金")
	private String name;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "dealer_account_id",displayName = "")
	private String dealerAccountId;
	@Column(type = Types.CHAR,length = 13,name = "payment_id",displayName = "非支付记录引起的账号明细，不用填")
	private String paymentId;
	@Column(type = Types.TINYINT,nullable = false,name = "funds",displayName = "资金款项：0 - 自由资金；1 - 定向预付金；2 - 定时预付金；3 - 保证金；4 - 加盟费；6 - 设计保证金；7 - 活动诚意金；8 - 合作诚意金，其他类型根据需求再添加")
	private Integer funds;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "类型：0 - 无限制（常指自由资金）；1 - 定向可用（只有购买指定东西时可用）；2 - 定时可用（在指定时间可用）；3 - 固定期间可用（常指优惠活动）；4 - 冻结期间不可用")
	private Integer type;
	@Column(type = TypesExtend.DATETIME,name = "start_time",displayName = "开始时间：与type配合使用，不需要设置的不保存任何数据")
	private Date startTime;
	@Column(type = TypesExtend.DATETIME,name = "end_time",displayName = "截至时间：与type结合使用，指设置固定期限的结束时间，不需要时可不填")
	private Date endTime;
	@Column(type = Types.BIT,name = "is_used",displayName = "是否已被使用：false - 还未使用；true - 一般指是否已被抵货款或转入自由账号")
	private Boolean used;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建该明细的操作人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建的日期")
	private Date created;
	@Column(type = TypesExtend.DATETIME,name = "used_time",displayName = "被使用的日期：抵扣货款或转入自由账号或提现的时间")
	private Date usedTime;
	@Column(type = Types.CHAR,length = 13,name = "used_user",displayName = "抵扣货款或转入自由账号或提现的操作人")
	private String usedUser;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "说明：描述信息")
	private String notes;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "amount",displayName = "金额：使用时从相应账号扣除")
	private BigDecimal amount;

    public DealerAccountDetails() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 50) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.dealerAccountId == null) {
			validResult.put("dealerAccountId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.dealerAccountId) > 13) {
				validResult.put("dealerAccountId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.paymentId) > 13) {
			validResult.put("paymentId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.funds == null) {
			validResult.put("funds", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
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
		if (LwxfStringUtils.getStringLength(this.usedUser) > 13) {
			validResult.put("usedUser", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","name","dealerAccountId","paymentId","funds","type","startTime","endTime","used","creator","created","usedTime","usedUser","notes","amount");

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
		if(map.containsKey("funds")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("funds",String.class))) {
				validResult.put("funds", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("startTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("startTime",String.class))) {
				validResult.put("startTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("endTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("endTime",String.class))) {
				validResult.put("endTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("used")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("used",String.class))) {
				validResult.put("used", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("usedTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("usedTime",String.class))) {
				validResult.put("usedTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("amount")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount",String.class))) {
				validResult.put("amount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("dealerAccountId")) {
			if (map.getTypedValue("dealerAccountId",String.class)  == null) {
				validResult.put("dealerAccountId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("dealerAccountId",String.class)) > 13) {
					validResult.put("dealerAccountId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("paymentId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("paymentId",String.class)) > 13) {
				validResult.put("paymentId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("funds")) {
			if (map.get("funds")  == null) {
				validResult.put("funds", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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
		if(map.containsKey("usedUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("usedUser",String.class)) > 13) {
				validResult.put("usedUser", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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

	public void setDealerAccountId(String dealerAccountId){
		this.dealerAccountId=dealerAccountId;
	}

	public String getDealerAccountId(){
		return dealerAccountId;
	}

	public void setPaymentId(String paymentId){
		this.paymentId=paymentId;
	}

	public String getPaymentId(){
		return paymentId;
	}

	public void setFunds(Integer funds){
		this.funds=funds;
	}

	public Integer getFunds(){
		return funds;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}

	public Date getStartTime(){
		return startTime;
	}

	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	public Date getEndTime(){
		return endTime;
	}

	public void setUsed(Boolean used){
		this.used=used;
	}

	public Boolean getUsed(){
		return used;
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

	public void setUsedTime(Date usedTime){
		this.usedTime=usedTime;
	}

	public Date getUsedTime(){
		return usedTime;
	}

	public void setUsedUser(String usedUser){
		this.usedUser=usedUser;
	}

	public String getUsedUser(){
		return usedUser;
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
}
