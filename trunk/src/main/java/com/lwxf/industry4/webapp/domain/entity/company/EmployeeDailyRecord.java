package com.lwxf.industry4.webapp.domain.entity.company;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：employee_daily_record 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-05-25 10:02 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value = "员工日志信息",description = "员工日志信息")
@Table(name = "employee_daily_record",displayName = "employee_daily_record")
public class EmployeeDailyRecord extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(name = "title",value ="日志标题，预留" )
	@Column(type = Types.CHAR,length = 100,name = "title",displayName = "日志标题，预留")
	private String title;
	@ApiModelProperty(name = "content",value ="日志内容" )
	@Column(type = Types.VARCHAR,length = 2048,name = "content",displayName = "日志内容")
	private String content;
	@ApiModelProperty(name = "createUser",value ="创建人(后台默认当前登录人)" )
	@Column(type = Types.CHAR,length = 13,name = "create_user",displayName = "创建人")
	private String createUser;
	@ApiModelProperty(name = "createTime",value ="创建时间(后台默认当前系统时间)" )
	@Column(type = TypesExtend.DATETIME,name = "create_time",displayName = "创建时间")
	private Date createTime;
	@ApiModelProperty(name = "updateUser",value ="修改人" )
	@Column(type = Types.CHAR,length = 13,name = "update_user",displayName = "修改人")
	private String updateUser;
	@ApiModelProperty(name = "updateTime",value ="修改时间" )
	@Column(type = TypesExtend.DATETIME,name = "update_time",displayName = "修改时间")
	private Date updateTime;
	@ApiModelProperty(name = "recordState",value ="日志状态，0：草稿，1：发布，2：已删除，默认：0" )
	@Column(type = Types.TINYINT,name = "record_state",displayName = "日志状态，0：草稿，1：发布，2：已删除，默认：0")
	private Integer recordState;
	@ApiModelProperty(name = "visibleState",value ="日志可见范围，0：本部门，1：全部可见，默认：0" )
	@Column(type = Types.TINYINT,name = "visible_state",displayName = "日志可见范围，0：本部门，1：全部可见，默认：0")
	private Integer visibleState;

    public EmployeeDailyRecord() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.title) > 100) {
			validResult.put("title", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.content) > 2048) {
			validResult.put("content", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.createUser) > 13) {
			validResult.put("createUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.updateUser) > 13) {
			validResult.put("updateUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","title","content","createUser","createTime","updateUser","updateTime","recordState","visibleState");

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
		if(map.containsKey("createTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("createTime",String.class))) {
				validResult.put("createTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("updateTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updateTime",String.class))) {
				validResult.put("updateTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("recordState")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("recordState",String.class))) {
				validResult.put("recordState", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("visibleState")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("visibleState",String.class))) {
				validResult.put("visibleState", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("title")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("title",String.class)) > 100) {
				validResult.put("title", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("content")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("content",String.class)) > 2048) {
				validResult.put("content", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("createUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("createUser",String.class)) > 13) {
				validResult.put("createUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("updateUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("updateUser",String.class)) > 13) {
				validResult.put("updateUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
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

	public void setCreateUser(String createUser){
		this.createUser=createUser;
	}

	public String getCreateUser(){
		return createUser;
	}

	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}

	public Date getCreateTime(){
		return createTime;
	}

	public void setUpdateUser(String updateUser){
		this.updateUser=updateUser;
	}

	public String getUpdateUser(){
		return updateUser;
	}

	public void setUpdateTime(Date updateTime){
		this.updateTime=updateTime;
	}

	public Date getUpdateTime(){
		return updateTime;
	}

	public void setRecordState(Integer recordState){
		this.recordState=recordState;
	}

	public Integer getRecordState(){
		return recordState;
	}

	public void setVisibleState(Integer visibleState){
		this.visibleState=visibleState;
	}

	public Integer getVisibleState(){
		return visibleState;
	}
}
