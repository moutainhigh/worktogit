package com.lwxf.industry4.webapp.domain.dto.customorder;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceFlow;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/24/024 17:06
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "生产流程信息",discriminator = "生产流程信息")
public class ProduceFlowDto extends ProduceFlow {
	@ApiModelProperty(value = "操作人名称")
	private String operatorName;

	public String getOperatorName() {
		return operatorName;
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}
}
