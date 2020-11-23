package com.lwxf.industry4.webapp.common.enums.order;


import com.lwxf.mybatis.utils.MapContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2018/7/5 14:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum OrderStatus {

//	NEW_ORDER(0,"新订单"),//待处理(新订单)
//	TO_ADD_DESIGNFEE(1,"设计费待评估"),//设计费待评估
//	TO_BE_CONFIRMED_DESIGNFEE(2,"设计费待确认"),//设计费待确认
//	TO_AUDIT_DESIGN(3,"设计费待审核"),//设计费待审核
//	TO_DESIGN(4,"待设计"),//待设计
//	DESIGNING(5,"设计中"),//设计中
//	TO_SUBMIT(6,"设计待确认"),//设计待确认(设计确认后的状态，将提交给工厂)
//	FACTORY_CONFIRMED_FPROCE(7,"出厂价待确认"),//出厂价待确认
//	DEALER_CONFIRMED_FPRICE(8,"经销商待确认出厂价"),//经销商待确认出厂价
//	TO_AUDIT(9,"货款支付审核"),//货款支付审核
//	TO_PRODUCTION(10,"待生产"),//待生产
//	IN_PRODUCTION(11,"生产中"),//生产中
//	TO_WAREHOUSE(12,"待入库"),//待入库
//	TO_OUT_STOCK(13,"待出库"),//待出库 暂不用
//	TO_DISPATCH(14,"待配送"),//待配送
//	DISPATCHING(15,"配送中"),//配送中
//	RECEIVED(16,"已完成"),//已完成
    TO_RECEIVE(9, "待接单"),
	TO_QUOTED(0,"待报价"),
	REMINDING(11, "催款中"),
	TO_PAID(1,"待支付"),
	TO_PRODUCED(2,"待生产"),
	PRODUCTION(3,"生产中"),
	TO_PACKAGED(4,"待包装"),
	TO_SHIPPED(5,"待发货"),
	SHIPPED(6,"已发货"),
	END(7,"已完成"),
	DELETE(8,"已废除"),
	;



	private Integer value;
	private String name;

	OrderStatus(Integer value, String name){
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

	public static List<Map> getAll() {
		List<Map> mapList = new ArrayList<>();
		for (int i=0,len = OrderStatus.values().length;i<len;i++){
			MapContext mapContext = new MapContext();
			mapContext.put("status",OrderStatus.values()[i].getValue().toString());
			mapContext.put("name",OrderStatus.values()[i].getName());
			mapList.add(mapContext);
		}
		return mapList;
	}

	public static List<Map> getStatus(){
		List<Map> mapList = new ArrayList<>();
		for (int i = 0;i<5;i++) {
			MapContext mapContext = new MapContext();
			if (i==0){
				mapContext.put("status", "0");
				mapContext.put("name", "待处理");
			}if (i==1){
				mapContext.put("status", "1");
				mapContext.put("name", "设计中");
			}if (i==2){
				mapContext.put("status", "2");
				mapContext.put("name", "生产中");
			}if (i==3){
				mapContext.put("status", "3");
				mapContext.put("name", "配送中");
			}if (i==4){
				mapContext.put("status", "4");
				mapContext.put("name", "已完成");
			}
			mapList.add(mapContext);
		}
		return mapList;
	}



	public Integer getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static OrderStatus getByValue(Integer value){
		OrderStatus allVaues[] = OrderStatus.values();
		OrderStatus status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}
}
