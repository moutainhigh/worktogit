package com.lwxf.industry4.webapp.config;

import org.springframework.beans.BeansException;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.ErrorPageFilter;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 在不能注入时候使用spring容器的bean
 * @author lyh on 2019/11/18
 */

@Component
@Configuration
public class ApplicationContextRegister implements ApplicationContextAware {
    private static ApplicationContext APPLICATION_CONTEXT;

    /**
     * 设置spring上下文  *
     * * @param applicationContext spring上下文
     * * @throws BeansException
     * * author:liyanhao
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        APPLICATION_CONTEXT = applicationContext;
    }



    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

}
