package com.lwxf.industry4.webapp.common.SMSConfig;

import java.util.HashMap;
import java.util.Map;

import static com.yunpian.sdk.util.HttpUtil.post;

/**
 * @author lyh on 2019/11/25
 */
public class YunPianSendsms {
    /**
     * @param apikey 成功注册后登录云片官网,进入后台可查看
     * @param text   需要使用已审核通过的模板或者默认模板
     * @param mobile 接收的手机号,仅支持单号码发送
     * @return
     * @throws Exception
     */
    public static String singleSend(String apikey, String text, String mobile) throws Exception {
        Map<String, String> params = new HashMap<String, String>();
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post("https://sms.yunpian.com/v2/sms/single_send.json", params);
    }

    /**
     * 批量发送短信,相同内容多个号码,智能匹配短信模板
     *
     * @param apikey 成功注册后登录云片官网,进入后台可查看
     * @param text   需要使用已审核通过的模板或者默认模板
     * @param mobile 接收的手机号,多个手机号用英文逗号隔开
     * @return json格式字符串
     */
    public static String batchSend(String apikey, String text, String mobile)throws Exception {
        Map<String, String> params = new HashMap<String, String>();//请求参数集合
        params.put("apikey", apikey);
        params.put("text", text);
        params.put("mobile", mobile);
        return post("https://sms.yunpian.com/v2/sms/batch_send.json",  params);//请自行使用post方式请求,可使用Apache HttpClient
    }
}
