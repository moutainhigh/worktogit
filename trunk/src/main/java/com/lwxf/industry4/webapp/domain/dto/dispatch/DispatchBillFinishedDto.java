package com.lwxf.industry4.webapp.domain.dto.dispatch;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/4/15 0015 9:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DispatchBillFinishedDto {
	private String orderId;//订单Id
	private Integer type;//产品品名
	private Integer finishedNum;//数量
	private Integer finishedStatus;//入库状态
	private String  finishedMan;//入库人
	private Integer dispatchStatus;//发货状态

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getFinishedNum() {
		return finishedNum;
	}

	public void setFinishedNum(Integer finishedNum) {
		this.finishedNum = finishedNum;
	}

	public Integer getFinishedStatus() {
		return finishedStatus;
	}

	public void setFinishedStatus(Integer finishedStatus) {
		this.finishedStatus = finishedStatus;
	}

	public String getFinishedMan() {
		return finishedMan;
	}

	public void setFinishedMan(String finishedMan) {
		this.finishedMan = finishedMan;
	}

	public Integer getDispatchStatus() {
		return dispatchStatus;
	}

	public void setDispatchStatus(Integer dispatchStatus) {
		this.dispatchStatus = dispatchStatus;
	}
}
