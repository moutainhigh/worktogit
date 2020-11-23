package com.lwxf.industry4.webapp.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/5/17 15:04
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class NumberUtilsExt {

    public static String getPercent(Integer oneNumber,Integer twoNumber,String decimalDigits){
        if (twoNumber==null||twoNumber==0){
            return "";
        }
        if (oneNumber==null){
            return "";
        }
        double price = (double)oneNumber / (double)twoNumber;
        NumberFormat nt = NumberFormat.getPercentInstance();
        DecimalFormat df=new DecimalFormat(decimalDigits);
        String value = df.format(price);
        nt.setMaximumFractionDigits(2);
        String percent = nt.format(new BigDecimal(value));
        return percent;
    }
    /**
        数字四舍五入保留两位小数
     */
    public static String numberFormat(double num) {
        DecimalFormat df = new DecimalFormat(".00");
       return df.format(num);
    }

    /**
     * 计算百分比
     * @param d
     * @param e
     * @return
     */
    public static String percent(Integer d, Integer e){
        if (e == null || e.equals(new Integer("0"))) {
            e = 1;
        }
        double p = (double) d / (double) e;
        DecimalFormat nf = (DecimalFormat) NumberFormat.getPercentInstance();
        nf.applyPattern("00%"); //00表示小数点2位
//        nf.setMaximumFractionDigits(2); //2表示精确到小数点后2位
        return nf.format(p);
    }

}
