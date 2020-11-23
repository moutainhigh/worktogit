package com.lwxf.industry4.webapp.facade.admin.factory.order;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/4/8/008 15:56
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface ProduceOrderFacade extends BaseFacade {
	RequestResult findCoordinationCount(String branchId);
	RequestResult findProduceOrderOverview();

	RequestResult findCoordinationPrintInfo(String id);

	RequestResult findProductList(Integer pageNum, Integer pageSize, MapContext mapContext);

	RequestResult updateOrderProduct(String productIds);

	RequestResult countTopProduct();

	RequestResult cancelOrderProduct(String productId);

	RequestResult addProduceDelayReason(String id, MapContext mapContext);

	RequestResult produceComplete(String id, MapContext mapContext);

	RequestResult findCoordinationList(MapContext mapContext, Integer pageNum, Integer pageSize);

    RequestResult updateCoordinationProduce(String produceIds);

	RequestResult cancelCoordinationProduce(String produceIds);

	RequestResult endCoordinationProduce(String produceIds);
}
