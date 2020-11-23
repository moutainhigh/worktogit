package com.lwxf.industry4.webapp.common.enums.order;

import com.lwxf.mybatis.utils.MapContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/4/29 14:07
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public enum OrderProductSeries {
    CUSTOMIZATION_SOLID_WOOD(0,"定制实木"),
    SPECIAL_SUPPLY_SOLID_WOOD(1,"特供实木"),
    MEIKE(2,"美克"),
    KANGNAI(3,"康奈"),
    HUINA(4,"慧娜"),
    MOYA(5,"模压");

    private Integer value;
    private String name;


    OrderProductSeries(Integer value, String name) {
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

    public static OrderProductSeries getByValue(Integer value){
        OrderProductSeries allVaues[] = OrderProductSeries.values();
        OrderProductSeries status;
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
        for (int i=0,len = OrderProductSeries.values().length;i<len;i++){
            MapContext mapContext = new MapContext();
            mapContext.put("status",OrderProductSeries.values()[i].getValue().toString());
            mapContext.put("name",OrderProductSeries.values()[i].getName());
            mapList.add(mapContext);
        }
        return mapList;
    }
}
