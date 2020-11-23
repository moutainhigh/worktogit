package com.lwxf.industry4.webapp.domain.entity.org;
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
 * 功能：dept 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-10 01:11
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "dept",displayName = "dept")
@ApiModel(value = "部门信息",discriminator = "部门信息")
public class Dept extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 20,nullable = false,name = "name",displayName = "部门名称")
	@ApiModelProperty(value = "部门名称")
	private String name;
	@Column(type = Types.VARCHAR,length = 20,nullable = false,name = "key",displayName = "部门唯一标识")
	@ApiModelProperty(value = "部门唯一标识")
	private String key;
	@Column(type = Types.VARCHAR,length = 500,name = "notes",displayName = "部门说明")
	@ApiModelProperty(value = "部门说明")
	private String notes;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "parent_id",displayName = "父部门id")
	@ApiModelProperty(value = "父部门id")
	private String parentId;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_Id",displayName = "关联公司主键ID")
	@ApiModelProperty(value = "关联公司主键ID")
	private String companyId;
	private String branchId;

	public Dept() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 20) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.key == null) {
			validResult.put("key", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.key) > 20) {
				validResult.put("key", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 500) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.parentId) > 13) {
			validResult.put("parentId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","key","notes");

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
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else if(map.getTypedValue("name",String.class).trim().equals("")){
				map.put("name",null);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 20) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("key")) {
			if (map.getTypedValue("key",String.class)  == null) {
				validResult.put("key", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else if(map.getTypedValue("key",String.class).trim().equals("")) {
				map.put("key", null);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("key",String.class)) > 20) {
					validResult.put("key", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 500) {
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

	public void setKey(String key){
		this.key=key;
	}

	public String getKey(){
		return key;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setParentId(String parentId){
		this.parentId=parentId;
	}

	public String getParentId(){
		return parentId;
	}

	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
