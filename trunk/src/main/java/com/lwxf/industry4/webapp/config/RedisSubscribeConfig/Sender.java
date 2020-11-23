package com.lwxf.industry4.webapp.config.RedisSubscribeConfig;

/**
 * @author lyh on 2020/7/20
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class Sender {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    public void sendNewStoreMessage(String content){

        stringRedisTemplate.convertAndSend("channel",content);
    }

}
