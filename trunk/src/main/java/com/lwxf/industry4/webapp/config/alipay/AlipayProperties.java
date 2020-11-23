package com.lwxf.industry4.webapp.config.alipay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: SunXianWei
 * @Date: 2020/07/06/16:58
 * @Description:
 */
@Component
public class AlipayProperties {
    @Value("${alipay.appid}")
    private String appId;
    @Value("${alipay.gatewayUrl}")
    private String gatewayUrl;
    @Value("${alipay.format}")
    private String format;
    @Value("${alipay.charset}")
    private String charset;
    @Value("${alipay.signType}")
    private String signType;
    @Value("${alipay.appPrivateKey}")
    private String appPrivateKey;
    @Value("${alipay.alipayPublicKey}")
    private String alipayPublicKey;
    @Value("${alipay.returnUrl}")
    private String returnUrl;
    @Value("${alipay.notifyUrl}")
    private String notifyUrl;

    public String getAppId() {
        return appId;
    }

    public String getGatewayUrl() {
        return gatewayUrl;
    }

    public String getFormat() {
        return format;
    }

    public String getCharset() {
        return charset;
    }

    public String getSignType() {
        return signType;
    }

    public String getAppPrivateKey() {
        return appPrivateKey;
    }

    public String getAlipayPublicKey() {
        return alipayPublicKey;
    }

    public String getReturnUrl() {
        return returnUrl;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }
}
