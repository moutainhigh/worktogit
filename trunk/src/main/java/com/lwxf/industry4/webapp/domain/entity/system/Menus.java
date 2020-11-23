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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：menus 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-02-22 01:06 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value="菜单实体",description="菜单实体")
@Table(name = "menus",displayName = "menus")
public class Menus extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="菜单名称",name="name",required=true,example = "")
	@Column(type = Types.VARCHAR,length = 20,nullable = false,name = "name",displayName = "")
	private String name;
	@ApiModelProperty(value="路径",name="path",required=false,example = "")
	@Column(type = Types.VARCHAR,length = 200,name = "path",displayName = "")
	private String path;
	@ApiModelProperty(value="图标",name="path",required=false,example = "")
	@Column(type = Types.VARCHAR,length = 30,name = "icon",displayName = "")
	private String icon;
	@ApiModelProperty(value="类型:菜单类型：0 - 厂家后台菜单；1 - 经销商后台菜单；2：B端APP端菜单 默认为0",name="type",required=true,example = "")
	@Column(type = Types.TINYINT,nullable = false,updatable = false,name = "type",displayName = "菜单类型：0 - 厂家后台菜单；1 - 经销商后台菜单；2：经销商APP端菜单，默认为0")
	private Integer type;
	@ApiModelProperty(value="是否为文件夹类菜单：true - 文件夹类；false - 功能类，默认为false，当为true时path失效，只能展开，不能打开功能页面",name="folder",required=true,example = "")
	@Column(type = Types.BIT,nullable = false,name = "is_folder",displayName = "是否为文件夹类菜单：true - 文件夹类；false - 功能类，默认为false，当为true时path失效，只能展开，不能打开功能页面")
	private Boolean folder;
	@ApiModelProperty(value="父菜单id",name="parentId",required=false,example = "")
	@Column(type = Types.CHAR,length = 13,name = "parent_id",displayName = "父菜单id")
	private String parentId;
	@ApiModelProperty(value="是否外部链接：true - 是（表示跳出当前功能页面，到另一个页面），false - 否（表示本功能页面的内部路由路径）",name="outLink",required=false,example = "")
	@Column(type = Types.BIT,nullable = false,name = "is_out_link",displayName = "是否外部链接：true - 是（表示跳出当前功能页面，到另一个页面），false - 否（表示本功能页面的内部路由路径）")
	private Boolean outLink;
	@ApiModelProperty(value="菜单唯一标识，用于权限控制",name="key",required=true,example = "")
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "key",displayName = "菜单唯一标识，用于权限控制")
	private String key;
	@ApiModelProperty(value="相关资源类型:0-通用,1-订单",name="relevantResource",required=true,example = "")
	@Column(type = Types.TINYINT,nullable = false,updatable = false,name = "relevant_resource",displayName = "相关资源类型:0-通用,1-订单")
	private Integer relevantResource;
	@ApiModelProperty(value="是否禁用 0-false-不禁用 1-true-禁用",name="disabled",required=true,example = "")
	@Column(type = Types.BIT,nullable = false,name = "is_disabled",displayName = "是否禁用 0-false-不禁用 1-true-禁用")
	private Boolean disabled;
	@Column(type = Types.INTEGER,nullable = false,name = "sort",displayName = "排序")
	private Integer sort;

	@ApiModelProperty(value="子菜单",name="submenu",required=true,example = "")
	private List<Menus> submenus;

	public List<Menus> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<Menus> submenus) {
		this.submenus = submenus;
	}

	public Menus() {
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
		if (LwxfStringUtils.getStringLength(this.path) > 200) {
			validResult.put("path", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.icon) > 30) {
			validResult.put("icon", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.folder == null) {
			validResult.put("folder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.parentId) > 13) {
			validResult.put("parentId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.outLink == null) {
			validResult.put("outLink", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.key == null) {
			validResult.put("key", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.key) > 50) {
				validResult.put("key", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.relevantResource == null) {
			validResult.put("relevantResource", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.disabled == null) {
			validResult.put("disabled", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","path","icon","folder","parentId","outLink","disabled","sort");

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
		if(map.containsKey("folder")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("folder",String.class))) {
				validResult.put("folder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("outLink")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("outLink",String.class))) {
				validResult.put("outLink", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("disabled")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("disabled",String.class))) {
				validResult.put("disabled", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 20) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("path")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("path",String.class)) > 200) {
				validResult.put("path", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("icon")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("icon",String.class)) > 30) {
				validResult.put("icon", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("folder")) {
			if (map.get("folder")  == null) {
				validResult.put("folder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("parentId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("parentId",String.class)) > 13) {
				validResult.put("parentId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("outLink")) {
			if (map.get("outLink")  == null) {
				validResult.put("outLink", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("disabled")) {
			if (map.get("disabled")  == null) {
				validResult.put("disabled", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
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

	public void setPath(String path){
		this.path=path;
	}

	public String getPath(){
		return path;
	}

	public void setIcon(String icon){
		this.icon=icon;
	}

	public String getIcon(){
		return icon;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setFolder(Boolean folder){
		this.folder=folder;
	}

	public Boolean getFolder(){
		return folder;
	}

	public void setParentId(String parentId){
		this.parentId=parentId;
	}

	public String getParentId(){
		return parentId;
	}

	public void setOutLink(Boolean outLink){
		this.outLink=outLink;
	}

	public Boolean getOutLink(){
		return outLink;
	}

	public void setKey(String key){
		this.key=key;
	}

	public String getKey(){
		return key;
	}

	public void setRelevantResource(Integer relevantResource){
		this.relevantResource=relevantResource;
	}

	public Integer getRelevantResource(){
		return relevantResource;
	}

	public void setDisabled(Boolean disabled){
		this.disabled=disabled;
	}

	public Boolean getDisabled(){
		return disabled;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
