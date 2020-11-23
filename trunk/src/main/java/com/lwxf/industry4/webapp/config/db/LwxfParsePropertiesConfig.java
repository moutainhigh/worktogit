package com.lwxf.industry4.webapp.config.db;

import com.lwxf.commons.security.EncryptionPropertyPlaceholderConfigurer;
import com.lwxf.industry4.webapp.common.security.config.AppEncryptionPropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-03-12 18:34
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Configuration
@Profile("lwxf")
public class LwxfParsePropertiesConfig {
    @Bean
    public static PropertyPlaceholderConfigurer encryptionPropertyPlaceholderConfigurer(){
        EncryptionPropertyPlaceholderConfigurer bean = new AppEncryptionPropertyPlaceholderConfigurer();
        bean.setLocations(new ClassPathResource("application.properties"),new ClassPathResource("application-lwxf.properties"));
        return bean;
    }
}
