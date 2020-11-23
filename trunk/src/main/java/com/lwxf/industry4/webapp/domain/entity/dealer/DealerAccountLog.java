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
 * 功能：dealer_account_log 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-24 06:21 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@Table(name = "dealer_account_log",displayName = "dealer_account_log")
public class DealerAccountLog extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "dealer_account_id",displayName = "经销商财务账号")
	private String dealerAccountId;
	@Column(type = Types.VARCHAR,length = 200,nullable = false,name = "content",displayName = "交易内容：比如 订单扣款：订单编号为xxx，订单客户为xxx，客户电话为xxx，一般情况下由系统生成")
	private String content;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "交易类型：0 - 线下充值；1 - 线上充值；2 - 订单扣款；3 - 设计扣款；4 - 提现；5 - 转入；6 - 转出；")
	private Integer type;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "操作人或创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "交易时间或创建时间")
	private Date created;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "amount",displayName = "交易金额")
	private BigDecimal amount;
	@Column(type = Types.VARCHAR,length = 100,name = "notes",displayName = "备注/说明：可以备注交易源，如订单编号、获益方姓名，或其他说明，一般可不填")
	private String notes;
	@Column(type = Types.BIT,nullable = false,name = "is_disburse",displayName = "是否支出：true - 支出；false - 支入，参与账号计算的标记，如果是支出则按减法算，如果是支入则按加法算")
	private Boolean disburse;
	@Column(type = Types.CHAR,length = 13,name = "resource_id",displayName = "资源id，有支付产生的账户日志，关联到支付表的id，如果是订单和订单设计产生的扣款，关联订单的id")
	private String resourceId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "公司id")
	private String companyId;

	public DealerAccountLog() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.dealerAccountId == null) {
			validResult.put("dealerAccountId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.dealerAccountId) > 13) {
				validResult.put("dealerAccountId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.content == null) {
			validResult.put("content", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.content) > 200) {
				validResult.put("content", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
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
		if (LwxfStringUtils.getStringLength(this.notes) > 100) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.disburse == null) {
			validResult.put("disburse", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.resourceId) > 13) {
			validResult.put("paymentId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","dealerAccountId","content","type","creator","created","amount","notes","disburse","resourceId","companyId");

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
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("amount")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount",String.class))) {
				validResult.put("amount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("disburse")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("disburse",String.class))) {
				validResult.put("disburse", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
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
		if(map.containsKey("companyId")) {
			if (map.getTypedValue("companyId",String.class)  == null) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId",String.class)) > 13) {
					validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("content")) {
			if (map.getTypedValue("content",String.class)  == null) {
				validResult.put("content", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("content",String.class)) > 200) {
					validResult.put("content", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
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
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 100) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("disburse")) {
			if (map.get("disburse")  == null) {
				validResult.put("disburse", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("resourceId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("resourceId",String.class)) > 13) {
				validResult.put("resourceId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setDealerAccountId(String dealerAccountId){
		this.dealerAccountId=dealerAccountId;
	}

	public String getDealerAccountId(){
		return dealerAccountId;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
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

	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}

	public BigDecimal getAmount(){
		return amount;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setDisburse(Boolean disburse){
		this.disburse=disburse;
	}

	public Boolean getDisburse(){
		return disburse;
	}

	public void setResourceId(String resourceId){
		this.resourceId=resourceId;
	}

	public String getResourceId(){
		return resourceId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
