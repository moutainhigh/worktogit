package com.lwxf.industry4.webapp.facade.admin.factory.dispatching.impl;

import com.alibaba.fastjson.JSON;
import com.lwxf.commons.utils.DateUtil;
import com.lwxf.industry4.webapp.bizservice.common.UploadFilesService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderLogService;
import com.lwxf.industry4.webapp.bizservice.customorder.CustomOrderService;
import com.lwxf.industry4.webapp.bizservice.customorder.OrderProductService;
import com.lwxf.industry4.webapp.bizservice.customorder.ProduceOrderService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillPlanItemService;
import com.lwxf.industry4.webapp.bizservice.dispatch.DispatchBillService;
import com.lwxf.industry4.webapp.bizservice.financing.PaymentService;
import com.lwxf.industry4.webapp.bizservice.system.BasecodeService;
import com.lwxf.industry4.webapp.bizservice.system.LogisticsCompanyService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockItemService;
import com.lwxf.industry4.webapp.bizservice.warehouse.FinishedStockService;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationMoudule;
import com.lwxf.industry4.webapp.common.aop.syslog.OperationType;
import com.lwxf.industry4.webapp.common.aop.syslog.SysOperationLog;
import com.lwxf.industry4.webapp.common.component.UploadInfo;
import com.lwxf.industry4.webapp.common.component.UploadType;
import com.lwxf.industry4.webapp.common.constant.WebConstant;
import com.lwxf.industry4.webapp.common.enums.UploadResourceType;
import com.lwxf.industry4.webapp.common.enums.customorder.ProduceOrderState;
import com.lwxf.industry4.webapp.common.enums.dispatch.DispatchBillStatus;
import com.lwxf.industry4.webapp.common.enums.financing.PaymentFunds;
import com.lwxf.industry4.webapp.common.enums.order.OrderProductStatus;
import com.lwxf.industry4.webapp.common.enums.order.OrderStage;
import com.lwxf.industry4.webapp.common.enums.order.OrderStatus;
import com.lwxf.industry4.webapp.common.enums.storage.FinishedStockItemType;
import com.lwxf.industry4.webapp.common.exceptions.ErrorCodes;
import com.lwxf.industry4.webapp.common.model.PaginatedFilter;
import com.lwxf.industry4.webapp.common.model.PaginatedList;
import com.lwxf.industry4.webapp.common.model.Pagination;
import com.lwxf.industry4.webapp.common.result.RequestResult;
import com.lwxf.industry4.webapp.common.result.ResultFactory;
import com.lwxf.industry4.webapp.common.uniquecode.UniquneCodeGenerator;
import com.lwxf.industry4.webapp.common.utils.WebUtils;
import com.lwxf.industry4.webapp.common.utils.WeiXin.WeiXinMsgPush;
import com.lwxf.industry4.webapp.domain.dto.company.CompanyDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.CustomOrderDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.OrderProductDto;
import com.lwxf.industry4.webapp.domain.dto.customorder.ProduceOrderDto;
import com.lwxf.industry4.webapp.domain.dto.dispatch.DispatchBillDto;
import com.lwxf.industry4.webapp.domain.dto.financing.PaymentDto;
import com.lwxf.industry4.webapp.domain.dto.printTable.DispatchPrintTableDto;
import com.lwxf.industry4.webapp.domain.entity.common.UploadFiles;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrder;
import com.lwxf.industry4.webapp.domain.entity.customorder.CustomOrderLog;
import com.lwxf.industry4.webapp.domain.entity.customorder.ProduceOrder;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBill;
import com.lwxf.industry4.webapp.domain.entity.dispatch.DispatchBillItem;
import com.lwxf.industry4.webapp.domain.entity.system.Basecode;
import com.lwxf.industry4.webapp.domain.entity.system.LogisticsCompany;
import com.lwxf.industry4.webapp.domain.entity.user.User;
import com.lwxf.industry4.webapp.domain.entity.user.UserThirdInfo;
import com.lwxf.industry4.webapp.facade.AppBeanInjector;
import com.lwxf.industry4.webapp.facade.admin.factory.dispatching.DispatchFacade;
import com.lwxf.industry4.webapp.facade.base.BaseFacadeImpl;
import com.lwxf.mybatis.utils.MapContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.*;

