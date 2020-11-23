package com.lwxf.industry4.webapp.bizservice.warehouse.impl;


import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dao.warehouse.StorageDao;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Storage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-13 15:21:18
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("storageService")
public class StorageServiceImpl extends BaseServiceImpl<Storage, String, StorageDao> implements StorageService {


	@Resource

	@Override	public void setDao( StorageDao storageDao) {
		this.dao = storageDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<StorageDto> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public Storage findOneByName(String name,String branchId) {
		return this.dao.findOneByName(name,branchId);
	}

	@Override
	public StorageDto findOneById(String id) {
		return this.dao.findOneById(id);
	}

	@Override
	public List<StockDto> findAllProduct() {
		return this.dao.findAllProduct();
	}




	@Override
	public Map findByOrderId(String orderId) {
		return this.dao.findByOrderId(orderId);
	}
}