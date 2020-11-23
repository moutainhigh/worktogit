package com.lwxf.industry4.webapp.bizservice.instantmessage;


import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.lwxf.commons.uniquekey.IdGererateFactory;
import com.lwxf.industry4.webapp.baseservice.cache.RedisUtils;
import com.lwxf.industry4.webapp.bizservice.chatrecord.ChatRecordService;
import com.lwxf.industry4.webapp.common.constant.CommonConstant;
import com.lwxf.industry4.webapp.domain.entity.chatrecord.ChatRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.*;
import java.util.concurrent.CopyOnWriteArraySet;


@Component
@ServerEndpoint("/websocket/{userName}")
public class WebSocketServer {
    //@OnError public void onerror(Session session,Throwable throwable){ System.out.println("error…"); }
    static Log log = LogFactory.getLog(WebSocketServer.class);
    //静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。
    private static int onlineCount = 0;
    //concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。
    private static CopyOnWriteArraySet<WebSocketServer> webSocketSet = new CopyOnWriteArraySet<WebSocketServer>();

    private Session session;

    // 这里使用静态，让 service 属于类
    private static ChatRecordService chatRecordService;

    // 注入的时候，给类的 service 注入
    @Resource(name = "chatRecordService")
    public void setChatService(ChatRecordService chatRecordService) {
        WebSocketServer.chatRecordService = chatRecordService;
    }

    public  ChatRecordService getChatRecordService() {
        return chatRecordService;
    }


    private static Map<String, Session> sessionPool = new HashMap<String, Session>();

    @Autowired
    RedisUtils redisUtils;

    @Resource
    private ApplicationContext ctx;


//    @Autowired
//    private ThreadPoolTaskExecutor threadPoolTaskExecutor;

    private String imPrefix = "imMsg_";

    @OnOpen
    public void onOpen(Session session, @PathParam(value = "userName") String userName) {
        this.session = session;
        webSocketSet.add(this);
        sessionPool.put(userName, session);
        System.out.println(userName + "【websocket消息】有新的连接，总数为:" + webSocketSet.size());
//        ApplicationContext act = ApplicationContextRegister.getApplicationContext();
//        ThreadPoolTaskExecutor threadPoolTaskExecutor = act.getBean(ThreadPoolTaskExecutor.class);
//        threadPoolTaskExecutor.execute(new Runnable() {
//            @Override
//            public void run() {
//                //websocket 使用threadPoolTaskExecutor线程池
//                ChatRecordDao chatRecordDao = act.getBean(ChatRecordDao.class);
//                //打开sock同事发送未读消息
//                ChatRecord chatRecord = new ChatRecord();
//                chatRecord.setChatUserId(userName);
//                chatRecord.setChatUserId(CommonConstant.CHAT_NO_SEND);//查询未发送消息
//                MapContext paramMap = new MapContext();
//                try {
//                    Thread.sleep(5000);//停顿5秒发送离线消息
//                } catch (Exception e) {
//                    log.error("数据查询失败");
//                }
//                paramMap.put("chat_user_id", userName);
//                paramMap.put("chat_is_send", CommonConstant.CHAT_NO_SEND);
//                PaginatedFilter paginatedFilter = new PaginatedFilter();
//                paginatedFilter.setFilters(paramMap);
//                PaginatedList<ChatRecord> chatRecordPaginatedList = chatRecordDao.selectByFilter(paginatedFilter);
//                List<ChatRecord> chatRecords = chatRecordPaginatedList.getRows();
//                for (ChatRecord chat : chatRecords) {
//                    System.out.println(userName + "【websocket消息】发送离线聊天记录:" + chat.getChatContent());
//                    sendOneFairlyMessage(userName, chat.getChatContent());
//                    chat.setChatIsSend("1");
//                    HashMap<String, Object> objectObjectHashMap = new HashMap<>();
//                    BeanUtils.copyProperties(chat, objectObjectHashMap);
//                    MapContext from = MapContext.from(objectObjectHashMap);
//                    int i = chatRecordDao.updateByMapContext(from);
//                    if (i == 0) {
//                        chatRecordDao.insert(chat);
//                    }
//                }
//            }
//        });

    }

//    @OnClose
//    public void onClose() {
//        webSocketSet.remove(this);
//        System.out.println("【websocket消息】连接断开，总数为:" + webSocketSet.size());
//    }

//    @OnMessage
//    public void onMessage(String message) {
//        System.out.println("【websocket消息】收到客户端消息:" + message);
//        JSONObject jsonTo = JSONObject.parseObject(message);
//        ImMessage msg = new ImMessage();
//        msg = JSONObject.parseObject(message, ImMessage.class);
//        if (msg != null) {
//            msg.setSendTime(new Date());
//            sendTextMsg(msg);
//        }
//    }

