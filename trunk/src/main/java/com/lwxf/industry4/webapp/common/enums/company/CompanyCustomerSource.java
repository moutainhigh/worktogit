package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2018/12/17 0017 15:32
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum CompanyCustomerSource {
    CSOCKET(0,"C端注册"),
	MOBILE(1,"电话访问"),
	VISIT(2,"上门访问"),
	PUBLICADDRESS(3,"公众号"),
	NETWORK(4,"网络"),
	ONLINE(5,"电商"),
	DEALERADD(6,"经销商添加"),
	FACTORYADD(7,"工厂录入"),

	;
	private Integer value;
	private String name;

	CompanyCustomerSource(Integer value, String name) {
		this.value = value;
		this.name = name;
	}
	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	public static Boolean contains(int value) {
		return null == getByValue(value) ? Boolean.FALSE.booleanValue() : Boolean.TRUE.booleanValue();
	}

	public static CompanyCustomerSource getByValue(int value) {
		CompanyCustomerSource allVaues[] = CompanyCustomerSource.values();
		CompanyCustomerSource source;
		for (int i = 0, len = allVaues.length; i < len; i++) {
			source = allVaues[i];
			if (source.getValue() == value) {
				return source;
			}
		}
		return null;
	}
}