/**
 * 功能：
 *
 * @author：dongshibo(F_baisi)
 * @create：2018/12/25/025 13:57
 * @version：2018 Version：1.0
 * @company：老屋新房 Created with IntelliJ IDEA
 */
@Component("dispatchFacade")
public class DispatchFacadeImpl extends BaseFacadeImpl implements DispatchFacade {

    @Resource(name = "dispatchBillService")
    private DispatchBillService dispatchBillService;
    @Resource(name = "customOrderService")
    private CustomOrderService customOrderService;
    @Resource(name = "finishedStockService")
    private FinishedStockService finishedStockService;
    @Resource(name = "logisticsCompanyService")
    private LogisticsCompanyService logisticsCompanyService;
    @Resource(name = "finishedStockItemService")
    private FinishedStockItemService finishedStockItemService;
    @Resource(name = "dispatchBillItemService")
    private DispatchBillItemService dispatchBillItemService;
    @Resource(name = "dispatchBillPlanItemService")
    private DispatchBillPlanItemService dispatchBillPlanItemService;
    @Resource(name = "uploadFilesService")
    private UploadFilesService uploadFilesService;
    @Resource(name = "customOrderLogService")
    private CustomOrderLogService customOrderLogService;
    @Resource(name = "produceOrderService")
    private ProduceOrderService produceOrderService;
    @Resource(name = "orderProductService")
    private OrderProductService orderProductService;
    @Resource(name = "basecodeService")
    private BasecodeService basecodeService;
    @Resource(name = "paymentService")
    private PaymentService paymentService;
    @Autowired
    private WeiXinMsgPush weiXinMsgPush;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public RequestResult findDispatchList(MapContext mapContext, Integer pageNum, Integer pageSize) {
        PaginatedFilter paginatedFilter = new PaginatedFilter();
        mapContext.put(WebConstant.KEY_ENTITY_BRANCH_ID, WebUtils.getCurrBranchId());
        paginatedFilter.setFilters(mapContext);
        Pagination pagination = new Pagination();
        pagination.setPageSize(pageSize);
        pagination.setPageNum(pageNum);
        paginatedFilter.setPagination(pagination);
        List<Map<String, String>> sorts = new ArrayList<Map<String, String>>();
        Map<String, String> created = new HashMap<String, String>();
        created.put(WebConstant.KEY_ENTITY_CREATED, "desc");
        sorts.add(created);
        paginatedFilter.setSorts(sorts);
        PaginatedList<DispatchBillDto> dispatchBillDtoPaginatedList = this.dispatchBillService.selectByFilter(paginatedFilter);
        for (DispatchBillDto dispatchBillDto : dispatchBillDtoPaginatedList.getRows()) {
            String dispatchBillId = dispatchBillDto.getId();
            List<Map> mapList = new ArrayList<>();
            List<DispatchBillItem> dispatchBillItems = this.dispatchBillItemService.findListByDispatchBillId(dispatchBillId);
            String productId = "";
            Map map = new HashMap();
            List typeList = new ArrayList();
            List countList = new ArrayList();
            for (DispatchBillItem dispatchBillItem : dispatchBillItems) {
                //产品信息
                String productIdValue = dispatchBillItem.getProductId();
                if (productIdValue == null || productIdValue.equals("")) {
                    return ResultFactory.generateResNotFoundResult();
                }
                OrderProductDto oneProductById = this.orderProductService.findOneById(productIdValue);
                String productType = oneProductById.getTypeName();
                String productNo = oneProductById.getNo();
                String productSeries = oneProductById.getSeriesName();
                Integer productCount = oneProductById.getStockCount();
                String productNotes = oneProductById.getNotes();
                Date payTime = oneProductById.getPayTime();
                String receiverName = oneProductById.getReceiverName();
                //生产单信息
                String produceId = dispatchBillItem.getOrderProduceId();
                ProduceOrderDto oneProduceById = this.produceOrderService.findOneById(produceId);
                String produceTypeAndWay = oneProduceById.getTypeName() + "-" + oneProduceById.getWayName();
                Integer produceCount = dispatchBillItem.getDeliverNum();
                Integer type = oneProduceById.getType();
                String countValue = "";
                if (produceCount != null && !produceCount.equals("")) {
                    Basecode produceOrderType = this.basecodeService.findByTypeAndCode("produceOrderType", type.toString());
                    countValue = produceOrderType.getRemark() + "=" + produceCount;
                }
                if (productId.equals(productIdValue)) {
                    typeList.add(produceTypeAndWay);
                    if (!countValue.equals("")) {
                        countList.add(countValue);
                    }
                } else {
                    Map map1 = new HashMap();
                    List typeList1 = new ArrayList();
                    List countList1 = new ArrayList();
                    typeList1.add(produceTypeAndWay);
                    if (!countValue.equals("")) {
                        countList1.add(countValue);
                    }
                    map1.put("productType", productType);
                    map1.put("productNo", productNo);
                    map1.put("productSeries", productSeries);
                    map1.put("productCount", productCount);
                    map1.put("productNotes", productNotes);
                    map1.put("payTime", payTime);
                    map1.put("receiverName", receiverName);
                    map1.put("typeList", typeList1);
                    map1.put("countList", countList1);
                    map1.put("orderNo", oneProductById.getOrderNo());
                    mapList.add(map1);
                    map = map1;
                    typeList = typeList1;
                    countList = countList1;
                    productId = productIdValue;
                }
            }
            dispatchBillDto.setProducts(mapList);
        }
        return ResultFactory.generateRequestResult(dispatchBillDtoPaginatedList);
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "新建发货单", operationType = OperationType.INSERT, operationMoudule = OperationMoudule.DISPATCH)
    public RequestResult addDispatch(DispatchBillDto dispatchBillDto) {
        User user = AppBeanInjector.userService.findByUserId(WebUtils.getCurrUserId());
        //创建发货单
        dispatchBillDto.setCreated(DateUtil.getSystemDate());
        dispatchBillDto.setCreator(WebUtils.getCurrUserId());
        dispatchBillDto.setDeliverer(WebUtils.getCurrUserId());
        dispatchBillDto.setBranchId(WebUtils.getCurrBranchId());
        dispatchBillDto.setDelivererTel(user.getMobile());
        if (dispatchBillDto.getActualDate() == null || dispatchBillDto.getActualDate().equals("")) {
            dispatchBillDto.setActualDate(DateUtil.getSystemDate());
        }
        dispatchBillDto.setNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.DISPATCH_NO));
        dispatchBillDto.setStatus(DispatchBillStatus.TRANSPORT.getValue());
        //自提的货，物流编号系统生成
        if(dispatchBillDto.getLogisticsMode()!=null&&dispatchBillDto.getLogisticsMode()==1){
            dispatchBillDto.setLogisticsNo(AppBeanInjector.uniquneCodeGenerator.getNextNo(UniquneCodeGenerator.UniqueResource.LOGISTICS_NO));
        }
        this.dispatchBillService.add(dispatchBillDto);
        String logisticsCompanyId = dispatchBillDto.getLogisticsCompanyId();
        String logisticsName="";
        if(logisticsCompanyId==null||logisticsCompanyId.equals("")){
            logisticsName="自提";
        }else {
            LogisticsCompany byLogisticId = this.logisticsCompanyService.findByLogisticId(logisticsCompanyId);
            logisticsName=byLogisticId.getName();
        }
        //生产单ids，生产单id逗号分隔的字符串
        String produceIdValue = dispatchBillDto.getProduceIds();
        String[] produceIds = produceIdValue.split(",");
        for (int i = 0; i < produceIds.length; i++) {
            MapContext mapContext = MapContext.newOne();
            String id = produceIds[i];
            //查询是否已完成入库
            ProduceOrderDto produceOrderDto = produceOrderService.findOneById(id);
            Integer inputStatus = produceOrderDto.getInputStatus();
            Integer hasDeliverCount = produceOrderDto.getHasDeliverCount();
            mapContext.put("id", id);
            mapContext.put("delivered", DateUtil.getSystemDate());
            mapContext.put("deliverer", WebUtils.getCurrUserId());
            if (inputStatus != null && !inputStatus.equals("")) {
                if (inputStatus == 1) {
                    mapContext.put("state", ProduceOrderState.COMPLETE.getValue());
                }
            }
            mapContext.put("deliverStatus", 1);
            mapContext.put("hasDeliverCount", produceOrderDto.getCount());
            this.produceOrderService.updateByMapContext(mapContext);
            //查询生产单类型名称
            ProduceOrderDto oneById1 = this.produceOrderService.findOneById(id);
            Basecode produceOrderType = this.basecodeService.findByTypeAndCode("produceOrderType", oneById1.getType().toString());
            //产品id
            String productId = oneById1.getOrderProductId();
            //查询产品
            OrderProductDto oneById = this.orderProductService.findOneById(productId);
            //生成订单日志
            if (dispatchBillDto.getLogisticsNo() != null&&!dispatchBillDto.getLogisticsNo().equals("")) {
                CustomOrderLog log = new CustomOrderLog();
                log.setCreated(new Date());
                log.setCreator(WebUtils.getCurrUserId());
                log.setName("发货");
                log.setStage(OrderStage.DELIVERY.getValue());
                log.setContent("订单产品:" + oneById.getNo() + "-" + produceOrderType.getValue() + "已发货" + "-" + logisticsName + dispatchBillDto.getLogisticsNo());
                log.setCustomOrderId(oneById.getCustomOrderId());
                customOrderLogService.add(log);

            }
            //创建发货清单
            DispatchBillItem dispatchBillItem = new DispatchBillItem();
            dispatchBillItem.setDispatchBillId(dispatchBillDto.getId());
            dispatchBillItem.setStatus(0);
            dispatchBillItem.setProductId(productId);
            dispatchBillItem.setOrderProduceId(id);
            dispatchBillItem.setDeliverNum(produceOrderDto.getCount() - hasDeliverCount);
            this.dispatchBillItemService.add(dispatchBillItem);
            //查询产品下已发货的生产单数量
            MapContext hasDelieveryProduce = this.produceOrderService.findListByProductIdAndState(productId, ProduceOrderState.COMPLETE.getValue());
            //查询产品下已发货的包裹数量
            MapContext hasDelievery = this.produceOrderService.findHasDeliverCountByProductId(productId);
            List<ProduceOrder> listByProductId = this.produceOrderService.findListByProductId(productId);
            //如果产品下的生产单全部已发货，则修改产品的状态为已发货
            if (listByProductId.size() == hasDelieveryProduce.getTypedValue("countDeliver", Integer.class)) {
                MapContext mapContext1 = MapContext.newOne();
                mapContext1.put("id", productId);
                mapContext1.put("deliveryTime", new Date());
                mapContext1.put("deliveryCreator", WebUtils.getCurrUserId());
                mapContext1.put("status", OrderProductStatus.HAS_DELIVERY.getValue());
                mapContext1.put("hasDeliverCount", hasDelievery.getTypedValue("countInStock", Integer.class));
                this.orderProductService.updateByMapContext(mapContext1);
                //公众号消息通知
                String companyId = oneById.getCompanyId();
                CompanyDto companyById = AppBeanInjector.companyService.findCompanyById(companyId);
                if (companyById != null) {
                    String leader = companyById.getLeader();
                    UserThirdInfo byUserId = AppBeanInjector.userThirdInfoService.findByUserId(leader);
                    if (byUserId != null) {
                        //查询负责人的公众号openId是否为空
                        if (byUserId.getOfficialOpenId() != null && !byUserId.getOfficialOpenId().equals("")) {
                            CustomOrderDto byOrderId = this.customOrderService.findByOrderId(oneById.getCustomOrderId());
                            MapContext msg = MapContext.newOne();
                            msg.put("orderId", oneById.getCustomOrderId());
                            msg.put("orderStatus", 4);
                            msg.put("first", "您好，您的订单产品" + oneById.getNo() + "-" + produceOrderType.getValue() + "已发货,请注意查收！");
                            msg.put("keyword1", oneById.getNo());
                            msg.put("keyword2", DateUtil.dateTimeToString(new Date()));
                            msg.put("keyword3", logisticsName);
                            msg.put("keyword4", dispatchBillDto.getLogisticsNo());
                            msg.put("keyword5", byOrderId.getConsigneeName() + "-" + byOrderId.getConsigneeTel() + "-" + byOrderId.getAddress());
                            msg.put("remark","感谢您的支持");
                            String openId = byUserId.getOfficialOpenId();
                            weiXinMsgPush.SendWxMsg(openId, msg);
                        }
                    }
                }

            } else {//修改已发货数量
                MapContext mapContext1 = MapContext.newOne();
                mapContext1.put("id", productId);
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
                MapContext map = MapContext.newOne();
                map.put("id", oneById.getCustomOrderId());
                map.put("status", OrderStatus.SHIPPED.getValue());
                map.put("deliveryDate", new Date());
                map.put("timeConsuming", timeConsuming);
                customOrderService.updateByMapContext(map);
                //向redis消息队列index通道发布消息
                CustomOrder byId = this.customOrderService.findById(oneById.getCustomOrderId());
                HashMap<String, Object> hashMap = new HashMap<>();
                hashMap.put("orderStatus",OrderStatus.SHIPPED.getValue());
                hashMap.put("orderNo",byId.getNewstoreOrderNo());
                String s = JSON.toJSONString(hashMap);
                stringRedisTemplate.convertAndSend("channel",s);
            }
        }

        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    @SysOperationLog(detail = "删除发货单", operationType = OperationType.DELETE, operationMoudule = OperationMoudule.DISPATCH)
    public RequestResult deleteById(String id) {
        DispatchBill dispatchBill = this.dispatchBillService.findById(id);
        //判断发货单是否存在
        if (dispatchBill == null) {
            return ResultFactory.generateSuccessResult();
        }
        //判断发货单是否是运输中
        if (dispatchBill.getStatus() != DispatchBillStatus.WAITING_FOR_PIECES.getValue()) {
            return ResultFactory.generateErrorResult(ErrorCodes.BIZ_DELIVERED_NOT_OPERATE_10082, AppBeanInjector.i18nUtil.getMessage("BIZ_DELIVERED_NOT_OPERATE_10082"));
        }
        this.dispatchBillService.deleteDispatchBillAndItemById(id);
        return ResultFactory.generateSuccessResult();
    }

    //	@Override
