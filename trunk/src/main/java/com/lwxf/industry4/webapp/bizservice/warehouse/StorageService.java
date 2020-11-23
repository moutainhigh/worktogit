package com.lwxf.industry4.webapp.bizservice.warehouse;


import com.lwxf.industry4.webapp.bizservice.base.BaseService;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Storage;

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
public interface StorageService extends BaseService <Storage, String> {

	PaginatedList<StorageDto> selectByFilter(PaginatedFilter paginatedFilter);

	Storage findOneByName(String name,String branchId);

	StorageDto findOneById(String id);

	List<StockDto> findAllProduct();



	Map findByOrderId(String orderId);
}