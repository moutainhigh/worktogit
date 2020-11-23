package com.lwxf.industry4.webapp.domain.dto.financing;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.financing.Funds;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/9/25 0025 14:51
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class FundsDto extends Funds {
	private List<FundsDto> funds;

	public List<FundsDto> getFunds() {
		return funds;
	}

	public void setFunds(List<FundsDto> funds) {
		this.funds = funds;
	}
}
