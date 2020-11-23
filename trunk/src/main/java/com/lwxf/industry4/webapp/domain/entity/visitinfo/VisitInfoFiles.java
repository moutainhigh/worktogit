package com.lwxf.industry4.webapp.domain.entity.visitinfo;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

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
 * 功能：visit_info_files 实体类
 *
 * @author：lyh
 * @created：2019-11-26 11:40 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value = "拜访信息附件", description = "拜访信息附件")
@Table(name = "visit_info_files",displayName = "visit_info_files")
public class VisitInfoFiles extends IdEntity  {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "路径", name = "path", example = "路径")
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "path",displayName = "")
	private String path;


	@ApiModelProperty(value = "全路径：加上服务域名的路径，富文本中的图片引用该路径", name = "fullPath", example = "全路径：加上服务域名的路径，富文本中的图片引用该路径")
	@Column(type = Types.VARCHAR,length = 150,nullable = false,name = "full_path",displayName = "全路径：加上服务域名的路径，富文本中的图片引用该路径")
	private String fullPath;


	@ApiModelProperty(value = "文件的mime类型", name = "mime", example = "文件的mime类型")
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "mime",displayName = "文件的mime类型")
	private String mime;

	@ApiModelProperty(value = "文件的名称", name = "name", example = "文件的名称")
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "name",displayName = "文件的名称")
	private String name;

	@ApiModelProperty(value = "文件的原始mime类型", name = "originalMime", example = "文件的原始mime类型")
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "original_mime",displayName = "文件的原始mime类型")
	private String originalMime;

	@ApiModelProperty(value = "文件状态：0 - 临时；1 - 正式；2 - 删除；", name = "status", example = "文件状态：0 - 临时；1 - 正式；2 - 删除；")
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "文件状态：0 - 临时；1 - 正式；2 - 删除；")
	private Byte status;

	@ApiModelProperty(value = "创建人", name = "creator", example = "创建人")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	private String creator;

	@ApiModelProperty(value = "创建时间", name = "created", example = "创建时间")
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;

	@ApiModelProperty(value = "访问id", name = "visitInfoId", example = "访问id")
	@Column(type = Types.CHAR,length = 13,name = "visit_info_id",displayName = "访问id")
	private String visitInfoId;

	@ApiModelProperty(value = "分类：0 - 现场照片 1 - 合影", name = "category", example = "分类：0 - 现场照片 1 - 合影")
	@Column(type = Types.TINYINT,name = "category",displayName = "分类：0 - 现场照片 1 - 合影")
	private Byte category;

    public VisitInfoFiles() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.path == null) {
			validResult.put("path", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.path) > 100) {
				validResult.put("path", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.fullPath == null) {
			validResult.put("fullPath", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.fullPath) > 150) {
				validResult.put("fullPath", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.mime == null) {
			validResult.put("mime", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.mime) > 50) {
				validResult.put("mime", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.name == null) {
			validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 100) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.originalMime == null) {
			validResult.put("originalMime", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.originalMime) > 50) {
				validResult.put("originalMime", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.creator == null) {
			validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.created == null) {
			validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.visitInfoId) > 13) {
			validResult.put("visitInfoId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","path","fullPath","mime","name","originalMime","status","creator","created","visitInfoId","category");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("category")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("category",String.class))) {
				validResult.put("category", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("path")) {
			if (map.getTypedValue("path",String.class)  == null) {
				validResult.put("path", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("path",String.class)) > 100) {
					validResult.put("path", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
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
		if(map.containsKey("mime")) {
			if (map.getTypedValue("mime",String.class)  == null) {
				validResult.put("mime", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("mime",String.class)) > 50) {
					validResult.put("mime", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 100) {
					validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("originalMime")) {
			if (map.getTypedValue("originalMime",String.class)  == null) {
				validResult.put("originalMime", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("originalMime",String.class)) > 50) {
					validResult.put("originalMime", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("creator")) {
			if (map.getTypedValue("creator",String.class)  == null) {
				validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
					validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("visitInfoId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("visitInfoId",String.class)) > 13) {
				validResult.put("visitInfoId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
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

	public void setOriginalMime(String originalMime){
		this.originalMime=originalMime;
	}

	public String getOriginalMime(){
		return originalMime;
	}

	public void setStatus(Byte status){
		this.status=status;
	}

	public Byte getStatus(){
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

	public void setVisitInfoId(String visitInfoId){
		this.visitInfoId=visitInfoId;
	}

	public String getVisitInfoId(){
		return visitInfoId;
	}

	public void setCategory(Byte category){
		this.category=category;
	}

	public Byte getCategory(){
		return category;
	}
}
