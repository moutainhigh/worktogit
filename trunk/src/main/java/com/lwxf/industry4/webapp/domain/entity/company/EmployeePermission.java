package com.lwxf.industry4.webapp.domain.entity.company;
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
 * 功能：employee_permission 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-05 11:05 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "employee_permission",displayName = "employee_permission")
public class EmployeePermission extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "employee_id",displayName = "公司员工id")
	private String employeeId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "module_key",displayName = "功能模块key：即根菜单的key")
	private String moduleKey;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "menu_key",displayName = "菜单资源key")
	private String menuKey;
	@Column(type = Types.VARCHAR,length = 200,nullable = false,name = "operations",displayName = "当前角色在当前菜单上所具有的操作：存操作按钮表中的key，以小写逗号（,）分隔")
	private String operations;
	@Column(type = Types.TINYINT,name = "is_show",displayName = "是否展示 0-false-不展示 1-true-展示")
	private Integer show;

    public EmployeePermission() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.employeeId == null) {
			validResult.put("employeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.employeeId) > 13) {
				validResult.put("employeeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.moduleKey == null) {
			validResult.put("moduleKey", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.moduleKey) > 50) {
				validResult.put("moduleKey", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.menuKey == null) {
			validResult.put("menuKey", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.menuKey) > 50) {
				validResult.put("menuKey", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.operations == null) {
			validResult.put("operations", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.operations) > 200) {
				validResult.put("operations", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(this.show==null){
			validResult.put("show",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("moduleKey","menuKey","operations");

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
		if(map.containsKey("moduleKey")) {
			if (map.getTypedValue("moduleKey",String.class)  == null) {
				validResult.put("moduleKey", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("moduleKey",String.class)) > 50) {
					validResult.put("moduleKey", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("menuKey")) {
			if (map.getTypedValue("menuKey",String.class)  == null) {
				validResult.put("menuKey", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("menuKey",String.class)) > 50) {
					validResult.put("menuKey", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("operations")) {
			if (map.getTypedValue("operations",String.class)  == null) {
				validResult.put("operations", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("operations",String.class)) > 200) {
					validResult.put("operations", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setEmployeeId(String employeeId){
		this.employeeId=employeeId;
	}

	public String getEmployeeId(){
		return employeeId;
	}

	public void setModuleKey(String moduleKey){
		this.moduleKey=moduleKey;
	}

	public String getModuleKey(){
		return moduleKey;
	}

	public void setMenuKey(String menuKey){
		this.menuKey=menuKey;
	}

	public String getMenuKey(){
		return menuKey;
	}

	public void setOperations(String operations){
		this.operations=operations;
	}

	public String getOperations(){
		return operations;
	}

	public Integer getShow() {
		return show;
	}

	public void setShow(Integer show) {
		this.show = show;
	}
}
