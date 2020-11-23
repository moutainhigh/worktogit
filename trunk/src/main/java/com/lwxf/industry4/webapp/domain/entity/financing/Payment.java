package com.lwxf.industry4.webapp.domain.entity.financing;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
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
 * 功能：payment 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2018-12-27 01:36
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "payment", displayName = "payment")
@ApiModel(value = "支付记录信息", discriminator = "支付记录信息")
public class Payment extends IdEntity {
    private static final long serialVersionUID = 1L;
    @Column(type = Types.TINYINT, nullable = false, name = "type", displayName = "支付类型：0 - 客户对经销商；1 - 经销商对客户（常指退款）；2 - 经销商对厂家；3 - 厂家对经销商（常指退款）；4 - 厂家对经销商扣款;5 - 厂家外协支付")
    @ApiModelProperty(value = "支付类型：0 - 客户对经销商；1 - 经销商对客户（常指退款）；2 - 经销商对厂家；3 - 厂家对经销商（常指退款）；4 - 厂家对经销商扣款;5 - 厂家外协支付")
    private Integer type;
    @Column(type = Types.TINYINT, nullable = false, name = "way", displayName = "支付方式：0 - 支付宝；1 - 微信；2 - 现金；3 - 银行转账（银联或POS机）；")
    @ApiModelProperty(value = "支付方式：0 - 支付宝；1 - 微信；2 - 现金；3 - 银行转账（银联或POS机）；")
    private Integer way;
    @Column(type = Types.TINYINT, nullable = false, name = "funds",
            displayName = "支付款项：0 - 定金；1 - 预付款；2 - 尾款；3 - 全款；" +
                    "4 - 设计金；5 - 自由资金；6 - 定向预付金；7 - 定时预付金；8 - 保证金；" +
                    "9 - 加盟费；10 - 设计保证金；11 - 活动诚意金；12 - 合作诚意金，13- 货款;14 - 外协;20 - 其他，" +
                    "其中4是厂家对经销商订单单独收取的设计费用，不计入任何账号，" +
                    "从5开始需要计入相应的账号，并生成dealer_account_detail数据，" +
                    "0 - 3是客户付给经销商的款项，计入经销商个人账号，" +
                    "不用生成dealer_account_detail数据\n")
    @ApiModelProperty(value = "支付款项")
    private Integer funds;
    @Column(type = Types.VARCHAR, length = 50, name = "notes", displayName = "支付说明")
    @ApiModelProperty(value = "支付说明")
    private String notes;
    @Column(type = Types.CHAR, length = 13, nullable = false, name = "creator", displayName = "创建人")
    @ApiModelProperty(value = "创建人")
    private String creator;
    @Column(type = TypesExtend.DATETIME, nullable = false, name = "created", displayName = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private Date created;
    @Column(type = Types.TINYINT, nullable = false, name = "status", displayName = "付款状态：0 - 待付款；1 - 已付款；默认0，审核后改为1")
    @ApiModelProperty(value = "付款状态：0 - 待付款；1 - 已付款；默认0，审核后改为1")
    private Integer status;
    @Column(type = Types.CHAR, length = 13, name = "custom_order_id", displayName = "订单id：当为订单的支付时，保存订单id")
    @ApiModelProperty(value = "订单id")
    private String customOrderId;
    @Column(type = Types.CHAR, length = 13, name = "auditor", displayName = "审核人")
    @ApiModelProperty(value = "审核人")
    private String auditor;
    @Column(type = TypesExtend.DATETIME, name = "audited", displayName = "审核时间")
    @ApiModelProperty(value = "审核时间")
    private Date audited;
    @Column(type = Types.DECIMAL, precision = 10, scale = 2, nullable = false, name = "amount", displayName = "支付金额：默认0")
    @ApiModelProperty(value = "支付金额")
    private BigDecimal amount;
    @Column(type = Types.DECIMAL, precision = 10, scale = 2, nullable = false, name = "pay_amount", displayName = "实付金额：默认0")
    @ApiModelProperty(value = "实付金额")
    private BigDecimal payAmount;
    @Column(type = Types.CHAR, length = 13, nullable = false, name = "payee", displayName = "付给经销商的，设为经销商的店主账号；付给厂家的，设为厂家的系统账号（系统内置的公司账号）")
    @ApiModelProperty(value = "付给经销商的，设为经销商的店主账号；付给厂家的，设为厂家的系统账号（系统内置的公司账号）")
    private String payee;
    @Column(type = Types.VARCHAR, length = 50, name = "name", displayName = "标题")
    @ApiModelProperty(value = "标题")
    private String name;
    @Column(type = Types.CHAR, length = 13, nullable = false, name = "company_id", displayName = "公司id，经销商的公司id")
    @ApiModelProperty(value = "公司id")
    private String companyId;
    @Column(type = Types.VARCHAR, length = 20, nullable = false, name = "holder", displayName = "收款人")
    @ApiModelProperty(value = "收款人")
    private String holder;
    @Column(type = Types.VARCHAR, length = 50, name = "holder_account", displayName = "收款账号")
    @ApiModelProperty(value = "收款账号")
    private String holderAccount;
    @Column(type = Types.VARCHAR, length = 50, name = "deposit_bank", displayName = "开户行")
    @ApiModelProperty(value = "开户行")
    private String depositBank;
    @Column(type = TypesExtend.DATETIME, name = "pay_time", displayName = "支付时间")
    @ApiModelProperty(value = "支付时间")
    private Date payTime;
    @Column(type = Types.VARCHAR, name = "no", displayName = "支付编号")
    @ApiModelProperty(value = "支付编号")
    private String no;
    @Column(type = Types.CHAR, length = 13, nullable = false, name = "branch_id", displayName = "企业id")
    @ApiModelProperty(value = "企业id")
    private String branchId;
    @Column(type = Types.CHAR, length = 13, nullable = false, name = "bank", displayName = "银行id")
    @ApiModelProperty(value = "银行id")
    private String bank;

    @Column(type = Types.TINYINT, nullable = false, name = "resource_type", displayName = "0:订单,1:售后单")
    @ApiModelProperty(value = "0:订单,1:售后单")
    private Integer resourceType;
    @Column(type = Types.TINYINT,name = "flag",displayName = "订单支付单的存在状态：0-正常 1-废除")
    @ApiModelProperty(value = "订单支付单的存在状态：0-正常 1-废除")
    private Integer flag;


    public Payment() {
    }

    public RequestResult validateFields() {
        Map<String, String> validResult = new HashMap<>();
        if (this.type == null) {
            validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (this.way == null) {
            validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (this.funds == null) {
            validResult.put("funds", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (LwxfStringUtils.getStringLength(this.notes) > 50) {
            validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        if (this.creator == null) {
            validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.creator) > 13) {
                validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (this.created == null) {
            validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (this.status == null) {
            validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (LwxfStringUtils.getStringLength(this.customOrderId) > 13) {
            validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        if (LwxfStringUtils.getStringLength(this.auditor) > 13) {
            validResult.put("auditor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        if (this.payee == null) {
            validResult.put("payee", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.payee) > 13) {
                validResult.put("payee", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (LwxfStringUtils.getStringLength(this.name) > 50) {
            validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        if (this.companyId == null) {
            validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
                validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (this.branchId == null) {
            validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
                validResult.put("branchId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (this.no == null) {
            validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.no) > 30) {
                validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (this.holder == null) {
            validResult.put("holder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        } else {
            if (LwxfStringUtils.getStringLength(this.holder) > 20) {
                validResult.put("holder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (LwxfStringUtils.getStringLength(this.holderAccount) > 50) {
            validResult.put("holderAccount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        if (LwxfStringUtils.getStringLength(this.depositBank) > 50) {
            validResult.put("depositBank", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
        }
        if (this.resourceType == null) {
            validResult.put("resourceType", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        } else {
            return null;
        }
    }

    private final static List<String> propertiesList = Arrays.asList("id", "no", "type", "way", "funds", "notes", "creator", "created", "status", "customOrderId", "auditor", "audited", "amount", "payee", "name", "companyId", "holder", "holderAccount", "depositBank", "payTime");

    public static RequestResult validateFields(MapContext map) {
        Map<String, String> validResult = new HashMap<>();
        if (map.size() == 0) {
            return ResultFactory.generateErrorResult("error", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
        }
        boolean flag;
        Set<String> mapSet = map.keySet();
        flag = propertiesList.containsAll(mapSet);
        if (!flag) {
            return ResultFactory.generateErrorResult("error", AppBeanInjector.i18nUtil.getMessage("VALIDATE_ILLEGAL_ARGUMENT"));
        }
        if (map.containsKey("type")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("type", String.class))) {
                validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("way")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("way", String.class))) {
                validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("bank")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("bank", String.class)) > 13) {
                validResult.put("bank", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("funds")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("funds", String.class))) {
                validResult.put("funds", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("created")) {
            if (!DataValidatorUtils.isDate(map.getTypedValue("created", String.class))) {
                validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("status")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("status", String.class))) {
                validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("audited")) {
            if (!DataValidatorUtils.isDate(map.getTypedValue("audited", String.class))) {
                validResult.put("audited", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("amount")) {
            if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount", String.class))) {
                validResult.put("amount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_INCORRECT_DATA_FORMAT"));
            }
        }
        if (map.containsKey("type")) {
            if (map.get("type") == null) {
                validResult.put("type", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("way")) {
            if (map.get("way") == null) {
                validResult.put("way", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("funds")) {
            if (map.get("funds") == null) {
                validResult.put("funds", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("notes")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("notes", String.class)) > 50) {
                validResult.put("notes", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (map.containsKey("creator")) {
            if (map.getTypedValue("creator", String.class) == null) {
                validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            } else {
                if (LwxfStringUtils.getStringLength(map.getTypedValue("creator", String.class)) > 13) {
                    validResult.put("creator", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
                }
            }
        }
        if (map.containsKey("created")) {
            if (map.get("created") == null) {
                validResult.put("created", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("status")) {
            if (map.get("status") == null) {
                validResult.put("status", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            }
        }
        if (map.containsKey("customOrderId")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("customOrderId", String.class)) > 13) {
                validResult.put("customOrderId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (map.containsKey("auditor")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("auditor", String.class)) > 13) {
                validResult.put("auditor", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (map.containsKey("payee")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("payee", String.class)) > 13) {
                validResult.put("payee", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (map.containsKey("no")) {
            if (map.getTypedValue("no", String.class) == null) {
                validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            } else {
                if (LwxfStringUtils.getStringLength(map.getTypedValue("no", String.class)) > 30) {
                    validResult.put("no", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
                }
            }
        }
        if (map.containsKey("name")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("name", String.class)) > 50) {
                validResult.put("name", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (map.containsKey("companyId")) {
            if (map.getTypedValue("companyId", String.class) == null) {
                validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            } else {
                if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId", String.class)) > 13) {
                    validResult.put("companyId", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
                }
            }
        }
        if (map.containsKey("holder")) {
            if (map.getTypedValue("holder", String.class) == null) {
                validResult.put("holder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOTNULL"));
            } else {
                if (LwxfStringUtils.getStringLength(map.getTypedValue("holder", String.class)) > 20) {
                    validResult.put("holder", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
                }
            }
        }
        if (map.containsKey("holderAccount")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("holderAccount", String.class)) > 50) {
                validResult.put("holderAccount", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (map.containsKey("depositBank")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("depositBank", String.class)) > 50) {
                validResult.put("depositBank", AppBeanInjector.i18nUtil.getMessage("VALIDATE_LENGTH_TOO_LONG"));
            }
        }
        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        } else {
            return null;
        }
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getBranchId() {
        return branchId;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public Integer getWay() {
        return way;
    }

    public void setFunds(Integer funds) {
        this.funds = funds;
    }

    public Integer getFunds() {
        return funds;
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

    public void setCustomOrderId(String customOrderId) {
        this.customOrderId = customOrderId;
    }

    public String getCustomOrderId() {
        return customOrderId;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAudited(Date audited) {
        this.audited = audited;
    }

    public Date getAudited() {
        return audited;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setPayee(String payee) {
        this.payee = payee;
    }

    public String getPayee() {
        return payee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolderAccount(String holderAccount) {
        this.holderAccount = holderAccount;
    }

    public String getHolderAccount() {
        return holderAccount;
    }

    public void setDepositBank(String depositBank) {
        this.depositBank = depositBank;
    }

    public String getDepositBank() {
        return depositBank;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Integer getResourceType() {
        return resourceType;
    }

    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
