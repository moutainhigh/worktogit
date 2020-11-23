package com.lwxf.industry4.webapp.facade.admin.factory.warehouse.impl;

import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StockService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageOutputInItemService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInDto;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Stock;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.warehouse.StockFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/14/014 16:56
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("stockFacade")
public class StockFacadeImpl extends BaseFacadeImpl implements StockFacade {
	@Resource(name = "stockService")
	private StockService stockService;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "storageService")
	private StorageService storageService;
	@Resource(name = "storageOutputInItemService")
	private StorageOutputInItemService storageOutputInItemService;
	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addStock(Stock stock) {
		//判断仓库是否存在
		if(!this.storageService.isExist(stock.getStorageId())){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断产品是否存在
		if(!this.productService.isExist(stock.getMaterialId())){
			return ResultFactory.generateResNotFoundResult();
		}
		Stock oldStock = this.stockService.findOneByStorageIdAndProductId(stock.getStorageId(),stock.getMaterialId());
		if(oldStock!=null){
			MapContext update = MapContext.newOne();
			update.put(WebConstant.KEY_ENTITY_ID,oldStock.getId());
			update.put("price",stock.getPrice());
			update.put("quantity",stock.getQuantity()+oldStock.getQuantity());
			this.stockService.updateByMapContext(update);
			return ResultFactory.generateRequestResult(this.stockService.findOneById(oldStock.getId()));
		}else{
			this.stockService.add(stock);
		}
		return ResultFactory.generateRequestResult(this.stockService.findOneById(stock.getId()));
	}

	@Override
	public RequestResult findStockList(MapContext mapContext,Integer pageNum,Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put("branchId", WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String,String>> sorts = new ArrayList<Map<String, String>>();
		Map<String,String> created = new HashMap<String, String>();
		created.put("operate_time","desc");
		sorts.add(created);
		paginatedFilter.setSorts(sorts);
		PaginatedList<StockDto> stockDtos = this.stockService.findListByFilter(paginatedFilter);
		return ResultFactory.generateRequestResult(stockDtos);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateStock(String id, String stockId, MapContext mapContext) {
		//判断仓库是否存在
		if(!this.storageService.isExist(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断该条数据是否存在
		if(!this.stockService.isExist(stockId)){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,stockId);
		this.stockService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.stockService.findOneById(stockId));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteById(String id, String stockId) {
		//判断仓库是否存在
		if(!this.storageService.isExist(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		//仓库下删除该产品
		this.stockService.deleteByIdAndStockId(id,stockId);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateColumn(String id,List<Stock> stockList) {
		//判断仓库是否存在
		if(!this.storageService.isExist(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		for(Stock stockInfo :stockList){
			//判断出库量是否是大于库存
			Stock stock = this.stockService.findById(stockInfo.getId());
			if(stock==null){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_LACK_OF_STOCK_10063,LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_LACK_OF_STOCK_10063"),0));
			}else if(!stock.getStorageId().equals(id)||stock.getQuantity()<stockInfo.getQuantity()){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_LACK_OF_STOCK_10063,LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_LACK_OF_STOCK_10063"),stock.getQuantity()));
			}
			//如果是全部取出 就删除这条数据
			if(stock.getQuantity()-stockInfo.getQuantity()==0){
				this.stockService.deleteByIdAndStockId(id,stockInfo.getId());
			}else{
				MapContext update = MapContext.newOne();
				update.put(WebConstant.KEY_ENTITY_ID,stockInfo.getId());
				update.put("quantity",stock.getQuantity()-stockInfo.getQuantity());
				this.stockService.updateByMapContext(update);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findInAndOutListBypid(Integer pageNum, Integer pageSize, String materialId) {
		MapContext mapContext=MapContext.newOne();
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put("materialId", materialId);
		paginatedFilter.setFilters(mapContext);
		PaginatedList<StorageOutputInDto> listByFilter = this.storageOutputInItemService.findInAndOutListBypid(paginatedFilter);
		return  ResultFactory.generateRequestResult(listByFilter);
	}
}
