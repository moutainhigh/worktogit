package com.lwxf.industry4.webapp.facade.admin.factory.warehouse;

import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Stock;
import com.lwxf.industry4.webapp.facade.base.BaseFacade;
import com.lwxf.mybatis.utils.MapContext;

import java.util.List;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/14/014 16:56
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
public interface StockFacade extends BaseFacade {
	RequestResult addStock(Stock stock);

	RequestResult findStockList(MapContext mapContext,Integer pageNum,Integer pageSize);

	RequestResult updateStock(String id, String stockId, MapContext mapContext);

	RequestResult deleteById(String id, String stockId);

	RequestResult updateColumn(String id,List<Stock> stockList);


	RequestResult findInAndOutListBypid(Integer pageNum, Integer pageSize, String materialId);
}
