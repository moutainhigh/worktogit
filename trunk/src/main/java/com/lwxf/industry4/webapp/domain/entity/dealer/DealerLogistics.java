package com.lwxf.industry4.webapp.domain.entity.dealer;

import java.sql.Types;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
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
 * 功能：dealer_logistics 实体类
 *
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:09 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value = "经销商物流公司信息", description = "经销商物流公司信息")
@Table(name = "dealer_logistics",displayName = "dealer_logistics")
public class DealerLogistics extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "经销商ID")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "经销商ID，引用company表主键")
	private String companyId;
    @ApiModelProperty(value = "物流公司ID", required = true)
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "logistics_company_id",displayName = "物流公司ID，引用logistics_company表主键")
	private String logisticsCompanyId;
    @ApiModelProperty(value = "是否默认物流公司： 0 - 否；1 - 是", required = true)
	@Column(type = Types.TINYINT,nullable = false,name = "checked",displayName = "是否默认物流公司： 0 - 否；1 - 是")
	private Byte checked;
    @ApiModelProperty(value = "创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	private Date created;
    @ApiModelProperty(value = "创建人ID")
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "创建人ID，引用user表主键")
	private String creator;

    public DealerLogistics() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.companyId == null) {
			validResult.put("companyId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.logisticsCompanyId == null) {
			validResult.put("logisticsCompanyId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.logisticsCompanyId) > 13) {
				validResult.put("logisticsCompanyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.checked == null) {
			validResult.put("checked", ErrorCodes.VALIDATE_NOTNULL);
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

	private final static List<String> propertiesList = Arrays.asList("id","companyId","logisticsCompanyId","checked","created","creator");

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
		if(map.containsKey("checked")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("checked",String.class))) {
				validResult.put("checked", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("companyId")) {
			if (map.getTypedValue("companyId",String.class)  == null) {
				validResult.put("companyId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId",String.class)) > 13) {
					validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("logisticsCompanyId")) {
			if (map.getTypedValue("logisticsCompanyId",String.class)  == null) {
				validResult.put("logisticsCompanyId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("logisticsCompanyId",String.class)) > 13) {
					validResult.put("logisticsCompanyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("checked")) {
			if (map.get("checked")  == null) {
				validResult.put("checked", ErrorCodes.VALIDATE_NOTNULL);
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


	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setLogisticsCompanyId(String logisticsCompanyId){
		this.logisticsCompanyId=logisticsCompanyId;
	}

	public String getLogisticsCompanyId(){
		return logisticsCompanyId;
	}

	public void setChecked(Byte checked){
		this.checked=checked;
	}

	public Byte getChecked(){
		return checked;
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
}
