package com.lwxf.industry4.webapp.domain.entity.userGoodsAcount;

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
 * 功能：user_goods_acount 实体类
 *
 * @author：lyh
 * @created：2020-01-13 04:48 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="user_goods_acount对象", description="user_goods_acount")
@Table(name = "user_goods_acount",displayName = "user_goods_acount")
public class UserGoodsAcount extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 20,name = "dealer_id",displayName = "经销商id")
	@ApiModelProperty(value = "经销商id")
	private String dealerId;
	@Column(type = Types.DECIMAL,precision = 20,scale=2,name = "dealer_goods_cost_year",displayName = "经销商年货款累计")
	@ApiModelProperty(value = "经销商年货款累计")
	private BigDecimal dealerGoodsCostYear;
	@Column(type = Types.DECIMAL,precision = 20,scale=2,name = "dealer_discount_year",displayName = "经销商打折后累计")
	@ApiModelProperty(value = "经销商打折后累计")
	private BigDecimal dealerDiscountYear;
	@Column(type = Types.VARCHAR,length = 10,name = "dealer_year",displayName = "货款累计年份")
	@ApiModelProperty(value = "货款累计年份")
	private String dealerYear;
	@Column(type = Types.INTEGER,name = "vip_cost",displayName = "会员费(抵扣后会员费为0会员等级不变)")
	@ApiModelProperty(value = "会员费(抵扣后会员费为0会员等级不变)")
	private Integer vipCost;
	@Column(type = TypesExtend.DATETIME,name = "vip_use_date",displayName = "会员有效期")
	@ApiModelProperty(value = "会员有效期")
	private Date vipUseDate;
	@Column(type = Types.INTEGER,name = "vip_cost_limit",displayName = "会员费使用限制")
	@ApiModelProperty(value = "会员费使用限制")
	private Integer vipCostLimit;
	@Column(type = TypesExtend.DATETIME,name = "creat_time",displayName = "插入时间")
	@ApiModelProperty(value = "插入时间")
	private Date creatTime;
	@Column(type = TypesExtend.DATETIME,name = "update_time",displayName = "更新时间")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	@Column(type = Types.VARCHAR,length = 50,name = "creater",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creater;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve1",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve1;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve2",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve2;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve3",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve3;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve4",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve4;

    public UserGoodsAcount() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.dealerId) > 20) {
			validResult.put("dealerId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.dealerYear) > 10) {
			validResult.put("dealerYear", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.creater) > 50) {
			validResult.put("creater", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve1) > 255) {
			validResult.put("reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve2) > 255) {
			validResult.put("reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve3) > 255) {
			validResult.put("reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve4) > 255) {
			validResult.put("reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","dealerId","dealerGoodsCostYear","dealerDiscountYear","dealerYear","vipCost","vipUseDate","vipCostLimit","creatTime","updateTime","creater","reserve1","reserve2","reserve3","reserve4");

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
		if(map.containsKey("dealerGoodsCostYear")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("dealerGoodsCostYear",String.class))) {
				validResult.put("dealerGoodsCostYear", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("dealerDiscountYear")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("dealerDiscountYear",String.class))) {
				validResult.put("dealerDiscountYear", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("vipCost")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("vipCost",String.class))) {
				validResult.put("vipCost", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("vipUseDate")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("vipUseDate",String.class))) {
				validResult.put("vipUseDate", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("vipCostLimit")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("vipCostLimit",String.class))) {
				validResult.put("vipCostLimit", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("creatTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("creatTime",String.class))) {
				validResult.put("creatTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("updateTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updateTime",String.class))) {
				validResult.put("updateTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("dealerId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("dealerId",String.class)) > 20) {
				validResult.put("dealerId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("dealerYear")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("dealerYear",String.class)) > 10) {
				validResult.put("dealerYear", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("creater")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("creater",String.class)) > 50) {
				validResult.put("creater", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve1")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve1",String.class)) > 255) {
				validResult.put("reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve2")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve2",String.class)) > 255) {
				validResult.put("reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve3")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve3",String.class)) > 255) {
				validResult.put("reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve4")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve4",String.class)) > 255) {
				validResult.put("reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setDealerId(String dealerId){
		this.dealerId=dealerId;
	}

	public String getDealerId(){
		return dealerId;
	}

	public void setDealerGoodsCostYear(BigDecimal dealerGoodsCostYear){
		this.dealerGoodsCostYear=dealerGoodsCostYear;
	}

	public BigDecimal getDealerGoodsCostYear(){
		return dealerGoodsCostYear;
	}

	public void setDealerDiscountYear(BigDecimal dealerDiscountYear){
		this.dealerDiscountYear=dealerDiscountYear;
	}

	public BigDecimal getDealerDiscountYear(){
		return dealerDiscountYear;
	}

	public void setDealerYear(String dealerYear){
		this.dealerYear=dealerYear;
	}

	public String getDealerYear(){
		return dealerYear;
	}

	public void setVipCost(Integer vipCost){
		this.vipCost=vipCost;
	}

	public Integer getVipCost(){
		return vipCost;
	}

	public void setVipUseDate(Date vipUseDate){
		this.vipUseDate=vipUseDate;
	}

	public Date getVipUseDate(){
		return vipUseDate;
	}

	public void setVipCostLimit(Integer vipCostLimit){
		this.vipCostLimit=vipCostLimit;
	}

	public Integer getVipCostLimit(){
		return vipCostLimit;
	}

	public void setCreatTime(Date creatTime){
		this.creatTime=creatTime;
	}

	public Date getCreatTime(){
		return creatTime;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setCreater(String creater){
		this.creater=creater;
	}

	public String getCreater(){
		return creater;
	}

	public void setReserve1(String reserve1){
		this.reserve1=reserve1;
	}

	public String getReserve1(){
		return reserve1;
	}

	public void setReserve2(String reserve2){
		this.reserve2=reserve2;
	}

	public String getReserve2(){
		return reserve2;
	}

	public void setReserve3(String reserve3){
		this.reserve3=reserve3;
	}

	public String getReserve3(){
		return reserve3;
	}

	public void setReserve4(String reserve4){
		this.reserve4=reserve4;
	}

	public String getReserve4(){
		return reserve4;
	}
}
