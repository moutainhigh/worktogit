package com.lwxf.industry4.webapp.domain.dto.dispatch;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/4/22 0022 13:59
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DispatchBillPlanItemDto {
	private String dispatchBillPlanId;//发货计划单Id
	private String orderNo;//订单编号
	private String dispatchBillId;//发货单id
	private String orderId;//订单id
	private String finishedStockItemId;//入库明细单id
	private String created;//创建时间
	private String companyName;//经销商公司名称
	private String finishedStockId;//入库单id
	private String dispatchBillItemId;//发货明细单id
	private String barcode;//发货物流编号
	private String customerName;//客户姓名
	private Integer status;//状态0 未发货 1 已发货

	public String getDispatchBillPlanId() {
		return dispatchBillPlanId;
	}

	public void setDispatchBillPlanId(String dispatchBillPlanId) {
		this.dispatchBillPlanId = dispatchBillPlanId;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getDispatchBillId() {
		return dispatchBillId;
	}

	public void setDispatchBillId(String dispatchBillId) {
		this.dispatchBillId = dispatchBillId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getFinishedStockItemId() {
		return finishedStockItemId;
	}

	public void setFinishedStockItemId(String finishedStockItemId) {
		this.finishedStockItemId = finishedStockItemId;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getFinishedStockId() {
		return finishedStockId;
	}

	public void setFinishedStockId(String finishedStockId) {
		this.finishedStockId = finishedStockId;
	}

	public String getDispatchBillItemId() {
		return dispatchBillItemId;
	}

	public void setDispatchBillItemId(String dispatchBillItemId) {
		this.dispatchBillItemId = dispatchBillItemId;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
