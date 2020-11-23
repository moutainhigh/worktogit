package com.lwxf.industry4.webapp.domain.dto.financing;

import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderState;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentTypeNew;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentWay;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceFlowDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;
import com.lwxf.industry4.webapp.domain.entity.financing.Payment;
import com.lwxf.industry4.webapp.domain.entity.financing.PaymentFiles;
import com.lwxf.mybatis.annotation.Column;
import com.lwxf.mybatis.utils.TypesExtend;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/19 15:23
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "支付记录信息",discriminator = "支付记录信息")
public class PaymentProduceOrderDto extends Payment {

    private String id;

    private String payId;



    private static final long serialVersionUID = 1L;
    @Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "custom_order_id",displayName = "订单主键ID")
    @ApiModelProperty(value = "订单主键ID")
    private String customOrderId;
    @Column(type = Types.VARCHAR,length = 50,updatable = false,name = "custom_order_no",displayName = "订单编号")
    @ApiModelProperty(value = "订单编号")
    private String customOrderNo;
    @Column(type = Types.VARCHAR,length = 50,nullable = false,updatable = false,name = "no",displayName = "生产拆单明细编号")
    @ApiModelProperty(value = "生产拆单明细编号")
    private String no;
    @Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "creator",displayName = "创建人")
    @ApiModelProperty(value = "创建人")
    private String creator;
    @Column(type = TypesExtend.DATETIME,nullable = false,updatable = false,name = "created",displayName = "创建时间")
    @ApiModelProperty(value = "创建时间")
    private Date created;
    @Column(type = Types.CHAR,length = 13,name = "update_user",displayName = "修改人")
    @ApiModelProperty(value = "修改人")
    private String updateUser;
    @Column(type = TypesExtend.DATETIME,name = "update_time",displayName = "修改时间")
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;
    @Column(type = TypesExtend.DATETIME,name = "completion_time",displayName = "完成时间")
    @ApiModelProperty(value = "完成时间")
    private Date completionTime;
    @Column(type = Types.INTEGER,nullable = false,name = "count",displayName = "数量")
    @ApiModelProperty(value = "数量")
    private Integer count;
    @Column(type = Types.VARCHAR,length = 300,name = "notes",displayName = "说明")
    @ApiModelProperty(value = "说明")
    private String notes;
    @Column(type = Types.DECIMAL,precision = 10,scale=2,name = "amount",displayName = "金额 是外协单时 才存在")
    @ApiModelProperty(value = "金额 是外协单时 才存在")
    private BigDecimal amount;
    private BigDecimal payamount;
    @Column(type = Types.VARCHAR,length = 50,name = "coordination_name",displayName = "外协厂家名称")
    @ApiModelProperty(value = "外协厂家名称")
    private String coordinationName;
    @Column(type = Types.VARCHAR,length = 50,name = "coordination_account",displayName = "外协厂家账户")
    @ApiModelProperty(value = "外协厂家账户")
    private String coordinationAccount;
    @Column(type = Types.VARCHAR,length = 50,name = "coordination_bank",displayName = "外协厂家开户行")
    @ApiModelProperty(value = "外协厂家开户行")
    private String coordinationBank;
    @Column(type = Types.BIT,nullable = false,name = "is_pay",displayName = "是否付款 false 0 未付款 true 1 已付款")
    @ApiModelProperty(value = "是否付款 false 0 未付款 true 1 已付款")
    private Boolean pay;
    @Column(type = Types.TINYINT,nullable = false,name = "type",displayName = "生产拆单类型 0 柜体 1 门板 2 五金")
    @ApiModelProperty(value = "生产拆单类型 0 柜体 1 门板 2 五金")
    private Integer type;
    @Column(type = Types.TINYINT,nullable = false,name = "way",displayName = "生产方式 0 自产 1 外协 2 特供实木")
    @ApiModelProperty(value = "生产方式 0 自产 1 外协 2 特供实木")
    private Integer way;
    @Column(type = Types.TINYINT,nullable = false,name = "state",displayName = "生产拆单状态:0 未开始 1 生产中 2 已完成")
    @ApiModelProperty(value = "生产拆单状态  自产:0 未开始 1 生产中 2 已完成   外协:0 待报价 1 待支付 2 待收货 3 完成")
    private Integer state;
    @Column(type = Types.DATE,name = "planned_time",displayName = "计划生产时间")
    @ApiModelProperty(value = "计划生产时间")
    private Date plannedTime;
    @Column(type = Types.DATE,name = "actual_time",displayName = "实际生产时间")
    @ApiModelProperty(value = "实际生产时间")
    private Date actualTime;
    private String branchId;
    @Column(type = Types.CHAR,length = 13,updatable = false,name = "order_product_id",displayName = "订单产品ID")
    private String orderProductId;
    @Column(type = Types.TINYINT,name = "permit",displayName = "是否允许生产 0 不允许 1 允许 只使用在自产的生产单")
    @ApiModelProperty(value = "是否允许生产 0 不允许 1 允许 只使用在自产的生产单")
    private Integer permit;
    @Column(type = Types.TINYINT,name = "resource_type",displayName = "0:订单,1:售后单")
    @ApiModelProperty(value = "0:订单,1:售后单")
    private Integer resourceType;
    @Column(type = Types.DATE,name = "plan_created",displayName = "生产计划创建时间")
    @ApiModelProperty(value = "生产计划创建时间")
    private Date planCreated;
    @Column(type = Types.CHAR,length = 13,nullable = false,updatable = false,name = "plan_creator",displayName = "生产计划创建人")
    @ApiModelProperty(value = "生产计划创建人")
    private String planCreator;
    @Column(type = Types.TINYINT,length = 2,nullable = false,updatable = false,name = "flag",displayName = "存在状态 0-正常 1-删除")
    @ApiModelProperty(value = "存在状态 0-正常 1-删除")
    private Integer flag;
    @ApiModelProperty(value = "物流公司id")
    @Column(type = Types.CHAR,length = 13,name = "logistics_company_id",displayName = "物流公司id")
    private String logisticsCompanyId;
    @ApiModelProperty(value = "物流单号")
    @Column(type = Types.VARCHAR,length = 50,name = "logistics_no",displayName = "物流单号")
    private String logisticsNo;
    @ApiModelProperty(value = "发货时间")
    @Column(type = TypesExtend.DATETIME,name = "delivered",displayName = "发货时间")
    private Date delivered;
    @ApiModelProperty(value = "发货人")
    @Column(type = Types.CHAR,length = 13,name = "deliverer",displayName = "发货人")
    private String deliverer;
    @ApiModelProperty(value = "是否已发货")
    @Column(type = Types.TINYINT,length = 2,name = "deliver_status",displayName = "是否已发货")
    private Integer deliverStatus;
    @ApiModelProperty(value = "是否创建配送计划")
    @Column(type = Types.TINYINT,length = 2,name = "is_shipped",displayName = "是否创建配送计划")
    private Integer shipped;
    @ApiModelProperty(value = "付款状态")
    private Integer status;




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
    @ApiModelProperty(value = "修改人名称")
    private String updateUserName;
    @ApiModelProperty(value = "跟单员名称")
    private String merchandiserName;
    @ApiModelProperty(value = "经销商名称")
    private String dealerName;
    @ApiModelProperty(value = "经销商地址")
    private String dealerAddress;
    @ApiModelProperty(value = "经销商id")
    private String companyId;
    @ApiModelProperty(value = "客户名称")
    private String customerName;
    @ApiModelProperty(value = "有效时间")
    private Date payTime;
    @ApiModelProperty(value = "计划交付时间")
    private Date estimatedDeliveryDate;
    @ApiModelProperty(value = "生产流程信息")
    private List<ProduceFlowDto> produceFlowDtos;
    @ApiModelProperty(value = "附件集合")
    private List<CustomOrderFiles> uploadFiles;
    @ApiModelProperty(value = "图片id集合")
    private List<String> fileIds;
    @ApiModelProperty(value = "是否付款 转义")
    private String payName;
    @ApiModelProperty(value = "生产方式 转义")
    private String wayName;
    @ApiModelProperty(value = "状态 转义")
    private String stateName;
    @ApiModelProperty(value = "是否允许生产 转义")
    private String permitName;
    @ApiModelProperty(value = "产品信息")
    private OrderProductDto orderProductDto;
    @ApiModelProperty(value = "物流公司")
    private String logisticsCompany;
    @ApiModelProperty(value = "部件标识")
    private String partMark;


    @Override
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String getId() {
        return id;
    }


    public BigDecimal getPayamount() {
        return payamount;
    }

    public void setPayamount(BigDecimal payamount) {
        this.payamount = payamount;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    @Override
    public String getCustomOrderId() {
        return customOrderId;
    }

    @Override
    public void setCustomOrderId(String customOrderId) {
        this.customOrderId = customOrderId;
    }

    public String getCustomOrderNo() {
        return customOrderNo;
    }

    public void setCustomOrderNo(String customOrderNo) {
        this.customOrderNo = customOrderNo;
    }

    @Override
    public String getNo() {
        return no;
    }

    @Override
    public void setNo(String no) {
        this.no = no;
    }

    @Override
    public String getCreator() {
        return creator;
    }

    @Override
    public void setCreator(String creator) {
        this.creator = creator;
    }

    @Override
    public Date getCreated() {
        return created;
    }

    @Override
    public void setCreated(Date created) {
        this.created = created;
    }

    public String getUpdateUser() {
        return updateUser;
    }

    public void setUpdateUser(String updateUser) {
        this.updateUser = updateUser;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCompletionTime() {
        return completionTime;
    }

    public void setCompletionTime(Date completionTime) {
        this.completionTime = completionTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String getNotes() {
        return notes;
    }

    @Override
    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public BigDecimal getAmount() {
        return amount;
    }

    @Override
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCoordinationName() {
        return coordinationName;
    }

    public void setCoordinationName(String coordinationName) {
        this.coordinationName = coordinationName;
    }

    public String getCoordinationAccount() {
        return coordinationAccount;
    }

    public void setCoordinationAccount(String coordinationAccount) {
        this.coordinationAccount = coordinationAccount;
    }

    public String getCoordinationBank() {
        return coordinationBank;
    }

    public void setCoordinationBank(String coordinationBank) {
        this.coordinationBank = coordinationBank;
    }

    public Boolean getPay() {
        return pay;
    }

    public void setPay(Boolean pay) {
        this.pay = pay;
    }

    @Override
    public Integer getType() {
        return type;
    }

    @Override
    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public Integer getWay() {
        return way;
    }

    @Override
    public void setWay(Integer way) {
        this.way = way;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getPlannedTime() {
        return plannedTime;
    }

    public void setPlannedTime(Date plannedTime) {
        this.plannedTime = plannedTime;
    }

    public Date getActualTime() {
        return actualTime;
    }

    public void setActualTime(Date actualTime) {
        this.actualTime = actualTime;
    }

    @Override
    public String getBranchId() {
        return branchId;
    }

    @Override
    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(String orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Integer getPermit() {
        return permit;
    }

    public void setPermit(Integer permit) {
        this.permit = permit;
    }

    @Override
    public Integer getResourceType() {
        return resourceType;
    }

    @Override
    public void setResourceType(Integer resourceType) {
        this.resourceType = resourceType;
    }

    public Date getPlanCreated() {
        return planCreated;
    }

    public void setPlanCreated(Date planCreated) {
        this.planCreated = planCreated;
    }

    public String getPlanCreator() {
        return planCreator;
    }

    public void setPlanCreator(String planCreator) {
        this.planCreator = planCreator;
    }

    @Override
    public Integer getFlag() {
        return flag;
    }

    @Override
    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getLogisticsCompanyId() {
        return logisticsCompanyId;
    }

    public void setLogisticsCompanyId(String logisticsCompanyId) {
        this.logisticsCompanyId = logisticsCompanyId;
    }

    public String getLogisticsNo() {
        return logisticsNo;
    }

    public void setLogisticsNo(String logisticsNo) {
        this.logisticsNo = logisticsNo;
    }

    public Date getDelivered() {
        return delivered;
    }

    public void setDelivered(Date delivered) {
        this.delivered = delivered;
    }

    public String getDeliverer() {
        return deliverer;
    }

    public void setDeliverer(String deliverer) {
        this.deliverer = deliverer;
    }

    public Integer getDeliverStatus() {
        return deliverStatus;
    }

    public void setDeliverStatus(Integer deliverStatus) {
        this.deliverStatus = deliverStatus;
    }

    public Integer getShipped() {
        return shipped;
    }

    public void setShipped(Integer shipped) {
        this.shipped = shipped;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public Integer getDalType() {
        return dalType;
    }

    public void setDalType(Integer dalType) {
        this.dalType = dalType;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getTransferTime() {
        return transferTime;
    }

    public void setTransferTime(String transferTime) {
        this.transferTime = transferTime;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getOldNo() {
        return oldNo;
    }

    public void setOldNo(String oldNo) {
        this.oldNo = oldNo;
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

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getWaysName() {
        return waysName;
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

    public String getStatusName() {
        if(this.status!=null&&!this.status.equals("")) {
            return PaymentStatus.getByValue(this.status).getName();
        }
        return null;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
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

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName;
    }

    public String getMerchandiserName() {
        return merchandiserName;
    }

    public void setMerchandiserName(String merchandiserName) {
        this.merchandiserName = merchandiserName;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    @Override
    public String getCompanyId() {
        return companyId;
    }

    @Override
    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public Date getPayTime() {
        return payTime;
    }

    @Override
    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public Date getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public List<ProduceFlowDto> getProduceFlowDtos() {
        return produceFlowDtos;
    }

    public void setProduceFlowDtos(List<ProduceFlowDto> produceFlowDtos) {
        this.produceFlowDtos = produceFlowDtos;
    }

    public List<CustomOrderFiles> getUploadFiles() {
        return uploadFiles;
    }

    public void setUploadFiles(List<CustomOrderFiles> uploadFiles) {
        this.uploadFiles = uploadFiles;
    }

    public List<String> getFileIds() {
        return fileIds;
    }

    public void setFileIds(List<String> fileIds) {
        this.fileIds = fileIds;
    }

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getWayName() {
        return wayName;
    }

    public void setWayName(String wayName) {
        this.wayName = wayName;
    }

    public String getStateName() {
        String stateNameValue="";
        if(this.state!=null&&!this.state.equals("")){
            stateNameValue=ProduceOrderState.getByValue(this.state).getName();
        }
        return stateNameValue;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getPermitName() {
        return permitName;
    }

    public void setPermitName(String permitName) {
        this.permitName = permitName;
    }

    public OrderProductDto getOrderProductDto() {
        return orderProductDto;
    }

    public void setOrderProductDto(OrderProductDto orderProductDto) {
        this.orderProductDto = orderProductDto;
    }

    public String getLogisticsCompany() {
        return logisticsCompany;
    }

    public void setLogisticsCompany(String logisticsCompany) {
        this.logisticsCompany = logisticsCompany;
    }

    public String getPartMark() {
        return partMark;
    }

    public void setPartMark(String partMark) {
        this.partMark = partMark;
    }

}

