package com.lwxf.industry4.webapp.domain.entity.customorder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：produce_flow 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-04-11 05:36 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@Table(name = "produce_flow",displayName = "produce_flow")
@ApiModel(value = "生产流程信息",discriminator = "生产流程信息")
public class ProduceFlow extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.TINYINT,nullable = false,name = "node",displayName = "节点 0 备料 1 下料 2 封边 3 打孔 4 清洗 5 试装 6 包装")
	@ApiModelProperty(value = "节点 0 备料 1 下料 2 封边 3 打孔 4 清洗 5 试装 6 包装")
	private Integer node;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "end_time",displayName = "完成时间")
	@ApiModelProperty(value = "完成时间")
	private Date endTime;
	@Column(type = Types.VARCHAR,length = 300,name = "notes",displayName = "备注")
	@ApiModelProperty(value = "备注")
	private String notes;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "operator",displayName = "操作人")
	@ApiModelProperty(value = "操作人")
	private String operator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "operation_time",displayName = "操作时间")
	@ApiModelProperty(value = "操作时间")
	private Date operationTime;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "produce_order_id",displayName = "生产拆单明细表主键ID")
	@ApiModelProperty(value = "生产拆单明细表主键ID")
	private String produceOrderId;

    public ProduceFlow() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.node == null) {
			validResult.put("node", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 300) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.operator == null) {
			validResult.put("operator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.operator) > 13) {
				validResult.put("operator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.produceOrderId == null) {
			validResult.put("produceOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.produceOrderId) > 13) {
				validResult.put("produceOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("node","notes");

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
		if(map.containsKey("node")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("node",String.class))) {
				validResult.put("node", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("node")) {
			if (map.get("node")  == null) {
				validResult.put("node", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 300) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setNode(Integer node){
		this.node=node;
	}

	public Integer getNode(){
		return node;
	}

	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	public Date getEndTime(){
		return endTime;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setOperator(String operator){
		this.operator=operator;
	}

	public String getOperator(){
		return operator;
	}

	public void setOperationTime(Date operationTime){
		this.operationTime=operationTime;
	}

	public Date getOperationTime(){
		return operationTime;
	}

	public void setProduceOrderId(String produceOrderId){
		this.produceOrderId=produceOrderId;
	}

	public String getProduceOrderId(){
		return produceOrderId;
	}
}
