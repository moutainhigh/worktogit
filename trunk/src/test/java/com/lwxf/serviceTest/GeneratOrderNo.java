package com.lwxf.serviceTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author lyh on 2019/12/21
 */
public class GeneratOrderNo {
    public static String generateOrderCode(String lastOrderCode,String codeHeader,String useMonthOrYear,String havePrudctStr,String prudctStr){
        StringBuilder resultsd = new StringBuilder();
        //J20190505-02
        resultsd.append(codeHeader);
        if("month".equals(useMonthOrYear)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMM");
            String dateStr = simpleDateFormat.format(new Date());
            resultsd.append(dateStr);
        }else if("year".equals(useMonthOrYear)){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy");
            String dateStr = simpleDateFormat.format(new Date());
            resultsd.append(dateStr);
        }
        String sequenceNumber = generateSequence(lastOrderCode,resultsd.toString(), useMonthOrYear);//生成序列号
        resultsd.append("-"+sequenceNumber);
        //是否需要产品简称
        if("1".equals(havePrudctStr)){//需要
            resultsd.append("("+prudctStr+")");
        }
        return resultsd.toString();
    }

    public static String generateSequence(String lastOrderCode,String results,String useMonthOrYear){
        //先查询缓存中的订单编号
        //查询最后一次的数据库保存的订单编号
        int contains = lastOrderCode.indexOf(results);
        String substring ="0";
        if(contains == 0){//包含并在索引为0 则累加
            //TODO 序号保存在数据库直接取序号
            substring = lastOrderCode.substring(lastOrderCode.lastIndexOf("-")+1, lastOrderCode.length());
        }//不包含则从0开始
        Integer sequence = Integer.valueOf(substring)+1;
        return sequence+"";
    }


    public static void main(String [] args){
        String lastOrderCode = "HT-J201912-2";
        String sequenceNumberType = "HT-J";
        String useMonthOrYear = "year";
        String havePrudctStr = "0";
        String prudctStr = "J";

        String s = generateOrderCode(lastOrderCode,sequenceNumberType, useMonthOrYear, havePrudctStr, prudctStr);
        System.out.println(s);

    }
}


















