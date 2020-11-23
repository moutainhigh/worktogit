package com.lwxf.industry4.webapp.domain.dto.aftersale;

import java.util.Date;
import java.util.List;

import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleCoupleBackStatus;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleFrom;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleStatus;
import com.lwxf.industry4.webapp.common.enums.aftersale.AftersaleType;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApply;
import com.lwxf.industry4.webapp.domain.entity.aftersale.AftersaleApplyFiles;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/1/3 0003 12:50
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel("售后单信息")
public class AftersaleDto extends AftersaleApply {
	@ApiModelProperty(value = "订单编号")
	private String orderNo;//订单编号
	@ApiModelProperty(value = "客户电话")
	private String customerMobile;//客户电话
	@ApiModelProperty(value = "客户地址")
	private String customerAddress;//客户地址
	@ApiModelProperty(value = "省市区合并后的名称")
	private String mergerName;//省市区合并后的名称
	@ApiModelProperty(value = "订单创建时间")
	private Date orderCreated;//订单创建时间
	@ApiModelProperty(value = "实际交货日期")
	private Date deliveryDate;//实际交货日期
	@ApiModelProperty(value = "创建人名称")
	private String creatorName;//创建人名称
	@ApiModelProperty(value = "创建人电话")
	private String creatorMobile;//创建人电话
	@ApiModelProperty(value = "客户ID")
	private String customerId;//客户ID
	@ApiModelProperty(value = "审核人姓名")
    private String checkerName;// 审核人姓名
	@ApiModelProperty(value = "申请单类型")
	private String aftersaleType;//申请单类型
	@ApiModelProperty(value = "售后订单编号")
	private String aftersaleOrderNo;//售后订单编号
	@ApiModelProperty(value = "经销商名称")
	private String companyName;//经销商名称
	@ApiModelProperty(value = "类型名称")
	private String typeName;//类型名称
	@ApiModelProperty(value = "状态名称")
	private String statusName;//状态名称
	@ApiModelProperty(value = "")
	private String operator;//经销商名称
	@ApiModelProperty(value = "文件列表")
	private List<AftersaleApplyFiles> aftersaleApplyFilesList;
	@ApiModelProperty(value = "产品列表")
	private List<OrderProductDto> orderProductDtoList;
	@ApiModelProperty("售后单来源")
	private String fromTypeName;
    @ApiModelProperty(value = "经销商电话")
	private String companyTel;
	@ApiModelProperty(value = "收货人电话")
    private String consigneeTel;
	@ApiModelProperty(value = "售后产品id")
	private String aftersaleProductid;

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getTypeName() {
		return AftersaleType.getByValue(this.getType()).getName();
	}

	public String getStatusName() {
	    return AftersaleCoupleBackStatus.getByValue(this.getStatus()).getName();
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCheckerName() {
		return checkerName;
	}

	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}


	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorMobile() {
		return creatorMobile;
	}

	public void setCreatorMobile(String creatorMobile) {
		this.creatorMobile = creatorMobile;
	}

	public List<AftersaleApplyFiles> getAftersaleApplyFilesList() {
		return aftersaleApplyFilesList;
	}

	public void setAftersaleApplyFilesList(List<AftersaleApplyFiles> aftersaleApplyFilesList) {
		this.aftersaleApplyFilesList = aftersaleApplyFilesList;
	}
	public String getAftersaleType() {
		return aftersaleType;
	}

	public void setAftersaleType(String aftersaleType) {
		this.aftersaleType = aftersaleType;
	}

	public String getAftersaleOrderNo() {
		return aftersaleOrderNo;
	}

	public void setAftersaleOrderNo(String aftersaleOrderNo) {
		this.aftersaleOrderNo = aftersaleOrderNo;
	}

	public List<OrderProductDto> getOrderProductDtoList() {
		return orderProductDtoList;
	}

	public void setOrderProductDtoList(List<OrderProductDto> orderProductDtoList) {
		this.orderProductDtoList = orderProductDtoList;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

    public String getFromTypeName() {
        if (this.getFromType() == null) {
            return "";
        }
        return AftersaleFrom.getByValue(this.getFromType()).getName();
    }

    public void setFromTypeName(String fromTypeName) {
        this.fromTypeName = fromTypeName;
    }

    public String getCompanyTel() {
        return companyTel;
    }

    public void setCompanyTel(String companyTel) {
        this.companyTel = companyTel;
    }

	@Override
	public String getConsigneeTel() {
		return consigneeTel;
	}

	@Override
	public void setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
	}

	public String getAftersaleProductid() {
		return aftersaleProductid;
	}

	public void setAftersaleProductid(String aftersaleProductid) {
		this.aftersaleProductid = aftersaleProductid;
	}
}
