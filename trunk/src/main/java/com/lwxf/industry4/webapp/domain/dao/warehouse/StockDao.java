package com.lwxf.industry4.webapp.domain.dao.warehouse;


import java.util.List;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StockDto;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Stock;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-13 15:21:19
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface StockDao extends BaseDao<Stock, String> {

	PaginatedList<Stock> selectByFilter(PaginatedFilter paginatedFilter);

	PaginatedList<StockDto> findListByFilter(PaginatedFilter paginatedFilter);

	Stock findOneByStorageIdAndProductId(String storageId, String productId);

	StockDto findOneById(String id);

	int deleteByIdAndStockId(String id, String stockId);
}