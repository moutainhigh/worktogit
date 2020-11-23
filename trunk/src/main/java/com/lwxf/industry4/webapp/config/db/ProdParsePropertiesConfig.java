package com.lwxf.industry4.webapp.config.db;

import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

import com.lwxf.industry4.webapp.common.security.config.AppEncryptionPropertyPlaceholderConfigurer;
import com.lwxf.commons.security.EncryptionPropertyPlaceholderConfigurer;

/**
 * 功能：
 *
 * @author：renzhongshan(zhongshan.ren@ihydt.com)
 * @create：2017-03-16 10:50:40
 * @version：2017 Version：1.0
 * @company：创海科技 Created with IntelliJ IDEA
 */
@Configuration
@Profile("prod")
public class ProdParsePropertiesConfig {
	@Bean
	public static PropertyPlaceholderConfigurer encryptionPropertyPlaceholderConfigurer(){
		EncryptionPropertyPlaceholderConfigurer bean = new AppEncryptionPropertyPlaceholderConfigurer();
		bean.setLocations(new ClassPathResource("application.properties"),new ClassPathResource("application-prod.properties"));
		return bean;
	}
}
