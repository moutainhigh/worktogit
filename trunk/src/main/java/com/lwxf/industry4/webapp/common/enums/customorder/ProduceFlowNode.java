package com.lwxf.industry4.webapp.common.enums.customorder;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/16/016 17:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum ProduceFlowNode {
	SPARE_PARTS(0,"备料"),
	BLANKING(1,"下料"),
	EDGE_SEALING(2,"封边"),
	PUNCH_HOLES(3,"打孔"),
	CLEAN(4,"清洗"),
	TRIAL_LOADING(5,"试装"),
	PACKING(6,"包装");


	private int value;
	private String name;

	ProduceFlowNode(int value, String name) {
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
	public static ProduceFlowNode getByValue(int value){
		ProduceFlowNode allVaues[] = ProduceFlowNode.values();
		ProduceFlowNode status;
		for (int i = 0,len = allVaues.length; i < len; i++) {
			status = allVaues[i];
			if (status.getValue()==value){
				return status;
			}
		}
		return null;
	}

}
