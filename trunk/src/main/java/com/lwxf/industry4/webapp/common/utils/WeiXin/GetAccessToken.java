package com.lwxf.industry4.webapp.common.utils.WeiXin;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: SunXianWei
 * @Date: 2020/06/24/9:32
 * @Description:
 */
@Component
public class GetAccessToken {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Value("${lwxf.wx.official.appid}")
    private String appid;
    @Value("${lwxf.wx.official.appSecret}")
    private String appSecret;

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
