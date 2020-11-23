package com.lwxf.industry4.webapp.domain.dto.financing;

import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFunds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：payment_simple_funds dto
 *
 * @author：DJL
 * @create：2019/7/25 11:10
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value="日常记账科目实体",description="日常记账科目实体")
public class PaymentSimpleFundsDto extends PaymentSimpleFunds {

    @ApiModelProperty(value = "创建人")
    private String creatorName; // 创建人
    @ApiModelProperty(value = "操作者")
    private String operatorName; // 操作者
    private String deptName;//部门名称


    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
