package com.lwxf.industry4.webapp.domain.dto.customorder;

import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderProcess;

import java.util.Date;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2019/1/4 16:25
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CustomOrderProcessDto extends CustomOrderProcess {

    private String name;//阶段名称
    private String creator;//操作人
    private Date created;//操作时间
    private String path;//视频路径


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}

