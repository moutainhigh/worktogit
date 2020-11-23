package com.lwxf.industry4.webapp.domain.dto.customorder;

import com.lwxf.industry4.webapp.common.enums.order.ProduceOrderType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Date;
import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderFiles;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceOrder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/12/012 16:57
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "生产拆单信息",discriminator = "生产拆单信息")
public class ProduceOrderDto extends ProduceOrder {
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;
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
	@ApiModelProperty(value = "类型 转义")
	private String typeName;
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
	@ApiModelProperty(value = "应发货数量")
	private String toBeDeliver;

	public String getDealerAddress() {return dealerAddress;}

	public void setDealerAddress(String dealerAddress) {this.dealerAddress = dealerAddress;}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public List<ProduceFlowDto> getProduceFlowDtos() {
		return produceFlowDtos;
	}

	public void setProduceFlowDtos(List<ProduceFlowDto> produceFlowDtos) {
		this.produceFlowDtos = produceFlowDtos;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Date getPayTime() {
		return payTime;
	}

	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}

	public Date getEstimatedDeliveryDate() {
		return estimatedDeliveryDate;
	}

	public void setEstimatedDeliveryDate(Date estimatedDeliveryDate) {
		this.estimatedDeliveryDate = estimatedDeliveryDate;
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

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getWayName() {
		return wayName;
	}

	public void setWayName(String wayName) {
		this.wayName = wayName;
	}

	public String getStateName() {
		return stateName;
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

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
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

	public String getToBeDeliver() {
		return toBeDeliver;
	}

	public void setToBeDeliver(String toBeDeliver) {
		this.toBeDeliver = toBeDeliver;
	}
}
