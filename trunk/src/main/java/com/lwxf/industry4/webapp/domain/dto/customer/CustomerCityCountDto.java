package com.lwxf.industry4.webapp.domain.dto.customer;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/3/19 0019 9:50
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CustomerCityCountDto {
	private Integer count;
	private String mergerName;

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getMergerName() {
		return mergerName;
	}

	public void setMergerName(String mergerName) {
		int a=mergerName.indexOf(",");
		String merger=mergerName.substring(a+1);
		this.mergerName = merger;
	}
}
