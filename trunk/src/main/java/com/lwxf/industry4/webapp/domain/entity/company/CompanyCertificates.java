package com.lwxf.industry4.webapp.domain.entity.company;
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
 * 功能：company_certificates 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-08-20 10:26
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="company_certificates对象", description="company_certificates")
@Table(name = "company_certificates",displayName = "company_certificates")
public class CompanyCertificates extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,name = "company_id",displayName = "经销商id")
	@ApiModelProperty(value = "经销商id")
	private String companyId;
	@Column(type = Types.INTEGER,name = "type",displayName = "证件类型 0-营业执照 1-身份证 2-合同")
	@ApiModelProperty(value = "证件类型 0-营业执照 1-身份证 2-合同")
	private Integer type;
	@Column(type = Types.VARCHAR,length = 255,name = "name",displayName = "证件名称")
	@ApiModelProperty(value = "证件名称")
	private String name;
	@Column(type = Types.VARCHAR,length = 100,name = "certificates_num",displayName = "证件号码")
	@ApiModelProperty(value = "证件号码")
	private String certificatesNum;
	@Column(type = Types.VARCHAR,length = 200,name = "term_of_validity",displayName = "有效期")
	@ApiModelProperty(value = "有效期")
	private String termOfValidity;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = Types.INTEGER,name = "status",displayName = "是否已上传证件照 0-待提交 1-已上传")
	@ApiModelProperty(value = "是否已上传证件照 0-待提交 1-已上传")
	private Integer status;

	public CompanyCertificates() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
			validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.name) > 255) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.certificatesNum) > 100) {
			validResult.put("certificatesNum", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	private final static List<String> propertiesList = Arrays.asList("id","companyId","type","name","certificatesNum","termOfValidity","created","creator","status");

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
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("type",String.class))) {
				validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("termOfValidity")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("termOfValidity",String.class))) {
				validResult.put("termOfValidity", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("companyId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId",String.class)) > 13) {
				validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 255) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("certificatesNum")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("certificatesNum",String.class)) > 100) {
				validResult.put("certificatesNum", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setCertificatesNum(String certificatesNum){
		this.certificatesNum=certificatesNum;
	}

	public String getCertificatesNum(){
		return certificatesNum;
	}

	public void setTermOfValidity(String termOfValidity){
		this.termOfValidity=termOfValidity;
	}

	public String getTermOfValidity(){
		return termOfValidity;
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

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}
}
