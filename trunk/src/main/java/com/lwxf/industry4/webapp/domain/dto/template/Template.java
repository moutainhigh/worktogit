package com.lwxf.industry4.webapp.domain.dto.template;

import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: SunXianWei
 * @Date: 2020/06/24/9:27
 * @Description:
 */
public class Template {
    // 消息接收方
    private String touser;
    // 模板id
    private String template_id;
    // 模板消息详情链接
    private String url;
    //跳转小程序
    WxMpTemplateMessage.MiniProgram miniprogram = new WxMpTemplateMessage.MiniProgram();
    // 消息顶部的颜色
    private String topColor;
    // 参数列表
    private Map<String,TemplateParam> data;

    public String getTouser() {
        return touser;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTemplate_id() {
        return template_id;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTopColor() {
        return topColor;
    }

    public void setTopColor(String topColor) {
        this.topColor = topColor;
    }


    public Map<String, TemplateParam> getData() {
        return data;
    }

    public void setData(Map<String, TemplateParam> data) {
        this.data = data;
    }

    public WxMpTemplateMessage.MiniProgram getMiniprogram() {
        return miniprogram;
    }

    public void setMiniprogram(WxMpTemplateMessage.MiniProgram miniprogram) {
        this.miniprogram = miniprogram;
    }
}
