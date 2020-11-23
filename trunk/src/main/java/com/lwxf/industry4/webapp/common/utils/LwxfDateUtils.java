package com.lwxf.industry4.webapp.common.utils;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author lyh on 2019/12/10
 */
public class LwxfDateUtils {

    public static int getMonthInt(Date date) {
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return localDate.getMonthValue();
    }

    public static String getSdfDate(Date date, String dsdfStr) {
        SimpleDateFormat yyyymm = new SimpleDateFormat(dsdfStr);
        String format = yyyymm.format(date);
        return format;
    }

    /**
     * 上一月的字符串
     * @param date
     * @param dsdfStr
     * @return
     */
    public static String getSdfDateMonthBefore(Date date, String dsdfStr) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        return getSdfDate(date,dsdfStr);
    }
    /**
     * 上一月的字符串
     * @param date
     * @return
     */
    public static Date getSdfDateMonthBeforeDate(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        return date;
    }
    /**
     * 上forwardNum月的字符串
     * @param date
     * @param dsdfStr
     * @return
     */
    public static String getSdfDateMonthBefore(Date date, String dsdfStr,int forwardNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - forwardNum); // 设置为上一个月
        date = calendar.getTime();
        return getSdfDate(date,dsdfStr);
    }

    /**
     * 上 forwardNum 天的字符串
     * @param date
     * @param dsdfStr
     * @param forwardNum
     * @return
     */
    public static String getSdfDateDayBefore(Date date, String dsdfStr,int forwardNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -forwardNum);
        date = calendar.getTime();
        return getSdfDate(date,dsdfStr);
    }

    /**
     * 上一月的Date
     * @param date
     * @return
     */
    public static Date getDateMonthBefore(Date date,int forwardNum) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - forwardNum); // 设置为上一个月
        date = calendar.getTime();
        return date;
    }


    /**
     * 获取某个月的天数
     * @param date
     * @return
     */
    public static int getDaysOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 截至到当前日这个月过了多少天
     * @param date
     * @return
     */
    public static int getnowMonth(Date date){

        SimpleDateFormat dd = new SimpleDateFormat("dd");
        String format = dd.format(date);
        Integer integer = Integer.valueOf(format);
        return integer;
    }
    /**
     * 几年截至到当前月过了几个月
     * @param date
     * @return
     */
    public static int getnowYear(Date date){

        SimpleDateFormat dd = new SimpleDateFormat("MM");
        String format = dd.format(date);
        Integer integer = Integer.valueOf(format);
        return integer;
    }

    /**
     * 根据sdf获取时间
     * @return
     */
    public static Date getDateBySdf(String dateStr,String sdf){

        SimpleDateFormat dd = new SimpleDateFormat(sdf);
        Date parse  = null;
        try {
            parse = dd.parse(dateStr);
        }catch (Exception e){//报错则返回当前时间
            e.printStackTrace();
        }
        return parse;
    }

    public static void main(String[] args){
        int yyyyMMdd = getnowMonth(new Date());
        System.out.println(yyyyMMdd);
    }


    public static void getDaysOfMonth(String queryDate, String yyyyMMdd) {
    }
}
