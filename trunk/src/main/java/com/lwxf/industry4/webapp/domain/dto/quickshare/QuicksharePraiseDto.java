package com.lwxf.industry4.webapp.domain.dto.quickshare;

import com.lwxf.industry4.webapp.domain.entity.quickshare.QuicksharePraise;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/8 0008 8:45
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class QuicksharePraiseDto extends QuicksharePraise {
	private String praiseAvatar;

	public String getPraiseAvatar() {
		return praiseAvatar;
	}

	public void setPraiseAvatar(String praiseAvatar) {
		this.praiseAvatar = praiseAvatar;
	}
}
