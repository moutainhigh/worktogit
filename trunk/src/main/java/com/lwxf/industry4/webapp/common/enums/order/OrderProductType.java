package com.lwxf.industry4.webapp.common.enums.order;

import com.lwxf.mybatis.utils.MapContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/24/024 13:42
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum OrderProductType {
	CUPBOARD(0,"橱柜(J)"),
	WARDROBE(1,"衣柜(B)"),
	FINISHED_FURNITURE(2,"成品家具(J)"),
	ELECTRICAL_EQUIPMENT(3,"电器(J)"),
	HARDWARE(4,"五金(J)"),
	SAMPLE_PIECE(5,"样块(Y)");

	private Integer value;
	private String name;


	OrderProductType(Integer value, String name) {
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
		return null == value?Boolean.FALSE.booleanValue():Boolean.TRUE.booleanValue();
	}
	public Integer getValue() {
		return this.value;
	}

	public String getName(){
		return this.name;
	}

	public static OrderProductType getByValue(Integer value){
		OrderProductType allVaues[] = OrderProductType.values();
		OrderProductType status;
		for (int i=0,len = allVaues.length;i<len;i++){
			status = allVaues[i];
			if(status.getValue()==value){
				return status;
			}
		}
		return null;
	}

	public static List<Map> getAll() {
		List<Map> mapList = new ArrayList<>();
		for (int i=0,len = OrderProductType.values().length;i<len;i++){
			MapContext mapContext = new MapContext();
			mapContext.put("status",OrderProductType.values()[i].getValue().toString());
			mapContext.put("name",OrderProductType.values()[i].getName());
			mapList.add(mapContext);
		}
		return mapList;
	}

}
