package com.lwxf.industry4.webapp.domain.entity.user;

import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;

import java.sql.Types;
import java.util.*;
/**
 * 功能：user_notify 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-10 10:31
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "user_notify",displayName = "user_notify")
public class UserNotify extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "company_id",displayName = "公司id")
	private String companyId;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "user_id",displayName = "消息接收人")
	private String userId;
	@Column(type = Types.TINYINT,updatable = false,name = "grouping",displayName = "消息分组：来源于NotifyGrouping.java，暂时不用，默认为空")
	private Integer grouping;
	@Column(type = Types.BIT,nullable = false,name = "is_readed",displayName = "是否已读")
	private Boolean readed;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "top_time",displayName = "消息置顶的时间：默认为创建时间，取消置顶时设为创建时间，查询列表时按置顶时间倒序")
	private Date topTime;
	@Column(type = Types.VARCHAR,length = 300,nullable = false,name = "content",displayName = "消息详情")
	private String content;
	@Column(type = Types.VARCHAR,length = 200,name = "link_path",displayName = "链接路径：当不为空时，点击链接可以跳到相应的资源")
	private String linkPath;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "name",displayName = "消息标题，用于消息列表显示")
	private String name;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "消息类型：0 - 系统消息；1 - 个人消息")
	private Integer type;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "sender",displayName = "消息发送人：系统消息，设置为系统账号")
	private String sender;

	public UserNotify() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.companyId == null) {
			validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
				validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.userId == null) {
			validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.userId) > 13) {
				validResult.put("userId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.readed == null) {
			validResult.put("readed", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.topTime == null) {
			validResult.put("topTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.content == null) {
			validResult.put("content", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.content) > 300) {
				validResult.put("content", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.linkPath) > 200) {
			validResult.put("linkPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 100) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.type == null) {
			validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.sender == null) {
			validResult.put("sender", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.sender) > 13) {
				validResult.put("sender", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("readed","topTime","content","linkPath","name","type","sender");

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
		if(map.containsKey("readed")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("readed",String.class))) {
				validResult.put("readed", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("topTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("topTime",String.class))) {
				validResult.put("topTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("type",String.class))) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("readed")) {
			if (map.get("readed")  == null) {
				validResult.put("readed", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("topTime")) {
			if (map.get("topTime")  == null) {
				validResult.put("topTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("content")) {
			if (map.getTypedValue("content",String.class)  == null) {
				validResult.put("content", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("content",String.class)) > 300) {
					validResult.put("content", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("linkPath")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("linkPath",String.class)) > 200) {
				validResult.put("linkPath", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 100) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("sender")) {
			if (map.getTypedValue("sender",String.class)  == null) {
				validResult.put("sender", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("sender",String.class)) > 13) {
					validResult.put("sender", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setCompanyId(String companyId){
		this.companyId=companyId;
	}

	public String getCompanyId(){
		return companyId;
	}

	public void setUserId(String userId){
		this.userId=userId;
	}

	public String getUserId(){
		return userId;
	}

	public void setGrouping(Integer grouping){
		this.grouping=grouping;
	}

	public Integer getGrouping(){
		return grouping;
	}

	public void setReaded(Boolean readed){
		this.readed=readed;
	}

	public Boolean getReaded(){
		return readed;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setTopTime(Date topTime){
		this.topTime=topTime;
	}

	public Date getTopTime(){
		return topTime;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setLinkPath(String linkPath){
		this.linkPath=linkPath;
	}

	public String getLinkPath(){
		return linkPath;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setSender(String sender){
		this.sender=sender;
	}

	public String getSender(){
		return sender;
	}
}
