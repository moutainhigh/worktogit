package com.lwxf.industry4.webapp.bizservice.customorder;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderOffer;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@126.com)
 * @created：2019-07-01 14:20:03
 * @version：2019 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OrderOfferService extends BaseService <OrderOffer, String> {

	PaginatedList<OrderOffer> selectByFilter(PaginatedFilter paginatedFilter);

	OrderOffer findByOrerId(String orderId);
}