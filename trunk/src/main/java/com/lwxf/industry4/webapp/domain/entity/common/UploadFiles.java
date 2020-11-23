package com.lwxf.industry4.webapp.domain.entity.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.*;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;
/**
 * 功能：upload_files 实体类
 *
 * @author：renzhongshan(d3shan@126.com)
 * @created：2018-09-06 05:40
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "upload_files",displayName = "upload_files")
@ApiModel(value = "文件资源信息",discriminator = "文件资源信息")
public class UploadFiles extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "company_id",displayName = "公司id")
	@ApiModelProperty(value = "公司id",required = true)
	private String companyId;
	@Column(type = Types.CHAR,length = 13,name = "resource_id",displayName = "所属资源id，比如商品、商品评论、")
	@ApiModelProperty(value = "所属资源id")
	private String resourceId;
	@Column(type = Types.TINYINT,nullable = false,name = "resource_type",displayName = "文件的所属资源类型：0-系统配置;1-商城配置;2-商品;3-商品评论;4-快享;5-商品规格;6-品牌;7-用户相关（头像、桌面背景等）8-背景图片,9-商品展示图片;")
	@ApiModelProperty(value = "文件的所属资源类型",required = true)
	private Integer resourceType;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,updatable = false,name = "path",displayName = "文件的访问路径，带域名的全路径，如：http://localhost:8080/resources/goods/3232233323.png")
	@ApiModelProperty(value = "文件的访问路径",required = true)
	private String path;
	@Column(type = Types.VARCHAR,length = 50,updatable = false,name = "name",displayName = "文件的名称：被上传时的原始文件名")
	@ApiModelProperty(value = "文件的名称：被上传时的原始文件名",required = true)
	private String name;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "mime",displayName = "文件的mime类型（上传时根据业务逻辑而设定的）")
	@ApiModelProperty(value = "文件的mime类型",required = true)
	private String mime;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "original_mime",displayName = "文件的mime类型（原始mime）")
	@ApiModelProperty(value = "文件的mime类型（原始mime）",required = true)
	private String originalMime;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "文件状态：0 - 临时文件；1：正式文件")
	@ApiModelProperty(value = "文件状态：0 - 临时文件；1：正式文件",required = true)
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人id（上传人id）")
	@ApiModelProperty(value = "创建人id（上传人id）",required = true)
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "上传时间")
	@ApiModelProperty(value = "上传时间",required = true)
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "belong_id",displayName = "该文件对应的资源属于哪个资源的子资源：比如商品评论的图片，那么belong_id填对应的商品id，没有则为null")
	@ApiModelProperty(value = "该文件对应的资源属于哪个资源的子资源")
	private String belongId;
	@Column(type = Types.VARCHAR,length = 150,nullable = false,name = "full_path",displayName = "文件访问全路径：带域名的路径")
	@ApiModelProperty(value = "文件访问全路径：带域名的路径",required = true)
	private String fullPath;

	public UploadFiles() {
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
		if (LwxfStringUtils.getStringLength(this.resourceId) > 13) {
			validResult.put("resourceId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.resourceType == null) {
			validResult.put("resourceType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.path == null) {
			validResult.put("path", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.path) > 100) {
				validResult.put("path", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.name) > 50) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.mime == null) {
			validResult.put("mime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.mime) > 50) {
				validResult.put("mime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
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
		if (this.fullPath == null) {
			validResult.put("fullPath", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.fullPath) > 150) {
				validResult.put("fullPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
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

	private final static List<String> propertiesList = Arrays.asList("id","companyId","resourceId","resourceType","status","belongId","fullPath");

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
		if(map.containsKey("resourceType")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("resourceType",String.class))) {
				validResult.put("resourceType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
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
		if(map.containsKey("resourceId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("resourceId",String.class)) > 13) {
				validResult.put("resourceId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("resourceType")) {
			if (map.get("resourceType")  == null) {
				validResult.put("resourceType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("belongId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("belongId",String.class)) > 13) {
				validResult.put("belongId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("fullPath")) {
			if (map.getTypedValue("fullPath",String.class)  == null) {
				validResult.put("fullPath", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("fullPath",String.class)) > 150) {
					validResult.put("fullPath", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
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

	public void setResourceId(String resourceId){
		this.resourceId=resourceId;
	}

	public String getResourceId(){
		return resourceId;
	}

	public void setResourceType(Integer resourceType){
		this.resourceType=resourceType;
	}

	public Integer getResourceType(){
		return resourceType;
	}

	public void setPath(String path){
		this.path=path;
	}

	public String getPath(){
		return path;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setMime(String mime){
		this.mime=mime;
	}

	public String getMime(){
		return mime;
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

	public void setBelongId(String belongId){
		this.belongId=belongId;
	}

	public String getBelongId(){
		return belongId;
	}

	public String getFullPath() {
		return fullPath;
	}

	public void setFullPath(String fullPath) {
		this.fullPath = fullPath;
	}

	public static UploadFiles create(String resId, String belongId, UploadInfo uploadInfo, UploadResourceType uploadResourceType, UploadType uploadType){
		UploadFiles file = new UploadFiles();
		file.setBelongId(belongId);
		file.setCreated(DateUtil.getSystemDate());
		file.setCreator(WebUtils.getCurrUserId());
		file.setMime(uploadInfo.getFileMimeType().getRealType());
		file.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
		file.setName(uploadInfo.getFileName());
		file.setPath(uploadInfo.getRelativePath());
		file.setResourceId(resId);
		file.setResourceType(uploadResourceType.getType());
		file.setStatus(uploadType.getValue());
		file.setFullPath(uploadInfo.getRealPath());
		return file;
	}
}
