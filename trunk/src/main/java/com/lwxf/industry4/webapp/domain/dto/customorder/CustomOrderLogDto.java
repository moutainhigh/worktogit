package com.lwxf.industry4.webapp.domain.dto.customorder;

import java.util.List;

import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：panchenxiao(Mister_pan@126.com)
 * @create：2018/12/20 13:39
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public class CustomOrderLogDto extends CustomOrderLog {

    private  Integer status;
    private List<MapContext> LogisticsInfo;//物流相关信息

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public List<MapContext> getLogisticsInfo() {
        return LogisticsInfo;
    }

    public void setLogisticsInfo(List<MapContext> logisticsInfo) {
        LogisticsInfo = logisticsInfo;
    }
}

