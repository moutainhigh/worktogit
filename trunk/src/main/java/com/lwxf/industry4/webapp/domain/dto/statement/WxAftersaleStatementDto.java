package com.lwxf.industry4.webapp.domain.dto.statement;

import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/6/30 0030 15:17
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class WxAftersaleStatementDto {
	@ApiModelProperty(value = "反馈")
	private Integer feedback;
	@ApiModelProperty(value = "补料")
	private Integer buLiao;
	@ApiModelProperty(value = "收费")
	private Integer charge;
	@ApiModelProperty(value = "免费")
	private Integer free;
	@ApiModelProperty(value = "拒绝")
	private Integer refuse;
	@ApiModelProperty(value = "生产中")
	private Integer produce;
	@ApiModelProperty(value = "待支付")
	private Integer waitPay;
	@ApiModelProperty(value = "待发货")
	private Integer waitDispatchBill;
	@ApiModelProperty(value = "已发货")
	private Integer endDispatchBill;



}