//	@Transactional(value = "transactionManager")
//	public RequestResult deliverById(MapContext mapContext, String id) {
//		DispatchBill dispatchBill = this.dispatchBillService.findById(id);
//		//判断发货单是否存在, 以及状态是否是待取件
//		if (dispatchBill == null || !dispatchBill.getStatus().equals(DispatchBillStatus.WAITING_FOR_PIECES.getValue())) {
//			return ResultFactory.generateResNotFoundResult();
//		}
//		String logisticsCompanyId = mapContext.getTypedValue("logisticsCompanyId", String.class);
//		//判断物流公司是否存在
//		if (logisticsCompanyId == null || !this.logisticsCompanyService.isExist(logisticsCompanyId)) {
//			return ResultFactory.generateResNotFoundResult();
//		}
//		mapContext.put(WebConstant.KEY_ENTITY_ID, id);
//		mapContext.put(WebConstant.KEY_ENTITY_STATUS, DispatchBillStatus.TRANSPORT.getValue());//配送单状态
//		mapContext.put("actualDate", DateUtil.getSystemDate());//配送日期
//		this.dispatchBillService.updateByMapContext(mapContext);
//
//
//		String orderId = this.finishedStockService.findById(dispatchBill.getFinishedStockId()).getOrderId();
//		CustomOrder order = this.customOrderService.findById(orderId);
//		FinishedStock finishedStock = this.finishedStockService.findById(dispatchBill.getFinishedStockId());
//		if(order.getStatus().equals(OrderStatus.TO_DISPATCH.getValue())){//如果订单的状态为待配送则修改为配送中
//			MapContext updateOrder = new MapContext();
//			updateOrder.put(WebConstant.KEY_ENTITY_ID,orderId);
//			updateOrder.put(WebConstant.KEY_ENTITY_STATUS,OrderStatus.DISPATCHING.getValue());
//			this.customOrderService.updateByMapContext(mapContext);
//		}
//
//
//		//全部发货则判断发货条目总数是否等于成品库包数
//		List<DispatchBillItem> dispatchBillItems = this.dispatchBillItemService.findListByOrderId(orderId);
//		if (dispatchBillItems.size() == finishedStock.getPackages()) {
//			//全部配送修改订单状态为已配送
//			MapContext updateOrder = new MapContext();
//			updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
//			updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.DISPATCHING.getValue());
//			this.customOrderService.updateByMapContext(updateOrder);
//			//全部配送修改配送单状态为全部配送
//			MapContext updateFinishedStock = new MapContext();
//			updateFinishedStock.put(WebConstant.KEY_ENTITY_ID, finishedStock.getId());
//			updateFinishedStock.put(WebConstant.KEY_ENTITY_STATUS, FinishedStockStatus.ALL_SHIPMENT.getValue());
//			this.finishedStockService.updateByMapContext(updateFinishedStock);
//		} else {
//			//存在配送单为待配送,则判断订单状态是否为待配送 是则修改
//			if (order.getStatus().equals(OrderStatus.TO_DISPATCH.getValue())) {
//				MapContext updateOrder = new MapContext();
//				updateOrder.put(WebConstant.KEY_ENTITY_ID, orderId);
//				updateOrder.put(WebConstant.KEY_ENTITY_STATUS, OrderStatus.DISPATCHING.getValue());
//				this.customOrderService.updateByMapContext(updateOrder);
//			}
//			//存在配送单为待配送,则判断成品库单状态是否是待配送 是则修改
//			if (finishedStock.getStatus().equals(FinishedStockStatus.UNSHIPPED.getValue())) {
//				MapContext updateFinishedStock = new MapContext();
//				updateFinishedStock.put(WebConstant.KEY_ENTITY_ID, finishedStock.getId());
//				updateFinishedStock.put(WebConstant.KEY_ENTITY_STATUS, FinishedStockStatus.PARTIAL_SHIPMENT.getValue());
//				this.finishedStockService.updateByMapContext(updateFinishedStock);
//			}
//		}
//		return ResultFactory.generateRequestResult(this.dispatchBillService.findById(id));
//	}
    @Override
    public RequestResult findDispatchBillInfo(String dispatchBillId) {
        DispatchBillDto dispatchBillDto = this.dispatchBillService.findDispatchPrintInfoById(dispatchBillId);
        if (dispatchBillDto == null) {
            return ResultFactory.generateResNotFoundResult();
        }
        List<Map> mapList = new ArrayList<>();
        List<DispatchBillItem> dispatchBillItems = this.dispatchBillItemService.findListByDispatchBillId(dispatchBillId);
        String productId = "";
        Map map = new HashMap();
        List typeList = new ArrayList();
        List countList = new ArrayList();
        for (DispatchBillItem dispatchBillItem : dispatchBillItems) {
            //产品信息
            String productIdValue = dispatchBillItem.getProductId();
            if (productIdValue == null || productIdValue.equals("")) {
                return ResultFactory.generateResNotFoundResult();
            }
            OrderProductDto oneProductById = this.orderProductService.findOneById(productIdValue);
            String productType = oneProductById.getTypeName();
            String productNo = oneProductById.getNo();
            String productSeries = oneProductById.getSeriesName();
            Integer productCount = oneProductById.getHasDeliverCount();
            String productNotes = oneProductById.getNotes();
            Date payTime = oneProductById.getPayTime();
            String receiverName = oneProductById.getReceiverName();
            //生产单信息
            String produceId = dispatchBillItem.getOrderProduceId();
            ProduceOrderDto oneProduceById = this.produceOrderService.findOneById(produceId);
            String produceTypeAndWay = oneProduceById.getTypeName() + "-" + oneProduceById.getWayName();
            Integer produceCount = oneProduceById.getCount();
            Integer type = oneProduceById.getType();
            String countValue;
            Basecode produceOrderType = basecodeService.findByTypeAndCode("produceOrderType", type.toString());
            countValue = produceOrderType.getRemark() + "=" + dispatchBillItem.getDeliverNum();
            if (productId.equals(productIdValue)) {
                typeList.add(produceTypeAndWay);
                if (!countValue.equals("")) {
                    countList.add(countValue);
                }
            } else {
                Map map1 = new HashMap();
                List typeList1 = new ArrayList();
                List countList1 = new ArrayList();
                typeList1.add(produceTypeAndWay);
                if (!countValue.equals("")) {
                    countList1.add(countValue);
                }
                map1.put("productType", productType);
                map1.put("productNo", productNo);
                map1.put("productSeries", productSeries);
                map1.put("productCount", productCount);
                map1.put("productNotes", productNotes);
                map1.put("payTime", payTime);
                map1.put("receiverName", receiverName);
                map1.put("produceTypeAndWay", produceTypeAndWay);
                map1.put("produceTypeAndWay", produceTypeAndWay);
                map1.put("typeList", typeList1);
                map1.put("countList", countList1);
                mapList.add(map1);
                map = map1;
                typeList = typeList1;
                countList = countList1;
                productId = productIdValue;
            }
        }
        dispatchBillDto.setProducts(mapList);
        return ResultFactory.generateRequestResult(dispatchBillDto);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult uploadFiles(String id, List<MultipartFile> multipartFileList) {
        boolean isId = false;
        if (!id.equals("dispatchId")) {//如果Id已存在,则判断资源是否存在
            DispatchBill dispatchBill = this.dispatchBillService.findById(id);
            //判断发货单是否存在
            if (dispatchBill == null) {
                return ResultFactory.generateResNotFoundResult();
            }
            isId = true;
        }
        List imgList = new ArrayList();
        for (MultipartFile multipartFile : multipartFileList) {
            UploadFiles uploadFiles = new UploadFiles();
            if (isId) {
                uploadFiles.setResourceId(id);
            }
            uploadFiles.setCreated(DateUtil.getSystemDate());
            uploadFiles.setCompanyId(WebUtils.getCurrCompanyId());
            uploadFiles.setCreator(WebUtils.getCurrUserId());
            UploadInfo uploadInfo = AppBeanInjector.baseFileUploadComponent.doUploadByModule(UploadType.TEMP, multipartFile, UploadResourceType.DISPATCH_BILL, id);
            uploadFiles.setName(uploadInfo.getFileName());
            uploadFiles.setPath(uploadInfo.getRelativePath());
            uploadFiles.setFullPath(WebUtils.getDomainUrl() + uploadInfo.getRelativePath());
            uploadFiles.setMime(uploadInfo.getFileMimeType().getRealType());
            uploadFiles.setOriginalMime(uploadInfo.getFileMimeType().getOriginalType());
            uploadFiles.setStatus(UploadType.TEMP.getValue());
            uploadFiles.setResourceType(UploadResourceType.DISPATCH_BILL.getType());
            this.uploadFilesService.add(uploadFiles);
            imgList.add(uploadFiles);
        }
        return ResultFactory.generateRequestResult(imgList);
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult updateDispatch(MapContext mapContext, String id) {
        DispatchBill byId = this.dispatchBillService.findById(id);
        if (byId == null) {
            return ResultFactory.generateResNotFoundResult();
        }
        //修改发货单
        String logisticsCompanyId = mapContext.getTypedValue("logisticsCompanyId", String.class);
        String logisticsNo = mapContext.getTypedValue("logisticsNo", String.class);
        mapContext.put("id", id);
        int i = dispatchBillService.updateByMapContext(mapContext);
        List<DispatchBillItem> listByDispatchBillId = this.dispatchBillItemService.findListByDispatchBillId(id);
        MapContext map = MapContext.newOne();
        map.put("logisticsCompanyId", logisticsCompanyId);
        map.put("logisticsNo", logisticsNo);
        if (map.size() > 0) {
            LogisticsCompany byLogisticId = this.logisticsCompanyService.findByLogisticId(logisticsCompanyId);
            for (DispatchBillItem dispatchBillItem : listByDispatchBillId) {
                String produceId = dispatchBillItem.getOrderProduceId();
                ProduceOrderDto oneById1 = this.produceOrderService.findOneById(produceId);
                Basecode produceOrderType = this.basecodeService.findByTypeAndCode("produceOrderType", oneById1.getType().toString());
                String productId = dispatchBillItem.getProductId();
                OrderProductDto oneById = this.orderProductService.findOneById(productId);
                map.put("id", produceId);
                this.produceOrderService.updateByMapContext(map);
                if (byId.getLogisticsNo() == null || byId.getLogisticsNo().equals("")) {
                    CustomOrderLog log = new CustomOrderLog();
                    log.setCreated(new Date());
                    log.setCreator(WebUtils.getCurrUserId());
                    log.setName("发货");
                    log.setStage(OrderStage.DELIVERY.getValue());
                    log.setContent("订单产品:" + oneById.getNo() + "-" + produceOrderType.getValue() + "已发货" + "-" + byLogisticId.getName() + logisticsNo);
                    log.setCustomOrderId(oneById.getCustomOrderId());
                    customOrderLogService.add(log);
                }
            }
        }
        return ResultFactory.generateSuccessResult();
    }

    @Override
    @Transactional(value = "transactionManager")
    public RequestResult deleteFile(String fileId) {
        //判断资源是否存在
        UploadFiles byId = this.uploadFilesService.findById(fileId);
        if (byId == null) {
            return ResultFactory.generateSuccessResult();
        }
        this.uploadFilesService.deleteById(fileId);
        //清除本地文件
        AppBeanInjector.baseFileUploadComponent.deleteFileByDir(byId.getFullPath());
        return ResultFactory.generateSuccessResult();
    }

    @Override
    public RequestResult findDispatchPrintInfo(String id) {
        DispatchPrintTableDto dispatchPrintTableDto = this.dispatchBillService.findDispatchPrintInfo(id);
        dispatchPrintTableDto.setBodyDispatchBillItems(this.dispatchBillItemService.findListByDIdAndTypes(id, Arrays.asList(FinishedStockItemType.CABINET.getValue())));
        dispatchPrintTableDto.setDoorDispatchBillItems(this.dispatchBillItemService.findListByDIdAndTypes(id, Arrays.asList(FinishedStockItemType.DOOR_HOMEGROWN.getValue(), FinishedStockItemType.DOOR_OUTSOURCING.getValue(), FinishedStockItemType.SPECIAL_SUPPLY.getValue())));
        dispatchPrintTableDto.setHardwareDispatchBillItems(this.dispatchBillItemService.findListByDIdAndTypes(id, Arrays.asList(FinishedStockItemType.HARDWARE.getValue())));
        return ResultFactory.generateRequestResult(dispatchPrintTableDto);
    }

    @Override
    public RequestResult findDispatchBillCount(String branchId) {
        MapContext mapContext = this.dispatchBillPlanItemService.findCountByBranchId(branchId);
        List result = new ArrayList();
        int a = 0;
        for (int i = 0; i < 3; i++) {
            Map map = new HashMap();
            switch (a) {
                case 0:
                    map.put("name", "今日计划发货");
                    map.put("value", mapContext.getTypedValue("plann", Integer.class));
                    a = a + 1;
                    break;
                case 1:
                    map.put("name", "今日发货完成");
                    map.put("value", mapContext.getTypedValue("orver", Integer.class));
                    a = a + 1;
                    break;
                case 2:
                    map.put("name", "今日发货笔数");
                    map.put("value", mapContext.getTypedValue("num", Integer.class));
                    a = a + 1;
                    break;
            }
            result.add(map);
        }
        return ResultFactory.generateRequestResult(result);
    }


}
