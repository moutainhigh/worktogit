package com.lwxf.industry4.webapp.domain.entity.financing;
import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.util.Date;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
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
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 功能：payment_simple 实体类
 *
 * @author：zhangxiaolin(3965488@qq.com)
 * @created：2019-04-07 11:58 
 * @version：2018 Version：1.0 
 * @dept：老屋新房 Created with IntelliJ IDEA 
 */
@Table(name = "payment_simple",displayName = "payment_simple")
public class PaymentSimple extends IdEntity  {
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="款项类型ID(1:收入,2:支出)",name="paymentDate")
	@Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "1:收入,2:支出")
	private Integer type;
	@ApiModelProperty(value="支付方式",name="ways")
	@Column(type = Types.TINYINT,nullable = false,name = "ways",displayName = "支付方式")
	private Integer ways;
	@ApiModelProperty(value="银行信息，对应bankAccount表id",name="bank")
	@Column(type = Types.VARCHAR,nullable = false,name = "bank",displayName = "银行信息，对应bankAccount")
	private String bank;
	@ApiModelProperty(value="款项ID",name="paymentDate")
	@Column(type = Types.INTEGER,nullable = false,name = "funds",displayName = "科目id")
	private Integer funds;
	@ApiModelProperty(value="总金额",name="amount")
	@Column(type = Types.DECIMAL,precision = 10,scale=2,nullable = false,name = "amount",displayName = "总金额")
	private BigDecimal amount;
	@ApiModelProperty(value="备注",name="note")
	@Column(type = Types.VARCHAR,length = 200,name = "note",displayName = "描述")
	private String note;
	@ApiModelProperty(value="支付时间 格式:2019-01-01",name="paymentDate")
	@Column(type = TypesExtend.DATETIME,name = "payment_date",displayName = "收支时间")
	private Date paymentDate;
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "creator",displayName = "创建人")
	private String creator;
	@ApiModelProperty(value="创建时间",name="created")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@Column(type = TypesExtend.DATETIME,nullable = false,name = "created",displayName = "创建时间")
	private Date created;
	@ApiModelProperty(value="操作人",name="operator")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "operator",displayName = "操作人")
	private String operator;
	@ApiModelProperty(value="企业id",name="branchId")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "branch_id",displayName = "企业id")
	private String branchId;
	@ApiModelProperty(value="转出银行",name="outcomeBank")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "outcome_bank",displayName = "企业id")
	private String outcomeBank;
	@ApiModelProperty(value="转入银行",name="incomeBank")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "income_bank",displayName = "企业id")
	private String incomeBank;
	@ApiModelProperty(value="部门id",name="deptId")
	@Column(type = Types.CHAR,length = 13,nullable = false,name = "dept_id",displayName = "部门id")
	private String deptId;
	@ApiModelProperty(value="款项描述组合字段",name="fundsName")
	@Column(type = Types.VARCHAR,length = 50,nullable = false,name = "funds_name",displayName = "款项描述组合字段")
	private String fundsName;
    public PaymentSimple() {  
     }


	public RequestResult validateFields() {
		Map<String, String> validResult = new HashMap<>();
		if (this.type == null) {
			validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (this.funds == null) {
			validResult.put("funds", ErrorCodes.VALIDATE_NOTNULL);
		}
		if (LwxfStringUtils.getStringLength(this.note) > 200) {
			validResult.put("note", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
		}
		if (this.creator == null) {
			validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
		}else{
 			if (LwxfStringUtils.getStringLength(this.creator) > 10) {
				validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if (this.created == null) {
			validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	private final static List<String> propertiesList = Arrays.asList("id","type","funds","amount","note","paymentDate","creator","created");

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
		if(map.containsKey("type")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("type",String.class))) {
				validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("funds")) {
			if (!DataValidatorUtils.isByte1(map.getTypedValue("funds",String.class))) {
				validResult.put("funds", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("amount")) {
			if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount",String.class))) {
				validResult.put("amount", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("paymentDate")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("paymentDate",String.class))) {
				validResult.put("paymentDate", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("created")) {
			if (!DataValidatorUtils.isDate(map.getTypedValue("created",String.class))) {
				validResult.put("created", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
			}
		}
		if(map.containsKey("type")) {
			if (map.get("type")  == null) {
				validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("funds")) {
			if (map.get("funds")  == null) {
				validResult.put("funds", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("branchId")) {
			if (map.get("branchId")  == null) {
				validResult.put("branchId", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(map.containsKey("note")) {
			if (LwxfStringUtils.getStringLength(map.getTypedValue("note",String.class)) > 200) {
				validResult.put("note", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
			}
		}
		if(map.containsKey("creator")) {
			if (map.getTypedValue("creator",String.class)  == null) {
				validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
			}else{
 				if (LwxfStringUtils.getStringLength(map.getTypedValue("creator",String.class)) > 13) {
					validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}

		if(map.containsKey("operator")) {
			if (map.getTypedValue("operator",String.class)  == null) {
				validResult.put("operator", ErrorCodes.VALIDATE_NOTNULL);
			}else{
				if (LwxfStringUtils.getStringLength(map.getTypedValue("operator",String.class)) > 13) {
					validResult.put("operator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
				}
			}
		}
		if(map.containsKey("created")) {
			if (map.get("created")  == null) {
				validResult.put("created", ErrorCodes.VALIDATE_NOTNULL);
			}
		}
		if(validResult.size()>0){
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,validResult);
		}else {
			return null;
		}
	}

	public String getOutcomeBank() {
		return outcomeBank;
	}

	public void setOutcomeBank(String outcomeBank) {
		this.outcomeBank = outcomeBank;
	}

	public String getIncomeBank() {
		return incomeBank;
	}

	public void setIncomeBank(String incomeBank) {
		this.incomeBank = incomeBank;
	}

	public String getBranchId() {return branchId;}

	public void setBranchId(String branchId) {this.branchId = branchId;}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFunds() {
		return funds;
	}

	public void setFunds(Integer funds) {
		this.funds = funds;
	}

	public void setAmount(BigDecimal amount){
		this.amount=amount;
	}

	public BigDecimal getAmount(){
		return amount;
	}

	public void setNote(String note){
		this.note=note;
	}

	public String getNote(){
		return note;
	}

	public void setPaymentDate(Date paymentDate){
		this.paymentDate=paymentDate;
	}

	public Date getPaymentDate(){
		return paymentDate;
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

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Integer getWays() {return ways;}

	public void setWays(Integer ways) {this.ways = ways;}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getFundsName() {
		return fundsName;
	}

	public void setFundsName(String fundsName) {
		this.fundsName = fundsName;
	}
}
