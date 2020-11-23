package com.lwxf.industry4.webapp.config;


import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.filter.LevelFilter;

import static ch.qos.logback.core.spi.FilterReply.ACCEPT;
import static ch.qos.logback.core.spi.FilterReply.DENY;

/**
 * 这个类的作用是给日志通过level设置过滤器
 * @author lyh on 2019/12/29
 */
public class LevelController {
    /**
     * 通过level设置过滤器
     *
     * @param level
     * @return
     */
    public LevelFilter getLevelFilter(Level level) {
        LevelFilter levelFilter = new LevelFilter();
        levelFilter.setLevel(level);
        levelFilter.setOnMatch(ACCEPT);
        levelFilter.setOnMismatch(DENY);
        return levelFilter;
    }
}
