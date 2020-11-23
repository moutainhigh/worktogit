package com.lwxf.industry4.webapp.bizservice.company.impl;


import javax.annotation.Resource;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.company.DealerShopService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.company.DealerShopDao;
import com.lwxf.industry4.webapp.domain.dto.company.dealerShop.DealerShopDto;
import com.lwxf.industry4.webapp.domain.entity.company.DealerShop;


/**
 * 功能：
 * 
 * @author：SunXianWei(17838625030@163.com)
 * @created：2019-03-25 11:36:55
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("dealerShopService")
public class DealerShopServiceImpl extends BaseServiceImpl<DealerShop, String, DealerShopDao> implements DealerShopService {


	@Resource

	@Override	public void setDao( DealerShopDao dealerShopDao) {
		this.dao = dealerShopDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<DealerShop> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public DealerShopDto findDealerShopById(String dealerShopId) {
		return this.dao.findDealerShopById(dealerShopId);
	}

	@Override
	public List<DealerShop> findShopList(String address) {
		return this.dao.findShopList(address);
	}

	@Override
	public int add(DealerShop entity) {
		return this.dao.insert(entity);
	}
}