package com.lwxf.industry4.webapp.domain.dto.financing;

import com.lwxf.industry4.webapp.common.constant.BizConstant;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimple;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentSimpleFunds;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;

@ApiModel(value="日常记账实体",description="日常记账实体")
public class PaymentSimpleDto extends PaymentSimple {
    @ApiModelProperty(value="类型名称",name="companyName")
    private String typeName;
    @ApiModelProperty(value="支付方式名称",name="waysName")
    private String waysName;
    @ApiModelProperty(value="银行名称",name="bankName")
    private String bankName;
    @ApiModelProperty(value="转入银行名称",name="incomeBankName")
    private String incomeBankName;
    @ApiModelProperty(value="转出银行名称",name="outcomeBankName")
    private String outcomeBankName;
    @ApiModelProperty(value="操作人名称",name="operatorName")
    private String operatorName;
    @ApiModelProperty(value="图片附件，多个用逗号间隔)",name="fileIds")
    private String fileIds;
    @ApiModelProperty(value="图片url列表，查询时使用)",name="imgPathList")
    private List<String> imgPathList;
    @ApiModelProperty(value="科目款项列表",name="paymentSimpleFundsList")
    private List<PaymentSimpleFundsDto> paymentSimpleFundsList;

    public String getIncomeBankName() {
        return incomeBankName;
    }

    public void setIncomeBankName(String incomeBankName) {
        this.incomeBankName = incomeBankName;
    }

    public String getOutcomeBankName() {
        return outcomeBankName;
    }

    public void setOutcomeBankName(String outcomeBankName) {
        this.outcomeBankName = outcomeBankName;
    }

    public String getWaysName() {
        return waysName;
    }

    public void setWaysName(String waysName) {
        this.waysName = waysName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }


    public List<String> getImgPathList() {
        return imgPathList;
    }

    public void setImgPathList(List<String> imgPathList) {
        this.imgPathList = imgPathList;
    }

    public String getFileIds() {
        return fileIds;
    }

    public void setFileIds(String fileIds) {
        this.fileIds = fileIds;
    }

    public String getOperatorName() {
        return operatorName;
    }

    public void setOperatorName(String operatorName) {
        this.operatorName = operatorName;
    }

    public String getTypeName() {
        if(this.getType()!=null) {
            if (this.getType() == 1) {
                return BizConstant.PAYMENT_SIMPLE_TYPE_1;
            } else if (this.getType() == 2) {
                return BizConstant.PAYMENT_SIMPLE_TYPE_2;
            } else if (this.getType() == 3) {
                return BizConstant.PAYMENT_SIMPLE_TYPE_3;
            } else {
                return "";
            }
        }else{
            return "";
        }
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public List<PaymentSimpleFundsDto> getPaymentSimpleFundsList() {
        return paymentSimpleFundsList;
    }

    public void setPaymentSimpleFundsList(List<PaymentSimpleFundsDto> paymentSimpleFundsList) {
        this.paymentSimpleFundsList = paymentSimpleFundsList;
    }
}
