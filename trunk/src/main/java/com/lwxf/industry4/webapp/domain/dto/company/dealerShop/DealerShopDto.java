package com.lwxf.industry4.webapp.domain.dto.company.dealerShop;

import com.lwxf.industry4.webapp.domain.entity.company.DealerShop;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/25 0025 11:54
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class DealerShopDto extends DealerShop {
	private String leaderName;
	private String mergerName;

	public String getLeaderName() {
		return leaderName;
	}

	public void setLeaderName(String leaderName) {
		this.leaderName = leaderName;
	}

	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		this.mergerName = mergerName;
	}
}
