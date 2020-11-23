package com.lwxf.industry4.webapp.baseservice.tsmanager.base;

import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManagerEntity;
import com.lwxf.industry4.webapp.common.enums.SQLType;
import com.lwxf.industry4.webapp.baseservice.tsmanager.TSManagerEntity;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 14:41:13
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BeforeUpdateEventListener extends EventListener {

    /**
     * 语句更新前
     * @param tsManagerEntity
     * @param sqlType
     */
    void beforeUpdate(TSManagerEntity tsManagerEntity, SQLType sqlType);

}
