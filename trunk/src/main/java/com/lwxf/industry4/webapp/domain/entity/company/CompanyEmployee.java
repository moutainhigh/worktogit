package com.lwxf.industry4.webapp.domain.entity.company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：company_employee 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-05 11:05
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "company_employee",displayName = "company_employee")
@ApiModel(value = "公司员工信息",discriminator = "公司员工信息")
public class CompanyEmployee extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_id",displayName = "公司id")
	@ApiModelProperty(value = "公司Id",name = "companyId",required = true)
	protected String companyId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "user_id",displayName = "用户id")
	@ApiModelProperty(value = "用户Id",name = "userId",required = true)
	protected String userId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "role_id",displayName = "角色")
	@ApiModelProperty(value = "角色Id",name = "roleId",required = true)
	protected String roleId;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "加入时间")
	@ApiModelProperty(value = "加入时间",name="created",required = true)
	protected Date created;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "店员状态：0 - 正常状态；1 - 禁用（删除）；2 - 离职")
	@ApiModelProperty(value = "店员状态：0 - 正常状态；1 - 禁用（删除）；2 - 离职",name = "status",required = true)
	protected Integer status;
	@Column(type = Types.CHAR,length = 10,name = "no",displayName = "员工编号，公司内部不允许重复")
	@ApiModelProperty(value = "员工编号",name = "no",required = false)
	protected String no;
	@Column(type = Types.TINYINT,length = 2,name = "is_supervisor",displayName = "是否为主管 : 0-不是 1-是 默认为0")
	@ApiModelProperty(value = "是否为主管 : 0-不是 1-是 默认为0")
    private Integer supervisor;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "corporate_partners_id",displayName = "外协厂家id")
	@ApiModelProperty(value = "外协厂家id",name = "corporatePartnersId",required = true)
	private String corporatePartnersId;

	public String getCorporatePartnersId() {
		return corporatePartnersId;
	}

	public void setCorporatePartnersId(String corporatePartnersId) {
		this.corporatePartnersId = corporatePartnersId;
	}

	public CompanyEmployee() {
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
		if (this.userId == null) {
			validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.roleId == null) {
			validResult.put("roleId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.roleId) > 13) {
				validResult.put("roleId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.no) > 10) {
			validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("userId","roleId","status","no","supervisor");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("userId")) {
			if (map.getTypedValue("userId",String.class)  == null) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("userId",String.class)) > 13) {
					validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("roleId")) {
			if (map.getTypedValue("roleId",String.class)  == null) {
				validResult.put("roleId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("roleId",String.class)) > 13) {
					validResult.put("roleId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("no")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 10) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("supervisor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("supervisor",String.class)) > 2) {
				validResult.put("supervisor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setRoleId(String roleId){
		this.roleId=roleId;
	}

	public String getRoleId(){
		return roleId;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public Integer getSupervisor() {
		return supervisor;
	}

	public void setSupervisor(Integer supervisor) {
		this.supervisor = supervisor;
	}

	public CompanyEmployee(String companyId, String userId, String roleId, Date created, Integer status, String no) {

	}
}
