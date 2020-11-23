package com.lwxf.industry4.webapp.domain.entity.contentmng;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.base.AbstractNoIdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.TypesExtend;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.annotation.Column;

import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
/**
 * 功能：contents_content 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-27 11:49 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "contents_content",displayName = "contents_content")
@ApiModel(value = "内容详情信息",description = "内容详情信息")
public class ContentsContent extends AbstractNoIdEntity {
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "contents_id",displayName = "")
	@ApiModelProperty(value = "内容ID")
	private String contentsId;
	@Column(type = Types.LONGVARCHAR,nullable = false,name = "content",displayName = "内容详情")
	@ApiModelProperty(value = "内容详情")
	private String content;

    public ContentsContent() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.contentsId == null) {
			validResult.put("contentsId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.contentsId) > 13) {
				validResult.put("contentsId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("contentsId","content");

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
		if(map.containsKey("contentsId")) {
			if (map.getTypedValue("contentsId",String.class)  == null) {
				validResult.put("contentsId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("contentsId",String.class)) > 13) {
					validResult.put("contentsId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setContentsId(String contentsId){
		this.contentsId=contentsId;
	}

	public String getContentsId(){
		return contentsId;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}
}
