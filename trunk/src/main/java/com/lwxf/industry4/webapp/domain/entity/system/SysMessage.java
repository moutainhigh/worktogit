package com.lwxf.industry4.webapp.domain.entity.system;
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
 * 功能：sys_message 实体类
 *
 * @author：zhangxiaolin(3965488qq.com)
 * @created：2019-12-09 04:47 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="系统消息实体", description="系统消息实体")
@Table(name = "sys_message",displayName = "sys_message")
public class SysMessage extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.TINYINT,name = "type",displayName = "类型：1：群发所有人，2：按部门群发，3：按用户id发")
	@ApiModelProperty(value = "类型：1：群发所有人，2：按部门群发，3：按用户id发")
	private Byte type;
	@Column(type = Types.VARCHAR,length = 255,name = "title",displayName = "标题")
	@ApiModelProperty(value = "标题")
	private String title;
	@Column(type = Types.CLOB,name = "content",displayName = "内容")
	@ApiModelProperty(value = "内容")
	private String content;
	@Column(type = Types.INTEGER,name = "status",displayName = "0:草稿，1：已发")
	@ApiModelProperty(value = "0:草稿，1：已发")
	private Integer status;
	@Column(type = Types.VARCHAR,length = 1000,name = "dept_ids",displayName = "部门id数组")
	@ApiModelProperty(value = "部门id数组")
	private String deptIds;
	@Column(type = Types.VARCHAR,length = 1000,name = "reciver_ids",displayName = "接收者id数组")
	@ApiModelProperty(value = "接收者id数组")
	private String reciverIds;
	@Column(type = Types.INTEGER,name = "reciver_count",displayName = "接收总人数")
	@ApiModelProperty(value = "接收总人数")
	private Integer reciverCount;
	@Column(type = Types.INTEGER,name = "read_count",displayName = "已读总人数")
	@ApiModelProperty(value = "已读总人数")
	private Integer readCount;
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	@ApiModelProperty(value = "企业id")
	private String branchId;

    public SysMessage() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.title) > 255) {
			validResult.put("title", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.deptIds) > 1000) {
			validResult.put("deptIds", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.reciverIds) > 1000) {
			validResult.put("reciverIds", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.creator) > 13) {
			validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
			validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","type","title","content","status","deptIds","reciverIds","reciverCount","readCount","creator","created","branchId");

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
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("reciverCount")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("reciverCount",String.class))) {
				validResult.put("reciverCount", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("readCount")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("readCount",String.class))) {
				validResult.put("readCount", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("title")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("title",String.class)) > 255) {
				validResult.put("title", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("deptIds")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("deptIds",String.class)) > 1000) {
				validResult.put("deptIds", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("reciverIds")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("reciverIds",String.class)) > 1000) {
				validResult.put("reciverIds", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("creator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("branchId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("branchId",String.class)) > 13) {
				validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setType(Byte type){
		this.type=type;
	}

	public Byte getType(){
		return type;
	}

	public void setTitle(String title){
		this.title=title;
	}

	public String getTitle(){
		return title;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setDeptIds(String deptIds){
		this.deptIds=deptIds;
	}

	public String getDeptIds(){
		return deptIds;
	}

	public void setReciverIds(String reciverIds){
		this.reciverIds=reciverIds;
	}

	public String getReciverIds(){
		return reciverIds;
	}

	public void setReciverCount(Integer reciverCount){
		this.reciverCount=reciverCount;
	}

	public Integer getReciverCount(){
		return reciverCount;
	}

	public void setReadCount(Integer readCount){
		this.readCount=readCount;
	}

	public Integer getReadCount(){
		return readCount;
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

	public void setBranchId(String branchId){
		this.branchId=branchId;
	}

	public String getBranchId(){
		return branchId;
	}
}
