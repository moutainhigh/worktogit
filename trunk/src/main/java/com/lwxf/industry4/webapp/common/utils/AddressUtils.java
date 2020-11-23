package com.lwxf.industry4.webapp.common.utils;

import com.lwxf.commons.utils.LwxfStringUtils;

/**
 * 功能：地址
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/11 16:20
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class AddressUtils {

    /**
     * 根据城市名和详细地址名拼接地址
     * @param city
     * @param address1
     * @return
     */
    public static String getAddress(String city,String address1){
        String address = "";
        if (LwxfStringUtils.isNotBlank(address1)){
            address = address1;
        }
        if (LwxfStringUtils.isNotBlank(city)){
            int a = city.indexOf(",");
            String cityName = city.substring(a + 1);//截取后的名字
            String replaceName = cityName.replace(",", "");//去除逗号后的名字
            address = replaceName;
            if (LwxfStringUtils.isNotBlank(address1)){
                address = replaceName+address1;//地区地址+详细地址
            }
        }
        return address;
    }


    public static  String getCityName(String city){
        String address = "";
        if (LwxfStringUtils.isNotBlank(city)){
            int a = city.indexOf(",");
            String cityName = city.substring(a + 1);//截取后的名字
            String replaceName = cityName.replace(",", "");//去除逗号后的名字
            address = replaceName;
        }
        return address;
    }

}

