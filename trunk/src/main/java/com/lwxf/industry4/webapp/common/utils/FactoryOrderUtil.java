package com.lwxf.industry4.webapp.common.utils;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/4/3 9:52
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class FactoryOrderUtil {

    public static List<Map> getOrderList(List<Map> rows){
        if (rows!=null&&rows.size()>0){
            for (Map map:rows){
                String customName = (String) map.get("customName");
                String demandName = (String) map.get("demandName");
                Integer oStatus = (Integer) map.get("status");
                Date created = (Date) map.get("created");
                String statusName = OrderStatus.getByValue(oStatus).getName();
                String name = "";
                if (LwxfStringUtils.isNotBlank(customName)){
                    name = customName;
                    if (LwxfStringUtils.isNotBlank(demandName)){
                        name = customName+"-"+demandName;
                    }
                }
                if (created!=null){
                    String createdToString = DateUtil.dateTimeToString(created, DateUtil.FORMAT_DATE);
                    map.put("created",createdToString);
                }
                map.put("name",name);
                map.put("status",statusName);
                map.remove("customName");
                map.remove("demandName");
            }
            return rows;
        }

        return new ArrayList<>();
    }


}
