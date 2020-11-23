package com.lwxf.industry4.webapp.domain.entity.dealer;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.enums.company.DealerAccountType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;
/**
 * 功能：dealer_account 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-24 06:21
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="经销商账户",description="经销商账户")
@Table(name = "dealer_account",displayName = "dealer_account")
public class DealerAccount extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "经销商ID")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "经销商的公司id")
	private String companyId;
	@ApiModelProperty(value = "账户类型：0 - 自由账户；1 - 预付账号；2 - 冻结账号；3 - 押金账号")
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "账户类型：0 - 自由账户；1 - 预付账号；2 - 冻结账号；3 - 押金账号")
	private Integer type;
	@ApiModelProperty(value = "账户状态：0 - 禁用；1 - 启用")
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "账户状态：0 - 禁用；1 - 启用")
	private Integer status;
	@ApiModelProperty(value = "性质：0 - 个人账户；1 - 对公账户（对工厂）；，默认为1")
	@Column(type = Types.TINYINT,nullable = false,name = "nature",displayName = "性质：0 - 个人账户；1 - 对公账户（对工厂）；，默认为1")
	private Integer nature;
	@ApiModelProperty(value = "余额，默认0")
	@Column(type = Types.DECIMAL,precision = 20,scale=2,nullable = false,name = "balance",displayName = "余额，默认0")
	private BigDecimal balance;
	@ApiModelProperty(value = "企业ID")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "branch_id",displayName = "企业ID")
	private String branchId;

	@ApiModelProperty(value = "经销商ID")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "经销商的公司id")
	private String typeName;



	public DealerAccount() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.branchId == null) {
			validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
				validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.nature == null) {
			validResult.put("nature", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","companyId","type","status","nature","balance");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("nature")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("nature",String.class))) {
				validResult.put("nature", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("balance")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("balance",String.class))) {
				validResult.put("balance", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
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
		if(map.containsKey("branchId")) {
			if (map.getTypedValue("branchId",String.class)  == null) {
				validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("branchId",String.class)) > 13) {
					validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("nature")) {
			if (map.get("nature")  == null) {
				validResult.put("nature", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public String getBranchId() {return branchId;}

	public void setBranchId(String branchId) {this.branchId = branchId;}

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

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setNature(Integer nature){
		this.nature=nature;
	}

	public Integer getNature(){
		return nature;
	}

	public void setBalance(BigDecimal balance){
		this.balance=balance;
	}

	public BigDecimal getBalance(){
		return balance;
	}

	public String getTypeName() {
		if(this.getType()!=null) {
			return DealerAccountType.getByValue(this.getType()).getName();
		}else{
			return "";
		}
	}
}
