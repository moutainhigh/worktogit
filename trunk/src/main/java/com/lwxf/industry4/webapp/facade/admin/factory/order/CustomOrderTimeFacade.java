package com.lwxf.industry4.webapp.facade.admin.factory.order;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderTime;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2019/9/3 0003 16:41
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomOrderTimeFacade extends BaseFacade {
    RequestResult selectCustoOrderTime(MapContext mapContext);

    RequestResult addCustoOrderTime(MapContext mapContext);

    RequestResult editCustoOrderTime(MapContext mapContext);

    RequestResult deleteCustoOrderTime(String id);

    RequestResult findOrderTimelist(MapContext mapContext, Integer pageNum, Integer pageSize);

}
