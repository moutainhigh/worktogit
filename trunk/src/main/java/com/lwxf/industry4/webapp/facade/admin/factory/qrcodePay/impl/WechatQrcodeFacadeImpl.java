package com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.WeiXin.HttpRequest;
import com.lwxf.industry4.webapp.common.utils.WeiXin.WechatUtil;
import com.lwxf.industry4.webapp.config.WeixinPayConfig;
import com.lwxf.industry4.webapp.facade.admin.factory.qrcodePay.WechatQrcodeFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/15 16:05
 * @Description:
 */
@Component("wechatQrcodeFacade")
public class WechatQrcodeFacadeImpl extends BaseFacadeImpl implements WechatQrcodeFacade {
    @Override
    public String wechatQrcodeUrl(String orderNo, String totalAmount, HttpServletRequest requests, HttpServletResponse response) {
        String out_trade_no = orderNo + System.currentTimeMillis(); //订单号
        String appid = WeixinPayConfig.appid;  // appid
        String mch_id = WeixinPayConfig.mch_id; // 商业号
        String key = WeixinPayConfig.key; // key
        String currTime = DateUtil.dateTimeToString(new Date(), "yyyyMMdd");
        String strRandom = WechatUtil.buildRandom(10).toString();
        String nonce_str = currTime + strRandom;
        // 获取请求 ip
        String spbill_create_ip = WebUtils.getClientIpAddress();
        // 回调接口
        String notify_url = WeixinPayConfig.notify_url;
        String trade_type = "NATIVE";
        //
        SortedMap<String, Object> packageParams = new TreeMap<String, Object>();
        packageParams.put("appid", appid);
        packageParams.put("mch_id", mch_id);
        packageParams.put("nonce_str", nonce_str);
        packageParams.put("body", orderNo);  //
        packageParams.put("out_trade_no", out_trade_no);
        packageParams.put("total_fee", Integer.valueOf(totalAmount) * 100+""); //价格的单位为分
        packageParams.put("spbill_create_ip", spbill_create_ip);
        packageParams.put("notify_url", notify_url);
        packageParams.put("trade_type", trade_type);
        String sign = WechatUtil.createSign(packageParams, key);
        packageParams.put("sign", sign);
        String requestXML = WechatUtil.map2XmlString(packageParams);
        String resXml = HttpRequest.sendPost(WeixinPayConfig.url, requestXML);
        Map map = WechatUtil.readXmlString2Map(resXml);
        String urlCode="";
        if(map!=null) {
           urlCode = (String) map.get("code_url");
        }
        return urlCode;
    }
}
