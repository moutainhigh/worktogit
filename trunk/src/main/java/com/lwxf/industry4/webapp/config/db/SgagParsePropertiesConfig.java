package com.lwxf.industry4.webapp.config.db;

import com.lwxf.commons.security.EncryptionPropertyPlaceholderConfigurer;
import com.lwxf.industry4.webapp.common.security.config.AppEncryptionPropertyPlaceholderConfigurer;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.ClassPathResource;

/**
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/7/16 19:36
 * @Description:
 */
@Configuration
@Profile("sgag")
public class SgagParsePropertiesConfig {
    @Bean
    public static PropertyPlaceholderConfigurer encryptionPropertyPlaceholderConfigurer(){
        EncryptionPropertyPlaceholderConfigurer bean = new AppEncryptionPropertyPlaceholderConfigurer();
        bean.setLocations(new ClassPathResource("application.properties"),new ClassPathResource("application-sgag.properties"));
        return bean;
    }
}
