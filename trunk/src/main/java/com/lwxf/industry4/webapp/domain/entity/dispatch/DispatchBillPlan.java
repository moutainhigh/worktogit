package com.lwxf.industry4.webapp.domain.entity.dispatch;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.text.SimpleDateFormat;
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
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 功能：dispatch_bill_plan 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-04-18 02:31 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "dispatch_bill_plan",displayName = "dispatch_bill_plan")
@ApiModel(value = "配送计划信息",discriminator = "配送计划信息")
public class DispatchBillPlan extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.TINYINT,name = "status",displayName = "状态 0未完成 1已完成")
	@ApiModelProperty(value = "状态 0未完成 1已完成")
	private Integer status;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = Types.INTEGER,nullable = false,name = "num",displayName = "计划发货总数")
	@ApiModelProperty(value = "计划发货总数")
	private Integer num;
	@Column(type = TypesExtend.DATETIME,name = "updated",displayName = "修改时间")
	@ApiModelProperty(value = "修改时间")
	private Date updated;
	@Column(type = Types.CHAR,length = 13,name = "updator",displayName = "修改人")
	@ApiModelProperty(value = "修改人")
	private String updator;
	@Column(type = Types.VARCHAR,length = 200,name = "note",displayName = "备注")
	@ApiModelProperty(value = "备注")
	private String note;
	@Column(type = Types.CHAR,length = 13,name = "branchId",displayName = "企业id")
	@ApiModelProperty(value = "企业id")
	private String branchId;
	@Column(type = Types.VARCHAR,length = 13,name = "name",displayName = "发货计划名称")
	@ApiModelProperty(value = "发货计划名称")
	private String name;
	@JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
	@Column(type = Types.DATE,name = "plan_time",displayName = "计划发货时间")
	@ApiModelProperty(value = "计划发货时间")
	private Date planTime;
	@Column(type = Types.INTEGER,nullable = false,name = "actual_num",displayName = "实际发货总数")
	@ApiModelProperty(value = "实际发货总数")
	private Integer actualNum;
	@Column(type = Types.INTEGER,nullable = false,name = "audit_status",displayName = "审核状态 0-待提交 1-待审核 2-已审核")
	@ApiModelProperty(value = "审核状态 0-待提交 1-待审核 2-已审核")
	private Integer auditStatus;
	@Column(type = Types.VARCHAR,length = 255,nullable = false,name = "auditor",displayName = "审核人名称")
	@ApiModelProperty(value = "审核人名称")
	private String auditor;

	@ApiModelProperty(value = "欠账产品数")
	private Integer debtProductNum;

    public DispatchBillPlan() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.creator == null) {
			validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.branchId == null) {
			validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
				validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.num == null) {
			validResult.put("num", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.updator) > 13) {
			validResult.put("updator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.note) > 200) {
			validResult.put("note", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","status","created","creator","num","updated","updator","note");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("num")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("num",String.class))) {
				validResult.put("num", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("updated")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("updated",String.class))) {
				validResult.put("updated", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("creator")) {
			if (map.getTypedValue("creator",String.class)  == null) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
					validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("num")) {
			if (map.get("num")  == null) {
				validResult.put("num", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("updator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("updator",String.class)) > 13) {
				validResult.put("updator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("note")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("note",String.class)) > 13) {
				validResult.put("note", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setCreator(String creator){
		this.creator=creator;
	}

	public String getCreator(){
		return creator;
	}

	public void setNum(Integer num){
		this.num=num;
	}

	public Integer getNum(){
		return num;
	}

	public void setUpdated(Date updated){
		this.updated=updated;
	}

	public Date getUpdated(){
		return updated;
	}

	public void setUpdator(String updator){
		this.updator=updator;
	}

	public String getUpdator(){
		return updator;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getPlanTime() {
		return planTime;
	}

	public void setPlanTime(Date planTime) {
		this.planTime = planTime;
	}

	public Integer getActualNum() {
		return actualNum;
	}

	public void setActualNum(Integer actualNum) {
		this.actualNum = actualNum;
	}

	public Integer getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditor() {
		return auditor;
	}

	public void setAuditor(String auditor) {
		this.auditor = auditor;
	}

	public Integer getDebtProductNum() {
		return debtProductNum;
	}

	public void setDebtProductNum(Integer debtProductNum) {
		this.debtProductNum = debtProductNum;
	}
}
