package com.lwxf.industry4.webapp.common.utils.WeiXin;


import com.alibaba.fastjson.JSONObject;
import com.lwxf.commons.json.JsonMapper;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.domain.dto.template.Template;
import com.lwxf.industry4.webapp.domain.dto.template.TemplateParam;
import com.lwxf.mybatis.utils.MapContext;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: SunXianWei
 * @Date: 2020/06/24/9:32
 * @Description:
 */


@Component
public class WeiXinMsgPush {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * 发送微信模板信息
     *
     * @param openId 接受者openId
     * @return 是否推送成功
     */
    @Value("${lwxf.wx.official.appid}")
    private String appid;
    @Value("${lwxf.wx.official.appSecret}")
    private String appSecret;
    @Value("${lwxf.wx.official.sure.templateId}")
    private String sureTemplateId;
    @Value("${lwxf.wx.official.produce.templateId}")
    private String produceTemplateId;
    @Value("${lwxf.wx.official.delive.templateId}")
    private String deliveTemplateId;
    @Value("${lwxf.wx.official.defaulturl}")
    private String defaulturl;

    public String SendWxMsg(String openId, MapContext mapContext) {
        String orderId=mapContext.getTypedValue("orderId",String.class);
        Integer orderStatus=mapContext.getTypedValue("orderStatus",Integer.class);
        WxMpTemplateMessage.MiniProgram miniProgram=new WxMpTemplateMessage.MiniProgram();
        miniProgram.setAppid( WebConstant.WX_APPID);
        miniProgram.setPagePath("pages/B/orderListDetail/orderListDetail?id="+orderId);
        //获取token
        String accessToken = this.getAccessToken();
        // 发送模板消息接口
        //根据不同订单节点，发送不同消息
        // 添加模板数据，key对应模板消息的key
        Template template=new Template();
        template.setTouser(openId);
        if(orderStatus== 2) {
            template.setTemplate_id(sureTemplateId);
        }
        else if(orderStatus== 3) {
            template.setTemplate_id(produceTemplateId);
        }
        else if(orderStatus== 4) {
            template.setTemplate_id(deliveTemplateId);
        }
        template.setUrl(defaulturl);
        template.setMiniprogram(miniProgram);
        Map<String, TemplateParam> data=new HashMap<>();
        data.put("first",new TemplateParam(mapContext.getTypedValue("first",String.class), "#000000"));
        data.put("keyword1",new TemplateParam(mapContext.getTypedValue("keyword1",String.class), "#000000"));
        data.put("keyword2",new TemplateParam(mapContext.getTypedValue("keyword2",String.class), "#000000"));
        data.put("keyword3",new TemplateParam(mapContext.getTypedValue("keyword3",String.class), "#000000"));
        data.put("keyword4",new TemplateParam(mapContext.getTypedValue("keyword4",String.class), "#000000"));
        data.put("keyword5",new TemplateParam(mapContext.getTypedValue("keyword5",String.class), "#000000"));
        data.put("remark",new TemplateParam(mapContext.getTypedValue("remark",String.class), "#000000"));
        template.setData(data);
        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String s1 = jsonMapper.toJson(template);
        System.out.println(s1);
        String s = HttpRequest.sendPost(url, jsonMapper.toJson(template));
        if(s==null){
            logger.warn("·==++--·推送微信模板信息：{}·--++==·", s != null ? "成功" : "失败");
        }
        return s;
    }

    public String testSendWxMsg(String openId, MapContext mapContext, String orderId) {
        openId = "oV3JE1HIQbYrYNtC-swZ7ulrKZuQ";
        orderId="5ipfro51s5j4";
        WxMpTemplateMessage.MiniProgram miniProgram=new WxMpTemplateMessage.MiniProgram();
        miniProgram.setAppid( WebConstant.WX_APPID);
        miniProgram.setPagePath("pages/B/orderListDetail/orderListDetail?id="+orderId);
        //获取token
        String accessToken = this.getAccessToken();
        // 发送模板消息接口
        //根据不同订单节点，发送不同消息
        // 添加模板数据，key对应模板消息的key
        Template template=new Template();
        template.setTouser(openId);
        template.setTemplate_id(deliveTemplateId);
        template.setUrl("https://erp4.htjjsc.com");
        template.setMiniprogram(miniProgram);
        Map<String, TemplateParam> data=new HashMap<>();
        data.put("first",new TemplateParam("您购买的订单已经发货啦，正快马加鞭向您飞奔而去。", "#000000"));
        data.put("keyword1",new TemplateParam("Y1912-141", "#000000"));
        data.put("keyword2",new TemplateParam(DateUtil.dateTimeToString(new Date()), "#000000"));
        data.put("keyword3",new TemplateParam("双友", "#000000"));
        data.put("keyword4",new TemplateParam("DD202006300012", "#000000"));
        data.put("keyword5",new TemplateParam("兴华大厦609", "#000000"));
        template.setData(data);
        String url="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
        JsonMapper jsonMapper=JsonMapper.createAllToStringMapper();
        String s1 = jsonMapper.toJson(template);
        System.out.println(s1);
        String s = HttpRequest.sendPost(url, jsonMapper.toJson(template));
        if(s==null){
            logger.warn("·==++--·推送微信模板信息：{}·--++==·", s != null ? "成功" : "失败");
        }
        return s;
    }
    public  String getAccessToken(){
        //获取token
        String params = "grant_type=client_credential&appid="+appid+"&secret="+appSecret;
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/token",params);
        JSONObject json= JSONObject.parseObject(sr);
        String access_token = json.get("access_token").toString();
        if(access_token==null){
            logger.error("获取access_token失败");
        }
        return access_token;
    }
}
