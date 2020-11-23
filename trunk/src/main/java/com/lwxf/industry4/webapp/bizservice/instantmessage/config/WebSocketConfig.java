package com.lwxf.industry4.webapp.bizservice.instantmessage.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * 开启WebSocket支持
 * spring.profiles.active 为localtest 时生效
 * @author lyh
 */
@Configuration
@ConditionalOnProperty(name = "spring.profiles.active",havingValue = "localtest")
public class WebSocketConfig {
    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

}