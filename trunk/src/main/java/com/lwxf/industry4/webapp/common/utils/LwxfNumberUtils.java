package com.lwxf.industry4.webapp.common.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * @author lyh on 2019/12/13
 */
public class LwxfNumberUtils {


    public static String fomatNumberTwoDigits(Double a) {
        if(a.doubleValue() == 0 || Double.isInfinite(a.doubleValue())||Double.isNaN(a.doubleValue())){
            return "0";
        }
        Double aDouble = Double.valueOf(String.format("%.2f", a));
        String data = aDouble.toString();
        String[] split = data.split("\\.");
        if (split.length != 2) {
            return a + "";
        }
        if(split[1].length()!=2 && split[1].equals("0")){
            split[1]="00";
        }
        //添加金额逗号类似111,111,111
        String s = inThousandSeparator(split[0]);
        return s + "." + split[1];
    }



    //格式化金额
    private static String inThousandSeparator(String str) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Double.valueOf(str));
    }


    private String intChange2Str(int number) {
        String str = "";
        if (number <= 0) {
            str = "";
        } else if (number < 10000) {
            str = number + "阅读";
        } else {
            double d = (double) number;
            double num = d / 10000;//1.将数字转换成以万为单位的数字

            BigDecimal b = new BigDecimal(num);
            double f1 = b.setScale(1,BigDecimal.ROUND_HALF_UP).doubleValue();//2.转换后的数字四舍五入保留小数点后一位;
            str = f1 + "万阅读";
        }
        System.out.println(str);
        return str;
    }
    public static void main(String[] args){
        String s = fomatNumberTwoDigits(Double.valueOf(0.4333333333));
        System.out.println(s);
    }

}
