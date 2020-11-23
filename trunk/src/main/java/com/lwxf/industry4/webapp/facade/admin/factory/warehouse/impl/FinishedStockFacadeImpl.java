package com.lwxf.industry4.webapp.facade.admin.factory.warehouse.impl;

import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.customorder.*;
import com.lwxf.industry4.webapp.bizservice.dealer.DealerLogisticsService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillPlanItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillPlanService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.bizservice.system.LogisticsCompanyService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockItemService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockService;
import com.lwxf.industry4.webapp.bizservice.warehouse.StorageService;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesCategory;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesStatus;
import com.lwxf.industry4.webapp.common.enums.customorder.CustomOrderFilesType;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderState;
import com.lwxf.industry4.webapp.common.enums.dispatch.DispatchBillStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderStage;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.enums.order.ProduceOrderType;
import com.lwxf.industry4.webapp.common.enums.storage.DispatchBillPlanItemStatus;
import com.lwxf.industry4.webapp.common.enums.storage.DispatchBillPlanStatus;
import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockStatus;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.excel.BaseExportExcelUtil;
import com.lwxf.industry4.webapp.common.utils.excel.ExcelParam;
import com.lwxf.industry4.webapp.common.utils.excel.impl.DispatchBillPlanItemParam;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.dealer.WxDealerLogisticsDto;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.DispatchBillPlanDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockDto;
import com.lwxf.industry4.webapp.domain.dto.warehouse.FinishedStockItemDto;
import com.lwxf.industry4.webapp.domain.entity.customorder.*;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillItem;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlan;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillPlanItem;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStock;
import com.lwxf.industry4.webapp.domain.entity.warehouse.FinishedStockItem;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.warehouse.FinishedStockFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/24/024 13:47
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("finishedStockFacade")
public class FinishedStockFacadeImpl extends BaseFacadeImpl implements FinishedStockFacade {
	@Resource(name = "finishedStockService")
	private FinishedStockService finishedStockService;
	@Resource(name="customOrderService")
	private CustomOrderService customOrderService;
	@Resource(name="produceOrderService")
	private ProduceOrderService produceOrderService;
	@Resource(name="orderProductService")
	private OrderProductService orderProductService;
	@Resource(name = "storageService")
	private StorageService storageService;
	@Resource(name = "finishedStockItemService")
	private FinishedStockItemService finishedStockItemService;
	@Resource(name = "dispatchBillService")
	private DispatchBillService dispatchBillService;
	@Resource(name = "dispatchBillPlanService")
	private DispatchBillPlanService dispatchBillPlanService;
	@Resource(name = "dispatchBillPlanItemService")
	private DispatchBillPlanItemService dispatchBillPlanItemService;
	@Resource(name = "customOrderFilesService")
	private CustomOrderFilesService customOrderFilesService;
	@Resource(name = "customOrderLogService")
	private CustomOrderLogService customOrderLogService;
	@Resource(name = "dealerLogisticsService")
	private DealerLogisticsService dealerLogisticsService;
	@Resource(name = "logisticsCompanyService")
	private LogisticsCompanyService logisticsCompanyService;
	@Resource(name = "basecodeService")
	private BasecodeService basecodeService;
	@Resource(name = "dispatchBillItemService")
	private DispatchBillItemService dispatchBillItemService;
	@Resource(name = "paymentService")
	private PaymentService paymentService;

