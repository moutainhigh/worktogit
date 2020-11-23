package com.lwxf.industry4.webapp.controller;

import com.alibaba.fastjson.JSONObject;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.utils.WeiXin.GetAccessToken;
import com.lwxf.industry4.webapp.common.utils.WeiXin.HttpRequest;
import com.lwxf.industry4.webapp.common.utils.WeiXin.WeiXinMsgPush;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/official")
@Api(value = "TestMsgController", tags = {"消息推送授权绑定"})
public class TestMsgController {
    private final Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private WeiXinMsgPush weiXinMsgPush;
    @Autowired
    private GetAccessToken getAccessToken;



    /**
     * 测试发送微信模板消息
     */
    @ApiOperation("测试发送微信模板消息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "openId", value = "接受者openId", dataType = "String", paramType = "query")
    })
    @GetMapping("/sendWxInfo")
    public void sendWxInfo(@RequestParam(required = false) String openId) {
        // 执行发送
        String aBoolean = weiXinMsgPush.testSendWxMsg(null, null,null);
        System.out.println(aBoolean);
    }


    @ApiOperation("获取公众号的openid并绑定手机号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code码,登录凭证", dataType = "string", paramType = "query", required = true),
            @ApiImplicitParam(name = "phone", value = "手机号", dataType = "String", paramType = "query")
    })
    @RequestMapping(value = "/bangdingOpenid", method = RequestMethod.POST)
    public void getOpenid(@RequestBody MapContext mapContext) throws Exception {
        String  code=mapContext.getTypedValue("code",String.class);
        String  phone=mapContext.getTypedValue("phone",String.class);
        String wxspAppid = WebConstant.WX_OFFICIAL_APPID;
        // app secret
        String wxspSecret = WebConstant.WX_OFFICIAL_APP_SECRET;
        //授权（必填）
        String grant_type = "authorization_code";
        // 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");
        //根据手机号查询用户信息
        User byLoginName = AppBeanInjector.userService.findByLoginName(phone);
        if(byLoginName!=null){
            String userid=byLoginName.getId();
            MapContext context=MapContext.newOne();
            context.put("userId",userid);
            context.put("official_open_id",openid);
            AppBeanInjector.userThirdInfoService.updateByMapContext(context);
        }
    }
    @ApiOperation("授权获取公众号的openid和unionid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code码,登录凭证", dataType = "string", paramType = "query", required = true),
    })
    @RequestMapping(value = "/bangding", method = RequestMethod.POST)
    public void bangdingOpenid(@RequestBody MapContext mapContext) throws Exception {
        String  code=mapContext.getTypedValue("code",String.class);
        String wxspAppid = WebConstant.WX_OFFICIAL_APPID;
        // app secret
        String wxspSecret = WebConstant.WX_OFFICIAL_APP_SECRET;
        //授权（必填）
        String grant_type = "authorization_code";
        // 1、向微信服务器 使用登录凭证 code 获取 session_key 和 openid
        String params = "appid=" + wxspAppid + "&secret=" + wxspSecret + "&code=" + code + "&grant_type=" + grant_type;
        //发送请求
        String sr = HttpRequest.sendGet("https://api.weixin.qq.com/sns/oauth2/access_token", params);
        //解析相应内容（转换成json对象）
        JSONObject json = JSONObject.parseObject(sr);
        //用户的唯一标识（openid）
        String openid = (String) json.get("openid");
//        //获取ACCESS_TOKEN
        String accessToken = getAccessToken.getAccessToken();
        // 获取用户的unionid
        String params2="access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
        String userinfo = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/info", params2);
        JSONObject object=JSONObject.parseObject(userinfo);
        String unionid=object.getString("unionid");
        //根据unionid查询系统对应的用户
        UserThirdInfo thirdInfo=AppBeanInjector.userThirdInfoService.findUserByunionid(unionid);
        if(thirdInfo!=null){
            MapContext context=MapContext.newOne();
            context.put("userId",thirdInfo.getUserId());
            context.put("official_open_id",openid);
            AppBeanInjector.userThirdInfoService.updateByMapContext(context);
        }
    }
    @ApiOperation("拉取公众号关注用户信息列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "code", value = "code码,登录凭证", dataType = "string", paramType = "query", required = true),
    })
    @RequestMapping(value = "/openids", method = RequestMethod.GET)
    public void getOfficialUserList() throws Exception {
       // https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
        String accessToken = getAccessToken.getAccessToken();
        String params2="access_token="+accessToken;
        String userinfo = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/get", params2);
        JSONObject object=JSONObject.parseObject(userinfo);
        Map data = (Map) object.get("data");
        List<String> openids =(List) data.get("openid");
        if(openids!=null&&openids.size()>0){
            for(String openid:openids){
                String params="access_token="+accessToken+"&openid="+openid+"&lang=zh_CN";
                String user = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/info", params);
                JSONObject obj=JSONObject.parseObject(user);
                String unionid=obj.getString("unionid");
                UserThirdInfo userByunionid = AppBeanInjector.userThirdInfoService.findUserByunionid(unionid);
                if(userByunionid!=null){
                    MapContext context=MapContext.newOne();
                    context.put("userId",userByunionid.getUserId());
                    context.put("officialOpenId",openid);
                    AppBeanInjector.userThirdInfoService.updateByMapContext(context);
                }
            }
        }
    }
}
