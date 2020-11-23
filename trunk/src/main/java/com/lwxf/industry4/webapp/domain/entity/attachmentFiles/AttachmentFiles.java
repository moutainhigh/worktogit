package com.lwxf.industry4.webapp.domain.entity.attachmentFiles;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：attachment_files 实体类
 *
 * @author：lyh
 * @created：2019-12-21 09:24 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="attachment_files对象", description="attachment_files")
@Table(name = "attachment_files",displayName = "attachment_files")
public class AttachmentFiles extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "path",displayName = "文件路径")
	@ApiModelProperty(value = "文件路径")
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
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "original_mime",displayName = "文件的原始mime类型")
	@ApiModelProperty(value = "文件的原始mime类型")
	private String originalMime;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "文件状态：0 - 临时；1 - 正式；2 - 删除；")
	@ApiModelProperty(value = "文件状态：0 - 临时；1 - 正式；2 - 删除；")
	private Byte status;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "aftersale_apply_id",displayName = "功能实体id")
	@ApiModelProperty(value = "功能实体id")
	private String originalId;
	@Column(type = Types.TINYINT,nullable = false,name = "category",displayName = "分类：0 - 图片；1 - 3D图；2 - VR图；3 - 视频；4 - 附件（除去前边的四种，其余的均为附件）。上传的为图片时，均设为0，和type字段配合确定文件的功能位置")
	@ApiModelProperty(value = "分类：0 - 图片；1 - 3D图；2 - VR图；3 - 视频；4 - 附件（除去前边的四种，其余的均为附件）。上传的为图片时，均设为0，和type字段配合确定文件的功能位置")
	private Byte category;
	@Column(type = Types.VARCHAR,length = 10,name = "function_type",displayName = "附件对应功能类型（1.经销商员工头像）")
	@ApiModelProperty(value = "附件对应功能类型（1.经销商员工头像）")
	private String functionType;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve1",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve1;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve2",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve2;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve3",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve3;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve4",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve4;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve5",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String reserve5;

	public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

	public AttachmentFiles() {
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
		if (this.category == null) {
			validResult.put("category", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.functionType) > 10) {
			validResult.put("functionType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve1) > 255) {
			validResult.put("reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve2) > 255) {
			validResult.put("reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve3) > 255) {
			validResult.put("reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve4) > 255) {
			validResult.put("reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reserve5) > 255) {
			validResult.put("reserve5", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","path","fullPath","mime","name","originalMime","status","creator","created","aftersaleApplyId","category","functionType","reserve1","reserve2","reserve3","reserve4","reserve5");

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
		if(map.containsKey("aftersaleApplyId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("aftersaleApplyId",String.class)) > 13) {
				validResult.put("aftersaleApplyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("category")) {
			if (map.get("category")  == null) {
				validResult.put("category", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("functionType")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("functionType",String.class)) > 10) {
				validResult.put("functionType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve1")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve1",String.class)) > 255) {
				validResult.put("reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve2")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve2",String.class)) > 255) {
				validResult.put("reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve3")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve3",String.class)) > 255) {
				validResult.put("reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve4")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve4",String.class)) > 255) {
				validResult.put("reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reserve5")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve5",String.class)) > 255) {
				validResult.put("reserve5", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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


	public void setCategory(Byte category){
		this.category=category;
	}

	public Byte getCategory(){
		return category;
	}

	public void setFunctionType(String functionType){
		this.functionType=functionType;
	}

	public String getFunctionType(){
		return functionType;
	}

	public void setReserve1(String reserve1){
		this.reserve1=reserve1;
	}

	public String getReserve1(){
		return reserve1;
	}

	public void setReserve2(String reserve2){
		this.reserve2=reserve2;
	}

	public String getReserve2(){
		return reserve2;
	}

	public void setReserve3(String reserve3){
		this.reserve3=reserve3;
	}

	public String getReserve3(){
		return reserve3;
	}

	public void setReserve4(String reserve4){
		this.reserve4=reserve4;
	}

	public String getReserve4(){
		return reserve4;
	}

	public void setReserve5(String reserve5){
		this.reserve5=reserve5;
	}

	public String getReserve5(){
		return reserve5;
	}
}
