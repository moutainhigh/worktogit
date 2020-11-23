package com.lwxf.industry4.webapp.domain.entity.design;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.facade.AppBeanInjector;
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
 * 功能：scheme_content 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-15 09:42 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "scheme_content",displayName = "scheme_content")
public class SchemeContent extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 100,name = "content_name",displayName = "案例的内容")
	private String contentName;
	@Column(type = Types.VARCHAR,length = 2000,name = "content_notes",displayName = "内容的描述")
	private String contentNotes;
	@Column(type = Types.VARCHAR,length = 150,name = "content_Image",displayName = "内容的图片")
	private String contentImage;
	@Column(type = Types.INTEGER,name = "content_index",displayName = "内容的排序")
	private Integer contentIndex;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "content_created",displayName = "内容的创建时间")
	private Date contentCreated;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "scheme_id",displayName = "案例的id")
	private String schemeId;

    public SchemeContent() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.contentName) > 100) {
			validResult.put("contentName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.contentNotes) > 2000) {
			validResult.put("contentNotes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.contentImage) > 150) {
			validResult.put("contentImage", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.contentCreated == null) {
			validResult.put("contentCreated", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.schemeId == null) {
			validResult.put("schemeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.schemeId) > 13) {
				validResult.put("schemeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","contentName","contentNotes","contentImage","contentIndex","contentCreated","schemeId");

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
		if(map.containsKey("contentIndex")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("contentIndex",String.class))) {
				validResult.put("contentIndex", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("contentCreated")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("contentCreated",String.class))) {
				validResult.put("contentCreated", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("contentName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("contentName",String.class)) > 100) {
				validResult.put("contentName", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("contentNotes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("contentNotes",String.class)) > 2000) {
				validResult.put("contentNotes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("contentImage")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("contentImage",String.class)) > 150) {
				validResult.put("contentImage", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("contentCreated")) {
			if (map.get("contentCreated")  == null) {
				validResult.put("contentCreated", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("schemeId")) {
			if (map.getTypedValue("schemeId",String.class)  == null) {
				validResult.put("schemeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("schemeId",String.class)) > 13) {
					validResult.put("schemeId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setContentName(String contentName){
		this.contentName=contentName;
	}

	public String getContentName(){
		return contentName;
	}

	public void setContentNotes(String contentNotes){
		this.contentNotes=contentNotes;
	}

	public String getContentNotes(){
		return contentNotes;
	}

	public void setContentImage(String contentImage){
		this.contentImage=contentImage;
	}

	public String getContentImage(){
		return contentImage;
	}

	public void setContentIndex(Integer contentIndex){
		this.contentIndex=contentIndex;
	}

	public Integer getContentIndex(){
		return contentIndex;
	}

	public void setContentCreated(Date contentCreated){
		this.contentCreated=contentCreated;
	}

	public Date getContentCreated(){
		return contentCreated;
	}

	public void setSchemeId(String schemeId){
		this.schemeId=schemeId;
	}

	public String getSchemeId(){
		return schemeId;
	}
}
