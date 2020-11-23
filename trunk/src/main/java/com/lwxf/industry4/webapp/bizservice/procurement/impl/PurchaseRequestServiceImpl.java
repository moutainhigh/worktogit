package com.lwxf.industry4.webapp.bizservice.procurement.impl;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseRequestDto;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.procurement.PurchaseRequestDao;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseRequestService;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseRequest;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("purchaseRequestService")
public class PurchaseRequestServiceImpl extends BaseServiceImpl<PurchaseRequest, String, PurchaseRequestDao> implements PurchaseRequestService {


	@Resource

	@Override	public void setDao( PurchaseRequestDao purchaseRequestDao) {
		this.dao = purchaseRequestDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PurchaseRequestDto> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<PurchaseRequestDto> selectRequestListByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.selectRequestListByFilter(paginatedFilter);
	}


}