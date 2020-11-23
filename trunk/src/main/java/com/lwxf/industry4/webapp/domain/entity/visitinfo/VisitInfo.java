package com.lwxf.industry4.webapp.domain.entity.visitinfo;

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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：visit_info 实体类
 *
 * @author：lyh
 * @created：2019-11-26 03:30
 * @version：2018 Version：1.0
 * @dept：老屋新房 Created with IntelliJ IDEA
 */

@Table(name = "visit_info", displayName = "visit_info")
@ApiModel(value = "拜访信息", description = "拜访信息")
public class VisitInfo extends IdEntity {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "visit_type", name = "visitType", example = "1.工厂访问经销商，2.经销商访问客户")
    @Column(type = Types.VARCHAR, length = 255, name = "visit_type", displayName = "1.工厂访问经销商，2.经销商访问客户")
    private String visitType;
    @ApiModelProperty(value = "visit_date", name = "visitDate", example = "访问时间")
    @Column(type = TypesExtend.DATETIME, name = "visit_date", displayName = "访问时间")
    private Date visitDate;
    @ApiModelProperty(value = "访问地点", name = "visitAddress", example = "访问地点")
    @Column(type = Types.VARCHAR, length = 255, name = "visit_address", displayName = "访问地点")
    private String visitAddress;
    @ApiModelProperty(value = "访问内容", name = "visitContent", example = "访问内容")
    @Column(type = Types.VARCHAR, length = 2000, name = "visit_content", displayName = "访问内容")
    private String visitContent;
    @ApiModelProperty(value = "访问人职务", name = "visitDuty", example = "访问人职务")
    @Column(type = Types.VARCHAR, length = 255, name = "visit_duty", displayName = "访问人职务")
    private String visitDuty;
    @ApiModelProperty(value = "访问人", name = "visitPeople", example = "访问人")
    @Column(type = Types.VARCHAR, length = 255, name = "visit_people", displayName = "访问人")
    private String visitPeople;
    @ApiModelProperty(value = "访问人id（工厂id或经销商id）", name = "visitUserId", example = "访问人id（工厂id或经销商id）")
    @Column(type = Types.VARCHAR, length = 255, name = "visit_user_id", displayName = "访问人id（工厂id或经销商id）")
    private String visitUserId;
    @ApiModelProperty(value = "访问结果", name = "visiResult", example = "访问结果")
    @Column(type = Types.VARCHAR, length = 2000, name = "visi_result", displayName = "访问结果")
    private String visiResult;
    @ApiModelProperty(value = "访问方式:1.实地访问2电话访问3邀约访问", name = "visitWay", example = "访问方式:1.实地访问2电话访问3邀约访问")
    @Column(type = Types.VARCHAR, length = 255, name = "visit_way", displayName = "访问方式:1.实地访问2电话访问3邀约访问")
    private String visitWay;
    @ApiModelProperty(value = "被访问人id(终端客户id 或 经销商id", name = "visitedUserId", example = "被访问人id(终端客户id 或 经销商id")
    @Column(type = Types.VARCHAR, length = 11, name = "visited_user_id", displayName = "被访问人id(终端客户id 或 经销商id)")
    private String visitedUserId;
    @ApiModelProperty(value = "被访问人意向级别(1.2.3)", name = "visitedIntentionLevel", example = "被访问人意向级别(1.2.3)")
    @Column(type = Types.VARCHAR, length = 255, name = "visited_intention_level", displayName = "被访问人意向级别(1.2.3)")
    private String visitedIntentionLevel;
    @ApiModelProperty(value = "被访问人财力", name = "visitedEstate", example = "被访问人财力")
    @Column(type = Types.VARCHAR, length = 255, name = "visited_estate", displayName = "被访问人财力")
    private String visitedEstate;
    @ApiModelProperty(value = "被访问人喜好", name = "visitedLike", example = "被访问人喜好")
    @Column(type = Types.VARCHAR, length = 255, name = "visited_like", displayName = "被访问人喜好")
    private String visitedLike;
    @ApiModelProperty(value = "被访问人关注点", name = "visitedFocus", example = "被访问人关注点")
    @Column(type = Types.VARCHAR, length = 255, name = "visited_focus", displayName = "被访问人关注点")
    private String visitedFocus;
    @ApiModelProperty(value = "备用1", name = "reserve1", example = "备用1")
    @Column(type = Types.VARCHAR, length = 255, name = "reserve1", displayName = "备用1")
    private String reserve1;
    @ApiModelProperty(value = "备用2", name = "reserve2", example = "备用2")
    @Column(type = Types.VARCHAR, length = 255, name = "reserve2", displayName = "备用2")
    private String reserve2;
    @ApiModelProperty(value = "备用3", name = "reserve3", example = "备用3")
    @Column(type = Types.VARCHAR, length = 255, name = "reserve3", displayName = "备用3")
    private String reserve3;
    @ApiModelProperty(value = "备用4", name = "reserve4", example = "备用4")
    @Column(type = Types.VARCHAR, length = 255, name = "reserve4", displayName = "备用4")
    private String reserve4;
    @ApiModelProperty(value = "被访问人姓名", name = "visitedUserName", example = "被访问人姓名")
    @Column(type = Types.VARCHAR, length = 255, name = "visited_user_name", displayName = "备用4")
    private String visitedUserName;

    public String getVisitedUserName() {
        return visitedUserName;
    }

    public void setVisitedUserName(String visitedUserName) {
        this.visitedUserName = visitedUserName;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public RequestResult validateFields() {
        Map<String, String> validResult = new HashMap<>();
        if (LwxfStringUtils.getStringLength(this.visitType) > 255) {
            validResult.put("visitType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitAddress) > 255) {
            validResult.put("visitAddress", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitContent) > 2000) {
            validResult.put("visitContent", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitDuty) > 255) {
            validResult.put("visitDuty", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitPeople) > 255) {
            validResult.put("visitPeople", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitUserId) > 255) {
            validResult.put("visitUserId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visiResult) > 2000) {
            validResult.put("visiResult", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitWay) > 255) {
            validResult.put("visitWay", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitedUserId) > 11) {
            validResult.put("visitedUserId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitedIntentionLevel) > 255) {
            validResult.put("visitedIntentionLevel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitedEstate) > 255) {
            validResult.put("visitedEstate", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitedLike) > 255) {
            validResult.put("visitedLike", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.visitedFocus) > 255) {
            validResult.put("visitedFocus", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.reserve1) > 255) {
            validResult.put("reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.reserve2) > 255) {
            validResult.put("reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.reserve3) > 255) {
            validResult.put("reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (LwxfStringUtils.getStringLength(this.reserve4) > 255) {
            validResult.put("reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
        }
        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        } else {
            return null;
        }
    }

    private final static List<String> propertiesList = Arrays.asList("id", "visitType", "visitDate", "visitAddress", "visitContent", "visitDuty", "visitPeople", "visitUserId", "visiResult", "visitWay", "visitedUserId", "visitedIntentionLevel", "visitedEstate", "visitedLike", "visitedFocus", "reserve1", "reserve2", "reserve3", "reserve4");

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
        if (map.containsKey("visitDate")) {
            if (!DataValidatorUtils.isDate(map.getTypedValue("visitDate", String.class))) {
                validResult.put("visitDate", ErrorCodes.VALIDATE_INCORRECT_DATA_FORMAT);
            }
        }
        if (map.containsKey("visitType")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitType", String.class)) > 255) {
                validResult.put("visitType", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitAddress")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitAddress", String.class)) > 255) {
                validResult.put("visitAddress", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitContent")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitContent", String.class)) > 2000) {
                validResult.put("visitContent", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitDuty")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitDuty", String.class)) > 255) {
                validResult.put("visitDuty", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitPeople")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitPeople", String.class)) > 255) {
                validResult.put("visitPeople", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitUserId")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitUserId", String.class)) > 255) {
                validResult.put("visitUserId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visiResult")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visiResult", String.class)) > 2000) {
                validResult.put("visiResult", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitWay")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitWay", String.class)) > 255) {
                validResult.put("visitWay", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitedUserId")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitedUserId", String.class)) > 11) {
                validResult.put("visitedUserId", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitedIntentionLevel")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitedIntentionLevel", String.class)) > 255) {
                validResult.put("visitedIntentionLevel", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitedEstate")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitedEstate", String.class)) > 255) {
                validResult.put("visitedEstate", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitedLike")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitedLike", String.class)) > 255) {
                validResult.put("visitedLike", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("visitedFocus")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("visitedFocus", String.class)) > 255) {
                validResult.put("visitedFocus", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("reserve1")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve1", String.class)) > 255) {
                validResult.put("reserve1", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("reserve2")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve2", String.class)) > 255) {
                validResult.put("reserve2", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("reserve3")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve3", String.class)) > 255) {
                validResult.put("reserve3", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (map.containsKey("reserve4")) {
            if (LwxfStringUtils.getStringLength(map.getTypedValue("reserve4", String.class)) > 255) {
                validResult.put("reserve4", ErrorCodes.VALIDATE_LENGTH_TOO_LONG);
            }
        }
        if (validResult.size() > 0) {
            return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR, validResult);
        } else {
            return null;
        }
    }


    public void setVisitType(String visitType) {
        this.visitType = visitType;
    }

    public String getVisitType() {
        return visitType;
    }

    public void setVisitDate(Date visitDate) {
        this.visitDate = visitDate;
    }

    public Date getVisitDate() {
        return visitDate;
    }

    public void setVisitAddress(String visitAddress) {
        this.visitAddress = visitAddress;
    }

    public String getVisitAddress() {
        return visitAddress;
    }

    public void setVisitContent(String visitContent) {
        this.visitContent = visitContent;
    }

    public String getVisitContent() {
        return visitContent;
    }

    public void setVisitDuty(String visitDuty) {
        this.visitDuty = visitDuty;
    }

    public String getVisitDuty() {
        return visitDuty;
    }

    public void setVisitPeople(String visitPeople) {
        this.visitPeople = visitPeople;
    }

    public String getVisitPeople() {
        return visitPeople;
    }

    public void setVisitUserId(String visitUserId) {
        this.visitUserId = visitUserId;
    }

    public String getVisitUserId() {
        return visitUserId;
    }

    public void setVisiResult(String visiResult) {
        this.visiResult = visiResult;
    }

    public String getVisiResult() {
        return visiResult;
    }

    public void setVisitWay(String visitWay) {
        this.visitWay = visitWay;
    }

    public String getVisitWay() {
        return visitWay;
    }

    public void setVisitedUserId(String visitedUserId) {
        this.visitedUserId = visitedUserId;
    }

    public String getVisitedUserId() {
        return visitedUserId;
    }

    public void setVisitedIntentionLevel(String visitedIntentionLevel) {
        this.visitedIntentionLevel = visitedIntentionLevel;
    }

    public String getVisitedIntentionLevel() {
        return visitedIntentionLevel;
    }

    public void setVisitedEstate(String visitedEstate) {
        this.visitedEstate = visitedEstate;
    }

    public String getVisitedEstate() {
        return visitedEstate;
    }

    public void setVisitedLike(String visitedLike) {
        this.visitedLike = visitedLike;
    }

    public String getVisitedLike() {
        return visitedLike;
    }

    public void setVisitedFocus(String visitedFocus) {
        this.visitedFocus = visitedFocus;
    }

    public String getVisitedFocus() {
        return visitedFocus;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve4(String reserve4) {
        this.reserve4 = reserve4;
    }

    public String getReserve4() {
        return reserve4;
    }
}
