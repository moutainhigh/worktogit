package com.lwxf.industry4.webapp.config.RedisSubscribeConfig;

import org.springframework.stereotype.Component;

/**
 * @author lyh on 2020/7/20
 */
@Component
public interface RedisMsg {

    void receiveMessage(String message);
}
