package com.lwxf.industry4.webapp.facade.admin.factory.warehouse.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.commons.utils.LwxfStringUtils;
import com.lwxf.industry4.webapp.bizservice.company.CompanyService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.procurement.PurchaseRequestService;
import com.lwxf.industry4.webapp.bizservice.product.ProductService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StockService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageOutputInItemService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageOutputInService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.storage.StorageOutputInStatus;
import com.lwxf.industry4.webapp.common.enums.storage.StorageOutputInType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.StorageOutputInItemDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.procurement.PurchaseRequest;
import com.lwxf.industry4.webapp.domain.entity.warehouse.Stock;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputIn;
import com.lwxf.industry4.webapp.domain.entity.warehouse.StorageOutputInItem;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.warehouse.StorageOutputInFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/20/020 10:26
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("storageOutputInFacade")
public class StorageOutputInFacadeImpl extends BaseFacadeImpl implements StorageOutputInFacade {
	@Resource(name = "storageOutputInService")
	private StorageOutputInService storageOutputInService;
	@Resource(name = "storageService")
	private StorageService storageService;
	@Resource(name = "productService")
	private ProductService productService;
	@Resource(name = "stockService")
	private StockService stockService;
	@Resource(name = "companyService")
	private CompanyService companyService;
	@Resource(name = "storageOutputInItemService")
	private StorageOutputInItemService storageOutputInItemService;
	@Resource(name = "customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name = "purchaseRequestService")
	private PurchaseRequestService purchaseRequestService;
	@Override
	public RequestResult findStorageOutPutInList(MapContext mapContext,Integer pageNum,Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		Pagination pagination = new Pagination();
		pagination.setPageSize(pageSize);
		pagination.setPageNum(pageNum);
		paginatedFilter.setPagination(pagination);
		List<Map<String,String>> sorts = new ArrayList<Map<String,String>>();
		Map<String,String> created = new HashMap<String,String>();
		created.put(WebConstant.KEY_ENTITY_CREATED,"desc");
		sorts.add(created);
		paginatedFilter.setSorts(sorts);
		PaginatedList<StorageOutputInDto> listByPaginateFilter = this.storageOutputInService.findListByPaginateFilter(paginatedFilter);
		if(listByPaginateFilter!=null){
			if(listByPaginateFilter.getRows().size()>0){
				for(StorageOutputInDto outputInDto:listByPaginateFilter.getRows()){
					Integer type = outputInDto.getType();
					String resId = outputInDto.getResId();
					if(resId!=null&&!resId.equals("")){
						if(type==StorageOutputInType.PURCHASE_WAREHOUSING.getValue()){//如果采购入库，查询采购单名称
							PurchaseRequest byId = this.purchaseRequestService.findById(resId);
							if(byId!=null){
								outputInDto.setPurchaseName(byId.getName());
							}
						}
						if(type==StorageOutputInType.ORDER_WAREHOUSING.getValue()){//如果订单入库（订单返货），查询订单编号，
							CustomOrder byId = this.customOrderService.findById(resId);
							if(byId!=null){
								outputInDto.setPurchaseName(byId.getNo());
							}
						}
					}
				}
			}
		}
		return ResultFactory.generateRequestResult(listByPaginateFilter);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addStorageInput(StorageOutputInDto storageOutputInDto) {
		String branchId=WebUtils.getCurrBranchId();
		//判断编号是否重复
		if(this.storageOutputInService.findOneByNo(storageOutputInDto.getNo(),branchId)!=null){
			Map result = new HashMap<String,String>();
			result.put(WebConstant.STRING_NO,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		//判断仓库是否存在
		if(!this.storageService.isExist(storageOutputInDto.getStorageId())){
			return ResultFactory.generateResNotFoundResult();
		}
		this.storageOutputInService.add(storageOutputInDto);
		//判断产品列表
		for(StorageOutputInItemDto storageOutputInItemDto :storageOutputInDto.getStorageOutputInItemList()){
			storageOutputInItemDto.setOutputInId(storageOutputInDto.getId());
			this.storageOutputInItemService.add(storageOutputInItemDto);
		}
		//判断是否直接入库
		Integer status = storageOutputInDto.getStatus();
		if(status!=null&&status==StorageOutputInStatus.CONFIRM.getValue()){
           //出入单下的商品库存录入
			for(StorageOutputInItemDto storageOutputInItemDto :storageOutputInDto.getStorageOutputInItemList()){
				//判断仓库下是否已存在该产品
				Stock stock = this.stockService.findOneByStorageIdAndProductId(storageOutputInDto.getStorageId(), storageOutputInItemDto.getMaterialId());
				if(stock!=null){
					//设定价格
					storageOutputInItemDto.setPrice(((stock.getPrice().multiply(new BigDecimal(stock.getQuantity())))
							.add(storageOutputInItemDto.getPrice().multiply(new BigDecimal(storageOutputInItemDto.getQuantity()))))
							.divide((new BigDecimal(stock.getQuantity())).add(new BigDecimal(storageOutputInItemDto.getQuantity())),2,BigDecimal.ROUND_UP));

					//修改相对应仓库下的产品数量
					MapContext update = MapContext.newOne();
					update.put(WebConstant.KEY_ENTITY_ID,stock.getId());
					update.put("quantity",stock.getQuantity()+storageOutputInItemDto.getQuantity());
					update.put("price",storageOutputInItemDto.getPrice());
					update.put("location",storageOutputInItemDto.getLocation());
					this.stockService.updateByMapContext(update);
				}else{
					//仓库下生成产品库存
					stock = new Stock();
					stock.setPrice(storageOutputInItemDto.getPrice());
					stock.setStorageId(storageOutputInDto.getStorageId());
					stock.setOperator(WebUtils.getCurrUserId());
					stock.setOperateTime(DateUtil.getSystemDate());
					stock.setColumn(storageOutputInItemDto.getColumn());
					stock.setShelf(storageOutputInItemDto.getShelf());
					stock.setTier(storageOutputInItemDto.getTier());
					stock.setMaterialId(storageOutputInItemDto.getMaterialId());
					stock.setPreOutput(0);
					stock.setQuantity(storageOutputInItemDto.getQuantity());
					stock.setBranchId(WebUtils.getCurrBranchId());
					stock.setLocation(storageOutputInItemDto.getLocation());
					this.stockService.add(stock);
				}
			}
		}
		return ResultFactory.generateRequestResult(storageOutputInDto);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateStorageOutputInStatus(String id, List<MapContext> storageItems) {
		StorageOutputIn output = this.storageOutputInService.findById(id);
		//判断该入库单是否存在
		if(output==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断订单状态是否是未确定
		if(output.getStatus().equals(StorageOutputInStatus.CONFIRM.getValue())){
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext mapContext = MapContext.newOne();
		mapContext.put("id",id);
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		paginatedFilter.setFilters(mapContext);
		PaginatedList<StorageOutputInDto> paginateFilter = this.storageOutputInService.findListByPaginateFilter(paginatedFilter);
		StorageOutputInDto storageOutputInDto = paginateFilter.getRows().get(0);
		//判断出入库单是入库还是出库
		if(output.getType().equals(StorageOutputInType.OUT_STOCK.getValue())){//出库
			//出库单下的商品库存修改
			for(MapContext map:storageItems){
				String materialId=map.getTypedValue("materialId",String.class);
				Integer quantity=map.getTypedValue("quantity",Integer.class);
				//仓库下产品出库
				Stock stock = this.stockService.findOneByStorageIdAndProductId(storageOutputInDto.getStorageId(), materialId);
				MapContext updateStock = new MapContext();
				updateStock.put(WebConstant.KEY_ENTITY_ID,stock.getId());
				updateStock.put("preOutput",stock.getPreOutput()-quantity);
				this.stockService.updateByMapContext(updateStock);
			}
		}else {//入库
			//出入单下的商品库存录入
			for (MapContext map : storageItems) {
				String materialId = map.getTypedValue("materialId", String.class);
				Integer quantity = map.getTypedValue("quantity", Integer.class);
				BigDecimal price = map.getTypedValue("quantity", BigDecimal.class);
				//判断仓库下是否已存在该产品
				Stock stock = this.stockService.findOneByStorageIdAndProductId(storageOutputInDto.getStorageId(), materialId);
				if (stock != null) {
					//设定价格
					BigDecimal divide = (stock.getPrice().multiply(new BigDecimal(stock.getQuantity())))
							.add(price.multiply(new BigDecimal(quantity)))
							.divide((new BigDecimal(stock.getQuantity())).add(new BigDecimal(quantity)), 2, BigDecimal.ROUND_UP);
					//修改相对应仓库下的产品数量
					MapContext update = MapContext.newOne();
					update.put(WebConstant.KEY_ENTITY_ID, stock.getId());
					update.put("quantity", stock.getQuantity() + quantity);
					update.put("price", divide);
					update.put("location", map.getTypedValue("location", String.class));
					this.stockService.updateByMapContext(update);
				} else {
					//仓库下生成产品库存
					stock = new Stock();
					stock.setPrice(price);
					stock.setStorageId(storageOutputInDto.getStorageId());
					stock.setOperator(WebUtils.getCurrUserId());
					stock.setOperateTime(DateUtil.getSystemDate());
					stock.setMaterialId(materialId);
					stock.setPreOutput(0);
					stock.setQuantity(quantity);
					stock.setLocation(map.getTypedValue("location", String.class));
					this.stockService.add(stock);
				}
				this.storageOutputInItemService.updateByMapContext(map);
			}
		}
		//修改订单状态为已确定
		mapContext.put(WebConstant.KEY_ENTITY_STATUS,StorageOutputInStatus.CONFIRM.getValue());
		this.storageOutputInService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(StorageOutputInStatus.CONFIRM.getValue());
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addStorageOutput(StorageOutputInDto storageOutputInDto) {
		String branchId=WebUtils.getCurrBranchId();
		//判断编号是否重复
		if(this.storageOutputInService.findOneByNo(storageOutputInDto.getNo(),branchId)!=null){
			Map result = new HashMap<String,String>();
			result.put(WebConstant.STRING_NO,AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,result);
		}
		//判断仓库是否存在
		if(!this.storageService.isExist(storageOutputInDto.getStorageId())){
			return ResultFactory.generateResNotFoundResult();
		}
		storageOutputInDto.setBranchId(branchId);
		this.storageOutputInService.add(storageOutputInDto);
		//判断产品列表
		for(StorageOutputInItemDto storageOutputInItemDto :storageOutputInDto.getStorageOutputInItemList()){
			//判断库存是否存在
			Stock stock = this.stockService.findOneByStorageIdAndProductId(storageOutputInDto.getStorageId(), storageOutputInItemDto.getMaterialId());
			if(stock==null){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_LACK_OF_STOCK_10063,LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_LACK_OF_STOCK_10063"),0));
			}else if(stock.getQuantity()<storageOutputInItemDto.getQuantity()){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_LACK_OF_STOCK_10063,LwxfStringUtils.format(AppBeanInjector.i18nUtil.getMessage("BIZ_LACK_OF_STOCK_10063"),stock.getQuantity()));
			}
			storageOutputInItemDto.setOutputInId(storageOutputInDto.getId());

			if(storageOutputInDto.getStatus()!=null&&storageOutputInDto.getStatus()==StorageOutputInStatus.UNCERTAIN.getValue()){
				Integer preOutput = stock.getPreOutput();
				if(preOutput==null){
					preOutput=0;
				}
				//修改库存表预出库及库存数据
				MapContext mapContext = MapContext.newOne();
				mapContext.put(WebConstant.KEY_ENTITY_ID,stock.getId());
				mapContext.put("quantity",stock.getQuantity()-storageOutputInItemDto.getQuantity());
				mapContext.put("preOutput",preOutput+storageOutputInItemDto.getQuantity());
				this.stockService.updateByMapContext(mapContext);
			}else if(storageOutputInDto.getStatus()!=null&&storageOutputInDto.getStatus()==StorageOutputInStatus.CONFIRM.getValue()){//直接出库
				//直接修改库存表库存数据
				MapContext mapContext = MapContext.newOne();
				mapContext.put(WebConstant.KEY_ENTITY_ID,stock.getId());
				mapContext.put("quantity",stock.getQuantity()-storageOutputInItemDto.getQuantity());
				this.stockService.updateByMapContext(mapContext);
			}
			this.storageOutputInItemService.add(storageOutputInItemDto);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateStorageOutputItem(String id, String itemId, MapContext mapContext) {
		//判断出入库条目单是否存在
		StorageOutputInItem outputInItem = this.storageOutputInItemService.findById(itemId);
		if(outputInItem==null||!outputInItem.getOutputInId().equals(id)){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,itemId);
		this.storageOutputInItemService.updateByMapContext(mapContext);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult cancelStorageOutput(String id) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		MapContext mapContext = MapContext.newOne();
		mapContext.put(WebConstant.KEY_ENTITY_ID,id);
		paginatedFilter.setFilters(mapContext);
		List<StorageOutputInDto> outputInDtos = this.storageOutputInService.findListByPaginateFilter(paginatedFilter).getRows();
		if(outputInDtos==null||outputInDtos.size()==0||outputInDtos.get(0).getStatus().equals(StorageOutputInStatus.CANCEL.getValue())){
			return ResultFactory.generateResNotFoundResult();
		}
		MapContext updateOutputIn = new MapContext();
		updateOutputIn.put(WebConstant.KEY_ENTITY_ID,id);
		updateOutputIn.put(WebConstant.KEY_ENTITY_STATUS,StorageOutputInStatus.CANCEL.getValue());
		this.storageOutputInService.updateByMapContext(updateOutputIn);
		StorageOutputInDto storageOutputInDto = outputInDtos.get(0);
		if(storageOutputInDto.getType().equals(StorageOutputInType.OUT_STOCK.getValue())){//出库
			for(StorageOutputInItemDto storageOutputInItemDto :storageOutputInDto.getStorageOutputInItemList()){
				MapContext updateStock = new MapContext();
				Stock stock = this.stockService.findOneByStorageIdAndProductId(storageOutputInDto.getStorageId(), storageOutputInItemDto.getMaterialId());
				updateStock.put(WebConstant.KEY_ENTITY_ID,stock.getId());
				updateStock.put("preOutput",stock.getPreOutput()-storageOutputInItemDto.getQuantity());
				updateStock.put("quantity",stock.getQuantity()+storageOutputInItemDto.getQuantity());
				this.stockService.updateByMapContext(updateStock);
			}
		}
		return ResultFactory.generateRequestResult(StorageOutputInStatus.CANCEL.getValue());
	}

}
