package com.lwxf.industry4.webapp.common.SMSConfig;


//如果JDK版本低于1.8,请使用三方库提供Base64类
//import org.apache.commons.codec.binary.Base64;

import com.alibaba.fastjson.JSONException;
import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import com.github.qcloudsms.httpclient.HTTPException;

import java.io.IOException;

//如果JDK版本是1.8,可使用原生Base64类

/**
 * @author lyh on 2019/11/25
 */

public class TencentYunSendsms {

    private int tx_appid = 0;
    private String tx_appkey = "";
    private int tx_templateId = 0;
    private String tx_smsSign = "";


    public String sendSMS(String phoneNumber, String code) {
        String reStr = ""; //定义返回值
//        // 短信应用SDK AppID  // 1400开头
//        int appid = 0;
//        // 短信应用SDK AppKey
//        String appkey = "";
//        // 短信模板ID，需要在短信应用中申请
//        int templateId = 0;
//        // 签名，使用的是`签名内容`，而不是`签名ID`
//        String smsSign = "";
        try {
            //参数，一定要对应短信模板中的参数顺序和个数，
            String[] params = {code};
            //创建ssender对象
            SmsSingleSender ssender = new SmsSingleSender(tx_appid, tx_appkey);
            //发送
            SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumber, tx_templateId, params, tx_smsSign, "", "");
            // 签名参数未提供或者为空时，会使用默认签名发送短信
            System.out.println(result.toString());
            if (result.result == 0) {
                reStr = "success";
            } else {
                reStr = "error";
            }
        } catch (HTTPException e) {
            // HTTP响应码错误
            e.printStackTrace();
        } catch (JSONException e) {
            // json解析错误
            e.printStackTrace();
        } catch (IOException e) {
            // 网络IO错误
            e.printStackTrace();
        } catch (Exception e) {
            // 网络IO错误
            e.printStackTrace();
        }
        return reStr;
    }

    /**
     * @param phone
     * @param code
     * @return
     */
    public String send(String phone, String code) {
        String result = sendSMS("17620937694", "123456");
        if (result.equals("success")) {
            return "发送成功！";
        } else {
            return "发送失败！";
        }
    }
}