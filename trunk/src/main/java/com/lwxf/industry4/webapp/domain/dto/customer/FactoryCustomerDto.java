package com.lwxf.industry4.webapp.domain.dto.customer;

import java.util.Date;
import java.util.List;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/4/2 0002 13:30
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class FactoryCustomerDto {
	private String customerName;//客户姓名
	private String customerMergerName;//客户区域地址
	private String customerAddress;//客户详细地址
	private Integer customerSource;//客户来源
	private String userId;//客户用户表Id
	private String customerId;//公司客户表Id
	private String companyId;//所属经销商公司ID
	private String companyName;//经销商名称
	private Date created;//注册时间

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public Integer getCustomerSource() {
		return customerSource;
	}

	public void setCustomerSource(Integer customerSource) {
		this.customerSource = customerSource;
	}

	public String getCustomerMergerName() {
		return customerMergerName;
	}

	public void setCustomerMergerName(String customerMergerName) {
		this.customerMergerName = customerMergerName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "{" +
				"customerName:'" + customerName + '\'' +
				", customerAddress:'" + customerAddress + '\'' +
				", customerSource:" + customerSource +
				'}';
	}
}
