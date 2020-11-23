package com.lwxf.industry4.webapp.common.utils.WeiXin;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: SunXianWei
 * @Date: 2020/07/11/8:45
 * @Description:
 */
public class WechatUtil {
// 微信支付请求

    public static String httpsRequest(String requestUrl, String requestMethod, String outputStr) {

        try {

            URL url = new URL(requestUrl);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);

            conn.setDoInput(true);

            conn.setUseCaches(false);

            // 设置请求方式（GET/POST）

            conn.setRequestMethod(requestMethod);

            conn.setRequestProperty("content-type", "application/x-www-form-urlencoded");

            // 当outputStr不为null时向输出流写数据

            if (null != outputStr) {

                OutputStream outputStream = conn.getOutputStream();

                // 注意编码格式

                outputStream.write(outputStr.getBytes("UTF-8"));

                outputStream.close();

            }
            // 从输入流读取返回内容

            InputStream inputStream = conn.getInputStream();

            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");

            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str;

            StringBuffer buffer = new StringBuffer();

            while ((str = bufferedReader.readLine()) != null) {

                buffer.append(str);

            }

            // 释放资源

            bufferedReader.close();

            inputStreamReader.close();

            inputStream.close();

            conn.disconnect();

            return buffer.toString();

        } catch (ConnectException ce) {

        } catch (Exception e) {

        }

        return null;

    }


    /**
     * map转xml格式字符串
     */
    public static String map2XmlString(SortedMap<String, Object> params) {

        String xmlResult;


        StringBuffer sb = new StringBuffer();

        sb.append("<xml>");

        for (String key : params.keySet()) {

            String value = "<![CDATA[" + params.get(key) + "]]>";

            sb.append("<" + key + ">" + value + "</" + key + ">");

        }

        sb.append("</xml>");

        xmlResult = sb.toString();

        return xmlResult;

    }


    /**
     * 将xml字符串转换成map
     */

    public static HashMap<String, Object> readXmlString2Map(String xml) {

        HashMap<String, Object> map = new HashMap<String, Object>();

        Document doc;

        try {

            doc = DocumentHelper.parseText(xml); // 将字符串转为XML

            Element rootElt = doc.getRootElement(); // 获取根节点

            List<Element> list = rootElt.elements();// 获取根节点下所有节点

            for (Element element : list) { // 遍历节点

                map.put(element.getName(), element.getText()); // 节点的name为map的key，text为map的value

            }

        } catch (DocumentException e) {

            e.printStackTrace();

        } catch (Exception e) {

            e.printStackTrace();

        }

        return map;

    }

    public static String createSign(SortedMap<String, Object> params, String mchKey) {

        StringBuffer sb = new StringBuffer();

        Set es = params.entrySet();//字典序

        Iterator it = es.iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();

            String k = (String) entry.getKey();

            String v = (String) entry.getValue();

//为空不参与签名、参数名区分大小写

            if (null != v && !"".equals(v) && !"sign".equals(k)

                    && !"key".equals(k)) {

                sb.append(k + "=" + v + "&");

            }

        }

        //第二步拼接key，key设置路径：微信商户平台(pay.weixin.qq.com)-->账户设置-->API安全-->密钥设置

        sb.append("key=" + mchKey);


        String sign = MD5Utils.MD5Encode(sb.toString(), "utf-8")

                .toUpperCase();//MD5加密


        return sign;

    }


    public static boolean isTenpaySign(String characterEncoding, SortedMap<Object, Object> packageParams, String API_KEY) {

        StringBuffer sb = new StringBuffer();

        Set es = packageParams.entrySet();

        Iterator it = es.iterator();

        while (it.hasNext()) {

            Map.Entry entry = (Map.Entry) it.next();

            String k = (String) entry.getKey();

            String v = (String) entry.getValue();

            if (!"sign".equals(k) && null != v && !"".equals(v)) {

                sb.append(k + "=" + v + "&");

            }

        }


        sb.append("key=" + API_KEY);


        //算出摘要

        String mysign = MD5Utils.MD5Encode(sb.toString(), characterEncoding).toLowerCase();

        String tenpaySign = ((String) packageParams.get("sign")).toLowerCase();


        //System.out.println(tenpaySign +" " + mysign);

        return tenpaySign.equals(mysign);

    }
    public static Integer buildRandom(int length) {
        int num = 1;
        double random = Math.random();
        if (random < 0.1) {
            random = random + 0.1;
        }
        for (int i = 0; i < length; i++) {
            num = num * 10;
        }
        return (int) ((random * num));
    }
}
