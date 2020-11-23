package com.lwxf.industry4.webapp.common.enums.company;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/12/012 11:23
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProduceOrderPay {

	PAY(true,"已付款"),
	NOT_PAY(false,"未付款");

	private Boolean value;
	private String name;

	ProduceOrderPay(Boolean value, String name){
		this.value = value;
		this.name = name;
	}



	public Boolean getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

}
