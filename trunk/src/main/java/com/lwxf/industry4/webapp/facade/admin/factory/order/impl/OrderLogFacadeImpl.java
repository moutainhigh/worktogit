package com.lwxf.industry4.webapp.facade.admin.factory.order.impl;

import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderLogService;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderLogStage;
import com.lwxf.industry4.webapp.common.enums.order.OrderStage;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.order.OrderLogFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component("orderLogFacade")
public class OrderLogFacadeImpl extends BaseFacadeImpl implements OrderLogFacade {

    @Resource(name = "customOrderLogService")
    private CustomOrderLogService customOrderLogService;

    @Override
    public RequestResult findOrderLogs(String id) {
        String userId= WebUtils.getCurrUserId();
        User byUserId = AppBeanInjector.userService.findByUserId(userId);
        Integer type = byUserId.getType();
        MapContext params=MapContext.newOne();
        if(type!=0){
            params.put("stage", OrderStage.PRESS_FOR_MONEY.getValue());
        }
        params.put("orderId",id);
        return ResultFactory.generateRequestResult(customOrderLogService.findByOrderId(params));
    }
}
