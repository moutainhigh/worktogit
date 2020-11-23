package com.lwxf.industry4.webapp.bizservice.procurement;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseRequestDto;
import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseRequest;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface PurchaseRequestService extends BaseService <PurchaseRequest, String> {

	PaginatedList<PurchaseRequestDto> selectByFilter(PaginatedFilter paginatedFilter);


	PaginatedList<PurchaseRequestDto> selectRequestListByFilter(PaginatedFilter paginatedFilter);
}