package com.lwxf.industry4.webapp.bizservice.warehouse.impl;


import java.util.List;
import java.util.Map;


import org.springframework.stereotype.Component;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StockDto;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.lwxf.industry4.webapp.bizservice.base.BaseServiceImpl;
import com.lwxf.industry4.webapp.domain.dao.warehouse.StockDao;
import com.lwxf.industry4.webapp.bizservice.warehouse.StockService;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Stock;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-13 15:21:19
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Service("stockService")
public class StockServiceImpl extends BaseServiceImpl<Stock, String, StockDao> implements StockService {


	@Resource

	@Override	public void setDao( StockDao stockDao) {
		this.dao = stockDao;
	}


	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public PaginatedList<Stock> selectByFilter(PaginatedFilter paginatedFilter) {
		//
		return this.dao.selectByFilter(paginatedFilter) ;
	}

	@Override
	public PaginatedList<StockDto> findListByFilter(PaginatedFilter paginatedFilter) {
		return this.dao.findListByFilter(paginatedFilter);
	}

	@Override
	public Stock findOneByStorageIdAndProductId(String storageId, String productId) {
		return this.dao.findOneByStorageIdAndProductId(storageId,productId);
	}

	@Override
	public StockDto findOneById(String id) {
		return this.dao.findOneById(id);
	}

	@Override
	public int deleteByIdAndStockId(String id, String stockId) {
		return this.dao.deleteByIdAndStockId(id,stockId);
	}

}