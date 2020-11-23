package com.lwxf.industry4.webapp.baseservice.zaoyi.service.base;


import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：renzhongshan(d3shan@126.com)
 * @create：2018-05-24 11:26:47
 * @version：2018 1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface BaseNoIdService<T> {
	List<T> findAll();
	List<T> findListByParams(Map<String,Object> params);
}
