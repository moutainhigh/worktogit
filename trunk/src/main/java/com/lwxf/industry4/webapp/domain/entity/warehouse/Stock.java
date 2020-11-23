package com.lwxf.industry4.webapp.domain.entity.warehouse;
import com.lwxf.commons.exception.ErrorCodes;
import com.lwxf.commons.utils.DataValidatorUtils;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.domain.entity.base.IdEntity;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.annotation.Table;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.utils.TypesExtend;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.*;
/**
 * 功能：stock 实体类
 *
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-21 04:39
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "stock",displayName = "stock")
public class Stock extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "storage_id",displayName = "仓库id")
	private String storageId;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "material_id",displayName = "原材料id")
	private String materialId;
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "price",displayName = "入库价：计算公式：（库存余量*上次采购价+本次采购量*本次采购价）/(库存余量+本次采购量)，入库时计算出来，可以进行修改，默认0")
	private BigDecimal price;
	@Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "operator",displayName = "入库人")
	private String operator;
	@Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "operate_time",displayName = "入库时间")
	private Date operateTime;
	@Column(type = Types.INTEGER,nullable = false,name = "quantity",displayName = "数量：默认0")
	private Integer quantity;
	@Column(type = Types.CHAR,length = 10,name = "shelf",displayName = "所在货架(整行或技巧)：做成枚举，入库时作为下拉选择项，枚举项为：A、B、C、D、E、F、J：产品定为由货架的行、列、层三维定为，行为整行的货架，列为每行中的某个货架，层为货架的上、中、下三层（类似高低床）")
	private String shelf;
	@Column(type = Types.TINYINT,name = "column",displayName = "所在货架的列位，作为下拉选择项：1、2、3、4、5、6、7、8、9、10、11、12、13、14")
	private Integer column;
	@Column(type = Types.TINYINT,name = "tier",displayName = "所在货架的层：1 - 第一层；2 - 第二层；3 -第三层，作为下拉选择项")
	private Integer tier;
	@Column(type = Types.INTEGER,name = "pre_output",displayName = "预出库数量")
	private Integer preOutput;
	@Column(type = Types.VARCHAR,name = "location",displayName = "存储位置")
	private String location;
	@Column(type = Types.CHAR,length = 13,updatable = false,name = "branch_id",displayName = "企业Id")
	private String branchId;
	public Stock() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.storageId == null) {
			validResult.put("storageId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.storageId) > 13) {
				validResult.put("storageId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.materialId == null) {
			validResult.put("productId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.materialId) > 13) {
				validResult.put("productId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.operator == null) {
			validResult.put("operator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}else{
			if (LwxfStringUtils.getStringLength(this.operator) > 13) {
				validResult.put("operator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
			}
		}
		if (this.operateTime == null) {
			validResult.put("operateTime", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}
		if (this.quantity == null) {
			validResult.put("quantity", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
		}

		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("price","quantity","shelf","column","tier","preOutput","location");

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
		if(map.containsKey("quantity")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("quantity",String.class))) {
				validResult.put("quantity", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
			}
		}
		if(map.containsKey("quantity")) {
			if (map.get("quantity")  == null) {
				validResult.put("quantity", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}
		if(map.containsKey("column")) {
			if (map.get("column")  == null) {
				validResult.put("column", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
			}
		}

		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}


	public void setStorageId(String storageId){
		this.storageId=storageId;
	}

	public String getStorageId(){
		return storageId;
	}

	public void setPrice(BigDecimal price){
		this.price=price;
	}

	public BigDecimal getPrice(){
		return price;
	}

	public void setOperator(String operator){
		this.operator=operator;
	}

	public String getOperator(){
		return operator;
	}

	public void setOperateTime(Date operateTime){
		this.operateTime=operateTime;
	}

	public Date getOperateTime(){
		return operateTime;
	}

	public void setQuantity(Integer quantity){
		this.quantity=quantity;
	}

	public Integer getQuantity(){
		return quantity;
	}

	public void setShelf(String shelf){
		this.shelf=shelf;
	}

	public String getShelf(){
		return shelf;
	}

	public void setColumn(Integer column){
		this.column=column;
	}

	public Integer getColumn(){
		return column;
	}

	public void setTier(Integer tier){
		this.tier=tier;
	}

	public Integer getTier(){
		return tier;
	}

	public void setPreOutput(Integer preOutput){
		this.preOutput=preOutput;
	}

	public Integer getPreOutput(){
		return preOutput;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}
}
