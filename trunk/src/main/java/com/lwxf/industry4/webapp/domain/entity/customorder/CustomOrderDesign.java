package com.lwxf.industry4.webapp.domain.entity.customorder;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

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
 * 功能：custom_order_design 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-07-09 04:01
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "custom_order_design",displayName = "custom_order_design")
@ApiModel(value = "订单设计信息",discriminator = "订单设计信息")
public class CustomOrderDesign extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "order_product_id",displayName = "订单产品主键ID")
	@ApiModelProperty(value = "订单产品主键ID")
	private String orderProductId;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "designer",displayName = "设计师用户id")
	@ApiModelProperty(value = "设计师用户id")
	private String designer;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.VARCHAR,length = 500,name = "notes",displayName = "设计说明")
	@ApiModelProperty(value = "设计说明")
	private String notes;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "状态：0 - 设计中；1 - 待审核；2 - 已审核")
	@ApiModelProperty(value = "状态：0 - 设计中；1 - 待审核；2 - 已审核")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "custom_order_id",displayName = "订单id")
	@ApiModelProperty(value = "订单id")
	private String customOrderId;
	@Column(type = TypesExtend.DATETIME,name = "start_time",displayName = "开始时间")
	@ApiModelProperty(value = "开始时间")
	private Date startTime;
	@Column(type = TypesExtend.DATETIME,name = "end_time",displayName = "设计完成时间")
	@ApiModelProperty(value = "设计完成时间")
	private Date endTime;
	@Column(type = TypesExtend.DATETIME,name = "examine_time",displayName = "审核时间")
	@ApiModelProperty(value = "审核时间")
	private Date examineTime;
	@Column(type = Types.CHAR,length = 13,name = "examine_user",displayName = "审核人")
	@ApiModelProperty(value = "审核人")
	private String examineUser;
	@Column(type = Types.VARCHAR,length = 50,name = "door_color",displayName = "门板颜色")
	@ApiModelProperty(value = "门板颜色")
	private String doorColor;
	@Column(type = Types.VARCHAR,length = 50,name = "door_model",displayName = "门板型号")
	@ApiModelProperty(value = "门板型号")
	private String doorModel;
	@Column(type = Types.VARCHAR,length = 50,name = "body_color",displayName = "柜体颜色")
	@ApiModelProperty(value = "柜体颜色")
	private String bodyColor;
	@Column(type = Types.VARCHAR,length = 50,name = "body_edge_size",displayName = "柜体封边尺寸")
	@ApiModelProperty(value = "柜体封边尺寸")
	private String bodyEdgeSize;
	@Column(type = Types.VARCHAR,length = 50,name = "ground_cabinet_h",displayName = "地柜高")
	@ApiModelProperty(value = "地柜高")
	private String groundCabinetH;
	@Column(type = Types.VARCHAR,length = 50,name = "ground_cabinet_d",displayName = "地柜深")
	@ApiModelProperty(value = "地柜深")
	private String groundCabinetD;
	@Column(type = Types.VARCHAR,length = 50,name = "cabinet_h",displayName = "吊柜高")
	@ApiModelProperty(value = "吊柜高")
	private String cabinetH;
	@Column(type = Types.VARCHAR,length = 50,name = "cabinet_d",displayName = "吊柜深")
	@ApiModelProperty(value = "吊柜深")
	private String cabinetD;
	@Column(type = Types.VARCHAR,length = 50,name = "mesa",displayName = "台面")
	@ApiModelProperty(value = "台面")
	private String mesa;
	@Column(type = Types.VARCHAR,length = 50,name = "handle_model",displayName = "拉手型号")
	@ApiModelProperty(value = "拉手型号")
	private String handleModel;
	@Column(type = Types.VARCHAR,length = 50,name = "hinge_model",displayName = "铰链型号")
	@ApiModelProperty(value = "铰链型号")
	private String hingeModel;
	@Column(type = Types.VARCHAR,length = 50,name = "slide_way_model",displayName = "滑道型号")
	@ApiModelProperty(value = "滑道型号")
	private String slideWayModel;
	@Column(type = Types.VARCHAR,length = 50,name = "lightbox_floor",displayName = "灯箱地板")
	@ApiModelProperty(value = "灯箱地板")
	private String lightboxFloor;
	@Column(type = Types.VARCHAR,length = 50,name = "led_model",displayName = "led灯型号")
	@ApiModelProperty(value = "led灯型号")
	private String ledModel;
	@Column(type = Types.VARCHAR,length = 50,name = "seasoning_basket",displayName = "调料拉篮")
	@ApiModelProperty(value = "调料拉篮")
	private String seasoningBasket;
	@Column(type = Types.VARCHAR,length = 50,name = "hoisting_code",displayName = "吊码")
	@ApiModelProperty(value = "吊码")
	private String hoistingCode;
	@Column(type = Types.VARCHAR,length = 50,name = "skirting",displayName = "踢脚")
	@ApiModelProperty(value = "踢脚")
	private String skirting;
	@Column(type = Types.VARCHAR,length = 50,name = "water_tank",displayName = "水槽")
	@ApiModelProperty(value = "水槽")
	private String waterTank;
	@Column(type = Types.VARCHAR,length = 50,name = "faucet",displayName = "龙头")
	@ApiModelProperty(value = "龙头")
	private String faucet;
	@Column(type = Types.VARCHAR,length = 50,name = "other",displayName = "其它")
	@ApiModelProperty(value = "其它")
	private String other;
	@Column(type = Types.VARCHAR,length = 50,name = "name",displayName = "产品品名")
	@ApiModelProperty(value = "产品品名")
	private String name;

	public CustomOrderDesign() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.orderProductId == null) {
			validResult.put("orderProductId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.orderProductId) > 13) {
				validResult.put("orderProductId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.designer == null) {
			validResult.put("designer", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.designer) > 13) {
				validResult.put("designer", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.created == null) {
			validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 500) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.customOrderId == null) {
			validResult.put("customOrderId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
				validResult.put("customOrderId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.examineUser) > 13) {
			validResult.put("examineUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.doorColor) > 50) {
			validResult.put("doorColor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.doorModel) > 50) {
			validResult.put("doorModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.bodyColor) > 50) {
			validResult.put("bodyColor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.bodyEdgeSize) > 50) {
			validResult.put("bodyEdgeSize", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.groundCabinetH) > 50) {
			validResult.put("groundCabinetH", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.groundCabinetD) > 50) {
			validResult.put("groundCabinetD", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.cabinetH) > 50) {
			validResult.put("cabinetH", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.cabinetD) > 50) {
			validResult.put("cabinetD", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.mesa) > 50) {
			validResult.put("mesa", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.handleModel) > 50) {
			validResult.put("handleModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.hingeModel) > 50) {
			validResult.put("hingeModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.slideWayModel) > 50) {
			validResult.put("slideWayModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.lightboxFloor) > 50) {
			validResult.put("lightboxFloor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.ledModel) > 50) {
			validResult.put("ledModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.seasoningBasket) > 50) {
			validResult.put("seasoningBasket", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.hoistingCode) > 50) {
			validResult.put("hoistingCode", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.skirting) > 50) {
			validResult.put("skirting", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.waterTank) > 50) {
			validResult.put("waterTank", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.faucet) > 50) {
			validResult.put("faucet", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.other) > 50) {
			validResult.put("other", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.name) > 50) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("designer","created","notes","status","startTime","endTime","examineTime","examineUser","doorColor","doorModel","bodyColor","bodyEdgeSize","groundCabinetH","groundCabinetD","cabinetH","cabinetD","mesa","handleModel","hingeModel","slideWayModel","lightboxFloor","ledModel","seasoningBasket","hoistingCode","skirting","waterTank","faucet","other","name");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("startTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("startTime",String.class))) {
				validResult.put("startTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("endTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("endTime",String.class))) {
				validResult.put("endTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("examineTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("examineTime",String.class))) {
				validResult.put("examineTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("designer")) {
			if (map.getTypedValue("designer",String.class)  == null) {
				validResult.put("designer", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("designer",String.class)) > 13) {
					validResult.put("designer", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 500) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("examineUser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("examineUser",String.class)) > 13) {
				validResult.put("examineUser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("doorColor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("doorColor",String.class)) > 50) {
				validResult.put("doorColor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("doorModel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("doorModel",String.class)) > 50) {
				validResult.put("doorModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("bodyColor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bodyColor",String.class)) > 50) {
				validResult.put("bodyColor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("bodyEdgeSize")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("bodyEdgeSize",String.class)) > 50) {
				validResult.put("bodyEdgeSize", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("groundCabinetH")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("groundCabinetH",String.class)) > 50) {
				validResult.put("groundCabinetH", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("groundCabinetD")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("groundCabinetD",String.class)) > 50) {
				validResult.put("groundCabinetD", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("cabinetH")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cabinetH",String.class)) > 50) {
				validResult.put("cabinetH", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("cabinetD")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cabinetD",String.class)) > 50) {
				validResult.put("cabinetD", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("mesa")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("mesa",String.class)) > 50) {
				validResult.put("mesa", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("handleModel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("handleModel",String.class)) > 50) {
				validResult.put("handleModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("hingeModel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("hingeModel",String.class)) > 50) {
				validResult.put("hingeModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("slideWayModel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("slideWayModel",String.class)) > 50) {
				validResult.put("slideWayModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("lightboxFloor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("lightboxFloor",String.class)) > 50) {
				validResult.put("lightboxFloor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("ledModel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("ledModel",String.class)) > 50) {
				validResult.put("ledModel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("seasoningBasket")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("seasoningBasket",String.class)) > 50) {
				validResult.put("seasoningBasket", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("hoistingCode")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("hoistingCode",String.class)) > 50) {
				validResult.put("hoistingCode", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("skirting")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("skirting",String.class)) > 50) {
				validResult.put("skirting", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("waterTank")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("waterTank",String.class)) > 50) {
				validResult.put("waterTank", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("faucet")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("faucet",String.class)) > 50) {
				validResult.put("faucet", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("other")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("other",String.class)) > 50) {
				validResult.put("other", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setOrderProductId(String orderProductId){
		this.orderProductId=orderProductId;
	}

	public String getOrderProductId(){
		return orderProductId;
	}

	public void setDesigner(String designer){
		this.designer=designer;
	}

	public String getDesigner(){
		return designer;
	}

	public void setCreated(Date created){
		this.created=created;
	}

	public Date getCreated(){
		return created;
	}

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
	}

	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setStartTime(Date startTime){
		this.startTime=startTime;
	}

	public Date getStartTime(){
		return startTime;
	}

	public void setEndTime(Date endTime){
		this.endTime=endTime;
	}

	public Date getEndTime(){
		return endTime;
	}

	public void setExamineTime(Date examineTime){
		this.examineTime=examineTime;
	}

	public Date getExamineTime(){
		return examineTime;
	}

	public void setExamineUser(String examineUser){
		this.examineUser=examineUser;
	}

	public String getExamineUser(){
		return examineUser;
	}

	public void setDoorColor(String doorColor){
		this.doorColor=doorColor;
	}

	public String getDoorColor(){
		return doorColor;
	}

	public void setDoorModel(String doorModel){
		this.doorModel=doorModel;
	}

	public String getDoorModel(){
		return doorModel;
	}

	public void setBodyColor(String bodyColor){
		this.bodyColor=bodyColor;
	}

	public String getBodyColor(){
		return bodyColor;
	}

	public void setBodyEdgeSize(String bodyEdgeSize){
		this.bodyEdgeSize=bodyEdgeSize;
	}

	public String getBodyEdgeSize(){
		return bodyEdgeSize;
	}

	public void setGroundCabinetH(String groundCabinetH){
		this.groundCabinetH=groundCabinetH;
	}

	public String getGroundCabinetH(){
		return groundCabinetH;
	}

	public void setGroundCabinetD(String groundCabinetD){
		this.groundCabinetD=groundCabinetD;
	}

	public String getGroundCabinetD(){
		return groundCabinetD;
	}

	public void setCabinetH(String cabinetH){
		this.cabinetH=cabinetH;
	}

	public String getCabinetH(){
		return cabinetH;
	}

	public void setCabinetD(String cabinetD){
		this.cabinetD=cabinetD;
	}

	public String getCabinetD(){
		return cabinetD;
	}

	public void setMesa(String mesa){
		this.mesa=mesa;
	}

	public String getMesa(){
		return mesa;
	}

	public void setHandleModel(String handleModel){
		this.handleModel=handleModel;
	}

	public String getHandleModel(){
		return handleModel;
	}

	public void setHingeModel(String hingeModel){
		this.hingeModel=hingeModel;
	}

	public String getHingeModel(){
		return hingeModel;
	}

	public void setSlideWayModel(String slideWayModel){
		this.slideWayModel=slideWayModel;
	}

	public String getSlideWayModel(){
		return slideWayModel;
	}

	public void setLightboxFloor(String lightboxFloor){
		this.lightboxFloor=lightboxFloor;
	}

	public String getLightboxFloor(){
		return lightboxFloor;
	}

	public void setLedModel(String ledModel){
		this.ledModel=ledModel;
	}

	public String getLedModel(){
		return ledModel;
	}

	public void setSeasoningBasket(String seasoningBasket){
		this.seasoningBasket=seasoningBasket;
	}

	public String getSeasoningBasket(){
		return seasoningBasket;
	}

	public void setHoistingCode(String hoistingCode){
		this.hoistingCode=hoistingCode;
	}

	public String getHoistingCode(){
		return hoistingCode;
	}

	public void setSkirting(String skirting){
		this.skirting=skirting;
	}

	public String getSkirting(){
		return skirting;
	}

	public void setWaterTank(String waterTank){
		this.waterTank=waterTank;
	}

	public String getWaterTank(){
		return waterTank;
	}

	public void setFaucet(String faucet){
		this.faucet=faucet;
	}

	public String getFaucet(){
		return faucet;
	}

	public void setOther(String other){
		this.other=other;
	}

	public String getOther(){
		return other;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
	}
}
