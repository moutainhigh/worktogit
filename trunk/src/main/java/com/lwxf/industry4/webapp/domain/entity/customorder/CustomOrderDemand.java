package com.lwxf.industry4.webapp.domain.entity.customorder;
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
 * 功能：custom_order_demand 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-07-15 02:31
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "custom_order_demand", displayName = "custom_order_demand")
public class CustomOrderDemand extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.LONGVARCHAR,name = "content",displayName = "")
	private String content;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "需求名称：可以是衣柜、橱柜等，也可以是厨房、客厅、卧室等")
	private String name;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "custom_order_id",displayName = "定制订单id")
	private String customOrderId;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "no",displayName = "设计需求的编号")
	private String no;
	@Column(type = Types.CHAR,length = 13,name = "design_scheme",displayName = "参考设计案例")
	private String designScheme;
	@Column(type = Types.CHAR,length = 13,name = "product_series",displayName = "产品系列")
	private String productSeries;
	@Column(type = Types.VARCHAR,length = 50,name = "product_model",displayName = "产品型号")
	private String productModel;
	@Column(type = Types.VARCHAR,length = 50,name = "door_texture",displayName = "门板材质")
	private String doorTexture;
	@Column(type = Types.VARCHAR,length = 50,name = "door_color",displayName = "门板颜色")
	private String doorColor;
	@Column(type = Types.VARCHAR,length = 50,name = "door_model",displayName = "门板门型")
	private String doorModel;
	@Column(type = Types.VARCHAR,length = 50,name = "cabinet_texture",displayName = "柜体材质")
	private String cabinetTexture;
	@Column(type = Types.VARCHAR,length = 50,name = "cabinet_color",displayName = "柜体颜色")
	private String cabinetColor;
	@Column(type = Types.VARCHAR,length = 50,name = "cabinet_series",displayName = "柜体系列")
	private String cabinetSeries;
	@Column(type = Types.VARCHAR,length = 50,name = "handle_style",displayName = "拉手样式")
	private String handleStyle;
	@Column(type = Types.VARCHAR,length = 50,name = "handle_color",displayName = "拉手颜色")
	private String handleColor;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "order_product_id",displayName = "订单产品Id")
	private String orderProductId;

	public CustomOrderDemand() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 50) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.creator == null) {
			validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.created == null) {
			validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.customOrderId == null) {
			validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
				validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.no == null) {
			validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.no) > 50) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (LwxfStringUtils.getStringLength(this.designScheme) > 13) {
			validResult.put("designScheme", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.productSeries) > 13) {
			validResult.put("productSeries", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.productModel) > 50) {
			validResult.put("productModel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.doorTexture) > 50) {
			validResult.put("doorTexture", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.doorColor) > 50) {
			validResult.put("doorColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.doorModel) > 50) {
			validResult.put("doorModel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.cabinetTexture) > 50) {
			validResult.put("cabinetTexture", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.cabinetColor) > 50) {
			validResult.put("cabinetColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.cabinetSeries) > 50) {
			validResult.put("cabinetSeries", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.handleStyle) > 50) {
			validResult.put("handleStyle", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.handleColor) > 50) {
			validResult.put("handleColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (this.orderProductId == null) {
			validResult.put("orderProductId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.orderProductId) > 13) {
				validResult.put("orderProductId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("content","name","creator","created","no","designScheme","productSeries","productModel","doorTexture","doorColor","doorModel","cabinetTexture","cabinetColor","cabinetSeries","handleStyle","handleColor");

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
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
					validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
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
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("no")) {
			if (map.getTypedValue("no",String.class)  == null) {
				validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("no",String.class)) > 50) {
					validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
				}
			}
		}
		if(map.containsKey("designScheme")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("designScheme",String.class)) > 13) {
				validResult.put("designScheme", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("productSeries")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productSeries",String.class)) > 13) {
				validResult.put("productSeries", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("productModel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productModel",String.class)) > 50) {
				validResult.put("productModel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("doorTexture")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("doorTexture",String.class)) > 50) {
				validResult.put("doorTexture", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("doorColor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("doorColor",String.class)) > 50) {
				validResult.put("doorColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("doorModel")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("doorModel",String.class)) > 50) {
				validResult.put("doorModel", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("cabinetTexture")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cabinetTexture",String.class)) > 50) {
				validResult.put("cabinetTexture", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("cabinetColor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cabinetColor",String.class)) > 50) {
				validResult.put("cabinetColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("cabinetSeries")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("cabinetSeries",String.class)) > 50) {
				validResult.put("cabinetSeries", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("handleStyle")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("handleStyle",String.class)) > 50) {
				validResult.put("handleStyle", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("handleColor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("handleColor",String.class)) > 50) {
				validResult.put("handleColor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setContent(String content){
		this.content=content;
	}

	public String getContent(){
		return content;
	}

	public void setName(String name){
		this.name=name;
	}

	public String getName(){
		return name;
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

	public void setCustomOrderId(String customOrderId){
		this.customOrderId=customOrderId;
	}

	public String getCustomOrderId(){
		return customOrderId;
	}

	public void setNo(String no){
		this.no=no;
	}

	public String getNo(){
		return no;
	}

	public void setDesignScheme(String designScheme){
		this.designScheme=designScheme;
	}

	public String getDesignScheme(){
		return designScheme;
	}

	public void setProductSeries(String productSeries){
		this.productSeries=productSeries;
	}

	public String getProductSeries(){
		return productSeries;
	}

	public void setProductModel(String productModel){
		this.productModel=productModel;
	}

	public String getProductModel(){
		return productModel;
	}

	public void setDoorTexture(String doorTexture){
		this.doorTexture=doorTexture;
	}

	public String getDoorTexture(){
		return doorTexture;
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

	public void setCabinetTexture(String cabinetTexture){
		this.cabinetTexture=cabinetTexture;
	}

	public String getCabinetTexture(){
		return cabinetTexture;
	}

	public void setCabinetColor(String cabinetColor){
		this.cabinetColor=cabinetColor;
	}

	public String getCabinetColor(){
		return cabinetColor;
	}

	public void setCabinetSeries(String cabinetSeries){
		this.cabinetSeries=cabinetSeries;
	}

	public String getCabinetSeries(){
		return cabinetSeries;
	}

	public void setHandleStyle(String handleStyle){
		this.handleStyle=handleStyle;
	}

	public String getHandleStyle(){
		return handleStyle;
	}

	public void setHandleColor(String handleColor){
		this.handleColor=handleColor;
	}

	public String getHandleColor(){
		return handleColor;
	}

	public void setOrderProductId(String orderProductId){
		this.orderProductId=orderProductId;
	}

	public String getOrderProductId(){
		return orderProductId;
	}
}
