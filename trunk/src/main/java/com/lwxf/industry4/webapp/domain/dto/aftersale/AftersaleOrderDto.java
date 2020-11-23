package com.lwxf.industry4.webapp.domain.dto.aftersale;

import java.util.Date;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/4/25 0025 10:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class AftersaleOrderDto {
	private String id;//订单id
	private String no;//订单编号
	private String isDesign;//订单类型:正常/散单
	private String isCoordination;//生产方式: 自产/外协
	private Date orderCreated;//创建时间
	private String merchandiserName;//跟单人
	private String creatorName;//创建人
	private String companyName;//经销商名称
	private Date documentaryTime;//下车间时间
	private String customerName;//客户姓名
	private String leaderName;//经销商负责人

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getIsDesign() {
		return isDesign;
	}

	public void setIsDesign(String isDesign) {
		this.isDesign = isDesign;
	}

	public String getIsCoordination() {
		return isCoordination;
	}

	public void setIsCoordination(String isCoordination) {
		this.isCoordination = isCoordination;
	}

	public Date getOrderCreated() {
		return orderCreated;
	}

	public void setOrderCreated(Date orderCreated) {
		this.orderCreated = orderCreated;
	}

	public String getMerchandiserName() {
		return merchandiserName;
	}

	public void setMerchandiserName(String merchandiserName) {
		this.merchandiserName = merchandiserName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Date getDocumentaryTime() {
		return documentaryTime;
	}

	public void setDocumentaryTime(Date documentaryTime) {
		this.documentaryTime = documentaryTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}
}
