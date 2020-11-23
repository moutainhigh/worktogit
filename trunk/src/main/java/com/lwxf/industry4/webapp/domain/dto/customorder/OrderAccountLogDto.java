package com.lwxf.industry4.webapp.domain.dto.customorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.customorder.OrderAccountLog;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/11/011 10:26
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "订单报价修改记录",discriminator = "订单报价修改记录")
public class OrderAccountLogDto extends OrderAccountLog {
	@ApiModelProperty(value = "创建时间")
	private String creatorName;

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
}
