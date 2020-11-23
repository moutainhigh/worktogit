package com.lwxf.industry4.webapp.facade.admin.factory.engineeringOrder;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.engineeringOrder.EngineeringDto;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;

/**
 * 功能：
 *
 * @author：SunXianWei(17838625030@163.com)
 * @create：2020-05-28 9:10
 * @version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */

public interface EngineeringOrderFacade extends BaseFacade {
    RequestResult findEngineeringOrderList(Integer pageSize, Integer pageNum, MapContext mapContext);

    RequestResult addEngineeringOrder(EngineeringDto engineeringDto);

    RequestResult editEngineeringOrder(String id, MapContext mapContext);

    RequestResult engineeringOrderInfo(String id);

    RequestResult deleteEngineeringOrder(String id);
}
