package com.lwxf.industry4.webapp.domain.entity.vipTypeInfo;
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
 * 功能：vip_type_info 实体类
 *
 * @author：lyh
 * @created：2020-01-13 01:57 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="vip_type_info对象", description="vip_type_info")
@Table(name = "vip_type_info",displayName = "vip_type_info")
public class VipTypeInfo extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 2,name = "vip_level",displayName = "会员等级")
	@ApiModelProperty(value = "会员等级")
	private String vipLevel;
	@Column(type = Types.VARCHAR,length = 50,name = "vip_name",displayName = "会员名称")
	@ApiModelProperty(value = "会员名称")
	private String vipName;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "vip_cost",displayName = "会员费")
	@ApiModelProperty(value = "会员费")
	private BigDecimal vipCost;
	@Column(type = Types.INTEGER,name = "vip_use_limit",displayName = "打折限制金额")
	@ApiModelProperty(value = "打折限制金额")
	private Integer vipUseLimit;
	@Column(type = Types.VARCHAR,length = 50,name = "vip_discount_ratio",displayName = "会员打折比例")
	@ApiModelProperty(value = "会员打折比例")
	private String vipDiscountRatio;
	@Column(type = TypesExtend.DATETIME,name = "update_time",displayName = "更新时间")
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
	@Column(type = TypesExtend.DATETIME,name = "insert_time",displayName = "插入时间")
	@ApiModelProperty(value = "插入时间")
	private Date insertTime;
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

    public VipTypeInfo() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.vipLevel) > 2) {
			validResult.put("vipLevel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.vipName) > 50) {
			validResult.put("vipName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.vipDiscountRatio) > 50) {
			validResult.put("vipDiscountRatio", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	private final static List<String> propertiesList = Arrays.asList("id","vipLevel","vipName","vipCost","vipUseLimit","vipDiscountRatio","updateTime","insertTime","reserve1","reserve2","reserve3","reserve4");

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
		if(map.containsKey("vipCost")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("vipCost",String.class))) {
				validResult.put("vipCost", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("vipUseLimit")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("vipUseLimit",String.class))) {
				validResult.put("vipUseLimit", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("updateTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updateTime",String.class))) {
				validResult.put("updateTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("insertTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("insertTime",String.class))) {
				validResult.put("insertTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("vipLevel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("vipLevel",String.class)) > 2) {
				validResult.put("vipLevel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("vipName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("vipName",String.class)) > 50) {
				validResult.put("vipName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("vipDiscountRatio")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("vipDiscountRatio",String.class)) > 50) {
				validResult.put("vipDiscountRatio", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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


	public void setVipLevel(String vipLevel){
		this.vipLevel=vipLevel;
	}

	public String getVipLevel(){
		return vipLevel;
	}

	public void setVipName(String vipName){
		this.vipName=vipName;
	}

	public String getVipName(){
		return vipName;
	}

	public void setVipCost(BigDecimal vipCost){
		this.vipCost=vipCost;
	}

	public BigDecimal getVipCost(){
		return vipCost;
	}

	public void setVipUseLimit(Integer vipUseLimit){
		this.vipUseLimit=vipUseLimit;
	}

	public Integer getVipUseLimit(){
		return vipUseLimit;
	}

	public void setVipDiscountRatio(String vipDiscountRatio){
		this.vipDiscountRatio=vipDiscountRatio;
	}

	public String getVipDiscountRatio(){
		return vipDiscountRatio;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setInsertTime(Date insertTime){
		this.insertTime=insertTime;
	}

	public Date getInsertTime(){
		return insertTime;
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
