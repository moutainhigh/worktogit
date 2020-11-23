package com.lwxf.industry4.webapp.domain.entity.product;
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
/**
 * 功能：product_files 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-03-12 02:25 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "product_files",displayName = "product_files")
public class ProductFiles extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,updatable = false,name = "path",displayName = "文件访问的路径:相对路径")
	private String path;
	@Column(type = Types.VARCHAR,length = 150,nullable = false,updatable = false,name = "full_path",displayName = "全路径:加上服务器域名的路径,富文本中的图片引用该路径")
	private String fullPath;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "mime",displayName = "文件的mime类型")
	private String mime;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,updatable = false,name = "name",displayName = "文件的名称")
	private String name;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "product_id",displayName = "产品ID")
	private String productId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "original_mime",displayName = "文件的原始mime类型")
	private String originalMime;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "文件状态: 0-临时,1-正式")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.TINYINT,nullable = false,updatable = false,name = "type",displayName = "文件类型:0-PC封面,1-PC主图,2-APP封面,3-APP主图,4-wx封面,5-wx详情,6-wx报价图")
	private Integer type;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "belong_id",displayName = "所属资源的id")
	private String belongId;

    public ProductFiles() {  
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
		if (this.productId == null) {
			validResult.put("productId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.productId) > 13) {
				validResult.put("productId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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
		if (this.type == null) {
			validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.belongId) > 13) {
			validResult.put("belongId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("status");

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
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
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

	public void setProductId(String productId){
		this.productId=productId;
	}

	public String getProductId(){
		return productId;
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
}
