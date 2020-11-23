package com.lwxf.industry4.webapp.domain.dto.financing;

import com.lwxf.industry4.webapp.common.enums.financing.PaymentStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.List;

import com.lwxf.industry4.webapp.common.constant.BizConstant;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentSimpleFunds;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/19 15:23
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "支付记录信息",discriminator = "支付记录信息")
public class PaymentDto extends Payment {

    @ApiModelProperty(value = "收款人")
    private  String payeeName;//收款人
    @ApiModelProperty(value = "审核人")
    private  String auditorName;//审核人
    @ApiModelProperty(value = "交易类型，线上，线下等")
    private Integer dalType;//交易类型，线上，线下等
    @ApiModelProperty(value = "创建人")
    private String creatorName;//创建人
    @ApiModelProperty(value = "转账时间")
    private String transferTime;//转账时间
    @ApiModelProperty(value = "订单编号")
    private String orderNo;//订单编号
    @ApiModelProperty(value = "源订单编号")
    private String oldNo;//源订单编号
    @ApiModelProperty(value = "公司名称")
    private String companyName;//公司名称
    @ApiModelProperty(value = "客户名称")
    private String customName;//客户名称
    @ApiModelProperty(value = "订单类型:0 - 正常订单；1 - 补产订单；2 - 返货单；3 - 打样订单；4 - 样板订单；5 - 展示厅订单;6 - 补发订单")
    private Integer orderType;
    @ApiModelProperty(value="类型名称",name="companyName")
    private String typeName;
    @ApiModelProperty(value="支付方式名称",name="waysName")
    private String waysName;
    @ApiModelProperty(value="款项名称",name="fundsName")
    private String fundsName;
    @ApiModelProperty(value="付款状态名称",name="statusName")
    private String statusName;
    @ApiModelProperty(value = "资源文件")
    private List<PaymentFiles> paymentFilesList;
    @ApiModelProperty(value = "下单人")
    private  String placeOrderName;//收款人
    @ApiModelProperty(value = "订单状态")
    private  String orderStatus;
    @ApiModelProperty(value = "订单金额")
    private BigDecimal orderPrice;
    @ApiModelProperty(value = "代扣款经销商名称")
    private  String withholdingCompanyName;

    @ApiModelProperty(value = "是否代扣款")
    private  Integer paymentWithholding;
    @ApiModelProperty(value = "代扣款经销商id")
    private  String withholdingCompanyId;
    @ApiModelProperty(value = "审核工期")
    private String auditInfo;

    public String getStatusName() {
       String statusValue= PaymentStatus.getByValue(this.getStatus()).getName();
        return statusValue;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getWaysName() {
        if(this.getWay()!=null&& this.getWay()>-1){
            return PaymentWay.getByValue(this.getWay())==null?"":PaymentWay.getByValue(this.getWay()).getName();
        }else{
            return "";
        }
    }

    public void setWaysName(String waysName) {
        this.waysName = waysName;
    }

    public String getFundsName() {
        return fundsName;
    }

    public void setFundsName(String fundsName) {
        this.fundsName = fundsName;
    }
    public String getTypeName() {
        if(this.getType()!=null && this.getType()>-1){
            return PaymentTypeNew.getByValue(this.getType()).getName();
        }else{
            return "";
        }

    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        transferTime = transferTime;
    }

    public Integer getDalType() {
        return dalType;
    }

    public void setDalType(Integer dalType) {
        this.dalType = dalType;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public List<PaymentFiles> getPaymentFilesList() {
        return paymentFilesList;
    }

    public void setPaymentFilesList(List<PaymentFiles> paymentFilesList) {
        this.paymentFilesList = paymentFilesList;
    }

    public String getPlaceOrderName() {
        return placeOrderName;
    }

    public void setPlaceOrderName(String placeOrderName) {
        this.placeOrderName = placeOrderName;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getOldNo() {
        return oldNo;
    }

    public void setOldNo(String oldNo) {
        this.oldNo = oldNo;
    }

    public BigDecimal getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(BigDecimal orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getWithholdingCompanyName() {
        return withholdingCompanyName;
    }

    public void setWithholdingCompanyName(String withholdingCompanyName) {
        this.withholdingCompanyName = withholdingCompanyName;
    }

    public Integer getPaymentWithholding() {
        return paymentWithholding;
    }

    public void setPaymentWithholding(Integer paymentWithholding) {
        this.paymentWithholding = paymentWithholding;
    }

    public String getWithholdingCompanyId() {
        return withholdingCompanyId;
    }

    public void setWithholdingCompanyId(String withholdingCompanyId) {
        this.withholdingCompanyId = withholdingCompanyId;
    }

    public String getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
    }
}

