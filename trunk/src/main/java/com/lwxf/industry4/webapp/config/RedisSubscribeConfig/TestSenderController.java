package com.lwxf.industry4.webapp.config.RedisSubscribeConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author lyh on 2020/7/20
 */
//定时器
@EnableScheduling
@Component
public class TestSenderController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //向redis消息队列index通道发布消息
    //@Scheduled(fixedRate = 2000)
//    public void sendMessage(){
//        HashMap<String, Object> hashMap = new HashMap<>();
//        hashMap.put("orderStatus","1");
//        hashMap.put("orderNo","123456");
//        String s = JSON.toJSONString(hashMap);
//        stringRedisTemplate.convertAndSend("channel",s);
//    }
}
