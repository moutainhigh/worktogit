package com.lwxf.industry4.webapp.domain.entity.supplier;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：supplier_product 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-28 09:18 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value = "供应商产品信息",discriminator = "供应商产品信息")
@Table(name = "supplier_product",displayName = "supplier_product")
public class SupplierProduct extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "供应商id")
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "supplier_id",displayName = "供应商id：即company的类型为供应商的公司id")
	private String supplierId;
	@ApiModelProperty(value = "原材料id")
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "material_id",displayName = "原材料id")
	private String materialId;
	@ApiModelProperty(value = "产品名称")
	@Column(type = Types.VARCHAR,length = 20,nullable = false,updatable = false,name = "name",displayName = "产品名称，关联bascode表中的供应商产品类型")
	private String name;
	@ApiModelProperty(value = "产品颜色")
	@Column(type = Types.VARCHAR,length = 20,nullable = false,updatable = false,name = "color",displayName = "颜色")
	private String color;
	@ApiModelProperty(value = "供应商报价")
	@Column(type = Types.VARCHAR,nullable = false,name = "price",displayName = "供应商报价")
	private String price;
	@ApiModelProperty(value = "创建人")
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人")
	private String creator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
	private Date created;
	@Column(type = Types.FLOAT,nullable = false,updatable = false,name = "supply_time",displayName = "产品供应周期")
	private Float supplyTime;
	@Column(type = Types.VARCHAR,length = 200,name = "notes",displayName = "供应商的产品说明")
	private String notes;
	@Column(type = Types.INTEGER,length = 11,name = "material_level",displayName = "原材料等级")
	private Integer materialLevel;
	@Column(type = Types.INTEGER,length = 11,name = "min_count",displayName = "最小采购数量")
	private Integer minCount;
	@Column(type = Types.VARCHAR,length = 10,name = "min_money",displayName = "最小采购金额")
	private String minMoney;
	@ApiModelProperty(value = "附件列表")
	List<UploadFiles> files;



	public Integer getMaterialLevel() {
		return materialLevel;
	}

	public void setMaterialLevel(Integer materialLevel) {
		this.materialLevel = materialLevel;
	}

	public Integer getMinCount() {
		return minCount;
	}

	public void setMinCount(Integer minCount) {
		this.minCount = minCount;
	}

    public String getMinMoney() {
        return minMoney;
    }

    public void setMinMoney(String minMoney) {
        this.minMoney = minMoney;
    }

    public SupplierProduct() {
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.supplierId == null) {
			validResult.put("supplierId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.supplierId) > 13) {
				validResult.put("supplierId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.name == null) {
			validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
 			if (LwxfStringUtils.getStringLength(this.name) > 13) {
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
		if (LwxfStringUtils.getStringLength(this.price) > 200) {
			validResult.put("price", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 200) {
			validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("price","notes");

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
		if(map.containsKey("price")) {
			if (!DataValidatorUtils.isDecmal4(new BigDecimal(map.getTypedValue("price",String.class)).toPlainString())) {
				validResult.put("price", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
			if (map.getTypedValue("price",BigDecimal.class).compareTo(new BigDecimal(100000000))!=-1){
				validResult.put("price",AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes",String.class)) > 200) {
				validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public List<UploadFiles> getFiles() {
		return files;
	}

	public void setFiles(List<UploadFiles> files) {
		this.files = files;
	}

	public String getColor() {return color;}

	public void setColor(String color) {this.color = color;}

	public Float getSupplyTime() {
		return supplyTime;
	}

	public void setSupplyTime(Float supplyTime) {
		this.supplyTime = supplyTime;
	}

	public void setSupplierId(String supplierId){
		this.supplierId=supplierId;
	}

	public String getSupplierId(){
		return supplierId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
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

	public void setNotes(String notes){
		this.notes=notes;
	}

	public String getNotes(){
		return notes;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}
}
