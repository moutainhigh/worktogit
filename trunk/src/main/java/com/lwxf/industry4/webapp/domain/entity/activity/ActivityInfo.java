package com.lwxf.industry4.webapp.domain.entity.activity;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import io.swagger.annotations.ApiModel;

/**
 * 功能：activity_info 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 09:57 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value="活动管理参数列表",description="活动管理参数列表")
@Table(name = "activity_info",displayName = "activity_info")
public class ActivityInfo extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "no",displayName = "活动的编号")
	private String no;
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "创建人类型（0厂家、1经销商）")
	private Integer type;
	@Column(type = Types.VARCHAR,length = 100,nullable = false,name = "name",displayName = "标题")
	private String name;
	@Column(type = Types.VARCHAR,length = 150,name = "cover",displayName = "封面")
	private String cover;
	@Column(type = Types.VARCHAR,length = 200,name = "summary",displayName = "摘要")
	private String summary;
	@Column(type = Types.LONGVARCHAR,name = "content",displayName = "活动详情")
	private String content;
	@Column(type = Types.VARCHAR,length = 150,name = "link",displayName = "链接路径")
	private String link;
	@Column(type = Types.TINYINT,name = "is_inner",displayName = "是否站内链接(1内联 0外联)")
	private Integer inner;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@Column(type = Types.DATE,nullable = false,name = "begin_time",displayName = "开始时间")
	private Date beginTime;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@Column(type = Types.DATE,name = "end_time",displayName = "结束时间，如果是长期活动，结束时间为空")
	private Date endTime;
	@Column(type = Types.VARCHAR,length = 500,name = "conditions",displayName = "参与条件")
	private String conditions;
	@Column(type = Types.VARCHAR,length = 500,name = "regulation",displayName = "活动规则")
	private String regulation;
	@Column(type = Types.INTEGER,nullable = false,name = "views",displayName = "浏览量")
	private Integer views;
	@Column(type = Types.TINYINT,nullable = false,name = "classify",displayName = "分类（0 海报  1 互动的页面）")
	private Integer classify;
	@Column(type = Types.TINYINT,nullable = false,name = "target",displayName = "面向的顾客（0 经销商 1终端）")
	private Integer target;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态 （0 未发布，1已发布，2活动结束）")
	private Integer status;
	@Column(type = Types.VARCHAR,nullable = false,name = "company_id",displayName = "公司id")
	private String companyId;
	@Column(type = Types.VARCHAR,nullable = false,name = "branch_id",displayName = "企业id")
	private String branchId;
	private String isJoins;



    public ActivityInfo() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.no == null) {
			validResult.put("no", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.no) > 50) {
				validResult.put("no", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.companyId == null) {
			validResult.put("companyId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.companyId) > 50) {
				validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.branchId == null) {
			validResult.put("branchId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
				validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.type == null) {
			validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.name == null) {
			validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 100) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}

 			if (LwxfStringUtils.getStringLength(this.cover) > 150) {
				validResult.put("cover", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}

		if (LwxfStringUtils.getStringLength(this.summary) > 200) {
			validResult.put("summary", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.link) > 150) {
			validResult.put("link", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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
		if (this.beginTime == null) {
			validResult.put("beginTime", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.conditions) > 500) {
			validResult.put("conditions", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.regulation) > 500) {
			validResult.put("regulation", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.views == null) {
			validResult.put("views", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.classify == null) {
			validResult.put("classify", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.target == null) {
			validResult.put("target", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","no","type","name","cover","summary","content","link","inner","creator","created","beginTime","endTime","conditions","regulation","views","classify","target","status");

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
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("type",String.class))) {
				validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("inner")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("inner",String.class))) {
				validResult.put("inner", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("beginTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("beginTime",String.class))) {
				validResult.put("beginTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("endTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("endTime",String.class))) {
				validResult.put("endTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("views")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("views",String.class))) {
				validResult.put("views", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("classify")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("classify",String.class))) {
				validResult.put("classify", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("target")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("target",String.class))) {
				validResult.put("target", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("no")) {
			if (map.getTypedValue("no",String.class)  == null) {
				validResult.put("no", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 50) {
					validResult.put("no", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
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
		if(map.containsKey("cover")) {

 				if (LwxfStringUtils.getStringLength(map.getTypedValue("cover",String.class)) > 150) {
					validResult.put("cover", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}

		if(map.containsKey("summary")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("summary",String.class)) > 200) {
				validResult.put("summary", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("link")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("link",String.class)) > 150) {
				validResult.put("link", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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
		if(map.containsKey("beginTime")) {
			if (map.get("beginTime")  == null) {
				validResult.put("beginTime", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("conditions")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("conditions",String.class)) > 500) {
				validResult.put("conditions", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("regulation")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("regulation",String.class)) > 500) {
				validResult.put("regulation", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("views")) {
			if (map.get("views")  == null) {
				validResult.put("views", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("classify")) {
			if (map.get("classify")  == null) {
				validResult.put("classify", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("target")) {
			if (map.get("target")  == null) {
				validResult.put("target", ErrorCodes.VALIDATE_NOTNULL);
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

	public String getBranchId() {return branchId;}

	public void setBranchId(String branchId) {this.branchId = branchId;}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}

	public void setCover(String cover){
		this.cover=cover;
	}

	public String getCover(){
		return cover;
	}

	public void setSummary(String summary){
		this.summary=summary;
	}

	public String getSummary(){
		return summary;
	}

	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setLink(String link){
		this.link=link;
	}

	public String getLink(){
		return link;
	}

	public void setInner(Integer inner){
		this.inner=inner;
	}

	public Integer getInner(){
		return inner;
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

	public void setBeginTime(Date beginTime){
		this.beginTime=beginTime;
	}

	public Date getBeginTime(){
		return beginTime;
	}

	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	public Date getEndTime(){
		return endTime;
	}

	public void setConditions(String conditions){
		this.conditions=conditions;
	}

	public String getConditions(){
		return conditions;
	}

	public void setRegulation(String regulation){
		this.regulation=regulation;
	}

	public String getRegulation(){
		return regulation;
	}

	public void setViews(Integer views){
		this.views=views;
	}

	public Integer getViews(){
		return views;
	}

	public void setClassify(Integer classify){
		this.classify=classify;
	}

	public Integer getClassify(){
		return classify;
	}

	public void setTarget(Integer target){
		this.target=target;
	}

	public Integer getTarget(){
		return target;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getIsJoins() {
		return isJoins;
	}

	public void setIsJoins(String isJoins) {
		this.isJoins = isJoins;
	}
}
