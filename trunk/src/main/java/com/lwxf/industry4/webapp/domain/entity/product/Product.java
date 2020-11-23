package com.lwxf.industry4.webapp.domain.entity.product;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
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
 * 功能：product 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2019-03-08 10:27
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "product",displayName = "product")
@ApiModel(value = "产品信息",description = "产品信息")
public class Product extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "name",displayName = "产品名称")
	@ApiModelProperty(value = "产品名称")
	private String name;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "简介")
	@ApiModelProperty(value = "简介")
	private String notes;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "product_category_id",displayName = "产品用途")
	@ApiModelProperty(value = "产品用途")
	private String productCategoryId;
	@Column(type = Types.VARCHAR,length = 50,name = "product_material",displayName = "产品材质")
	@ApiModelProperty(value = "产品材质")
	private String productMaterial;
	@Column(type = Types.VARCHAR,length = 50,name = "product_color",displayName = "产品颜色")
	@ApiModelProperty(value = "产品颜色")
	private String productColor;
	@Column(type = Types.VARCHAR,length = 50,name = "product_spec",displayName = "产品尺寸")
	@ApiModelProperty(value = "产品尺寸")
	private String productSpec;
	@Column(type = Types.TINYINT,nullable = false,name = "status",displayName = "产品状态：0 - 正常；1 - 禁用；")
	@ApiModelProperty(value = "产品状态")
	private Integer status;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人")
	@ApiModelProperty(value = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	@ApiModelProperty(value = "创建时间")
	private Date created;
	@Column(type = Types.TINYINT,nullable = false,name = "unit",displayName = "计算单位：0 - 个；1 - 张；2 - 米；3 - 平米；4 - 包，定义为枚举类")
	@ApiModelProperty(value = "计算单位")
	private Integer unit;
	@Column(type = Types.TINYINT,name = "type",displayName = "产品用途Id")
	@ApiModelProperty(value = "产品用途Id")
	private Integer type;
	@Column(type = Types.INTEGER,name = "thickness",displayName = "厚度(mm 毫米)")
	@ApiModelProperty(value = "厚度(mm 毫米)")
	private Integer thickness;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "factory_price",displayName = "吊柜市场价")
	@ApiModelProperty(value = "吊柜市场价")
	private BigDecimal factoryPriceWall;
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "model",displayName = "型号")
	@ApiModelProperty(value = "型号")
	private String model;
	@Column(type = Types.VARCHAR,length = 50,name = "series",displayName = "系列")
	@ApiModelProperty(value = "系列")
	private String series;
	@Column(type = Types.VARCHAR,length = 200,name = "remarks",displayName = "备注")
	@ApiModelProperty(value = "备注")
	private String remarks;
	@Column(type = Types.BIT,defaultValue = "0",nullable = false,name = "is_lower_shelf",displayName = "是否推荐 0 false 不推荐 1 true 推荐")
	@ApiModelProperty(value = "是否推荐 0 false 不推荐 1 true 推荐")
	private Boolean lowerShelf;
	@ApiModelProperty(value = "企业Id")
	private String branchId;;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "factory_price",displayName = "地柜市场价")
	@ApiModelProperty(value = "地柜市场价")
	private BigDecimal factoryPriceFloor;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "factory_price",displayName = "吊柜拿货价")
	@ApiModelProperty(value = "吊柜拿货价")
	private BigDecimal takeAwayPriceWall;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "factory_price",displayName = "地柜拿货价")
	@ApiModelProperty(value = "地柜拿货价")
	private BigDecimal takeAwayPriceFloor;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "factory_price",displayName = "吊柜上样价")
	@ApiModelProperty(value = "吊柜上样价")
	private BigDecimal samplePriceWall;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,name = "factory_price",displayName = "地柜上样价")
	@ApiModelProperty(value = "地柜上样价")
	private BigDecimal samplePriceFloor;


	public Product() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.name == null) {
			validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.name) > 50) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.productCategoryId == null) {
			validResult.put("productCategoryId", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.productCategoryId) > 13) {
				validResult.put("productCategoryId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.productMaterial) > 50) {
			validResult.put("productMaterial", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.productColor) > 50) {
			validResult.put("productColor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.productSpec) > 50) {
			validResult.put("productSpec", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
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
		if (this.unit == null) {
			validResult.put("unit", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.model == null) {
			validResult.put("model", ErrorCodes.VALIDATE_NOTNULL);
		}else{
			if (LwxfStringUtils.getStringLength(this.model) > 50) {
				validResult.put("model", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.series) > 50) {
			validResult.put("series", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.remarks) > 200) {
			validResult.put("remarks", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.lowerShelf == null) {
			validResult.put("lowerShelf", ErrorCodes.VALIDATE_NOTNULL);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("name","notes","productCategoryId","productMaterial","productColor","productSpec","status","unit","type","thickness",
			"factoryPriceWall","model","series","remarks","lowerShelf","factoryPriceFloor","takeAwayPriceWall","takeAwayPriceFloor","samplePriceWall","samplePriceFloor");

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
		if(map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status",String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("unit")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("unit",String.class))) {
				validResult.put("unit", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("thickness")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("thickness",String.class))) {
				validResult.put("thickness", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("factoryPrice")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("factoryPrice",String.class))) {
				validResult.put("factoryPrice", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("lowerShelf")) {
			if (!DataValidatorUtils.isBoolean(map.getTypedValue("lowerShelf",String.class))) {
				validResult.put("lowerShelf", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("name")) {
			if (map.getTypedValue("name",String.class)  == null) {
				validResult.put("name", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 50) {
					validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("productCategoryId")) {
			if (map.getTypedValue("productCategoryId",String.class)  == null) {
				validResult.put("productCategoryId", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("productCategoryId",String.class)) > 13) {
					validResult.put("productCategoryId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("productMaterial")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productMaterial",String.class)) > 50) {
				validResult.put("productMaterial", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("productColor")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productColor",String.class)) > 50) {
				validResult.put("productColor", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("productSpec")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("productSpec",String.class)) > 50) {
				validResult.put("productSpec", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("status")) {
			if (map.get("status")  == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("unit")) {
			if (map.get("unit")  == null) {
				validResult.put("unit", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("model")) {
			if (map.getTypedValue("model",String.class)  == null) {
				validResult.put("model", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("model",String.class)) > 50) {
					validResult.put("model", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("series")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("series",String.class)) > 50) {
				validResult.put("series", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("remarks")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("remarks",String.class)) > 200) {
				validResult.put("remarks", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("lowerShelf")) {
			if (map.get("lowerShelf")  == null) {
				validResult.put("lowerShelf", ErrorCodes.VALIDATE_NOTNULL);
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

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public void setProductCategoryId(String productCategoryId){
		this.productCategoryId=productCategoryId;
	}

	public String getProductCategoryId(){
		return productCategoryId;
	}

	public void setProductMaterial(String productMaterial){
		this.productMaterial=productMaterial;
	}

	public String getProductMaterial(){
		return productMaterial;
	}

	public void setProductColor(String productColor){
		this.productColor=productColor;
	}

	public String getProductColor(){
		return productColor;
	}

	public void setProductSpec(String productSpec){
		this.productSpec=productSpec;
	}

	public String getProductSpec(){
		return productSpec;
	}

	public void setStatus(Integer status){
		this.status=status;
	}

	public Integer getStatus(){
		return status;
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

	public void setUnit(Integer unit){
		this.unit=unit;
	}

	public Integer getUnit(){
		return unit;
	}

	public void setType(Integer type){
		this.type=type;
	}

	public Integer getType(){
		return type;
	}

	public void setThickness(Integer thickness){
		this.thickness=thickness;
	}

	public Integer getThickness(){
		return thickness;
	}

	public void setModel(String model){
		this.model=model;
	}

	public String getModel(){
		return model;
	}

	public void setSeries(String series){
		this.series=series;
	}

	public String getSeries(){
		return series;
	}

	public void setRemarks(String remarks){
		this.remarks=remarks;
	}

	public String getRemarks(){
		return remarks;
	}

	public void setLowerShelf(Boolean lowerShelf){
		this.lowerShelf=lowerShelf;
	}

	public Boolean getLowerShelf(){
		return lowerShelf;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public BigDecimal getFactoryPriceWall() {
		return factoryPriceWall;
	}

	public void setFactoryPriceWall(BigDecimal factoryPriceWall) {
		this.factoryPriceWall = factoryPriceWall;
	}

	public BigDecimal getFactoryPriceFloor() {
		return factoryPriceFloor;
	}

	public void setFactoryPriceFloor(BigDecimal factoryPriceFloor) {
		this.factoryPriceFloor = factoryPriceFloor;
	}

	public BigDecimal getTakeAwayPriceWall() {
		return takeAwayPriceWall;
	}

	public void setTakeAwayPriceWall(BigDecimal takeAwayPriceWall) {
		this.takeAwayPriceWall = takeAwayPriceWall;
	}

	public BigDecimal getTakeAwayPriceFloor() {
		return takeAwayPriceFloor;
	}

	public void setTakeAwayPriceFloor(BigDecimal takeAwayPriceFloor) {
		this.takeAwayPriceFloor = takeAwayPriceFloor;
	}

	public BigDecimal getSamplePriceWall() {
		return samplePriceWall;
	}

	public void setSamplePriceWall(BigDecimal samplePriceWall) {
		this.samplePriceWall = samplePriceWall;
	}

	public BigDecimal getSamplePriceFloor() {
		return samplePriceFloor;
	}

	public void setSamplePriceFloor(BigDecimal samplePriceFloor) {
		this.samplePriceFloor = samplePriceFloor;
	}
}
