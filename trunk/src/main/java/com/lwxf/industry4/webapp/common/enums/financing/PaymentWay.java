package com.lwxf.industry4.webapp.common.enums.financing;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/7/007 15:38
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PaymentWay {

	ALIPAY(0,"支付宝"),
	WEIXIN(1,"微信"),
	CASH(2,"现金"),
	BANK(3,"银行转账"),
	DEALER_ACCOUNT(4,"经销商账户"),
	VISA(5,"刷卡"),
	QITA(6,"其他");

	private Integer value;
	private String name;

	PaymentWay(Integer value, String name){
		this.value = value;
		this.name = name;
	}

	/**
	 * 数据范围校验
	 *
	 * @param value
	 * @return
	 */
	public static boolean contains(Integer value) {
		return null == getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}

	public Integer getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static PaymentWay getByValue(Integer value){
		PaymentWay allVaues[] = PaymentWay.values();
		PaymentWay status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
