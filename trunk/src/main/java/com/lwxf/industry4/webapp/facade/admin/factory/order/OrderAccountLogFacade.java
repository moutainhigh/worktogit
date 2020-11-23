package com.lwxf.industry4.webapp.facade.admin.factory.order;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderAccountLog;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2019/3/5/005 15:26
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OrderAccountLogFacade extends BaseFacade {
	RequestResult findOrderList(MapContext mapContext, Integer pageNum, Integer pageSize);

	RequestResult updateOrderAccount(String id,Integer type,OrderAccountLog orderAccountLog);
}
