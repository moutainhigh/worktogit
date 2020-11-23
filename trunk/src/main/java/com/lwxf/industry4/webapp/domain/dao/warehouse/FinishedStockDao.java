package com.lwxf.industry4.webapp.domain.dao.warehouse;


import java.util.List;
import java.util.Map;


import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.mybatis.utils.MapContext;
import com.lwxf.mybatis.annotation.IBatisSqlTarget;
import com.lwxf.industry4.webapp.domain.dao.base.BaseDao;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;


/**
 * 功能：
 * 
 * @author：F_baisi(F_baisi@163.com)
 * @created：2018-12-17 11:00:40
 * @version：2018 V1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@IBatisSqlTarget
public interface FinishedStockDao extends BaseDao<FinishedStock, String> {

	PaginatedList<FinishedStockDto> selectByFilter(PaginatedFilter paginatedFilter);

	List<FinishedStockItemDto> findListByFinishedStockId(String id);

	FinishedStockDto findOneById(String id);

	List<FinishedStockItem> findUnshippedListById(String id);

    FinishedStock findByOrderId(String orderId);

	List<FinishedStock> findListByStorageId(String id);

	List<FinishedStock> findListByOrderId(String orderId);

    Map findMapByOrderId(String orderId);

	PaginatedList<MapContext> findDispathcBillList(PaginatedFilter paginatedFilter);

	int deleteByOrderId(String orderId);

	List<FinishedStockDto> findWxFinishedList(String orderId);

	MapContext findCountByBranchId(String branchId);
}