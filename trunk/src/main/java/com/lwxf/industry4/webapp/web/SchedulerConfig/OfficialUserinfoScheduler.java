package com.lwxf.industry4.webapp.web.SchedulerConfig;

import com.alibaba.fastjson.JSONObject;
import com.lwxf.industry4.webapp.common.utils.WeiXin.GetAccessToken;
import com.lwxf.industry4.webapp.common.utils.WeiXin.HttpRequest;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**定时更新公众号关注用户的信息
 * Created with IntelliJ IDEA.
 *
 * @version 1.0
 * @Auther: SunXianWei
 * @Date: 2020/8/6 11:02
 * @Description:
 */
@Component
public class OfficialUserinfoScheduler {
    @Autowired
    private GetAccessToken getAccessToken;

    /**
     * 定时更新公众号关注用户的信息
     */
    @Scheduled(cron = "0 15 0 * * ?")//每天0:15触发一次
    private void officialUserinfo() {
        // https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN&next_openid=NEXT_OPENID
        String accessToken = getAccessToken.getAccessToken();
        String next_openid = null;
        String params2 = "access_token=" + accessToken + "&next_openid=" + next_openid;
        String userinfo = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/get", params2);
        JSONObject object = JSONObject.parseObject(userinfo);
        Map data = (Map) object.get("data");
        List<String> openids = (List) data.get("openid");
        if (openids != null && openids.size() > 0) {
            for (String openid : openids) {
                String params = "access_token=" + accessToken + "&openid=" + openid + "&lang=zh_CN";
                String user = HttpRequest.sendGet("https://api.weixin.qq.com/cgi-bin/user/info", params);
                JSONObject obj = JSONObject.parseObject(user);
                String unionid = obj.getString("unionid");
                UserThirdInfo userByunionid = AppBeanInjector.userThirdInfoService.findUserByunionid(unionid);
                if (userByunionid != null) {
                    MapContext context = MapContext.newOne();
                    context.put("userId", userByunionid.getUserId());
                    context.put("officialOpenId", openid);
                    AppBeanInjector.userThirdInfoService.updateByMapContext(context);
                }
            }
        }
    }
}
