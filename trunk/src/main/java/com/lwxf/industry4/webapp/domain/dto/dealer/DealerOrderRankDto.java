package com.lwxf.industry4.webapp.domain.dto.dealer;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/5/22 0022 15:34
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ApiModel(value = "经销商下单排行",description = "经销商下单排行")
public class DealerOrderRankDto {
	@ApiModelProperty(name = "company_id",value = "经销商id,查看详情时使用")
	private String company_id;
	@ApiModelProperty(name = "price",value = "下单金额")
	private String price;
	@ApiModelProperty(name = "companyName",value = "经销商名称")
	private String companyName;
	@ApiModelProperty(name = "orderNum",value = "下单数量")
	private Integer orderNum;

	public String getCompany_id() {
		return company_id;
	}

	public void setCompany_id(String company_id) {
		this.company_id = company_id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public Integer getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(Integer orderNum) {
		this.orderNum = orderNum;
	}
}
