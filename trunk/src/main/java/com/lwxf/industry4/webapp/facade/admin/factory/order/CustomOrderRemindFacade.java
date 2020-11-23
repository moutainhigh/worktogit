package com.lwxf.industry4.webapp.facade.admin.factory.order;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderRemindDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderRemind;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：订单催款接口
 *
 * @author：DJL
 * @create：2020/1/6 11:11
 * @version：2020 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface CustomOrderRemindFacade extends BaseFacade {

    RequestResult selectByCondition(Integer pageNum, Integer pageSize, MapContext params, String uid, String bid, String cid);

    RequestResult addCustomOrderRemind(CustomOrderRemind customOrderRemind);

    RequestResult updateCustomOrderRemind(String orderId, MapContext mapContext);

    RequestResult receiveRemindOrder(String id);

    void createRemindOrder(CustomOrder customOrder);

    RequestResult completeRemindOrder(String id);

    RequestResult selectByOrderId(String orderId);

    RequestResult saveRemindOrderReceive(String orderId, MapContext mapContext);

    CustomOrderRemindDto selectDtoByOrderId(String orderId);


    RequestResult updateBack(String orderId);
}
