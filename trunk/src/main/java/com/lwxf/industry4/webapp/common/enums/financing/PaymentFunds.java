package com.lwxf.industry4.webapp.common.enums.financing;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/5/005 11:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum PaymentFunds {
	FREE_FUNDS(11,"预付款"),
	BOND(12,"保证金"),
	DESSIGN_FEE(13,"设计金"),
	SOFTWARE(14,"软件年金"),
	ACTIVITY(15,"活动款"),
	DESIGN_FEE_PAY(21,"设计金支付"), //工厂账户支付到设计人
	FREE_FUNDS_REFUND(22,"经销商账户退款"), //工厂账户退款到经销商自由账户
	DESIGN_FEE_REFUND(23,"经销商设计费退款"), //工厂账户退款到经销商自由账户
	BOND_REFUND(24,"保证金退款"), //经销商保证金账户退款
	ORDER_FEE_CHARGE(31,"订单货款扣款"), //从经销商自由账户扣款到工厂账户
	DESIGN_FEE_CHARGE(32,"设计费扣款"), //从经销商自由账户扣款到工厂账户
	PAYMENT_WITHHOLDING_CHARGE(33,"订单货款代扣款"),//经销商订单货款代扣款
	EARNEST_MONEY(0,"定金"),
	//ORIENTED_ADVANCE_PAYMENT(6,"定向预付款"),
	//TIMED_ADVANCE_PAYMENT(7,"定时预付款"),

	FRANCHISE_FEE(41,"加盟费"),
	//DESIGN_BOND(10,"设计保证金"),
	//ACTIVE_GOLD(11,"活动诚意金"),
	COOPERATIVE_GOLD(42,"合作诚意金"),

	COORDINATION(44,"外协"),
	OTHER(45,"其他");



	Integer value;
	String name;
	PaymentFunds(Integer value, String name){
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

	public static PaymentFunds getByValue(Integer value){
		PaymentFunds allVaues[] = PaymentFunds.values();
		PaymentFunds status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
	}
