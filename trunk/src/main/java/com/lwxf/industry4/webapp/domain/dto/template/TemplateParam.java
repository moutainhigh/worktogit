package com.lwxf.industry4.webapp.domain.dto.template;

/**
 * Created with IntelliJ IDEA.
 *
 * @Auther: SunXianWei
 * @Date: 2020/06/24/9:30
 * @Description:
 */
public class TemplateParam {
    // 参数值
    private String value;
    // 颜色
    private String color;


    public TemplateParam(String value, String color){

        this.value=value;
        this.color=color;
    }


    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
