package com.lwxf.industry4.webapp.domain.entity.activity;

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
 * 功能：activity_join 实体类
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @created：2019-03-08 09:57
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */
@Table(name = "activity_join", displayName = "activity_join")
public class ActivityJoin extends IdEntity {
    private static final long serialVersionUID = 1L;
    @Column(type = Types.TINYINT, nullable = false, name = "type", displayName = "类型（0 经销商  1终端） ")
    private Integer type;
    @Column(type = Types.CHAR, length = 13, nullable = false, name = "company_id", displayName = "公司id		")
    private String companyId;
    @Column(type = Types.CHAR, length = 13, nullable = false, name = "activity_id", displayName = "活动id")
    private String activityId;
    @Column(type = Types.CHAR, length = 13, nullable = false, name = "activity_params_id", displayName = "活动参数id")
    private String activityParamsId;
    @Column(type = Types.TINYINT, name = "is_paid", displayName = "是否缴费(0 未交费  1缴费)")
    private Integer paid;
    @Column(type = Types.TINYINT, nullable = false, name = "is_free", displayName = "是否免费（1 免费   0收费）")
    private Integer free;
    @Column(type = Types.VARCHAR, length = 13, nullable = false, name = "creator", displayName = "创建人")
    private String creator;
    @Column(type = Types.VARCHAR,  nullable = false, name = "created", displayName = "参加时间（创建时间）")
    private Date created;
    @Column(type = Types.DECIMAL, precision = 10, scale = 2, name = "amount", displayName = "金额（交了多少钱）")
    private BigDecimal amount;
    @Column(type = Types.CHAR, length = 13, name = "payment_id", displayName = "支付表id（管联支付表）")
    private String paymentId;
    @Column(type = Types.CHAR, length = 13, name = "user_id", displayName = "用户id")
    private String userId;
    @Column(type = Types.TINYINT,name = "status",displayName = "状态 （1，已取消，0,正常）")
    private Integer status;

    public ActivityJoin() {
    }

    public RequestResult validateFields() {
        Map<String, String> validResult = new HashMap<>();
        if (this.type == null) {
            validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
        }
        if (this.companyId == null) {
            validResult.put("companyId", ErrorCodes.VALIDATE_NOTNULL);
        } else {
            if (LwxfStringUtils.getStringLength(this.companyId) > 13) {
                validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (this.activityId == null) {
            validResult.put("activityId", ErrorCodes.VALIDATE_NOTNULL);
        } else {
            if (LwxfStringUtils.getStringLength(this.activityId) > 13) {
                validResult.put("activityId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (LwxfStringUtils.getStringLength(this.activityParamsId) > 13) {
            validResult.put("activityParamsId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }

        if (this.creator == null) {
            validResult.put("creator", ErrorCodes.VALIDATE_NOTNULL);
        } else {
            if (LwxfStringUtils.getStringLength(this.creator) > 50) {
                validResult.put("creator", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (LwxfStringUtils.getStringLength(this.paymentId) > 13) {
            validResult.put("paymentId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        } else {
            return null;
        }
    }

    private final static List<String> propertiesList = Arrays.asList("id", "type", "companyId", "activityId", "activityParamsId", "paid", "free", "creator", "created", "amount", "paymentId","status");

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
        if (map.containsKey("type")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("type", String.class))) {
                validResult.put("type", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
            }
        }
        if (map.containsKey("paid")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("paid", String.class))) {
                validResult.put("paid", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
            }
        }
        if (map.containsKey("free")) {
            if (!DataValidatorUtils.isInteger1(map.getTypedValue("free", String.class))) {
                validResult.put("free", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
            }
        }
        if (map.containsKey("amount")) {
            if (!DataValidatorUtils.isDecmal4(map.getTypedValue("amount", String.class))) {
                validResult.put("amount", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
            }
        }
        if (map.containsKey("type")) {
            if (map.get("type") == null) {
                validResult.put("type", ErrorCodes.VALIDATE_NOTNULL);
            }
        }if (map.containsKey("status")) {
            if (map.get("status") == null) {
                validResult.put("status", ErrorCodes.VALIDATE_NOTNULL);
            }
        }
        if (map.containsKey("companyId")) {
            if (map.getTypedValue("companyId", String.class) == null) {
                validResult.put("companyId", ErrorCodes.VALIDATE_NOTNULL);
            } else {
                if (LwxfStringUtils.getStringLength(map.getTypedValue("companyId", String.class)) > 13) {
                    validResult.put("companyId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
                }
            }
        }
        if (map.containsKey("activityId")) {
            if (map.getTypedValue("activityId", String.class) == null) {
                validResult.put("activityId", ErrorCodes.VALIDATE_NOTNULL);
            } else {
                if (LwxfStringUtils.getStringLength(map.getTypedValue("activityId", String.class)) > 13) {
                    validResult.put("activityId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
                }
            }
        }
        if (map.containsKey("activityParamsId")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("activityParamsId", String.class)) > 13) {
                validResult.put("activityParamsId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
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
        if (map.containsKey("paymentId")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("paymentId", String.class)) > 13) {
                validResult.put("paymentId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        } else {
            return null;
        }
    }


    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityParamsId(String activityParamsId) {
        this.activityParamsId = activityParamsId;
    }

    public String getActivityParamsId() {
        return activityParamsId;
    }

    public void setPaid(Integer paid) {
        this.paid = paid;
    }

    public Integer getPaid() {
        return paid;
    }

    public void setFree(Integer free) {
        this.free = free;
    }

    public Integer getFree() {
        return free;
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

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentId() {
        return paymentId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Integer getStatus() {return status; }

    public void setStatus(Integer status) {this.status = status; }
}
