package com.lwxf.industry4.webapp.common.utils;

import net.sourceforge.pinyin4j.PinyinHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lyh on 2019/11/13
 */
public class PinYinUtils {

    /**
     * 获取汉字首字母的方法。如： 张三 --> ZS
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     *
     * @param hanzi 汉子字符串
     * @return 大写汉子首字母; 如果都转换失败,那么返回null
     */
    public static String getHanziInitials(String hanzi) {
        String result = null;
        if (null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);

                if (null != stringArray) {
                    sb.append(stringArray[0].charAt(0));
                }
            }
            if (sb.length() > 0) {
                result = sb.toString().toUpperCase();
            }
        }
        return result;
    }

    /**
     * 获取汉字拼音的方法。如： 张三 --> zhangsan
     * 说明：暂时解决不了多音字的问题，只能使用取多音字的第一个音的方案
     *
     * @param hanzi 汉子字符串
     * @return 汉字拼音; 如果都转换失败,那么返回null
     */
    public static String getHanziPinYin(String hanzi) {
        String result = null;
        if (null != hanzi && !"".equals(hanzi)) {
            char[] charArray = hanzi.toCharArray();
            StringBuffer sb = new StringBuffer();
            for (char ch : charArray) {
                // 逐个汉字进行转换， 每个汉字返回值为一个String数组（因为有多音字）
                String[] stringArray = PinyinHelper.toHanyuPinyinStringArray(ch);
                if (null != stringArray) {
                    // 把第几声这个数字给去掉
                    sb.append(stringArray[0].replaceAll("\\d", ""));
                }
            }
            if (sb.length() > 0) {
                result = sb.toString();
            }
        }
        return result;
    }

    /**
     * 判断是不是汉字
     *
     * @param countname
     * @return
     */
    public static boolean checkcountname(String countname) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(countname);
        return m.find();
    }

    /**
     * 判断是否是字母
     *
     * @param str 传入字符串
     * @return 是字母返回true，否则返回false
     */
    public static boolean is_alpha(String str) {
        if (str == null) return false;
        return str.matches("[a-zA-Z]+");
    }

    public static String getfirstName(String name) {
        if (name != null && name.length() != 0) {
            String namefirst = name.charAt(0) + "";
            boolean isHanZi = checkcountname(namefirst);//是否为汉字
            String firstAlpha = "";
            if (isHanZi) {
                String firstAlphaTmp = PinYinUtils.getHanziInitials(namefirst);
                firstAlpha = firstAlphaTmp.toUpperCase();//均转为大写
                return firstAlpha;
            }
            boolean isAlpha = is_alpha(namefirst);//是否为英文字母
            if (isAlpha) {
                firstAlpha = namefirst.toUpperCase();//均转为大写
            } else {
                //其他
                firstAlpha = "ABC";
            }
            return firstAlpha;
        } else {
            return "ABC";//名字为空也放#里边
        }
    }

    public static void main(String[] args) {
        System.out.println(PinYinUtils.getHanziInitials("袁素芳"));
        System.out.println(PinYinUtils.getHanziPinYin("袁素芳"));
    }
}
