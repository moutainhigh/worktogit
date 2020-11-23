package com.lwxf.industry4.webapp.domain.entity.customorder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesType;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
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
 * 功能：custom_order_files 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-04 05:34 
 * @version：2018 Version：1.0 
 * @company：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "custom_order_files",displayName = "custom_order_files")
@ApiModel(value = "订单资源文件",discriminator = "订单资源文件")
public class CustomOrderFiles extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "path",displayName = "文件的访问路径：相对路径")
	@ApiModelProperty(value = "文件的访问路径：相对路径")
	private String path;
	@Column(type = Types.VARCHAR,length = 150,nullable = false,name = "full_path",displayName = "全路径：加上服务域名的路径，富文本中的图片引用该路径")
	@ApiModelProperty(value = "全路径：加上服务域名的路径，富文本中的图片引用该路径")
	private String fullPath;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "mime",displayName = "文件的mime类型")
	@ApiModelProperty(value = "文件的mime类型")
	private String mime;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "name",displayName = "文件的名称")
	@ApiModelProperty(value = "文件的名称")
	private String name;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "custom_order_id",displayName = "定制订单的id")
	@ApiModelProperty(value = "定制订单的id")
	private String customOrderId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "original_mime",displayName = "文件的原始mime类型")
	@ApiModelProperty(value = "文件的原始mime类型")
	private String originalMime;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "文件状态：0 - 临时；1 - 正式；2 - 删除；")
	@ApiModelProperty(value = "文件状态：0 - 临时；1 - 正式；2 - 删除；")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "文件类型：0 - 订单需求；1 - 订单设计；2- 合同附件;3 - 订单产品附件;4 - 生产拆单明细附件;5 - 生产流程明细附件；")
	@ApiModelProperty(value = "文件类型：0 - 订单需求；1 - 订单设计；2- 合同附件;3 - 订单产品附件;4 - 生产拆单明细附件;5 - 生产流程明细附件；")
	private Integer type;
	@Column(type = Types.CHAR,length = 13,name = "belong_id",displayName = "所属资源的id：当type为0时，为custom_order_demand的id；当type为1时，为custom_order_design的id")
	@ApiModelProperty(value = "所属资源的id：当type为0时，为custom_order_demand的id；当type为1时，为custom_order_design的id")
	private String belongId;
	@Column(type = Types.TINYINT,nullable = false,name = "category",displayName = "分类：0 - 平面图（或图片）；1 - 3D图；2 - VR图；3 - 视频；4 - 附件（除去前边的四种，其余的均为附件）。上传的为图片时，均设为0，和type字段配合确定文件的功能位置")
	@ApiModelProperty(value = "分类：0 - 平面图（或图片）；1 - 3D图；2 - VR图；3 - 视频；4 - 附件（除去前边的四种，其余的均为附件）。上传的为图片时，均设为0，和type字段配合确定文件的功能位置")
	private Integer category;

    public CustomOrderFiles() {
     }

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.path == null) {
			validResult.put("path", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.path) > 100) {
				validResult.put("path", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.fullPath == null) {
			validResult.put("fullPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.fullPath) > 150) {
				validResult.put("fullPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.mime == null) {
			validResult.put("mime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.mime) > 50) {
				validResult.put("mime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 100) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.customOrderId == null) {
			validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
				validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.originalMime == null) {
			validResult.put("originalMime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.originalMime) > 50) {
				validResult.put("originalMime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.status == null) {
			validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.creator == null) {
			validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.category == null) {
			validResult.put("category", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}

 		if (LwxfStringUtils.getStringLength(this.belongId) > 13) {
			validResult.put("belongId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}

		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","path","fullPath","mime","name","customOrderId","originalMime","status","creator","created","type","belongId","category");

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
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("category")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("category",String.class))) {
				validResult.put("category", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("path")) {
			if (map.getTypedValue("path",String.class)  == null) {
				validResult.put("path", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("path",String.class)) > 100) {
					validResult.put("path", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("fullPath")) {
			if (map.getTypedValue("fullPath",String.class)  == null) {
				validResult.put("fullPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("fullPath",String.class)) > 150) {
					validResult.put("fullPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("mime")) {
			if (map.getTypedValue("mime",String.class)  == null) {
				validResult.put("mime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("mime",String.class)) > 50) {
					validResult.put("mime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 100) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("customOrderId")) {
			if (map.getTypedValue("customOrderId",String.class)  == null) {
				validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("customOrderId",String.class)) > 13) {
					validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("originalMime")) {
			if (map.getTypedValue("originalMime",String.class)  == null) {
				validResult.put("originalMime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("originalMime",String.class)) > 50) {
					validResult.put("originalMime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("creator")) {
			if (map.getTypedValue("creator",String.class)  == null) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
					validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("category")) {
			if (map.get("category")  == null) {
				validResult.put("category", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}

		if (LwxfStringUtils.getStringLength(map.getTypedValue("belongId",String.class)) > 13) {
					validResult.put("belongId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}

		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public Integer getCategory() {
		return category;
	}

	public void setCategory(Integer category) {
		this.category = category;
	}

	public void setPath(String path){
		this.path=path;
	}

	public String getPath(){
		return path;
	}

	public void setFullPath(String fullPath){
		this.fullPath=fullPath;
	}

	public String getFullPath(){
		return fullPath;
	}

	public void setMime(String mime){
		this.mime=mime;
	}

	public String getMime(){
		return mime;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setOriginalMime(String originalMime){
		this.originalMime=originalMime;
	}

	public String getOriginalMime(){
		return originalMime;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setBelongId(String belongId){
		this.belongId=belongId;
	}

	public String getBelongId(){
		return belongId;
	}


	public static CustomOrderFiles create(String customOrderId, String belongId, UploadInfo uploadInfo,UploadType uploadType, Integer category, Integer type){
		CustomOrderFiles file = new CustomOrderFiles();
		file.setBelongId(belongId);
		file.setCreated(DateUtil.getSystemDate());
		file.setMime(uploadInfo.getFileMimeType().getRealType());
		file.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
		file.setName(uploadInfo.getFileName());
		file.setPath(uploadInfo.getRelativePath());
		file.setStatus(uploadType.getValue());
		file.setCustomOrderId(customOrderId);
		file.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
		file.setType(type);
		file.setCategory(category);
		return file;
	}


}
