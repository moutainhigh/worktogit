package com.lwxf.industry4.webapp.bizservice.procurement.impl;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.procurement.PurchaseProductDao;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseProductService;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseProductDto;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseProduct;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("purchaseProductService")
public class PurchaseProductServiceImpl extends BaseServiceImpl<PurchaseProduct, String, PurchaseProductDao> implements PurchaseProductService {


	@Resource

	@Override	public void setDao( PurchaseProductDao purchaseProductDao) {
		this.dao = purchaseProductDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PurchaseProduct> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public List<PurchaseProductDto> selectProductsByRequestId(String id) {
		return this.dao.selectProductsByRequestId(id);
	}


}