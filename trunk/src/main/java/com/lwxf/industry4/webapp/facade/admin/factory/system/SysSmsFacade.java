package com.lwxf.industry4.webapp.facade.admin.factory.system;


import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.system.SysSms;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：DJL(yuuyoo@163.com)
 * @created：2019-11-20 16:11:56
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface SysSmsFacade extends BaseFacade {

	RequestResult add(SysSms sysSms);

	RequestResult update(MapContext mapContext, String id);

	RequestResult delete(String id);

	RequestResult selectSysSmsDtoList(Integer pageNum, Integer pageSize, MapContext params);
}