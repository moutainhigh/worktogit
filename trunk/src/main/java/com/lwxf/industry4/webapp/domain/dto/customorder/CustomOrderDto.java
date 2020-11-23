package com.lwxf.industry4.webapp.domain.dto.customorder;

import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/13 15:57
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "订单信息",discriminator = "订单信息")
public class CustomOrderDto extends CustomOrder{
    @ApiModelProperty(value = "城市名称")
    private String cityName; //城市名称
    @ApiModelProperty(value = "业务员名称")
    private String salesmanName; //业务员名称
    @ApiModelProperty(value = "业务员电话")
    private String salesmanTel;  //业务员电话
    @ApiModelProperty(value = "客户名称")
    private String customerName;  //客户名称
    @ApiModelProperty(value = "业务员头像")
    private String salesmanAvatar;  //业务员头像
    @ApiModelProperty(value = "经销商名称")
    private String dealerName;//经销商名称
    @ApiModelProperty(value = "经销商地址")
    private String dealerAddress;//经销商地址
    @ApiModelProperty(value = "经销商所在地区ID")
    private String dealerCityAreaId;//经销商所在地区ID
    @ApiModelProperty(value = "经销商电话")
    private String dealerTel;//经销商电话
    @ApiModelProperty(value = "创建人名称")
    private String creatorName;//创建人名称
    @ApiModelProperty(value = "设计师名称")
    private String designerName;//设计师名称
    @ApiModelProperty(value = "包裹总数")
    private Integer counts;
    @ApiModelProperty(value = "跟单员名称")
    private String merchandiserName;
    @ApiModelProperty(value = "下单时间")
    private String orderTime;
    @ApiModelProperty(value = "下单人")
    private String placeOrderName;
    @ApiModelProperty(value = "财务审核时间")
    private Date payTime;
    @ApiModelProperty(value = "省ID")
    private String provinceId;
    @ApiModelProperty(value = "市ID")
    private String cityId;
    @ApiModelProperty(value = "业务经理名称")
    private String businessManagerName;
    @ApiModelProperty(value = "业务经理电话")
    private String businessManagerTel;
    @ApiModelProperty(value = "门板包裹数量")
    private Integer doorCount;
    @ApiModelProperty(value = "柜体包裹数量")
    private Integer cabinetCount;
    @ApiModelProperty(value = "五金包裹数量")
    private Integer hardwareCount;
    @ApiModelProperty(value = "是否存在门板生产单")
    private Integer existDoor;
    @ApiModelProperty(value = "是否存在柜体生产单")
    private Integer existCabinet;
    @ApiModelProperty(value = "是否存在五金生产单")
    private Integer existHardware;
    @ApiModelProperty(value = "订单状态")
    private String orderStatus;
    @ApiModelProperty(value = "客户地址")
    private String customerAddress;
    @ApiModelProperty(value = "类型转义")
    private String typeName;
    @ApiModelProperty(value = "是否需要设计转义")
    private String designName;
    @ApiModelProperty(value = "接单员名称")
    private String receiverName;
    @ApiModelProperty(value = "订单产品列表")
    private List<OrderProductDto> orderProductDtoList;
    @ApiModelProperty(value = "剩余时间")
    private String timeRemaining;//剩余时间
    @ApiModelProperty(value = "已耗时",name="timeConsuming",hidden = true)
    private String timeConsuming;//已耗时
    @ApiModelProperty(value = "剩余时间状态",name="timeRemainingStatus")
    private Integer timeRemainingStatus;//剩余时间状态
    @ApiModelProperty(value = "附件id列表逗号隔开")
    private String files;//剩余时间
    @ApiModelProperty(value = "订单合同附件")
    private List<CustomOrderFiles> orderContractFiles;
    @ApiModelProperty(value = "售后产品id")
    private String aftersaleProductId;//售后产品id（创建售后盯单时用）
    @ApiModelProperty(value = "售后产品编号")
    private String aftersaleProductNo;
    @ApiModelProperty(value = "产品系列名称")
    private String seriesName;
    @ApiModelProperty(value = "产品门板颜色")
    private String doorColor;
    @ApiModelProperty(value = "产品开始生产时间")
    private String startProduceTime;
    @ApiModelProperty(value = "订单所有部件数量")
    private Integer allProduceNum;
    @ApiModelProperty(value = "订单已入库包裹数量")
    private Integer hasInputProduceNum;
    @ApiModelProperty(value = "订单产品数量")
    private Integer orderProductNum;
    @ApiModelProperty(value = "订单物流单号（可能有多个）")
    private List<String> logisticsNos;
    @ApiModelProperty(value = "追加单编号（可能有多个）")
    private List<String> appendOrderNos;
    @ApiModelProperty(value = "追加单产品列表")
    private List<OrderProductDto> appendProductDtoList;
    @ApiModelProperty(value = "经销商默认物流名称")
    private String dealerLogistics;
    @ApiModelProperty(value = "经销商默认物流ID")
    private String dealerLogisticsId;
    @ApiModelProperty(value = "外协信息")
    private List<String> outProduceInfo;
    @ApiModelProperty(value = "（B端）家居顾问名称")
    private String householdConsultant;
    @ApiModelProperty(value = "（B端）设计师名称")
    private String dealerDesigner;
    @ApiModelProperty(value = "（B端）安装工名称")
    private String dealerErector;
    @ApiModelProperty(value = "代扣款经销商名称")
    private String withholdingCompanyName;
    @ApiModelProperty(value = "审核人员姓名")
    private String auditorName;
    @ApiModelProperty(value = "接单工期")
    private String receiveInfo;
    @ApiModelProperty(value = "下单工期")
    private String placeTimeInfo;
    @ApiModelProperty(value = "催款工期")
    private String pressForMoneyTime;
    @ApiModelProperty(value = "审核工期")
    private String auditInfo;
    @ApiModelProperty(value = "生产发货工期")
    private String produceAndDeliveryInfo;
    @ApiModelProperty(value = "挂账标识")
    private String onCredit;
    @ApiModelProperty(value = "催款客服")
    private String pressName;
    @ApiModelProperty(value = "是否外协的值 flase-0 true-1")
    private String coordinationValue;
    @ApiModelProperty(value = "售后责任人名称")
    private String afterPersonName;
    @ApiModelProperty(value = "售后责任部门名称")
    private String afterDeptName;



