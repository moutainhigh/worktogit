package com.lwxf.industry4.webapp.domain.entity.supplier;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.sql.Types;
import java.util.*;
/**
 * 功能：material 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-08-01 11:22 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@ApiModel(value = "原材料信息",description = "原材料信息")
@Table(name = "material",displayName = "material")
public class Material extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "产品名称",name = "name")
	@Column(type = Types.VARCHAR,length = 40,name = "name",displayName = "产品名称")
	private String name;
	@ApiModelProperty(value = "颜色",name = "color",example="001")
	@Column(type = Types.VARCHAR,length = 20,name = "color")
	private String color;
	@ApiModelProperty(value = "供应的原材料等级",name = "materialLevel")
	@Column(type = Types.INTEGER,name = "material_level",displayName = "供应的原材料等级")
	private Integer materialLevel;
	@ApiModelProperty(value = "创建时间",name = "created")
	@Column(type = TypesExtend.DATETIME,name = "created",displayName = "")
	private Date created;
	@ApiModelProperty(value = "创建人",name = "creator")
	@Column(type = Types.CHAR,length = 13,name = "creator",displayName = "")
	private String creator;
	@ApiModelProperty(value = "企业id",name = "branchId")
	@Column(type = Types.CHAR,length = 13,name = "branch_id",displayName = "")
	private String branchId;
	@ApiModelProperty(value = "原材料规格：长*宽*高",name = "materialSize",example="001")
	@Column(type = Types.VARCHAR,length = 100,name = "material_size")
	private String materialSize;
	@ApiModelProperty(value = "状态-0：启用 1：下线",name = "materialStatus",example="状态-0：启用 1：下线")
	@Column(type = Types.INTEGER,length = 10,name = "material_status")
	private Integer materialStatus;
	@ApiModelProperty(value = "原材料类型",name = "categoryId",example="原材料类型")
	@Column(type = Types.CHAR,length = 13,name = "category_id")
	private String categoryId;
	@ApiModelProperty(value = "计算单位",name = "unit",example="计算单位")
	@Column(type = Types.INTEGER,length = 13,name = "unit")
	private Integer unit;
    public Material() {  
     } 

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.name) > 40) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.color) > 20) {
			validResult.put("color", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.creator) > 13) {
			validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.branchId) > 13) {
			validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.categoryId) > 13) {
			validResult.put("categoryId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","name","color","materialLevel","created","creator","categoryId","branchId","materialStatus","material_size");

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
		if(map.containsKey("materialLevel")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("materialLevel",String.class))) {
				validResult.put("materialLevel", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name",String.class)) > 40) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("color")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("color",String.class)) > 20) {
				validResult.put("color", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("creator")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("branchId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("branchId",String.class)) > 13) {
				validResult.put("branchId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("categoryId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("categoryId",String.class)) > 13) {
				validResult.put("categoryId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("materialSize")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("materialSize",String.class)) > 100) {
				validResult.put("materialSize", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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

	public void setColor(String color){
		this.color=color;
	}

	public String getColor(){
		return color;
	}

	public void setMaterialLevel(Integer materialLevel){
		this.materialLevel=materialLevel;
	}

	public Integer getMaterialLevel(){
		return materialLevel;
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

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getMaterialSize() {
		return materialSize;
	}

	public void setMaterialSize(String materialSize) {
		this.materialSize = materialSize;
	}

	public Integer getMaterialStatus() {
		return materialStatus;
	}

	public void setMaterialStatus(Integer materialStatus) {
		this.materialStatus = materialStatus;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getUnit() {
		return unit;
	}

	public void setUnit(Integer unit) {
		this.unit = unit;
	}
}
