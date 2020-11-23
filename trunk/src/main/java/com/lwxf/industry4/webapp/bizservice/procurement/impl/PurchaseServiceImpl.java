package com.lwxf.industry4.webapp.bizservice.procurement.impl;


import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.procurment.PurchaseStatus;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.Arrays;
import java.util.List;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.procurement.PurchaseDao;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseService;
import com.lwxf.industry4.webapp.domain.dao.procurement.PurchaseProductDao;
import com.lwxf.industry4.webapp.domain.dto.procurement.PurchaseDto;
import com.lwxf.industry4.webapp.domain.entity.procurement.Purchase;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseProduct;
import com.lwxf.mybatis.utils.MapContext;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("purchaseService")
public class PurchaseServiceImpl extends BaseServiceImpl<Purchase, String, PurchaseDao> implements PurchaseService {


	@Resource

	@Override	public void setDao( PurchaseDao purchaseDao) {
		this.dao = purchaseDao;
	}

	@Resource(name = "purchaseProductDao")
	private PurchaseProductDao purchaseProductDao;
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<PurchaseDto> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		PaginatedList<PurchaseDto> purchaseDtoPaginatedList = this.dao.selectByFilter(paginatedFilter);
//		for(PurchaseDto purchaseDto :purchaseDtoPaginatedList.getRows()){
//			purchaseDto.setPurchaseProductDtoList(this.purchaseProductDao.selectListByPurchaseId(purchaseDto.getId()));
//		}
		return purchaseDtoPaginatedList;
	}

	@Override
	public PurchaseDto findOneById(String id) {
		PurchaseDto purchaseDto = this.dao.findOneById(id);
		//purchaseDto.setPurchaseProductDtoList(this.purchaseProductDao.selectListByPurchaseId(id));
		return purchaseDto;
	}

	@Override
	public int updateStatusById(String id) {
//		List<PurchaseProduct> purchaseProducts = this.purchaseProductDao.findListByStatusAndPurchaseId(Arrays.asList(0,1,2),id);
//		MapContext mapContext = new MapContext();
//		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
//		if(purchaseProducts.size()==0){
//			mapContext.put(WebConstant.KEY_ENTITY_STATUS,PurchaseStatus.ALL_WAREHOUSING.getValue());
//		}else{
//			mapContext.put(WebConstant.KEY_ENTITY_STATUS,PurchaseStatus.PARTIAL_WAREHOUSING.getValue());
//		}
//		this.dao.updateByMapContext(mapContext);
		return 0;
	}

}