    @OnMessage
    public void onMessage(String message) {
        System.out.println("【websocket消息】收到客户端消息:" + message);
        JSONObject jsonTo = JSONObject.parseObject(message);
        Map msg = JSONObject.parseObject(message, Map.class);
        if (msg != null) {
            //msg.setSendTime(new Date());
            sendTextMsgIm(msg);
        }
    }

    // 此为广播消息
    public void sendAllMessage(String message) {
        for (WebSocketServer webSocket : webSocketSet) {
            System.out.println("【websocket消息】广播消息:" + message);
            try {
                webSocket.session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    // 此为单点消息
    public void sendOneMessage(String userName, String message) {
        System.out.println("【websocket消息】单点消息:" + message);
        Session session = sessionPool.get(userName);
        //保存聊天记录消息
        String ss = "";
        Map<String, String> params = null;
        if (session != null && session.isOpen()) {
            try {
                session.getAsyncRemote().sendText(message);
                ss =CommonConstant.CHAT_NO_SEND; //是否已发送:1.已发送 2.未发送
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            ChatRecord chatRecord = new ChatRecord();
            chatRecord.setChatUserId("12312"); //用户id
            chatRecord.setChatType(CommonConstant.CHAT_STR);//聊天记录类型：1.字符串2.文件路径
            chatRecord.setChatTime(new Date());//聊天插入记录时间
            chatRecord.setChatUsername("");//保存汉字姓名
            chatRecord.setChatDepatId("");//保存部门名称
            chatRecord.setChatContent("123123");//聊天内容：文字为字符串，图片文件为地址
            try{
                chatRecordService.add(chatRecord);
                System.out.println("插入成功+++");
            }catch (Exception e){
                System.out.println("插入失败+++");
            }
            //保存未读聊天记录消息
            chatRecord.setChatIsSend(CommonConstant.CHAT_NO_SEND); //是否已发送:1.已发送 2.未发送
            //给发送着提示信息 "你好"本条消息未发送成功，会在用户登录系统后发送
            JSONObject jsonObject = JSONObject.parseObject(message);
            params = JSONObject.parseObject(jsonObject.toJSONString(), new TypeReference<Map<String, String>>() {
            });
            String fromid = params.get("fromid");//原发送者id==>username
            params.put("content", "本条内容为     [" + params.get("content") + "]的消息接收者不在线，将在用户上线后发送给此用户");
            params.put("mine", "false");
            params.put("online","0");
            sendOneFairlyMessage(fromid, JSONObject.toJSONString(params));
        }




    }

    /**
     * 失败消息回复
     *
     * @param fromid
     * @param message
     */
    public void sendOneFairlyMessage(String fromid, String message) {

        Session session = sessionPool.get(fromid);
        if (session != null && session.isOpen()) {
            System.out.println("【websocket消息】发送失败消息提示:" + message);
            try {
                session.getAsyncRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    public void sendTextMsgIm(Map msg) {
        if ("onMessage".equals((String) msg.get("type")))//发送全局所有人的
        {
            try {
                //根据用户ID获取用户名
                //获取发送人ID
                 Object data = msg.get("data");
                Map<String, Object> user = null;
                if (data != null) {
                    user = (Map<String, Object>) data;
                }
                Map<String, Object> mineUser = (Map<String, Object>) user.get("mine");
                Map<String, Object> toUser = (Map<String, Object>) user.get("to");
                if (mineUser != null && toUser != null) {
                    boolean mine = false;//是否我发送的消息，如果为true，则会显示在右方
                    if (mineUser.get("id").equals(toUser.get("id"))) {
                        mine = true;
                    }
                    mineUser.put("mine", mine);
                    mineUser.put("fromid", mineUser.get("id"));
                    mineUser.put("timestamp", System.currentTimeMillis());
                    mineUser.put("type", "friend");//聊天窗口来源类型，从发送消息传递的to里面获取
                    mineUser.put("messageType", "im");
                    String tempMsg = JSONObject.toJSONString(mineUser);
                    sendOneMessage((String) toUser.get("id"), tempMsg);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }else if ("img".equals( msg.get("type"))){
            //图片
            Object data = msg.get("data");
            Map<String, Object> user = null;
            if (data != null) {
                user = (Map<String, Object>) data;
            }
            Map<String, Object> mineUser = (Map<String, Object>) user.get("mine");
            Map<String, Object> toUser = (Map<String, Object>) user.get("to");
            if (mineUser != null && toUser != null) {
                boolean mine = false;//是否我发送的消息，如果为true，则会显示在右方
                if (mineUser.get("id").equals(toUser.get("id"))) {
                    mine = true;
                }
                mineUser.put("mine", mine);
                mineUser.put("fromid", mineUser.get("id"));
                mineUser.put("timestamp", System.currentTimeMillis());
                mineUser.put("type", "friend");//聊天窗口来源类型，从发送消息传递的to里面获取
                mineUser.put("messageType", "img");
                String tempMsg = JSONObject.toJSONString(mineUser);
                sendOneMessage((String) toUser.get("id"), tempMsg);
            }

        }else if("file".equals(msg.get("type"))){
            //文件
            //拿出id查询图片文件路径拼接到content中
        }
    }

    public void sendTextMsg(ImMessage msg) {
        if (msg.getMsgType() == 0)//发送全局所有人的
        {
            for (WebSocketServer webSocket : webSocketSet) {
                System.out.println("【websocket消息】广播消息:" + msg.toString());
                try {
                    //根据用户ID获取用户名
                    //获取发送人ID
//                    String userName = sysUserService.getUserNameById(msg.getFrom());
//                    if (StringUtils.isEmpty(userName)) {
//                        userName = "系统用户";
//                    }
//                    msg.setFrom(userName);
                    String tempMsg = JSONObject.toJSONString(msg);
                    webSocket.session.getAsyncRemote().sendText(tempMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } else if (msg.getMsgType() == 1)//发送给部门
        {
            //获取部门人员信息

            Object obj = redisUtils.get(imPrefix + msg.getTo());
            if (obj != null) {
                List<ImMessage> imList = (List<ImMessage>) obj;
                if (imList == null) {
                    imList = new ArrayList<>();
                }
                imList.add(msg);
                redisUtils.set(imPrefix + msg.getTo(), imList, 432000);//有效期保存5天
            } else {
                List<ImMessage> imList = new ArrayList<>();
                imList.add(msg);
                redisUtils.set(imPrefix + msg.getTo(), imList, 432000);//有效期保存5天
            }
        } else if (msg.getMsgType() == 2)//发送给个人的
        {
            //根据用户ID获取用户名
            //获取发送人ID
//            String fromUserName = sysUserService.getUserNameById(msg.getFrom());
//            if (StringUtils.isEmpty(fromUserName)) {
//                fromUserName = "系统用户";
//            }
            //首先判断人员在不在线，如果不在线则保存到缓存中
            Session session = sessionPool.get(msg.getTo());
            if (session != null) {
                try {
                    String tempMsg = JSONObject.toJSONString(msg);
                    session.getAsyncRemote().sendText(tempMsg);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                Object obj = redisUtils.get(imPrefix + msg.getTo());
                if (obj != null) {
                    List<ImMessage> imList = (List<ImMessage>) obj;
                    if (imList == null) {
                        imList = new ArrayList<>();
                    }
                    imList.add(msg);
                    redisUtils.set(imPrefix + msg.getTo(), imList, 432000);//有效期保存5天
                } else {
                    List<ImMessage> imList = new ArrayList<>();
                    imList.add(msg);
                    redisUtils.set(imPrefix + msg.getTo(), imList, 432000);//有效期保存5天
                }
            }
        }
    }
}
