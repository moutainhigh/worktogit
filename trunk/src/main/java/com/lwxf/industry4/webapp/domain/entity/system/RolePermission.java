package com.lwxf.industry4.webapp.domain.entity.system;
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
 * 功能：role_permission 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-07 02:06 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "role_permission",displayName = "role_permission")
public class RolePermission extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "role_id",displayName = "角色id")
	private String roleId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "module_key",displayName = "功能模块的key：即根菜单的key")
	private String moduleKey;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "menu_key",displayName = "菜单（资源）key")
	private String menuKey;
	@Column(type = Types.TINYINT,length = 500,nullable = false,updatable = false,name = "operations",displayName = "当前角色在当前菜单上所具有的操作：存操作按钮表中的key，以小写逗号（,）分隔")
	private String operations;
	@Column(type = Types.TINYINT,name = "is_show",displayName = "是否展示 0-false-不展示 1-true-展示")
	private Integer show;

    public RolePermission() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.roleId == null) {
			validResult.put("roleId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.roleId) > 13) {
				validResult.put("roleId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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
 			if (LwxfStringUtils.getStringLength(this.operations) > 500) {
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

	private final static List<String> propertiesList = Arrays.asList();

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
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setRoleId(String roleId){
		this.roleId=roleId;
	}

	public String getRoleId(){
		return roleId;
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
