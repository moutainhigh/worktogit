package com.lwxf.industry4.webapp.baseservice.zaoyi.facade.base;

import java.util.Map;

import com.lwxf.industry4.webapp.common.result.RequestResult;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 17:34:05
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BaseFacade<T> {
	RequestResult findAll();
	RequestResult findListByParams(Map<String,Object> params);
}
