package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/16/016 16:07
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProduceOrderState {
	NOT_YET_BEGUN(0,"未开始"),
	TO_BE_QUOTED(4,"外协厂家待报价"),
	TO_BE_PENDING_PRICE(5,"工厂待审价"),
	TO_BE_PAY(6,"财务待付款"),
	TO_BE_START_PRODUCE(7,"外协单待生产"),
	TO_BE_BACK_PRODUCE(8,"报价驳回"),
	IN_PRODUCTION(1,"生产中"),
	INPUT_STORAGE(2,"已入库"),
	COMPLETE(3,"已完成"),
	;


	private int value;
	private String name;

	ProduceOrderState(int value, String name) {
		this.value = value;
		this.name = name;
	}

	public int getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
	public static Boolean contains(int value){
		return null==getByValue(value)?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public static ProduceOrderState getByValue(int value){
		ProduceOrderState allVaues[] = ProduceOrderState.values();
		ProduceOrderState status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}

}
