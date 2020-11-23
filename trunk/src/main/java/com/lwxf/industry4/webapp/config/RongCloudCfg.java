package com.lwxf.industry4.webapp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import org.apache.commons.lang3.StringUtils;

import com.lwxf.commons.security.DesTool;

import javax.annotation.PostConstruct;

/**
 * 功能：融云配置
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-09-14 16:53:33
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Configuration
public class RongCloudCfg {
	@Value("${lwxf.rongcloud.cfg}")
	private String cfg;
	private String appKey;
	private String appSecret;

	private DesTool desTool = new DesTool();

	@PostConstruct
	public void postConstruct(){
		if(StringUtils.isNoneBlank(this.cfg)){
			String[] arr = this.desTool.decrypt(this.cfg).split("=");
			this.appKey = arr[0];
			this.appSecret = arr[1];
		}
	}

	public String getAppKey() {
		return appKey;
	}

	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}

	public String getAppSecret() {
		return appSecret;
	}

	public void setAppSecret(String appSecret) {
		this.appSecret = appSecret;
	}
}
