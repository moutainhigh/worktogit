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
 * @Auther: SunXianWei
 * @Date: 2020/07/09/14:45
 * @Description:
 */
@Configuration
@Profile("tshps")
public class TshpsParsePropertiesConfig {
    @Bean
    public static PropertyPlaceholderConfigurer encryptionPropertyPlaceholderConfigurer(){
        EncryptionPropertyPlaceholderConfigurer bean = new AppEncryptionPropertyPlaceholderConfigurer();
        bean.setLocations(new ClassPathResource("application.properties"),new ClassPathResource("application-tshps.properties"));
        return bean;
    }
}
