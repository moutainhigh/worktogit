package com.lwxf.industry4.webapp.domain.entity.system;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.*;

/**
 * 功能：sys_sms 实体类
 *
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-20 05:31 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "sys_sms",displayName = "sys_sms")
public class SysSms extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "消息名称")
	@Column(type = Types.VARCHAR,length = 255,name = "name",displayName = "消息名称")
	private String name;
	@ApiModelProperty(value = "业务节点类型")
	@Column(type = Types.TINYINT,nullable = false,name = "node_type",displayName = "业务节点类型：0 - 传单；1 - 下单；2 - 财务扣款；3 - 生产；4 - 物流发货；5 - 订单催款；")
	private Byte nodeType;
	@ApiModelProperty(value = "短信模板ID")
	@Column(type = Types.CHAR,length = 13,name = "sms_template_id",displayName = "短信模板ID")
	private String smsTemplateId;
	@ApiModelProperty(value = "短信状态：0 - 启用；1 - 禁用")
	@Column(type = Types.TINYINT,name = "sms_status",displayName = "短信状态：0 - 启用；1 - 禁用")
	private Byte smsStatus;
	@ApiModelProperty(value = "微信模板ID")
	@Column(type = Types.CHAR,length = 13,name = "wx_template_id",displayName = "微信模板ID")
	private String wxTemplateId;
	@ApiModelProperty(value = "微信状态：0 - 启用；1 - 禁用")
	@Column(type = Types.TINYINT,name = "wx_status",displayName = "微信状态：0 - 启用；1 - 禁用")
	private Byte wxStatus;
	@ApiModelProperty(value = "排序")
	@Column(type = Types.INTEGER,defaultValue = "0",name = "order_num",displayName = "排序")
	private Integer orderNum;
	@ApiModelProperty(value = "允许发送消息状态：0 - 启用；1 - 禁用 (默认启用)")
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "允许发送消息状态：0 - 启用；1 - 禁用 (默认启用)")
	private Byte status;
	@ApiModelProperty(value = "备注")
	@Column(type = Types.VARCHAR,length = 255,name = "remark",displayName = "备注")
	private String remark;
	@ApiModelProperty(value = "企业id")
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "企业id")
	private String branchId;
    public SysSms() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.name) > 255) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.nodeType == null) {
			validResult.put("nodeType", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.smsTemplateId) > 13) {
			validResult.put("smsTemplateId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.wxTemplateId) > 13) {
			validResult.put("wxTemplateId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.remark) > 255) {
			validResult.put("remark", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","name","nodeType","smsTemplateId","smsStatus","wxTemplateId","wxStatus","orderNum","status","remark");

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
		if(map.containsKey("nodeType")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("nodeType",String.class))) {
				validResult.put("nodeType", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("smsStatus")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("smsStatus",String.class))) {
				validResult.put("smsStatus", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("wxStatus")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("wxStatus",String.class))) {
				validResult.put("wxStatus", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("orderNum")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("orderNum",String.class))) {
				validResult.put("orderNum", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 255) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("nodeType")) {
			if (map.get("nodeType")  == null) {
				validResult.put("nodeType", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("smsTemplateId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("smsTemplateId",String.class)) > 13) {
				validResult.put("smsTemplateId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("wxTemplateId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("wxTemplateId",String.class)) > 13) {
				validResult.put("wxTemplateId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("remark")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("remark",String.class)) > 255) {
				validResult.put("remark", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public void setNodeType(Byte nodeType){
		this.nodeType=nodeType;
	}

	public Byte getNodeType(){
		return nodeType;
	}

	public void setSmsTemplateId(String smsTemplateId){
		this.smsTemplateId=smsTemplateId;
	}

	public String getSmsTemplateId(){
		return smsTemplateId;
	}

	public void setSmsStatus(Byte smsStatus){
		this.smsStatus=smsStatus;
	}

	public Byte getSmsStatus(){
		return smsStatus;
	}

	public void setWxTemplateId(String wxTemplateId){
		this.wxTemplateId=wxTemplateId;
	}

	public String getWxTemplateId(){
		return wxTemplateId;
	}

	public void setWxStatus(Byte wxStatus){
		this.wxStatus=wxStatus;
	}

	public Byte getWxStatus(){
		return wxStatus;
	}

	public void setOrderNum(Integer orderNum){
		this.orderNum=orderNum;
	}

	public Integer getOrderNum(){
		return orderNum;
	}

	public void setStatus(Byte status){
		this.status=status;
	}

	public Byte getStatus(){
		return status;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}

	public String getRemark(){
		return remark;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