	@Override
	public RequestResult findFinishedDto(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String,String>> sorts=  new ArrayList<Map<String,String>>();
//		Map<String,String> orderId = new HashMap<String,String>();
//		orderId.put("orderId","desc");
//		sorts.add(orderId);
		Map<String,String> created = new HashMap<String,String>();
		created.put("created","desc");
		sorts.add(created);
		Map<String,String> id = new HashMap<String,String>();
		id.put("id","desc");
		sorts.add(id);
		paginatedFilter.setSorts(sorts);
		return ResultFactory.generateRequestResult(this.finishedStockItemService.findListByFilter(paginatedFilter));
	}
	@Override
	public RequestResult findProductsDto(MapContext mapContext, Integer pageNum, Integer pageSize) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String,String>> sorts=  new ArrayList<Map<String,String>>();
//		Map<String,String> orderId = new HashMap<String,String>();
//		orderId.put("orderId","desc");
//		sorts.add(orderId);
		Map<String,String> created = new HashMap<String,String>();
		created.put("created","desc");
		sorts.add(created);
		Map<String,String> id = new HashMap<String,String>();
		id.put("id","desc");
		sorts.add(id);
		paginatedFilter.setSorts(sorts);
		return ResultFactory.generateRequestResult(this.orderProductService.selectDtoByFilter(paginatedFilter));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addFinishedStock(FinishedStockDto finishedStockDto) {
		//判断订单是否存在
		CustomOrder customOrder = this.customOrderService.findById(finishedStockDto.getOrderId());
		if(customOrder==null||!customOrder.getNo().equals(finishedStockDto.getOrderNo())){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断订单是否已存在成品库单 如果已存在则不允许新建
		FinishedStock finishedStock = this.finishedStockService.findByOrderId(finishedStockDto.getOrderId());
		if(finishedStock!=null){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		//判断仓库是否存在
		if(!this.storageService.isExist(finishedStockDto.getStorageId())){
			return ResultFactory.generateResNotFoundResult();
		}

		//验证包装编号不允许重复
		Set set = new HashSet<String>();
		for(FinishedStockItemDto finishedStockItemDto:finishedStockDto.getFinishedStockItemDtos()){
			set.add(finishedStockItemDto.getBarcode());
		}
		if(set.size()!=finishedStockDto.getFinishedStockItemDtos().size()||this.finishedStockItemService.findListByBarcodes(set).size()!=0){
			Map res = new HashMap<String,String>();
			res.put("barcode",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,res);
		}
		this.finishedStockService.add(finishedStockDto);
		for(FinishedStockItemDto finishedStockItemDto:finishedStockDto.getFinishedStockItemDtos()){
			finishedStockItemDto.setFinishedStockId(finishedStockDto.getId());
			this.finishedStockItemService.add(finishedStockItemDto);
		}
		//记录操作日志
		CustomOrderLog log = new CustomOrderLog();
		log.setCreated(new Date());
		log.setCreator(WebUtils.getCurrUserId());
		log.setName("订单打包中");
		log.setStage(OrderStage.WAREHOUSING.getValue());
		log.setContent("订单号："+customOrder.getNo()+" 中的产品正在打包中");
		log.setCustomOrderId(customOrder.getId());
		customOrderLogService.add(log);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addFinishedItem(FinishedStockItem finishedStockItem, String id,String storageId) {
		FinishedStock finishedStock = this.finishedStockService.findById(id);
		//判断成品库单是否存在以及是否是对应仓库下的
		if(finishedStock==null||!finishedStock.getStorageId().equals(storageId)){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断成品库单包数是否达到预期值 达到则不允许新增
		List<FinishedStockItemDto> listByFinishedStockId = this.finishedStockService.findListByFinishedStockId(id);
		if(listByFinishedStockId.size()==finishedStock.getPackages()){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_RESOURCES_LIMIT_10076,AppBeanInjector.i18nUtil.getMessage("BIZ_RESOURCES_LIMIT_10076"));
		}
		//判断编号是否重复
		if(this.finishedStockItemService.findListByBarcodes(new HashSet(Arrays.asList(finishedStockItem.getBarcode()))).size()!=0){
			Map res = new HashMap<String,String>();
			res.put("barcodes",AppBeanInjector.i18nUtil.getMessage("VALIDATE_NOT_ALLOWED_REPEAT"));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,res);
		}
		this.finishedStockItemService.add(finishedStockItem);
		return ResultFactory.generateRequestResult(this.finishedStockItemService.findOneById(finishedStockItem.getId()));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateFinishedStock(String productId, List<MapContext> mapContexts,String status,String notes,String queryInstock) {
		//查询订单产品是否存在
		OrderProductDto orderproductDto=this.orderProductService.findOneById(productId);
		if(orderproductDto==null){
			return ResultFactory.generateResNotFoundResult();
		}
		String orderId=orderproductDto.getCustomOrderId();
		//判断是否需要五金快捷入库
		if(queryInstock!=null&&!queryInstock.equals("")){
			//五金快捷入库：同一订单下的产品的五金生产单（本产品除外），包裹数全设置为0
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			for(OrderProductDto orderProductDto:listByOrderId){
				if(!productId.equals(orderProductDto.getId())){
					Integer type=ProduceOrderType.HARDWARE.getValue();
					List<ProduceOrder> produces = produceOrderService.findListByProductIdAndType(orderProductDto.getId(),type);
					Integer wujinCount=0;
					for(ProduceOrder produceOrder:produces) {
						MapContext map = MapContext.newOne();
						map.put("id", produceOrder.getId());
						map.put("count", 0);
						map.put("inputStatus",1);
						this.produceOrderService.updateByMapContext(map);
						if(produceOrder.getCount()!=null&&!produceOrder.getCount().equals("")) {
							wujinCount = produceOrder.getCount();
						}
					}
					//其他产品原有的包裹总数减去五金数
					if(orderProductDto.getStockCount()!=null&&!orderProductDto.getStockCount().equals("")) {
						MapContext mapContext = MapContext.newOne();
						mapContext.put("id", orderProductDto.getId());
						mapContext.put("stockCount", orderProductDto.getStockCount() - wujinCount);
						this.orderProductService.updateByMapContext(mapContext);
					}
				}
			}

		}
		List<ProduceOrder> po = produceOrderService.findListByProductId(productId);
		for(MapContext map:mapContexts){
			String id=map.getTypedValue("id",String.class);
			ProduceOrderDto oneById = this.produceOrderService.findOneById(id);
			Integer count = oneById.getCount();
			if(count==null||count.equals("")){
				count=0;
			}
			int thiscount=0;
			int hascount=0;
			//如果是完成入库
			//更新生产单状态
			if(map.getTypedValue("inputStatus",Integer.class)==1) {//完成入库，入库数修改
				map.put("state", ProduceOrderState.INPUT_STORAGE.getValue());
				thiscount= map.getTypedValue("count", Integer.class) ;//本次入库数
				count=thiscount;
			}else if(map.getTypedValue("inputStatus",Integer.class)==0){//部分入库，入库数累加
				map.put("state", ProduceOrderState.IN_PRODUCTION.getValue());
				thiscount= map.getTypedValue("count", Integer.class) ;//本次入库数
				hascount=thiscount+count;//总入库数
				count=hascount;
			}
			map.put("count",count);
			map.put("deliverStatus", 0);
			map.put("shipped", 0);
			produceOrderService.updateByMapContext(map);
		}
		if(notes!=null&&!notes.equals("")){
			MapContext map=MapContext.newOne();
			map.put("packgeNotes",notes);
			map.put("id",productId);
			orderProductService.updateByMapContext(map);
		}
		MapContext hasInputCount = this.produceOrderService.findHasInPutCount(productId);
		Integer inCount=this.produceOrderService.findByProductIdAndStockCount(productId);//查询已入库的生产单数量
		if(inCount==po.size()){
			//生成订单日志
			OrderProduct op = orderProductService.findOneById(productId);
			if(op.getStatus()!=OrderProductStatus.TO_DELIVERY.getValue()) {
				CustomOrderLog log = new CustomOrderLog();
				log.setCreated(new Date());
				log.setCreator(WebUtils.getCurrUserId());
				log.setName("入库");
				log.setStage(OrderStage.WAREHOUSING.getValue());
				log.setContent("产品编号：" + op.getNo() + "已入库");
				log.setCustomOrderId(op.getCustomOrderId());
				customOrderLogService.add(log);
			}
			//修改产品状态
			MapContext map = MapContext.newOne();
			map.put("id",productId);
			map.put("stockInput",new Date());
			map.put("stockCount",hasInputCount.getTypedValue("countInStock", Integer.class));
			map.put("partStock",0);
			map.put("stockInputCreator",WebUtils.getCurrUserId());
			map.put("status", OrderProductStatus.TO_DELIVERY.getValue());
			map.put("planDeliveryVerifyTime",new Date());
			orderProductService.updateByMapContext(map);
			//如果订单下产品都是待发货，则修改订单状态为待发货
			List<OrderProductDto> listByOrderId = this.orderProductService.findListByOrderId(orderId);
			int statusNum=0;//记录状态为待发货的产品数
			for(OrderProductDto productDto:listByOrderId){
				Integer status1 = productDto.getStatus();
				if(status1==OrderProductStatus.TO_DELIVERY.getValue()){
					statusNum +=1;
				}
			}
            if(statusNum==listByOrderId.size()){
            	MapContext map1=MapContext.newOne();
            	map1.put("id",orderId);
            	map1.put("status", OrderStatus.TO_SHIPPED.getValue());
            	this.customOrderService.updateByMapContext(map1);
			}
		}else {//更新产品为部分入库
			MapContext mapContext=MapContext.newOne();
			mapContext.put("stockCount",hasInputCount.getTypedValue("countInStock", Integer.class));
			mapContext.put("partStock",1);
			mapContext.put("id",productId);
			this.orderProductService.updateByMapContext(mapContext);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult verifyDelivery(String productIds) {
		String[] arr = productIds.split(",");
		for(String id : arr){
			MapContext map = MapContext.newOne();
			map.put("id",id);
			map.put("status",OrderProductStatus.TO_DELIVERY.getValue());
			map.put("planDeliveryVerifyTime",new Date());
			orderProductService.updateByMapContext(map);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult countVerifyProducts() {
		MapContext map = MapContext.newOne();
		map.put("status",OrderProductStatus.HAS_STOCK.getValue());
		map.put("branchId",WebUtils.getCurrBranchId());
		return ResultFactory.generateRequestResult(orderProductService.countProductsByStatus(map));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteFinishedStockById(String id,String storageId) {
		FinishedStock finishedStock = this.finishedStockService.findById(id);
		//判断成品库单是否存在
		if(finishedStock==null||!finishedStock.getStorageId().equals(storageId)){
			return ResultFactory.generateResNotFoundResult();
		}
		//判断成品库单是否已发货
		if(!finishedStock.getStatus().equals(FinishedStockStatus.UNSHIPPED.getValue())){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_DELIVERED_NOT_OPERATE_10082,AppBeanInjector.i18nUtil.getMessage("BIZ_DELIVERED_NOT_OPERATE_10082"));
		}
		//判断成品库下的包裹 是否已有创建配送计划的 有 则不允许删除
		List<DispatchBillDto> dispatchsByOrderId = this.dispatchBillService.findDispatchsByOrderId(finishedStock.getOrderId());
		if(dispatchsByOrderId.size()!=0){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_DELIVERED_NOT_OPERATE_10082,AppBeanInjector.i18nUtil.getMessage("BIZ_DELIVERED_NOT_OPERATE_10082"));
		}
		this.finishedStockItemService.deleteByFinishedStockId(id);
		this.finishedStockService.deleteById(id);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult updateItemById(String itemId,MapContext mapContext) {
		FinishedStockItem finishedStockItem = this.finishedStockItemService.findById(itemId);
		//判断item是否存在
		if(finishedStockItem==null){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,itemId);
		this.finishedStockItemService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.finishedStockItemService.findOneById(itemId));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deleteByItemId(String id, String itemId,String storageId) {
		//判断成品库单是否存在
		FinishedStock finishedStock = this.finishedStockService.findById(id);
		if(finishedStock==null||!finishedStock.getStorageId().equals(storageId)){
			return ResultFactory.generateResNotFoundResult();
		}
		FinishedStockItem finishedStockItem = this.finishedStockItemService.findById(itemId);
		//判断item是否存在
		if(finishedStockItem==null){
			return ResultFactory.generateSuccessResult();
		}
		//判断是否已发货
		if(finishedStockItem.getDelivered()!=null){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_DELIVERED_NOT_OPERATE_10082,AppBeanInjector.i18nUtil.getMessage("BIZ_DELIVERED_NOT_OPERATE_10082"));
		}
		this.finishedStockItemService.deleteById(itemId);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult itemWarehousing(String itemId,MapContext mapContext) {
		FinishedStockItem finishedStockItem = this.finishedStockItemService.findById(itemId);
		//判断item是否存在
		if(finishedStockItem==null){
			return ResultFactory.generateResNotFoundResult();
		}
		mapContext.put(WebConstant.KEY_ENTITY_ID,itemId);
		mapContext.put("ins",true);
		this.finishedStockItemService.updateByMapContext(mapContext);
		return ResultFactory.generateRequestResult(this.finishedStockItemService.findOneById(itemId));
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDispatchPlan(DispatchBillPlanDto dispatchBillPlanDto) {
		//判断是否存在还未完成的配送计划，如果存在，则不允许再创建
		String branchId=WebUtils.getCurrBranchId();
		DispatchBillPlan dispatchBillPlan=this.dispatchBillPlanService.findByStatusAndBranchId(0,branchId);
		if(dispatchBillPlan!=null){
			return ResultFactory.generateErrorResult("500","有未完成的发货计划");
		}
		dispatchBillPlanDto.setBranchId(branchId);
		dispatchBillPlanDto.setCreated(DateUtil.getSystemDate());
		dispatchBillPlanDto.setCreator(WebUtils.getCurrUserId());
		dispatchBillPlanDto.setNum(dispatchBillPlanDto.getDispatchBillPlanItems().size());
		dispatchBillPlanDto.setStatus(DispatchBillPlanStatus.INCOMPLETE.getValue());
        dispatchBillPlanDto.setAuditStatus(1);
        dispatchBillPlanDto.setActualNum(0);
		this.dispatchBillPlanService.add(dispatchBillPlanDto);
		//发货计划明细
		Set products=new HashSet();
		MapContext mapContext=MapContext.newOne();
		for(DispatchBillPlanItem dispatchBillPlanItem:dispatchBillPlanDto.getDispatchBillPlanItems()){
			dispatchBillPlanItem.setStatus(DispatchBillPlanItemStatus.UNSHIPPED.getValue());//加入计划的都默认未发货
			dispatchBillPlanItem.setDispatchBillPlanId(dispatchBillPlanDto.getId());
			String produceId = dispatchBillPlanItem.getProduceId();
			int i=0;
			if(produceId!=null&&!produceId.equals("")){
				ProduceOrderDto oneById = this.produceOrderService.findOneById(produceId);
				if(oneById!=null){
					Integer count = oneById.getCount();
					Integer hasDeliverCount = oneById.getHasDeliverCount();
					if(hasDeliverCount==null||hasDeliverCount.equals("")){
						hasDeliverCount=0;
					}
					i = count - hasDeliverCount;
				}
			}
			dispatchBillPlanItem.setProduceNum(i);
			this.dispatchBillPlanItemService.add(dispatchBillPlanItem);
			//产品数量（计划发货数量）
			String productId=dispatchBillPlanItem.getOrderProductId();
            products.add(productId);
            //修改生产单
			mapContext.put("id",dispatchBillPlanItem.getProduceId());
			mapContext.put("shipped",1);
			this.produceOrderService.updateByMapContext(mapContext);
		}
		//更新发货单产品数
		MapContext mapContext1=MapContext.newOne();
		mapContext1.put("id",dispatchBillPlanDto.getId());
		mapContext1.put("num",products.size());
		this.dispatchBillPlanService.updateByMapContext(mapContext1);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult uploadPackageFiles(String id, String itemId, List<MultipartFile> multipartFileList) {
		CustomOrderFiles customOrderFiles = new CustomOrderFiles();
		if(itemId.equals("itemId")){//如果是创建包裹时上传文件
			customOrderFiles.setStatus(CustomOrderFilesStatus.TEMP.getValue());
		}else{
			customOrderFiles.setStatus(CustomOrderFilesStatus.FORMAL.getValue());
			customOrderFiles.setBelongId(itemId);
		}
		customOrderFiles.setCustomOrderId(id);
		customOrderFiles.setCategory(CustomOrderFilesCategory.ACCESSORY.getValue());
		customOrderFiles.setType(CustomOrderFilesType.ORDER_PACKAGE.getValue());
		customOrderFiles.setCreated(DateUtil.getSystemDate());
		customOrderFiles.setCreator(WebUtils.getCurrUserId());
		List imgList = new ArrayList();
		for (MultipartFile multipartFile : multipartFileList) {
			UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.FORMAL, multipartFile, UploadResourceType.CUSTOM_ORDER, id, itemId);
			customOrderFiles.setPath(uploadInfo.getRelativePath());
			customOrderFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
			customOrderFiles.setName(uploadInfo.getFileName());
			customOrderFiles.setMime(uploadInfo.getFileMimeType().getRealType());
			customOrderFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
			this.customOrderFilesService.add(customOrderFiles);
			imgList.add(customOrderFiles);
		}
		return ResultFactory.generateRequestResult(imgList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult deletePackageFiles(String id, String itemId, String fileId) {
		//判断资源文件是否存在
		CustomOrderFiles byId = this.customOrderFilesService.findById(fileId);
		if(byId==null||!byId.getCustomOrderId().equals(id)){
			return ResultFactory.generateSuccessResult();
		}
		//删除数据库记录
		this.customOrderFilesService.deleteById(fileId);
		//删除本地文件
		AppBeanInjector.baseFileUploadComponent.deleteFileByDir(byId.getFullPath());
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult writeExcel(PaginatedFilter paginatedFilter, ExcelParam excelParam) {
		List<Map<String,String>> sorts=  new ArrayList<Map<String,String>>();
		Map<String,String> created = new HashMap<String,String>();
		created.put("orderId","desc");
		sorts.add(created);
		paginatedFilter.setSorts(sorts);

		new BaseExportExcelUtil().download("第一页",this.finishedStockItemService.findListMapByFilter(paginatedFilter).getRows(),excelParam);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult findFinishedStockNos(String order,MapContext mapContext) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(1);
		pagination.setPageSize(100);
		paginatedFilter.setPagination(pagination);
		mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID,WebUtils.getCurrBranchId());
		paginatedFilter.setFilters(mapContext);
		List<Map<String,String>> sorts=  new ArrayList<Map<String,String>>();
		Map<String,String> created = new HashMap<String, String>();
		if(order!=null){
			if(order.equals("orderCreated")){
				created.put("orderCreated","asc");
			}else if(order.equals("consigneeName")){
				created.put("consigneeName","desc");
			}
		}else {
			created.put("finishedCreated","desc");
		}
		sorts.add(created);
		paginatedFilter.setSorts(sorts);
		PaginatedList<MapContext> result=this.finishedStockItemService.findFinishedStockNos(paginatedFilter);
		return ResultFactory.generateRequestResult(result.getRows());
	}

	@Override
	public RequestResult findFinishedStockCount(String branchId) {
		MapContext mapContext=this.finishedStockService.findCountByBranchId(branchId);
		return ResultFactory.generateRequestResult(mapContext);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDispatchPlanByOrder(DispatchBillPlanDto dispatchBillPlanDto) {
		List<String> orderIds = dispatchBillPlanDto.getOrderIds();
		//查询这些订单下的包裹
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageSize(1);
		pagination.setPageNum(999999999);
		paginatedFilter.setPagination(pagination);
		MapContext mapContext = new MapContext();
		List<List<FinishedStockItemDto>> lists = new ArrayList<List<FinishedStockItemDto>>();
		int pages = 0;
		for(String orderId:orderIds){
			mapContext.put("orderId",orderId);
			paginatedFilter.setFilters(mapContext);
			PaginatedList<FinishedStockItemDto> listByFilter = this.finishedStockItemService.findListByFilter(paginatedFilter);
			lists.add(listByFilter.getRows());
			pages+=listByFilter.getRows().size();
		}
		if(pages!=0){
			dispatchBillPlanDto.setBranchId(WebUtils.getCurrBranchId());
			dispatchBillPlanDto.setCreated(DateUtil.getSystemDate());
			dispatchBillPlanDto.setCreator(WebUtils.getCurrUserId());
			dispatchBillPlanDto.setNum(pages);
			dispatchBillPlanDto.setStatus(DispatchBillPlanStatus.INCOMPLETE.getValue());
			this.dispatchBillPlanService.add(dispatchBillPlanDto);
			List itemIds = new ArrayList();
			Set ids = new HashSet();
			for(List<FinishedStockItemDto> finishedStockItemDtoList:lists){
				for(FinishedStockItemDto finishedStockItemDto :finishedStockItemDtoList){
					DispatchBillPlanItem dispatchBillPlanItem = new DispatchBillPlanItem();
					dispatchBillPlanItem.setStatus(DispatchBillPlanItemStatus.UNSHIPPED.getValue());
					dispatchBillPlanItem.setDispatchBillPlanId(dispatchBillPlanDto.getId());
					dispatchBillPlanItem.setFinishedStockItemId(finishedStockItemDto.getId());
					dispatchBillPlanDto.getDispatchBillPlanItems().add(dispatchBillPlanItem);
					//判断包裹是否存在 以及包裹是否已入库 并且 未发货 未创建配送计划
					FinishedStockItem finishedStockItem = this.finishedStockItemService.findById(dispatchBillPlanItem.getFinishedStockItemId());
					if(finishedStockItem==null||finishedStockItem.getIn().equals(false)||finishedStockItem.getShipped().equals(true)||finishedStockItem.getDelivered()!=null){
						return ResultFactory.generateResNotFoundResult();
					}
					itemIds.add(dispatchBillPlanItem.getFinishedStockItemId());
					ids.add(dispatchBillPlanItem.getFinishedStockItemId());
				}
			}
			if(itemIds.size()!=ids.size()){
				return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
			}
			for(DispatchBillPlanItem dispatchBillPlanItem:dispatchBillPlanDto.getDispatchBillPlanItems()){
				this.dispatchBillPlanItemService.add(dispatchBillPlanItem);
			}
			//修改包裹状态为已创建配送计划
			this.finishedStockItemService.updateShippedByIds(itemIds);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult editDispatchPlan(DispatchBillPlanDto dispatchBillPlanDto) {
		String planId=dispatchBillPlanDto.getId();
		DispatchBillPlan byId = this.dispatchBillPlanService.findById(planId);
		if(byId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//已完成状态的发货计划单不允许修改
		if(byId.getStatus()==1){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
//		//未完成发货计划的单子
//		List<DispatchBillPlanItem> dispatchBillPlanItems = dispatchBillPlanDto.getDispatchBillPlanItems();
//		//未发货数
//		Integer nodelieverNum=0;
//		List<String> productsList=new ArrayList();
//		if(dispatchBillPlanItems!=null&&dispatchBillPlanItems.size()>0) {
//			for (DispatchBillPlanItem dispatchBillPlanItem : dispatchBillPlanItems) {
//						//修改生产单为未创建配送计划
//					    DispatchBillPlanItem byId1 = this.dispatchBillPlanItemService.findById(dispatchBillPlanItem.getId());
//						String produceId=byId1.getProduceId();
//						MapContext mapContext1=MapContext.newOne();
//						mapContext1.put("id",produceId);
//						mapContext1.put("shipped",0);
//						this.produceOrderService.updateByMapContext(mapContext1);
//			}
//		}
		//修改发货计划单
		MapContext plan=MapContext.newOne();
		Integer status = dispatchBillPlanDto.getStatus();
		String note = dispatchBillPlanDto.getNote();
		plan.put("id",planId);
		if(status!=null&&!status.equals("")) {
			plan.put("status", status);
		}
		if(note!=null&&!note.equals("")) {
			plan.put("note", note);
		}
		if(dispatchBillPlanDto.getAuditStatus()!=null&&!dispatchBillPlanDto.getAuditStatus().equals("")){
			plan.put("auditStatus",dispatchBillPlanDto.getAuditStatus());
		}
		if(plan.size()>1) {
			this.dispatchBillPlanService.updateByMapContext(plan);
		}
		//如果是完成发货计划，则把未完成的发货计划明细的生产单修改成未创建配送计划
		if(status!=null&&status==1){
			//未完成的发货计划明细
			List<DispatchBillPlanItem> bydbpIdAndStatus = this.dispatchBillPlanItemService.findBydbpIdAndStatus(planId, 0);
            if(bydbpIdAndStatus!=null&&bydbpIdAndStatus.size()>0){
            	for(DispatchBillPlanItem planItem:bydbpIdAndStatus){
					String produceId=planItem.getProduceId();
					MapContext mapContext1=MapContext.newOne();
					mapContext1.put("id",produceId);
					mapContext1.put("shipped",0);
					this.produceOrderService.updateByMapContext(mapContext1);
				}
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult finddispatchPlans(MapContext mapContext, Integer pageSize, Integer pageNum) {
		PaginatedFilter paginatedFilter = new PaginatedFilter();
		Pagination pagination = new Pagination();
		pagination.setPageNum(pageNum);
		pagination.setPageSize(pageSize);
		paginatedFilter.setPagination(pagination);
		paginatedFilter.setFilters(mapContext);
		PaginatedList<DispatchBillPlan> plans=this.dispatchBillPlanService.selectByFilter(paginatedFilter);
		//查询挂账产品数
		if(plans!=null){
		if(plans.getRows().size()>0) {
			for(DispatchBillPlan dispatchBillPlan:plans.getRows()){
				String planId=dispatchBillPlan.getId();
				Integer debtProductNum=this.dispatchBillPlanItemService.findDebtProductNumByPlanId(planId);
				dispatchBillPlan.setDebtProductNum(debtProductNum);
			}
		}
		}
		return ResultFactory.generateRequestResult(plans);
	}

	@Override
	public RequestResult findDispatchPlanInfo(String planId,MapContext mapContext) {
		DispatchBillPlan byId = this.dispatchBillPlanService.findById(planId);
		if(byId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		List<Map> mapList = new ArrayList<>();
		mapContext.put("planId",planId);
		List<DispatchBillPlanItem> planItems = this.dispatchBillPlanItemService.findListByDispatchBillPlanId(mapContext);
		String productId = "";
		Map map = new HashMap();
		List produceList = new ArrayList();
		List countList = new ArrayList();
		for (DispatchBillPlanItem dispatchBillItem : planItems) {
			//产品信息
			String productIdValue = dispatchBillItem.getOrderProductId();
			if (productIdValue == null || productIdValue.equals("")) {
				return ResultFactory.generateResNotFoundResult();
			}
			OrderProductDto oneProductById = this.orderProductService.findOneById(productIdValue);
			//查询相关的经销商信息
			String orderId=oneProductById.getCustomOrderId();
			CustomOrderDto byOrderId = this.customOrderService.findByOrderId(orderId);
			String companyId=byOrderId.getCompanyId();
			//收货地址
			String address=byOrderId.getAddress();
			//收货人
			String consigneeName=byOrderId.getConsigneeName();
			//收货电话
			String consigneeTel=byOrderId.getConsigneeTel();
			//终端客户姓名
			String customerName=byOrderId.getCustomerName();
			//经销商名称
			String dealerName=byOrderId.getDealerName();
			String dealerTel=byOrderId.getDealerTel();
			//产品备注
			String notes = oneProductById.getNotes();
			//查询指定物流
			WxDealerLogisticsDto defaultDto = this.dealerLogisticsService.findDefaultDto(companyId);
			String logisticsName="";
			String logisticsPhone="";
			String dealerLogisticsId="";
			if(defaultDto!=null){
				String logisticsCompanyId = defaultDto.getLogisticsCompanyId();
				LogisticsCompany byId1 = this.logisticsCompanyService.findById(logisticsCompanyId);
                dealerLogisticsId=logisticsCompanyId;
				logisticsName=byId1.getName();
				logisticsPhone=byId1.getTel();
			}

			String productNo = oneProductById.getNo();
			String productNotes = oneProductById.getNotes();
			String productType = oneProductById.getTypeName();
			//生产单信息
			String produceId = dispatchBillItem.getProduceId();
			ProduceOrderDto oneProduceById = this.produceOrderService.findOneById(produceId);
			oneProduceById.setId(dispatchBillItem.getId());
			oneProduceById.setDeliverStatus(dispatchBillItem.getStatus());
			Integer produceCount = oneProduceById.getCount();//包裹总件数
			Integer hasdeliverCount=oneProduceById.getHasDeliverCount();
			Integer type = oneProduceById.getType();
			String countValue="";
			if(produceCount!=null&&!produceCount.equals("")) {
				Basecode produceOrderType = basecodeService.findByTypeAndCode("produceOrderType", type.toString());
					countValue = produceOrderType.getRemark() + "=" + dispatchBillItem.getProduceNum();
			}
			if (productId.equals(productIdValue)) {
				    produceList.add(oneProduceById);
				    if(!countValue.equals("")) {
						countList.add(countValue);
					}
			} else {
				Map map1 = new HashMap();
				List produceList1 = new ArrayList();
				List countList1 = new ArrayList();
				if(!countValue.equals("")) {
					produceList1.add(oneProduceById);
					countList1.add(countValue);
				}
				map1.put("productId", productIdValue);
				map1.put("productNo", productNo);
				map1.put("productNotes", productNotes);
				map1.put("dealerName", dealerName);
				map1.put("dealerTel", dealerTel);
				map1.put("customerName", customerName);
				map1.put("address", address);
				map1.put("logisticsName", logisticsName);
				map1.put("logisticsPhone", logisticsPhone);
				map1.put("produceList", produceList1);
				map1.put("countList", countList1);
				map1.put("productType", productType);
				map1.put("notes", notes);
				map1.put("consigneeName", consigneeName);
				map1.put("consigneeTel", consigneeTel);
				map1.put("companyId", companyId);
				map1.put("dealerLogisticsId", dealerLogisticsId);
				map1.put("auditOpinion",dispatchBillItem.getAuditOpinion());
				map1.put("auditResult",dispatchBillItem.getAuditResult());
				map1.put("onCredit", oneProductById.getOnCredit());//挂账标识
				mapList.add(map1);
				map = map1;
				produceList = produceList1;
				countList = countList1;
				productId = productIdValue;
			}
		}
		return ResultFactory.generateRequestResult(mapList);

	}

	@Override
	public RequestResult execlDispatchPlanInfo(String planId, DispatchBillPlanItemParam excelParam) {
		DispatchBillPlan byId = this.dispatchBillPlanService.findById(planId);
		if(byId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		List<Map<String,Object>> mapList = new ArrayList<>();
		MapContext mapContext=MapContext.newOne();
		mapContext.put("planId",planId);
		List<DispatchBillPlanItem> planItems = this.dispatchBillPlanItemService.findListByDispatchBillPlanId(mapContext);
		String productId = "";
		Map<String,Object> map = new HashMap();
		List countList = new ArrayList();
		for (DispatchBillPlanItem dispatchBillItem : planItems) {
			//产品信息
			String productIdValue = dispatchBillItem.getOrderProductId();
			if (productIdValue == null || productIdValue.equals("")) {
				return ResultFactory.generateResNotFoundResult();
			}
			OrderProductDto oneProductById = this.orderProductService.findOneById(productIdValue);
			//产品系列
			String seriesName = oneProductById.getSeriesName();
			//查询相关的经销商信息
			String orderId=oneProductById.getCustomOrderId();
			CustomOrderDto byOrderId = this.customOrderService.findByOrderId(orderId);
			String companyId=byOrderId.getCompanyId();
            //省市区地址
			String cityName = byOrderId.getCityName();
			//收货地址
			String address=byOrderId.getAddress();
			//终端客户姓名
			String customerName=byOrderId.getCustomerName();
			//经销商名称
			String dealerName=byOrderId.getDealerName();
			String dealerTel=byOrderId.getDealerTel();
			//查询指定物流
			WxDealerLogisticsDto defaultDto = this.dealerLogisticsService.findDefaultDto(companyId);
			String logisticsName="";
			String logisticsPhone="";
			if(defaultDto!=null){
				String logisticsCompanyId = defaultDto.getLogisticsCompanyId();
				LogisticsCompany byId1 = this.logisticsCompanyService.findById(logisticsCompanyId);
				logisticsName=byId1.getName();
				logisticsPhone=byId1.getTel();
			}

			String productNo = oneProductById.getNo();
			String productNotes = oneProductById.getNotes();
			//生产单信息
			String produceId = dispatchBillItem.getProduceId();
			ProduceOrderDto oneProduceById = this.produceOrderService.findOneById(produceId);
			Integer produceCount = oneProduceById.getCount();
			Integer type = oneProduceById.getType();
			String countValue="";
			if(produceCount!=null&&!produceCount.equals("")) {
				Basecode produceOrderType = basecodeService.findByTypeAndCode("produceOrderType", type.toString());
				countValue = produceOrderType.getValue()+"=" + produceCount;
			}
			if (productId.equals(productIdValue)) {
				if(!countValue.equals("")) {
					countList.add(countValue);
				}
			} else {
				Map map1 = new HashMap();
				List countList1 = new ArrayList();
				if(!countValue.equals("")) {
					countList1.add(countValue);
				}
				map1.put("productNo", productNo);
				map1.put("dealerName", dealerName);
				map1.put("cityName", cityName);
				map1.put("address", address);
				map1.put("customerName", customerName);
				map1.put("series", seriesName);
				map1.put("dealerTel", dealerTel);
				map1.put("countList", countList1);
				map1.put("logisticsName", logisticsName);
				map1.put("logisticsNo", "");
				map1.put("productNotes", productNotes);
				mapList.add(map1);
				map = map1;
				countList = countList1;
				productId = productIdValue;
			}
		}
		String filename= null;
		try {
			filename = new String(byId.getName().concat(".xls").getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		excelParam.setFileName(filename);
		new BaseExportExcelUtil().download(byId.getName(), mapList, excelParam);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult addDispatchPlanProduct(DispatchBillPlanDto dispatchBillPlanDto) {
		//查询发货单是否存在
		String planId=dispatchBillPlanDto.getId();
		DispatchBillPlan byId = this.dispatchBillPlanService.findById(planId);
		if(byId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//已完成状态的发货计划单不允许修改
		if(byId.getStatus()==1){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		//删除所有发货计划明细
		MapContext mapContext0o=MapContext.newOne();
		mapContext0o.put("planId",planId);
		List<DispatchBillPlanItem> listByDispatchBillPlanId = this.dispatchBillPlanItemService.findListByDispatchBillPlanId(mapContext0o);
		for(DispatchBillPlanItem planItem:listByDispatchBillPlanId){
			//修改生产单的是否已创建配送计划为否
			MapContext mapContext=MapContext.newOne();
			mapContext.put("id",planItem.getProduceId());
			mapContext.put("shipped",0);
			this.produceOrderService.updateByMapContext(mapContext);
			this.dispatchBillPlanItemService.deleteById(planItem.getId());
		}
		//新增发货计划产品
		Set products=new HashSet();
		MapContext mapContext=MapContext.newOne();
		for(DispatchBillPlanItem dispatchBillPlanItem:dispatchBillPlanDto.getDispatchBillPlanItems()){
			dispatchBillPlanItem.setStatus(DispatchBillPlanItemStatus.UNSHIPPED.getValue());//
			dispatchBillPlanItem.setDispatchBillPlanId(dispatchBillPlanDto.getId());
			this.dispatchBillPlanItemService.add(dispatchBillPlanItem);
			//产品数量
			String productId=dispatchBillPlanItem.getOrderProductId();
			products.add(productId);
			//修改生产单
			mapContext.put("id",dispatchBillPlanItem.getProduceId());
			mapContext.put("shipped",1);
			this.produceOrderService.updateByMapContext(mapContext);
		}
		//更新发货单产品数
		MapContext mapContext1=MapContext.newOne();
		mapContext1.put("id",dispatchBillPlanDto.getId());
		mapContext1.put("num",products.size());
		this.dispatchBillPlanService.updateByMapContext(mapContext1);
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult nodoneDispatchPlan() {
		String branchId=WebUtils.getCurrBranchId();
		DispatchBillPlan dispatchBillPlan=this.dispatchBillPlanService.findByStatusAndBranchId(0,branchId);
		if(dispatchBillPlan!=null) {
			String planName=dispatchBillPlan.getName();
			Map errorMap=new HashMap();
			errorMap.put(planName,ErrorCodes.BIZ_DISPATCH_PLAN_NOT_END_10097);
			errorMap.put("id",dispatchBillPlan.getId());
			errorMap.put("name",dispatchBillPlan.getName());
			SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
			errorMap.put("planTime",dateFormat.format(dispatchBillPlan.getPlanTime()));
			return ResultFactory.generateErrorResult(ErrorCodes.VALIDATE_ERROR,errorMap);
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult nodoneDispatchPlanNowinfo() {
		String branchId=WebUtils.getCurrBranchId();
		DispatchBillPlan byId = this.dispatchBillPlanService.findByStatusAndBranchId(0,branchId);
		if(byId==null){
			return ResultFactory.generateSuccessResult();
		}
		List<Map> mapList = new ArrayList<>();
		MapContext mapContext0o=MapContext.newOne();
		mapContext0o.put("planId",byId.getId());
		List<DispatchBillPlanItem> planItems = this.dispatchBillPlanItemService.findListByDispatchBillPlanId(mapContext0o);
		String productId = "";
		Map map = new HashMap();
		List produceList = new ArrayList();
		List countList = new ArrayList();
		for (DispatchBillPlanItem dispatchBillItem : planItems) {
			//产品信息
			String productIdValue = dispatchBillItem.getOrderProductId();
			if (productIdValue == null || productIdValue.equals("")) {
				return ResultFactory.generateResNotFoundResult();
			}
			OrderProductDto oneProductById = this.orderProductService.findOneById(productIdValue);
			//查询相关的经销商信息
			String orderId=oneProductById.getCustomOrderId();
			CustomOrderDto byOrderId = this.customOrderService.findByOrderId(orderId);
			String companyId=byOrderId.getCompanyId();
			//收货地址
			String address=byOrderId.getAddress();
			//终端客户姓名
			String customerName=byOrderId.getCustomerName();
			//经销商名称
			String dealerName=byOrderId.getDealerName();
			String dealerTel=byOrderId.getDealerTel();
			//查询指定物流
			WxDealerLogisticsDto defaultDto = this.dealerLogisticsService.findDefaultDto(companyId);
			String logisticsName="";
			String logisticsPhone="";
			if(defaultDto!=null){
				String logisticsCompanyId = defaultDto.getLogisticsCompanyId();
				LogisticsCompany byId1 = this.logisticsCompanyService.findById(logisticsCompanyId);
				logisticsName=byId1.getName();
				logisticsPhone=byId1.getTel();
			}

			String productNo = oneProductById.getNo();
			String productNotes = oneProductById.getNotes();
			String productType = oneProductById.getTypeName();
			Date payTime = oneProductById.getPayTime();
			String receiverName = oneProductById.getReceiverName();
			//生产单信息
			String produceId = dispatchBillItem.getProduceId();
			ProduceOrderDto oneProduceById = this.produceOrderService.findOneById(produceId);
			oneProduceById.setId(dispatchBillItem.getId());
			oneProduceById.setDeliverStatus(dispatchBillItem.getStatus());
			String produceTypeAndWay = oneProduceById.getTypeName() + "-" + oneProduceById.getWayName();
			Integer produceCount = oneProduceById.getCount();
			Integer type = oneProduceById.getType();
			String countValue="";
			if(produceCount!=null&&!produceCount.equals("")) {
				Basecode produceOrderType = basecodeService.findByTypeAndCode("produceOrderType", type.toString());
				countValue = produceOrderType.getRemark()+"=" + produceCount;
			}
			if (productId.equals(productIdValue)) {
				produceList.add(oneProduceById);
				if(!countValue.equals("")) {
					countList.add(countValue);
				}
			} else {
				Map map1 = new HashMap();
				List produceList1 = new ArrayList();
				List countList1 = new ArrayList();
				if(!countValue.equals("")) {
					produceList1.add(oneProduceById);
					countList1.add(countValue);
				}
				map1.put("productType", productType);
				map1.put("payTime", payTime);
				map1.put("receiverName", receiverName);
				map1.put("produceTypeAndWay", produceTypeAndWay);
				map1.put("productNo", productNo);
				map1.put("productNotes", productNotes);
				map1.put("dealerName", dealerName);
				map1.put("dealerTel", dealerTel);
				map1.put("customerName", customerName);
				map1.put("address", address);
				map1.put("logisticsName", logisticsName);
				map1.put("logisticsPhone", logisticsPhone);
				map1.put("produceList", produceList1);
				map1.put("countList", countList1);
				mapList.add(map1);
				map = map1;
				produceList = produceList1;
				countList = countList1;
				productId = productIdValue;
			}
		}
		return ResultFactory.generateRequestResult(mapList);
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult editDispatchPlanlogistics(DispatchBillPlanDto dispatchBillPlanDto) {
		//查询发货单是否存在
		String planId=dispatchBillPlanDto.getId();
		DispatchBillPlan billPlan = this.dispatchBillPlanService.findById(planId);
		if(billPlan==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//已完成状态的发货计划单不允许修改
		if(billPlan.getStatus()==1){
			return ResultFactory.generateErrorResult(ErrorCodes.BIZ_NOT_ALLOW_OPERATION_10020,AppBeanInjector.i18nUtil.getMessage("BIZ_NOT_ALLOW_OPERATION_10020"));
		}
		//创建发货单
		User user=AppBeanInjector.userService.findByUserId(WebUtils.getCurrUserId());
		//创建发货单
		DispatchBillDto dispatchBillDto=new DispatchBillDto();
		dispatchBillDto.setCreated(DateUtil.getSystemDate());
		dispatchBillDto.setCreator(WebUtils.getCurrUserId());
		dispatchBillDto.setDeliverer(WebUtils.getCurrUserId());
		dispatchBillDto.setBranchId(WebUtils.getCurrBranchId());
		dispatchBillDto.setDelivererTel(user.getMobile());
		dispatchBillDto.setConsignee(dispatchBillPlanDto.getConsignee());
		dispatchBillDto.setConsigneeTel(dispatchBillPlanDto.getConsigneeTel());
		dispatchBillDto.setAddress(dispatchBillPlanDto.getAddress());
		dispatchBillDto.setCompanyId(dispatchBillPlanDto.getCompanyId());
		dispatchBillDto.setNotes(dispatchBillPlanDto.getNote());
		if (dispatchBillDto.getActualDate() == null || dispatchBillDto.getActualDate().equals("")) {
			dispatchBillDto.setActualDate(DateUtil.getSystemDate());
		}else {
			dispatchBillDto.setActualDate(dispatchBillPlanDto.getActualDate());
		}
		dispatchBillDto.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.DISPATCH_NO));
		dispatchBillDto.setStatus(DispatchBillStatus.TRANSPORT.getValue());
		dispatchBillDto.setLogisticsCompanyId(dispatchBillPlanDto.getLogisticsCompanyId());
		dispatchBillDto.setLogisticsNo(dispatchBillPlanDto.getLogisticsNo());
		this.dispatchBillService.add(dispatchBillDto);
		//已发货产品
		Integer actualNum=0;
		if(billPlan.getActualNum()!=null&&!billPlan.getActualNum().equals("")){
			actualNum=billPlan.getActualNum();
		}
		List<String> productsList=new ArrayList();
		//创建发货明细
		List<DispatchBillPlanItem> dispatchBillPlanItems = dispatchBillPlanDto.getDispatchBillPlanItems();
		if(dispatchBillPlanItems!=null&&dispatchBillPlanItems.size()>0){
			for(DispatchBillPlanItem dispatchBillPlanItem:dispatchBillPlanItems){
				String planItemid=dispatchBillPlanItem.getId();
				DispatchBillPlanItem byId = dispatchBillPlanItemService.findById(planItemid);
				productsList.add(byId.getOrderProductId());
				String produceId=byId.getProduceId();
				ProduceOrderDto oneById = produceOrderService.findOneById(produceId);
				Integer hasDeliverCount = oneById.getHasDeliverCount();
				if(hasDeliverCount==null||hasDeliverCount.equals("")){
					hasDeliverCount=0;
				}
				int deliverNum=oneById.getCount()-hasDeliverCount;
				DispatchBillItem dispatchBillItem=new DispatchBillItem();
				dispatchBillItem.setDispatchBillId(dispatchBillDto.getId());
				dispatchBillItem.setOrderProduceId(byId.getProduceId());
				dispatchBillItem.setProductId(byId.getOrderProductId());
				dispatchBillItem.setStatus(0);
				dispatchBillItem.setDeliverNum(deliverNum);//此次发货包裹数量
				dispatchBillItemService.add(dispatchBillItem);
				//判断生产单是否完成入库
				Integer inputStatus = oneById.getInputStatus();
				//修改生产单已发货数量
				MapContext mapContext=new MapContext();
				mapContext.put("id",produceId);
				if(inputStatus!=null&&inputStatus==1){
					mapContext.put("state", ProduceOrderState.COMPLETE.getValue());
					mapContext.put("deliverStatus", 1);
				}
				mapContext.put("hasDeliverCount",oneById.getCount());
				this.produceOrderService.updateByMapContext(mapContext);
				//修改发货计划明细单为已发货
				MapContext map=MapContext.newOne();
				map.put("status",DispatchBillPlanItemStatus.SHIPPED.getValue());
				map.put("id",dispatchBillPlanItem.getId());
				this.dispatchBillPlanItemService.updateByMapContext(map);
				//记录发货计划表的实际发货数
				//查询此次发货计划的产品下的部件是否已全部发货
				MapContext mapCon=MapContext.newOne();
				mapCon.put("planId",planId);
				mapCon.put("orderProductId",byId.getOrderProductId());
				List<DispatchBillPlanItem> listByDispatchBillPlanId = this.dispatchBillPlanItemService.findListByDispatchBillPlanId(mapCon);
                mapCon.put("status",1);
				List<DispatchBillPlanItem> listByDispatchBillPlanIdtwo = this.dispatchBillPlanItemService.findListByDispatchBillPlanId(mapCon);
				if(listByDispatchBillPlanIdtwo!=null){
					if(listByDispatchBillPlanId.size()==listByDispatchBillPlanIdtwo.size()){
						actualNum=actualNum+1;
					}
				}
				//查询产品下已发货的生产单
				MapContext hasDelievery = this.produceOrderService.findListByProductIdAndState(byId.getOrderProductId(), ProduceOrderState.COMPLETE.getValue());
				List<ProduceOrder> listByProductId = this.produceOrderService.findListByProductId(byId.getOrderProductId());
				//如果产品下的生产单全部已发货，则修改产品的状态为已发货
				if (listByProductId.size() == hasDelievery.getTypedValue("countDeliver", Integer.class)) {
					MapContext mapContext1 = MapContext.newOne();
					mapContext1.put("id", byId.getOrderProductId());
					mapContext1.put("deliveryTime", new Date());
					mapContext1.put("deliveryCreator", WebUtils.getCurrUserId());
					mapContext1.put("status", OrderProductStatus.HAS_DELIVERY.getValue());
					mapContext1.put("hasDeliverCount", hasDelievery.getTypedValue("countInStock", Integer.class));
					this.orderProductService.updateByMapContext(mapContext1);
					if(!productsList.contains(byId.getOrderProductId())){
						productsList.add(byId.getOrderProductId());
					}
				} else {//修改已发货数量
					MapContext mapContext1 = MapContext.newOne();
					mapContext1.put("id", byId.getOrderProductId());
					mapContext1.put("hasDeliverCount", hasDelievery.getTypedValue("countInStock", Integer.class));
					this.orderProductService.updateByMapContext(mapContext1);
				}
				//查询订单下产品
				List<OrderProductDto> listProd = orderProductService.findListByOrderId(oneById.getCustomOrderId());
				int count = 0;
				for (OrderProductDto dto : listProd) {
					if (dto.getStatus() == OrderProductStatus.HAS_DELIVERY.getValue()) {
						count++;
					}
				}
				//如果所有的产品都已发货，则修改订单状态
				if (count == listProd.size()) {
					//查询审核时间，计算订单总用时
					MapContext params = MapContext.newOne();
					params.put("orderId", oneById.getCustomOrderId());
					params.put("funds", PaymentFunds.ORDER_FEE_CHARGE.getValue());
					PaymentDto paymentDto = this.paymentService.findByOrderIdAndFunds(params);
					Date audited = paymentDto.getAudited();
					long audit = audited.getTime();
					long nowTime = DateUtil.getSystemDate().getTime();
					long diff = (nowTime - audit);
					long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
					long nh = 1000 * 60 * 60;//一小时的毫秒数
					long nm = 1000 * 60;//一分钟的毫秒数
					long day = diff / nd;//计算用时多少天
					long hour = diff % nd / nh;//计算用时多少小时
					long min = diff % nd % nh / nm;//计算用时多少分钟
					String timeConsuming = day + "天" + hour + "小时" + min + "分钟";
					MapContext maps = MapContext.newOne();
					maps.put("id", oneById.getCustomOrderId());
					maps.put("status", OrderStatus.SHIPPED.getValue());
					maps.put("deliveryDate", new Date());
					maps.put("timeConsuming",timeConsuming);
					customOrderService.updateByMapContext(map);
				}

			}
			//更新计划表实际发货数
			MapContext plan=MapContext.newOne();
			plan.put("id",planId);
			plan.put("actualNum",actualNum);
			this.dispatchBillPlanService.updateByMapContext(plan);
		}

		return ResultFactory.generateSuccessResult();
	}

	@Override
	@Transactional(value = "transactionManager")
	public RequestResult auditplan(DispatchBillPlanDto dispatchBillPlanDto) {
		String loginId=WebUtils.getCurrUserId();
		User byUserId = AppBeanInjector.userService.findByUserId(loginId);
		if(byUserId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		String planId=dispatchBillPlanDto.getId();
		DispatchBillPlan byId = this.dispatchBillPlanService.findById(planId);
		if(byId==null){
			return ResultFactory.generateResNotFoundResult();
		}
		//修改发货计划单为已审核
		MapContext map=MapContext.newOne();
		map.put("id",planId);
		map.put("auditStatus",dispatchBillPlanDto.getAuditStatus());
		map.put("auditor",byUserId.getName());
		this.dispatchBillPlanService.updateByMapContext(map);
		//修改明细的审核意见
		List<DispatchBillPlanItem> listByDispatchBillPlanId = dispatchBillPlanDto.getDispatchBillPlanItems();
		if(listByDispatchBillPlanId!=null&&listByDispatchBillPlanId.size()>0){
			for(DispatchBillPlanItem planItem:listByDispatchBillPlanId){
				String itemId=planItem.getId();
				String auditOpinion=planItem.getAuditOpinion();
				Integer auditResult=planItem.getAuditResult();
				MapContext mapContext=MapContext.newOne();
				mapContext.put("id",itemId);
				mapContext.put("auditOpinion",auditOpinion);
				mapContext.put("auditResult",auditResult);
				this.dispatchBillPlanItemService.updateByMapContext(mapContext);
			}
		}
		return ResultFactory.generateSuccessResult();
	}

	@Override
	public RequestResult countInputPart(String branchId) {
		MapContext map = this.orderProductService.countInputPart(branchId);
		 return ResultFactory.generateRequestResult(this.orderProductService.countInputPart(branchId));
	}
}
