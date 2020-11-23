package com.lwxf.industry4.webapp.domain.dto.customorder;

import java.math.BigDecimal;

/**
 * 功能：订单统计数据输出字段
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/15 0015 10:40
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class OrderCountDto {
	Integer orderNum;
	BigDecimal orderMoney;

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}

	public BigDecimal getOrderMoney() {
		return orderMoney;
	}

	public void setOrderMoney(BigDecimal orderMoney) {
		this.orderMoney = orderMoney;
	}
}
