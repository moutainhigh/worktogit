package com.lwxf.industry4.webapp.bizservice.customorder;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.entity.customorder.OrderProductAttribute;

import java.util.List;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2020-07-29 10:35:09
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface OrderProductAttributeService extends BaseService <OrderProductAttribute, String> {

	PaginatedList<OrderProductAttribute> selectByFilter(PaginatedFilter paginatedFilter);

    List<OrderProductAttribute> findListByProductId(String productId);
}