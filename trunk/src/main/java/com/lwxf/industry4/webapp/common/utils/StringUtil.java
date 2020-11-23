package com.lwxf.industry4.webapp.common.utils;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串操作工具类
 *
 * @author lyh on 2019/9/24
 */
public class StringUtil {

    private static final Pattern pattern = Pattern.compile("(\\{(\\d+)\\})");


    private static int defaultLen = 4;
    /**
     * 纯数字种子
     */
    private static char[] randomNumberCodeSet = {
            '0','1', '2', '3', '4', '5', '6', '7', '8', '9'
    };
    /**
     * 纯数字种子
     */
    private static char[] randomNumberCodeSetLetter = {
            'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1', '2', '3', '4', '5', '6', '7', '8', '9'
    };

    private static Random random = new Random();

    public static boolean isEmpty(CharSequence value) {
        return value == null || value.length() == 0;
    }

    public static String format(String string, Object... args) {
        if (string == null) {
            return null;
        } else {
            Matcher m = pattern.matcher(string);
            if (!m.find()) {
                return string;
            } else {
                StringBuffer sb = new StringBuffer();

                do {
                    Integer g2 = Integer.parseInt(m.group(2));
                    if (g2 >= 0 && g2 < args.length) {
                        m.appendReplacement(sb, Matcher.quoteReplacement(args[g2].toString()));
                    }
                } while(m.find());

                m.appendTail(sb);
                return sb.toString();
            }
        }
    }

    /**
     * 纯数字验证码支持
     * @param len
     * @return
     */
    public static String getRandomNumberCode(int len){
        if(len<=0){
            len = defaultLen;
        }
        int charslen = randomNumberCodeSet.length;
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i<len; i++){
            sb.append(randomNumberCodeSet[random.nextInt(charslen)]);
        }
        return sb.toString();
    }
    /**
     * 把表名转化位实体类名字
     * sys_dict_item ==》SysDictItem
     *
     * @param tableName
     * @return
     */
    public static String convertTableName2EntityName(String tableName) {
        if (isEmpty(tableName)) {
            return "";
        }
        String[] s = tableName.split("_");
        StringBuilder sb = new StringBuilder();
        for (String stmp : s) {
            if (isNotEmpty(stmp)) {
                String s1 = stmp.substring(0, 1).toUpperCase() + stmp.substring(1);
                sb.append(s1);
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否位空
     * 空/""  true
     *
     * @param s
     * @return
     */
    public static boolean isEmpty(String s) {
        if (s == null) {
            return true;
        } else {
            return s.length() == 0 ? true : false;
        }
    }

    /**
     * 判断字符串是否位空
     * 空/""  false
     *
     * @param s
     * @return
     */
    public static boolean isNotEmpty(String s) {
        return !isEmpty(s);
    }

    /**
     * 生成由[A-Z,0-9]生成的随机字符串
     *
     * @param length 欲生成的字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        Random random = new Random();

        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < length; ++i) {
            int number = random.nextInt(2);
            long result = 0;

            switch (number) {
                case 0:
                    result = Math.round(Math.random() * 25 + 65);
                    sb.append(String.valueOf((char) result));
                    break;
                case 1:

                    sb.append(String.valueOf(new Random().nextInt(10)));
                    break;
            }
        }
        return sb.toString();
    }


    /**
     *
     * @param s 最小值
     * @param e 最大值
     * @param flexLen 固定长度
     * @return
     */
    public static String getRandStr(int s, int e,int flexLen) {
        int n = s + (int) (Math.random() * (e - s));
        String s1 = String.valueOf(n);
        if(s1.length() <flexLen){
            for (int i=0;i<=flexLen-s1.length();i++){
                s1 = "0"+s1;
            }
        }
        return s1;
    }







}
