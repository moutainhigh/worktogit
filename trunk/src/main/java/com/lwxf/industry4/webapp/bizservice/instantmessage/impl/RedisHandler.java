package com.lwxf.industry4.webapp.bizservice.instantmessage.impl;//package com.lwxf.industry4.webapp.common.instantmessage.impl;
//
//import com.lwxf.industry4.webapp.baseservice.cache.RedisUtils;
//import com.lwxf.industry4.webapp.common.instantmessage.ISendMsgHandler;
//import com.lwxf.industry4.webapp.common.instantmessage.ImMessage;
//import com.lwxf.industry4.webapp.common.instantmessage.WebSocketServer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class RedisHandler implements ISendMsgHandler {
//    @Autowired
//    WebSocketServer webSocketServer;
//
//
//    @Autowired
//    RedisUtils redisUtils;
//    private String imPrefix="imMsg_";
//    @Override
//    public boolean sendTextMsg(ImMessage msg) {
//
//        if(msg.getMsgType()==2)//发送给个人的
//        {
//            Object obj=redisUtils.get(imPrefix+msg.getTo());
//            if(obj!=null)
//            {
//                List<ImMessage> imList=(List<ImMessage>)obj;
//                if(imList==null)
//                {
//                    imList=new ArrayList<>();
//                }
//                imList.add(msg);
//                redisUtils.set(imPrefix+msg.getTo(),imList,432000);//有效期保存5天
//            }
//            else
//            {
//                List<ImMessage> imList=new ArrayList<>();
//                imList.add(msg);
//                redisUtils.set(imPrefix+msg.getTo(),imList,432000);//有效期保存5天
//            }
//        }
//
//
//        return false;
//    }
//}
