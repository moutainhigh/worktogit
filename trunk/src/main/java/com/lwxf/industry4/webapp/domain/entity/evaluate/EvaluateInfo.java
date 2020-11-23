package  com.lwxf.industry4.webapp.domain.entity.evaluate;
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
 * 功能：evaluate_info 实体类
 *
 * @author：lyh
 * @created：2019-11-30 02:08 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */ 
@ApiModel(value="evaluate_info对象", description="evaluate_info")
@Table(name = "evaluate_info",displayName = "evaluate_info")
public class EvaluateInfo extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 255,name = "evaluate_order_id",displayName = "评价订单id")
	@ApiModelProperty(value = "评价订单id")
	private String evaluateOrderId;
	@Column(type = Types.VARCHAR,length = 255,name = "evaluate_prduct_id",displayName = "评价商品id")
	@ApiModelProperty(value = "评价商品id")
	private String evaluatePrductId;
	@Column(type = Types.INTEGER,name = "evaluate_type",displayName = "1.客户评价 2.商家回复 3.客户追评")
	@ApiModelProperty(value = "1.客户评价 2.商家回复 3.客户追评")
	private Integer evaluateType;
	@Column(type = Types.INTEGER,name = "star_level",displayName = "整体评价星级1、2、3、4、5")
	@ApiModelProperty(value = "整体评价星级1、2、3、4、5")
	private Integer starLevel;
	@Column(type = Types.INTEGER,name = "star_level_server",displayName = "服务评价星级1、2、3、4、5 ")
	@ApiModelProperty(value = "服务评价星级1、2、3、4、5 ")
	private Integer starLevelServer;
	@Column(type = Types.INTEGER,name = "star_level_logistics",displayName = "物流整体评价星级1、2、3、4、5")
	@ApiModelProperty(value = "物流整体评价星级1、2、3、4、5")
	private Integer starLevelLogistics;
	@Column(type = Types.VARCHAR,length = 2550,name = "evaluate_content",displayName = "评价内容")
	@ApiModelProperty(value = "评价内容")
	private String evaluateContent;
	@Column(type = Types.INTEGER,name = "hide_name",displayName = "是否匿名评价 0隐藏 1显示")
	@ApiModelProperty(value = "是否匿名评价 0隐藏 1显示")
	private Integer hideName;
	@Column(type = Types.VARCHAR,length = 255,name = "evaluate_user_id",displayName = "评价客户id")
	@ApiModelProperty(value = "评价客户id")
	private String evaluateUserId;
	@Column(type = Types.VARCHAR,length = 255,name = "evaluate_user_name",displayName = "评价客户姓名")
	@ApiModelProperty(value = "评价客户姓名")
	private String evaluateUserName;
	@Column(type = Types.INTEGER,name = "evaluate_like_num",displayName = "评价点赞数量")
	@ApiModelProperty(value = "评价点赞数量")
	private Integer evaluateLikeNum;
	@Column(type = Types.INTEGER,name = "evaluate_look_num",displayName = "评价观看数量")
	@ApiModelProperty(value = "评价观看数量")
	private Integer evaluateLookNum;
	@Column(type = Types.VARCHAR,length = 255,name = "evaluate_parent_id",displayName = "评价父id(在类型是追评和商家回复时候有效)")
	@ApiModelProperty(value = "评价父id(在类型是追评和商家回复时候有效)")
	private String evaluateParentId;
	@Column(type = TypesExtend.DATETIME,name = "evaluate_Date",displayName = "评价时间")
	@ApiModelProperty(value = "评价时间")
	private Date evaluateDate;
	@Column(type = Types.VARCHAR,length = 255,name = "customer_name",displayName = "终端客户姓名")
	@ApiModelProperty(value = "终端客户姓名")
	private String customerName;
	@Column(type = Types.VARCHAR,length = 255,name = "customer_phone",displayName = "终端客户电话")
	@ApiModelProperty(value = "终端客户电话")
	private String customerPhone;
	@Column(type = Types.VARCHAR,length = 1,name = "hade_evaluate",displayName = "是否隐藏评价1显示0隐藏")
	@ApiModelProperty(value = "是否隐藏评价1显示0隐藏")
	private String hadeEvaluate;
	@Column(type = Types.VARCHAR,length = 255,name = "Reserve",displayName = "备用字段")
	@ApiModelProperty(value = "备用字段")
	private String Reserve;
	@Column(type = Types.VARCHAR,length = 255,name = "Reserve1",displayName = "备用字段1")
	@ApiModelProperty(value = "备用字段1")
	private String Reserve1;
	@Column(type = Types.VARCHAR,length = 255,name = "Reserve2",displayName = "备用字段2")
	@ApiModelProperty(value = "备用字段2")
	private String Reserve2;
	@Column(type = Types.VARCHAR,length = 255,name = "Reserve3",displayName = "备用字段3")
	@ApiModelProperty(value = "备用字段3")
	private String Reserve3;
	@Column(type = Types.VARCHAR,length = 255,name = "Reserve4",displayName = "备用字段4")
	@ApiModelProperty(value = "备用字段4")
	private String Reserve4;
	@Column(type = Types.VARCHAR,length = 255,name = "Reserve5",displayName = "备用字段5")
	@ApiModelProperty(value = "备用字段5")
	private String Reserve5;

	@ApiModelProperty(value = "附件id")
	private String[] fileIds;

	public String[] getFileIds() {
		return fileIds;
	}

	public void setFileIds(String[] fileIds) {
		this.fileIds = fileIds;
	}

	public EvaluateInfo() {
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.evaluateOrderId) > 255) {
			validResult.put("evaluateOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.evaluatePrductId) > 255) {
			validResult.put("evaluatePrductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.evaluateContent) > 2550) {
			validResult.put("evaluateContent", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.evaluateUserId) > 255) {
			validResult.put("evaluateUserId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.evaluateUserName) > 255) {
			validResult.put("evaluateUserName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.evaluateParentId) > 255) {
			validResult.put("evaluateParentId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.customerName) > 255) {
			validResult.put("customerName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.customerPhone) > 255) {
			validResult.put("customerPhone", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.hadeEvaluate) > 1) {
			validResult.put("hadeEvaluate", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.Reserve) > 255) {
			validResult.put("Reserve", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.Reserve1) > 255) {
			validResult.put("Reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.Reserve2) > 255) {
			validResult.put("Reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.Reserve3) > 255) {
			validResult.put("Reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.Reserve4) > 255) {
			validResult.put("Reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.Reserve5) > 255) {
			validResult.put("Reserve5", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","evaluateOrderId","evaluatePrductId","evaluateType","starLevel","starLevelServer","starLevelLogistics","evaluateContent","hideName","evaluateUserId","evaluateUserName","evaluateLikeNum","evaluateLookNum","evaluateParentId","evaluateDate","customerName","customerPhone","hadeEvaluate","Reserve","Reserve1","Reserve2","Reserve3","Reserve4","Reserve5");

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
		if(map.containsKey("evaluateType")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("evaluateType",String.class))) {
				validResult.put("evaluateType", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("starLevel")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("starLevel",String.class))) {
				validResult.put("starLevel", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("starLevelServer")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("starLevelServer",String.class))) {
				validResult.put("starLevelServer", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("starLevelLogistics")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("starLevelLogistics",String.class))) {
				validResult.put("starLevelLogistics", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("hideName")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("hideName",String.class))) {
				validResult.put("hideName", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("evaluateLikeNum")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("evaluateLikeNum",String.class))) {
				validResult.put("evaluateLikeNum", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("evaluateLookNum")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("evaluateLookNum",String.class))) {
				validResult.put("evaluateLookNum", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("evaluateDate")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("evaluateDate",String.class))) {
				validResult.put("evaluateDate", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("evaluateOrderId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("evaluateOrderId",String.class)) > 255) {
				validResult.put("evaluateOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("evaluatePrductId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("evaluatePrductId",String.class)) > 255) {
				validResult.put("evaluatePrductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("evaluateContent")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("evaluateContent",String.class)) > 2550) {
				validResult.put("evaluateContent", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("evaluateUserId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("evaluateUserId",String.class)) > 255) {
				validResult.put("evaluateUserId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("evaluateUserName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("evaluateUserName",String.class)) > 255) {
				validResult.put("evaluateUserName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("evaluateParentId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("evaluateParentId",String.class)) > 255) {
				validResult.put("evaluateParentId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("customerName")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("customerName",String.class)) > 255) {
				validResult.put("customerName", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("customerPhone")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("customerPhone",String.class)) > 255) {
				validResult.put("customerPhone", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("hadeEvaluate")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("hadeEvaluate",String.class)) > 1) {
				validResult.put("hadeEvaluate", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("Reserve")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("Reserve",String.class)) > 255) {
				validResult.put("Reserve", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("Reserve1")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("Reserve1",String.class)) > 255) {
				validResult.put("Reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("Reserve2")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("Reserve2",String.class)) > 255) {
				validResult.put("Reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("Reserve3")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("Reserve3",String.class)) > 255) {
				validResult.put("Reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("Reserve4")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("Reserve4",String.class)) > 255) {
				validResult.put("Reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("Reserve5")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("Reserve5",String.class)) > 255) {
				validResult.put("Reserve5", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setEvaluateOrderId(String evaluateOrderId){
		this.evaluateOrderId=evaluateOrderId;
	}

	public String getEvaluateOrderId(){
		return evaluateOrderId;
	}

	public void setEvaluatePrductId(String evaluatePrductId){
		this.evaluatePrductId=evaluatePrductId;
	}

	public String getEvaluatePrductId(){
		return evaluatePrductId;
	}

	public void setEvaluateType(Integer evaluateType){
		this.evaluateType=evaluateType;
	}

	public Integer getEvaluateType(){
		return evaluateType;
	}

	public void setStarLevel(Integer starLevel){
		this.starLevel=starLevel;
	}

	public Integer getStarLevel(){
		return starLevel;
	}

	public void setStarLevelServer(Integer starLevelServer){
		this.starLevelServer=starLevelServer;
	}

	public Integer getStarLevelServer(){
		return starLevelServer;
	}

	public void setStarLevelLogistics(Integer starLevelLogistics){
		this.starLevelLogistics=starLevelLogistics;
	}

	public Integer getStarLevelLogistics(){
		return starLevelLogistics;
	}

	public void setEvaluateContent(String evaluateContent){
		this.evaluateContent=evaluateContent;
	}

	public String getEvaluateContent(){
		return evaluateContent;
	}

	public void setHideName(Integer hideName){
		this.hideName=hideName;
	}

	public Integer getHideName(){
		return hideName;
	}

	public void setEvaluateUserId(String evaluateUserId){
		this.evaluateUserId=evaluateUserId;
	}

	public String getEvaluateUserId(){
		return evaluateUserId;
	}

	public void setEvaluateUserName(String evaluateUserName){
		this.evaluateUserName=evaluateUserName;
	}

	public String getEvaluateUserName(){
		return evaluateUserName;
	}

	public void setEvaluateLikeNum(Integer evaluateLikeNum){
		this.evaluateLikeNum=evaluateLikeNum;
	}

	public Integer getEvaluateLikeNum(){
		return evaluateLikeNum;
	}

	public void setEvaluateLookNum(Integer evaluateLookNum){
		this.evaluateLookNum=evaluateLookNum;
	}

	public Integer getEvaluateLookNum(){
		return evaluateLookNum;
	}

	public void setEvaluateParentId(String evaluateParentId){
		this.evaluateParentId=evaluateParentId;
	}

	public String getEvaluateParentId(){
		return evaluateParentId;
	}

	public void setEvaluateDate(Date evaluateDate){
		this.evaluateDate=evaluateDate;
	}

	public Date getEvaluateDate(){
		return evaluateDate;
	}

	public void setCustomerName(String customerName){
		this.customerName=customerName;
	}

	public String getCustomerName(){
		return customerName;
	}

	public void setCustomerPhone(String customerPhone){
		this.customerPhone=customerPhone;
	}

	public String getCustomerPhone(){
		return customerPhone;
	}

	public void setHadeEvaluate(String hadeEvaluate){
		this.hadeEvaluate=hadeEvaluate;
	}

	public String getHadeEvaluate(){
		return hadeEvaluate;
	}

	public void setReserve(String Reserve){
		this.Reserve=Reserve;
	}

	public String getReserve(){
		return Reserve;
	}

	public void setReserve1(String Reserve1){
		this.Reserve1=Reserve1;
	}

	public String getReserve1(){
		return Reserve1;
	}

	public void setReserve2(String Reserve2){
		this.Reserve2=Reserve2;
	}

	public String getReserve2(){
		return Reserve2;
	}

	public void setReserve3(String Reserve3){
		this.Reserve3=Reserve3;
	}

	public String getReserve3(){
		return Reserve3;
	}

	public void setReserve4(String Reserve4){
		this.Reserve4=Reserve4;
	}

	public String getReserve4(){
		return Reserve4;
	}

	public void setReserve5(String Reserve5){
		this.Reserve5=Reserve5;
	}

	public String getReserve5(){
		return Reserve5;
	}
}
