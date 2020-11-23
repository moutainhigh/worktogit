package com.lwxf.industry4.webapp.domain.entity.procurement;

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
 * 功能：purchase_request 实体类
 *
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-10-21 03:29
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "采购申请单", description = "采购申请单")
@Table(name = "purchase_request", displayName = "purchase_request")
public class PurchaseRequest extends IdEntity {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "采购单名称", name = "name", example = "")
	@Column(type = Types.VARCHAR, length = 100, nullable = false, name = "name", displayName = "采购名称")
	private String name;
	@ApiModelProperty(value = "仓库id", name = "storageId", example = "")
	@Column(type = Types.CHAR, length = 13, name = "storage_id", displayName = "仓库id")
	private String storageId;
	@ApiModelProperty(value = "类型 0 - 板材类；1 - 五金类；2 - 耗材类；3 - 其他", name = "type", required = true, example = "")
	@Column(type = Types.CHAR, nullable = false, name = "categoryId", displayName = "采购类型")
	private String categoryId;
	@ApiModelProperty(value = "采购总数", name = "quantity", required = true, example = "")
	@Column(type = Types.INTEGER, nullable = false, name = "quantity", displayName = "采购数量：默认0")
	private Integer quantity;
	@ApiModelProperty(value = "采购说明", name = "notes", example = "")
	@Column(type = Types.VARCHAR, length = 500, name = "notes", displayName = "采购说明")
	private String notes;
	@ApiModelProperty(value = "创建人", name = "creator", required = true, example = "")
	@Column(type = Types.CHAR, length = 13, nullable = false, name = "creator", displayName = "系统采购时存一个默认的系统账号，如：System")
	private String creator;
	@ApiModelProperty(value = "创建时间", name = "created", required = true, example = "")
	@Column(type = TypesExtend.DATETIME, nullable = false, name = "created", displayName = "")
	private Date created;
	@ApiModelProperty(value = "采购单状态", name = "status", example = "")
	@Column(type = Types.TINYINT, nullable = false, name = "status", displayName = "0：待提交；1 - 待审核；2 - 待付款；3 - 采购中；4-待入库；5-已完成；6-已取消")
	private Integer status;
	@ApiModelProperty(value = "采购单编码", name = "no", required = true, example = "")
	@Column(type = Types.VARCHAR, length = 30, nullable = false, name = "no", displayName = "采购申请唯一编号")
	private String no;
	@ApiModelProperty(value = "采购金额", name = "amount", required = true, example = "")
	@Column(type = Types.DECIMAL, precision = 10, scale = 2, name = "amount", displayName = "采购金额")
	private BigDecimal amount;
	@ApiModelProperty(value = "申请人", name = "proposer", required = true, example = "")
	@Column(type = Types.CHAR, length = 13, name = "proposer", displayName = "申请人")
	private String proposer;
	@ApiModelProperty(value = "申请时间", name = "applyTime", example = "")
	@Column(type = TypesExtend.DATETIME, name = "apply_time", displayName = "申请时间")
	private Date applyTime;
	@ApiModelProperty(value = "供应商id", name = "supplierId", example = "")
	@Column(type = Types.CHAR, length = 13, name = "supplier_id", displayName = "供应商id")
	private String supplierId;
	@ApiModelProperty(value = "采购人", name = "purchaser", required = true, example = "")
	@Column(type = Types.CHAR, length = 13, name = "purchaser", displayName = "采购人")
	private String purchaser;
	@ApiModelProperty(value = "企业id", name = "branchId", required = true, example = "")
	@Column(type = Types.CHAR, length = 13, name = "branch_id", displayName = "企业id")
	private String branchId;
	@ApiModelProperty(value = "财务审核人", name = "financialAudit",  example = "")
	@Column(type = Types.CHAR, length = 13, name = "financial_audit", displayName = "财务审核人")
	private String financialAudit;
	@ApiModelProperty(value = "财务审核时间", name = "financialTime", example = "")
	@Column(type = TypesExtend.DATETIME, name = "financial_time", displayName = "财务审核时间")
	private Date financialTime;

	public PurchaseRequest() {
	}

	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (LwxfStringUtils.getStringLength(this.name) > 100) {
			validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.storageId) > 13) {
			validResult.put("storageId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.supplierId) > 13) {
			validResult.put("supplierId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (LwxfStringUtils.getStringLength(this.purchaser) > 13) {
			validResult.put("purchaser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.categoryId == null) {
			validResult.put("categoryId", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.quantity == null) {
			validResult.put("quantity", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.notes) > 500) {
			validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.creator == null) {
			validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
		} else {
			if (LwxfStringUtils.getStringLength(this.creator) > 13) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.created == null) {
			validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.status == null) {
			validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.no == null) {
			validResult.put("no", ErrorCodes.VALIDATE_NOTNULL);
		} else {
			if (LwxfStringUtils.getStringLength(this.no) > 30) {
				validResult.put("no", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (LwxfStringUtils.getStringLength(this.proposer) > 13) {
			validResult.put("proposer", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (validResult.size() > 0) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
		} else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id", "name", "storageId", "productId", "type", "quantity", "notes", "creator", "created", "status", "no", "amount", "proposer", "applyTime", "supplier_id", "purchaser", "branch_id");

	public static RequestResult validateFields(MapContext map) {
		Map<String, String> validResult = new HashMap<>();
		if (map.size() == 0) {
			return ResultFactory.generateErrorResult("error", ErrorCodes.VALIDATE_NOTNULL);
		}
		boolean flag;
		Set<String> mapSet = map.keySet();
		flag = propertiesList.containsAll(mapSet);
		if (!flag) {
			return ResultFactory.generateErrorResult("error", ErrorCodes.VALIDATE_ILLEGAL_ARGUMENT);
		}
		if (map.containsKey("categoryId")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("categoryId", String.class))) {
				validResult.put("categoryId", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if (map.containsKey("quantity")) {
			if (!DataValidatorUtils.isInteger1(map.getTypedValue("quantity", String.class))) {
				validResult.put("quantity", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if (map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created", String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if (map.containsKey("status")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("status", String.class))) {
				validResult.put("status", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if (map.containsKey("amount")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount", String.class))) {
				validResult.put("amount", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if (map.containsKey("applyTime")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("applyTime", String.class))) {
				validResult.put("applyTime", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if (map.containsKey("name")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("name", String.class)) > 100) {
				validResult.put("name", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (map.containsKey("storageId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("storageId", String.class)) > 13) {
				validResult.put("storageId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (map.containsKey("supplierId")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("supplierId", String.class)) > 13) {
				validResult.put("supplierId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (map.containsKey("purchaser")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("purchaser", String.class)) > 13) {
				validResult.put("purchaser", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}

		if (map.containsKey("categoryId")) {
			if (map.get("categoryId") == null) {
				validResult.put("categoryId", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if (map.containsKey("quantity")) {
			if (map.get("quantity") == null) {
				validResult.put("quantity", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if (map.containsKey("notes")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("notes", String.class)) > 500) {
				validResult.put("notes", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (map.containsKey("creator")) {
			if (map.getTypedValue("creator", String.class) == null) {
				validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
			} else {
				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator", String.class)) > 13) {
					validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if (map.containsKey("created")) {
			if (map.get("created") == null) {
				validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if (map.containsKey("status")) {
			if (map.get("status") == null) {
				validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if (map.containsKey("no")) {
			if (map.getTypedValue("no", String.class) == null) {
				validResult.put("no", ErrorCodes.VALIDATE_NOTNULL);
			} else {
				if (LwxfStringUtils.getStringLength(map.getTypedValue("no", String.class)) > 30) {
					validResult.put("no", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if (map.containsKey("proposer")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("proposer", String.class)) > 13) {
				validResult.put("proposer", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (validResult.size() > 0) {
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
		} else {
			return null;
		}
	}


	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setStorageId(String storageId) {
		this.storageId = storageId;
	}

	public String getStorageId() {
		return storageId;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getNotes() {
		return notes;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getCreated() {
		return created;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getNo() {
		return no;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setProposer(String proposer) {
		this.proposer = proposer;
	}

	public String getProposer() {
		return proposer;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public String getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getBranchId() {
		return branchId;
	}

	public void setBranchId(String branchId) {
		this.branchId = branchId;
	}

	public String getFinancialAudit() {
		return financialAudit;
	}

	public void setFinancialAudit(String financialAudit) {
		this.financialAudit = financialAudit;
	}

	public Date getFinancialTime() {
		return financialTime;
	}

	public void setFinancialTime(Date financialTime) {
		this.financialTime = financialTime;
	}
}

