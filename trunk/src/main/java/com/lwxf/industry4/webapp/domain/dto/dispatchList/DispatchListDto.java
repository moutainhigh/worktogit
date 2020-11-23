package com.lwxf.industry4.webapp.domain.dto.dispatchList;

import java.util.Date;

import com.lwxf.industry4.webapp.domain.entity.assemble.DispatchList;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2018/12/20 0020 13:29
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DispatchListDto extends DispatchList {
	private String orderNo;
	private String shareMemberName;
	private String shareMeberMobile;
	private String customerName;
	private String customerMobile;
    private Date orderCreated;
    private Date deliveryDate;
    private String customerAddress;
    private String auditorName;
    private String customerId;

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getShareMemberName() {
		return shareMemberName;
	}

	public void setShareMemberName(String shareMemberName) {
		this.shareMemberName = shareMemberName;
	}

	public String getShareMeberMobile() {
		return shareMeberMobile;
	}

	public void setShareMeberMobile(String shareMeberMobile) {
		this.shareMeberMobile = shareMeberMobile;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
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
}
