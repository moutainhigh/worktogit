package com.lwxf.industry4.webapp.domain.entity.dealer;
import com.fasterxml.jackson.annotation.JsonFormat;
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

import java.sql.Types;
import java.util.*;

/**
 * 功能：dealer_address 实体类
 *
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-28 11:09 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value = "经销商收货地址信息", description = "经销商收货地址信息")
@Table(name = "dealer_address",displayName = "dealer_address")
public class DealerAddress extends IdEntity  {
	private static final long serialVersionUID = 1L;
    @ApiModelProperty(value = "经销商ID")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "经销商ID，引用company表主键")
	private String companyId;
    @ApiModelProperty(value = "收货人姓名", required = true)
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "consignee_name",displayName = "收货人姓名")
	private String consigneeName;
    @ApiModelProperty(value = "收货人电话", required = true)
	@Column(type = Types.VARCHAR,length = 20,nullable = false,name = "consignee_tel",displayName = "收货人电话")
	private String consigneeTel;
    @ApiModelProperty(value = "客户所在地区ID", required = true)
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "city_area_id",displayName = "客户所在地区ID")
	private String cityAreaId;
    @ApiModelProperty(value = "详细收货地址", required = true)
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "address",displayName = "详细收货地址")
	private String address;
    @ApiModelProperty(value = "是否默认收货地址： 0 - 否；1 - 是", required = true)
	@Column(type = Types.INTEGER,nullable = false,name = "checked",displayName = "是否默认收货地址： 0 - 否；1 - 是")
	private Integer checked;
    @ApiModelProperty(value = "创建时间")
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	private Date created;
    @ApiModelProperty(value = "创建人ID")
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "创建人ID，引用user表主键")
	private String creator;

    public DealerAddress() {  
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
		if (this.consigneeName == null) {
			validResult.put("consigneeName", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.consigneeName) > 50) {
				validResult.put("consigneeName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.consigneeTel == null) {
			validResult.put("consigneeTel", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.consigneeTel) > 20) {
				validResult.put("consigneeTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.cityAreaId == null) {
			validResult.put("cityAreaId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.cityAreaId) > 13) {
				validResult.put("cityAreaId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.address == null) {
			validResult.put("address", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.address) > 100) {
				validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	private final static List<String> propertiesList = Arrays.asList("id","companyId","consigneeName","consigneeTel","cityAreaId","address","checked","created","creator");

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
		if(map.containsKey("consigneeName")) {
			if (map.getTypedValue("consigneeName",String.class)  == null) {
				validResult.put("consigneeName", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("consigneeName",String.class)) > 50) {
					validResult.put("consigneeName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("consigneeTel")) {
			if (map.getTypedValue("consigneeTel",String.class)  == null) {
				validResult.put("consigneeTel", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("consigneeTel",String.class)) > 20) {
					validResult.put("consigneeTel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("cityAreaId")) {
			if (map.getTypedValue("cityAreaId",String.class)  == null) {
				validResult.put("cityAreaId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("cityAreaId",String.class)) > 13) {
					validResult.put("cityAreaId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("address")) {
			if (map.getTypedValue("address",String.class)  == null) {
				validResult.put("address", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("address",String.class)) > 100) {
					validResult.put("address", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public void setChecked(Integer checked){
		this.checked=checked;
	}

	public Integer getChecked(){
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
