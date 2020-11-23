package com.lwxf.industry4.webapp.facade.admin.factory.order;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderOfferDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.OfferItem;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderOffer;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

/**
 * 功能：
 *
 * @author：Administrator
 * @create：2019/7/8/008 16:48
 * @version：2019 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OrderOfferFacade extends BaseFacade {

	RequestResult addOffer(String orderId, OrderOfferDto orderOfferDto);

	RequestResult updateOffer(String orderId, MapContext update);

	RequestResult addOfferItem(String orderId, OfferItem offerItem);

	RequestResult updateOfferItem(String orderId,String itemId, MapContext update);

	RequestResult deleteOfferItem(String orderId, String itemId);

	RequestResult findOfferPrintTableInfo(String id);
}
