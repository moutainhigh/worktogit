package com.lwxf.industry4.webapp.domain.entity.userRole;
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
 * 功能：user_role 实体类
 *
 * @author：lyh
 * @created：2020-01-07 01:46 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="user_role对象", description="user_role")
@Table(name = "user_role",displayName = "user_role")
public class UserRole extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "role_id",displayName = "")
	@ApiModelProperty(value = "")
	private String roleId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "user_id",displayName = "")
	@ApiModelProperty(value = "")
	private String userId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "user_id",displayName = "")
	@ApiModelProperty(value = "角色名字")
	private String roleName;
	@Column(type = Types.VARCHAR,length = 255,name = "role_grorp_name",displayName = "")
	@ApiModelProperty(value = "")
	private String roleGrorpName;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve1",displayName = "")
	@ApiModelProperty(value = "")
	private String reserve1;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve2",displayName = "")
	@ApiModelProperty(value = "")
	private String reserve2;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve3",displayName = "")
	@ApiModelProperty(value = "")
	private String reserve3;
	@Column(type = Types.VARCHAR,length = 255,name = "reserve4",displayName = "")
	@ApiModelProperty(value = "")
	private String reserve4;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public UserRole() {
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.roleId == null) {
			validResult.put("roleId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.roleId) > 50) {
				validResult.put("roleId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.userId == null) {
			validResult.put("userId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.userId) > 50) {
				validResult.put("userId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.roleGrorpName) > 255) {
			validResult.put("roleGrorpName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","roleId","userId","roleGrorpName","reserve1","reserve2","reserve3","reserve4");

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
		if(map.containsKey("roleId")) {
			if (map.getTypedValue("roleId",String.class)  == null) {
				validResult.put("roleId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("roleId",String.class)) > 50) {
					validResult.put("roleId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("userId")) {
			if (map.getTypedValue("userId",String.class)  == null) {
				validResult.put("userId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("userId",String.class)) > 50) {
					validResult.put("userId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("roleGrorpName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("roleGrorpName",String.class)) > 255) {
				validResult.put("roleGrorpName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setRoleGrorpName(String roleGrorpName){
		this.roleGrorpName=roleGrorpName;
	}

	public String getRoleGrorpName(){
		return roleGrorpName;
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
}
