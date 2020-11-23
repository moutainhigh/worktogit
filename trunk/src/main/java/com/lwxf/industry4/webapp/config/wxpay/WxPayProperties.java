package com.lwxf.industry4.webapp.config.wxpay;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.context.properties.ConfigurationProperties;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.lwxf.commons.utils.LwxfStringUtils;

/**
 * 功能：微信支付配置项加载
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-07-27 19:04
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@ConfigurationProperties(prefix = "lwxf.wx.pay")
public class WxPayProperties {
	private Logger logger = LoggerFactory.getLogger(this.getClass().getName());
	/**
	 * 设置微信公众号的appid
	 */
	private String appId;

	/**
	 * 微信支付商户号
	 */
	private String mchId;

	/**
	 * 微信支付商户密钥
	 */
	private String mchKey;

	/**
	 * 服务商模式下的子商户公众账号ID，普通模式请不要配置，请在配置文件中将对应项删除
	 */
	private String subAppId;

	/**
	 * 服务商模式下的子商户号，普通模式请不要配置，最好是请在配置文件中将对应项删除
	 */
	private String subMchId;

	/**
	 * apiclient_cert.p12文件的绝对路径，或者如果放在项目中，请以classpath:开头指定
	 */
	private String keyPath;

	/**
	 * 支付成功后的回调通知地址
	 */
	private String notifyUrl;

	/**
	 * 支付场景：app应用的名称
	 */
	private String appname;

	/**
	 * 支付完成后的跳转路径
	 */
	private String redirectUrl;

	private Boolean enableRedirecturl;

	public String getAppId() {
		return this.appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getMchId() {
		return mchId;
	}

	public void setMchId(String mchId) {
		this.mchId = mchId;
	}

	public String getMchKey() {
		return mchKey;
	}

	public void setMchKey(String mchKey) {
		this.mchKey = mchKey;
	}

	public String getSubAppId() {
		return subAppId;
	}

	public void setSubAppId(String subAppId) {
		this.subAppId = subAppId;
	}

	public String getSubMchId() {
		return subMchId;
	}

	public void setSubMchId(String subMchId) {
		this.subMchId = subMchId;
	}

	public String getKeyPath() {
		return this.keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}

	public String getNotifyUrl() {
		return notifyUrl;
	}

	public void setNotifyUrl(String notifyUrl) {
		this.notifyUrl = notifyUrl;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this,
				ToStringStyle.MULTI_LINE_STYLE);
	}

	public String getAppname() {
		return appname;
	}

	public void setAppname(String appname) {
		this.appname = appname;
	}

	/**
	 * 已经进行UrlEncoder转码
	 * @return
	 */
	public String getRedirectUrl(String orderId) {
		try {
			return URLEncoder.encode(LwxfStringUtils.format(redirectUrl,orderId),"utf-8");
		} catch (UnsupportedEncodingException e) {
			this.logger.error("############### 执行URLEncoder.encode时异常：",e);
			return "";
		}
	}

	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}

	public Boolean isEnableRedirecturl() {
		return enableRedirecturl == Boolean.TRUE;
	}

	public void setEnableRedirecturl(Boolean enableRedirecturl) {
		this.enableRedirecturl = enableRedirecturl;
	}
}
