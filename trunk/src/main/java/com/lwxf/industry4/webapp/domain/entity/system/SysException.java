package com.lwxf.industry4.webapp.domain.entity.system;
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
 * 功能：sys_exception 实体类
 *
 * @author：zhangxiaolin(3965488qq.com)
 * @created：2019-11-16 02:40 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "sys_exception",displayName = "sys_exception")
public class SysException extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 10,name = "post_type",displayName = "请求方式:get或者post")
	private String postType;
	@Column(type = Types.VARCHAR,length = 50,name = "param",displayName = "参数内容")
	private String param;
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.VARCHAR,length = 50,name = "url",displayName = "请求接口地址")
	private String url;
	@Column(type = Types.CLOB,name = "content",displayName = "异常内容")
	private String content;

    public SysException() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.postType) > 10) {
			validResult.put("postType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.param) > 50) {
			validResult.put("param", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.url) > 50) {
			validResult.put("url", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","postType","param","created","url","content");

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
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("postType")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("postType",String.class)) > 10) {
				validResult.put("postType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("param")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("param",String.class)) > 50) {
				validResult.put("param", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("url")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("url",String.class)) > 50) {
				validResult.put("url", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setPostType(String postType){
		this.postType=postType;
	}

	public String getPostType(){
		return postType;
	}

	public void setParam(String param){
		this.param=param;
	}

	public String getParam(){
		return param;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setUrl(String url){
		this.url=url;
	}

	public String getUrl(){
		return url;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}
}