    public String getFiles() {
        return files;
    }

    public void setFiles(String files) {
        this.files = files;
    }

    private BigDecimal orderAmount = new BigDecimal(0);//订单金额


    public BigDecimal getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(BigDecimal orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getSalesmanAvatar() {
        return salesmanAvatar;
    }

    public void setSalesmanAvatar(String salesmanAvatar) {
        this.salesmanAvatar = salesmanAvatar;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getSalesmanName() {
        return salesmanName;
    }

    public void setSalesmanName(String salesmanName) {
        this.salesmanName = salesmanName;
    }

    public String getSalesmanTel() {
        return salesmanTel;
    }

    public void setSalesmanTel(String salesmanTel) {
        this.salesmanTel = salesmanTel;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
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

    public String getDealerCityAreaId() {
        return dealerCityAreaId;
    }

    public void setDealerCityAreaId(String dealerCityAreaId) {
        this.dealerCityAreaId = dealerCityAreaId;
    }

    public String getDealerTel() {
        return dealerTel;
    }

    public void setDealerTel(String dealerTel) {
        this.dealerTel = dealerTel;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getDesignerName() {
        return designerName;
    }

    public void setDesignerName(String designerName) {
        this.designerName = designerName;
    }

    public Integer getCounts() {
        return counts;
    }

    public void setCounts(Integer counts) {
        this.counts = counts;
    }

    public String getMerchandiserName() {
        return merchandiserName;
    }

    public void setMerchandiserName(String merchandiserName) {
        this.merchandiserName = merchandiserName;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public Date getPayTime() {
        return payTime;
    }

    public void setPayTime(Date payTime) {
        this.payTime = payTime;
    }

    public String getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(String provinceId) {
        this.provinceId = provinceId;
    }

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getBusinessManagerName() {
        return businessManagerName;
    }

    public void setBusinessManagerName(String businessManagerName) {
        this.businessManagerName = businessManagerName;
    }

    public String getBusinessManagerTel() {
        return businessManagerTel;
    }

    public void setBusinessManagerTel(String businessManagerTel) {
        this.businessManagerTel = businessManagerTel;
    }

    public Integer getDoorCount() {
        return doorCount;
    }

    public void setDoorCount(Integer doorCount) {
        this.doorCount = doorCount;
    }

    public Integer getCabinetCount() {
        return cabinetCount;
    }

    public void setCabinetCount(Integer cabinetCount) {
        this.cabinetCount = cabinetCount;
    }

    public Integer getHardwareCount() {
        return hardwareCount;
    }

    public void setHardwareCount(Integer hardwareCount) {
        this.hardwareCount = hardwareCount;
    }

    public Integer getExistDoor() {
        return existDoor;
    }

    public void setExistDoor(Integer existDoor) {
        this.existDoor = existDoor;
    }

    public Integer getExistCabinet() {
        return existCabinet;
    }

    public void setExistCabinet(Integer existCabinet) {
        this.existCabinet = existCabinet;
    }

    public Integer getExistHardware() {
        return existHardware;
    }

    public void setExistHardware(Integer existHardware) {
        this.existHardware = existHardware;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getDesignName() {
        return designName;
    }

    public void setDesignName(String designName) {
        this.designName = designName;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public List<OrderProductDto> getOrderProductDtoList() {
        return orderProductDtoList;
    }

    public void setOrderProductDtoList(List<OrderProductDto> orderProductDtoList) {
        this.orderProductDtoList = orderProductDtoList;
    }

    public String getPlaceOrderName() {
        return placeOrderName;
    }

    public void setPlaceOrderName(String placeOrderName) {
        this.placeOrderName = placeOrderName;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public String getAftersaleProductId() {
        return aftersaleProductId;
    }

    public void setAftersaleProductId(String aftersaleProductId) {
        this.aftersaleProductId = aftersaleProductId;
    }

    public String getAftersaleProductNo() {
        return aftersaleProductNo;
    }

    public void setAftersaleProductNo(String aftersaleProductNo) {
        this.aftersaleProductNo = aftersaleProductNo;
    }

    public String getSeriesName() {
        return seriesName;
    }

    public void setSeriesName(String seriesName) {
        this.seriesName = seriesName;
    }

    public String getStartProduceTime() {
        return startProduceTime;
    }

    public void setStartProduceTime(String startProduceTime) {
        this.startProduceTime = startProduceTime;
    }

    public Integer getAllProduceNum() {
        return allProduceNum;
    }

    public void setAllProduceNum(Integer allProduceNum) {
        this.allProduceNum = allProduceNum;
    }

    public Integer getHasInputProduceNum() {
        return hasInputProduceNum;
    }

    public void setHasInputProduceNum(Integer hasInputProduceNum) {
        this.hasInputProduceNum = hasInputProduceNum;
    }

    public Integer getOrderProductNum() {
        return orderProductNum;
    }

    public void setOrderProductNum(Integer orderProductNum) {
        this.orderProductNum = orderProductNum;
    }

    public List<String> getLogisticsNos() {
        return logisticsNos;
    }

    public void setLogisticsNos(List<String> logisticsNos) {
        this.logisticsNos = logisticsNos;
    }

    public List<String> getAppendOrderNos() {
        return appendOrderNos;
    }

    public void setAppendOrderNos(List<String> appendOrderNos) {
        this.appendOrderNos = appendOrderNos;
    }

    public List<OrderProductDto> getAppendProductDtoList() {
        return appendProductDtoList;
    }

    public void setAppendProductDtoList(List<OrderProductDto> appendProductDtoList) {
        this.appendProductDtoList = appendProductDtoList;
    }

    public String getDealerLogistics() {
        return dealerLogistics;
    }

    public void setDealerLogistics(String dealerLogistics) {
        this.dealerLogistics = dealerLogistics;
    }

    public String getDealerLogisticsId() {
        return dealerLogisticsId;
    }

    public void setDealerLogisticsId(String dealerLogisticsId) {
        this.dealerLogisticsId = dealerLogisticsId;
    }

    public List<String> getOutProduceInfo() {
        return outProduceInfo;
    }

    public void setOutProduceInfo(List<String> outProduceInfo) {
        this.outProduceInfo = outProduceInfo;
    }

    public String getHouseholdConsultant() {
        return householdConsultant;
    }

    public void setHouseholdConsultant(String householdConsultant) {
        this.householdConsultant = householdConsultant;
    }

    public String getDealerDesigner() {
        return dealerDesigner;
    }

    public void setDealerDesigner(String dealerDesigner) {
        this.dealerDesigner = dealerDesigner;
    }

    public String getDealerErector() {
        return dealerErector;
    }

    public void setDealerErector(String dealerErector) {
        this.dealerErector = dealerErector;
    }

    @Override
    public String getTimeConsuming() {
        return timeConsuming;
    }

    @Override
    public void setTimeConsuming(String timeConsuming) {
        this.timeConsuming = timeConsuming;
    }

    public Integer getTimeRemainingStatus() {
        return timeRemainingStatus;
    }

    public void setTimeRemainingStatus(Integer timeRemainingStatus) {
        this.timeRemainingStatus = timeRemainingStatus;
    }

    public List<CustomOrderFiles> getOrderContractFiles() {
        return orderContractFiles;
    }

    public void setOrderContractFiles(List<CustomOrderFiles> orderContractFiles) {
        this.orderContractFiles = orderContractFiles;
    }

    public String getDoorColor() {
        return doorColor;
    }

    public void setDoorColor(String doorColor) {
        this.doorColor = doorColor;
    }

    public String getWithholdingCompanyName() {
        return withholdingCompanyName;
    }

    public void setWithholdingCompanyName(String withholdingCompanyName) {
        this.withholdingCompanyName = withholdingCompanyName;
    }

    public String getAuditorName() {
        return auditorName;
    }

    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName;
    }

    public String getReceiveInfo() {
        return receiveInfo;
    }

    public void setReceiveInfo(String receiveInfo) {
        this.receiveInfo = receiveInfo;
    }

    public String getPlaceTimeInfo() {
        return placeTimeInfo;
    }

    public void setPlaceTimeInfo(String placeTimeInfo) {
        this.placeTimeInfo = placeTimeInfo;
    }

    public String getPressForMoneyTime() {
        return pressForMoneyTime;
    }

    public void setPressForMoneyTime(String pressForMoneyTime) {
        this.pressForMoneyTime = pressForMoneyTime;
    }

    public String getAuditInfo() {
        return auditInfo;
    }

    public void setAuditInfo(String auditInfo) {
        this.auditInfo = auditInfo;
    }

    public String getProduceAndDeliveryInfo() {
        return produceAndDeliveryInfo;
    }

    public void setProduceAndDeliveryInfo(String produceAndDeliveryInfo) {
        this.produceAndDeliveryInfo = produceAndDeliveryInfo;
    }

    public String getOnCredit() {
        return onCredit;
    }

    public void setOnCredit(String onCredit) {
        this.onCredit = onCredit;
    }

    public String getPressName() {
        return pressName;
    }

    public void setPressName(String pressName) {
        this.pressName = pressName;
    }

    public String getCoordinationValue() {
        return coordinationValue;
    }

    public void setCoordinationValue(String coordinationValue) {
        this.coordinationValue = coordinationValue;
    }

    public String getAfterPersonName() {
        return afterPersonName;
    }

    public void setAfterPersonName(String afterPersonName) {
        this.afterPersonName = afterPersonName;
    }

    public String getAfterDeptName() {
        return afterDeptName;
    }

    public void setAfterDeptName(String afterDeptName) {
        this.afterDeptName = afterDeptName;
    }
}

