package com.lwxf.industry4.webapp.common.enums.order;

import com.lwxf.mybatis.utils.MapContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public enum OrderProductStatus {

    TO_PAYMENT(0,"待付款"),
    TO_PRODUCE(1,"待生产"),
    PRODUCING(2,"生产中"),
    HAS_STOCK(3,"已入库"),
    TO_DELIVERY(4,"待发货"),
    HAS_DELIVERY(5,"已发货"),
    DELETE(6,"已废除"),
    /**
     * 生产完成状态，生产中后置状态，已入库前置状态
     */
    PRODUCE_COMPLETE(7,"生产完成"),
    ;

    private Integer value;
    private String name;


    OrderProductStatus(Integer value, String name) {
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

    public static OrderProductStatus getByValue(Integer value){
        OrderProductStatus allVaues[] = OrderProductStatus.values();
        OrderProductStatus status;
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
