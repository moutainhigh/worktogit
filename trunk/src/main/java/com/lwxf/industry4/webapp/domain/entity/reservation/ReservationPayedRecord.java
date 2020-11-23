package com.lwxf.industry4.webapp.domain.entity.reservation;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
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
 * 功能：reservation_payed_record 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-09-08 03:14 
 * @version：2018 Version：1.0 
 * @company：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "reservation_payed_record",displayName = "reservation_payed_record")
public class ReservationPayedRecord extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "reservation_id",displayName = "预约单id")
	private String reservationId;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "amount",displayName = "支付金额")
	private BigDecimal amount;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "payer",displayName = "")
	private String payer;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "payed_time",displayName = "")
	private Date payedTime;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "本次支付的备注信息")
	private String notes;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "支付类型：0 - 定金；1 - 预付款；2 - 尾款")
	private Integer type;
	@Column(type = Types.TINYINT,nullable = false,name = "mode",displayName = "支付方式：0 - 微信；1 - 支付宝")
	private Integer mode;

    public ReservationPayedRecord() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.reservationId == null) {
			validResult.put("reservationId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.reservationId) > 13) {
				validResult.put("reservationId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.payer == null) {
			validResult.put("payer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.payer) > 13) {
				validResult.put("payer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.payedTime == null) {
			validResult.put("payedTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.mode == null) {
			validResult.put("mode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("amount","payer","notes","type","mode");

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
		if(map.containsKey("amount")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount",String.class))) {
				validResult.put("amount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("mode")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("mode",String.class))) {
				validResult.put("mode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("payer")) {
			if (map.getTypedValue("payer",String.class)  == null) {
				validResult.put("payer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("payer",String.class)) > 13) {
					validResult.put("payer", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("mode")) {
			if (map.get("mode")  == null) {
				validResult.put("mode", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setReservationId(String reservationId){
		this.reservationId=reservationId;
	}

	public String getReservationId(){
		return reservationId;
	}

	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}

	public BigDecimal getAmount(){
		return amount;
	}

	public void setPayer(String payer){
		this.payer=payer;
	}

	public String getPayer(){
		return payer;
	}

	public void setPayedTime(Date payedTime){
		this.payedTime=payedTime;
	}

	public Date getPayedTime(){
		return payedTime;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setMode(Integer mode){
		this.mode=mode;
	}

	public Integer getMode(){
		return mode;
	}
}
