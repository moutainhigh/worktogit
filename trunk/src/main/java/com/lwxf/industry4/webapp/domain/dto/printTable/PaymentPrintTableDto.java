package com.lwxf.industry4.webapp.domain.dto.printTable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.financing.Payment;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/11/011 18:54
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "支出单信息",discriminator = "支出单信息")
public class PaymentPrintTableDto extends Payment {
	@ApiModelProperty(value = "支付类型转义")
	private String typeName;
	@ApiModelProperty(value = "录表人")
	private	String creatorName;
	@ApiModelProperty(value = "审核人")
	private String auditorName;
	@ApiModelProperty(value = "订单编号")
	private String orderNo;


	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getAuditorName() {
		return auditorName;
	}

	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
}
