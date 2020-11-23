package com.lwxf.industry4.webapp.domain.entity.financing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.*;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;
/**
 * 功能：funds 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-09-25 09:24 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value="财务科目管理",description="财务科目管理")
@Table(name = "funds",displayName = "funds")
public class Funds extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="科目名称",name="name",required=true,example = "")
	@Column(type = Types.VARCHAR,length = 30,nullable = false,name = "name",displayName = "科目名称")
	private String name;
	@ApiModelProperty(value="科目等级 0-一级 1-二级 2-三级",name="grade",required=true,example = "")
	@Column(type = Types.TINYINT,nullable = false,name = "grade",displayName = "科目等级0-一级 1-二级 2-三级")
	private Integer grade;
	@ApiModelProperty(value="科目分类 0-收入 1-支出",name="way",required=true,example = "")
	@Column(type = Types.TINYINT,nullable = false,name = "way",displayName = "分类 0-收入 1-支出")
	private Integer way;
	@ApiModelProperty(value="说明",name="notes",required=true,example = "")
	@Column(type = Types.VARCHAR,length = 50,name = "notes",displayName = "备注")
	private String notes;
	@ApiModelProperty(value="状态 0-正常 1-禁用",name="status",required=true,example = "")
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态  0-正常 1-禁用")
	private Integer status;
	@ApiModelProperty(value="父ID",name="parents",required=true,example = "")
	@Column(type = Types.CHAR,length = 13,name = "parent_id",displayName = "父级ID")
	private String parentId;
	@ApiModelProperty(value="企业ID",name="branchId",required=true,example = "")
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业ID")
	private String branchId;
	@ApiModelProperty(value="创建人",name="creator",required=true,example = "")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	private String creator;
	@ApiModelProperty(value="创建时间",name="created",required=true,example = "")
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;
	@ApiModelProperty(value="0-工厂科目 1-经销商科目",name="类型",required=true,example = "")
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "0-工厂科目 1-经销商科目")
	private Integer type;


    public Funds() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 30) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.grade == null) {
			validResult.put("grade", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.way == null) {
			validResult.put("way", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 50) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.type == null) {
			validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.parentId) > 13) {
			validResult.put("parentId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
			validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","name","grade","way","notes","status","parentId","branchId","creator","created");

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
		if(map.containsKey("grade")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("grade",String.class))) {
				validResult.put("grade", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("way")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("way",String.class))) {
				validResult.put("way", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
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
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 30) {
					validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("grade")) {
			if (map.get("grade")  == null) {
				validResult.put("grade", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("way")) {
			if (map.get("way")  == null) {
				validResult.put("way", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 50) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("parentId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("parentId",String.class)) > 13) {
				validResult.put("parentId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("branchId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("branchId",String.class)) > 13) {
				validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public void setGrade(Integer grade){
		this.grade=grade;
	}

	public Integer getGrade(){
		return grade;
	}

	public void setWay(Integer way){
		this.way=way;
	}

	public Integer getWay(){
		return way;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setParentId(String parentId){
		this.parentId=parentId;
	}

	public String getParentId(){
		return parentId;
	}

	public void setBranchId(String branchId){
		this.branchId=branchId;
	}

	public String getBranchId(){
		return branchId;
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

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
